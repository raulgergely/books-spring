package raul.springframework.soring_6_webapp.services;

import raul.springframework.soring_6_webapp.domain.Book;

public interface BookService {
    Iterable<Book> findAll();
}
