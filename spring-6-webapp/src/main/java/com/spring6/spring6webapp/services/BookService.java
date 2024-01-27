package com.spring6.spring6webapp.services;

import com.spring6.spring6webapp.domain.Book;


public interface BookService {
    public Iterable<Book> findAll();
}
