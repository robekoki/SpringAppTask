package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.demo.model.Item;
import com.example.demo.service.ItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Optional;

@Controller
public class ItemCrud {
    @Autowired
    ItemService service;

    Item tempItem;

    @GetMapping("/newItem")
    public String createItem(Model model) {
        model.addAttribute("Item", new Item());
        return "newItem";
    }

    @PostMapping("/createItem")
    public String itemSubmit(@ModelAttribute Item item, Model model) {
        model.addAttribute("item", item);
        service.saveItem(item);
        return "createItemSuccess";

    }

    @GetMapping("/getAllItems")
    public String getAll(Model model) {
        model.addAttribute("items", service.getAllItems());
        return "getAllItems";
    }

    @GetMapping("/getItem")
    public String getItem(Model model) {
        model.addAttribute("item", new Item());
        return "/getItem";
    }

    @PostMapping("/getItem")
    public String showItem(Item item, Model model) {
        Optional<Item> optional = service.getById(item.getId());
        if (optional.isPresent()) {
            model.addAttribute("item", optional.get());
            tempItem = optional.get();
            return "/showItem";
        } else {
            return "/noItemFound";
        }
    }

    @PostMapping("/delete")
    public String deleteItem(@RequestParam int id, Model model) {
        service.delete(id);
        return "delete";
    }

    @GetMapping("/update") //FIXME
    public String updateItem(Model model) {
        model.addAttribute("item", tempItem);
        return "update";
    }

    @PostMapping("/update") //FIXME
    public String performUpdate(@ModelAttribute Item item, Model model) {
        System.out.println(item);
        item.setId(tempItem.getId());
        service.saveItem(item);
        return "updated";
    }

}