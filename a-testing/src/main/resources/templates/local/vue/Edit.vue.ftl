<template>
  <div>
    <el-dialog
      v-model="dialogVisible"
      :align-center="true"
      :title="'${table.comment!} - ' + frow.title"
      width="1000px"
      @close="handleClose"
    >
      <el-form ref="ruleFormRef" :model="ruleForm" :rules="rules" label-width="0px">
        <el-row :gutter="10">
          <#list table.fields as field>
          <el-col :span="6">
            <el-form-item prop="${field.propertyName}">
              <et-input
                v-model="ruleForm.${field.propertyName}"
                v-left-label="'${field.comment!}'"
                :disabled="disabledStatus"
                :maxlength="100"
                class="w-full"
              />
            </el-form-item>
          </el-col>
          </#list>
        </el-row>
      </el-form>
      <el-button v-if="frow.title != '查看'" style="margin-top: 20px" type="primary" @click="saveRuleForm">
        确定
      </el-button>
      <el-button v-if="frow.title == '查看'" style="margin-top: 20px" type="primary" @click="handleClose">
        关闭
      </el-button>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
  import useUtils from "@/hooks/useUtils";

  const $utils = useUtils();
  const props = defineProps({
    visible: {
      type: Boolean,
      default: false,
    },
    frow: {
      type: Object,
      default: () => ({}),
    },
  });

  const selectOptions = reactive({});
  const ruleForm = reactive({
  <#list table.fields as field>
    ${field.propertyName}: null,
  </#list>
  });

  const rules = reactive<FormRules>({
    // testRule: [{ required: true, message: "请输入", trigger: "blur" }],
  });

  const emit = defineEmits(["update:visible", "fetch-data"]);

  const dialogVisible = computed({
    get: () => props.visible,
    set: (val) => {
      emit("update:visible", val);
    },
  });

  const disabledStatus = ref<boolean>(false);
  const initData = () => {
    if (props.frow.title !== "新增") {
      let options = { id: props.frow.row.id };
      ${table.entityPath}Detail(options).then((res) => {
        Object.keys(ruleForm).map((key) => {
          if (res[key]) {
            ruleForm[key] = res[key];
          }
        });
      });
    }
    if (props.frow.title === "查看") {
      disabledStatus.value = true;
    }
  };

  const getSelectOptions = async () => {};

  // 关闭弹框
  const handleClose = () => {
    dialogVisible.value = false;
  };

  const ruleFormRef = ref<FormInstance>();
  // 必填、规则校验
  const formValidate = async () => {
    let isContinue = false;
    // 表单验证
    await ruleFormRef.value.validate((valid, fields) => {
      if (!valid) {
        $utils.showNote("操作失败，请检查必填项。", "warning");
        isContinue = true;
      }
    });
    return isContinue;
  };

  //  确定
  const saveRuleForm = async () => {
    if (await formValidate()) {
      return;
    }
    let saveRes = "";
    if (props.frow.title === "新增") {
      saveRes = await ${table.entityPath}Save(ruleForm);
    }
    if (props.frow.title === "修改") {
      saveRes = await ${table.entityPath}Update(ruleForm);
    }
    if (saveRes?.code && saveRes.code !== 0) {
      $utils.showNote(saveRes.messageInfo, "error");
    } else {
      $utils.showNote("操作成功", "success");
      emit("fetch-data");
      handleClose();
    }
  };


  onMounted(() => {
    getSelectOptions();
    initData();
  });
</script>

<style scoped lang="scss">
  @import "@/assets/css/table.scss";
  :deep(.addarea-ruleForm) {
    overflow: unset !important;
  }
</style>
