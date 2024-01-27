package com.spring6.spring6webapp.bootstrap;

import com.spring6.spring6webapp.domain.Author;
import com.spring6.spring6webapp.domain.Book;
import com.spring6.spring6webapp.domain.Publisher;
import com.spring6.spring6webapp.repositories.AuthorRepository;
import com.spring6.spring6webapp.repositories.BookRepository;
import com.spring6.spring6webapp.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository){
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception{
        Author eric = new Author("Eric", "Smith");
        Book ddd = new Book("Domain Driven Designs", "ISBN1234");

        Author tom = new Author("Tom", "Cat");
        Book ejb = new Book("J2EE Development without EJB", "ISBN5678");


        Author ericSaved = authorRepository.save(eric);
        Author tomSaved = authorRepository.save(tom);

        Book dddSaved = bookRepository.save(ddd);
        Book ejbSaved = bookRepository.save(ejb);


        eric.getBooks().add(dddSaved);
        tom.getBooks().add(ejbSaved);
        dddSaved.getAuthors().add(ericSaved);
        ejbSaved.getAuthors().add(tomSaved);

        // saving book associations
        authorRepository.saveAll(List.of(ericSaved, tomSaved));

        System.out.println("In the Bootstrap\n");
        System.out.println("Author count: " + authorRepository.count());
        System.out.println("Book count: " + bookRepository.count());


        Publisher pub1 = new Publisher("Publisher1", "3211 cypress ct",
                "new york", "new york", 1L);
        publisherRepository.save(pub1);

        dddSaved.setPublisher(pub1);
        ejbSaved.setPublisher(pub1);
        bookRepository.saveAll(List.of(dddSaved, ejbSaved));


        System.out.println("Publisher count: " + publisherRepository.count());
    }
}
