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
                <h4>角色列表</h4>
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
                        <label>名称</label>
                        <input id="roles.name" type="text" class="formList" name="roles.name" placeholder="请输入名称" value="${requestScope.name }"/>
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
    <form id="commonForm" method="post">
   		<input type="hidden" name="id" id="editId"/>
   		<input type="hidden" name="name" id="name"/>
   		<input type="hidden" name="nowPage" id="commonNowPage"/>
        <input type="hidden" name="pageSize" id="commonPageSize"/>
    </form>
</div>
<script type="text/javascript">
	var nowPage = '${requestScope.nowPage}';
	var pageSize = '${requestScope.pageSize}';
	var name = '${requestScope.name}';
</script>
<script src="${base }/resources/assets/js/roles/list.js"/>

<!-- ================== BEGIN PAGE LEVEL JS ================ -->
<script src="${base }/resources/common/script/parsley/dist/parsley.js"></script>
<!-- ================== END PAGE LEVEL JS ================== -->
</body>
</html>