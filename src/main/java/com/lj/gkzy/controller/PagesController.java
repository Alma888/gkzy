package com.lj.gkzy.controller;

import com.lj.gkzy.common.response.ApiResponse;
import com.lj.gkzy.domain.vo.UserInfoVO;
import com.lj.gkzy.service.converter.UserInfoVoConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class PagesController {

    @RequestMapping("/showScore")
    public String showScore(){
        return "redirect:/showScore.html";
    }
    @RequestMapping("/showZhiYuan")
    public String showZhiYuan(){
        return "redirect:/zhiyuantuiijian.html";
    }

    @RequestMapping("/addFenshuxian")
    public String fenShuxian_add(){
        return "redirect:/luqufenshuxian_add.html";
    }

    @RequestMapping("/showFenshuxian")
    public String fenshuxian_list(){
        return "redirect:/luqufenshuxian_list.html";
    }
}
