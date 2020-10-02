/**
 * @description
 * @author Zhang Yongwei
 * @date 2019-06-06 13:44:14
 * @version 1.0
 */
$(function () {
    loadPage();
});
// 选择每页显示多少条
$("#pageSize").change(function(){
    loadPage();
})
function loadPage(page) {
    layer.load(1, {shade: [0.5, '#000']});
    $("#itemsPanel").skillAjax({
        url: "/role/page",//提交连接
        model: $("#skillModel"),
        pageModel:$("#pageModel"),
        data: {"pageSize":$("#pageSize").val()},
        pageNum: page,
        callback: function (result) {
            layer.closeAll('loading');
            if (result != null && result.records != null && result.records.length > 0) {
                $("#itemsPanel tr").each(function () {
                    //渲染自动排课
                    var status = $(this).find("[key=status]").html();
                    if (status == 0) {
                        $(this).find("[key=status]").html("禁用");
                    } else if (status == 1) {
                        $(this).find("[key=status]").html("启用");
                    }
                });
            } else {
                $("#itemsPanel").html("<tr><td style='text-align: center' colspan='6'>暂无数据</td>></tr>")
            }
        }//回调方法
    });
}

//进入修改页面
$(document).on("click", ".edit", function () {
    var id = $(this).parent().find("[key=id]").val();
    window.location.href = "/role/edit?id=" + id;
});
//删除
$(document).on("click", ".delete", function () {
    var id = $(this).parent().find("[key=id]").val();
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
                url: "/role/delete",
                data: {"id": id},
                success: function (result) {
                    layer.closeAll('loading');
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
