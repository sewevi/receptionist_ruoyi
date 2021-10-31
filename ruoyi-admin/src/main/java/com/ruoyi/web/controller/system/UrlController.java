package com.ruoyi.web.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UrlController {
    @RequestMapping(value="/hi",method= RequestMethod.GET)
    public String hi() {
        return "index";
    }

}

