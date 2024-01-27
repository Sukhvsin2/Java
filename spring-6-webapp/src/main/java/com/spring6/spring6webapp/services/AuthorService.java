package com.spring6.spring6webapp.services;

import com.spring6.spring6webapp.domain.Author;

public interface AuthorService {
    Iterable<Author> findAll();
}
