package servlet;

import bean.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 *
 */
@WebServlet(name = "UserServlet", urlPatterns = "/UserServlet")
public class UserServlet extends BaseServlet {
    private final UserService userService = new UserServiceImpl();

    /**
     * 登录
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.getUser(new User(null, username, password, null,null));
        if (user == null) {
            //标记，在request域中存放数据
            request.setAttribute("msg", "用户名或密码有误，请重新输入！");
            //失败，请求转发到登录页面
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);

        } else {

            //成功，请求重定向到登录成功页面
            session.setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/pages/user/login_success.jsp");


        }
    }

    /**
     * 注册
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //取用户名值
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String isadmin = request.getParameter("isadmin");
        int admin=isadmin==null?0:1;//选中1 否则 0

        //获取验证码
        String code = request.getParameter("code");
        //获取session域中的验证码文本
        String code2 = session.getAttribute("KAPTCHA_SESSION_KEY").toString();

        if (code2 != null && code2.equals(code)) {
            //调用service中的方法,判断用户名是否存在
            boolean isUserExist = userService.checkUserName(username);
            if (isUserExist) {
                //标记，域中存放数据
                request.setAttribute("msg", "用户名已存在！");
                //用户名存在，转发
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            } else {
                //用户名不存在,saveUser();
                boolean isSave = userService.saveUser(new User(null, username, password, email,admin));
                if (isSave) {//如果注册成功
                    //重定向到注册成功页面
                    response.sendRedirect(request.getContextPath() + "/pages/user/regist_success.jsp");
                } else {
                    //保存失败，转发
                    request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
                }

            }
        }else {
            //标记，域中存放数据
            request.setAttribute("msg", "验证码输入错误！");
            //用户名存在，转发
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
        }
    }

    /**
     * 注销
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("user");
        response.sendRedirect(request.getContextPath() + "index.jsp");
    }

    /**
     * 检查用户名是否存在
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void checkUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        String username = request.getParameter("username");
        boolean isUserExist = userService.checkUserName(username);
        // if (isUserExist){
        //     //用户名存在
        //     writer.write("用户名已存在，请重新输入！");
        // }else {
        //     writer.write("用户名可用！");
        // }
        writer.print(isUserExist);
    }
}
