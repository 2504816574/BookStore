<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../WEB-INF/include/base.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">

        <title>书城首页</title>
    </head>
    <body>
        <div id="header">
            <img class="logo_img" alt="" src="../../static/img/logo.gif">
            <span class="wel_word">网上书城</span>
            <%@ include file="../../WEB-INF/include/welcome.jsp" %>
        </div>

        <div id="main">
            <div id="book">
                <div class="book_cond">
                    价格：<input type="text" name="minPrice" value="${param.minPrice}"> 元 -
                    <input type="text" name="maxPrice" value="${param.maxPrice}"> 元
                    <button>查询</button>
                </div>
                <div style="text-align: center">
                    <c:if test="${not empty sessionScope.cart.totalCount && sessionScope.cart.totalCount != 0 }">
                        <span>您的购物车中有${sessionScope.cart.totalCount }件商品</span>
                    </c:if>
                    <div>
                        <c:if test="${not empty sessionScope.title}">
                            您刚刚将<span style="color: red">${sessionScope.title }</span>加入到了购物车中
                        </c:if>
                        <c:remove var="title" scope="session"/>
                        <c:if test="${not empty sessionScope.msg}">
                            <span style="color: red">${sessionScope.msg }</span>
                        </c:if>
                        <c:remove var="msg" scope="session"/>
                    </div>
                </div>

                <c:forEach items="${requestScope.page.list}" var="book">
                    <div class="b_list">
                        <div class="img_div">
                            <img class="book_img" alt="" src="../../static/img/default.jpg"/>
                        </div>
                        <div class="book_info">
                            <div class="book_name">
                                <span class="sp1">书名:</span>
                                <span class="sp2">${book.title}</span>
                            </div>
                            <div class="book_author">
                                <span class="sp1">作者:</span>
                                <span class="sp2">${book.author}</span>
                            </div>
                            <div class="book_price">
                                <span class="sp1">价格:</span>
                                <span class="sp2">￥${book.price}</span>
                            </div>
                            <div class="book_sales">
                                <span class="sp1">销量:</span>
                                <span class="sp2">${book.sales}</span>
                            </div>
                            <div class="book_amount">
                                <span class="sp1">库存:</span>
                                <span class="sp2">${book.stock}</span>
                            </div>
                            <div class="book_add">
                                <button id="${book.id}">加入购物车</button>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <div id="page_nav">
                <c:choose>
                    <%--[1]23     总页数小于3 --%>
                    <c:when test="${page.totalPageNo<5 }">
                        <c:set var="begin" value="1"></c:set>
                        <c:set var="end" value="${page.totalPageNo }"></c:set>
                    </c:when>
                    <%--[1]2345     1[2]345     12[3]45
                  总页数当前页大于等于5但当前页小于等于3--%>
                    <c:when test="${page.pageNo<=3 }">
                        <c:set var="begin" value="1"></c:set>
                        <c:set var="end" value="5"></c:set>
                    </c:when>
                    <%--23[4]56     34[5]67     45[6]78
                    总页数当前页大于等于5且当前页小于总页数减二--%>
                    <c:when test="${page.pageNo>3 && page.pageNo <= page.totalPageNo-2}">
                        <c:set var="begin" value="${page.pageNo-2 }"></c:set>
                        <c:set var="end" value="${page.pageNo+2 }"></c:set>
                    </c:when>
                    <%--    456[7]8     4567[8]        总页数当前页大于等于5且当前页到后两页--%>
                    <c:otherwise>
                        <c:set var="begin" value="${page.totalPageNo-4 }"></c:set>
                        <c:set var="end" value="${page.totalPageNo }"></c:set>
                    </c:otherwise>
                </c:choose>
                <c:forEach var="i" begin="${begin}" end="${end}" step="1">
                    <c:if test="${page.pageNo == i }">
                        【${i}】
                    </c:if>
                    <c:if test="${page.pageNo != i }">
                        <a href="${pageContext.request.contextPath}/BookClientServlet?method=getBooksByPageAndPrice&pageNo=${i}&minPrice=${param.minPrice}&maxPrice=${param.maxPrice}">${i }</a>
                    </c:if>
                </c:forEach>
                共${requestScope.page.totalPageNo}页，${requestScope.page.totalRecord}条记录 &nbsp 到第 &nbsp
                <input value="${requestScope.page.pageNo}" name="pn" id="pn_input"/>&nbsp 页 &nbsp
                <input id="sub_page" type="button" value="确定">
            </div>
        </div>
        <div id="bottom">
            <span>
                书城.Copyright &copy;2020
            </span>
        </div>
    </body>
    <script type="text/javascript">
        $(function () {
            //实现确定按钮分页查询
            $("#sub_page").click(function () {
                //取pageNo值
                var pageNo = $("#pn_input").val();
                var minPrice = $("input[name='minPrice']").val();
                var maxPrice = $("input[name='maxPrice']").val();
                //请求BookServlet
                location = "/BookClientServlet?method=getBooksByPageAndPrice&pageNo=" + pageNo + "&minPrice=" + minPrice + "&maxPrice=" + maxPrice;
            });
            //带价格区间的分页查询
            $(".book_cond :button").click(function () {
                var pageNo = $("#pn_input").val();
                var minPrice = $("input[name='minPrice']").val();
                var maxPrice = $("input[name='maxPrice']").val();
                location = "/BookClientServlet?method=getBooksByPageAndPrice&pageNo=" + pageNo + "&minPrice=" + minPrice + "&maxPrice=" + maxPrice;
            });
            //加入购物车
            $(".book_add button").click(function () {
                //获取bookid
                var bookid = $(this).attr("id");
                //servlet
                location = "/CartServlet?method=addBookToCart&bookId=" + bookid;

            });


        })

    </script>
</html>