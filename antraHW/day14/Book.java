import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    private int id;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy="book", fetch=FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Books_Likes> books_likes = new ArrayList<>();

    public Book(int id, String title){
        this.id = id;
        this.title = title;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }
    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

}
