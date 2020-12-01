<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>图书管理</title>
        <%@ include file="../../WEB-INF/include/base.jsp" %>
        <script type="text/javascript">
            $(function () {
                $(".delbook").click(function () {
                    // var title=$(this).parents("tr").children().html();
                    var title = $(this).attr("id");
                    if (confirm("确定删除《" + title + "》吗？") === false) {
                        return false;
                    }
                })
                //实现确定按钮分页查询
                $("#sub_page").click(function () {
                    //取pageNo值
                    var pageNo = $("#pn_input").val();
                    //请求BookServlet
                    location = "BookServlet?method=getBooksByPage&pageNo=" + pageNo;
                });
            })
        </script>
    </head>
    <body>

        <div id="header">
            <img class="logo_img" alt="" src="../../static/img/logo.gif">
            <span class="wel_word">图书管理系统</span>

        </div>

        <div id="main">
            <table>
                <tr>
                    <td>名称</td>
                    <td>价格</td>
                    <td>作者</td>
                    <td>销量</td>
                    <td>库存</td>
                    <td colspan="2">操作</td>
                </tr>
                <c:forEach items="${requestScope.page.list}" var="book">
                    <tr>
                        <td>${book.title}</td>
                        <td>${book.price}</td>
                        <td>${book.author}</td>
                        <td>${book.sales}</td>
                        <td>${book.stock}</td>
                        <td><a href="${pageContext.request.contextPath}/BookServlet?method=getBookById&bookId=${book.id}">修改</a></td>
                        <td><a class="delbook" id="${book.title}"
                               href="${pageContext.request.contextPath}/BookServlet?method=delBookById&bookId=${book.id}">删除</a></td>
                    </tr>
                </c:forEach>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><a href="pages/manager/book_edit.jsp">添加图书</a></td>
                </tr>
            </table>
            <br><br><%--防止分页紧贴--%>
            <div id="page_nav"><%--分页--%>
                <c:if test="${requestScope.page.pageNo !=1}">
                    <a href="${pageContext.request.contextPath}/BookServlet?method=getBooksByPage&pageNo=1">首页</a> &nbsp
                </c:if>

                <c:if test="${requestScope.page.pageNo-1>0}">
                    <a href="${pageContext.request.contextPath}/BookServlet?method=getBooksByPage&pageNo=${requestScope.page.pageNo-1}">上一页</a>
                    <a href="${pageContext.request.contextPath}/BookServlet?method=getBooksByPage&pageNo=${requestScope.page.pageNo-1}">${requestScope.page.pageNo-1}</a>
                </c:if>
                【${requestScope.page.pageNo}】
                <c:if test="${requestScope.page.pageNo+1<=requestScope.page.totalPageNo}">
                    <a href="${pageContext.request.contextPath}/BookServlet?method=getBooksByPage&pageNo=${requestScope.page.pageNo+1}">${requestScope.page.pageNo+1}</a>
                    <a href="${pageContext.request.contextPath}/BookServlet?method=getBooksByPage&pageNo=${requestScope.page.pageNo+1}">下一页</a> &nbsp
                </c:if>
                <c:if test="${requestScope.page.pageNo != requestScope.page.totalPageNo}">
                    <a href="${pageContext.request.contextPath}/BookServlet?method=getBooksByPage&pageNo=${requestScope.page.totalPageNo}">末页</a> &nbsp
                </c:if>
                共${requestScope.page.totalPageNo}页，${requestScope.page.totalRecord}条记录 &nbsp 到第 &nbsp <input value="1" name="pn" id="pn_input"/> &nbsp
                页 &nbsp<input id="sub_page" type="button" value="确定">
            </div>
        </div>

        <div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2020
		</span>
        </div>
    </body>
</html>