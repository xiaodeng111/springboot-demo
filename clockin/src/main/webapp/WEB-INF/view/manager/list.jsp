<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>${systemName }</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="" name="author"/>
    <%@ include file="/jsp/common/meta.jsp"%>
    <!--dtgrid资源包引入-->
    <link rel="stylesheet" href="${base }/resources/common/plugins/DTgrid/jquery.dtGrid.min.css">
    <link rel="stylesheet" href="${base }/resources/assets/css/demo.css">
    <script src="${base}/resources/common/plugins/DTgrid/jquery.dtGrid.min.js"></script>
	<script src="${base}/resources/common/plugins/DTgrid/i18n/zh-cn.js"></script>
    <!--其他资源包-->
    <style type="text/css">
    	.modal-header{padding: 1px 15px;}
    </style>
</head>
<body>
<!-- 加载器 -->
<div id="page-loader" class="fade in"><span class="spinner"></span></div>

<!-- 页面容器--start -->
<div id="page-container" class="fade page-without-sidebar">
    <!-- 页面中间内容部分--start -->
    <div id="content" class="content">
        <!-- 列表标题条 -->
        <div class="row">
            <div class="rowTitle col-md-12 col-sm-12">
                <h4>账号列表</h4>
            </div>
        </div>
        <!--数据列表部分-->
        <div class="row">
            <div class="lists">
            	<div class="buttons col-md-2 col-sm-12 col-xs-12">
                    <button class="btn btn-info" type="button" onclick="add()">
                        <i class="fa fa-plus"></i>
                        <span>新增</span>
                    </button>
                </div>
                <div class="query col-md-10 col-sm-12 col-xs-12 text-right">
                	<div class="form-group inlineBlock">
                        <label>登录名</label>
                        <input id="manager.loginName" type="text" class="formList" name="manager.loginName" value="${requestScope.loginName}" placeholder="请输入登录名"/>
                    </div>
                    <div class="form-group inlineBlock">
                        <label>角色</label>
                        <select name="manager.roleId" id="manager.roleId" class="formList">
                        	<option value="">全部</option>
                            <c:forEach items="${rolesList}" var="roles">
								<option value="${roles.id }" <c:if test="${roles.id == requestScope.roleId}"> selected</c:if>>${roles.name}</option>
							</c:forEach>
                        </select>
                    </div>
                    <div class="form-group inlineBlock">
                        <label>是否在职</label>
                        <select name="manager.isOnjob" id="manager.isOnjob" class="formList">
                        	<option value="">全部</option>
							<option value="1" <c:if test="${requestScope.isOnjob == 1 }">selected</c:if>>是</option>
							<option value="0" <c:if test="${requestScope.isOnjob == 0 }">selected</c:if>>否</option>
                        </select>
                    </div>
                    <div class="form-group inlineBlock">
                        <label>账号状态</label>
                        <select name="manager.isLock" id="manager.isLock" class="formList">
                            <option value="">全部</option>
							<option value="0" <c:if test="${requestScope.isLock == 0 }">selected</c:if>>启用</option>
							<option value="1" <c:if test="${requestScope.isLock == 1 }">selected</c:if>>禁用</option>
                        </select>
                    </div>
                    <div class="form-group inlineBlock">
                        <button class="queryBtn btn btn-success" type="button">查询</button>
                    </div>
                </div>
                <div class="tableList col-md-12" style="overflow: auto;">
                    <!--分页部分-->
                    <div id="dtGridContainer_2_1_2" class="dt-grid-container"></div>
                    <div id="dtGridToolBarContainer_2_1_2" class="dt-grid-toolbar-container"></div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- #modal-dialog 模态框 -->
