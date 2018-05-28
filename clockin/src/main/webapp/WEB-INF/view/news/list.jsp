<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>${systemName}-发现</title>
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
                <h4>${requestScope.title}</h4>
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
                      <label  class="m-l-30">状态:</label>
                      <select id="news.status" name="news.status" class="formList width-150">
                          <option value="">请选择</option>
                          <option value="0" <c:if test="${0 == requestScope.status}"> selected</c:if>>已下架</option>
                          <option value="1" <c:if test="${1 == requestScope.status}"> selected</c:if>>已上架</option>
                      </select>
                      <label class="m-l-30">标题:</label>
                      <input id="news.name" type="text" class="formList" name="news.name" placeholder="请输入标题" value="${requestScope.name }"/>
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
                <div class="form-group inlineBlock col-xs-12">
                    <button class="selectAllBtn btn btn-success m-r-15 btn-font" type="button" onclick="selectAll()">全选</button>
                    <button class="deselectAllBtn btn btn-success m-r-15 btn-font" type="button" onclick="deselectAll()">取消全选</button>
                    <button class="btn btn-success m-r-15 btn-font" type="button" onclick="changeAll(1)">批量上架</button>
                    <button class="btn btn-success m-r-15 btn-font" type="button" onclick="changeAll(0)">批量下架</button>
                    <button class="btn btn-success btn-font" type="button" onclick="delAll()">批量删除</button>
                </div>
            </div>
        </div>
        <form id="commonForm" method="post">
          <input type="hidden" name="id" id="editId"/>
          <input type="hidden" name="name" id="name"/>
          <input type="hidden" name="status" id="status"/>
          <input type="hidden" name="nowPage" id="commonNowPage"/>
          <input type="hidden" name="pageSize" id="commonPageSize"/>
       </form>
    </div>
</div>
<!--通用询问模版-->
<div class="modal fade deleteModal" id="common-confirm" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title text-center" id="confirmHeader">提示</h4>
            </div>
            <div class="modal-body m-t-20">
                <h5 class="modal-title text-center" id="confirmContent">确定吗?</h5>
            </div>
            <div class="modal-footer no-border text-center">
                <button type="button" id="confirmBtn_cancel" class="btn btn-default m-r-15" >
                    取消
                </button>
                <button type="button" id="confirmBtn_yes" class="btn btn-success m-l-15" >确定
                </button>
            </div>
        </div>
    </div>
</div>
<!--通用询问模版 end-->
<script src="${base}/resources/common/script/common.js"></script>
<script type="text/javascript">
  var nowPage   = '${requestScope.nowPage}';
  var pageSize  = '${requestScope.pageSize}';
  var name = '${requestScope.name }';
  var status = '${requestScope.status }';
  var type ='${requestScope.type}';
