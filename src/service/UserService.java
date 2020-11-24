package service;

import bean.User;

/**
 *
 */
public interface UserService {

    /**
     * 登录
     * @param user
     * @return
     */
    User getUser(User user);

    /**
     * 检查用户名是否存在
     * 		true:用户名存在
     * 		false:用户名不存在
     */
    boolean checkUserName(String username);

    /**
     * 将user信息保存到数据库
     * @param user
     */
    boolean saveUser(User user);
}
