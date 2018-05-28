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
                { id: 'schoolCode', title: '学校', type: 'string', columnClass: 'text-center'},
                { id: 'name', title: '姓名', type: 'string', columnClass: 'text-center', resolution:function(value, record, column, grid, dataNo, columnNo){
                	var content = '';
                	if(value){
            			content += '<span>'+value+'</span>';
            		}else{
            			content += '<span>--</span>';
            		}
            		return content;
            	}},
            	{ id: 'loginName', title: '登录名', type: 'string', columnClass: 'text-center'},
                { id: 'gender', title: '性别', type: 'string', columnClass: 'text-center', resolution:function(value, record, column, grid, dataNo, columnNo){
            		var content = '';
            		if(value){
    					if (parseInt(value) == 1) {
    						content += '<span>男</span>';
    					} else if (parseInt(value) == 2) {
    						content += '<span>女</span>';
    					} else {
    						content += '<span>未知</span>';
    					}
    				}else{
    					content += '<span>未知</span>';
    				}
            		return content;
            	}},
                { id: 'isOnjob', title: '是否在职', type: 'string', columnClass: 'text-center', resolution:function(value, record, column, grid, dataNo, columnNo){
            		var content = '';
            		if(value){
            			if (value == 1) {
            				content += '<span>是</span>';
        				} else{
        					content += '<span>否</span>';
        				}
    				}else{
    					content += '<span>否</span>';
    				}
            		return content;
            	}},
                { id: 'isLock', title: '账号状态', type: 'string', columnClass: 'text-center', resolution:function(value, record, column, grid, dataNo, columnNo){
            		var content = '';
           			if (value == 1) {
           				content += '<span>禁用</span>';
       				} else {
       					content += '<span>启用</span>';
       				}
            		return content;
            	}},
                { id: 'lastLoginTime', title: '最后登录时间', type: 'string', columnClass: 'text-center', resolution:function(value, record, column, grid, dataNo, columnNo){
                	var content = '';
            		if(value){
            			content += '<span>'+(new Date(parseInt(value))).Format("yyyy-MM-dd hh:mm:ss")+'</span>';
            		}else{
            			content += '<span>--</span>';
            		}
            		return content;
            	}},
                { id: 'lastLoginIp', title: '最后登录IP', type: 'string', columnClass: 'text-center', resolution:function(value, record, column, grid, dataNo, columnNo){
                	var content = '';
            		if(value){
            			content += '<span>'+value+'</span>';
            		}else{
            			content += '<span>--</span>';
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
            		return content;
            	}},
                { id: 'createTime', title: '创建时间', type: 'string', columnClass: 'text-center', resolution:function(value, record, column, grid, dataNo, columnNo){
                	var content = '';
            		if(value){
            			content += '<span>'+ (new Date(parseInt(value))).Format("yyyy-MM-dd hh:mm:ss")+'</span>';
            		}else{
            			content += '<span>--</span>';
            		}
            		// 重置隐含数据
            		grid.parameters = new Object();
            		grid.parameters.nowPage = '';
	                grid.parameters.pageSize = '';
	                grid.parameters.loginName = $('input[name="manager.loginName"]').val();
    			 	grid.parameters.schoolCode = $('select[name="manager.schoolCode"]').val();
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
                pageSize: 15,
                pageSizeLimit: [15, 25, 50]
            };
            grid = $.fn.DtGrid.init(dtGridOption_2_1_2);
            grid.parameters = new Object();
            grid.parameters.nowPage = nowPage;
            grid.parameters.pageSize = pageSize;
            grid.parameters.loginName = loginName;
		 	grid.parameters.schoolCode = schoolCode;
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
   	 	grid.parameters.schoolCode = $('select[name="manager.schoolCode"]').val();
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
	window.location="${base}/admin/manager/managerAction!add.${actionExt}";
}
function edit(id){
	var actionUrl="${base}/admin/manager/managerAction!edit.${actionExt}";
	$("#editId").val(id);
	$("#loginName").val($("input[name='manager.loginName']").val());
	$("#schoolCode").val($("select[name='manager.schoolCode']").val());
	$("#roleId").val($("select[name='manager.roleId']").val());
	$("#isOnjob").val($("select[name='manager.isOnjob']").val());
	$("#isLock").val($("select[name='manager.isLock']").val());
	$("#commonNowPage").val(grid.pager.nowPage);
	$("#commonPageSize").val(grid.pager.pageSize);
	$("#commonForm").attr("action",actionUrl);
	$("#commonForm").submit();
	//window.location="${base}/admin/manager/managerAction!edit.${actionExt}?id=" + id;
}
function resetPassword(id){
	window.location="${base}/admin/manager/managerAction!executeShowResetPassword.${actionExt}?id=" + id;
}

function dialogBtn(id){
	$("#id").val(id);
}

function save(){
	var actionUrl="${base}/admin/ajax/ajaxEditManagerResetSavePassword.${actionExt}";
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