package associations.optional.service;

import associations.optional.model.Book;

import java.util.ArrayList;
import java.util.List;

public class MainService {
    private List<Book> books = new ArrayList<>();

    public MainService() {
        books.add(new Book("b1"));
        books.add(new Book("b2"));
        books.add(new Book("b3"));
    }

    public Book findBookByName(String name) {
        return books.stream()
                .filter(book -> book.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Carte doesn't exist"));
    }
}

