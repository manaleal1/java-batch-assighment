package homework.week4.day17.service;

import homework.week4.day17.domain.dto.BooksLikesResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BooksLikesService {
    BooksLikesResponseDTO findBooksLikesById(String id);
    List<BooksLikesResponseDTO> findAllBooksLikes();
}
