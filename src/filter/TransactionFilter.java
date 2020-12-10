package filter;

import until.JDBCUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Auther Ashen One
 * @Date 2020/12/10
 */
@WebFilter(filterName = "TransactionFilter", urlPatterns = {"/*"})
public class TransactionFilter extends HttpFilter {

    /**
     * 同一处理异常
     * 同一处理事务
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Connection connection = JDBCUtils.getConnection();
        try {
            //开启事务
            connection.setAutoCommit(false);
            //放行
            chain.doFilter(request, response);
            //无异常，提交事务
            connection.commit();
        } catch (Exception e) {
            //有异常，回滚事务
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/pages/error/transaction_error.jsp");
        } finally {
            //释放Connection
            JDBCUtils.releaseConnection();
        }


    }
}
