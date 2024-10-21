<!-- ${table.comment!} -->
<template>
  <table-search
    :commonQuery="pageJson.COMMON_QUERY_CONDITIONS_SELECTED"
    :moreQuery="pageJson.MORE_QUERY_CONDITIONS_SELECTED"
    :queryObj="query"
    :selectOptions="selectOptions"
    @filterOptions="filterOptions"
    @pageSet="pageSet"
    @search="searchFormEvent"
    v-show="!tableFullPage"
  />
  <!-- 分割线：收起折叠 -->
  <div :class="[tableFullPage ? 'divider-fold-full' : '', 'divider-fold']">
    <div class="divider-line"></div>
    <div class="fold-btn" @click="foldPage">
      <template v-if="tableFullPage">
        <el-icon><ArrowDown /></el-icon>
      </template>
      <template v-else>
        <el-icon><ArrowUp /></el-icon>
      </template>
    </div>
  </div>
  <div class="tbl">
    <!-- 表头操作按钮 -->
    <div :class="{ 'tbl-btn': true, 'text-right': pageJson.PAGE_BUTTON_PAGE_LOCATION?.location?.includes('right') }">
      <el-button
        v-for="(el, i) in pageJson.PAGE_BUTTON_SELECTED.filter((el) => el.config.common)"
        :key="i"
        type="primary"
        @click="operate(el.config.fieldName)"
      >
        {{ el.label }}
      </el-button>
      <el-dropdown
              v-show="pageJson.PAGE_BUTTON_SELECTED.filter((el) => !el.config.common).length > 0"
              class="ml-3"
              @command="handleCommand($event, null)"
      >
        <el-button type="primary">
          更多操作
          <el-icon class="el-icon--right">
            <arrow-down />
          </el-icon>
        </el-button>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item
              v-for="(el, i) in pageJson.PAGE_BUTTON_SELECTED.filter((el) => !el.config.common)"
              :key="i"
              :command="el.config.fieldName"
            >
              {{ el.label }}
            </el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>

    <!-- 表格数据 -->
    <el-table v-loading="loading" :data="tableData" border>
      <el-table-column
        v-for="(item, index) in pageJson.QUERY_RESULT"
        :key="item.prop"
        :align="item.align"
        :label="item.label"
        :prop="item.prop"
        :width="item.width"
      />

      <el-table-column
        align="center"
        :fixed="pageJson.OPERATION_BUTTON_PAGE_LOCATION?.location?.includes('left') ? 'left' : 'right'"
        label="操作"
        min-width="150px"
      >
        <template #default="scope">
          <el-link
            type="primary"
            v-for="(item, index) in pageJson.operateButtonCommonData"
            :key="index"
            class="mr-2"
            @click="doOperate(scope.row, item.config.fieldName)"
          >
            {{ item?.value ? item?.value : item.label }}
          </el-link>
          <el-dropdown
            v-show="pageJson.operateButtonMoreData.length > 0"
            class="ml-1 mt-1.5"
            @command="handleCommand($event, scope.row)"
          >
            <el-text type="primary">
              <el-icon>
                <MoreFilled />
              </el-icon>
            </el-text>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item
                  v-for="(el, i) in pageJson.operateButtonMoreData"
                  :key="i"
                  :command="el.config.fieldName"
                >
                  {{ el.label }}
                </el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <pagination
      v-if="total > 0"
      v-model:limit="query.size"
      v-model:page="query.current"
      v-model:total="total"
      @pagination="getData"
    />

    <!-- 新增/修改 -->
    <Edit v-if="editVisible" v-model:visible="editVisible" :frow="frow" @fetch-data="getData" />

    <!-- 页面设置 -->
    <page-setting
      v-if="pageSetVisible"
      v-model:visible="pageSetVisible"
      :pageSetData="pageSetData"
      :pageSettingJson="pageJson"
      @changePageSet="renderDomByLocalData"
    />
  </div>
</template>

