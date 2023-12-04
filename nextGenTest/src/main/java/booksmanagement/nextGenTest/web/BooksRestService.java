package booksmanagement.nextGenTest.web;


import booksmanagement.nextGenTest.entities.Writings;
import booksmanagement.nextGenTest.services.BooksService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/booksManagement")
public class BooksRestService {

    private BooksService booksService;


    //Ici c'est le service API qui permet de sauvegarder un livre
    @PostMapping(path = "/books")
    @PostAuthorize("hasAuthority('ajout')")
    public Writings addBook(@RequestBody Writings writings){
        return booksService.addBook(writings);
    }

    //Ici c'est le service API qui permet de lister tous les livres
    @GetMapping(path = "/books")
    @PostAuthorize("hasAuthority('recuperation')")
    public List<Writings> AllBooks(){
        return booksService.AllBooks();
    }

    /*Ici c'est le service API qui permet de rechercher un livre avec
    pour critere de recherche le nom de l'auteur du livre*/
    @GetMapping(path = "/books/{author}")
    @PostAuthorize("hasAuthority('test')")
    public Writings getBookByAuthor(@PathVariable String author){
        return booksService.showBookbyAuthor(author);
    }

    /*Ici c'est le service API qui permet de rechercher un livre avec
   pour critere de recherche le genre du livre*/
    @GetMapping(path = "/books/{gender}")
    @PostAuthorize("hasAuthority('test')")
    public Writings getBookByGender(@PathVariable String gender){
        return booksService.showBookbyGender(gender);
    }

    /*Ici c'est le service API qui permet de rechercher un livre avec
   pour critere de recherche le titre du livre*/
    @GetMapping(path = "/books/{title}")
    @PostAuthorize("hasAuthority('test')")
    public Writings getBookByTitle(@PathVariable String title){
        return booksService.showBookbyTitle(title);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
