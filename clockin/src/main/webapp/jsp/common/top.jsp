<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/jsp/common/taglibs.jsp"%>
<script language="javascript">
	function liClick(action, flag) {
		if (flag == 1) {
			window.open(action);
		} else {
			self.location = action;
		}
	}
</script>
<div class="row nav-header">
    <!--logo-->
    <a class="nav-brand" href="${base}/gotoMain">
        <img src="${base }/resources/assets/images/logo.png">
    </a>
    <a class="navbar-brand-desc hidden-xs" href="${base}/gotoMain.${actionExt}"><%=Property.SYSTEM_NAME %></a>
    <!--按钮-->
    <ul class="navBars right hidden-xs">
        <c:if test="${sessionScope.manager.roleId==1}">
        	<li onclick="liClick('${base }/admin/manager/onMain',1);"><a href="javascript:void()">权限管理</a></li>
        </c:if>
        <li onclick="liClick('${base }/logout');"><a href="${base }/logout">退出</a></li>
    </ul>
</div>