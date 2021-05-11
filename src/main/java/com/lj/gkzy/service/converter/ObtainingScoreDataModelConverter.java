package com.lj.gkzy.service.converter;

import com.lj.gkzy.domain.dto.ObtainingScoreDataDO;
import com.lj.gkzy.domain.model.ObtainingScoreDataModel;
import com.lj.gkzy.domain.vo.VolunteerRecommendVO;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author liujing
 * @version $Id: ObtainingScoreDataModelConverter.java, v 0.1 2021-04-03 14:05:07 liujing Exp $$
 */
@Service
public class ObtainingScoreDataModelConverter {

    /**
     * Convert ObtainingScoreDataDO to ObtainingScoreDataModel
     *
     * @param obtainingScoreDataDO
     * @return
     */
    public static ObtainingScoreDataModel convertToObtainingScoreDataModel(ObtainingScoreDataDO obtainingScoreDataDO) {
        if (obtainingScoreDataDO == null) {
            return null;
        }
        ObtainingScoreDataModel obtainingScoreDataModel = new ObtainingScoreDataModel();

        obtainingScoreDataModel.setId(obtainingScoreDataDO.getId());
        obtainingScoreDataModel.setSchoolName(obtainingScoreDataDO.getSchoolName());
        obtainingScoreDataModel.setYear(obtainingScoreDataDO.getYear());
        obtainingScoreDataModel.setProvinces(obtainingScoreDataDO.getProvinces());
        obtainingScoreDataModel.setAdmissionCategory(obtainingScoreDataDO.getAdmissionCategory());
        obtainingScoreDataModel.setDivisionOfClass(obtainingScoreDataDO.getDivisionOfClass());
        obtainingScoreDataModel.setBatch(obtainingScoreDataDO.getBatch());
        obtainingScoreDataModel.setTheNameOfTheProfessional(obtainingScoreDataDO.getTheNameOfTheProfessional());
        obtainingScoreDataModel.setProfessionalDescription(obtainingScoreDataDO.getProfessionalDescription());
        obtainingScoreDataModel.setEnrollment(obtainingScoreDataDO.getEnrollment());
        obtainingScoreDataModel.setHighestScore(obtainingScoreDataDO.getHighestScore());
        obtainingScoreDataModel.setLowestScore(obtainingScoreDataDO.getLowestScore());
        obtainingScoreDataModel.setAverageScore(obtainingScoreDataDO.getAverageScore());
        obtainingScoreDataModel.setControlScore(obtainingScoreDataDO.getControlScore());
        obtainingScoreDataModel.setTheMinimumGap(obtainingScoreDataDO.getTheMinimumGap());
        obtainingScoreDataModel.setDifferenceOfAverage(obtainingScoreDataDO.getDifferenceOfAverage());
        obtainingScoreDataModel.setCreated(obtainingScoreDataDO.getCreated());
        obtainingScoreDataModel.setUpdated(obtainingScoreDataDO.getUpdated());

        return obtainingScoreDataModel;
    }

    public static List<ObtainingScoreDataModel> convertToObtainingScoreDataModels(List<ObtainingScoreDataDO> obtainingScoreDataDOList) {
        if (CollectionUtils.isEmpty(obtainingScoreDataDOList)) {
            return new ArrayList<>();
        }
        List<ObtainingScoreDataModel> result = new ArrayList<>(obtainingScoreDataDOList.size());
        obtainingScoreDataDOList.forEach(item -> result.add(convertToObtainingScoreDataModel(item)));
        return result;
    }
    /**
     * Convert ObtainingScoreDataModel to ObtainingScoreDataDO
     *
     * @param obtainingScoreDataModel
     * @return
     */
    public static ObtainingScoreDataDO convertToObtainingScoreDataDO(ObtainingScoreDataModel obtainingScoreDataModel) {
        if (obtainingScoreDataModel == null) {
            return null;
        }
        ObtainingScoreDataDO obtainingScoreDataDO = new ObtainingScoreDataDO();

        obtainingScoreDataDO.setId(obtainingScoreDataModel.getId());
        obtainingScoreDataDO.setSchoolName(obtainingScoreDataModel.getSchoolName());
        obtainingScoreDataDO.setYear(obtainingScoreDataModel.getYear());
        obtainingScoreDataDO.setProvinces(obtainingScoreDataModel.getProvinces());
        obtainingScoreDataDO.setAdmissionCategory(obtainingScoreDataModel.getAdmissionCategory());
        obtainingScoreDataDO.setDivisionOfClass(obtainingScoreDataModel.getDivisionOfClass());
        obtainingScoreDataDO.setBatch(obtainingScoreDataModel.getBatch());
        obtainingScoreDataDO.setTheNameOfTheProfessional(obtainingScoreDataModel.getTheNameOfTheProfessional());
        obtainingScoreDataDO.setProfessionalDescription(obtainingScoreDataModel.getProfessionalDescription());
        obtainingScoreDataDO.setEnrollment(obtainingScoreDataModel.getEnrollment());
        obtainingScoreDataDO.setHighestScore(obtainingScoreDataModel.getHighestScore());
        obtainingScoreDataDO.setLowestScore(obtainingScoreDataModel.getLowestScore());
        obtainingScoreDataDO.setAverageScore(obtainingScoreDataModel.getAverageScore());
        obtainingScoreDataDO.setControlScore(obtainingScoreDataModel.getControlScore());
        obtainingScoreDataDO.setTheMinimumGap(obtainingScoreDataModel.getTheMinimumGap());
        obtainingScoreDataDO.setDifferenceOfAverage(obtainingScoreDataModel.getDifferenceOfAverage());
        obtainingScoreDataDO.setCreated(obtainingScoreDataModel.getCreated());
        obtainingScoreDataDO.setUpdated(obtainingScoreDataModel.getUpdated());

        return obtainingScoreDataDO;
    }

