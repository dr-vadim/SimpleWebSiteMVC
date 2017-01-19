package ru.drv.dao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.drv.interfaces.UserDao;
import ru.drv.models.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    //language=HQL
    private final String HQL_SELECT_ALL_USERS = "from User u";
    //language=HQL
    private final String HQL_SELECT_USER_BY_ID = "from User u where u.id=:id";


    JdbcTemplate template;

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(DataSource dataSource){
        template = new JdbcTemplate(dataSource);
    }

    @Override
    public User find(int id) {
        Session session = getSession();
        session.beginTransaction();
        User user = session.get(User.class,id);
        session.getTransaction().commit();
        return user;
    }

    @Override
    public List<User> findAll() {
        Session session = getSession();
        session.beginTransaction();
        Query query = session.createQuery(HQL_SELECT_ALL_USERS);
        List<User> users = query.list();
        session.getTransaction().commit();
        return users;
    }

    @Override
    public User save(User item) {
        return null;
    }

    @Override
    public boolean update(int id, User item) {
        return false;
    }

    @Override
    public boolean remove(int id) {
        return false;
    }

    private Session getSession() {
        Session session;

        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }

        return session;
    }
}
