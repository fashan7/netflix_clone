package tests;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BaseTestConfig.class)
public class TestUser {
    @Autowired
    SessionFactory sf;

    @Test
    public void testSessionFactory(){
        //given
      /*  Users user = new Users();
        user.setUserName("user1");

       Role role1 = new Role();
        role1.setName("admin");


        Role role2 = new Role();
        role2.setName("manager");

        List<Role> roles = new ArrayList<>();
        roles.add(role1);
        roles.add(role2);
        user.addRole(roles.get(0));


        Movie movie1 = new Movie();
        movie1.setTitle("Movie1");

        Movie movie2 = new Movie();
        movie2.setTitle("movie2");

        List<Movie> movies = new ArrayList<>();
        movies.add(movie1);
        movies.add(movie2);
        user.addMovie(movies.get(0));



        Contact contact = new Contact();
        contact.setName("contact1");
       user.setContact(contact);

        //when
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();



        session.createQuery("from Users", Users.class);*/

    }

}
