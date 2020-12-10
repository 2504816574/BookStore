package dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import until.JDBCUtils;


/**
 * 定义一个用来被继承的对数据库进行基本操作的Dao
 *
 * @param <T>
 * @author Ashen One
 */
public class BaseDao<T> {
    private QueryRunner queryRunner = new QueryRunner();
    //定义一个变量来接收泛型的类型
    private Class<T> type;

    // 获取T的Class对象，获取泛型的类型，泛型是在被子类继承时才确定
    public BaseDao() {
        //获取子类的类型
        Class clazz = this.getClass();
        //获取父类的类型
        //getGenericSuperclass()用来获取当前类的父类的类型
        //ParameterizedType表示的是带泛型的类型
        ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass();
        //获取具体的泛型类型 getActualTypeArguments获取具体的泛型的类型
        //这个方法会返回一个Type的数组
        Type[] types = parameterizedType.getActualTypeArguments();
        //获取具体的泛型的类型·
        this.type = (Class<T>) types[0];
    }

    /**
     * 通用的增删改操作
     *
     * @param sql
     * @param params
     * @return
     */
    public int update(String sql, Object... params) {
        // 获取连接
        Connection connection = JDBCUtils.getConnection();
        int count = 0;
        try {
            count = queryRunner.update(connection, sql, params);
        } catch (SQLException e) {
              // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            // JDBCUtils.releaseConnection(connection);
        }
        return count;
    }

    /**
     * 批处理通用的增删改操作
     *
     * @param sql
     * @param params 二维数组（一维：次数 ；二维：参数）
     * @return
     */
    public void batchUpdate(String sql, Object[][] params) {
        // 获取连接
        Connection connection = JDBCUtils.getConnection();
        try {
            queryRunner.batch(connection, sql, params);
        } catch (SQLException e) {
              // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            // JDBCUtils.releaseConnection(connection);
        }

    }

    /**
     * 获取一个对象
     *
     * @param sql
     * @param params
     * @return
     */
    public T getBean(String sql, Object... params) {
        // 获取连接
        Connection connection = JDBCUtils.getConnection();
        T t = null;
        try {
            t = queryRunner.query(connection, sql, new BeanHandler<T>(type),
                    params);
        } catch (SQLException e) {
              // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            // JDBCUtils.releaseConnection(connection);
        }
        return t;
    }

    /**
     * 获取所有对象
     *
     * @param sql
     * @param params
     * @return
     */
    public List<T> getBeanList(String sql, Object... params) {
        // 获取连接
        Connection connection = JDBCUtils.getConnection();
        List<T> list = null;
        try {
            list = queryRunner.query(connection, sql, new BeanListHandler<T>(
                    type), params);
        } catch (SQLException e) {
              // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            // JDBCUtils.releaseConnection(connection);
        }
        return list;
    }

    /**
     * @param sql
     * @param params
     * @return
     */
    public Object getSingleValue(String sql, Object... params) {
        // 获取连接
        Connection connection = JDBCUtils.getConnection();
        Object o = null;
        try {
            o = queryRunner.query(connection, sql, new ScalarHandler<>(),
                    params);
        } catch (SQLException e) {
              // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            // JDBCUtils.releaseConnection(connection);
        }
        return o;
    }
}
