package booksmanagement.nextGenTest.web;


import booksmanagement.nextGenTest.entities.writings;
import booksmanagement.nextGenTest.services.BooksService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping(path = "/booksManagement")
public class BooksRestService {

    private BooksService booksService;


    //Ici c'est le service API qui permet de sauvegarder un livre
    @PostMapping(path = "/books")
    @PostAuthorize("hasAuthority('ajout')")
    public writings addBook(@RequestBody writings writings){
        return booksService.addBook(writings);
    }

    //Ici c'est le service API qui permet de lister tous les livres
    @GetMapping(path = "/books")
    @PostAuthorize("hasAuthority('recuperation')")
    public List<writings> AllBooks(){
        return booksService.AllBooks();
    }

    /*Ici c'est le service API qui permet de rechercher un livre avec
    pour critere de recherche le nom de l'auteur du livre*/
    @GetMapping(path = "/books/{author}")
    @PostAuthorize("hasAuthority('recherche')")
    public writings getBookByAuthor(String author){
        return booksService.showBookbyAuthor(author);
    }

    /*Ici c'est le service API qui permet de rechercher un livre avec
   pour critere de recherche le genre du livre*/
    @GetMapping(path = "/books/{gender}")
    @PostAuthorize("hasAuthority('recherche')")
    public writings getBookByGender(String gender){
        return booksService.showBookbyGender(gender);
    }

    /*Ici c'est le service API qui permet de rechercher un livre avec
   pour critere de recherche le titre du livre*/
    @GetMapping(path = "/books/{title}")
    @PostAuthorize("hasAuthority('recherche')")
    public writings getBookByTitle(String title){
        return booksService.showBookbyTitle(title);
    }

}
