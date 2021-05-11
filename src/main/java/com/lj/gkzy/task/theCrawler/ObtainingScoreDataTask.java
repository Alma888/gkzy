package com.lj.gkzy.task.theCrawler;


import com.lj.gkzy.domain.model.ObtainingScoreDataModel;
import com.lj.gkzy.service.ObtainingScoreDataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.util.Strings;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import static com.lj.gkzy.common.constant.ObtainingScoreDataConstant.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取学校录取分数情况的task
 *
 * @author liujing
 * @created 2021/04/03
 */

@Slf4j
@Component
@EnableScheduling
public class ObtainingScoreDataTask {

    @Resource
    private ObtainingScoreDataService obtainingScoreDataService;

    /**
     * 定时任务 每天8点刷取录取数据并落库
     */
    @PostConstruct
    @Scheduled(cron = "0 0 8 * * ?")
    public void refreshObtainingScoreData() throws IOException, InterruptedException {
        // 清空库 每天重新刷取
        obtainingScoreDataService.truncateTable();
        Thread.sleep(1000000);
        // 刷取西安科技大学录取数据（普通）
        obtainingXustScoreData();
        //刷取西北工业大学录取数据 （985）
         obtainingFactoryScoreData();
        //刷取西北大学录取数据 （211）
        obtainingXiBeiScoreData();
    }


    /**
     * 刷取西北工业大学的录取数据并落库
     */

    public void obtainingFactoryScoreData() throws IOException {
        try {
            log.info("==============开始获取【西北工业大学】录取信息==================");
            CloseableHttpClient client = HttpClientBuilder.create().build();
            String cookie = getCookie(Factory_HOME_PAGE_URL);
            HttpGet request = new HttpGet(Factory_HOME_PAGE_URL);
            request.setHeader("Cookie", cookie);
            CloseableHttpResponse response = client.execute(request);
            String resultStr = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            // 所有的省份集合
            List<String> provincesList = new ArrayList<>();

            // 所有的年份集合
            List<String> yearList = new ArrayList<>();

            // 所有的科目种类集合
            List<String> divisionOfClassList = new ArrayList<>();

            //所有类型集合
            List<String> typeList=new ArrayList<>();

            Document parse = Jsoup.parse(resultStr);
            // 解析科目种类
            Elements klmc = parse.getElementsByClass("klmc");
            klmc.forEach(a -> divisionOfClassList.add(a.text()));
            // 解析年份
            Elements zsnf = parse.getElementsByClass("zsnf");
            zsnf.forEach(a -> yearList.add(a.text()));
            // 解析省份
            Elements ssmc = parse.getElementsByClass("ssmc");
            ssmc.forEach(a -> provincesList.add(a.text()));
            //解析类型
            Elements zslx = parse.getElementsByAttribute("zslx");
            zslx.forEach(a ->typeList.add(a.text()));

            List<ObtainingScoreDataModel> obtainingScoreDataList = new ArrayList<>();
            for (String divisionOfClass : divisionOfClassList) {
                for (String year : yearList) {
                    for (String provinces : provincesList) {
                        for (String type : typeList) {
                            HttpGet getDataRequest = new HttpGet(String.format(Factory_DATA_SOURCE_URL, year, divisionOfClass, provinces,type));
                            setHeaders(getDataRequest);
                            getDataRequest.setHeader("Cookie", cookie);
                            String result = EntityUtils.toString(client.execute(getDataRequest).getEntity(), StandardCharsets.UTF_8);
                            Document data = Jsoup.parse(result);
                            Element tbody = data.getElementsByTag("tbody").get(1);
                            Elements tr = tbody.getElementsByTag("tr");
                            for (Element element : tr) {
                                ObtainingScoreDataModel obtainingScoreDataModel = new ObtainingScoreDataModel();
                                List<String> properties = new ArrayList<>();
                                Elements td = element.getElementsByTag("td");
                                for (Element element1 : td) {
                                    properties.add(element1.text());
                                }
                                if (properties.size() != 10) {
                                    continue;
                                }
                                obtainingScoreDataModel.setSchoolName(Factory_SCHOOL_NAME);
                                obtainingScoreDataModel.setYear(properties.get(0));
                                obtainingScoreDataModel.setProvinces(properties.get(1));
                                obtainingScoreDataModel.setAdmissionCategory(properties.get(3));
                                obtainingScoreDataModel.setDivisionOfClass(properties.get(2));
                                obtainingScoreDataModel.setTheNameOfTheProfessional(properties.get(4));
                                obtainingScoreDataModel.setHighestScore(getScore(properties.get(8)));
                                obtainingScoreDataModel.setLowestScore(getScore(properties.get(5)));
                                obtainingScoreDataModel.setAverageScore(getScore(properties.get(7)));
                                obtainingScoreDataModel.setControlScore(getScore(properties.get(9)));
                                obtainingScoreDataList.add(obtainingScoreDataModel);
                            }
                            log.info(String.format("%s:科目:[%s],年份:[%s],省份:[%s]获取数据完毕", Factory_SCHOOL_NAME, divisionOfClass, year, provinces));
                        }
                    }
                }
            }
            log.info(String.format("==============读取到的数据数%d=========", obtainingScoreDataList.size()));
            obtainingScoreDataService.insertBatch(obtainingScoreDataList);
            client.close();
            log.info("==============【西北工业大学】录取信息获取结束==================");
        } catch (IOException e) {
            log.error("ObtainingScoreDataTask.obtainingFactoryScoreData | 定时任务获取Factory录取数据时发生异常, e=", e);
            throw e;
        }
    }


