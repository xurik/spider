package com.wine.spider.web.controller;

import com.wine.spider.entity.SearchEntity;
import com.wine.spider.entity.SiteEntity;
import com.wine.spider.service.SearchService;
import com.wine.spider.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 12/8/12 Time: 5:28 PM To change this template use File | Settings |
 * File Templates.
 */
@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SiteService   siteService;
    @Autowired
    private SearchService searchService;

    @RequestMapping("/{siteId}")
    public String index(@PathVariable("siteId")
    String siteId, Model model) {
        model.addAttribute("siteId", siteId);
        return "search";
    }

    @RequestMapping("/{siteId}/list")
    public void list(@PathVariable("siteId")
    Long siteId, Model model) {
        SiteEntity siteEntity = siteService.get(siteId);
        model.addAttribute("rows", siteEntity.getSearchEntityList());
    }

    @RequestMapping("/{siteId}/save")
    public void save(@PathVariable("siteId")
    Long siteId, SearchEntity searchEntity, Model model) {
        Assert.notNull(siteId);
        Assert.notNull(searchEntity);
        siteService.addSearch(siteId, searchEntity);
        model.addAttribute("success", "true");
    }
}
