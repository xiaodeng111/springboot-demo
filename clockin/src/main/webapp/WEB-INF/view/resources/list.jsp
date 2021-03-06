<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>${systemName}-资源管理</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport"/>
    <%@ include file="/jsp/common/meta.jsp"%>
    <!--dtgrid资源包引入-->
    <link rel="stylesheet" href="${base}/resources/common/plugins/DTgrid/jquery.dtGrid.min.css">
    <link rel="stylesheet" href="${base}/resources/assets/css/demo.css">
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
                <h4>资源管理管理</h4>
            </div>
        </div>
        <!--数据列表部分-->
        <div class="row">
            <div class="lists">
              <div class="buttons col-md-2 col-sm-12 col-xs-12">
                    <button class="btn btn-info" type="button" onclick="javascript:itemclick('add');">
                        <i class="fa fa-plus"></i>
                        <span>新增</span>
                    </button>
                </div>
                <div class="query col-md-10 col-sm-12 col-xs-12 text-right">
                  <div class="form-group inlineBlock">
                  		<label>名称</label>
	                    <input id="searchNameZh" type="text" class="formList" name="searchNameZh" placeholder="请输入名称" value="${requestScope.searchNameZh}"/>
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
        <form id="commonForm" method="post">
          <input type="hidden" name="id" id="editId"/>
          <input type="hidden" name="searchNameZh" id="searchNameZh"/>
          <input type="hidden" name="nowPage" id="commonNowPage"/>
          <input type="hidden" name="pageSize" id="commonPageSize"/>
       </form>
    </div>
</div>
<script type="text/javascript">
  var nowPage   = '${requestScope.nowPage}';
  var pageSize  = '${requestScope.pageSize}';
  var searchNameZh = '${requestScope.searchNameZh}';
</script>
<script>
  var grid;
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
    var typeListData = [{id:1,text:"菜单"},{id:2,text:"目录"},{id:3,text:"模块"}];
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
                            content += '<a class="edit btn btn-xs btn-warning" data-toggle="modal" data-value="'+dataNo+'" onclick="javascript:itemclick(\'edit\','+record.id+');"><i class="fa fa-edit"></i>  编辑</a>';
                            content += '&nbsp;<a class="edit btn btn-xs btn-danger" data-toggle="modal" data-value="'+dataNo+'" onclick="javascript:itemclick(\'delete\','+record.id+');"><i class="fa fa-edit"></i>  删除</a>';
                            return content;
                        }
                    },
                    { id: 'parentName', title: '上级资源', type: 'string', columnClass: 'text-center' },
                    { id: 'nameZh', title: '名称', type: 'string', columnClass: 'text-center' },
                    { id: 'resourceTypeName', title: '类型', type: 'string', columnClass: 'text-center', resolution:function(value, record, column, grid, dataNo, columnNo){
                    	var content = '';
                    	content += '<span>--</span>';
                    	for(var i = 0 ; i < typeListData.length; i ++){
                        	if (parseInt(record.resourceType) == typeListData[i].id){
                        		return content = '<span>'+typeListData[i].text+'</span>';
                        	}
                        }
                		return content;
                	}},
                	{ id: 'actionsStr', title: '动作', type: 'string', columnClass: 'text-center' },
                	{ id: 'defaultUri', title: '访问路径', type: 'string', columnClass: 'text-center' },
                	{ id: 'resourceStatus', title: '状态', type: 'string', columnClass: 'text-center', resolution:function(value, record, column, grid, dataNo, columnNo){
                		var content = '';
                		if(value=="1"){
                			content += '<span>启用</span>';
                		}else{
                			content += '<span>禁用</span>';
                		}
                		// 重置隐含数据
                		grid.parameters = new Object();
                		grid.parameters.nowPage = '';
    	                grid.parameters.pageSize = '';
    	                grid.parameters.searchNameZh = $('input[name="searchNameZh"]').val();
                		return content;
                	}},
                	{ id: 'creator', title: '创建人', type: 'string', columnClass: 'text-center' },
                	{ id: 'createDate', title: '创建时间', type: 'string', columnClass: 'text-center' }
                ];
                var dtGridOption_2_1_2 = {
                    lang: 'zh-cn',
                    ajaxLoad: true,
                    loadURL: baseUrl + '/admin/resources/list4ajax',
                    columns: dtGridColumns_2_1_2,
                    gridContainer: 'dtGridContainer_2_1_2',
                    toolbarContainer: 'dtGridToolBarContainer_2_1_2',
                    tools: '',
                    pageSize: 15,
                    pageSizeLimit: [15, 25, 50]
                };
                grid = $.fn.DtGrid.init(dtGridOption_2_1_2);
                grid.parameters = new Object();
                grid.parameters.searchNameZh = searchNameZh;
                grid.parameters.nowPage = nowPage;
                grid.parameters.pageSize = pageSize;
                grid.load();
            };
            dtGrid();
        },
        search: function() {
            grid.parameters = new Object();
            grid.parameters.searchNameZh = $('input[name="searchNameZh"]').val();
            grid.refresh(true);
        },
        getOppRowInfo: function(num) {
            var data = grid.exhibitDatas;
            return data[num];
        }
    };
    //执行构造函数
    var adminList = new AdminList();
    
    function itemclick(type,id){
    	if(type=='add'){
    		window.location = baseUrl + "/admin/resources/onAdd";
    	}
    	if(type=='edit'){
    		var actionUrl = baseUrl + "/admin/resources/onEdit";
    		$("#editId").val(id);
    		$("#searchNameZh").val($("input[name='searchNameZh']").val());
        	$("#commonNowPage").val(grid.pager.nowPage);
        	$("#commonPageSize").val(grid.pager.pageSize);
        	$("#commonForm").attr("action",actionUrl);
        	$("#commonForm").submit();
    	}
    	if(type=='delete'){
    		if(confirm("确定要删除？")){
    			var actionUrl = baseUrl + "/admin/resources/doDel";
        		$("#editId").val(id);
        		$("#searchNameZh").val($("input[name='searchNameZh']").val());
            	$("#commonNowPage").val(grid.pager.nowPage);
            	$("#commonPageSize").val(grid.pager.pageSize);
            	$("#commonForm").attr("action",actionUrl);
            	$("#commonForm").submit();
    		}
    	}
    }
    
</script>
<!-- ================== BEGIN PAGE LEVEL JS ================ -->
<script src="${base}/resources/common/script/parsley/dist/parsley.js"></script>
<!-- ================== END PAGE LEVEL JS ================== -->
</body>
</html>