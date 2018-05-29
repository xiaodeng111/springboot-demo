<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>${systemName}</title>
    <link rel="shortcut icon" type=image/x-icon href="${base}/resources/assets/images/favicon.ico">
    <link rel="stylesheet" href="${base }/resources/common/script/bootstrap/css/bootstrap.min.css">
    <script src="${base }/resources/common/script/jquery-1.11.3.min.js"></script>
    <!--[if !IE]>-->
    <link rel="stylesheet" href="${base }/resources/assets/css/common.css">
    <link rel="stylesheet" href="${base }/resources/assets/css/account/login.css">
    <!--<![endif]-->
    
    <script>
		$(function (){
			$("#loginName").on('click',function () {
                document.getElementById("userErr").innerHTML = "";
            });
			$("#password").on('click',function () {
                document.getElementById("userErr").innerHTML = "";
            });
		});

	</script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="layout">
            <!--logo部分-->
            <div class="logo">
                    <div>
                        <p class="logo-title">资源管理系统</p>
                    </div>
            </div>
            <!--表单部分-->
            <div class="form-group">
            	<form id="loginform" action="${base }/login" method="post">
	                <div class="username">
	                    <input type="text" name="loginName" id="loginName" placeholder="用户名/邮箱" >
	                    <span class="errInfo userErr" style="color:red;" id="userErr">${sessionScope.userErr}</span>
	                </div>
	                <div class="password">
	                    <input type="password" id="password" name="password" placeholder="密码">
                        <span class="errInfo userErr" style="color:red;"></span>
	                </div>
	                <div class="signUp">
	                    <input type="submit" value="登录">
	                </div>
                </form>
            </div>
            <div class="footer">
                    <p>Copyright&copy;&nbsp;${companyName}</p>
                    <p>All right reserved</p>
            </div>

            <c:if test="${!empty sessionScope.userErr}">
                <c:remove var="userErr" scope="session" />
            </c:if>
        </div>
    </div>
</div>
<script src="${base }/resources/common/script/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>