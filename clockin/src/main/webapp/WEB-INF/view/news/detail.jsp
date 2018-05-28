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
            return true;
        }
        function cancleClick(){
            self.location = baseUrl + '/admin/news/onList';    
        }
    </script>
    <style type="text/css">
        body{ font-size:12px;}
            .l-table-edit {}
            .l-table-edit-td{ padding:4px;}
            .l-button-submit,.l-button-reset{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
            .l-verify-tip{ left:230px; top:120px;}
            .p-t-22{padding-top: 22px!important;}
    </style>
</head>
</head>
<body>
    <div class="col-md-6 ui-sortable" style="width:100%;">
        <!-- begin panel -->
        <div class="panel panel-inverse">
            <div class="panel-header">
                <span class="glyphicon glyphicon-menu-left m-r-15 click-mose" aria-hidden="true" onclick="history.back()"></span>
                <h4 class="panel-title not-line">${requestScope.title}-详情</h4>
            </div>
             <div class="panel-body panel-form">
                  <form data-parsley-validate="true" name="schoolForm" id="schoolForm" method="post" action="" onsubmit="return checkForm();" class="form-horizontal form-bordered" data-validate="parsley">
                    <input type="hidden" id="id" name="id" value="${requestScope.news.id }"/>
                    <input type="hidden" name="nowPage" id="commonNowPage" value="${requestScope.nowPage}"/>
                    <input type="hidden" name="pageSize" id="commonPageSize" value="${requestScope.pageSize}"/>
                      <input type="hidden" name="commonName" id="commonName" value="${requestScope.name}"/>
                      <input type="hidden" name="commonStatus" id="commonStatus" value="${requestScope.status}"/>
                    <div class="form-group">
                        <label class="control-label col-md-2 col-sm-4 ui-sortable " >标题：</label>
                        <div class="col-md-6 col-sm-6 ui-sortable p-t-22">
                            <span >${requestScope.news.name }</span>
                    </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2 col-sm-4 ui-sortable" >配图：</label>
                        <div class="col-md-6 col-sm-6 ui-sortable">
                            <input type="hidden" name="imgeUrl" id="imgeUrl" value='${requestScope.news.imgeUrl }'>

                            <c:if test="${requestScope.news.type == 1}">
                                <img src="" id="group-img" class="hide"  width="500"  height="250">
                            </c:if>
                            <c:if test="${requestScope.news.type == 2}">
                                <img src="" id="group-img" class="hide" width="200"  height="100">
                            </c:if>
                            <c:if test="${requestScope.news.type == 3 || requestScope.news.type == 4}">
                                <img src="" id="group-img" class="hide"  width="100"  height="100" >
                            </c:if>
                        </div>
                    </div>
                    <c:if test="${requestScope.news.type != 2}">
                        <div class="form-group">
                            <label class="control-label col-md-2 col-sm-4 ui-sortable" >跳转链接：</label>
                            <div class="col-md-6 col-sm-6 ui-sortable p-t-22">
                                <span >${requestScope.news.jumpUrl }</span>
                            </div>
                        </div>
                    </c:if>
                    <div class="form-group">
                        <label class="control-label col-md-2 col-sm-4 ui-sortable" >副标题：</label>
                        <div class="col-md-6 col-sm-6 ui-sortable p-t-22">
                            <span >${requestScope.news.descIntroduction }</span>
                        </div>
                    </div>
                    <c:if test="${requestScope.news.type == 2}">
                      <div class="form-group">
                          <label class="control-label col-md-2 col-sm-4 ui-sortable" >内容：</label>
                          <div class="col-md-6 col-sm-6 ui-sortable">
                              <span>${requestScope.news.content}</span>
                          </div>
                      </div>
                    </c:if>
                    <div class="form-group">
                        <label class="control-label col-md-2 col-sm-4 ui-sortable">顺序号：</label>
                        <div class="col-md-6 col-sm-6 ui-sortable p-t-22">
                            <span >${requestScope.news.oderNum }</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2 col-sm-4 ui-sortable" >状态：</label>
                        <div class="col-md-6 col-sm-6 ui-sortable p-t-22">
                            <c:if test="${0 == requestScope.news.status}"><span >下架</span></c:if>
                            <c:if test="${1 == requestScope.news.status}"><span >上架</span></c:if>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2 col-sm-4 ui-sortable"></label>
                        <div class="col-md-6 col-sm-6 ui-sortable p-t-22" style="margin-bottom:60px;">
                            <input type="button" value="返回" class="btn btn-primary" onclick="javascript: history.back(-1);"/>
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
    <script type="text/javascript">
        var imgUrl = '${requestScope.news.imgeUrl}';
        imgUrl===""?$('#group-img').attr('display','hide'):$('#group-img').attr('src', fileRootUrl+imgUrl).removeClass();
    </script>
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
                                cb(data);
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
                        $('#news.imgeUrl').val(imgUrl);
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