    /**
     * 刷取西安科技大学的录取数据并落库
     */
    public void obtainingXustScoreData() throws IOException {
        try {
            log.info("==============开始获取【西安科技大学】录取信息==================");
            CloseableHttpClient client = HttpClientBuilder.create().build();
            String cookie = getCookie(XUST_HOME_PAGE_URL);
            HttpGet request = new HttpGet(XUST_HOME_PAGE_URL);
            request.setHeader("Cookie", cookie);
            //解决https证书安全认证问题
            request.setHeader("Cookie", "JSESSIONID=2FD7CD8756C551DAE242E51D1F411BAB");
            CloseableHttpResponse response = client.execute(request);
            String resultStr = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            // 所有的科目种类集合
            List<String> divisionOfClassList = new ArrayList<>();
            // 所有的年份集合
            List<String> yearList = new ArrayList<>();
            // 所有的省份集合
            List<String> provincesList = new ArrayList<>();
            Document parse = Jsoup.parse(resultStr);
            // 解析科目种类
            Elements klmcclick = parse.getElementsByClass("klmcclick");
            klmcclick.forEach(var -> divisionOfClassList.add(var.id()));
            // 解析年份
            Elements nfclick = parse.getElementsByClass("nfclick");
            nfclick.forEach(var -> yearList.add(var.id()));
            // 解析省份
            Elements ssmcclick = parse.getElementsByClass("ssmcclick ");
            ssmcclick.forEach(var -> provincesList.add(var.id()));
            List<ObtainingScoreDataModel> obtainingScoreDataList = new ArrayList<>();
            for (String divisionOfClass : divisionOfClassList) {
                for (String year : yearList) {
                    for (String provinces : provincesList) {
                        HttpGet getDataRequest = new HttpGet(String.format(XUST_DATA_SOURCE_URL, year, divisionOfClass, provinces));
                        setHeaders(getDataRequest);
                        getDataRequest.setHeader("Cookie", cookie);
                        String result = EntityUtils.toString(client.execute(getDataRequest).getEntity(), StandardCharsets.UTF_8);
                        Document data = Jsoup.parse(result);
                        Element tbody = data.getElementsByTag("tbody").get(0);
                        Elements tr = tbody.getElementsByTag("tr");
                        for (Element element : tr) {
                            ObtainingScoreDataModel obtainingScoreDataModel = new ObtainingScoreDataModel();
                            List<String> properties = new ArrayList<>();
                            Elements td = element.getElementsByTag("td");
                            for (Element element1 : td) {
                                properties.add(element1.text());
                            }
                            if (properties.size() != 13) {
                                continue;
                            }
                            obtainingScoreDataModel.setSchoolName(XUST_SCHOOL_NAME);
                            obtainingScoreDataModel.setYear(properties.get(0));
                            obtainingScoreDataModel.setProvinces(properties.get(1));
                            obtainingScoreDataModel.setAdmissionCategory(properties.get(2));
                            obtainingScoreDataModel.setDivisionOfClass(properties.get(3));
                            obtainingScoreDataModel.setBatch(properties.get(4));
                            obtainingScoreDataModel.setTheNameOfTheProfessional(properties.get(5));
                            obtainingScoreDataModel.setEnrollment(Integer.valueOf(properties.get(6)));
                            obtainingScoreDataModel.setHighestScore(getScore(properties.get(7)));
                            obtainingScoreDataModel.setLowestScore(getScore(properties.get(8)));
                            obtainingScoreDataModel.setAverageScore(getScore(properties.get(9)));
                            obtainingScoreDataModel.setControlScore(getScore(properties.get(10)));
                            obtainingScoreDataModel.setTheMinimumGap(getScore(properties.get(11)));
                            obtainingScoreDataModel.setDifferenceOfAverage(getScore(properties.get(12)));
                            obtainingScoreDataList.add(obtainingScoreDataModel);
                        }
                        log.info(String.format("%s:科目:[%s],年份:[%s],省份:[%s]获取数据完毕", XUST_SCHOOL_NAME, divisionOfClass, year, provinces));
                    }
                }
            }
            log.info(String.format("==============读取到的数据数%d=========", obtainingScoreDataList.size()));
            obtainingScoreDataService.insertBatch(obtainingScoreDataList);
            client.close();
            log.info("==============【西安科技大学】录取信息获取结束==================");
        } catch (IOException e) {
            log.error("ObtainingScoreDataTask.obtainingXustScoreData | 定时任务获取xust录取数据时发生异常, e=", e);
            throw e;
        }
    }


