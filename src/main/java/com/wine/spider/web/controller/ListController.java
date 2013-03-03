package com.wine.spider.web.controller;

import com.wine.spider.entity.ListEntity;
import com.wine.spider.entity.SearchEntity;
import com.wine.spider.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 12/9/12 Time: 10:28 AM To change this template use File | Settings |
 * File Templates.
 */
@Controller
@RequestMapping("/list")
public class ListController {

    @Autowired
    private SearchService searchService;

    @RequestMapping("/{searchId}/list")
    public void list(@PathVariable("searchId")
    Long searchId, Model model) {
        SearchEntity searchEntity = searchService.get(searchId);
        model.addAttribute("rows", searchEntity.getListEntityList());
    }

    @RequestMapping("/{searchId}/save")
    public void save(@PathVariable("searchId")
    Long searchId, ListEntity listEntity, Model model) {
        Assert.notNull(searchId);
        Assert.notNull(listEntity);
        searchService.addList(searchId, listEntity);
        model.addAttribute("success", "true");
    }
}
