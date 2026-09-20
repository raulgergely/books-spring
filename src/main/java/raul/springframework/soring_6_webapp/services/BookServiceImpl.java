package raul.springframework.soring_6_webapp.services;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import raul.springframework.soring_6_webapp.domain.Book;
import raul.springframework.soring_6_webapp.repositories.BookRepository;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Iterable<Book> findAll(){
        return bookRepository.findAll();
    }

    //Această anotație garantează că totul se salvează cu succes sau se dă rollback în caz de eroare
    @Transactional
    public void saveBook(Book book){
        bookRepository.save(book);
    }

    @Override
    public Page<Book> searchBook(String title, Pageable pageable) {
        return bookRepository.findByTitleContaining(title,pageable);
    }
    @Override
    public void deleteBookById(Long id){
        bookRepository.deleteById(id);
    }
}
