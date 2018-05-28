<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>${systemName}-资源管理</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport"/>
    <%@ include file="/jsp/common/meta.jsp"%>
    <script language="javascript">
        function checkForm(){
            return true;
        }
        function cancleClick(){
            self.location = baseUrl + '/admin/resources/onList';    
        }
    </script>
    <script language="javascript">
	var resultListData = ${requestScope.resjson};
	$(function(){
		var resourcesParent = $("select[name='parentId']");
		var $menu = $("#resourceMenu");
		var $catalog = $("#parentId");
		
		for(var i = 0 ; i <  resultListData.length;i++){
			var type = resultListData[i].resourceType;
			if(type == 1){
				$menu.append("<option value='" + resultListData[i].id + "'>" + resultListData[i].nameZh + "</option>");
			}
		}
	});
	function checkForm(){
		var actionsArray = $("#resourcesActions").val();
		if(actionsArray){
			var actions_bit = 0;
			for(var j = 0 ; j < actionsArray.length ; j++){
				actions_bit += parseInt(actionsArray[j]);
			}
			$("input[name='actions']").val(actions_bit);
		}else{
			$("input[name='actions']").val(0);
		}
		return true;
	}
	function resourceTypeSel(item){
		$("select[name='resource_type_sel']").val('');
		$("select[name='parentId']").val('');
		var $catalog = $("#parentId");
		$catalog.empty();
		$catalog.append("<option value=''>--请选择--</option>");
		if(item.value == 3){
			$("#resource_type_sel_div").css("display","block");
			$("#resource_actions_sel_div").css("display","block");
			$("#resource_parent_sel_div").css("display","block");
			$("select[name='resource_type_sel']").attr("data-parsley-required",true);
			$("select[name='parentId']").attr("data-parsley-required",true);
			$("#resourcesActions").attr("data-parsley-required",true);
		}else if(item.value==2){
			$("#resource_parent_sel_div").css("display","block");
			$("#resource_type_sel_div").css("display","none");
			$("#resource_actions_sel_div").css("display","none");
			
			$("select[name='resource_type_sel']").removeAttr("data-parsley-required");
			$("select[name='parentId']").attr("data-parsley-required",true);
			$("#resourcesActions").removeAttr("data-parsley-required");
			
			for(var i = 0 ; i <  resultListData.length;i++){
				var type = resultListData[i].resourceType;
				if(type == 1){
					$catalog.append("<option value='" + resultListData[i].id + "'>" + resultListData[i].nameZh + "</option>");
				}
			}
		}else if(item.value==1){
			$("#resource_parent_sel_div").css("display","none");
			$("#resource_type_sel_div").css("display","none");
			$("#resource_actions_sel_div").css("display","none");
			$("select[name='resource_type_sel']").removeAttr("data-parsley-required");
			$("select[name='parentId']").removeAttr("data-parsley-required");
			$("#resourcesActions").removeAttr("data-parsley-required");
		}else{
			$("#resource_parent_sel_div").css("display","none");
			$("#resource_type_sel_div").css("display","none");
			$("#resource_actions_sel_div").css("display","none");
			$("select[name='resource_type_sel']").removeAttr("data-parsley-required");
			$("select[name='parentId']").removeAttr("data-parsley-required");
			$("#resourcesActions").removeAttr("data-parsley-required");
		}
	}
	function changeMenu(){
		var $menu = $("#resourceMenu");
		var $catalog = $("#parentId");
		$catalog.empty();
		$catalog.append("<option value=''>--请选择--</option>");
		for(var i = 0 ; i <  resultListData.length;i++){
			var type = resultListData[i].resourceType;
			if($menu.val() == resultListData[i].parentId){
				$catalog.append("<option value='" + resultListData[i].id + "'>" + resultListData[i].nameZh + "</option>");
			}
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
	             <h4 class="panel-title">资源添加</h4>
	         </div>
	         <div class="panel-body panel-form">
	            <form data-parsley-validate="true" class="form-horizontal form-bordered"  name="managerForm" id="managerForm" method="post" action="${base }/admin/resources/add" onsubmit="return checkForm();" data-validate="parsley">
	              	<div class="form-group">
                        <label class="col-md-3 control-label ui-sortable">类型：</label>
                        <div class="col-md-9 ui-sortable">
                            <select name="resourceType" data-parsley-required="true" id="resourceType" onchange="resourceTypeSel(this)" class="form-control">
                            		<option value="">--请选择--</option>
						        	<option value="1">菜单</option>
						        	<option value="2">目录</option>
						        	<option value="3">模块</option>
							</select>
                        </div>
                    </div>
                    <div class="form-group" id="resource_type_sel_div" style="display: none;">
                        <label class="col-md-3 control-label ui-sortable">菜单：</label>
                        <div class="col-md-9 ui-sortable">
                            <select name="resource_type_sel" id="resourceMenu" class="form-control" onchange="changeMenu()">
                            	<option value="">--请选择--</option>
							</select>
                        </div>
                    </div>
                    <div class="form-group"  id="resource_parent_sel_div" style="display: none;">
                        <label class="col-md-3 control-label ui-sortable">上级资源：</label>
                        <div class="col-md-9 ui-sortable">
                            <select name="parentId" id="parentId"  class="form-control">
                            		<option value="">--请选择--</option>
							</select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label ui-sortable">英文名称：</label>
                        <div class="col-md-9 ui-sortable">
                            <input name="nameEn" maxlength="60" data-parsley-required="true" type="text" id="nameEn"  class="form-control" type="text"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label ui-sortable">中文名称：</label>
                        <div class="col-md-9 ui-sortable">
                            <input name="nameZh" maxlength="60" data-parsley-required="true" type="text" id="nameZh"  class="form-control" type="text"/>
                        </div>
                    </div>
                    <div class="form-group" id="resource_actions_sel_div">
                        <label class="col-md-3 control-label ui-sortable">动作：</label>
                        <div class="col-md-9 ui-sortable">
                        	<input type="hidden" name="actions" class="resource_actions" />
                            <select id="resourcesActions" multiple class="form-control">
                            	<c:forEach items="${requestScope.actionjson}" var="resourceAction" varStatus="vstatus">
                            		<option value="${resourceAction.bit }">${resourceAction.nameZh }</option>
                            	</c:forEach>
							</select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label ui-sortable">访问路径：</label>
                        <div class="col-md-9 ui-sortable">
                            <input name="defaultUri" maxlength="255" data-parsley-required="true" type="text" id="defaultUri"  class="form-control" type="text"/>
                        </div>
                    </div>
                    <div class="form-group gender">
						<label class="col-md-3 control-label ui-sortable">状态：</label>
						<div class="col-md-9 ui-sortable">
							<label class="radio-inline">
                                <input type="radio" name="resourceStatus" value="1" checked />启用
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="resourceStatus" value="2" />禁用
                            </label>
                        </div>
					</div>
					<div class="form-group">
                        <label class="col-md-3 control-label ui-sortable">排序：</label>
                        <div class="col-md-9 ui-sortable">
                            <input name="resourceOrder" maxlength="11" data-parsley-required="true" type="text" id="resourceOrder"  class="form-control" type="text"/>
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
    <!-- ================== BEGIN PAGE LEVEL JS ================== -->
    <script src="${base}/resources/common/script/parsley/dist/parsley.js"></script>
    <!-- ================== END PAGE LEVEL JS ================== -->
</body>
</html>