<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>${systemName}</title>
	<link rel="stylesheet" href="${base }/resources/assets/css/common.css">
	<link rel="shortcut icon" type=image/x-icon href="${base}/resources/assets/images/favicon.ico">
	<link rel="stylesheet" href="${base }/resources/assets/css/account/changePersonalInfo.css">
	<link rel="stylesheet" href="${base }/resources/assets/css/account/imgareaselect-default.css">
	<link rel="stylesheet" href="${base }/resources/common/script/bootstrap/css/bootstrap.min.css">

	<script src="${base }/resources/common/script/fastclick/lib/fastclick.js"></script>
	<script src="${base}/resources/common/script/jquery-1.11.3.min.js" type="text/javascript"></script>
	<script src="${base }/resources/common/script/bootstrap/js/bootstrap.min.js"></script>

	<script src="${base}/resources/assets/js/account/jquery.imgareaselect.min.js" type="text/javascript"></script>
	<script src="${base}/resources/assets/js/account/upload.js" type="text/javascript"></script>

	<script type="text/javascript">
        var uploadTypesImg = '${imageTypes}';
        var baseUrl = '${base}';
        var ias;
        $(function(){
            <c:if test="${requestScope.flag==1}">
            alert('修改成功');
            </c:if>
            <c:if test="${requestScope.flag==2}">
            alert('修改失败');
            </c:if>
            FastClick.attach(document.body);
        });

        function checkForm() {
            return true;
        }
        function cancleClick() {
            window.location = baseUrl + '/account/onEdit';
        }
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
	<div class="container">
		<div class="row">
			<div class="msgTitle col-md-10 col-md-offset-1">
				<h4>修改个人信息</h4>
			</div>
		</div>
		<div class="row h-content">
			<div class="avatorMsg col-lg-3 col-md-3  col-sm-12  col-xs-12 ">
				<div>
					<input type="file" style="display: none" id="upImg" onchange="changeImg(this)">
					<label for="upImg">
						<img id="imghead" src="${requestScope.managerInfo.logoUrl}" width="130" height="130" alt="点击上传头像">
					</label>
				</div>
				<p>${requestScope.managerInfo.name}</p>
				<div class="boxFooter">
					<input type="hidden" name="x1" value="0">
					<input type="hidden" name="y1" value="0">
					<input type="hidden" name="x2" value="100">
					<input type="hidden" name="y2" value="100">
					<div id="imgmsg"></div>
				</div>
			</div>
			<div class="msgDetail col-lg-7 col-md-7 col-sm-10 col-xs-12">
				<form data-parsley-validate="true"  name="managerInfoForm" id="managerInfoForm" method="post" action="${base }/account/edit" onsubmit="return checkForm();">
					<input type="hidden" id="id" name="id" value="${requestScope.managerInfo.id}" />
					<input name="logo" type="hidden" id="logo" value="${requestScope.managerInfo.logo}" />

					<div class="form-group name">
						<label for="name">姓名：</label>
						<input type="text" id="name" maxlength="59" name="name" placeholder="请填写您的真实姓名" value="${requestScope.managerInfo.name}">
					</div>
					<div class="form-group nickname" <c:if test="${sessionScope.manager.editNicknameCode != 0}">style="margin: 0;"</c:if>>
						<label for="name">昵称：</label>
						<input type="text" id="nickname" name="nickname" style="height:40px;" <c:if test="${sessionScope.manager.editNicknameCode == 0}">style="background-color: #d9e0e0;"</c:if> maxlength="59"  placeholder="将显示在APP上" value="${sessionScope.manager.nickname}" <c:if test="${sessionScope.manager.editNicknameCode == 0}"> readonly</c:if>>
					</div>
					<c:if test="${sessionScope.manager.editNicknameCode != 0}">
						<div class="form-group" id="mark" style="margin-bottom: 20px;">
							<span style="color: #d9c0e0;">24小时内只能修改一次</span>
						</div>
					</c:if>
					<div class="form-group mobile">
						<label for="mobile">手机号码：</label>
						<input type="text" data-parsley-length="[11,11]" data-parsley-type="number" id="mobile" maxlength="30" name="mobile" placeholder="为保证能正常使用系统请正确填写手机号码" value="${requestScope.managerInfo.mobile}">
					</div>
					<div class="form-group email">
						<label for="email">邮箱：</label>
						<input type="text" data-parsley-type="email" id="email" maxlength="256" name="email" placeholder="如无企业邮箱请填写常用的私人邮箱" value="${requestScope.managerInfo.email}">
					</div>
					<div class="form-group wechat">
						<label for="wechat">微信号：</label>
						<input type="text" id="wechat" maxlength="100" name="wechat" placeholder="微信号" value="${requestScope.managerInfo.wechat}">
					</div>
					<div class="form-group qq">
						<label for="qq">QQ：</label>
						<input type="text" id="qq" maxlength="30" name="qq" placeholder="QQ" value="${requestScope.managerInfo.qq}">
					</div>
					<div class="form-group academies">
						<label for="academies">毕业院校：</label>
						<input type="text" id="academies" maxlength="200" name="academies" placeholder="请填写您毕业院校的全称" value="${requestScope.managerInfo.academies}">
					</div>
					<div class="form-group awards">
						<label for="awards">所获奖项：</label>
						<textarea id="awards" maxlength="200" name="awards" placeholder="为了更好的宣传您请您详细填写所获奖项" >${requestScope.managerInfo.awards}</textarea>
					</div>
					<div class="form-group summary">
						<label for="summary">简要介绍：</label>
						<textarea id="summary" maxlength="256" name="summary" placeholder="请用一两句话简单介绍自己">${requestScope.managerInfo.summary}</textarea>
					</div>
					<div class="form-group details">
						<label for="details">详细介绍：</label>
						<textarea name="details" id="details" placeholder="请详细介绍自己，包括但不限于过往的教学经验、心得及成绩">${requestScope.managerInfo.details}</textarea>
					</div>
					<div class="checkbox ">
						<button class="h-save" type="submit">确认修改</button>
						<button class="h-cancle" type="button" onclick="javascript:cancleClick();">取消修改</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close modalClose" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					上传头像
				</h4>
			</div>
			<div class="modal-body">
				<div class="h-imgupload-wrapper" id="preview" style="width: 550px;text-align: center;margin: 0 auto;">

				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default modalClose" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary" id="subPhoto">确定上传</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>
</body>
<!-- ================== BEGIN PAGE LEVEL JS ================== jiaoyan -->
<script src="${base }/resources/common/script/parsley/dist/parsley.js"></script>
<!-- ================== END PAGE LEVEL JS ================== -->
</html>