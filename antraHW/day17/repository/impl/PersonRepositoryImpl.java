package homework.week4.day17.repository.impl;

import homework.week4.day17.domain.entity.Person;
import homework.week4.day17.repository.PersonRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class PersonRepositoryImpl implements PersonRepository {
    private final Map<String, Person> personMap;

    public PersonRepositoryImpl(){
        this.personMap = new ConcurrentHashMap<>();
        init();
    }
    private void init(){
        personMap.put("1", new Person(1, "Woody"));
        personMap.put("2", new Person(2, "Buzz"));
        personMap.put("3", new Person(3, "Elsa"));
        personMap.put("4", new Person(4, "Doug"));
    }

    @Override
    public void setPerson(String personId, String name){
        personMap.put(personId, new Person(Integer.parseInt(personId), name));
    }

    @Override
    public Person findPersonById(String id) {
        return personMap.get(id);
    }

    @Override
    public List<Person> findAllPeople() {
        return personMap.values().stream().collect(Collectors.toList());
    }
}