</script>
<script>
    var grid;
    $(function () {
        App.init();
        $("#dtGridContainer_2_1_2").on("click",".edit",function(){
            var rowinfo = adminList.getOppRowInfo($(this).attr("data-value"));
            // console.log(rowinfo);
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
                if(type != 2){
                    //初始化dtgrid
                    var dtGridColumns_2_1_2 = [
                        { id: 'name', title: '标题', type: 'string', columnClass: 'text-center' }
                        ,{ id: 'imgeUrl', title: '图片', type: 'string', columnClass: 'text-center' ,
                            resolution: function (value, record, column, grid, dataNo, columnNo) {
                                var html ='';
                                if(value !=""){
                                    if(record.type ==1){
                                        html = "<img src='"+fileRootUrl+value+"' width='60'  height='60'>";
                                    }else{
                                        html = "<img src='"+fileRootUrl+value+"' width='40'  height='30'>";
                                    }

                                }
                                return html;
                            }
                        }
                        ,{ id: 'jumpUrl', title: '跳转链接', type: 'string', columnClass: 'text-center',
                            resolution: function (value, record, column, grid, dataNo, columnNo) {
                                var tmp = value;
                                var tmpstr = tmp.replace(/<[^>]+>/g, '');
                                if(tmpstr.length > 10){
                                    var html='<span class="more-text" title="'+ value +'">'+(tmpstr + "").substring(0, 10) + '...'+'</span>';
                                }else{
                                    var html='<span class="more-text">'+value+'</span>';
                                }
                                return html
                            }
                        }
                        ,{ id: 'descIntroduction', title: '副标题', type: 'string', columnClass: 'text-center',
                            resolution: function (value, record, column, grid, dataNo, columnNo) {
                                var tmp = value;
                                var tmpstr = tmp.replace(/<[^>]+>/g, '');
                                if(tmpstr.length > 10){
                                    var html='<span class="more-text" title="'+ value +'">'+(tmpstr + "").substring(0, 10) + '...'+'</span>';
                                }else{
                                    var html='<span class="more-text">'+value+'</span>';
                                }
                                return html
                            }
                        }
                        ,{ id: 'oderNum', title: '顺序号', type: 'string', columnClass: 'text-center' }
                        ,{ id: 'status', title: '状态', type: 'string', columnClass: 'text-center',
                            resolution: function (value, record, column, grid, dataNo, columnNo) {
                                var content = '';
                                if(value==0){
                                    content='已下架';
                                }else{
                                    content='已上架';
                                }
                                return content;
                            },
                        }
                        ,{ id: 'createTime', title: '添加时间', type: 'string', columnClass: 'text-center',columnStyle: 'width: 180px;', resolution: function (value, record, column, grid, dataNo, columnNo) {
                            var tmp = value.substring(0,10);
                            return tmp;
                        }
                        }
                        ,{
                            id: 'operation', title: '操作', type: 'string', columnClass: 'text-center',columnStyle: 'width: 260px;',
                            resolution: function (value, record, column, grid, dataNo, columnNo) {
                                var content = '';
                                content += '<a class="edit btn btn-xs btn-warning" data-toggle="modal" data-value="'+dataNo+'" onclick="changeStatus('+record.id+','+record.status+')"><i class="fa fa-edit"></i>'+(record.status===1?"下架":"上架")+'</a>&nbsp;';
                                content += '<a class="edit btn btn-xs btn-warning" data-toggle="modal" data-value="'+dataNo+'" onclick="edit('+record.id+')"><i class="fa fa-edit"></i>编辑</a>&nbsp;';
                                content += '<a class="edit btn btn-xs btn-danger" data-toggle="modal" data-value="'+dataNo+'" onclick="del('+record.id+')"><i class="fa fa-edit"></i>删除</a>&nbsp;';
                                content += '<a class="edit btn btn-xs btn-warning" data-toggle="modal" data-value="'+dataNo+'" onclick="detail('+record.id+')"><i class="fa fa-edit"></i>查看</a>&nbsp;';

                                grid.parameters = new Object();
                                grid.parameters.nowPage = '';
                                grid.parameters.pageSize = '';
                                grid.parameters.name = $("input[name='news.name']").val();
                                grid.parameters.status = $("select[name='news.status']").val();
                                return content;
                            }
                        }
                    ];
                }else{
                    //初始化dtgrid
                    var dtGridColumns_2_1_2 = [
                        { id: 'name', title: '标题', type: 'string', columnClass: 'text-center' }
                        ,{ id: 'imgeUrl', title: '图片', type: 'string', columnClass: 'text-center' ,
                            resolution: function (value, record, column, grid, dataNo, columnNo) {
                                var html ='';
                                if(value !=""){
                                    html = "<img src='"+fileRootUrl+value+"' width='80'  height='60'>";
                                }
                                return html;
                            }
                        }
                        ,{ id: 'descIntroduction', title: '副标题', type: 'string', columnClass: 'text-center',
                            resolution: function (value, record, column, grid, dataNo, columnNo) {
                                var tmp = value;
                                var tmpstr = tmp.replace(/<[^>]+>/g, '');
                                if(tmpstr.length > 10){
                                    var html='<span class="more-text" title="'+ value +'">'+(tmpstr + "").substring(0, 10) + '...'+'</span>';
                                }else{
                                    var html='<span class="more-text">'+value+'</span>';
                                }
                                return html
                            }
                        }
                        ,{ id: 'oderNum', title: '顺序号', type: 'string', columnClass: 'text-center' }
                        ,{ id: 'status', title: '状态', type: 'string', columnClass: 'text-center',
                            resolution: function (value, record, column, grid, dataNo, columnNo) {
                                var content = '';
                                if(value==0){
                                    content='已下架';
                                }else{
                                    content='已上架';
                                }
                                return content;
                            },
                        }
                        ,{ id: 'createTime', title: '添加时间', type: 'string', columnClass: 'text-center',columnStyle: 'width: 180px;', resolution: function (value, record, column, grid, dataNo, columnNo) {
                                var tmp = value.substring(0,10);
                                return tmp;
                            }
                        }
                        ,{
                            id: 'operation', title: '操作', type: 'string', columnClass: 'text-center',columnStyle: 'width: 260px;',
                            resolution: function (value, record, column, grid, dataNo, columnNo) {
                                var content = '';
                                content += '<a class="edit btn btn-xs btn-warning" data-toggle="modal" data-value="'+dataNo+'" onclick="changeStatus('+record.id+','+record.status+')"><i class="fa fa-edit"></i>'+(record.status===1?"下架":"上架")+'</a>&nbsp;';
                                content += '<a class="edit btn btn-xs btn-warning" data-toggle="modal" data-value="'+dataNo+'" onclick="edit('+record.id+')"><i class="fa fa-edit"></i>编辑</a>&nbsp;';
                                content += '<a class="edit btn btn-xs btn-danger" data-toggle="modal" data-value="'+dataNo+'" onclick="del('+record.id+')"><i class="fa fa-edit"></i>删除</a>&nbsp;';
                                content += '<a class="edit btn btn-xs btn-warning" data-toggle="modal" data-value="'+dataNo+'" onclick="detail('+record.id+')"><i class="fa fa-edit"></i>查看</a>&nbsp;';

                                grid.parameters = new Object();
                                grid.parameters.nowPage = '';
                                grid.parameters.pageSize = '';
                                grid.parameters.name = $("input[name='news.name']").val();
                                grid.parameters.status = $("select[name='news.status']").val();
                                return content;
                            }
                        }
                    ];
                }
                var dtGridOption_2_1_2 = {
                    lang: 'zh-cn',
                    ajaxLoad: true,
                    loadURL: baseUrl + '/admin/news/list4ajax/'+type,
                    columns: dtGridColumns_2_1_2,
                    gridContainer: 'dtGridContainer_2_1_2',
                    check:true,//checkbox设置
                    toolbarContainer: 'dtGridToolBarContainer_2_1_2',
                    tools: '',
                    pageSize: 15,
                    pageSizeLimit: [15, 25, 50]
                };
                grid = $.fn.DtGrid.init(dtGridOption_2_1_2);
                grid.parameters = new Object();
                grid.parameters.name = name;
                grid.parameters.status = status;
                grid.parameters.nowPage = nowPage;
                grid.parameters.pageSize = pageSize;
                grid.load();
            };
            dtGrid();
        },
        search: function() {
            grid.parameters = new Object();
            grid.parameters.name = $('input[name="news.name"]').val();
            grid.parameters.status = $("select[name='news.status']").val();
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
      window.location = baseUrl + "/admin/news/onAdd/"+type;
    }
    function edit(id){
      var actionUrl = baseUrl + "/admin/news/onEdit";
      $("#editId").val(id);
      $("#name").val($("input[name='news.name']").val());
      $("#status").val( $("select[name='news.status']").val());
      $("#commonNowPage").val(grid.pager.nowPage);
      $("#commonPageSize").val(grid.pager.pageSize);
      $("#commonForm").attr("action",actionUrl);
      $("#commonForm").submit();
    }
    function del(id){
      if (confirm('确定删除?')){
        var actionUrl = baseUrl + "/admin/news/doDel/"+type;
          $("#editId").val(id);
          $("#name").val($("input[name='news.name']").val());
          $("#status").val( $("select[name='news.status']").val());
          $("#commonNowPage").val(grid.pager.nowPage);
          $("#commonPageSize").val(grid.pager.pageSize);
          $("#commonForm").attr("action",actionUrl);
          $("#commonForm").submit();
        }
    }

  function detail(id){
      var actionUrl = baseUrl + "/admin/news/onDetail";
      $("#editId").val(id);
      $("#name").val($("input[name='news.name']").val());
      $("#status").val( $("select[name='news.status']").val());
      $("#commonNowPage").val(grid.pager.nowPage);
      $("#commonPageSize").val(grid.pager.pageSize);
      $("#commonForm").attr("action",actionUrl);
      $("#commonForm").submit();
  }

  //banner上架、下架
  function changeStatus(paramId,statusId) {
      if(statusId == 0){
          statusId = 1;
      }else{
          statusId = 0;
      }
      Common.confirm({
          title: '提示',
          message: '确认'+(statusId == 1?'上架':'下架')+'?',
          operate: function (result) {
              if (result) {
                  $.ajax({
                      url:baseUrl+ '/api/news/updateStatus',
                      data: {id:paramId,status:statusId},
                      type:'post',
                      success: function (data) {
                          if(data.status === 1){
                              grid.refresh(true);
                          }else{
                              Common.warning({
                                  title: '提示',
                                  message: data.message
                              });
                          }
                      },
                      error:function (status) {
                          Common.warning({
                              title: '提示',
                              message: '提交失败，返回码：'+status
                          });
                      }
                  });
              } else {
                  console.log('用户放弃了');
              }
          }
      });
  }

  function selectAll() {
      var checkboxes = $('input[type=checkbox]');
      if(checkboxes == undefined || checkboxes == null  || checkboxes.length == 0) {
          return;
      }

      for(var i = 0; i < checkboxes.length; i++) {
          checkboxes[i].checked = true;
      }
  };

  function deselectAll() {
      var checkboxes = $('input[type=checkbox]');
      if(checkboxes == undefined || checkboxes == null  || checkboxes.length == 0) {
          return;
      }

      for(var i = 0; i < checkboxes.length; i++) {
          checkboxes[i].checked = false;
      }
  };

  //批量删除
  function delAll() {
      var allIds = [];
      $(grid.getCheckedRecords()).each(function (index,value) {
          var idList = value.id;
          allIds.push(idList);
      });
      if(allIds.length > 0){
          Common.confirm({
              title: '提示',
              message: '是否确定全部删除?',
              operate: function (result) {
                  if (result) {
                      $.ajax({
                          url:baseUrl+ '/api/news/delBatch',
                          data: {ids:JSON.stringify(allIds)},
                          type:'post',
                          success: function (data) {
                              if(data.status === 1){
                                  grid.refresh(true);
                              }else{
                                  Common.warning({
                                      title: '提示',
                                      message: data.message
                                  });
                              }
                          },
                          error:function (status) {
                              Common.warning({
                                  title: '提示',
                                  message: '提交失败，返回码：'+status
                              });
                          }
                      });
                  } else {
                      console.log('用户放弃了');
                  }
              }
          });
      }else{
          Common.confirm({
              "title":'提示',
              "message":'请勾选需要删除的内容'
          })
      }
  }

  //批量上下架
  function changeAll(statusId) {
      var allIds = [];
      $(grid.getCheckedRecords()).each(function (index,value) {
          var idList = value.id;
          allIds.push(idList);
      });
      if(allIds.length > 0){
          Common.confirm({
              title: '提示',
              message: '确认'+(statusId == 1?'上架':'下架')+'?',
              operate: function (result) {
                  if (result) {
                      $.ajax({
                          url:baseUrl+ '/api/news/updateBatchStatus',
                          data: {ids:JSON.stringify(allIds),status:statusId},
                          type:'post',
                          success: function (data) {
                              if(data.status === 1){
                                  grid.refresh(true);
                              }else{
                                  Common.warning({
                                      title: '提示',
                                      message: data.message
                                  });
                              }
                          },
                          error:function (status) {
                              Common.warning({
                                  title: '提示',
                                  message: '提交失败，返回码：'+status
                              });
                          }
                      });
                  } else {
                      console.log('用户放弃了');
                  }
              }
          });
      }else{
          Common.confirm({
              title: '提示',
              message: '请勾选'+(statusId == 1?'上架':'下架')+'内容'
          })
      }
  }
</script>
<!-- ================== BEGIN PAGE LEVEL JS ================ -->
<script src="${base}/resources/common/script/parsley/dist/parsley.js"></script>
<!-- ================== END PAGE LEVEL JS ================== -->
</body>
</html>