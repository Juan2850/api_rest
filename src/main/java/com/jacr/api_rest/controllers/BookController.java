package com.jacr.api_rest.controllers;

import com.jacr.api_rest.models.Book;
import com.jacr.api_rest.repositories.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
//Niega la autorización a los endpoints a todos tipos de usuarios
@PreAuthorize("denyAll()")
public class BookController {
    private final Logger log= LoggerFactory.getLogger(BookController.class);
    //CRUD SOBRE LA ENTIDAD BOOK

    //Inyectamos el atributo BookRepository y utilizando un constructor
    private BookRepository bookRepository;
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Operation(
            summary = "Listar",
            description = "Listar todos los libros"

    )
    //listar todos los books desde base de datos
    /*
    * http://localhost:8080/api/books
    * */
    @GetMapping("/books")
    @PreAuthorize("hasAuthority('READ')")
    public List<Book> findAll(){
        //recuperar y devolver los libros de bases de datos
        return bookRepository.findAll();
    }
    @Operation(
            summary = "Encontrar libro por id",
            description = "Buscar un libro por clave primaria id Long"

    )
    //traer un solo book por id desde las bases de datos
    @GetMapping("/books/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Book> findOneById(@Parameter(description = "Buscar un libro por clave primaria id Long") @PathVariable Long id){
        Optional<Book> bookOpt = bookRepository.findById(id);
        /*
        Opcion 1:
        pero no es recomendable ya que si no hay libro devuelve un null esto devuelve un estado de 200ok
        mejor utilizar devolver un estado de 404not-found json
        * */
        /*
        if(bookOpt.isPresent()){
            return bookOpt.get();
        }else {
            return null;
        }
        */

        //opcion 2
        if(bookOpt.isPresent()){
            return ResponseEntity.ok(bookOpt.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Crear un nuevo libro",
            description = "Cree un libro sin usar el id"

    )
    //crear un nuevo book en bases de datos
    @PostMapping("/books")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<Book> saveBook(@RequestBody  Book book /*Recibir cabecera*/ , @RequestHeader HttpHeaders headers){
        System.out.println(headers.get("User-Agent"));
        //Guardar el libro recibido por parametro en la base de datos
       if(book.getId() != null) {//quiere decir que existe el id por tanto no es creamos
           log.warn("trying to create a book with id");
           System.out.println("trying to create a book with id");
           return ResponseEntity.badRequest().build();
       }else {
           return ResponseEntity.ok(bookRepository.save(book));
       }
    }
    @Operation(
            summary = "Actualizar",
            description = "Actualize un libro existente sin usar el id"

    )
    //actualizar un book existente traído por id en bases de datos
    @PutMapping("/books/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<?> updateBook(@RequestBody Book book, @PathVariable Long id){
        Optional<Book> bookExistente = bookRepository.findById(id);
        if(bookExistente.isPresent()){
            bookExistente.get().setTitle(book.getTitle());
            bookExistente.get().setAuthor(book.getAuthor());
            bookExistente.get().setPages(book.getPages());
            bookExistente.get().setPrice(book.getPrice());
            bookExistente.get().setReleaseDate(book.getReleaseDate());
            bookExistente.get().setOnline(book.getOnline());

            bookRepository.save(bookExistente.get());
            return ResponseEntity.ok(bookExistente.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(
            summary = "Eliminar un libro",
            description = "Eliminar un libro por clave primaria id Long"

    )
    //eliminar un book según el id existente en bases de datos
    @DeleteMapping("/books/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Book> deleteBook(@Parameter(description = "Eliminar un libro por clave primaria id Long") @PathVariable Long id){
        if(!bookRepository.existsById(id)){
            log.warn("Trying to delete a non existent book");
            return ResponseEntity.notFound().build();
        }else {
            bookRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }
    @Operation(
            summary = "Eliminar",
            description = "Elimina todos los libros"

    )
    //eliminar todos los books
    @DeleteMapping("/books")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Book> deleteAll(){
        bookRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
