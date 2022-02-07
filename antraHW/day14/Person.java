import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "people")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "person",cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    private List<Books_Likes> books_likes = new ArrayList<>();

    public Person(int id, String name){
        this.id = id;
        this.name = name;
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
