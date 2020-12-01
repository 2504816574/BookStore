<%--
  Created by IntelliJ IDEA.
  User: 25048
  Date: 2020/11/20
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty sessionScope.user}">
    <div>
        <a href="../../pages/user/login.jsp">登录</a> |
        <a href="../../pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
        <a href="../../pages/cart/cart.jsp">购物车</a>
        <a href="../../pages/manager/manager.jsp">后台管理</a>
    </div>
</c:if>
<c:if test="${not empty sessionScope.user}">
    <div>
        <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
        <a href="../../pages/order/order.jsp">我的订单</a>
        <a href="${pageContext.request.contextPath}/UserServlet?method=logout">注销</a>&nbsp;&nbsp;
        <a href="../../index.jsp">返回</a>
    </div>
</c:if>


