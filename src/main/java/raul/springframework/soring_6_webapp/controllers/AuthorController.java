package raul.springframework.soring_6_webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import raul.springframework.soring_6_webapp.domain.Author;
import raul.springframework.soring_6_webapp.domain.Book;
import raul.springframework.soring_6_webapp.repositories.AuthorRepository;
import raul.springframework.soring_6_webapp.services.AuthorService;
@Controller
public class AuthorController {
    private final AuthorService authorService;
    private final AuthorRepository authorRepository;

    public AuthorController(AuthorService authorService, AuthorRepository authorRepository) {
        this.authorService = authorService;
        this.authorRepository = authorRepository;
    }

    @RequestMapping("/authors")
    public String getAuthors(Model model){
        model.addAttribute("authors", authorService.findAll());
        return "authors";
    }

    @GetMapping("/authors/add")
    public String showAddAuthorForm(Model model){
        model.addAttribute("author", new Author());
        return "add-author"; // Numele fișierului HTML
    }

    @PostMapping("/authors")
    public String addAuthor(@ModelAttribute Author author, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-author";
        }
        authorRepository.save(author);
        return "redirect:/authors";
    }
}
