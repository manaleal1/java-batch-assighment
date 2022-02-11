package homework.week4.day17.repository;

import homework.week4.day17.domain.entity.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository {
    Person findPersonById(String id);
    void setPerson(String personId, String name);
    List<Person> findAllPeople();
}
