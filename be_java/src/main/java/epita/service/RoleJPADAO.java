package epita.service;

import epita.datamodel.MovieUser;
import epita.datamodel.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class RoleJPADAO extends JPADAO<Role>{
    public RoleJPADAO(SessionFactory sf) {

        super(sf);
    }

    @Override
    public Query<Role> getAll(Session session) {
        Query<Role> query = session.createQuery(
                "from Role r "
        );

        query.getQueryString();
        return query;
    }

    @Override
    public Query<Role> get(Long id, Session session) {

        Query<Role> query = session.createQuery(
                "from Role r " +
                        "where (:id is null or :id = r.id) "
        );

        query.setParameter("id", id);
        query.getQueryString();
        return query;

    }

    @Override
    public Query<Role> getbyExternalID(String externalid, Session session) {
        return null;

    }


}