<div class="modal fade" id="modal-dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="closeBtn()">×</button>
            </div>
            <div class="panel panel-inverse">
	         <div class="panel-heading" style="background-color: #707478;">
	             <h4 class="panel-title">重置密码</h4>
	         </div>
	         <div class="panel-body panel-form">
	            <form data-parsley-validate="true" class="form-horizontal form-bordered"  name="editResetSavePasswordForm" id="editResetSavePasswordForm" method="post">
	            	<input type="hidden" name="id" id="id"/>
                    <div class="form-group">
                        <label class="col-md-3 control-label ui-sortable">新密码：</label>
                        <div class="col-md-9 ui-sortable">
                            <input name="newPassword" onkeyup="checkNewPasswordStyle();" data-parsley-required="true" data-parsley-minlength="6" data-parsley-maxlength="125" id="newPassword"  class="form-control" type="password"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label ui-sortable">再次输入新密码：</label>
                        <div class="col-md-9 ui-sortable">
                            <input name="confirmPassword" onkeyup="checkCfmPasswordStyle();" data-parsley-required="true" data-parsley-minlength="6" data-parsley-maxlength="125" data-parsley-equalto="#newPassword" id="confirmPassword"  class="form-control" type="password"/>
                        </div>
                    </div>
					<div class="form-group">
						<label class="control-label col-md-3 ui-sortable"></label>
						<div class="col-md-6 col-sm-6 ui-sortable">
							<a href="javascript:;" class="btn btn-sm btn-white" data-dismiss="modal" id="modalCloseEdit" onclick="closeBtn()">关闭</a>
                			<a href="javascript:;" class="btn btn-sm btn-success" onclick="save()">确定</a>
						</div>
					</div>
	           </form>
	           <form id="commonForm" method="post">
	           		<input type="hidden" name="id" id="editId"/>
	           		<input type="hidden" name="loginName" id="loginName"/>
	            	<input type="hidden" name="roleId" id="roleId"/>
	            	<input type="hidden" name="isOnjob" id="isOnjob"/>
	            	<input type="hidden" name="isLock" id="isLock"/>
	            	<input type="hidden" name="nowPage" id="commonNowPage"/>
	            	<input type="hidden" name="pageSize" id="commonPageSize"/>
	           </form>
	        </div>
	       </div>
        </div>
    </div>
</div>
<script type="text/javascript">
	var nowPage = '${requestScope.nowPage}';
	var pageSize = '${requestScope.pageSize}';
	var loginName = '${requestScope.loginName}';
	var roleId = '${requestScope.roleId}';
	var isOnjob = '${requestScope.isOnjob}';
	var isLock = '${requestScope.isLock}';
