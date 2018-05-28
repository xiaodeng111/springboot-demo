<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>${systemName}</title>
    <link rel="shortcut icon" type=image/x-icon href="${base}/resources/assets/images/favicon.ico">
    <link rel="stylesheet" href="${base }/resources/common/script/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${base }/resources/assets/css/common.css">
    <link rel="stylesheet" href="${base }/resources/assets/css/account/changePassword.css">
    <script src="${base }/resources/common/script/jquery-1.11.3.min.js"></script>
    <link rel="stylesheet" href="${base}/resources/assets/css/demo.css">
    <script type="text/javascript">
        var baseUrl = '${base}';
	    function checkForm(){
	    	var newpwd = document.getElementById("newPassword").value;
	    	var password = $("input[name='password']").val();
	    	if(password==null||password==''){
	    		return false;
	    	}
	    	if(newpwd.length<6){
	    		return false;
	    	}
	    	var cfmpwd = document.getElementById("confirmPassword").value;
	    	//alert(newpwd + "  " + cfmpwd);
	    	if(newpwd!=cfmpwd){
	    		return false;
	    	}else{
	    		return true;
	    	}
	    }
	    
	    function cancleClick(){
            window.location = baseUrl + '/account/onChange';
	    }
	    
		$(function() {
			<c:if test="${errmsg != null && errmsg != ''}">
				alert('${errmsg}');
			</c:if>
		});
	</script>
   		<style type="text/css">
			.parsley-error{
			    background: #ffdedd none repeat scroll 0 0 !important;
			    border-color: #ff5b57 !important;
			}
			.parsley-success{
			    background: #cee none repeat scroll 0 0 !important;
			    border-color: #00acac !important;
			}
			.parsley-errors-list{
			    color: #e5603b;
			    font-size: 12px !important;
			    line-height: inherit !important;
			    list-style-type: none !important;
			}
		</style>
</head>
<body>
<!--页面内容部分-->
<div class="content">
    <div class="panel panel-inverse">
        <div class="panel-header" style="width:100%;height: 100%;">
            <h4 class="panel-title not-line">修改密码</h4>
        </div>
        <div class="row">
            <div class="msgDetail col-lg-5 col-md-7 col-sm-10 col-xs-12">
                <form data-parsley-validate="true" name="userForm" id="userForm" method="post" action="${base }/account/change" onsubmit="return checkForm();">
                    <div class="form-group password">
                        <label for="password">原密码：</label>
                        <input data-parsley-required="true" type="password" id="password" name="password" placeholder="原密码" >
                    </div>
                    <div class="form-group newPassword">
                        <label for="newPassword">新密码：</label>
                        <input data-parsley-required="true" data-parsley-minlength="6" maxlength="60" type="password" id="newPassword" name="newPassword" placeholder="新密码">
                    </div>
                    <div class="form-group confirmPassword">
                        <label for="confirmPassword">再次输入新密码：</label>
                        <input data-parsley-required="true" data-parsley-minlength="6" maxlength="60" data-parsley-equalto="#newPassword" type="password" id="confirmPassword" name="confirmPassword" placeholder="再次输入新密码">
                    </div>
                    <div class="checkbox">
                        <button class="h-save" type="submit">确认修改</button>
                        <button class="h-cancle" type="button" onclick="javascript:cancleClick();">取消修改</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- ================== BEGIN PAGE LEVEL JS ================== jiaoyan -->
<script src="${base }/resources/common/script/parsley/dist/parsley.js"></script>
<!-- ================== END PAGE LEVEL JS ================== -->
</body>
</html>