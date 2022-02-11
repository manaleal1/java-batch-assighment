package homework.week4.day17.repository.impl;

import homework.week4.day17.domain.entity.Book;
import homework.week4.day17.repository.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private final Map<String, Book> bookMap;

    public BookRepositoryImpl() {
        this.bookMap = new ConcurrentHashMap<>();
        init();
    }

    private void init(){
        bookMap.put("1", new Book(1, "Book A"));
        bookMap.put("2", new Book(2, "Book B"));
        bookMap.put("3", new Book(3, "Book C"));
        bookMap.put("4", new Book(4, "Book D"));
        bookMap.put("5", new Book(5, "Book E"));
    }

    @Override
    public Book findBookById(String id) { return bookMap.get(id); }

    @Override
    public List<Book> findAllBooks() { return bookMap.values().stream().collect(Collectors.toList()); }
}
