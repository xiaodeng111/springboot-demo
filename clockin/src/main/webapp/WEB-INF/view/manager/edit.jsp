<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>账号修改</title>
<%@ include file="/jsp/common/meta.jsp"%>
<script language="javascript">

//初始化方法

// 表单提交
function checkForm(){
	/* var subSystems = '';
	for(var i = 0 ; i < $("input[id='sub_system_ids']").length ; i ++){
		if($("input[id='sub_system_ids']")[i].checked){
			subSystems += $("input[id='sub_system_ids']")[i].value;
			if(i<$("input[id='sub_system_ids']").length-1)
				subSystems += ",";
		}
	}
	$("input[name='subSystemIds']").val(subSystems); */
	var name = $("input[name='managerInfo.name']").val();
	if(name==null || name==''){
		return false;
	}
	return true;
}

</script>
<style type="text/css">
    body{ font-size:12px;}
    .l-table-edit {}
    .l-table-edit-td{ padding:4px;}
    .l-button-submit,.l-button-reset{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
    .l-verify-tip{ left:230px; top:120px;}
    .col-md-9{width: 70%;}
</style>
</head>
<body onload="">
	<div class="col-md-6 ui-sortable">
	    <!-- begin panel -->
	    <div class="panel panel-inverse">
	         <div class="panel-heading">
	             <h4 class="panel-title">账号信息修改</h4>
	         </div>
	         <div class="panel-body panel-form">
	            <form data-parsley-validate="true" class="form-horizontal form-bordered"  name="managerForm" id="managerForm" method="post" action="${base}/admin/manager/edit" onsubmit="return checkForm();" data-validate="parsley">
		            <input type="hidden" id="manager.id" name="manager.id" value="${requestScope.manager.id}"/>					
					<input type="hidden" name="loginName" id="loginName" value="${ requestScope.loginName}"/>
	            	<input type="hidden" name="roleId" id="roleId" value="${ requestScope.roleId}"/>
	            	<input type="hidden" name="isOnjob" id="isOnjob" value="${ requestScope.isOnjob}"/>
	            	<input type="hidden" name="isLock" id="isLock" value="${ requestScope.isLock}"/>
	            	<input type="hidden" name="nowPage" id="commonNowPage" value="${ requestScope.nowPage}"/>
	            	<input type="hidden" name="pageSize" id="commonPageSize" value="${ requestScope.pageSize}"/>
					
                    <div class="form-group">
                        <label class="col-md-3 control-label ui-sortable">姓名：</label>
                        <div class="col-md-9 ui-sortable">
                            <input name="managerInfo.name" maxlength="60" data-parsley-required="true" value="${requestScope.manager.name }" type="text" id="managerInfo.name"  class="form-control" type="text"/>
                        </div>
                    </div>
                    <div class="form-group gender">
						<label class="col-md-3 control-label ui-sortable">性别：</label>
						<div class="col-md-9 ui-sortable">
							<label class="radio-inline">
                                <input type="radio" name="managerInfo.gender" value="1" <c:if test="${requestScope.manager.gender == 1}">checked</c:if> />男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="managerInfo.gender" value="2" <c:if test="${requestScope.manager.gender == 2}">checked</c:if>/>女
                            </label>
                        </div>
					</div>
                    <div class="form-group">
                        <label class="col-md-3 control-label ui-sortable">角色：</label>
                        <div class="col-md-9 ui-sortable">
                        	<c:forEach items="${roleList }" var="roles">
	                       		<label class="radio-inline">
	                                <input type="radio" name="manager.roleId" value="${roles.id }" <c:if test="${roles.id == requestScope.manager.roleId}">checked</c:if>/>${roles.name }
	                            </label>
                            </c:forEach>
                        </div>
                    </div>
					<div class="form-group">
                        <label class="col-md-3 control-label ui-sortable">是否在职：</label>
                        <div class="col-md-9 ui-sortable">
                        	<label class="radio-inline">
                                <input type="radio" name="manager.isOnjob" value="1" <c:if test="${requestScope.manager.isOnjob == 1}">checked</c:if>/>是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="manager.isOnjob" value="0" <c:if test="${requestScope.manager.isOnjob == 0}">checked</c:if>/>否
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label ui-sortable">账号状态：</label>
                        <div class="col-md-9 ui-sortable">
                        	<label class="radio-inline">
                                <input type="radio" name="manager.isLock" value="1" <c:if test="${requestScope.manager.isLock == 1}">checked</c:if>/>禁用
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="manager.isLock" value="0" <c:if test="${requestScope.manager.isLock == 0}">checked</c:if>/>启用
                            </label>
                        </div>
                    </div>
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 ui-sortable"></label>
						<div class="col-md-6 col-sm-6 ui-sortable">
							<input type="submit" value="保存" id="Button1" class="btn btn-primary" /> 
							&nbsp;&nbsp;
							<input type="button" value="取消" class="btn btn-primary" onclick="javascript: history.back(-1);"/>
						</div>
					</div>
	           </form>
	        </div>
	    </div>
	 	<!-- end panel -->
	</div>
	<!-- ================== BEGIN PAGE LEVEL JS ================== jiaoyan -->
	<script src="${base }/resources/common/script/parsley/dist/parsley.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->
</body>
</html>