package com.jacr.api_rest.services;

import com.jacr.api_rest.models.Book;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookPriceCalculatorTest {

    @Test
    void calculator() {
        //Configuración de la prueba
        Book book = new Book(1L,"Las 48 leyes del poder", "Robert", 650, 99.9, LocalDate.now(), true);
        System.out.println("El precio del libro es: " + book.getPrice());
        BookPriceCalculator calculator = new BookPriceCalculator();

        //se ejecuta el comportamiento de la prueba
        double price = calculator.calculator(book);
        System.out.println(price);

        //Comprobaciones aserciones
        assertTrue(price > 0);

    }
}