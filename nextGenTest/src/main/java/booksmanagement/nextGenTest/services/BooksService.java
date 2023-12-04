package booksmanagement.nextGenTest.services;

import booksmanagement.nextGenTest.entities.Writings;

import java.util.List;

public interface BooksService {

    Writings addBook(Writings writings);
    Writings showBookbyAuthor(String author);
    Writings showBookbyGender(String gender);
    Writings showBookbyTitle(String title);
    List<Writings> AllBooks();

}
