package homework.week4.day17.repository.impl;

import homework.week4.day17.domain.entity.Book;
import homework.week4.day17.domain.entity.BooksLikes;
import homework.week4.day17.domain.entity.Person;
import homework.week4.day17.repository.BooksLikesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class BooksLikesRepositoryImpl implements BooksLikesRepository {
    private final Map<String, BooksLikes> bookslikesMap;

    @Autowired
    public BooksLikesRepositoryImpl(Map<String, BooksLikes> bookslikesMap) {
        this.bookslikesMap = new ConcurrentHashMap<>();
        init();
    }

    private void init(){
        bookslikesMap.put("1", new BooksLikes(1, 1, 1));
        bookslikesMap.put("2", new BooksLikes(2, 1, 1));
        bookslikesMap.put("3", new BooksLikes(3, 1, 1));
    }

    public void setBookslikes(String personId, String bookId){
        //bookslikesMap.put(bookslikesMap.size()+1, new BooksLikes(bookslikesMap.size(), n))
    }

    @Override
    public BooksLikes findBooksLikesById(String id) { return bookslikesMap.get(id); }

    @Override
    public List<BooksLikes> findAllBooksLikes() { return bookslikesMap.values().stream().collect(Collectors.toList()); }
}
