/**
 * Created by Zhang Yongwei on 2017/4/21.
 */
$(function(){
    $(document).on("click",".firstSmSkill",function(){
        var id = $(this).parent().parent().attr("id");
        loadPage(1,id);
    });
    $(document).on("click",".prevSmSkill",function(){
        var id = $(this).parent().parent().attr("id");
        var page = $(this).parent().parent().find(".active").text();
        page = parseInt(page);
        if(page != null && page != '' && page != undefined && page !=1){
            var num = page - 1;
            loadPage(num,id);
        }
    });
    $(document).on("click",".SmSkill",function(){
        var id = $(this).parent().parent().attr("id");
        var page = $(this).parent().parent().parent().find(".active").html();
        page = parseInt(page);
        var num = $(this).html();
        if(page != null && page != '' && page != undefined && num != null && num != '' && num != undefined){
            if(page != num){
                loadPage(num,id);
            }
        }
    });
    $(document).on("click",".nextSmSkill",function(){
        var id = $(this).parent().parent().attr("id");
        var page = $(this).parent().parent().find(".active").text();
        page = parseInt(page);
        var lastNum = $(this).parent().parent().find(".smSkillPageNum").html();
        lastNum = parseInt(lastNum);
        if(page != null && page != '' && page != undefined){
            var num =  page + 1;
            if(num <= lastNum){
                loadPage(num,id);
            }
        }
    });
    $(document).on("click",".lastSmSkill",function(){
        var id = $(this).parent().parent().attr("id");
        var lastNum = $(this).parent().parent().find(".smSkillPageNum").html();
        loadPage(lastNum,id);
    });

    var localStorage=window.localStorage;
    var selectedId = localStorage.getItem("selectedId");
    var selectedParentId = localStorage.getItem("selectedParentId");
    if ($.isEmptyObject(selectedId) || $.isEmptyObject(selectedParentId)) {
        $("#homeMenu").addClass("active");
    } else {
        $('#'+selectedId+'').attr("class", "active");
        $('#'+selectedParentId+'').find("ul").addClass("in");
        $('#'+selectedParentId+'').attr("class", "active");
    }

});
// 选择每页显示多少条
$("#pageSize").change(function(){
    loadPage();
})

function resetValue(){
    $("#searchForm").clearVal();
    loadPage(1);
}
function setSelectMenuId(menuId, parentId,url){
    var localStorage=window.localStorage;
    localStorage.setItem("selectedId", menuId);
    localStorage.setItem("selectedParentId", parentId);
    window.location.href = url;
}