<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>${systemName}-资源动作</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport"/>
    <%@ include file="/jsp/common/meta.jsp"%>
    <script language="javascript">
        function checkForm(){
            return true;
        }
        $(window).load(function() {
        	$("input.resources_action_status").each(function(){
        		if(this.value == '1')
        			this.checked = true;
        		});
        });
        function cancleClick(){
            self.location = baseUrl + '/admin/resourceAction/resourceActionAction';    
        }
    </script>
    <style type="text/css">
        body{ font-size:12px;}
            .l-table-edit {}
            .l-table-edit-td{ padding:4px;}
            .l-button-submit,.l-button-reset{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
            .l-verify-tip{ left:230px; top:120px;}
    </style>
</head>
<body>
    <div class="col-md-6 ui-sortable">
	    <!-- begin panel -->
	    <div class="panel panel-inverse">
	         <div class="panel-heading">
	             <h4 class="panel-title">资源动作添加</h4>
	         </div>
	         <div class="panel-body panel-form">
	              <form data-parsley-validate="true" name="resourceActionForm" id="resourceActionForm" method="post" action="${base}/admin/resourceAction/add" onsubmit="return checkForm();" class="form-horizontal form-bordered" data-validate="parsley">
	              	<div class="form-group">
                        <label class="col-md-3 control-label ui-sortable">名称：</label>
                        <div class="col-md-9 ui-sortable">
                            <input name="nameZh" maxlength="32" data-parsley-required="true" type="text" id="nameZh"  class="form-control" type="text"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label ui-sortable">英文名称：</label>
                        <div class="col-md-9 ui-sortable">
                            <input name="nameEn" maxlength="32" data-parsley-required="true" type="text" id="nameEn"  class="form-control" type="text"/>
                        </div>
                    </div>
					<div class="form-group">
                        <label class="col-md-3 control-label ui-sortable">状态：</label>
                        <div class="col-md-9 ui-sortable">
                            <label class="radio-inline">
                                <input name="actionStatus" value="1" checked="checked" type="radio"/>启用
                            </label>
                            <label class="radio-inline">
                                <input name="actionStatus" value="2" type="radio"/>禁用
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label ui-sortable">描述：</label>
                        <div class="col-md-9 ui-sortable">
                            <textarea class="form-control" data-parsley-length="[0,255]"  name="description" id="description" placeholder="描述" rows="5"></textarea>
                        </div>
                    </div>
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 ui-sortable"></label>
						<div class="col-md-6 col-sm-6 ui-sortable">
							<input type="submit" value="保存" id="Button1" class="btn btn-primary" /> 
							&nbsp;&nbsp;
							<input type="button" value="取消" class="btn btn-primary" onclick="javascript:cancleClick();"/>
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