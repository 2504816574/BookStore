import bean.User;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import org.junit.Test;

/**
 *
 */
public class UserDaoImplTest {
    private UserDao userDao = new UserDaoImpl();

    @Test
    public void getUser() {
        User user = userDao.getUser(new User(null, "zs", "123", null));
        System.out.println(user);
    }
    @Test
    public void checkUserName() {
        boolean b = userDao.checkUserName("zs12345");
        System.out.println(b);
    }
    @Test
    public void saveUser() {
        boolean b = userDao.saveUser(new User(null,"test02","12345","t2@qq.com"));
        System.out.println(b);
    }
}