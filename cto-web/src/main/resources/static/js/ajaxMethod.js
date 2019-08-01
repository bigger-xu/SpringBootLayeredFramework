/**
 * Created by Zhang Yongwei on 2017/4/21.
 */
//标签值填入对应标签属性
var key = "key";
var hideKey = "hdkey";
(function ($) {
    $.fn.extend({
        /**
         * 从对象里获取key 下的值
         * var obj = {"t1":"1","t2":"text"} ;
         * var value = $.attrForObj(obj,"t2") ;
         * 【value 的值 "text"】
         * @param obj
         * @param key
         * @returns {*}
         */
        attrForObj: function (obj, key) {
            var value = obj;
            var objType = null == obj ? "null" : typeof(obj);
            if (objType == "object" && null != key && $.trim(key) != "") {
                $.each($.trim(key).split('.'), function (point, valKey) {
                    if (null != value) {
                        value = value[valKey];
                    } else {
                        return false;
                    }
                });
            }
            return value;
        },
        /**
         * 异步请求
         */
        skillAjax: function (params) {
            var thisElem = $(this);
            var defaultParams = {
                data: "",
                url: "",
                contentType: "application/x-www-form-urlencoded;charset=UTF-8",
                type: "post",
                pageNum:null,
                async: true,//false 同步，true 异步
                model: "",
                pageModel:"",
                callback: null
            };
            $.extend(defaultParams, params);
            var nowPageNum = thisElem.data('pageNum') ;
            var selectNum = thisElem.parent().parent().find(".pagination .active").html();
            if(isNaN(parseInt(nowPageNum))){
                if(isNaN(parseInt(selectNum))){
                    nowPageNum = 1 ;
                }else{
                    nowPageNum = selectNum;
                }
            }else{
                nowPageNum = parseInt(nowPageNum) ;
            }
            nowPageNum = defaultParams.pageNum||nowPageNum ;
            if(nowPageNum<1){
                nowPageNum = 1 ;
            }
            if(defaultParams.url==null || $.trim(defaultParams.url)==""){
                alert("url不能为空");
                return ;
            }
            if(defaultParams.data == null){
                defaultParams.data = "" ;
            }
            if( typeof defaultParams.data == "string"){
                defaultParams.data = defaultParams.data.replace(/pageNum=[0-9]*/,"");
                defaultParams.data += "&pageNum="+nowPageNum;
                defaultParams.data = defaultParams.data.replace(/&&+/g, '&').replace(/&+$/, '').replace(/^&+/, '').replace(/=null/g, '=')
            }else{
                defaultParams.data['pageNum'] = nowPageNum ;
            }
            $.ajax({
                // 后台处理程序
                url: defaultParams.url,
                // 数据发送方式
                type: defaultParams.type,
                // 接受数据格式
                dataType: "json",
                // false为同步模式
                async: defaultParams.async,
                // 要传递的数据
                data: defaultParams.data,
                //类型
                contentType: defaultParams.contentType,
                // 回传函数
                success: function (result) {
                    thisElem.html("");
                    if(null!=defaultParams.callback){
                        if (null != result.rows) {
                            var data = result.rows;
                            for(var item in data){
                                var modelClone = $(defaultParams.model).eq(0).clone().removeAttr("id");
                                modelClone.find('[' + key + '],[' + hideKey + ']').each(function () {
                                    var value = $(this).attr(key);
                                    var res = $(this).attrForObj(data[item],value);
                                    if($(this).is('input')){
                                        $(this).val(res);
                                    }else{
                                        $(this).html(res);
                                    }
                                    $(this).formatDate()
                                });
                                thisElem.append(modelClone);
                            }
                        }
                        var pageCustomize = new Object() ;
                        pageCustomize['prev'] = '<li><a href="javaScript:void(0);" class="firstSmSkill">首页</a></li><li><a href="javaScript:void(0);" class="prevSmSkill">上一页</a></li>'; //上一个
                        pageCustomize['btn'] = '<li><a href="javaScript:void(0);" pageNumPanel="true" class="SmSkill">1</a></li>';//中间按钮
                        pageCustomize['moreBtn'] = '<li><a pageNumPanel="true">...</a></li>'; //更多
                        pageCustomize['next'] ='<li><a href="javaScript:void(0);" class="nextSmSkill">下一页</a></li><li><a href="javaScript:void(0);" class="lastSmSkill">末页</a></li>';//下一个
                        pageCustomize['checkedClass'] = 'active';// 选中class
                        pageCustomize['orderBtnBefore'] = ['<li><a href="javaScript:void(0);">共<font class="smSkillPageNum">'+result.totalPage+'</font>页</a></li>'];
                        var _start = result["page"] ;
                        $(defaultParams.pageModel).customPage(result,_start,6,"reloadPage",pageCustomize);
                        defaultParams.callback(result);
                    }
                }
            });
        },
        /**
         *
         * @param pageResult 查询结果
         * @param checkedNum 选中页
         * @param maxBtn 最大按钮数
         * @param callback 回调
         * @private
         */
        customPage :function(pageResult,checkedNum,maxBtn,callback,pageCustomize){
            var pagePanel = $(this);
            var pageCount =pageResult["totalPage"] ;
            var prev = pageCustomize['prev'];
            var btn = pageCustomize['btn'];
            var moreBtn = pageCustomize['moreBtn'];
            var next = pageCustomize['next'];
            var checkedClass = pageCustomize['checkedClass'] ;
            if(pageCount>1){
                pagePanel.show() ;
                checkedNum = parseInt(checkedNum) ;
                var tempCallback = function(num){
                }
                if($.isFunction(callback)){
                    tempCallback = eval(callback) ;
                }
                pagePanel.empty() ;
                prev = null==prev?null:$(prev).clone() ;
                btn = null==btn?null:$(btn).clone() ;
                moreBtn = null==moreBtn?null:$(moreBtn).clone() ;
                next = null==next?null:$(next).clone() ;
                var beginPageNum = 0 ;
                var endPageNum = 0 ;
                var halfLength = parseInt(maxBtn/2) ;

                if(maxBtn>pageCount){
                    endPageNum = pageCount ;
                }else if((checkedNum+(halfLength))>pageCount){
                    endPageNum = pageCount ;
                }else{
                    endPageNum = (checkedNum+(halfLength)) ;
                }
                if((endPageNum-maxBtn+1)<1){
                    beginPageNum = 1 ;
                }else{
                    beginPageNum = endPageNum-maxBtn+1 ;
                }

                if((beginPageNum+maxBtn-1)>pageCount){
                    endPageNum = pageCount ;
                }else{
                    endPageNum = beginPageNum+maxBtn-1 ;
                }
                if(null!=prev){
                    if(checkedNum==1){
                        prev.removeAttr("href");
                        prev.find("[href]").removeAttr("href");
                        prev.css("cursor","default");
                        if(prev.is("[hideFirst]")){
                            prev.hide();
                        }
                    }else{
                        prev.click(function(){
                            tempCallback(checkedNum-1);
                        });
                    }
                    pagePanel.append(prev);
                }

                if(null!=moreBtn && beginPageNum>1){
                    var prevMore = moreBtn.clone() ;
                    prevMore.click(function(){
                        tempCallback(beginPageNum-1);
                    });
                    pagePanel.append(prevMore);
                }
                for(var i=beginPageNum;i<=endPageNum;i++){
                    var btnClone = btn.clone() ;
                    if(btnClone.is("[pageNumPanel]")){
                        btnClone.text(i);
                    }else{
                        btnClone.find("[pageNumPanel]").text(i);
                    }
                    btnClone.data("pageNum",i) ;

                    if(i==checkedNum){
                        if(null!=checkedClass){
                            btnClone.children().eq(0).addClass(checkedClass);
                        }
                        btnClone.removeAttr("href");
                        btnClone.find("[href]").removeAttr("href");
                        btnClone.css("cursor","default");
                    }else{
                        btnClone.click(function(){
                            tempCallback($(this).data("pageNum"));
                        });
                    }
                    pagePanel.append(btnClone);
                }
                if(null!=moreBtn && endPageNum<pageCount){
                    var nextMore = moreBtn.clone() ;
                    nextMore.click(function(){
                        tempCallback(endPageNum+1);
                    });
                    pagePanel.append(nextMore);
                }
                if(null!=next){
                    if(checkedNum==endPageNum){
                        next.removeAttr("href");
                        next.find("[href]").removeAttr("href");
                        next.css("cursor","default");
                        if(next.is("[hideLast]")){
                            next.hide();
                        }
                    }else{
                        next.click(function(){
                            tempCallback(checkedNum+1);
                        });
                    }
                    pagePanel.append(next);
                }
                if(null!= pageCustomize['orderBtnBefore'] && pageCustomize['orderBtnBefore'].length>0){
                    $.each(pageCustomize['orderBtnBefore'],function(point,val){
                        var thisElem = $(val).clone();
                        thisElem.setValByKey(pageResult) ;
                        if(checkedNum==1 && thisElem.is("[hideFirst]")){
                            thisElem.hide();
                        }
                        //前追加
                        //pagePanel.prepend(thisElem);
                        pagePanel.append(thisElem);
                    });
                }
                if(null!= pageCustomize['orderBtnAfter'] && pageCustomize['orderBtnAfter'].length>0){
                    $.each(pageCustomize['orderBtnAfter'],function(point,val){
                        var thisElem = $(val).clone();
                        thisElem.setValByKey(pageResult) ;
                        if(checkedNum==endPageNum && thisElem.is("[hideLast]")){
                            thisElem.hide();
                        }
                        pagePanel.append(thisElem);
                    });
                }
                pagePanel.next().show();
            }else{
                pagePanel.hide() ;
                pagePanel.next().hide();
            }
        },
        /**
         * 判断是否存在方法
         * @param funOrFunName
         * @returns {boolean}
         */
        isFunction:function(funOrFunName) {
            if (null != funOrFunName && funOrFunName !=undefined && (funOrFunName instanceof Function || (window[funOrFunName] !=undefined && eval(funOrFunName) instanceof Function))) {
                return true ;
            }else{
                return false ;
            }
        },
        /**
         * 批量数据填入有key属性 或者 hdkey属性 或者 basePath属性 的控件集合
         * var json = {} ;
         * $('body').setValByKey(json)
         */
        setValByKey: function (val) {
            var thisElem = $(this);
            thisElem.find('[' + key + '],[' + hideKey + ']').each(function () {
                var value = $(this).attr(key);
                var res = $(this).attrForObj(val, value);
                $(this).html(res);
            });
        },
        /**
         * 一键清除input、select
         */
        clearVal:function(){
            var thisElemArr = $(this) ;
            thisElemArr.each(function () {
                var thisElem = $(this);
                thisElem.find("input[type=text]").val("");
                thisElem.find("select").each(function(){
                    var firstVal =  $(this).find("option:first").attr("value");
                    $(this).val([firstVal]);
                });
            });
        },
        /**
         * 获取选中复选框的值，返回逗号拼接的字符串
         * @returns {string}
         */
        checkboxVal: function () {
            var ids= "";
            var thisElem = $(this) ;
            thisElem.each(function(index,obj){
                if(index == thisElem.length - 1){
                    ids += $(obj).next().val();
                }else{
                    ids += $(obj).next().val() + ",";
                }
            });
            return ids;
        },
        //格式时间戳
        formatDate:function(tm){
            var thisElem = $(this) ;
            if(!thisElem.is('[formatDate]')){
                return ;
            }
            var fmt =  thisElem.attr("formatDate") ;
            if(null==tm){
                tm = 0;
                var elemTag = thisElem[0].tagName;
                switch (elemTag) {
                    case "TEXTAREA":
                    case "INPUT":
                        tm = thisElem.val() ;
                        break;
                    default :
                        tm = thisElem.text() ;
                        break;
                }
            }
            if(null!=tm && $.trim(tm)!=""){
                var yearNum = "1970" ;
                var o = null ;
                if(tm.replace(/[0-9]/g,"").length>0){
                    var tempTm = tm.replace(/^[^0-9]*([0-9]*)[^0-9]*([0-9]*)[^0-9]*([0-9]*)[^0-9]*([0-9]*)[^0-9]*([0-9]*)[^0-9]*([0-9]*)[^0-9]*([0-9]*).*$/,"$1-$2-$3-$4-$5-$6-$7");
                    var tempTmArr = tempTm.split("-") ;
                    var tempTmArrOutYear = new Array() ;
                    var yearNum = "1970" ;
                    $.each(tempTmArr,function(point,val){
                        if(val.length==4){
                            yearNum = val ;
                        }else{
                            tempTmArrOutYear.push(val) ;
                        }
                    });
                    o = {
                        "M+" : tempTmArrOutYear[0].length>0?tempTmArrOutYear[0]:"0",                 //月份
                        "d+" : tempTmArrOutYear[1].length>0?tempTmArrOutYear[1]:"0",                    //日
                        "h+" : tempTmArrOutYear[2].length>0?tempTmArrOutYear[2]:"0",                   //小时
                        "m+" : tempTmArrOutYear[3].length>0?tempTmArrOutYear[3]:"0",                 //分
                        "s+" : tempTmArrOutYear[4].length>0?tempTmArrOutYear[4]:"0",                 //秒
                        "q+" : Math.floor((parseInt(tempTmArrOutYear[0])+3)/3), //季度
                        "S"  : tempTmArrOutYear[5].length>0?tempTmArrOutYear[5]:"0"             //毫秒
                    };
                }else{
                    if(tm.length<=11){
                        tm = parseInt(tm)*1000 ;
                    }else{
                        tm = parseInt(tm) ;
                    }
                    var tempDate = new Date(tm) ;
                    yearNum = tempDate.getFullYear()+"" ;
                    var o = {
                        "M+" : tempDate.getMonth()+1,                 //月份
                        "d+" : tempDate.getDate(),                    //日
                        "h+" : tempDate.getHours(),                   //小时
                        "m+" : tempDate.getMinutes(),                 //分
                        "s+" : tempDate.getSeconds(),                 //秒
                        "q+" : Math.floor((tempDate.getMonth()+3)/3), //季度
                        "S"  : tempDate.getMilliseconds()             //毫秒
                    };
                }

                if(/(y+)/.test(fmt)){
                    fmt=fmt.replace(RegExp.$1, yearNum.substr(4 - RegExp.$1.length));
                }
                for(var k in o){
                    if(new RegExp("("+ k +")").test(fmt)){
                        fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
                    }
                }
                switch (elemTag) {
                    case "TEXTAREA":
                    case "INPUT":
                        thisElem.val(fmt) ;
                        break;
                    default :
                        tm = thisElem.text(fmt) ;
                        break;
                }
            }
        }
    });
})(jQuery);