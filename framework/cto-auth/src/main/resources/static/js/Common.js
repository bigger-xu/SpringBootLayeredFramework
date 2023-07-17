/**
 *
 * @author Zhang Yongwei
 * @date 2019-12-30
 * @version 1.0
 */
$.fn.serializeObject = function() {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [ o[this.name] ];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
}
function ctoAjaxPost(url,data,callback){
    // var sign = SIGN();
    $.ajax({
        type : 'POST',
        headers:{"Content-Type":"application/json;charset=UTF-8"},
        url : url,
        data : data,
        dataType : 'json',
        error:function(data){
            layer.msg(data.message);
        },
        success :callback
    });
};

/**
 * 邮箱格式
 * @param obj
 * @returns {boolean}
 */
function checkEmail(obj){
    var reg = /^(\w)+(\.\w+)*@(\w)+((\.\w{2,4}){1,3})$/;
    if(reg.test(obj)){
        return true;
    }
}

/**
 * isBlank
 * @param str
 * @returns {boolean}
 */
function isBlank(str){
    if( str == null || str == "" )
        return true ;
    return false ;
};

/**
 * 密码格式
 * @param obj
 * @returns {boolean}
 */
function checkPassword(obj){
    var reg = /^[a-zA-Z0-9]{6,16}$/;
    if(reg.test(obj)){
        return true;
    }
}
