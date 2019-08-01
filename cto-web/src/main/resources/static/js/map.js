/**
 var map = new Map();
 map.put("re","redhacker");
 map.put("do","douguoqiang");
 map.put("gq","dougq");
 alert("map的大小为：" + map.size())
 alert("key为re的map中存储的对象为：" + map.get("re"));
 map.remove("re");
 alert("移除key为re的对象后，获取key为re的map中存储的对象为：" + map.get("re"));
 alert("map移除一个元素后的大小为：" + map.size());
 alert("map是否是一个空map:" + map.isEmpty());
 遍历Map
 for(var index in map.arr){
    console.info(_select_question_id.arr[index].value);
}
 */

function Map() {

    var struct = function(key, value) {
        this.key = key;
        this.value = value;
    };

    var put = function(key, value){
        for (var i = 0; i < this.arr.length; i++) {
            if ( this.arr[i].key === key ) {
                this.arr[i].value = value;
                return;
            }
        }
        this.arr[this.arr.length] = new struct(key, value);
    };

    var get = function(key) {
        for (var i = 0; i < this.arr.length; i++) {
            if ( this.arr[i].key === key ) {
                return this.arr[i].value;
            }
        }
        return null;
    };

    var remove = function(key) {
        var v;
        var new_array = new Array();
        var total = this.arr.length;
        for (var i = 0; i < total; i++) {
            v = this.arr.pop();
            if ( v.key === key ) {
                continue;
            }
            new_array.unshift(v);
        }
        this.arr = new_array;
    };

    var size = function() {
        return this.arr.length;
    };

    var isEmpty = function() {
        return this.arr.length <= 0;
    };

    var getValues = function(){
        var ids = "";
        for(var index in this.arr){
            if(Number(index) + 1 == this.arr.length){
                ids += this.arr[index].value;
            }else{
                ids += this.arr[index].value+",";
            }
        }
        return ids;
    };

    var clear = function(){
        var new_array = new Array();
        this.arr = new_array;
    }

    this.arr = new Array();
    this.get = get;
    this.put = put;
    this.remove = remove;
    this.size = size;
    this.isEmpty = isEmpty;
    this.getValues = getValues;
    this.clear = clear;
}
