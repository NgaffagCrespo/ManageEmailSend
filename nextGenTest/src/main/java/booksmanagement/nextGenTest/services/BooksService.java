package booksmanagement.nextGenTest.services;

import booksmanagement.nextGenTest.entities.Books;

import java.util.List;

public interface BooksService {

    Books addBook(Books book);
    Books showBookbyAuthor(String book_author);
    Books showBookbyGender(String book_gender);
    Books showBookbyTitle(String book_title);
    List<Books> AllBooks();

}
