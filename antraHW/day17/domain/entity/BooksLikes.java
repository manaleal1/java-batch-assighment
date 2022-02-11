package homework.week4.day17.domain.entity;

import java.util.Objects;

public class BooksLikes {
    private int id;
    private Person person;
    private Book book;
    private int personId;
    private int bookId;

    public BooksLikes() { }

    public BooksLikes(int id, int personId, int bookId){
        this.id = id;
        this.personId = personId;
        this.bookId = bookId;
    }

    public BooksLikes(int id, Person person, Book book) {
        this.id = id;
        this.person = person;
        this.book = book;
    }

    public int getId() { return id; }

    public Book getBook() {
        return book;
    }

    public Person getPerson() { return person; }



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
        BooksLikes that = (BooksLikes) o;
        return Objects.equals(id, that.id) && Objects.equals(person, that.person) && Objects.equals(book, that.book);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id,person,book);
    }

}
