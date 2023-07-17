$(function(){
    buildTree();
});
//加载菜单树
function buildTree(){
    var zTreeObj;
    var setting={
        view: {
            showLine: false,
            selectedMulti: false,
            showIcon: false
        },
        check: {
            enable: true,
            chkStyle: "checkbox",
            chkboxType: { "Y": "ps", "N": "ps" }
        }
    };
    var roleId = $('#dataForm').find("input[name=id]").val();
    $.ajax({
        data: {"roleId":roleId},
        url: "/menu/getMenuTree",
        type: 'post',
        contentType: "application/x-www-form-urlencoded;charset=UTF-8",
        success: function (result) {
            if (result.status=="FAILURE") {
                layer.alert("查询失败");
            } else {
                var zNodes=result;
                zTreeObj=$.fn.zTree.init($("#menu-tree"), setting, zNodes);
            }
        }
    });
}
function saveOrUpdate() {
    var flag = $("#dataForm").valid(3);
    var data = $("#dataForm").serialize();
    if (flag) {
        var treeObj=$.fn.zTree.getZTreeObj("menu-tree");
        var nodes=treeObj.getCheckedNodes();
        var menuIds=[];
        if(nodes){
            for(var i=0;i<nodes.length;i++){
                if(nodes[i].attributes.type==0){
                    menuIds.push(nodes[i].attributes.id);
                }
            }
        }
        data += "&roleIds="+menuIds;
        layer.load(1, {shade: [0.5,'#000']});
        $.ajax({
            data: data,//提交的数据
            url: "/role/saveOrUpdate",//提交连接
            type: 'post',
            dataType: 'json',
            success: function (result) {
                layer.closeAll('loading');
                if (result.code == 0) {
                    window.location.href = "/role";
                }else {
                    toastr.error(result.msg,'系统通知!')
                }
            }//回调方法
        });
    }
}