package com.jacr.api_rest;

import com.jacr.api_rest.models.Book;
import com.jacr.api_rest.repositories.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class ApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestApplication.class, args);
		/*
		A continuación es una forma de probar y traer el repositorio utilizando ApplicationContext para interectuar
		con las bases de datos.
		pero no es recomendable para eso se hace en los controllers es solamente probar
		*/
		/*
		//Inicialización del contexto de la aplicación Spring
		ApplicationContext context = SpringApplication.run(ApiRestApplication.class, args);
		//Obtención del bean BookRepository del contexto de Spring (instancia)
		BookRepository repository = context.getBean(BookRepository.class);

		//CRUD
		//crear libro
		Book book1 = new Book(null,"Las 48 leyes del poder", "Robert", 650, 99.99, LocalDate.of(2018,12,1),true);
		Book book2 = new Book(null,"Homo Deus", "Yuval Noah", 450, 29.99, LocalDate.of(2018,12,1),true);
		Book book3 = new Book(null,"Homo Sapiens", "Yuval Noah", 450, 29.99, LocalDate.of(2013,12,1),true);

		//recuperar libros
		System.out.println("Numeros de libros en base de datos: "+ repository.findAll().size());

		//almacenar todos los libros
		repository.save(book1);
		repository.save(book2);
		repository.save(book3);

		//recuperar todos los libros
		System.out.println("Numeros de libros en base de datos: "+ repository.findAll().size());

		//borrar un libro
		repository.deleteById(1L);

		//recuperar todos los libros
		System.out.println("Numeros de libros en base de datos: "+ repository.findAll().size());

		 */
	}

}
