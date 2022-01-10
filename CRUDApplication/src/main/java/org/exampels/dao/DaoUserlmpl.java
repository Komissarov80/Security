package org.exampels.dao;

import org.exampels.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DaoUserlmpl implements DaoUser {

    @PersistenceContext
    private EntityManager sessionFactory;

    @Override
    public List<User> getAllUsers() {
        return sessionFactory.createQuery
                ("SELECT u FROM User u").getResultList();
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
