/**
 * Created by Evan on 2017/2/23.
 */
(function ($) {
    $.fn.extend({
        /**
         * 打开loading
         */
        openLoading:function(){
            layer.load(1, {
                shade: [0.5,'#000'] //0.1透明度的白色背景
            });
        },
        /**
         * 关闭loading
         */
        closeLoading:function(){
            layer.closeAll('loading');
        }

    });
})(jQuery);