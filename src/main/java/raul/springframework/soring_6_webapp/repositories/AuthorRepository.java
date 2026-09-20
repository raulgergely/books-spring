package raul.springframework.soring_6_webapp.repositories;

import org.springframework.data.repository.CrudRepository;
import raul.springframework.soring_6_webapp.domain.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