</script>
<%-- <script type="text/javascript" src="${base}/resources/assets/js/manager/list.js"/> --%>
<script type="text/javascript">
$(function () {
    App.init();
    $("#dtGridContainer_2_1_2").on("click",".edit",function(){
        var rowinfo = adminList.getOppRowInfo($(this).attr("data-value"));
        console.log(rowinfo);
    });
});
function AdminList() {
    this.init();
}
//重写原型对象
AdminList.prototype = {
    grid: "",
    //初始化页面
    init: function () {
        this.renderDOM();
        $(".queryBtn").click(function(){
            adminList.search();
        });
        
      	//添加回车事件
        $(".query").on("keydown","input",function(e){
        	if(e && e.keyCode==13){ // enter 键
        		adminList.search();
        	}
        });
    },
    //动态渲染DOM
    renderDOM: function () {
        //配置dtgrid
        var dtGrid = function () {
            //初始化dtgrid
            var dtGridColumns_2_1_2 = [
            	{
                    id: 'operation', title: '操作', type: 'string', columnClass: 'text-center',
                    resolution: function (value, record, column, grid, dataNo, columnNo) {
                        var content = '';
                        content += '<a class="add btn btn-xs btn-warning" data-toggle="modal" data-value="'+dataNo+'" onclick="edit('+record.id+')"><i class="fa fa-edit"></i>编辑</a>&nbsp;';
                        content += '&nbsp;<a href="#modal-dialog" class="moreDetail btn btn-xs btn-primary" data-toggle="modal" onclick="dialogBtn('+record.id+')"><i class="fa fa-edit"></i>重置密码</a> ';
                        return content;
                    }
                },
                { id: 'name', title: '姓名', type: 'string', columnClass: 'text-center', resolution:function(value, record, column, grid, dataNo, columnNo){
                	var content = '--';
                	if(value){
            			content = '<span>'+value+'</span>';
            		}
            		return content;
            	}},
            	{ id: 'loginName', title: '登录名', type: 'string', columnClass: 'text-center'},
                { id: 'gender', title: '性别', type: 'string', columnClass: 'text-center', resolution:function(value, record, column, grid, dataNo, columnNo){
            		var content = '--';
            		if(value){
    					if (parseInt(value) == 1) {
    						content = '<span>男</span>';
    					} else if (parseInt(value) == 2) {
    						content = '<span>女</span>';
    					}
    				}
            		return content;
            	}},
                { id: 'isOnjob', title: '是否在职', type: 'string', columnClass: 'text-center', resolution:function(value, record, column, grid, dataNo, columnNo){
            		var content = '--';
                    if (value == 1) {
                        content = '<span>是</span>';
                    } else if(value == 0) {
                        content = '<span>否</span>';
                    }
            		return content;
            	}},
                { id: 'isLock', title: '账号状态', type: 'string', columnClass: 'text-center', resolution:function(value, record, column, grid, dataNo, columnNo){
            		var content = '--';
           			if (value == 1) {
           				content = '<span>禁用</span>';
       				} else {
       					content = '<span>启用</span>';
       				}
            		return content;
            	}},
                { id: 'lastLoginTime', title: '最后登录时间', type: 'string', columnClass: 'text-center', resolution:function(value, record, column, grid, dataNo, columnNo){
                	var content = '--';
            		if(value){
            			/* content += '<span>'+(new Date(parseInt(value))).Format("yyyy-MM-dd hh:mm:ss")+'</span>'; */
            			content = '<span>'+value+'</span>';
            		}
            		return content;
            	}},
                { id: 'creator', title: '创建人', type: 'string', columnClass: 'text-center', resolution:function(value, record, column, grid, dataNo, columnNo){
                	var content = '--';
            		if(value){
            			content = '<span>'+value+'</span>';
            		}
            		return content;
            	}},
                { id: 'createTime', title: '创建时间', type: 'string', columnClass: 'text-center', resolution:function(value, record, column, grid, dataNo, columnNo){
                	var content = '--';
            		if(value){
            			content = '<span>'+value+'</span>';
            		}
            		// 重置隐含数据
            		grid.parameters = new Object();
            		grid.parameters.nowPage = '';
	                grid.parameters.pageSize = '';
	                grid.parameters.loginName = $('input[name="manager.loginName"]').val();
    	     		grid.parameters.roleId = $('select[name="manager.roleId"]').val();
	           	 	grid.parameters.isOnjob = $('select[name="manager.isOnjob"]').val();
	           	 	grid.parameters.isLock = $('select[name="manager.isLock"]').val();
            		return content;
            	}}
            ];
            var dtGridOption_2_1_2 = {
                lang: 'zh-cn',
                ajaxLoad: true,
                loadURL: baseUrl + '/admin/manager/list4ajax',
                exportFileName: '账号列表',
                columns: dtGridColumns_2_1_2,
                gridContainer: 'dtGridContainer_2_1_2',
                toolbarContainer: 'dtGridToolBarContainer_2_1_2',
                tools: '',
                pageSize: 10,
                pageSizeLimit: [10, 15, 50]
            };
            grid = $.fn.DtGrid.init(dtGridOption_2_1_2);
            grid.parameters = new Object();
            grid.parameters.nowPage = nowPage;
            grid.parameters.pageSize = pageSize;
            grid.parameters.loginName = loginName;
     		grid.parameters.roleId = roleId;
       	 	grid.parameters.isOnjob = isOnjob;
       	 	grid.parameters.isLock = isLock;
            grid.load();
        };
        dtGrid();
    },
    search: function() {
        grid.parameters = new Object();
        grid.parameters.loginName = $('input[name="manager.loginName"]').val();
   	 	grid.parameters.roleId = $('select[name="manager.roleId"]').val();
   		grid.parameters.isOnjob = $('select[name="manager.isOnjob"]').val();
   		grid.parameters.isLock = $('select[name="manager.isLock"]').val();
        grid.refresh(true);
    },
    getOppRowInfo: function(num) {
        var data = grid.exhibitDatas;
        return data[num];
    }
};
//执行构造函数
var adminList = new AdminList();

