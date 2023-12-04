package booksmanagement.nextGenTest.services;

import booksmanagement.nextGenTest.entities.writings;

import java.util.List;

public interface BooksService {

    writings addBook(writings writings);
    writings showBookbyAuthor(String author);
    writings showBookbyGender(String gender);
    writings showBookbyTitle(String title);
    List<writings> AllBooks();

}
