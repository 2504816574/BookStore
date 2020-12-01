<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>编辑图书</title>
        <%@ include file="../../WEB-INF/include/base.jsp" %>
        <style type="text/css">
            h1 {
                text-align: center;
                margin-top: 200px;
            }

            h1 a {
                color: red;
            }

            input {
                text-align: center;
            }
        </style>
    </head>
    <body>
        <div id="header">
            <img class="logo_img" alt="" src="../../static/img/logo.gif">
            <span class="wel_word">编辑图书</span>
            <%@include file="../../WEB-INF/include/header.jsp" %>
        </div>

        <div id="main">
            <form action="${pageContext.request.contextPath}/BookServlet?method=editBook" method="post">
                <input type="hidden" name="id" value="${book.id}">
                <table>
                    <tr>
                        <td>名称</td>
                        <td>价格</td>
                        <td>作者</td>
                        <td>销量</td>
                        <td>库存</td>
                        <td colspan="2">操作</td>
                    </tr>
                    <tr>
                        <td><input name="title" type="text" value="${book.title}"/></td>
                        <td><input name="price" type="text" value="${book.price}"/></td>
                        <td><input name="author" type="text" value="${book.author}"/></td>
                        <td><input name="sales" type="text" value="${book.sales}"/></td>
                        <td><input name="stock" type="text" value="${book.stock}"/></td>
                        <td><input type="submit" value="提交"/></td>
                    </tr>
                </table>
            </form>


        </div>

        <div id="bottom">
			<span>
				尚硅谷书城.Copyright &copy;2015
			</span>
        </div>
    </body>
</html>