/**
 * Created by Lee on 2017/5/26.
 */

var _window = window.parent || window;


$(function () {
    $.extend({


        getUrlParam: function (name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return decodeURI(r[2]);
            return null;
        }
    })

    var contentheight = $(window.parent.document.getElementsByTagName('body')).height() - $(window.parent.document.getElementsByClassName('.header')).height();
    $(window.parent.document.getElementById('iframe-content')).attr("height", contentheight);

    $('.sub-menu').find('li').on('click', function () {
        $('.has-sub').removeClass('active');
        $('.sub-menu').find('li').removeClass('active');
        $(this).addClass('active');
        $('.expand').addClass('active');
    })
});
var datePickerLang = {
    "applyLabel": "确认",
    "cancelLabel": "取消",
    "fromLabel": "起始时间",
    "toLabel": "结束时间",
    "weekLabel": "W",
    "format": 'YYYY-MM-DD',
    "daysOfWeek": [
        "一",
        "二",
        "三",
        "四",
        "五",
        "六",
        "日"
    ],
    "monthNames": [
        "一月",
        "二月",
        "三月",
        "四月",
        "五月",
        "六月",
        "七月",
        "八月",
        "九月",
        "十月",
        "十一月",
        "十二月"
    ]
};

var Common = {
    confirm: function (params) {
        var model = $(_window.document.getElementById("common-confirm"));
        model.find("#confirmHeader").html(params.title)
        model.find("#confirmContent").html(params.message);

        //每次都将监听先关闭，防止多次监听发生，确保只有一次监听
        model.find("#confirmBtn_yes").off("click");
        model.find("#confirmBtn_cancel").off("click");

        model.off('hidden.bs.modal');
        model.find("#confirmBtn_yes").on("click", function () {
            model.on('hidden.bs.modal', function () {
                params.operate(true);
            })
            model.modal('hide');

        });
        model.find("#confirmBtn_cancel").on("click", function () {
            model.on('hidden.bs.modal', function () {
                params.operate(false);
            })
            model.modal('hide');
        });
        _window.document.onkeydown = function (e) {
            var ev = document.all ? window.event : e;
            if (ev.keyCode == 13) {
                model.on('hidden.bs.modal', function () {
                    params.operate(true);
                })
                model.modal('hide');
            }
        }
        model.modal('show');

    },
    changeCheckbox: function (value, name) {
        if (value === 1) {
            $('input[name=' + name + ']').attr('type', 'radio');
        } else {
            $('input[name=' + name + ']').attr('type', 'checkbox');
        }

    },
    warning: function (params) {
        // warningModel = '<div class=warningBg" id="warning-modal">' +
        //     '<div class="warningBg" id="warning-modal"><div class="modal-dialog modal-sm"> <div class="modal-content"> ' +
        //     '<div class="modal-header text-center" id="warning-title"></div>' +
        //     ' <div class="modal-body text-center" id="warning-content"></div> ' +
        //     '<div class="confirm-btn"> <button type="button" class="btn btn-default" id="knowIt">好的</button></div></div></div></div>';
        var warningModel = "<div class='modal fade bs-example-modal-sm' tabindex='-1' role='dialog' id='warning-modal'>" +
            "<div class='modal-dialog modal-sm' role='document'><div class='modal-content'><div class='modal-header text-center' id='warning-title'>" +
            "</div><div class='modal-body text-center p-15' id='warning-content'></div>" +
            "<div class='modal-footer no-border'><button type='button' class='btn btn-default' data-dismiss='modal'>好的</button></div></div></div></div>";
        $("body").append(warningModel);
        $("#warning-modal").modal("show");
        $("#warning-title").html(params.title);
        $("#warning-content").html(params.message);
    },
    removeStyle: function (groupName) {
        $.each($("input[data-parsley-group='" + groupName + "']"), function (i, item) {
            $(item).removeClass("parsley-error");
            $(item).removeClass("parsley-success");
            $(item).parent('.parsley-error').removeClass("parsley-error");
            $(item).parent('.parsley-success').removeClass("parsley-success");
        })
    }
}
/**
 * iframe内页面跳转
 * @param url相对首页地址
 */
function openNewPage(url) {
    if (!!window.parent.document.getElementById('iframe-content')) {
        window.parent.document.getElementById('iframe-content').src = url;
    } else {
        window.location.href = url;
    }
}

/***
 *在模态框外点击时取消关闭模态框功能
 */
$(".modal").attr("data-backdrop", "static");

$(".modal").on('show.bs.modal', function () {
    var frmParsley = $(this).find("form[data-parsley-validate]");
    if (frmParsley.length > 0) {
        $(this).find('form').parsley().reset();
    }

});

/***
 * 返回验证结果
 * @param group 组名
 */
var getParsleyResult = function (group) {
    var parsleyResult = true;
    var firstFocus;
    var parsleyArray = $("[data-parsley-group=" + group + "]").parsley();
    if (parsleyArray instanceof Array == false) {
        parsleyArray = [parsleyArray];
    }
    parsleyArray.forEach(function (item) {
        if(!item)return;
        item.validate({group: group, force: true});
        item.on('field:success', function () {
            $(item.$element[0].parentElement).removeClass('parsley-success');
            $(item.$element[0]).removeClass('parsley-success');
        });
        item.on('field:error', function () {
            $(item.$element[0].parentElement).removeClass('parsley-error');
            $(item.$element[0]).removeClass('parsley-error');
        });
        if (!item.isValid()) {
            if (!firstFocus) {
                firstFocus = item.$element;
            }
            console.log(item);
            parsleyResult = false;
            return false;
        }
    });
    if (!!firstFocus) {
        firstFocus.focus();
    }
    return parsleyResult;
};

