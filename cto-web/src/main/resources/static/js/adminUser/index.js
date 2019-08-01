/**
 * @description
 * @author Zhang Yongwei
 * @date 2019-06-06 13:44:13
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
        url: "/adminUser/page",//提交连接
        model: $("#skillModel"),
        pageModel:$("#pageModel"),
        data: {"pageCount":$("#pageCount").val()},
        pageNum: page,
        callback: function (result) {
            layer.closeAll('loading');
            var num = result.startIndex;
            if (result != null && result.rows != null && result.rows.length > 0) {
                $("#itemsPanel tr").each(function () {
                    //渲染自动排课
                    var status = $(this).find("[key=status]").html();
                    if (status == 0) {
                        $(this).find("[key=status]").html("锁定");
                    } else if (status == 1) {
                        $(this).find("[key=status]").html("正常");
                    }

                    var userType = $(this).find("[key=userType]").html();
                    if (userType == 1) {
                        $(this).find("[key=userType]").html("个人");
                    } else if (userType == 2) {
                        $(this).find("[key=userType]").html("餐饮机构");
                    } else if (userType == 3) {
                        $(this).find("[key=userType]").html("经销商");
                    }
                    //为序号赋值
                    $(this).find("[key=num]").append(num);
                    num ++ ;
                });
            } else {
                $("#itemsPanel").html("<tr><td style='text-align: center' colspan='8'>暂无数据</td>></tr>")
            }
        }//回调方法
    });
}

//进入修改页面
$(document).on("click", ".edit", function () {
    var id = $(this).parent().find("[key=id]").val();
    window.location.href = "/adminUser/edit?id=" + id;
});
//删除
$(document).on("click", ".delete", function () {
    var id = $(this).parent().find("[key=id]").val();
    layer.confirm('您确定要删除吗？', {
        btn: ['确定', '关闭'],//按钮
        title: false,
        closeBtn: false
    }, function () {
        $.ajax({
            type: 'post',
            dataType: 'json',
            url: "/adminUser/delete",
            data: {"id": id},
            success: function (result) {
                if (result.code == 0) {
                    $("#closeBtnLab").click();
                    layer.msg("操作成功");
                    loadPage();
                } else {
                    layer.msg("操作失败");
                }
            }
        })
    })
});
