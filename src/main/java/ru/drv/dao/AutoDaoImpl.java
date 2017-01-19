package ru.drv.dao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.drv.interfaces.AutoDao;
import ru.drv.models.Auto;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class AutoDaoImpl implements AutoDao {

    //language=HQL
    private final String HQL_SELECT_ALL_AUTO = "from Auto a";

    JdbcTemplate template;

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    public AutoDaoImpl(DataSource dataSource){
        template = new JdbcTemplate(dataSource);
    }

    @Override
    public Auto find(int id) {
        return null;
    }

    @Override
    public List<Auto> findAll() {
        return null;
    }

    @Override
    public Auto save(Auto item) {
        return null;
    }

    @Override
    public boolean update(int id, Auto item) {
        return false;
    }

    @Override
    public boolean remove(int id) {
        return false;
    }

    @Override
    public List<Auto> findByUser(int userId) {
        Session session = getSession();
        session.beginTransaction();
        Query query = session.createQuery(HQL_SELECT_ALL_AUTO);
        List<Auto> auto = query.list();
        session.getTransaction().commit();
        return auto;
    }

    @Override
    public boolean removeByUser(int id) {
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
