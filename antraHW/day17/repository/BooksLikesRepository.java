package homework.week4.day17.repository;

import homework.week4.day17.domain.entity.BooksLikes;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksLikesRepository {
    BooksLikes findBooksLikesById(String id);
    List<BooksLikes> findAllBooksLikes();
}
