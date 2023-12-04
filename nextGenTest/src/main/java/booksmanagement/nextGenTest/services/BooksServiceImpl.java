package booksmanagement.nextGenTest.services;

import booksmanagement.nextGenTest.entities.Writings;
import booksmanagement.nextGenTest.exceptions.WritingsCanNotSaveException;
import booksmanagement.nextGenTest.exceptions.WritingsNotFoundException;
import booksmanagement.nextGenTest.repositories.BooksRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public Writings addBook(Writings writings) {

        writings.setPublication_date(new Date());

        Writings writings1 = new Writings();
        try {
            writings1 = booksRepository.save(writings);


        } catch (Exception e) {

            throw new WritingsCanNotSaveException("Il y'a un probleme lors de l'enregistrement du livre ");

        }
        return writings1;

    }

    @Override
    public Writings showBookbyAuthor(String author) {

        Writings writings = new Writings();

        try{

            writings = booksRepository.findByAuthor(author);

        }catch (Exception e){

            throw new WritingsNotFoundException("Livre que vous recherchez est introuvable");

        }


        return writings;
    }

    @Override
    public Writings showBookbyGender(String gender) {

        Writings writings = new Writings();

        try{

            writings = booksRepository.findByGender(gender);

        }catch (Exception e){

            throw new WritingsNotFoundException("Livre que vous recherchez est introuvable");

        }


        return writings;

    }

    @Override
    public Writings showBookbyTitle(String title) {

        Writings writings = new Writings();

        try{

            writings =  booksRepository.findByTitle(title);

        }catch (Exception e){

            throw new WritingsNotFoundException("Livre que vous recherchez est introuvable");

        }

        return writings;
    }

    @Override
    public List<Writings> AllBooks() {

        List<Writings> writingsList = new ArrayList<>();

        Writings writings = new Writings();

        try{

            writingsList = booksRepository.findAll();

        }catch (Exception e){

            throw new WritingsNotFoundException("Il semblerait que la liste des livres soient introuvable");

        }

        return writingsList;
    }
}
