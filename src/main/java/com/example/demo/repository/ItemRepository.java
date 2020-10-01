package com.example.demo.repository;

import com.example.demo.model.Item;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ItemRepository extends CrudRepository<Item, Integer> {
    @Modifying
    @Query(value = "UPDATE ITEM SET NAME = ?, PRICE = ? WHERE ID = ?", nativeQuery = true)
    void update(String name, BigDecimal price, int id);

}
