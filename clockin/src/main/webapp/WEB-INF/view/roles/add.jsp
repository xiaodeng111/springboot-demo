<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>角色添加</title>
<%@ include file="/jsp/common/meta.jsp"%>
<link rel="stylesheet" href="${base}/resources/common/plugins/ztree/css/demo.css" type="text/css"/>
<link rel="stylesheet" href="${base}/resources/common/plugins/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
<script type="text/javascript" src="${base}/resources/common/plugins/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${base}/resources/common/plugins/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<script language="javascript">
var permissionData = ${requestScope.json};

function checkForm(){
    $("#Button1").attr("disabled",true);
	var zTree = $.fn.zTree.getZTreeObj("resourceTree");
	var permissionsArr = zTree.getCheckedNodes(true);
	var permissions = '';
	for(var i = 0 ; i < permissionsArr.length ; i ++){
		permissions+= permissionsArr[i].object.nameEn;
		if(i<permissionsArr.length)
			permissions+=';';
	}
	$("input.permissions").val(permissions);
	return true;
}

var setting = {
	check: {
		enable: true,
		chkDisabledInherit: true
	},
	data: {
		simpleData: {
			enable: true
		}
	},
	callback: {
		onCheck: onCheck
	}
};
function onCheck(event, treeId, treeNode) {
	if(treeNode.checked){
		var parent = treeNode.getParentNode();
		if(parent){
			var children = parent.children;
			var view ;
			for(i = 0 ; i < children.length; i ++)
				if(children[i].object.actions == 1){
					view = children[i];
					break;
				}
			view.checked = true;
			var zTree = $.fn.zTree.getZTreeObj("resourceTree");
			zTree.updateNode(view);	
		}	
	}
}
function createTreeData(){
	var data = [];
	for(var i = 0 ; i < permissionData.length ; i ++){
		data.push({ id: permissionData[i].id,
					pId: permissionData[i].parentId, 
					name: permissionData[i].nameZh,
					object:permissionData[i] });
	}
	return data;
}
function cancleClick(){
	self.location='rolesAction.${actionExt}';    
}
$(window).load(function() {
	$("input.role_status").each(function(){
		if(this.value == 1)
			this.checked = true;
		});
	$.fn.zTree.init($("#resourceTree"), setting, createTreeData());
  });
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
	             <h4 class="panel-title">角色添加</h4>
	         </div>
	         <div class="panel-body panel-form">
	            <form data-parsley-validate="true" class="form-horizontal form-bordered"  name="rolesForm" id="rolesForm" method="post" action="${base }/admin/roles/add" onsubmit="return checkForm();" data-validate="parsley">
                    <div class="form-group">
                        <label class="col-md-3 control-label ui-sortable">名称：</label>
                        <div class="col-md-9 ui-sortable">
                            <input name="name" maxlength="60" data-parsley-required="true" type="text" id="name"  class="form-control" type="text"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label ui-sortable">状态：</label>
                        <div class="col-md-9 ui-sortable">
                            <label class="radio-inline">
                                <input name="roleStatus" value="1" checked="checked" type="radio"/>启用
                            </label>
                            <label class="radio-inline">
                                <input name="roleStatus" value="2" type="radio"/>禁用
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label ui-sortable">权限：</label>
                        <div class="col-md-9 ui-sortable">
                        	<input type='hidden' name='permissions' id ='permissions' class='permissions' />
							<ul id="resourceTree" class="ztree " style="padding:0px;"></ul>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label ui-sortable">描述：</label>
                        <div class="col-md-9 ui-sortable">
                            <textarea class="form-control" data-parsley-maxlength="125" name="description" id="description" placeholder="描述" rows="5"></textarea>
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