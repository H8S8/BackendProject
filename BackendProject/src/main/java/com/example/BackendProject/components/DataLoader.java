package com.example.BackendProject.components;

import com.example.BackendProject.models.Item;
import com.example.BackendProject.models.ProductType;
import com.example.BackendProject.models.Stock;
import com.example.BackendProject.repositories.ItemRepository;
import com.example.BackendProject.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    StockRepository stockRepository;

    public DataLoader(){}

    @Override
    public void run(ApplicationArguments args) throws Exception{

        //Items

        Item milk = new Item("Semi-skimmed milk", ProductType.DAIRY, 5);
        itemRepository.save(milk);
        Item carrot = new Item("Carrot", ProductType.FRUITANDVEG, 3);
        itemRepository.save(carrot);
        Item chocolate = new Item("Mint Chocolote Caramel", ProductType.DAIRY, 15);
        itemRepository.save(chocolate);


        //Stock
        Stock stock1 = new Stock(milk, 50, "11/03/2024");
        stockRepository.save(stock1);
        Stock stock2 = new Stock(carrot, 100, "15/03/2024");
        stockRepository.save(stock2);
        Stock stock3 = new Stock(chocolate, 75, "30/09/2024");
        stockRepository.save(stock3);

    }




}
