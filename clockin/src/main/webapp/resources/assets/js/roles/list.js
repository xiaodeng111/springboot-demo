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
                        if(record.id == 1){
                            content += '<a class="edit btn btn-xs btn-warning" data-toggle="modal" data-value="'+dataNo+'" disabled="true"><i class="fa fa-edit"></i>  编辑</a>&nbsp;';
                        }else{
                            content += '<a class="edit btn btn-xs btn-warning" data-toggle="modal" data-value="'+dataNo+'" onclick="edit('+record.id+')"><i class="fa fa-edit"></i>  编辑</a>&nbsp;';

                        }
                        //content += '<a class="edit btn btn-xs btn-danger" data-toggle="modal" data-value="'+dataNo+'" onclick="del('+record.id+')"><i class="fa fa-edit"></i>  删除</a>&nbsp;';
                        return content;
                    }
                },
                { id: 'name', title: '名称', type: 'string', columnClass: 'text-center' },
                { id: 'roleStatus', title: '状态', type: 'string', columnClass: 'text-center' , resolution:function(value, record, column, grid, dataNo, columnNo){
            		var content = '';
            		if(value=="1"){
            			content += '<span>启用</span>';
            		}else{
            			content += '<span>禁用</span>';
            		}
            		return content;
            	}},
                { id: 'creator', title: '创建人', type: 'string', columnClass: 'text-center', resolution:function(value, record, column, grid, dataNo, columnNo){
                	var content = '';
            		if(value){
            			content += '<span>'+value+'</span>';
            		}else{
            			content += '<span>--</span>';
            		}
            		// 重置隐含数据
            		grid.parameters = new Object();
            		grid.parameters.nowPage = '';
	                grid.parameters.pageSize = '';
	                grid.parameters.name = $('input[name="roles.name"]').val();
            		return content;
            	}},
                { id: 'createDate', title: '创建时间', type: 'String', columnClass: 'text-center'}
            ];
            var dtGridOption_2_1_2 = {
                lang: 'zh-cn',
                ajaxLoad: true,
                loadURL: baseUrl + '/admin/roles/list4ajax',
                exportFileName: '角色列表',
                columns: dtGridColumns_2_1_2,
                gridContainer: 'dtGridContainer_2_1_2',
                toolbarContainer: 'dtGridToolBarContainer_2_1_2',
                tools: '',
                pageSize: 15,
                pageSizeLimit: [15, 25, 50]
            };
            grid = $.fn.DtGrid.init(dtGridOption_2_1_2);
            grid.parameters = new Object();
            grid.parameters.nowPage = nowPage;
            grid.parameters.pageSize = pageSize;
       	 	grid.parameters.name = name;
            grid.load();
        };
        dtGrid();
    },
    search: function() {
        grid.parameters = new Object();
   	 	grid.parameters.name = $('input[name="roles.name"]').val();
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
	window.location = baseUrl + "/admin/roles/onAdd";
}
function edit(id){
	var actionUrl = baseUrl + "/admin/roles/onEdit";
	$("#editId").val(id);
	$("#name").val($("input[name='roles.name']").val());
	$("#commonNowPage").val(grid.pager.nowPage);
	$("#commonPageSize").val(grid.pager.pageSize);
	$("#commonForm").attr("action",actionUrl);
	$("#commonForm").submit();
	//window.location="${base}/admin/roles/rolesAction!edit.${actionExt}?id="+id;
}