package epita.service;

import epita.datamodel.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class MovieJPADAO extends JPADAO<Movie> {

    public MovieJPADAO(SessionFactory sf) {

        super(sf);
    }

    @Override
    public Query<Movie> getAll(Session session) {
        Query<Movie> query = session.createQuery(
                "from Movie m "
        );

        query.getQueryString();
        return query;
    }

    @Override
    public Query<Movie> getbyExternalID(String externalid, Session session) {

        Query<Movie> query = session.createQuery(
                "from Movie m " +
                        "where (:external_id is null or :external_id = m.externalId) "
        );

        query.setParameter("external_id", externalid);
        query.getQueryString();
        return query;

    }

    @Override
    public Query<Movie> get(Long id, Session session) {

        Query<Movie> query = session.createQuery(
                "from Movie m " +
                        "where (:id is null or :id = m.id) "
        );

        query.setParameter("id", id);
        query.getQueryString();
        return query;

    }

}
