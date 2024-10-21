{
  "COMMON_QUERY_CONDITIONS_SELECTED": [

  ],
  "MORE_QUERY_CONDITIONS_SELECTED": [

  ],
  "PAGE_BUTTON_PAGE_LOCATION": {
    "active": 1,
    "location": "page-btn-left"
  },
  "OPERATION_BUTTON_PAGE_LOCATION": {
    "active": 1,
    "location": "operate-btn-left"
  },
  "PAGE_BUTTON_SELECTED": [
    {
      "style": {
        "width": "100%"
      },
      "__slot__": {
        "append": "",
        "prepend": ""
      },
      "label": "新增",
      "disabled": false,
      "readonly": false,
      "clearable": true,
      "maxlength": null,
      "config": {
        "tag": "el-input",
        "span": 24,
        "common": true,
        "layout": "colFormItem",
        "regList": [],
        "tagIcon": "input",
        "document": "",
        "isSelect": false,
        "orderNum": 1,
        "required": true,
        "changeTag": true,
        "fieldName": "add",
        "showLabel": true,
        "labelWidth": null
      },
      "placeholder": "请输入",
      "prefix-icon": "",
      "suffix-icon": "",
      "show-word-limit": false
    },
    {
      "style": {
        "width": "100%"
      },
      "__slot__": {
        "append": "",
        "prepend": ""
      },
      "label": "导出",
      "disabled": false,
      "readonly": false,
      "clearable": true,
      "maxlength": null,
      "config": {
        "tag": "el-input",
        "span": 24,
        "common": true,
        "layout": "colFormItem",
        "regList": [],
        "tagIcon": "input",
        "document": "",
        "isSelect": false,
        "orderNum": 2,
        "required": true,
        "changeTag": true,
        "fieldName": "export",
        "showLabel": true,
        "labelWidth": null
      },
      "placeholder": "请输入",
      "prefix-icon": "",
      "suffix-icon": "",
      "show-word-limit": false
    }
  ],
  "OPERATION_BUTTON_SELECTED": [
    {
      "label": "修改",
      "config": {
        "tag": "el-input",
        "span": 24,
        "value": "",
        "common": true,
        "layout": "colFormItem",
        "tagIcon": "input",
        "document": "",
        "isSelect": false,
        "orderNum": 13,
        "required": true,
        "changeTag": true,
        "fieldName": "edit",
        "showLabel": true,
        "labelWidth": null
      }
    },
    {
      "label": "查看",
      "config": {
        "tag": "el-input",
        "span": 24,
        "value": "",
        "common": true,
        "layout": "colFormItem",
        "tagIcon": "input",
        "document": "",
        "isSelect": false,
        "orderNum": 13,
        "required": true,
        "changeTag": true,
        "fieldName": "view",
        "showLabel": true,
        "labelWidth": null
      }
    },
    {
      "label": "删除",
      "config": {
        "tag": "el-input",
        "span": 24,
        "value": "",
        "common": true,
        "layout": "colFormItem",
        "tagIcon": "input",
        "document": "",
        "isSelect": false,
        "orderNum": 13,
        "required": true,
        "changeTag": true,
        "fieldName": "delete",
        "showLabel": true,
        "labelWidth": null
      }
    }
  ],
  "QUERY_RESULT": [
<#list table.fields as field>
    {
      "label": "${field.comment!}",
      "prop": "${field.propertyName!}",
      "align": "center",
      "index": ${field_index + 1},
      "width": 150,
      "flag": "true",
      "sortable": false,
      "fixedable": false
    }<#if field_has_next>,</#if>
</#list>
  ]
}
