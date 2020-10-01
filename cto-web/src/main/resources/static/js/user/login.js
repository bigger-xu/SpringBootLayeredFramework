var localStorage=window.localStorage;
localStorage.setItem("selectedId", "");
localStorage.setItem("selectedParentId", "");

function toLogin(){
    var userName = $("#userName").val();
    var password = $("#password").val();
    var code = $("#code").val();
    if(userName == null || userName == ''){
        layer.msg("请输入用户名");
        return;
    }
    if(password == null || password == ''){
        layer.msg("请输入密码");
        return;
    }
    if(code == null || code == ''){
        layer.msg("请输入验证码");
        return;
    }
    layer.load(1, {shade: [0.5,'#000']});
    $.ajax({
        type: 'post',
        dataType: 'json',
        url: "/doLogin",
        data: {"userName": userName,"password":password,"code":code},
        success: function (result) {
            if (result.code == 0) {
                setTimeout(function(){
                    layer.closeAll('loading');
                    window.location.href="/";
                },1000)
            } else {
                layer.closeAll('loading');
                layer.msg(result.msg);
                reloadCode();
            }
        }
    })
}

function reloadCode() {
    $("#validateCodeImg").attr("src", "/code?data=" + new Date() + "");
}