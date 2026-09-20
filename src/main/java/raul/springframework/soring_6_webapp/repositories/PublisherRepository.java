package raul.springframework.soring_6_webapp.repositories;

import org.springframework.data.repository.CrudRepository;
import raul.springframework.soring_6_webapp.domain.Publisher;

public interface PublisherRepository  extends CrudRepository<Publisher,Long> {
}
