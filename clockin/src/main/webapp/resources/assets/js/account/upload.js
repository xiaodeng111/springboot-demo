
$(document).ready(function() {
    $('.modalClose').click(function () {
        $('#imgSelect').imgAreaSelect({remove: true});
    });
    //提交图片剪切信息到后台
    $("#subPhoto").click(function() {
        var x1 = $("input[name='x1']").val();
        var y1 = $("input[name='y1']").val();
        var x2 = $("input[name='x2']").val();
        var y2 = $("input[name='y2']").val();
        var img64 = $("#imgSelect").attr("src");
        var url = baseUrl + '/upload/image';
        var param = {
            'x1': x1,
            'y1': y1,
            'x2': x2,
            'y2': y2,
            'image': img64
        }
        console.log(param);
        $.ajax({
            type: 'POST',
            url: url,
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(param),
            success: function success(data) {
                var json = typeof data == 'string' ? JSON.parse(data) : data;
                if(json.message != null && json.message.length > 0) {
                    $.ligerDialog.error(json.message);
                    return;
                }
                $("#imghead").attr('src', json.logoUrl);
                $("#logo").val(json.logo);
                $('#myModal').modal("hide");
                $('#imgSelect').imgAreaSelect({remove: true});
            }
        });

    })

});
//点击图像区域选择图片
function changeImg(obj) {
    //图片选择处理
    var file = obj;
    var MAXWIDTH = 400;
    var MAXHEIGHT = 400;
    var MAXSIZE = 2048 * 1024;
    var div = document.getElementById('preview');
    if(file.files && file.files[0]) {
        var fileExt = file.files[0].name.substring(file.files[0].name.lastIndexOf(".") + 1, file.files[0].name.length);
        if(fileExt == null || fileExt == "" || uploadTypesImg.indexOf(fileExt) == -1){
            alert("上传失败：上传文件类型错误("+uploadTypesImg+")");
            return false;
        }
        if(file.files[0].size > MAXSIZE) {
            alert("图片尺寸过大");
            return false;
        };
        $('#myModal').modal("show");
        div.innerHTML = '<img id="imgSelect" style="width: 100%;height: auto;max-height: 550px;max-width: 550px;">';
        var img = document.getElementById('imgSelect');
        img.onload = function() {
            setTimeout(function(){
                var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
                //img.style.marginTop = rect.top + 'px';

                setTimeout(function(){
                    //图片剪切区域处理
                    $('#imgSelect').imgAreaSelect({
                        x1: 0,
                        y1: 0,
                        x2: 100,
                        y2: 100,
                        aspectRatio: '1:1', //比例
                        handles: true,
                        onSelectChange: function(img, selection) { //图片剪切区域变化时触发
                            //$("#imgmsg").html("x1:" + selection.x1 + ", y1:" + selection.y1 + ", x2:" + selection.x2 + ", y2:" + selection.y2);
                        },
                        onSelectEnd: function(img, selection) { //图片剪切区域结束时触发
                            $('input[name="x1"]').val(selection.x1);
                            $('input[name="y1"]').val(selection.y1);
                            $('input[name="x2"]').val(selection.x2);
                            $('input[name="y2"]').val(selection.y2);
                        }
                    });
                }, 400);
            }, 200);
        }
        var reader = new FileReader();
        reader.onload = function(evt) {
            img.src = evt.target.result;
        }
        reader.readAsDataURL(file.files[0]);
    }

    $(obj).val('');
}

//设置图片显示区域为固定大小,方便后台按统一比例截取图片
function clacImgZoomParam(maxWidth, maxHeight, width, height) {
    var param = { top: 0, left: 0, width: width, height: height };
    if(width < maxWidth && height < maxHeight){
        param.width = maxWidth;
        param.height = maxHeight;
    }else {
        if(width > maxWidth || height > maxHeight) {
            rateWidth = width / maxWidth;
            rateHeight = height / maxHeight;
        }else {
            rateWidth = maxWidth / width;
            rateHeight = maxHeight / height;
        }
        if(rateWidth > rateHeight) {
            param.width = maxWidth;
            param.height = Math.round(height / rateWidth);
        } else {
            param.width = Math.round(width / rateHeight);
            param.height = maxHeight;
        }
    }
    param.left = Math.round((maxWidth - param.width) / 2);
    param.top = Math.round((maxHeight - param.height) / 2);
    return param;
}