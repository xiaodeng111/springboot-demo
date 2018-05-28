<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.demo.clockin.common.constant.Property" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="base" value="<%=Property.BASE%>"/>
<c:set var="systemName" value="<%=Property.SYSTEM_NAME%>"/>
<c:set var="companyName" value="<%=Property.COMP_NAME%>"/>
<c:set var="imageTypes" value="<%=Property.FILE_IMAG_TYPES%>"/>