function add(){
	window.location="${base}/admin/manager/onAdd";
}
function edit(id){
	var actionUrl="${base}/admin/manager/onEdit";
	$("#editId").val(id);
	$("#loginName").val($("input[name='manager.loginName']").val());
	$("#roleId").val($("select[name='manager.roleId']").val());
	$("#isOnjob").val($("select[name='manager.isOnjob']").val());
	$("#isLock").val($("select[name='manager.isLock']").val());
	$("#commonNowPage").val(grid.pager.nowPage);
	$("#commonPageSize").val(grid.pager.pageSize);
	$("#commonForm").attr("action",actionUrl);
	$("#commonForm").submit();
}

function dialogBtn(id){
	$("#id").val(id);
}

function save(){
	var actionUrl="${base}/admin/manager/resetpw";
   	var id = $("#id").val();
   	var newPassword=$("input[name='newPassword']").val();
   	var cfmpwd = document.getElementById("confirmPassword").value;
   	$("input[name='newPassword']").attr("isSaved","true");
   	$("input[name='confirmPassword']").attr("isSaved","true");
   	if(newPassword==null||newPassword.length<6){
   		$("input[name='newPassword']").removeClass("parsley-error");
   		$("#parsley-id-9999").remove();
   		var str ="请输入必要信息";
   		if(newPassword.length<6) str = "长度最小为【6】位";
   		if(newPassword==null) str ="请输入必要信息";
   		$("input[name='newPassword']").addClass("parsley-error");
   		$("input[name='newPassword']").after('<ul class="parsley-errors-list filled" id="parsley-id-9999"><li class="parsley-required">'+str+'</li></ul>');
	}else{
		$("input[name='newPassword']").removeClass("parsley-error");
   		$("#parsley-id-9999").remove();
   		$("input[name='newPassword']").addClass("parsley-success");
	}
   	if(newPassword==null||newPassword.length<6||cfmpwd==null||cfmpwd.length<6){
   		$("input[name='confirmPassword']").removeClass("parsley-error");
   		$("#parsley-id-8888").remove();
   		if(cfmpwd==null||cfmpwd.length<6){
   			var str ="请输入必要信息";
       		if(cfmpwd.length<6) str = "长度最小为【6】位";
       		if(cfmpwd==null) str ="请输入必要信息";
       		$("input[name='confirmPassword']").addClass("parsley-error");
       		$("input[name='confirmPassword']").after('<ul class="parsley-errors-list filled" id="parsley-id-8888"><li class="parsley-required">'+str+'</li></ul>');
   		}
		return false;
	}else{
		$("input[name='confirmPassword']").removeClass("parsley-error");
   		$("#parsley-id-8888").remove();
   		$("input[name='confirmPassword']").addClass("parsley-success");
	}
   	if(newPassword!=cfmpwd){
   		$("input[name='newPassword']").removeClass("parsley-error");
   		$("#parsley-id-9999").remove();
   		$("input[name='confirmPassword']").removeClass("parsley-error");
   		$("#parsley-id-8888").remove();
   		$("input[name='newPassword']").addClass("parsley-success");
   		$("input[name='confirmPassword']").removeClass("parsley-success");
   		$("input[name='confirmPassword']").addClass("parsley-error");
   		$("input[name='confirmPassword']").after('<ul class="parsley-errors-list filled" id="parsley-id-8888"><li class="parsley-required">两次输入不一致</li></ul>');
		return false;
	}else{
		$("input[name='newPassword']").removeClass("parsley-error");
   		$("#parsley-id-9999").remove();
   		$("input[name='confirmPassword']").removeClass("parsley-error");
   		$("#parsley-id-8888").remove();
	}
   	$.ajax({
   		url:actionUrl,
   		data:{"id":id,"newPassword":newPassword},
   		success:function(data){
   			if(data>0){
   				$("#modalCloseEdit").click();
   				alert("修改成功!");
   			}else{
   				alert("修改失败!");
   			}
   		}
   	});
}

