package org.exampels.dao;

import org.exampels.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DaoUserlmpl implements DaoUser{

    @PersistenceContext
    private EntityManager sessionFactory;

    @Override
    public List<User> getAllUsers() {
        return sessionFactory.createQuery("SELECT u FROM User u").getResultList();
    }

    @Override
    public void addUser(User user) {
       sessionFactory.persist(user);
    }

    @Override
    public void editUser(User user) {
    sessionFactory.merge(user);
    }

    @Override
    public void deletUser(Long id) {
        User user = sessionFactory.find(User.class, id);
        sessionFactory.remove(user);
    }

    @Override
    public User getUser(Long id) {
        return sessionFactory.find(User.class, id);
    }
}