    /**
     * 刷取西北大学的录取数据并落库
     */
    public void obtainingXiBeiScoreData() throws IOException {
        try {
            log.info("=============开始获取【西北大学】录取信息=================");
            CloseableHttpClient client = HttpClientBuilder.create().build();
            String cookie = getCookie(XiBei_HOME_PAGE_URL);
            HttpGet request = new HttpGet(XiBei_HOME_PAGE_URL);
            request.setHeader("Cookie", cookie);
            //解决https证书安全认证问题
            request.setHeader("Cookie", "JSESSIONID=2FD7CD8756C551DAE242E51D1F411BAB");
            CloseableHttpResponse response = client.execute(request);
            String resultStr = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);

            // 所有的科目种类集合
            List<String> divisionOfClassList = new ArrayList<>();
            // 所有的年份集合
            List<String> yearList = new ArrayList<>();
            // 所有的省份集合
            List<String> provincesList = new ArrayList<>();
            //所有的批次集合
            List<String> batchList=new ArrayList<>();
            //所有类型集合
            List<String> typeList=new ArrayList<>();

            Document parse = Jsoup.parse(resultStr);
            // 解析科目种类
            Elements scienceClass = parse.getElementsByAttribute("scienceClass");
            scienceClass.forEach(option -> divisionOfClassList.add(option.text()));
            // 解析年份
            Elements years = parse.getElementsByAttribute("year");
            years.forEach(option -> yearList.add(option.text()));
            // 解析省份
            Elements cityName = parse.getElementsByAttribute("cityName ");
            cityName.forEach(option -> provincesList.add(option.text()));
            //解析类型（普通文理）
            Elements type = parse.getElementsByAttribute("type ");
            type.forEach(option ->typeList.add(option.text()));
            //解析录取批次
            Elements batch = parse.getElementsByAttribute("batch ");
            batch.forEach(option -> batchList.add(option.text()));

            List<ObtainingScoreDataModel> obtainingScoreDataList = new ArrayList<>();
            for (String divisionOfClass : divisionOfClassList) {
                for (String year : yearList) {
                    for (String provinces : provincesList) {
                        for (String types : typeList) {
                            for (String batches : batchList) {
                                    HttpGet getDataRequest = new HttpGet(String.format(XiBei_DATA_SOURCE_URL, year, divisionOfClass, provinces, types,batches));
                                    setHeaders(getDataRequest);
                                    getDataRequest.setHeader("Cookie", cookie);
                                    String result = EntityUtils.toString(client.execute(getDataRequest).getEntity(), StandardCharsets.UTF_8);
                                    Document data = Jsoup.parse(result);
                                    Element tbody = data.getElementsByTag("tbody").get(1);
                                    Elements tr = tbody.getElementsByTag("tr");
                                    for (Element element : tr) {
                                        ObtainingScoreDataModel obtainingScoreDataModel = new ObtainingScoreDataModel();
                                        List<String> properties = new ArrayList<>();
                                        Elements td = element.getElementsByTag("td");
                                        for (Element element1 : td) {
                                            properties.add(element1.text());
                                        }
                                        if (properties.size() != 9) {
                                            continue;
                                        }
                                        obtainingScoreDataModel.setSchoolName(XiBei_SCHOOL_NAME);
                                        obtainingScoreDataModel.setYear(year);
                                        obtainingScoreDataModel.setProvinces(provinces);
                                        obtainingScoreDataModel.setAdmissionCategory(types);
                                        obtainingScoreDataModel.setDivisionOfClass(divisionOfClass);
                                        obtainingScoreDataModel.setBatch(properties.get(2));
                                        obtainingScoreDataModel.setTheNameOfTheProfessional(properties.get(1));
                                        obtainingScoreDataModel.setLowestScore(getScore(properties.get(4)));
                                        obtainingScoreDataModel.setAverageScore(getScore(properties.get(6)));
                                        obtainingScoreDataModel.setControlScore(getScore(properties.get(3)));
                                        obtainingScoreDataModel.setTheMinimumGap(getScore(properties.get(9)));
                                        obtainingScoreDataModel.setDifferenceOfAverage(getScore(properties.get(8)));
                                        obtainingScoreDataList.add(obtainingScoreDataModel);
                                    }
                                    log.info(String.format("%s:科目:[%s],年份:[%s],省份:[%s]获取数据完毕",XiBei_SCHOOL_NAME, divisionOfClass, year, provinces));
                                }
                            }
                        }
                    }
                }
            log.info(String.format("==============读取到的数据数%d=========", obtainingScoreDataList.size()));
            obtainingScoreDataService.insertBatch(obtainingScoreDataList);
            client.close();
            log.info("========【西北大学】录取信息获取结束======");
        } catch (IOException e) {
            log.error("ObtainingScoreDataTask.obtainingXiBeiScoreData | 定时任务获取XiBei录取数据时发生异常, e=", e);
            throw e;
        }
    }


    /**
     * 根据给的Url 获取当前网站设定的cookie
     * @param url 请求的url地址
     * @return cookie信息
     */
    private String getCookie(String url) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        try {
            setHeaders(request);
            Header[] headers = client.execute(request).getHeaders("Set-Cookie");
            return headers[0].getValue();
        } catch (IOException e) {
            log.error("ObtainingScoreDataTask.getCookie | 定时任务获取学校录取数据获取cookie时发生异常, url:{}, e=", url, e);
            throw e;
        }
    }

    /**
     * 对HttpGet及HttpPost请求进行设置Header 防止网站反爬措施
     *
     * @return 请求
     */
    private void setHeaders(HttpRequestBase request) {
        request.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        request.setHeader("Accept-Encoding", "gzip, deflate, br");
        request.setHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,fr;q=0.7");
        request.setHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,fr;q=0.7");
        request.setHeader("Cache-Control", "max-age=0");
        request.setHeader("Connection", "keep-alive");
        request.setHeader("sec-ch-ua", "\"Chromium\";v=\"88\", \"Google Chrome\";v=\"88\", \";Not A Brand\";v=\"99\"");
        request.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 11_2_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.192 Safari/537.36");
    }

    /**
     * 抓取数据 通过String获取Double类型分数 为空返回0
     * @return 分数
     */
    private Double getScore(String score) {
        if (Strings.isBlank(score)) {
            return 0.0;
        }
        return Double.valueOf(score);
    }
}