package booksmanagement.nextGenTest.services;

import booksmanagement.nextGenTest.entities.writings;
import booksmanagement.nextGenTest.repositories.BooksRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@NoArgsConstructor
public class BooksServiceImpl implements BooksService {


    private BooksRepository booksRepository;

   /* public BooksServiceImpl(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }*/

    @Override
    public writings addBook(writings writings) {

        writings.setPublication_date(new Date());
        return booksRepository.save(writings);
    }

    @Override
    public writings showBookbyAuthor(String author) {

        return booksRepository.findByBook_author(author);
    }

    @Override
    public writings showBookbyGender(String gender) {
        return booksRepository.findByGender(gender);
    }

    @Override
    public writings showBookbyTitle(String title) {
        return booksRepository.findByTitle(title);
    }

    @Override
    public List<writings> AllBooks() {

        List<writings> writingsList = booksRepository.findAll();

        return writingsList;
    }
}
