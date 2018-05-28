/**
 * Created by DELL on 2017/2/28.
 */
$(function () {
    //头部navbar绑定事件
    $('.btns').on('click',function () {
        $('.navBar').toggleClass('toggleNav');
    })
    $(document).on('click',function (e) {
        var target = $(e.target);
        if(target.closest(".btns,.navBar").length == 0){//点击id为sibader之外的地方触发
            $('.navBar').removeClass('toggleNav');
        }
    })
})

function clearSelect(s){	//清空select
	if(s!=null && s.options!=null && s.options.length>0){
		for(var i=s.options.length-1;i>=0;i--){	//此处使用倒着删除，否则会有垃圾数据
			s.options.remove(i); 
		}
	}
	
}

function getSelectedValue(obj){//获取select选中项的text
	var t = "";	
	var v = obj.value;
	if(v>0){
		var os = obj.options;
		for(var i=0;i<os.length;i++){
			if(os[i].value==v){
				t = os[i].text;
				break;
			}
		}
	}
	return t;
}

function trim(str){	//去掉空格
	return str.replace(/(^\s*)|(\s*$)/g, "");
}

function checkNumber(value){	//验证数字
    //定义正则表达式部分
	var reg = new RegExp("^[0-9]*$");
	if(!reg.test(value)){
		return true;
	}
    return false;
}
//获取数据类型
function getType(o){
    var _t;
    return ((_t = typeof(o)) == "object" ? o==null && "null" || Object.prototype.toString.call(o).slice(8,-1):_t).toLowerCase();
}
//深度克隆数组
function cloneArray(sourceArray){
	var resultArray = [];
    for(var index in sourceArray){
    	if(getType(sourceArray[index])=="array"){
    		resultArray[index] = cloneArray(sourceArray[index]);
    	}else if(getType(sourceArray[index])=="object"){
    		resultArray[index] = cloneObject(sourceArray[index]);
    	}else {
            resultArray[index] = sourceArray[index];
        }
    }
    return resultArray;
}
//深度克隆对象
function cloneObject(sourceObject){
	var resultObject ={};
	for(var key in sourceObject){
		if(getType(sourceObject[key])=="array"){
			resultObject[key] = cloneArray(sourceObject[key]);
    	}else if(getType(sourceObject[key])=="object"){
    		resultObject[key] = cloneObject(sourceObject[key]);
    	}else {
    		resultObject[key] = sourceObject[key];
        }
	}
	return resultObject;
}
//window.open弹出居中窗口
function openWindow(url, w, h){
	//获得窗口的垂直位置;
	var top = (window.screen.availHeight-30-h)/2; 
	//获得窗口的水平位置;
	var left = (window.screen.availWidth-10-w)/2; 
	
	window.open(url,"","height="+h+", width="+w+", top="+top+", left="+left+", toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no"); 
	
}

//window.open关闭页面
function closeWindow(){
	window.close();
}

/*   
js由毫秒数得到年月日   
使用： (new Date(data[i].creationTime)).Format("yyyy-MM-dd hh:mm:ss.S")   
*/    
Date.prototype.Format = function (fmt) { //author: meizz    
var o = {    
    "M+": this.getMonth() + 1, //月份    
    "d+": this.getDate(), //日    
    "h+": this.getHours(), //小时    
    "m+": this.getMinutes(), //分    
    "s+": this.getSeconds(), //秒    
    "q+": Math.floor((this.getMonth() + 3) / 3), //季度    
    "S": this.getMilliseconds() //毫秒    
};    
if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));    
for (var k in o)    
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));    
return fmt;    
};    
