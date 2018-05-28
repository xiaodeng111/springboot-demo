<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>新建账号</title>
<%@ include file="/jsp/common/meta.jsp"%>
<!-- ================== BEGIN PAGE LEVEL JS ================== jiaoyan -->
<script src="${base }/resources/common/script/parsley/dist/parsley.js"></script>
<!-- ================== END PAGE LEVEL JS ================== -->
<script language="javascript">
var loginNameFlag = false;
function checkForm(){

	if(!loginNameFlag){
		$("input[name='manager.loginName']").removeClass("parsley-success");
		$("input[name='manager.loginName']").addClass("parsley-error");
		return loginNameFlag;
	}
	$("#Button1").attr("disabled",true);
	return true;
}

function ajaxCheckLoginName(){
	var loginName = $("input[name='manager.loginName']").val();
	if(loginName){
		$.ajax({
			url:"${base}/admin/manager/checkLoginName",
			data:{"loginName":loginName},
			success:function(data){
				if(data>0){
					$("input[name='manager.loginName']").removeClass("parsley-success");
					$("input[name='manager.loginName']").removeClass("parsley-error");
					$("#parsley-id-9999").remove();
					$("input[name='manager.loginName']").addClass("parsley-error");
					loginNameFlag = false;
					alert("登录名重复");
				}else{
					$("input[name='manager.loginName']").removeClass("parsley-error");
					$("#parsley-id-9999").remove();
					loginNameFlag = true;
				}
			}
		});
	}
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
<body>
	<div class="col-md-6 ui-sortable">
	    <!-- begin panel -->
	    <div class="panel panel-inverse">
	         <div class="panel-heading">
	             <h4 class="panel-title">新建账号</h4>
	         </div>
	         <div class="panel-body panel-form">
	            <form data-parsley-validate="true" class="form-horizontal form-bordered"  name="managerForm" id="managerForm" method="post" action="${base }/admin/manager/add" onsubmit="return checkForm();">
	            	<input type="password" style="position: absolute; top: -1000px;"/>
                    <input type="password" style="position:absolute;left: -1000px;"/>
                    <div class="form-group">
                        <label class="col-md-3 control-label ui-sortable">登录名：</label>
                        <div class="col-md-9 ui-sortable">
                            <input id="manager.loginName" name="manager.loginName" maxlength="60" onblur="ajaxCheckLoginName()" data-parsley-remote="loginNameRepeat" data-parsley-remote-validator='loginNameRepeat' data-parsley-required="true" type="text" id="manager.loginName"  class="form-control" type="text" autocomplete="off"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label ui-sortable">密码：</label>
                        <div class="col-md-9 ui-sortable">
                            <input name="manager.password" data-parsley-required="true" data-parsley-minlength="6" data-parsley-maxlength="125" type="password" id="manager.password"  class="form-control" autocomplete="off"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label ui-sortable">姓名：</label>
                        <div class="col-md-9 ui-sortable">
                            <input name="managerInfo.name" maxlength="60" data-parsley-required="true" type="text" id="managerInfo.name"  class="form-control" type="text"/>
                        </div>
                    </div>
                    <div class="form-group gender">
						<label class="col-md-3 control-label ui-sortable">性别：</label>
						<div class="col-md-9 ui-sortable">
							<label class="radio-inline">
                                <input type="radio" name="managerInfo.gender" value="1" checked />男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="managerInfo.gender" value="2" />女
                            </label>
                        </div>
					</div>
                    <div class="form-group">
                        <label class="col-md-3 control-label ui-sortable">角色：</label>
                        <div class="col-md-9 ui-sortable">
                        	<c:forEach items="${roleList }" var="roles" varStatus="vstatus">
	                        	<label class="radio-inline">
	                                <input type="radio" name="manager.roleId" value="${roles.id }" <c:if test="${roles.id==3 }">checked</c:if>/>${roles.name }
	                            </label>
                            </c:forEach>
                        </div>
                    </div>
					<div class="form-group">
                        <label class="col-md-3 control-label ui-sortable">是否在职：</label>
                        <div class="col-md-9 ui-sortable">
                        	<label class="radio-inline">
                                <input type="radio" name="manager.isOnjob" value="1" checked />是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="manager.isOnjob" value="0" />否
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label ui-sortable">账号状态：</label>
                        <div class="col-md-9 ui-sortable">
                        	<label class="radio-inline">
                                <input type="radio" name="manager.isLock" value="1"/>禁用
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="manager.isLock" value="0" checked/>启用
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
</body>
</html>