/***
 * 日期转换时间戳
 */
var convertToTimeSpan = function (date) {
    return Date.parse(new Date(date)) / 1000;
};

/**
 * 时间戳转日期格式    YY-MM-DD hh:mm
 */

var convertToDateTime = function (timeString) {
    var date = new Date(typeof timeString == 'string'?parseInt(timeString):timeString); //获取一个时间对象  注意：如果是uinx时间戳记得乘于1000。比如php函数time()获得的时间戳就要乘于1000
    var Y = date.getFullYear() + '-';
    var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    var D = date.getDate() + ' ';
    var h = date.getHours() + ':';
    var m = date.getMinutes();
    return Y+M+D+h+m;
};

var getNowDate = function (only) {
    var date = new Date();
    var Y = date.getFullYear() + '-';
    var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    var D = date.getDate() ;
    var h = ' '+date.getHours() + ':';
    var m = date.getMinutes();
    if(!!only && only === 'onlyDay'){
        return Y+M+D;
    }else{
        return Y+M+D+h+m;
    }
};

/**
 * 根据图片的路径转化
 * @param text 全部文本的字符
 * @param baseURL 要转换的地址
 * @param type true,绝对路径转为相对路径，false，反之
 */
var convertImgSrc = function (text, baseURL, type , input) {
    var changeStatus = false;
    function convertSrc(body) {
        if (!!body) {
            if (!!type) {
                //绝对路径转为相对路径
                if (body.indexOf(baseURL) == 0) {
                    changeStatus = true;
                    return body.replace(baseURL, '');
                }
            }
            else {
                if (body.indexOf(baseURL) == -1) {
                    changeStatus = true;
                    return baseURL + body;
                }
            }
        }
    }

    // var result = '';
    var $content = $('<div></div>');
    $content.html(text);
    $content.find('img').each(function () {
        $(this).attr('src', convertSrc($(this).attr('src')));
        $(this).attr('_src', convertSrc($(this).attr('_src')));
        $(this).removeAttr('data-latex');
    })

    if(!!input){
        $content.find('input').each(function () {
            $(this).attr('disabled','disabled');
            $(this).removeAttr('placeholder');
            $(this).css({'border-left':'0','border-right':'0','border-top':'0','background':'transparent','border-bottom':'1px solid'});
            changeStatus = true;
        })
    }
    // if ($content.is('img')) {
    //     $content.attr('src', convertSrc($content.attr('src')));
    //     $content.attr('_src', convertSrc($content.attr('_src')));
    // }
    // $content.siblings('img').each(function () {
    //     $(this).attr('src', convertSrc($(this).attr('src')));
    //     $(this).attr('_src', convertSrc($(this).attr('_src')));
    // })
    // $content.each(function () {
    //     result += $(this)[0].outerHTML;
    //     console.log($(this)[0].outerHTML);
    // })
    if (changeStatus) {
        console.log('convertURLTO:' + $content.html());
    }
    return changeStatus ? $content.html() : text;
}

/**
 * 检查编辑器路径是否需要设置为绝对路径
 * @param editor
 */
function checkUEeditorImgPath(editor) {
    var content = editor.getContent();
    if(content==='') return;
    if (content.indexOf('data:image/png;base64') == -1) {
        // if(content.indexOf('ueditor/themes/default/images/spacer.gif')>-1){
        //     // editor.fireEvent("selectionchange");
        //     editor.focus();
        // } else {
            var contentNew = convertImgSrc(content, quesRootUrl, false);
            if (!!contentNew && content != contentNew) {
               editor.setContent(contentNew);

            // }
        }
    }
}

var tmpKnowleage = [];

/**
 * 检查保存是否在请求中
 * @param  0:检查    1:设置为doing    2：设置成done
 */

function checkSave(param) {
    switch (param){
        case 0:
            return (!!$('.saveBtn').attr('saveClass') && $('.saveBtn').attr('saveClass') === 'doing');
            break;
        case 1:
            $('.saveBtn').attr('saveClass','doing');
            $('.saveBtn').attr('disabled','disabled');
            break;
        default:
            $('.saveBtn').attr('saveClass','done');
            $('.saveBtn').removeAttr('disabled');
            break;
    }
}

var checkSubmitFlg = false;

function checkSubmit(){

    if(checkSubmitFlg ==true){
        $('.saveBtn').attr('disabled','disabled');
        return false; //当表单被提交过一次后checkSubmitFlg将变为true,根据判断将无法进行提交。

    }

    checkSubmitFlg ==true;
    $('.saveBtn').attr('disabled','none');

    return true;

}


/**
 * 所有列表页的操作按钮组
 * @param  t:dom对象
 */
function changeButton(t){
    if($(t).hasClass('open')){
        $(t).removeClass('open')
    }else{
        $('.btn-group').removeClass('open');
        $(t).addClass('open')
    }
}
