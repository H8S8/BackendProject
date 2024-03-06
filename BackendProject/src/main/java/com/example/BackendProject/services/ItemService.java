package com.example.BackendProject.services;

import com.example.BackendProject.models.Item;
import com.example.BackendProject.models.ProductType;
import com.example.BackendProject.models.Stock;
import com.example.BackendProject.repositories.ItemRepository;
import com.example.BackendProject.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;
    @Autowired
    StockRepository stockRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Optional<Item> findItemById(Long id) {
        return itemRepository.findById(id);
    }

    public List<Item> findByProductType(ProductType productType) {
        return itemRepository.findByProductType(productType);
    }

    public Item saveItem(Item createdItem) {
        itemRepository.save(createdItem);
        return createdItem;
    }

    public void deleteItem(Long id) {
        Item item = findItemById(id).get();
        for(Stock stock: item.getStocks()){
            stockRepository.deleteById(stock.getId());
        }
        itemRepository.deleteById(id);
    }

    public Item updateItem(Item item, Long id) {
        Item itemToUpdate = itemRepository.findById(id).get();
        itemToUpdate.setName(item.getName());
        itemRepository.save(itemToUpdate);
        return itemToUpdate;
    }


}
