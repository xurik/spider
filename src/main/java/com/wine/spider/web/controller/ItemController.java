package com.wine.spider.web.controller;

import com.wine.spider.entity.ItemEntity;
import com.wine.spider.entity.ListEntity;
import com.wine.spider.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 12/9/12 Time: 1:58 PM To change this template use File | Settings |
 * File Templates.
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ListService listService;

    @RequestMapping("/{listId}/list")
    public void list(@PathVariable("listId")
    Long listId, Model model) {
        ListEntity listEntity = listService.get(listId);
        model.addAttribute("rows", listEntity.getItemEntityList());
    }

    @RequestMapping("/{listId}/save")
    public void save(@PathVariable("listId")
    Long listId, ItemEntity itemEntity, Model model) {
        Assert.notNull(listId);
        Assert.notNull(itemEntity);
        listService.addItem(listId, itemEntity);
        model.addAttribute("success", "true");
    }
}
