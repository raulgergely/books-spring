package raul.springframework.soring_6_webapp.services;

import raul.springframework.soring_6_webapp.domain.Author;

public interface AuthorService {
    Iterable<Author> findAll();
}
