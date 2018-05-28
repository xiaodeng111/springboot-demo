/**
 * Created by dev002 on 2017/10/16.
 */
/***
 * 提示
 */
var Common = {
    confirm: function (params) {
        var model = $(window.document.getElementById("common-confirm"));
        model.find("#confirmHeader").html(params.title)
        model.find("#confirmContent").html(params.message);

        //每次都将监听先关闭，防止多次监听发生，确保只有一次监听
        model.find("#confirmBtn_yes").off("click");
        model.find("#confirmBtn_cancel").off("click");

        model.off('hidden.bs.modal');
        model.find("#confirmBtn_yes").on("click", function () {
            model.on('hidden.bs.modal', function () {
                params.operate(true);
            });
            model.modal('hide');

        });
        model.find("#confirmBtn_cancel").on("click", function () {
            model.on('hidden.bs.modal', function () {
                params.operate(false);
            });
            model.modal('hide');
        });
        window.document.onkeydown = function (e) {
            var ev = document.all ? window.event : e;
            if (ev.keyCode == 13) {
                model.on('hidden.bs.modal', function () {
                    params.operate(true);
                });
                model.modal('hide');
            }
        };
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
