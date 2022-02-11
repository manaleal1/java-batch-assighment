package homework.week4.day17.service.impl;

import homework.week4.day17.domain.entity.Book;
import homework.week4.day17.domain.dto.BookResponseDTO;
import homework.week4.day17.repository.BookRepository;
import homework.week4.day17.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository repository;

    @Autowired
    public BookServiceImpl(BookRepository repository){ this.repository = repository; }

    @Override
    public BookResponseDTO findBookById(String id) {
        Book b = repository.findBookById(id);
        return new BookResponseDTO(b.getTitle());
    }

    @Override
    public List<BookResponseDTO> findAllBooks() {
        return repository.findAllBooks()
                .stream()
                .map(b -> new BookResponseDTO(b.getTitle()))
                .collect(Collectors.toList());
    }
}
