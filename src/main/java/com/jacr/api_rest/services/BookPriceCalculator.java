package com.jacr.api_rest.services;

import com.jacr.api_rest.persistence.models.Book;

public class BookPriceCalculator {
    public double calculator(Book book){
        double price = book.getPrice();

        if(book.getPages() > 300){
            price += 5;
        }
        //envio
        price += 5.3;
        return price;
    }
}
