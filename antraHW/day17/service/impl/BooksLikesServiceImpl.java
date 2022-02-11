package homework.week4.day17.service.impl;

import homework.week4.day17.domain.dto.BooksLikesResponseDTO;
import homework.week4.day17.domain.entity.BooksLikes;
import homework.week4.day17.repository.BooksLikesRepository;
import homework.week4.day17.service.BooksLikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BooksLikesServiceImpl implements BooksLikesService {
    private final BooksLikesRepository repository;

    @Autowired
    public BooksLikesServiceImpl(BooksLikesRepository repository) {
        this.repository = repository;
    }


    @Override
    public BooksLikesResponseDTO findBooksLikesById(String id) {
        BooksLikes bl = repository.findBooksLikesById(id);
        return new BooksLikesResponseDTO(Integer.toString(bl.getId()));
    }

    @Override
    public List<BooksLikesResponseDTO> findAllBooksLikes() {
        return repository.findAllBooksLikes()
                .stream()
                .map(bl-> new BooksLikesResponseDTO(Integer.toString(bl.getId())))
                .collect(Collectors.toList());
    }
}
