package filter;

import bean.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Auther Ashen One
 * @Date 2020/12/9
 */
@WebFilter(filterName = "FilterCheckLoginFilter", urlPatterns = {"/OrderServlet"})
public class FilterCheckLoginFilter extends HttpFilter {


    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user==null){
            //未登录，跳转到登录界面
            request.setAttribute("msg","想要结算请先登录");
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        }else {
            chain.doFilter(request,response);
        }
    }
}
