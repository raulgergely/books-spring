package raul.springframework.soring_6_webapp.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import raul.springframework.soring_6_webapp.domain.Book;

import java.util.List;

public interface BookRepository extends CrudRepository<Book,Long> {
    Page<Book> findByTitleContaining(String title, Pageable pageable);
}
