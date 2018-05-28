<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<%
	if(session.getAttribute("manager") == null){
	%>
	<script type="text/javascript">
		window.location.href = "<%=Property.BASE %>"; 	
	</script>
	<%
	}
	%>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>${systemName}</title>
    <link rel="shortcut icon" type=image/x-icon href="${base}/resources/assets/images/favicon.ico">
    <link rel="stylesheet" href="${base }/resources/common/script/bootstrap/css/bootstrap.min.css">

	<link rel="stylesheet" href="${base }/resources/assets/css/common.css">
    <link rel="stylesheet" href="${base }/resources/assets/css/systemManage.css">
</head>
<body>
    <!--头部通栏-->
    <div class="navbar-fixed-top">
        <div class="container header">
            <%@ include file="/jsp/common/top.jsp"%>
        </div>
    </div>
    <!--页面内容部分-->
    <div class="content">
        <!--个人头像信息展示部分-->
        <div class="container">
            <div class="row hidden-xs">
                <div class="avatorMsg">
                    <div class="avator">
                        <a class="hidden-xs" href="#">
                            <img src="${sessionScope.manager.logoUrl}" width="100px" height="100px" alt="${sessionScope.manager.name}">
                        </a>
                        <span>${sessionScope.manager.name}</span>
                    </div>
                    <div class="messageBtn">
                        <a class="personalInfo" href="${base}/account/onEdit" target="_blank">个人信息</a>
                        <a class="changePassword" href="${base}/account/onChange" target="_blank">修改密码</a>
                    </div>
                </div>
            </div>
            <!--底部icon部分-->
            <div class="row bottomIcons col-md-12">
                <ul>
                	<c:forEach items="${sessionScope.subSystemList}" var="item">
	                    <li class="col-lg-2 col-md-2 col-sm-3 col-xs-3">
							<a href="${base}/direct.${actionExt}?subId=${item.id}" target="_blank">
	                            <img src="${item.iconUrl}" alt="${item.name}">
	                            <span>${item.name}</span>
	                        </a>
                        </li>
					</c:forEach>
                </ul>
            </div>
        </div>
    </div>
    
    <div class="footer">
        <p>Copyright&copy;&nbsp;${companyName}</p>
        <p>All right reserved</p>
    </div>
<script src="${base }/resources/common/script/jquery-1.11.3.min.js"></script>
<script src="${base }/resources/common/script/bootstrap/js/bootstrap.min.js"></script>
<script src="${base }/resources/common/script/fastclick/lib/fastclick.js"></script>
<script src="${base }/resources/common/script/common.js"></script>
<script>
    $(function() {
        //实例化fastclick
        FastClick.attach(document.body);
    });
</script>
</body>
</html>