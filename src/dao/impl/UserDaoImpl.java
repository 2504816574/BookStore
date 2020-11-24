package dao.impl;

import bean.User;
import dao.BaseDao;
import dao.UserDao;


/**
 *
 */
public class UserDaoImpl extends BaseDao<User> implements UserDao {
    @Override
    public User getUser(User user) {
        String sql = "SELECT id,username,`password`,email FROM users WHERE username = ? AND `password` = ?";
        return this.getBean(sql, user.getUsername(), user.getPassword());
    }

    @Override
    public boolean checkUserName(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        User bean = this.getBean(sql, username);
        return bean != null;
    }

    @Override
    public boolean saveUser(User user) {
        String sql = "insert into users (username,password,email) values(?,?,?)";
        int update = this.update(sql, user.getUsername(), user.getPassword(), user.getEmail());
        return update > 0;
    }


}
