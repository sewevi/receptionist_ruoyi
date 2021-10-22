package com.ruoyi.web.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author xdayi@linewell.com
 * @Description
 * @date 2021/10/22
 */
@Controller
public class UrlController {
    @RequestMapping(value="/hi",method= RequestMethod.GET)
    public String hi() {
        return "index";
    }

}
