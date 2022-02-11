package homework.week4.day17.service;

import homework.week4.day17.domain.dto.BookResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    BookResponseDTO findBookById(String id);
    List<BookResponseDTO> findAllBooks();
}
