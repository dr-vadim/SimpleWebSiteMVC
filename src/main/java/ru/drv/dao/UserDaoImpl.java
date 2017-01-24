package ru.drv.dao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.drv.interfaces.UserDao;
import ru.drv.models.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {

    //language=HQL
    private final String HQL_SELECT_ALL_USERS = "from User u";
    //language=HQL
    private final String HQL_SELECT_USER_BY_ID = "from User u where u.id=:id";
    //language=SQL
    private final String SQL_INSERT_USER = "INSERT INTO group_users(name,age) VALUES (:name,:age)";
    //language=SQL
    private final String SQL_DELETE_USER = "DELETE FROM group_users WHERE id=:id";
    //language=SQL
    private final String SQL_UPDATE_USER = "UPDATE group_users SET name=:name, age=:age Where id=:id";


    NamedParameterJdbcTemplate template;

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(DataSource dataSource){
        template = new NamedParameterJdbcTemplate(dataSource);
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
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("name",item.getName());
        map.addValue("age", item.getAge());
        int result = template.update(SQL_INSERT_USER, map,keyHolder, new String[]{"id"});
        int id = keyHolder.getKey().intValue();
        return new User.Builder().setId(id).setName(item.getName()).setAge(item.getAge()).build();
    }

    @Override
    public boolean update(int id, User item) {
        Map<String,Object> map = new HashMap<>();
        map.put("name", item.getName());
        map.put("age", item.getAge());
        map.put("id", id);
        int res = template.update(SQL_UPDATE_USER, map);
        return res > 0;
    }

    @Override
    public boolean remove(int id) {
        Map<String,Object> map = new HashMap<>();
        map.put("id", id);
        int res = template.update(SQL_DELETE_USER,map);
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
