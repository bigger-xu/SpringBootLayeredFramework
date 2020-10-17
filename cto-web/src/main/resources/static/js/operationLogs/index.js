/**
 * @description
 * @author 594cto版权所有
 * @date 2020-10-17 14:39:34
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
        url: "/operationLogs/page",//提交连接
        model: $("#skillModel"),
        pageModel:$("#pageModel"),
        data: {"description":$("#description").val(),"type":$("#type").val(),"ipAddress":$("#ipAddress").val(),"userName":$("#userName").val(),"pageSize":$("#pageSize").val()},
        pageNum: page,
        callback: function (result) {
            layer.closeAll('loading');
            if (result != null && result.records != null && result.records.length > 0) {
                $("#itemsPanel tr").each(function () {

                });
            } else {
                $("#itemsPanel").html("<tr><td style='text-align: center' colspan='8'>暂无数据</td>></tr>")
            }
        }//回调方法
    });
}

//进入修改页面
$(document).on("click", ".edit", function () {
    var uuid = $(this).parent().find("[key=uuid]").val();
    window.location.href = "/operationLogs/edit?uuid=" + uuid;
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
                url: "/operationLogs/delete",
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
