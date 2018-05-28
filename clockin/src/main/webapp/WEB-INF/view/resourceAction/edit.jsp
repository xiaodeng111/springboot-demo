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
</head>
<body>
    <div class="col-md-6 ui-sortable">
	    <!-- begin panel -->
	    <div class="panel panel-inverse">
	         <div class="panel-heading">
	             <h4 class="panel-title">资源动作修改</h4>
	         </div>
	         <div class="panel-body panel-form">
	              <form data-parsley-validate="true" name="resourceActionForm" id="resourceActionForm" method="post" action="${base}/admin/resourceAction/edit" onsubmit="return checkForm();" class="form-horizontal form-bordered" data-validate="parsley">
	              	<input type="hidden" id="id" name="id" value="${requestScope.resourceAction.id}"/>
    				<input type="hidden" name="bit" id="bit" value="${requestScope.resourceAction.bit}"/>
    				<%-- <input type="hidden" name="nameZh" id="nameZh" value="${ requestScope.nameZh}"/> --%>
	            	<input type="hidden" name="nowPage" id="commonNowPage" value="${ requestScope.nowPage}"/>
	            	<input type="hidden" name="pageSize" id="commonPageSize" value="${ requestScope.pageSize}"/>
	              	<div class="form-group">
                        <label class="col-md-3 control-label ui-sortable">名称：</label>
                        <div class="col-md-9 ui-sortable">
                            <input name="nameZh" maxlength="32" data-parsley-required="true" type="text" id="nameZh"  class="form-control" type="text" value="${requestScope.resourceAction.nameZh}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label ui-sortable">英文名称：</label>
                        <div class="col-md-9 ui-sortable">
                            <input name="nameEn" maxlength="32" data-parsley-required="true" type="text" id="nameEn"  class="form-control" type="text" value="${requestScope.resourceAction.nameEn}"/>
                        </div>
                    </div>
					<div class="form-group">
                        <label class="col-md-3 control-label ui-sortable">状态：</label>
                        <div class="col-md-9 ui-sortable">
                            <label class="radio-inline">
                                <input name="actionStatus" value="1" type="radio" <c:if test="${requestScope.resourceAction.actionStatus ==1 }">checked</c:if>/>启用
                            </label>
                            <label class="radio-inline">
                                <input name="actionStatus" value="2" type="radio" <c:if test="${requestScope.resourceAction.actionStatus ==2 }">checked</c:if>/>禁用
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label ui-sortable">描述：</label>
                        <div class="col-md-9 ui-sortable">
                            <textarea class="form-control" data-parsley-length="[0,255]" name="description" id="description" placeholder="描述" rows="5">${requestScope.resourceAction.description}</textarea>
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
    <!-- ================== BEGIN PAGE LEVEL JS ================== jiaoyan -->
    <script src="${base}/resources/common/script/parsley/dist/parsley.js"></script>
    <!-- ================== END PAGE LEVEL JS ================== -->
</body>
</html>