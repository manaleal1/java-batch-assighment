import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
/*
Books_likes = Many to Many relation because
A person can like many books and A book can be liked by many people
*/
@Entity
@Table(name = "books_likes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Books_Likes {
    @Id
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "p_id")
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "b_id")
    private Book book;

    @Override
    public String toString() {
        return "Books_Likes{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Books_Likes that = (Books_Likes) o;
        return Objects.equals(id, that.id) && Objects.equals(person, that.person) && Objects.equals(book, that.book);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id,person,book);
    }

}
