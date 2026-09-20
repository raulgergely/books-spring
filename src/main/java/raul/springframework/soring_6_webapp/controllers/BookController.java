package raul.springframework.soring_6_webapp.controllers;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import raul.springframework.soring_6_webapp.domain.Book;
import raul.springframework.soring_6_webapp.repositories.AuthorRepository;
import raul.springframework.soring_6_webapp.repositories.BookRepository;
import raul.springframework.soring_6_webapp.repositories.PublisherRepository;
import raul.springframework.soring_6_webapp.services.BookService;

@Controller
public class BookController {
    private final BookService bookService;
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;


    public BookController(BookService bookService, PublisherRepository publisherRepository, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookService = bookService;
        this.publisherRepository = publisherRepository;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }
    @RequestMapping("/books")
    public String getBooks(Model model){
        model.addAttribute("books",bookService.findAll());
        return "books";
    }
    @GetMapping("/books/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("publishers", publisherRepository.findAll());
        model.addAttribute("allAuthors", authorRepository.findAll());
        return "add-book";
    }

    @PostMapping("/add-book")
    public String addBooks(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("publishers", publisherRepository.findAll());
            model.addAttribute("allAuthors", authorRepository.findAll());
            return "add-book";
        }

        bookService.saveBook(book);
        return "redirect:/books";
    }
    @GetMapping("/books/search")
    public String searchBooks(@RequestParam("keyword") String keyword, Pageable pageable, Model model){
        Page<Book> bookPage = bookService.searchBook(keyword,pageable);
        model.addAttribute("books", bookPage);
        return "books";
    }
    @GetMapping("/books/remove")
    public String removeBook(@RequestParam("id") String id){
        bookService.deleteBookById(Long.parseLong(id));
        return "redirect:/books";
    }

}
