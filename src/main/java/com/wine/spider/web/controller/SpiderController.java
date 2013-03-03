package com.wine.spider.web.controller;

import com.wine.spider.service.SpiderDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 11/25/12 Time: 5:19 PM To change this template use File | Settings |
 * File Templates.
 */
@Controller
@RequestMapping(value = "/spider")
public class SpiderController {

    @Autowired
    private SpiderDataService spiderDataService;

    @RequestMapping(value = "/site/{id}")
    public void build(@PathVariable("id")
    Long id, Model model) {
        spiderDataService.build(id);
        model.addAttribute("success", true);
    }

    @RequestMapping(value = "/all")
    public void build(Model model) {
        spiderDataService.build();
        model.addAttribute("success", true);
    }

}
