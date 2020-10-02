package com.example.demo.service;

import com.example.demo.repository.ItemRepository;
import com.example.demo.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class ItemService {

    @Autowired
    ItemRepository repo;

    public List<Item> getAllItems() {
        List<Item> result = new LinkedList<>();
        repo.findAll().forEach(result::add);
        return result;
    }

    public void saveItem(Item item) {
        repo.save(item);
    }

    public Optional<Item> getById(int id) {
        return repo.findById(id);
    }

    public void delete(int id) {
        repo.deleteById(id);
    }

}
