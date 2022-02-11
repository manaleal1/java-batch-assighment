package homework.week4.day17.domain.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {
    private int id;
    private String name;

    private List<BooksLikes> books_likes = new ArrayList<>();

    public Person(){ }

    public Person(int id, String name){
        this.id = id;
        this.name = name;
    }

    public Person(int id, String name, List<BooksLikes> books_likes){
        this.id = id;
        this.name = name;
        this.books_likes = books_likes;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBooks_likes(List<BooksLikes> books_likes) {
        this.books_likes = books_likes;
    }

    public List<BooksLikes> getBooks_likes() {
        return books_likes;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", books_likes=" + books_likes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id,person.id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

}
