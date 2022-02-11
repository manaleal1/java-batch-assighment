package homework.week4.day17.service.impl;

import homework.week4.day17.domain.dto.PersonResponseDTO;
import homework.week4.day17.domain.entity.Person;
import homework.week4.day17.repository.PersonRepository;
import homework.week4.day17.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository repository;

    @Autowired
    public PersonServiceImpl(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public void setPerson(String personId, String name){
        repository.setPerson(personId, name);
    }

    @Override
    public PersonResponseDTO findPersonById(String id) {
        Person p = repository.findPersonById(id);
        return new PersonResponseDTO(p.getName());
    }

    @Override
    public List<PersonResponseDTO> findAllPeople() {
        return repository.findAllPeople()
                .stream()
                .map(p -> new PersonResponseDTO(p.getName()))
                .collect(Collectors.toList());
    }
}
