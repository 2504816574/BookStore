package service.impl;

import bean.User;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import service.UserService;

/**
 *
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public User getUser(User user) {
        return userDao.getUser(user);
    }

    @Override
    public boolean checkUserName(String username) {
        return userDao.checkUserName(username);
    }

    @Override
    public boolean saveUser(User user) {
        return userDao.saveUser(user);
    }
}
