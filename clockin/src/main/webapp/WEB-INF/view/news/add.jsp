<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>${systemName}-发现</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport"/>
    <link rel="stylesheet" href="${base}/resources/assets/css/demo.css">
    <link href="${base}/resources/assets/css/bootstrap.min.css" rel="stylesheet"/>
    <%@ include file="/jsp/common/meta.jsp"%>
    <script language="javascript">
        function checkForm(){
            if($('#jumpUrl').val() !='' && $('#jumpUrl').val() !=null){
                if($('#jumpUrl').val().substring(0,8) =='https://' || $('#jumpUrl').val().substring(0,7) =='http://'){
                    $('#jumpUrlErrorNote').text("");
                }else{

                    $('#jumpUrlErrorNote').text("链接前缀格式不正确");
                    return false;
                }
            }
            if(!UE.getEditor('content').hasContents()){
                $('#contentErrorNote').text("内容不能为空");
               return false;
            }

            return true;
        }
        function cancleClick(){
            self.location = baseUrl + '/admin/news/onList';    
        }
    </script>
<%--    <script type="text/javascript" charset="utf-8" src="${base}/resources/common/plugins/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${base}/resources/common/plugins/ueditor/ueditor.all.js"></script>
    <script type="text/javascript" charset="utf-8" src="${base}/resources/common/plugins/ueditor/lang/zh-cn/zh-cn.js"></script>--%>
    <script type="text/javascript" charset="utf-8" src="${base}/js/common.js"></script>
    <style type="text/css">
        body{ font-size:12px;}
            .l-table-edit {}
            .l-table-edit-td{ padding:4px;}
            .l-button-submit,.l-button-reset{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
            .l-verify-tip{ left:230px; top:120px;}
    </style>

</head>
<body>
    <div class="col-md-6 ui-sortable" style="width:100%;height: 100%;">
        <!-- begin panel -->
        <div class="panel panel-inverse">
            <div class="panel-header">
                <span class="glyphicon glyphicon-menu-left m-r-15 click-mose" aria-hidden="true" onclick="history.back()"></span>
                <h4 class="panel-title not-line">${requestScope.title}-添加</h4>
            </div>
             <div class="panel-body panel-form">
                  <form data-parsley-validate="true" name="schoolForm" id="schoolForm" method="post" action="${base}/admin/news/add/${requestScope.type}" onsubmit="return checkForm();" class="form-horizontal form-bordered" data-validate="parsley">
                    <div class="form-group">
                        <label class="control-label col-md-2 col-sm-4 ui-sortable" for="name"><span style="color: red">*</span>标题：</label>
                        <div class="col-md-6 col-sm-6 ui-sortable">
                            <input name="name" maxlength="20" data-parsley-required="true" type="text" id="name" ltype="text" class="form-control parsley-validated" style="width:300px;"  placeholder="请输入不超过20位"/>
                        </div>
                    </div>

                    <div class="form-group ">
                        <label class="control-label col-md-2 col-sm-4 ui-sortable">
                            <c:if test="${requestScope.type != 2}">
                                 <span style="color: red">*</span>
                            </c:if>配图：
                        </label>
                        <div class="col-md-6 col-sm-6 ui-sortable">
                            <input type="button" value="上传配图"  onclick="updateImg()"/>
                            <div class="nav-padding-top">
                                <input type="hidden" name="imgeUrl" id="imgeUrl">

                                <c:if test="${requestScope.type == 1}">
                                <img src="" id="group-img" width="500"  height="200" class="hidden">
                                <input id="img-input" class="hide" data-parsley-required="true" data-parsley-group="addInfo"  data-parsley-error-message="请上传720px*350px尺寸图片"  >
                                <p class="text-info f-s-14 m-t-10">建议上传图片720px*350px尺寸</p>
                             </c:if>
                            <c:if test="${requestScope.type == 2}">
                                <img src="" id="group-img" width="80"  height="60"  class="hidden">
                                <input id="img-input" class="hide" data-parsley-required="false" data-parsley-group="addInfo"  data-parsley-error-message="请上传232px*132px尺寸图片"  >
                                <p class="text-info f-s-14 m-t-10">建议上传图片232px*132px尺寸</p>
                            </c:if>
                            <c:if test="${requestScope.type == 3 || requestScope.type == 4}">
                                <img src="" id="group-img" width="60"  height="40"  class="hidden">
                                <input id="img-input" class="hide" data-parsley-required="true" data-parsley-group="addInfo"  data-parsley-error-message="请上传160px*160px尺寸图片"  >
                                <p class="text-info f-s-14 m-t-10">建议上传图片160px*160px尺寸</p>
                            </c:if>
                            <input accept="image/gif,image/jpeg,image/jpg,image/png,image/svg" type="file"
                                   id="fileControl" class="hide">
                            </div>
                        </div>
                    </div>
                      <c:if test="${requestScope.type != 2}">
                          <div class="form-group">
                              <label class="control-label col-md-2 col-sm-4 ui-sortable" for="name">
                                  <c:if test="${requestScope.type == 3 || requestScope.type == 4}">
                                      <span style="color: red">*</span>
                                  </c:if>跳转链接：</label>
                              <div class="col-md-6 col-sm-6 ui-sortable">
                                  <input name="jumpUrl" maxlength="128" type="text" id="jumpUrl" ltype="text" placeholder="链接以https://或者http://开头"  <c:if test="${requestScope.type == 3 || requestScope.type == 4}">
                                      data-parsley-required="true"
                                 </c:if> class="form-control parsley-validated"/>
                                  <p style="color:rgb(229, 96, 59);font-size:12px;font-family: Open Sans,Helvetica Neue,Helvetica,Arial,sans-serif;" id="jumpUrlErrorNote"></p>
                              </div>
                          </div>
                      </c:if>

                    <div class="form-group">
                        <label class="control-label col-md-2 col-sm-4 ui-sortable" for="name"><span style="color: red">*</span>副标题：</label>
                        <div class="col-md-6 col-sm-6 ui-sortable">
                            <input name="descIntroduction" maxlength="20" data-parsley-required="true" type="text" id="descIntroduction" ltype="text" class="form-control parsley-validated" style="width:400px;"  placeholder="请输入不超过20位"/>
                        </div>
                    </div>

                      <c:if test="${requestScope.type == 2}">
                          <div class="form-group">
                              <label class="control-label col-md-2 col-sm-4 ui-sortable" for="name">
                                      <span style="color: red">*</span> 内容：</label>
                            <%--  <div class="col-md-6 col-sm-6 ui-sortable">
                                  <textarea id="ckeditor" name="content" data-parsley-required="true"></textarea>
                              </div>--%>
                              <div class="col-md-6 col-sm-6 ui-sortable">
                                  <textarea id="content" name="content" data-parsley-required="true"></textarea>
                                  <script type="text/javascript" charset="UTF-8">
                                      UE.getEditor('content');
                                  </script>
                                  <p style="color:rgb(229, 96, 59);font-size:12px;font-family: Open Sans,Helvetica Neue,Helvetica,Arial,sans-serif;" id="contentErrorNote"></p>
                              </div>
                          </div>
                      </c:if>

                      <div class="form-group">
                        <label class="control-label col-md-2 col-sm-4 ui-sortable" for="name"><span style="color: red">*</span>顺序号：</label>
                        <div class="col-md-6 col-sm-6 ui-sortable">
                            <input name="oderNum" maxlength="1" data-parsley-required="true" type="text" onkeyup="value=value.replace(/[^\d]/g,'')"
                                   ng-pattern="/[^a-zA-Z]/" id="oderNum" ltype="text" class="form-control parsley-validated" style="width:100px;" placeholder="请输入一位数"/>
                        </div>
                    </div>
                    <div class="form-group">
                      <label class="control-label col-md-2 col-sm-4 ui-sortable"><span style="color: red">*</span>状态：</label>
                      <div class="col-md-6 col-sm-6 ui-sortable">
                          <select id="status" name="status" class="formList width-150" onchange="statusChange(this)">
                              <option value="">请选择</option>
                              <option value="0">下架</option>
                              <option value="1">上架</option>
                          </select>
                          <input type="text" id="status-hidden"  class="hidden-input"
                                 data-parsley-required="true"
                                 data-parsley-required-message="请选择状态">
                      </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2 col-sm-4 ui-sortable"></label>
                        <div class="col-md-6 col-sm-6 ui-sortable" style="margin-bottom:60px;">
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

 <%--   <script src="${base}/resources/common/plugins/ckeditor/ckeditor.js"></script>

    <script>
    CKEDITOR.replace('ckeditor',{
            filebrowserImageUploadUrl : baseUrl+"/CKEditor/image",
            filebrowserFlashUploadUrl : baseUrl+"/CKEditor/video",
        });
    </script>--%>
    <!-- ================== END PAGE LEVEL JS ================== -->
    <script>
        function statusChange(t) {
            if($(t).val() === ''){
                $('#status-hidden').val('');
            }else{
                $('#status-hidden').val($(t).val());
            }
        }

        function updateImg() {
            $('#fileControl').click();
        }
        //上传
        function Upload(source, cb) {
            var file = source.files[0],
                file_size = null;
            if (file) {
                file_size = Math.floor(file.size / 1024);
            }
            if (window.FileReader) {
                var fr = new FileReader();
                fr.onloadend = function (e) {
                    var base64Str = e.target.result;
                    if (file_size < 1024) {
                        $.ajax({
                            url: baseUrl + '/upload/image/baseStr',
                            type: 'post',
                            data: {file: base64Str},
                            success: function (data) {
                                if(data.status== 1){
                                    cb(data);
                                }else{
                                    Common.warning({
                                        title: '提示',
                                        message: '图片上传失败！'
                                    });
                                }
                            }, error: function () {
                                Common.warning({
                                    title: '提示',
                                    message: '图片上传失败！'
                                });
                            }
                        });
                    }
                    else {
                        Common.warning({
                            title: '提示',
                            message: '*图片超过1M'
                        });
                    }
                };
                try {
                    fr.readAsDataURL(file);
                } catch (e) {
                }
            }
        }
        $(function () {
            if (!!$('#group-img').attr('src') && $('#group-img').attr('src').length > 12) {
                $('#img-input').val('1');
            }
            var $fileControl = $('#fileControl');
            $fileControl.on("change", function () {
                if ($(this).val() == "") {
                    return;
                }
                Upload(this, function (data) {
                    if (!!data && !!data.data) {
                        var imgUrl = data.data;
                        $('#group-img').attr('src', fileRootUrl+imgUrl);
                        $("#group-img").removeClass();
                        $('#imgeUrl').val(imgUrl);
                        $('#img-input').val('1');
                        $('#img-input').siblings('.parsley-errors-list').remove();
                        $fileControl.val('');

                    } else {
                        Common.warning({
                            title: '提示',
                            message: '图片上传失败！'
                        });
                        $fileControl.val('');
                    }

                });
            });
        })
    </script>
</body>
</html>