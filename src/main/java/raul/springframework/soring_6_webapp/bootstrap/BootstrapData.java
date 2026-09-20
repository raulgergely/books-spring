package raul.springframework.soring_6_webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import raul.springframework.soring_6_webapp.domain.Author;
import raul.springframework.soring_6_webapp.domain.Book;
import raul.springframework.soring_6_webapp.domain.Publisher;
import raul.springframework.soring_6_webapp.repositories.AuthorRepository;
import raul.springframework.soring_6_webapp.repositories.BookRepository;
import raul.springframework.soring_6_webapp.repositories.PublisherRepository;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (bookRepository.count() == 0) {
            Author eric = new Author();
            eric.setFirstName("Eric");
            eric.setLastName("Evans");

            Book ddd = new Book();
            ddd.setTitle("DOmain Drived Design");
            ddd.setIsbn("123456");

            Author rod = new Author();
            rod.setFirstName("Rod");
            rod.setLastName("Johnson");

            Book noEJB = new Book();
            noEJB.setTitle("J2EE Development without EJB");
            noEJB.setIsbn("54757585");
            ;

            Publisher pub = new Publisher();
            pub.setPublisherName("Koreea");
            pub.setAddress("Str something nr 2");
            pub.setCity("Timisoara");
            pub.setState("Timis");
            pub.setZip("300771");

            Author ericSaved = authorRepository.save(eric);
            Book dddSaved = bookRepository.save(ddd);
            Author rodSaved = authorRepository.save(rod);
            Book noEJBSaved = bookRepository.save(noEJB);
            Publisher pubSaved = publisherRepository.save(pub);


            ericSaved.getBooks().add(dddSaved);
            rodSaved.getBooks().add(noEJBSaved);
            dddSaved.getAuthors().add(ericSaved);
            noEJBSaved.getAuthors().add(rodSaved);

            dddSaved.setPublisher(pubSaved);
            noEJBSaved.setPublisher(pubSaved);

            authorRepository.save(ericSaved);
            authorRepository.save(rodSaved);
            bookRepository.save(dddSaved);
            bookRepository.save(noEJBSaved);

            System.out.println("In bootstrap");
            System.out.println("Author count:" + authorRepository.count());
            System.out.println("Book count" + bookRepository.count());
            System.out.println("Publisher count" + publisherRepository.count());
        }
    }
}
