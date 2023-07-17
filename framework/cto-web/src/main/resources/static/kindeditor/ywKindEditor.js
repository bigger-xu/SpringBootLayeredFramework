var _kindEditorMap = new Map() ;
var _kindEditorIdNum = 0 ;
var _kindEditorIdBefore = "kindEditorId" ;
(function ($) {
    $.fn.extend({
        //注册富文本编辑器
        initKindEditor: function (config,kindEditorHtml) {
            var _thisElem = $(this);
            var elemId ;
            if(null==kindEditorHtml){
                kindEditorHtml =_thisElem.val();
            }
            if(!_thisElem.is("[id]")){
                elemId = _kindEditorIdBefore+_kindEditorIdNum ;
                _thisElem.attr("id",elemId) ;
                _kindEditorIdNum++ ;
            }else{
                elemId = _thisElem.attr("id");
            }
            var defaultConfig = {
                resizeType: 1,
                uploadJson: '/common/file/kindEditorUpload',
                allowFileManager: false,
                afterChange: function () {
                    this.sync();
                },
                urlType:'domain',
                items: [
                    'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
                    'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
                    'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
                    'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
                    'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
                    'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'multiimage',
                    'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
                    'anchor', 'link', 'unlink'
                ]
            };
            $.extend(defaultConfig, config);
            KindEditor.options.filterMode = false;
            KindEditor.ready(function (K) {
                var editor = K.create(_thisElem, defaultConfig);
                editor.html(kindEditorHtml);
                editor.sync();
                _kindEditorMap.put(elemId,editor);
            })
        },
        kindEditorGetData: function () {
            var _thisElem = $(this);
            var editorId =_thisElem.attr("id");
            var editor = _kindEditorMap.get(editorId);
            if(editor){
                return editor.html();
            }else{
                alert("尚未初始化编辑器");
            }
        },
        kindEditorSetData: function (editorHtml) {
            var _thisElem = $(this);
            var editorId =_thisElem.attr("id");
            var editor = _kindEditorMap.get(editorId);
            if(editor){
                editor.html(editorHtml);
            }else{
                alert("尚未初始化编辑器");
            }
        }
    });
    $.extend({
        //注册富文本编辑器
        initKindEditor: function (editorId,config, kindEditorHtml) {
            var defaultConfig = {
                resizeType: 1,
                uploadJson: '/common/file/kindEditorUpload',
                allowFileManager: false,
                afterChange: function () {
                    this.sync();
                },
                urlType:'domain',
                items: [
                    'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
                    'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
                    'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
                    'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
                    'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
                    'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'multiimage',
                    'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
                    'anchor', 'link', 'unlink'
                ]
            };
            $.extend(defaultConfig, config);
            KindEditor.options.filterMode = false;
            KindEditor.ready(function (K) {
                var editor = K.create(editorId, defaultConfig);
                if(kindEditorHtml){
                    editor.html(kindEditorHtml);
                }
                editor.sync();
                _kindEditorMap.put(editorId,editor);
            })
        },
        kindEditorGetData: function (editorId) {
            var editor = _kindEditorMap.get(editorId);
            if(editor){
                return editor.html();
            }
        },
        kindEditorSetData: function (editorId,editorHtml) {
            var editor = _kindEditorMap.get(editorId);
            if(editor){
                editor.html(editorHtml);
            }
        }
    });
})(jQuery);