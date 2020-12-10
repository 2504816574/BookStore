<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../WEB-INF/include/base.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>购物车</title>

        <script type="text/javascript">
            $(function () {
                $(".cartItemCount").change(function () {
                    //获取默认值
                    var dValue = this.defaultValue;
                    //获取bookId
                    var bookId = $(this).attr("id");
                    //获取库存
                    var stock = $(this).attr("name");
                    //获取输入数量
                    var count = $(this).val();
                    //定义正则规则（非零的正整数）
                    var countReg = /^\+?[1-9][0-9]*$/;
                    if (!countReg.test(count)) {
                        alert("购买数量输入有误，请重新输入！（非零的正整数）");
                        $(this).val(dValue);
                        return false;
                    }
                    //验证库存
// 			        alert("countType:"+typeof(parseInt(count)));
                    if (parseInt(count) > parseInt(stock)) {
                        alert("库存不足，库存只剩" + stock + "件商品啦！");
                        $(this).val(dValue);
                        return false;
                    }
                    var $amountTr = $(this).parent().next().next();
                    //调用CartServlet
                    // location = "/CartServlet?method=updateCartItemCount&bookId=" + bookId + "&count=" + count;
                    //Ajax异步请求
                    $.getJSON("/CartServlet?method=updateCartItemCount", {
                        "bookId": bookId,
                        "count": count
                    }, function (jsonObj) {
                        $(".b_count").html(jsonObj.totalCount);
                        $(".b_price").html(jsonObj.totalAmount);
                        $amountTr.html(jsonObj.amount);


                    })
                });
            });
        </script>
    </head>
    <body>

        <div id="header">
            <img class="logo_img" alt="" src="../../static/img/logo.gif">
            <span class="wel_word">购物车</span>
            <%@ include file="../../WEB-INF/include/welcome.jsp" %>
        </div>

        <div id="main">
            <c:if test="${empty sessionScope.cart.cartItems }">
                <h1 align="center">购物车暂无商品，快来<a href="../../index.jsp" style="color:red">购物</a>呀</h1>

            </c:if>
            <c:if test="${not empty sessionScope.cart.cartItems }">
                <table>
                    <tr>
                        <td>商品名称</td>
                        <td>数量</td>
                        <td>单价</td>
                        <td>金额</td>
                        <td>操作</td>
                    </tr>
                    <c:forEach var="cartItem" items="${sessionScope.cart.cartItems}">
                        <tr>
                            <td>${cartItem.book.title}</td>
                            <td>
                                <input id="${cartItem.book.id}" name="${cartItem.book.stock}" type="text"
                                       class="cartItemCount" value="${cartItem.count }" size="2"
                                       style="text-align: center">
                            </td>
                            <td>${cartItem.book.price}</td>
                            <td>${cartItem.amount}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/CartServlet?method=delCartItem&bookId=${cartItem.book.id }">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <div class="cart_info">
                <span class="cart_span">购物车中共有<span
                        class="b_count">${sessionScope.cart.totalCount==null?0:sessionScope.cart.totalCount}</span>件商品</span>
                    <span class="cart_span">总金额<span
                            class="b_price">${sessionScope.cart.totalAmount==null?0:sessionScope.cart.totalAmount}</span>元</span>
                    <span class="cart_span"><a href="${pageContext.request.contextPath}/CartServlet?method=emptyCart">清空购物车</a></span>
                    <span class="cart_span"><a href="${pageContext.request.contextPath}/OrderServlet?method=checkout">去结账</a></span>
                    <span class="cart_span"><a href="../../index.jsp">继续购物</a></span>
                </div>
            </c:if>
        </div>

        <div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
        </div>
    </body>
</html>