function saveOrUpdate() {
    var flag = $("#dataForm").valid(3);
    var data = $("#dataForm").serialize();
    if (flag) {
        layer.load(1, {shade: [0.5,'#000']});
        $.ajax({
            data: data,//提交的数据
            url: "/roleUser/saveOrUpdate",//提交连接
            type: 'post',
            dataType: 'json',
            success: function (result) {
                layer.closeAll('loading');
                if (result.code == 0) {
                    window.location.href = "/roleUser";
                }else {
                    layer.msg(result.msg);
                }
            }//回调方法
        });
    }
}