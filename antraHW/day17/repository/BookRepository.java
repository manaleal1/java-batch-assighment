package homework.week4.day17.repository;

import homework.week4.day17.domain.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository {
    Book findBookById(String id);
    List<Book> findAllBooks();
}