<script setup lang="ts">
  import pageSettingJson from "./detail/query.json";
  import useUtils from "@/hooks/useUtils";
  import useDicts from "@/hooks/useDicts";

  defineOptions({
    inheritAttrs: false,
  });

  const pageObj = JSON.parse(JSON.stringify(pageSettingJson));
  const $utils = useUtils();
  const $dicts = useDicts();
  const editVisible = ref<boolean>(false);
  const loading = ref<boolean>(false);
  const total = ref<number>(0);
  const tableData = ref<RowProps[]>();

  /** 列表数据类型-根据列表数据自定义 */
  export interface RowProps {
  <#list table.fields as field>
    ${field.propertyName}?: String;
  </#list>
  }

  const frow = reactive<FrowProps<any>>({
    title: "新增",
    row: {},
  });

  /** 用户查询参数  */
  let query = reactive<PageQuery>({
    current: 1,
    size: 10,
  });

  /** 查询模板参数  */
  const pageSetData = reactive<PageSetDataProps>({
    module: "*****", //需要自己设置
    pageCode: "*****", //需要自己设置
    orgId: -1,
    title: "${table.comment!}",
    roleIds: [],
  });

  const selectOptions = reactive<SelectOptionsProps>({});

  let pageJson = reactive<PageJsonProps>({
    COMMON_QUERY_CONDITIONS_SELECTED: [], // 常用查询条件
    MORE_QUERY_CONDITIONS_SELECTED: [], // 更多查询条件
    PAGE_BUTTON_SELECTED: [], // 页面按钮
    OPERATION_BUTTON_SELECTED: [], //操作按钮的内容
    QUERY_RESULT: [], //表格查询结果内容
    PAGE_BUTTON_PAGE_LOCATION: pageObj.PAGE_BUTTON_PAGE_LOCATION, // 页面按键的位置
    OPERATION_BUTTON_PAGE_LOCATION: pageObj.OPERATION_BUTTON_PAGE_LOCATION, // 操作按键
    operateButtonCommonData: pageObj.OPERATION_BUTTON_SELECTED.filter(
      (item: OperationButtonProps) => item.config.common
    ), // 表格操作按钮-常用
    operateButtonMoreData: pageObj.OPERATION_BUTTON_SELECTED.filter(
      (item: OperationButtonProps) => !item.config.common
    ), // 表格操作按钮-更多
  });
  const pageSetVisible = ref<boolean>(false);
  const tableFullPage = ref<boolean>(false);

  const foldPage = () => {
    tableFullPage.value = !tableFullPage.value;
  };

  // 查询列表
  function getData() {
    $utils.queryFn(${table.entityPath}Page, query, (res: PageResult<RowProps[]>) => {
      tableData.value = res.records;
      total.value = res.total;
    });
  }

  // 新增/修改
  const handleEdit = (row?: RowProps) => {
    if (row?.id) {
      frow.title = "修改";
      frow.row = row;
    } else {
      frow.title = "新增";
    }
    editVisible.value = true;
  };

  // 查看
  const handleView = (row: RowProps) => {
    frow.title = "查看";
    frow.row = row;
    editVisible.value = true;
  };

  // 删除
  const handleDelete = async (id: string) => {
    const result = await $utils.showConfirm("确定要删除此数据？");
    if (result === "confirm") {
      $utils.queryFn(${table.entityPath}DeleteById, { id }, (res: any) => {
        $utils.showNote(res.messageInfo, "success");
        getData();
      });
    }
  };

  // 导出
  const handleExport = () => {
    getData();
  };

  // 页面配置
  const pageSet = () => {
    pageSetVisible.value = true;
  };

  // 页面操作按键，点击事件
  const operate = (fieldName: string) => {
    switch (fieldName) {
      case "add":
        handleEdit();
        break;
      case "export":
        handleExport();
        break;
    }
  };

  // 表格操作按键，点击事件
  const doOperate = (row: RowProps, fieldName: string) => {
    switch (fieldName) {
      case "edit":
        handleEdit(row);
        break;
      case "view":
        handleView(row);
        break;
      case "delete":
        handleDelete(row.id);
        break;
    }
  };

  // 查询模板
  const getQueryTemplate = async () => {
    pageSetVisible.value = false;
    await $utils.setQueryTemplate(pageJson, pageSetData);
    getData();
  };

  // 下拉框 - 过滤搜索
  const filterOptions = (keywords: string, fieldName: string, queryData: any) => {
    $utils.reactiveAssign(query, queryData);
  };

  // 查询
  const searchFormEvent = (queryData: any) => {
    $utils.reactiveAssign(query, queryData);
    getData();
  };

  //本地数据渲染
  const renderDomByLocalData = () => {
    $utils.reactiveAssign(pageJson, pageObj);
    getData();
  };

  const getSelectOptions = () => {
    // getAirPortOptions();
    // getCityOptions()
  };

  const handleCommand = (command: string, row?: RowProps) => {
    if (row) {
      doOperate(row, command);
    } else {
      operate(command);
    }
  };

  onMounted(() => {
    getSelectOptions();
    renderDomByLocalData(); //本地数据
    // getQueryTemplate();  //接口数据
  });
</script>

<style lang="scss" scoped></style>
