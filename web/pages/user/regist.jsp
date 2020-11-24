<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>尚硅谷会员注册页面</title>
        <%@ include file="../../WEB-INF/include/base.jsp" %>
        <script type="text/javascript">
            $(function () {
                $("#sub_btn").click(function () {
                    //用户名校验，规则：用户名只能是6-12为的字母、数字、_
                    //取用户名值
                    var uname = $("#username").val();
                    var regUName = /^\w{6,12}$/;
// 			var regUName = /^[a-zA-Z0-9_-\.]{6,12}$/;
                    if (regUName.test(uname) == false) {
                        alert("用户名不合法，");
                        return false;
                    }
                    //验证邮箱 sdfd@163.
                    var email = $("#email").val();
                    var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
                    if (!regEmail.test(email)) {
                        alert("邮箱格式不正确！");
                        return false;
                    }

                });

            });
        </script>

        <style type="text/css">
            .login_form {
                height: 420px;
                margin-top: 25px;
            }

        </style>
    </head>
    <body>
        <div id="login_header">
            <img class="logo_img" alt="" src="../../static/img/logo.gif">
        </div>

        <div class="login_banner">

            <div id="l_content">
                <span class="login_word">欢迎注册</span>
            </div>

            <div id="content">
                <div class="login_form">
                    <div class="login_box">
                        <div class="tit">
                            <h1>注册尚硅谷会员</h1>
                            <%--<span class="errorMsg"><%=request.getAttribute("msg")==null?"":request.getAttribute("msg") %></span>--%>
                            <span class="errorMsg">${requestScope.msg}</span>
                        </div>
                        <div class="form">
                            <form action="/UserServlet?method=regist" method="post">
                                <label>用户名称：</label>
                                <input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1"
                                       name="username" id="username" value="${param.username}"/>
                                <br/>
                                <br/>
                                <label>用户密码：</label>
                                <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
                                       name="password" id="password"/>
                                <br/>
                                <br/>
                                <label>确认密码：</label>
                                <input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1"
                                       name="repwd" id="repwd"/>
                                <br/>
                                <br/>
                                <label>电子邮件：</label>
                                <input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1"
                                       name="email" id="email" value="${param.email}"/>
                                <br/>
                                <br/>
                                <label>验证码：</label>
                                <input class="itxt" type="text" style="width: 150px;" id="code"/>
                                <img alt="" src="../../static/img/code.bmp" style="float: right; margin-right: 40px">
                                <br/>
                                <br/>
                                <input type="submit" value="注册" id="sub_btn"/>

                            </form>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <div id="bottom">
			<span>
				尚硅谷书城.Copyright &copy;2015
			</span>
        </div>
    </body>
</html>