package com.jacr.api_rest.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//RestController para  trabajar con JSON
@RestController
public class HelloController {
    @Operation(hidden = true)
    @GetMapping("/hello")
    public String helloWorld(){
        return "Hola mundo con Juan";
    }
    /**
     * El siguiente metodo devuelve html, trabajar html dentro de java no es recomendable
     * Es solo un ejemplo que si se puede, otra forma de trabajar con html es con mvc
     * lo cual se trabaja con gestor de plantillas
     **/
    @Operation(hidden = true)
    @GetMapping("/bootstrap")
    public String bootstrap(){
        return """
                <!doctype html>
                <html lang="en">
                  <head>
                    <meta charset="utf-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1">
                    <title>Bootstrap demo</title>
                    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
                  </head>
                  <body>
                    <h1>Hello, world con boostrap!</h1>
                    <a class="btn btn-danger" href="https://www.google.com">Hola</a>
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
                  </body>
                </html>
                """;

    }
}