function checkNewPasswordStyle(){
	var newPassword=$("input[name='newPassword']").val();
	var cfmpwd = $("input[name='confirmPassword']").val();
	if($("input[name='newPassword']").attr("isSaved")){
		if(newPassword!=null&&newPassword.length>=6){
			
			if(newPassword!=cfmpwd){
				$("input[name='newPassword']").removeClass("parsley-success");
       			$("input[name='newPassword']").removeClass("parsley-error");
       	   		$("#parsley-id-9999").remove();
           	   	var str ="请输入必要信息";
           		if(newPassword.length<6) str = "长度最小为【6】位";
           		if(newPassword==null) str ="请输入必要信息";
           		$("input[name='newPassword']").addClass("parsley-error");
           		if(newPassword.length<6)
           		$("input[name='newPassword']").after('<ul class="parsley-errors-list filled" id="parsley-id-9999"><li class="parsley-required">'+str+'</li></ul>');
           		
           		$("input[name='confirmPassword']").removeClass("parsley-success");
       			$("input[name='confirmPassword']").removeClass("parsley-error");
       	   		$("#parsley-id-8888").remove();
           		$("input[name='confirmPassword']").addClass("parsley-error");
           		$("input[name='confirmPassword']").after('<ul class="parsley-errors-list filled" id="parsley-id-8888"><li class="parsley-required">两次输入不一致</li></ul>');
			}
			$("input[name='newPassword']").removeClass("parsley-error");
      	   	$("#parsley-id-9999").remove();
      	   	$("input[name='newPassword']").addClass("parsley-success");
   		}else{
   			$("input[name='newPassword']").removeClass("parsley-success");
   			$("input[name='newPassword']").removeClass("parsley-error");
   	   		$("#parsley-id-9999").remove();
   			var str ="请输入必要信息";
       		if(newPassword.length<6) str = "长度最小为【6】位";
       		if(newPassword==null) str ="请输入必要信息";
       		$("input[name='newPassword']").addClass("parsley-error");
       		$("input[name='newPassword']").after('<ul class="parsley-errors-list filled" id="parsley-id-9999"><li class="parsley-required">'+str+'</li></ul>');
   		}
	}
}

function checkCfmPasswordStyle(){
	var cfmpwd = $("input[name='confirmPassword']").val();
	var newPassword=$("input[name='newPassword']").val();
	if($("input[name='confirmPassword']").attr("isSaved")){
		if(cfmpwd!=null&&cfmpwd.length>=6){
			if(newPassword!=cfmpwd){
				$("input[name='confirmPassword']").removeClass("parsley-success");
       			$("input[name='confirmPassword']").removeClass("parsley-error");
       	   		$("#parsley-id-8888").remove();
           		$("input[name='confirmPassword']").addClass("parsley-error");
           		$("input[name='confirmPassword']").after('<ul class="parsley-errors-list filled" id="parsley-id-8888"><li class="parsley-required">两次输入不一致</li></ul>');
			}else{
				$("input[name='confirmPassword']").removeClass("parsley-error");
       	   		$("#parsley-id-8888").remove();
       	   		$("input[name='confirmPassword']").addClass("parsley-success");
			}
   		}else{
   			$("input[name='confirmPassword']").removeClass("parsley-success");
   			$("input[name='confirmPassword']").removeClass("parsley-error");
   	   		$("#parsley-id-8888").remove();
   			var str ="请输入必要信息";
       		if(cfmpwd.length<6) str = "长度最小为【6】位";
       		if(cfmpwd==null) str ="请输入必要信息";
       		$("input[name='confirmPassword']").addClass("parsley-error");
       		$("input[name='confirmPassword']").after('<ul class="parsley-errors-list filled" id="parsley-id-8888"><li class="parsley-required">'+str+'</li></ul>');
   		}
	}
}

function closeBtn(){
	$("input[name='newPassword']").removeClass("parsley-error");
	$("#parsley-id-9999").remove();
	$("input[name='newPassword']").removeClass("parsley-success");
	$("input[name='confirmPassword']").removeClass("parsley-error");
	$("#parsley-id-8888").remove();
	$("input[name='confirmPassword']").removeClass("parsley-success");
	
	$("#id").val("");
	$("input[name='newPassword']").val("");
	$("input[name='confirmPassword']").val("");
	
	$("input[name='newPassword']").removeAttr("isSaved");
   	$("input[name='confirmPassword']").removeAttr("isSaved");
}
</script>

<!-- ================== BEGIN PAGE LEVEL JS ================ -->
<script src="${base }/resources/common/script/parsley/dist/parsley.js"></script>
<!-- ================== END PAGE LEVEL JS ================== -->
</body>
</html>