    public static List<ObtainingScoreDataDO> convertToObtainingScoreDataDOs(List<ObtainingScoreDataModel> obtainingScoreDataModelList) {
        if (CollectionUtils.isEmpty(obtainingScoreDataModelList)) {
            return new ArrayList<>();
        }
        List<ObtainingScoreDataDO> result = new ArrayList<>();
        obtainingScoreDataModelList.forEach(item -> result.add(convertToObtainingScoreDataDO(item)));
        return result;
    }

    /**
     * Convert VolunteerRecommendVO to ObtainingScoreDataModel
     * @param volunteerRecommendVO
     * @return
     */
    public static ObtainingScoreDataModel convertToObtainingScoreDataModel(VolunteerRecommendVO volunteerRecommendVO) {
        if (volunteerRecommendVO == null) {
            return null;
        }
        ObtainingScoreDataModel obtainingScoreDataModel = new ObtainingScoreDataModel();

        obtainingScoreDataModel.setSchoolName(volunteerRecommendVO.getSchoolName());
        obtainingScoreDataModel.setYear(volunteerRecommendVO.getYear());
        obtainingScoreDataModel.setProvinces(volunteerRecommendVO.getProvinces());
        obtainingScoreDataModel.setAdmissionCategory(volunteerRecommendVO.getAdmissionCategory());
        obtainingScoreDataModel.setDivisionOfClass(volunteerRecommendVO.getDivisionOfClass());
        obtainingScoreDataModel.setBatch(volunteerRecommendVO.getBatch());
        obtainingScoreDataModel.setTheNameOfTheProfessional(volunteerRecommendVO.getTheNameOfTheProfessional());
        obtainingScoreDataModel.setProfessionalDescription(volunteerRecommendVO.getProfessionalDescription());
        obtainingScoreDataModel.setEnrollment(volunteerRecommendVO.getEnrollment());
        obtainingScoreDataModel.setHighestScore(volunteerRecommendVO.getHighestScore());
        obtainingScoreDataModel.setLowestScore(volunteerRecommendVO.getLowestScore());
        obtainingScoreDataModel.setAverageScore(volunteerRecommendVO.getAverageScore());
        obtainingScoreDataModel.setControlScore(volunteerRecommendVO.getControlScore());
        obtainingScoreDataModel.setTheMinimumGap(volunteerRecommendVO.getTheMinimumGap());
        obtainingScoreDataModel.setDifferenceOfAverage(volunteerRecommendVO.getDifferenceOfAverage());

        return obtainingScoreDataModel;
    }

    /**
     * Convert ObtainingScoreDataModel to VolunteerRecommendVO
     * @param obtainingScoreDataModel
     * @return
     */
    public static VolunteerRecommendVO convertToVolunteerRecommendVO(ObtainingScoreDataModel obtainingScoreDataModel) {
        if (obtainingScoreDataModel == null) {
            return null;
        }
        VolunteerRecommendVO volunteerRecommendVO = new VolunteerRecommendVO();

        volunteerRecommendVO.setSchoolName(obtainingScoreDataModel.getSchoolName());
        volunteerRecommendVO.setYear(obtainingScoreDataModel.getYear());
        volunteerRecommendVO.setProvinces(obtainingScoreDataModel.getProvinces());
        volunteerRecommendVO.setAdmissionCategory(obtainingScoreDataModel.getAdmissionCategory());
        volunteerRecommendVO.setDivisionOfClass(obtainingScoreDataModel.getDivisionOfClass());
        volunteerRecommendVO.setBatch(obtainingScoreDataModel.getBatch());
        volunteerRecommendVO.setTheNameOfTheProfessional(obtainingScoreDataModel.getTheNameOfTheProfessional());
        volunteerRecommendVO.setProfessionalDescription(obtainingScoreDataModel.getProfessionalDescription());
        volunteerRecommendVO.setEnrollment(obtainingScoreDataModel.getEnrollment());
        volunteerRecommendVO.setHighestScore(obtainingScoreDataModel.getHighestScore());
        volunteerRecommendVO.setLowestScore(obtainingScoreDataModel.getLowestScore());
        volunteerRecommendVO.setAverageScore(obtainingScoreDataModel.getAverageScore());
        volunteerRecommendVO.setControlScore(obtainingScoreDataModel.getControlScore());
        volunteerRecommendVO.setTheMinimumGap(obtainingScoreDataModel.getTheMinimumGap());
        volunteerRecommendVO.setDifferenceOfAverage(obtainingScoreDataModel.getDifferenceOfAverage());

        return volunteerRecommendVO;
    }

    public static List<VolunteerRecommendVO> convertToVolunteerRecommendVOs(List<ObtainingScoreDataModel> obtainingScoreDataModelList) {
        if (CollectionUtils.isEmpty(obtainingScoreDataModelList)) {
            return new ArrayList<>();
        }
        List<VolunteerRecommendVO> result = new ArrayList<>(obtainingScoreDataModelList.size());
        obtainingScoreDataModelList.forEach(item -> result.add(convertToVolunteerRecommendVO(item)));
        return result;
    }
}