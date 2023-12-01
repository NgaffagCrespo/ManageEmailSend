package booksmanagement.nextGenTest.services;

import booksmanagement.nextGenTest.entities.Books;
import booksmanagement.nextGenTest.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BooksServiceImpl implements BooksService {


    private BooksRepository booksRepository;

    public BooksServiceImpl(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Override
    public Books addBook(Books book) {

        book.setPublication_date(new Date());
        return booksRepository.save(book);
    }

    @Override
    public Books showBookbyAuthor(String book_author) {

        return booksRepository.findByBook_author(book_author);
    }

    @Override
    public Books showBookbyGender(String book_gender) {
        return booksRepository.findByGender(book_gender);
    }

    @Override
    public Books showBookbyTitle(String book_title) {
        return booksRepository.findByTitle(book_title);
    }

    @Override
    public List<Books> AllBooks() {

        List<Books> booksList = booksRepository.findAll();

        return booksList;
    }
}
