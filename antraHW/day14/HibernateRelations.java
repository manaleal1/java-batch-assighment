import org.hibernate.jpa.HibernatePersistenceProvider;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.*;
import javax.sql.DataSource;
import java.util.*;

public class HibernateRelations {

    private DataSource getDataSource() {
        final PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUser("username");
        dataSource.setPassword("password");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/database_name");
        return dataSource;
    }

    private Properties getProperties() {
        final Properties properties = new Properties();
        properties.put( "hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect" );
        properties.put( "hibernate.connection.driver_class", "org.postgresql.Driver" );
        return properties;
    }

    private EntityManagerFactory entityManagerFactory(DataSource dataSource, Properties hibernateProperties ){
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("path/to/package");
        em.setJpaVendorAdapter( new HibernateJpaVendorAdapter() );
        em.setJpaProperties( hibernateProperties );
        em.setPersistenceUnitName( "demo-unit" );
        em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        em.afterPropertiesSet();
        return em.getObject();
    }

    public static void populateTables(EntityManager em){
        List<String> names = Arrays.asList("Fred", "Barney", "Wilma", "Betty","Bugs","Daffy","Porky");
        List<String> books = Arrays.asList("Harry Potter", "Grapes of Wrath", "Othelo", "The Hunger Games","Twilight");
        LinkedList<Person> personList = new LinkedList<>();
        LinkedList<Book> bookList = new LinkedList<>();
        for (int i = 1; i <= names.size(); i++) {
            personList.add( new Person(i, names.get(i-1)) );
            em.persist( personList.get(i-1) );

        }
        for (int i = 1; i <= books.size(); i++) {
            bookList.add( new Book(i, books.get(i-1)) );
            em.persist( bookList.get(i-1) );
        }


        for (int i = 0, id = 1; i < names.size(); i++, id++) {
            Books_Likes bl = new Books_Likes(
                    id,
                    personList.get(i),
                    bookList.get( i % (books.size()-1) )
            );
            personList.get(i).getBooks_likes().add(bl);
            em.persist( bl );
            if(i % 3 == 1){
                Books_Likes bl2 = new Books_Likes(
                        ++id,
                        personList.get(i),
                        bookList.get( id % (books.size()-1) )
                );
                personList.get(i).getBooks_likes().add(bl2);
                em.persist( bl2 );
            }
        }


    }
    public static void selectData(EntityManager em){
        List<Person> persons = (List<Person>)em.createQuery("SELECT p FROM Person p").getResultList();
        List<Book> books = (List<Book>)em.createQuery("SELECT b FROM Book b").getResultList();
        List<Books_Likes> books_likes = (List<Books_Likes>)em.createQuery("SELECT bl FROM Books_Likes bl").getResultList();

        System.out.println("Select Data: ");
        System.out.println( persons );
        System.out.println( books );
        System.out.println( books_likes );
        System.out.println();
    }
    public static void selectDataById(EntityManager em){
        List<Person> personIds= (List<Person>) em.createQuery("SELECT p.id FROM Person p join p.books_likes").getResultList();
        System.out.println("Select person Data By Id: \n" + personIds);

        List<Book> bookIds= (List<Book>) em.createQuery("SELECT b.id FROM Book b join b.books_likes").getResultList();
        System.out.println("Select book Data By Id: \n" + bookIds);
    }
    public static void joinData(EntityManager em){
        List<Person> per= (List<Person>) em.createQuery("SELECT p FROM Person p join p.books_likes").getResultList();
        System.out.println("Join Data: \n" + per);
    }
    public static void removeData(EntityManager em){
        Query query = em.createQuery("select p from Person p join fetch p.books_likes bl where p.id = 2");
        Person p = (Person) query.getSingleResult();
        Book b = em.find(Book.class, 4);
        List<Books_Likes> books_likes = new ArrayList<>();
        for(Books_Likes bl: p.getBooks_likes()) {
            if(bl.getBook().getId() == b.getId()) {
                books_likes.add(bl);
                em.remove(bl);
            }
        }
        p.getBooks_likes().removeAll(books_likes);
        b.getBooks_likes().removeAll(books_likes);
    }


    /**
     *  1. build relation between hibernate (entitymanager) and database table
     *  2. create many to many relation in database
     *  3. use 1 - m + m - 1 in hibernate
     *  4. write jpql to select data / select data by id / join data
     *  5. write jpql to remove data
     *
     *  don't use : spring data jpa
     *              many to many annotation
     *              hibernate auto creation
     */

	//Example of Many to Many relation:
	//A person can likes many books and A book can be liked by many people
    public static void main(String[] args) {
        HibernateRelations hr = new HibernateRelations();
        DataSource dataSource = hr.getDataSource();
        Properties properties = hr.getProperties();
        EntityManagerFactory entityManagerFactory = hr.entityManagerFactory(dataSource, properties);
        EntityManager em = entityManagerFactory.createEntityManager();
        PersistenceUnitUtil unitUtil = entityManagerFactory.getPersistenceUnitUtil();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        populateTables( em );
        selectData( em );
        selectDataById( em );
        joinData( em );
        removeData( em );

        tx.commit();

    }

}
