package raul.springframework.soring_6_webapp.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import raul.springframework.soring_6_webapp.domain.Book;

import java.util.List;

public interface BookService {
    Iterable<Book> findAll();
    void saveBook(Book book);
    Page<Book> searchBook(String title, Pageable pageable);
    void deleteBookById(Long id);
}
