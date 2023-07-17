/**
 *
 * @author Zhang Yongwei
 * @date 2019-12-30
 * @version 1.0
 */
function login(){
    var email = $('#username').val();
    if(isBlank(email)){
        layer.msg("请输入邮箱");
        return;
    }
    if(!checkEmail(email)){
        layer.msg("请输入正确的邮箱");
        return;
    }
    var pwd = $('#password').val();
    if(isBlank(pwd)){
        layer.msg("请输入密码");
        return;
    }
    if(!checkPassword(pwd)){
        layer.msg("请输入6-16位密码");
        return;
    }
    var jsonObject = $("#dataForm").serializeObject();
    var json = JSON.stringify(jsonObject);
    var url = "/login";
    //发送请求
    ctoAjaxPost(url,json,function(data){
            if(data != null && data.code == 0){
                layer.msg("登录成功");
                setTimeout(function(){
                    window.location.href = '/';
                },1500)
            }else{
                layer.msg(data.message);
            }
    });
}