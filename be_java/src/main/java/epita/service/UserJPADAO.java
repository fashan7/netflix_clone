package epita.service;

import epita.datamodel.Role;
import epita.datamodel.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class UserJPADAO extends JPADAO<User>{
    public UserJPADAO(SessionFactory sf) {

        super(sf);
    }

    @Override
    public Query<User> getAll(Session session) {
        Query<User> query = session.createQuery(
                "from User u "
        );

        query.getQueryString();
        return query;
    }

    @Override
    public Query<User> get(Long id, Session session) {
        return null;
    }

    @Override
    public Query<User> getbyExternalID(String externalid, Session session) {
        return null;
    }

}
