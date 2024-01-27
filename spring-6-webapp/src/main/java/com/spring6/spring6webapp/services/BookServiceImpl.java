package com.spring6.spring6webapp.services;

import com.spring6.spring6webapp.domain.Book;
import com.spring6.spring6webapp.repositories.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }
    @Override
    public Iterable<Book> findAll(){
        return this.bookRepository.findAll();
    }
}
