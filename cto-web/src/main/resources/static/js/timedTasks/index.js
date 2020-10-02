/**
 * @description
 * @author 594cto版权所有
 * @date 2020-10-01 23:44:41
 * @version 1.0
 */
$(function () {
    loadPage();
});
// 选择每页显示多少条
$("#pageCount").change(function(){
    $("#pageCountParam").val($(this).val());
    loadPage();
})
function loadPage(page) {
    layer.load(1, {shade: [0.5, '#000']});
    $("#itemsPanel").skillAjax({
        url: "/timedTasks/page",//提交连接
        model: $("#skillModel"),
        pageModel:$("#pageModel"),
        data: {"pageSize":$("#pageSize").val()},
        pageNum: page,
        callback: function (result) {
            layer.closeAll('loading');
            var num = result.startIndex;
            if (result != null && result.records != null && result.records.length > 0) {
                $("#itemsPanel tr").each(function () {
                    //渲染自动排课
                    var status = $(this).find("[key=status]").html();
                    if (status == 0) {
                        $(this).find("[key=status]").html("否");
                        $(this).find(".start").show();
                        $(this).find(".push").hide();
                    } else if (status == 1) {
                        $(this).find("[key=status]").html("是");
                        $(this).find(".start").hide();
                        $(this).find(".push").show();
                    }
                    //为序号赋值
                    $(this).find("[key=num]").append(num);
                    num ++ ;
                });
            } else {
                $("#itemsPanel").html("<tr><td style='text-align: center' colspan='9'>暂无数据</td>></tr>")
            }
        }//回调方法
    });
}

//进入修改页面
$(document).on("click", ".edit", function () {
    var uuid = $(this).parent().find("[key=uuid]").val();
    window.location.href = "/timedTasks/edit?uuid=" + uuid;
});
//进入修改页面
$(document).on("click", ".start", function () {
    var uuid = $(this).parent().find("[key=uuid]").val();
    $.ajax({
        type: 'post',
        dataType: 'json',
        url: "/timedTasks/editStatus",
        data: {"uuid": uuid,"status":1},
        success: function (result) {
            if (result.code == 0) {
                $("#closeBtnLab").click();
                toastr.success('操作成功','系统通知!');
                loadPage();
            } else {
                toastr.error('操作失败','系统通知!');
            }
        }
    })
});
//进入修改页面
$(document).on("click", ".push", function () {
    var uuid = $(this).parent().find("[key=uuid]").val();
    $.ajax({
        type: 'post',
        dataType: 'json',
        url: "/timedTasks/editStatus",
        data: {"uuid": uuid,"status":0},
        success: function (result) {
            if (result.code == 0) {
                $("#closeBtnLab").click();
                toastr.success('操作成功','系统通知!');
                loadPage();
            } else {
                toastr.error('操作失败','系统通知!');
            }
        }
    })
});
//删除
$(document).on("click", ".delete", function () {
    var uuid = $(this).parent().find("[key=uuid]").val();
    swal({
        title: "您确定要删除吗？",
        text: "您将无法恢复这个操作！",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#1ab394",
        confirmButtonText: "确定",
        cancelButtonText: "取消"
    },
    function (isConfirm) {
        if (isConfirm) {
            $.ajax({
                type: 'post',
                dataType: 'json',
                url: "/timedTasks/delete",
                data: {"uuid": uuid},
                success: function (result) {
                    if (result.code == 0) {
                        $("#closeBtnLab").click();
                        toastr.success('操作成功','系统通知!');
                        loadPage();
                    } else {
                        toastr.error('操作失败','系统通知!');
                    }
                }
            })
        }
    });
});
