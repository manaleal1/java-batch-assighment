package homework.week4.day17.service;

import homework.week4.day17.domain.dto.PersonResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonService {
    PersonResponseDTO findPersonById(String id);
    void setPerson(String personId, String name);
    List<PersonResponseDTO> findAllPeople();

}
