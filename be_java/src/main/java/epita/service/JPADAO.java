package epita.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.data.util.ProxyUtils;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.List;

public abstract class JPADAO<T> {
    private final SessionFactory factory;


    public JPADAO(SessionFactory sf){

        this.factory =sf;
    }

    public abstract Query<T> getAll(Session session);
    public abstract Query<T> get(Long id, Session session);
    public abstract Query<T> getbyExternalID(String externalid, Session session);

    public List<T> search(){
        Session session = getSession();
        Query<T> query = getAll(session);
        return query.list();
    }

    public void create(T something){
        Session session = getSession();
        session.persist(something);
    }

    public void delete(String name){
        Session session = getSession();
        session.delete(name,session);
    }

    public List<T> search(Long id){
        Session session = getSession();
        Query<T> query = get(id,session);
        return query.list();
    }

    public List<T> searchExternalid(String externalid){
        Session session = getSession();
        Query<T> query = getbyExternalID(externalid,session);
        return query.list();
    }



    private Session getSession() {
        Session currentSession = null;
        try {
            currentSession = this.factory.getCurrentSession();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        if (currentSession != null && currentSession.isOpen()) {
            return currentSession;
        } else {
            return this.factory.openSession();
        }
    }
}
