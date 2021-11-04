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
        var page = $(this).parent().parent().find(".active").html();
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
        var page = $(this).parent().parent().find(".active").html();
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
});

function resetValue(){
    $("#searchForm").clearVal();
    loadPage(1);
}