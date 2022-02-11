package homework.week4.day17.domain.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Book {
    private int id;
    private String title;

    private List<BooksLikes> books_likes = new ArrayList<>();

    public Book() {}

    public Book(int id, String title){
        this.id = id;
        this.title = title;
    }

    public Book(int id, String title, List<BooksLikes> books_likes){
        this.id = id;
        this.title = title;
        this.books_likes = books_likes;
    }

    public int getId() {
        return id;
    }
    public String getTitle(){
        return title;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public List<BooksLikes> getBooks_likes() {
        return books_likes;
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
