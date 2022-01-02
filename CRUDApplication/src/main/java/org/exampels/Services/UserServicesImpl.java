package org.exampels.Services;

import org.exampels.dao.DaoUser;
import org.exampels.dao.DaoUserlmpl;
import org.exampels.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServicesImpl implements UserServices{

    private DaoUser daoUser;

    @Autowired
    public UserServicesImpl(DaoUser daoUser) {
       this.daoUser = daoUser;
    }
    @Override
    public List<User> getAllUsers() {
        return daoUser.getAllUsers();
    }

    @Override
    public void addUser(User user) {
        daoUser.addUser(user);
    }

    @Override
    public void editUser(User user) {
    daoUser.editUser(user);
    }

    @Override
    public void deletUser(Long id) {
    daoUser.deletUser(id);
    }

    @Override
    public User getUser(Long id) {
        return daoUser.getUser(id);
    }
}
