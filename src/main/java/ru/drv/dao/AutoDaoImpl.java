package ru.drv.dao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.drv.interfaces.AutoDao;
import ru.drv.models.Auto;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AutoDaoImpl implements AutoDao {

    //language=HQL
    private final String HQL_SELECT_ALL_AUTO = "from Auto a";
    //language=HQL
    private final String HQL_SELECT_AUTO_BY_USER = "from Auto a where a.user.id=:userId";
    //language=HQL
    private final String HQL_SELECT_AUTO_BY_ID = "from Auto a where a.id=:id";
    //language=SQL
    private final String SQL_INSER_AUTO = "INSERT INTO auto(model,color) VALUES (:model,:color)";
    //language=SQL
    private final String SQL_DELETE_AUTO = "DELETE FROM auto WHERE id=:id";
    //language=SQL
    private final String SQL_DELETE_AUTO_BY_USER = "DELETE FROM auto WHERE user_id=:userId";
    //language=SQL
    private final String SQL_UPDATE_AUTO = "UPDATE auto SET model=:model,color=:color WHERE id=:id";

    NamedParameterJdbcTemplate template;

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    public AutoDaoImpl(DataSource dataSource){
        template = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Auto find(int id) {
        Session session = getSession();
        session.beginTransaction();
        Auto auto = session.get(Auto.class,id);
        session.getTransaction().commit();
        return auto;
    }

    @Override
    public List<Auto> findAll() {
        Session session = getSession();
        session.beginTransaction();
        Query query = session.createQuery(HQL_SELECT_ALL_AUTO);
        List<Auto> auto = query.list();
        session.getTransaction().commit();
        return auto;
    }

    @Override
    public Auto save(Auto item) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("model", item.getModel());
        map.addValue("color", item.getColor());
        template.update(SQL_INSER_AUTO,map,keyHolder, new String[] {"id"});
        int id = keyHolder.getKey().intValue();
        return new Auto.Builder().setId(id).setModel(item.getModel()).setColor(item.getColor()).build();
    }

    @Override
    public boolean update(int id, Auto item) {
        Map<String,Object> map = new HashMap<>();
        map.put("model", item.getModel());
        map.put("color", item.getColor());
        map.put("id", id);
        int res = template.update(SQL_UPDATE_AUTO, map);
        return res > 0;
    }

    @Override
    public boolean remove(int id) {
        Map<String,Object> map = new HashMap<>();
        map.put("id", id);
        int res = template.update(SQL_DELETE_AUTO,map);
        return res > 0;
    }

    @Override
    public List<Auto> findByUser(int userId) {
        Session session = getSession();
        session.beginTransaction();
        Query query = session.createQuery(HQL_SELECT_AUTO_BY_USER);
        query.setInteger("userId",userId);
        List<Auto> auto = query.list();
        session.getTransaction().commit();
        return auto;
    }

    @Override
    public boolean removeByUser(int id) {
        Map<String,Object> map = new HashMap<>();
        map.put("userId", id);
        int res = template.update(SQL_DELETE_AUTO_BY_USER,map);
        return res > 0;
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
