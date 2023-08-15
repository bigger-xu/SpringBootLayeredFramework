package com.cto.common.mq.order.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AfOrderMqDTO implements Serializable {

    private Integer orderId;

    /**
     * 订单uuid
     */
    private String orderUuid;

    private String ycOrderId;

    private String transPortMode;

    /**
     * 签约公司ID
     */
    private Integer orgId;

    /**
     * 订单号
     */
    private String orderCode;

    /**
     * 订单状态
     */
    private String orderStatus;

    /**
     * 订单状态 (取名orderStatusReq因为orderStatus被业务锁定占用了，好尴尬)
     */
    private String orderStatusReq;

    private List<String> orderStatusReqList;

    /**
     * 货物状态
     */
    private String cargoStatus;

    private List<String> cargoStatusList;

    /**
     * 是否主单货
     */
    private Boolean isMwb;

    /**
     * 主单id
     */

    private Integer awbId;

    /**
     * 主单uuid
     */
    private String awbUuid;

    /**
     * 主单号
     */
    private String awbNumber;

    /**
     * 运单来源ID
     */
    private transient Integer awbFromId;

    /**
     * 运单来源名称
     */
    private transient String awbFromName;

    /**
     * 运单来源代码
     */
    private transient String supplierCode;

    /**
     * 分单数量
     */

    private Integer hawbQuantity;

    /**
     * 分单id
     */

    private Integer hawbId;

    /**
     * 分单号
     */
    private String hawbNumber;

    /**
     * 客户单号
     */
    private String customerNumber;

    /**
     * 客户代码
     */
    private transient String customerCode;

    /**
     * 客户项目id
     */
    private Integer projectId;

    /**
     * 客户id
     */
    private Integer coopId;

    private Integer servicerId;

    private String servicerName;

    private Integer salesId;

    private String salesName;

    /**
     * 业务范畴
     */
    private String businessScope;

    /**
     * 服务产品
     */
    private String businessProduct;

    /**
     * 运输条款
     */
    private String transitClause;

    /**
     * 到货方式
     */
    private String arrivalMethod;

    /**
     * 到货日期
     */

    private LocalDateTime receiptDate;

    /**
     * 是否可拼
     */
    private Boolean isConsol;

    /**
     * 是否非木
     */
    private Boolean isNoSolidWood;

    /**
     * 预计航班号
     */
    private String expectFlight;

    /**
     * 预计航班日期(ETD)
     */

    private LocalDate expectDeparture;

    /**
     * 预计到达日期(ETA)
     */

    private LocalDate expectArrival;


    /**
     * 始发港
     */
    private String departureStation;

    /**
     * 目的港
     */
    private String arrivalStation;

    /**
     * 目的港代理
     */
    private String arrivalAgent;

    /**
     * 中转港
     */
    private String transitStation;

    /**
     * 中转港
     */
    private String transitStation2;


    /**
     * 始发货栈
     */

    private Integer departureWarehouseId;


    /**
     * 始发库房
     */

    private Integer departureStorehouseId;


    /**
     * 品名_中文
     */
    private String goodsNameCn;

    /**
     * 品名_英文
     */
    private String goodsNameEn;

    /**
     * 货物类型
     */
    private String goodsType;

    /**
     * 危险品类型
     */
    private String dangerousType;

    private String appraisalCompany;

    /**
     * 电池类型
     */
    private String batteryType;

    /**
     * 包装类型
     */
    private String packageType;

    /**
     * 订舱包装类型
     */
    private String bookPackageType;

    /**
     * 订舱件数
     */
    private Integer bookPieces;

    /**
     * 订舱毛重
     */
    private BigDecimal bookWeight;

    /**
     * 订舱体积
     */
    private BigDecimal bookVolume;

    /**
     * 订舱尺寸
     */
    private String bookDimensions;

    /**
     * 订舱密度
     */
    private Integer bookDensity;

    /**
     * 预报件数
     */

    private Integer planPieces;

    /**
     * 预报小件数
     */

    private Integer planSmallPieces;

    /**
     * 预报毛重
     */

    private BigDecimal planWeight;

    /**
     * 预报体积
     */

    private Double planVolume;

    /**
     * 预报计费重量
     */
    private Double planChargeWeight;

    /**
     * 预报密度
     */
    private Integer planDensity;

    /**
     * 实际密度
     */

    private Integer confirmDensity;

    /**
     * 预报尺寸
     */
    private String planDimensions;

    /**
     * 实际件数
     */

    private Integer confirmPieces;

    /**
     * 实际毛重
     */

    private BigDecimal confirmWeight;

    /**
     * 实际体积
     */

    private Double confirmVolume;

    /**
     * 实际计费重量
     */

    private Double confirmChargeWeight;

    /**
     * 实际尺寸
     */

    private String confirmDimensions;

    /**
     * 结算计费重量
     */
    private Double settleChargeWeight;

    /**
     * 运费币种
     */
    private String currecnyCode;

    //成本币种

    private String msrCurrecnyCode;

    /**
     * 付款方式
     */
    private String paymentMethod;

    /**
     * 运费单价
     */

    private Double freightUnitprice;

    /**
     * 运费总价
     */

    private Double freightAmount;

    /**
     * 客户分泡
     */
    private String freightProfitRatioRemark;

    /**
     * msr单价（成本单价）
     */

    private Double msrUnitprice;

    /**
     * msr总价（成本总价）
     */

    private Double msrAmount;

    /**
     * 随机文件
     */
    private String airborneDocument;

    /**
     * 随机文件：常用; 产地证，发票，装箱单，分舱单，ATA单证（可能是多个，多个用","隔开）
     */
    private String commonlyUse;

    /**
     * 价格备注
     */
    private String priceRemark;

    /**
     * 成本分泡
     */
    private String msrProfitRatioRemark;

    /**
     * 主单付费重量
     */
    private Double awbCostChargeWeight;

    /**
     * 主单成本单价
     */
    private Double awbMsrUnitprice;

    /**
     * 主单成本单价
     */
    private Double awbMsrAmount;

    /**
     * 订单收费重量
     */
    private Double incomeChargeWeight;

    /**
     * 销售费用单价
     */
    private Double sellingCostUnitprice;

    /**
     * 销售费用总价
     */
    private Double sellingCostAmount;

    /**
     * 利润分成deptid
     */
    private Integer shareProfitId;

    /**
     * 利润分成模式
     */
    private String shareProfitType;

    /**
     * 利润分成标准
     */
    private Double shareProfitNumber;

    /**
     * 创建人
     */
    private Integer creatorId;

    private String creatorName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private Integer editorId;

    private String editorName;

    //鉴定情况
    private String appraisalNote;

    //备注
    private String orderRemark;

    //handling_info
    private String handlingInfo;

    //唛头
    private String shippingMarks;

    //海关代码
    private String customsStatusCode;

    //操作备注
    private String operationRemark;

    //货物流向
    private String cargoFlowType;

    //货物流向备注
    private String cargoFlowRemark;

    //通知人信息
    private String notifierRemark;

    //始发港代理
    private String departureAgent;

    //外库服务
    private Boolean switchAwbService;
    //外库服务_调单日期

    private LocalDate switchAwbDate;

    //外库服务_调单地址
    private String switchAwbAddress;

    //外库服务_调单备注
    private String switchAwbRemark;

    //库内服务
    private Boolean warehouseService;

    //库内服务_操作公司
    private String warehouseOperator;
    //库内服务_到货时间

    private LocalDateTime planInboundDate;
    //库内服务_入库日期

    private LocalDateTime inboundDate;
    //库内服务_出库日期

    private LocalDateTime outboundDate;

    //库内服务_破损记录
    private String damageRemark;

    //报关服务
    private Boolean customsClearanceService;

    //报关类型
    private String customsDeclaresType;

    //报关代理
    private String customsClearanceCompany;
    //查验日期

    private LocalDate customsInspectionDate;
    //放行日期

    private LocalDate customsClearanceDate;

    //查验备注
    private String customsInspectionRemark;

    //报关备注
    private String customsDeclaresRemark;

    //派送服务
    private Boolean deliveryService;

    //卡车公司
    private String deliveryCompany;

    //车辆司机
    private String deliveryDriver;

    //收货人信息
    private String deliverySigner;

    //送货地址
    private String deliveryAddress;
    //送货日期

    private LocalDate deliveryDate;
    //签收日期

    private LocalDate deliverySignDate;

    //派送备注
    private String deliveryRemark;

    /**
     * 提货服务
     */
    private Boolean pickUpDeliveryService;

    /**
     * 提货公司
     */
    private String pickUpDeliveryCompany;

    /**
     * 提货地址
     */
    private String pickUpAddress;

    /**
     * 提货日期
     */

    private LocalDate pickUpDeliveryDate;

    /**
     * 送货地址
     */
    private String pickUpDeliveryAddress;

    /**
     * 提货备注
     */
    private String pickUpDeliveryRemark;

    /**
     * 外场服务
     */
    private Boolean outfieldService;

    /**
     * 送货公司
     */
    private String outfieldDeliveryCompany;

    /**
     * 车牌号
     */
    private String outfieldTruckNumber;

    /**
     * 司机信息
     */
    private String outfieldDriver;

    /**
     * 打板代理
     */
    private String buildUpCompany;

    /**
     * 外场操作备注
     */
    private String outfieldRemark;

    /**
     * 外场操作：交单时间
     */

    private LocalDateTime presentationTime;

    /**
     * 外场操作：交单人ID
     */

    private Integer presentationId;

    /**
     * 外场操作：截单时间
     */

    private LocalDateTime cutOffTime;

    /**
     * 目的港清关服务：1是 0否
     */
    private Boolean arrivalCustomsClearanceService;

    /**
     * 目的港清关服务：报关代理
     */
    private String arrivalCustomsClearanceCompany;

    /**
     * 目的港清关服务：查验日期
     */

    private LocalDate arrivalCustomsInspectionDate;

    /**
     * 目的港清关服务：放行日期
     */

    private LocalDate arrivalCustomsClearanceDate;

    /**
     * 目的港清关服务：报关备注
     */
    private String arrivalCustomsDeclaresRemark;

    /**
     * 目的港清关服务：查验备注
     */
    private String arrivalCustomsInspectionRemark;

    /**
     * 修改时间
     */
    private Date editTime;

    //
    private transient String createTimeBegin;

    private transient String createTimeEnd;

    private transient String flightDateBegin;

    private transient String flightDateEnd;

    private transient String expectDepartureStart;

    private transient String expectDepartureEnd;

    /**
     * 始发库房名称
     */
    private transient String departureStorehouseName;

    /**
     * 始发货栈名称
     */
    private transient String departureWarehouseName;

    /**
     * 货站代码
     */
    private transient String departureWarehouseEdiCode;


    private transient String salesManagerName;

    private transient String projectName;

    private transient String coopName;

    private transient String coopCode;

    private transient String shortCoopName;

    private transient String contactName;

    private transient String priceType2;

    private transient Double priceValue2;

    private transient Double priceValue4;

    private transient BigDecimal incomeExchangeRate;

    private transient BigDecimal costExchangeRate;

    /**
     * 币种汇率
     */
    private transient BigDecimal currencyRate;

    private transient List<Integer> orderContacts;

    private String deletePoIds;

    private List<Integer> deleteSlIdList;


    /**
     * 出重信息
     */
    private transient BigDecimal inboundOrderChargeWeight;

    private transient BigDecimal inboundAwbChargeWeight;

    private String rowUuid;

    /**
     * 应收状态
     */
    private String incomeStatus;

    /**
     * 应付状态
     */
    private String costStatus;

    /**
     * 询价单id
     */
    private Integer orderInquiryId;

    /**
     * 报价方案id
     */

    private Integer orderInquiryQuotationId;


    private String orderInquiryRowUuid;


    private String columnStrs;

    private Boolean incomeRecorded;

    private Boolean costRecorded;


    private Integer incomeRecordedForSort;

    private Integer costRecordedForSort;

    /**
     * 杂费付款方式
     */
    private String paymentMethodOther;

    /**
     * 危险品编号
     */
    private String undgCode;

    /**
     * 危险品联系人
     */
    private String undgContactName;

    /**
     * 危险品联系人通讯类型
     */
    private String undgContactCommunicationType;

    /**
     * 危险品联系人通讯号码
     */
    private String undgContactCommunicationNo;

    /**
     * 装载时间
     */

    private LocalDateTime loadingDate;

    /**
     * 是否分批
     */
    private Boolean partialShipment;

    /**
     * 货源地
     */
    private String goodsSourceCode;

    /*
    主单舱单状态
     */
    private String manifestStatus;

    //分享源所在的签约公司

    private Integer orderShareOrgId;
    //分享源 所指定的orderId

    private Integer orderShareOrderId;
    //分享源所指定的客商ID

    private Integer OrderShareCoopId;
    //分享源所绑定的客商资料ID

    private Integer coopOrgCoopId;

    private transient String presets;

    /**
     * 审结
     */
    private transient String audit;

    private transient String arrived;

    private transient String passed;

    /**
     * 查验
     */
    private transient String checked;

    private transient String ams;

    private transient String entryPlate;


    /**
     * 是否货已到齐
     */
    private Integer cargoCompleted;

    /**
     * 工作组ID
     */

    private Integer workgroupId;

    /**
     * 工作组名称
     */

    private String workgroupName;

    /**
     * 当前用户的订单权限
     */

    private Integer orderPermission;

    /**
     * 当前用户ID
     */

    private Integer currentUserId;

    /**
     * 航线
     */

    private String routingName;

    /**
     * 签单ID
     */

    private Integer rountingSignId;

    /**
     * 签单状态
     */

    private Integer signState;

    /**
     * 航线签单
     */

    private Integer rountingSign;

    /**
     * 航线签单-服务产品
     */

    private String rountingSignBusinessProduct;

    /**
     * 航线负责人
     */
    private String routingPersonName;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 托盘材质
     */

    private String palletMaterial;

    /**
     * 特货包装
     */

    private String specialPackage;

    /**
     * 温度要求
     */

    private String celsiusRequire;

    /**
     * 温度计
     */

    private Integer thermometer;

    /**
     * 是否有温度要求
     */

    private Boolean isCelsiusRequire;

    private String coldStorageMethod;

    private String coldStorageMethodRemark;

    private String tranFlag;
    //yc接单使用

    private String contactPhone;

    private String contactEmail;

    private Boolean fromYc;

    private Integer orderStatusInt;

    private Integer boundDays;


    private Integer configId;


    private BigDecimal confirmVolumeWeight;

    private BigDecimal planVolumeWeight;

    private String internalNumber;

    private Integer routingPersonId;

    private String transitFlight;

    private String transitFlight2;

    private LocalDate transitFlightDate;

    private LocalDate transitFlightDate2;

    private String transitFlightDateBegin;

    private String transitFlightDateEnd;
    //附属表  鉴定结论

    private String appraisalConclusion;

    /**
     * 库内操作->普通库房联系人
     */

    private Integer departureStorehouseContactId;

    /**
     * 外场操作->交货货站联系人
     */

    private Integer departureWarehouseContactId;

    /**
     * 订舱代理
     */

    private Integer bookingAgentId;

    private String bookingAgentName;

    private String bookingAgentCode;

    /**
     * 订舱代理简称
     */

    private String bookingAgentShortName;


    private String bookingAgentType;

    private String couponCode;

    private String functionName;

    /**
     * 操作公司
     */

    private Integer operationAgentId;

    private String operationAgentName;

    private String operationAgentType;

    /**
     * 最低温度
     */
    private BigDecimal minTemperature;

    /**
     * 最高温度
     */
    private BigDecimal maxTemperature;


    /**
     * 报检员姓名
     */
    private String securitycheckerName;

    /**
     * 报检员手机号
     */
    private String securitycheckerTel;

    /**
     * 报检证号
     */
    private String securitycheckerCertificate;

    /**
     * 报检员身份证号
     */
    private String securitycheckerCardId;

    private Integer isOnlineBooking;


    private Double proportion;//比重

    private String deliveryType;//交货方式：S自送、P提货

    private String isCustomsDeclaration;//是否自报关：0是、1否

    /**
     * 报关服务-报关方式：委托报关；客户自报
     */

    private String customsType;

    //以下小程序使用

    private String servicerPhoneNumber;

    private String servicerInternationalCountryCode;

    private String salesPhoneNumber;

    private String rountingPhoneNumber;


    private String salesInternationalCountryCode;

    private Integer currentPage;

    private Integer pageSize;


    private Date customsDetainDate;


    private String reason;


    private List<String> bscope;

    private String editTimeStart;

    private String editTimeEnd;


    private String createTimeStart;


    private String flightDateStart;


    private Integer orgCoopNameView;

    private Integer twOrderId;

    private String twRowId;

    private Integer rountingApproval;

    private Integer assemblyPlanPieces;

    /**
     * 查询  TW订单状态
     */

    private String coopOrgTwOrderStatus;

    private String coopOrgTwOrderRemark;

    /**
     * 附属表_货物性质
     */

    private String natureOfCargos;

    /**
     * 附属表_货物包含种类
     */

    private String typeOfCargos;

    /**
     * 附属表_货物状态（1:是，0:否）
     */

    private Integer goodsState;

    /**
     * 制单信息：品名分类
     */

    private String tradeNameClassification;

    /**
     * 鉴定结论(是否限制性物品)
     */

    private String appraisalRestrictiveType;

    private String afOrderShipperConsignee1ScPrintRemark;

    private String afOrderShipperConsignee2ScPrintRemark;

    /**
     * 责任操作 id
     */

    private Integer controllerId;

    /**
     * 责任操作 名称
     */

    private String controllerName;

    /**
     * 友舱询价单号
     */

    private Long inquiryNo;

    /**
     * 友舱报价id
     */

    private Long quotationId;

    //网上订舱 委托客户查询条件

    private String entrustCustomer;


    private String heavyState;//出重状态


    private String voucherState;//制单状态


    private String costState;//费用状态

    private Integer coopOrgOrderId;

    private Integer coopOrgId;

    /**
     * 责任销售id
     */

    private Integer transactorId;

    /**
     * 报告编号
     */

    private String reportIssueNo;

    /**
     * 协同收入状态   0未接收  1已接收  -1拒绝接收
     */

    private Integer synergyIncomeState;

    /**
     * 揽货类型：自揽货/指定货
     */
    private String solicitationType;

    /**
     * 内部询价订单来源（0 友舱，1 生态云）
     */

    private Integer inquirySource;

    /**
     * 内部询价，报价方AE订单id
     */

    private Integer innerOrderId;

    /**
     * 询价方邮箱
     */

    private String userEmail;

    /**
     * 询价方用户名
     */

    private String userName;

    /**
     * 询价方部门id
     */

    private Integer deptId;


    private Integer buyerId;


    private String buyerName;

    private transient String receiptDateStart;

    private transient String receiptDateEnd;


    private String hawbOrPoNumsLog;

    private String carrierNameEn;


    private String deleteOrderPoIds;

    /**
     * 报关金额
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)

    private BigDecimal customsMoney;

    /**
     * 报关单号
     */
    private String customsOrder;

    /**
     * 网上预配锁定
     */

    private Integer isLockOnlineBooking;

    /**
     * 锁定查询条件
     */

    private Integer isLockOnlineBookingQuery;


    /**
     * 卸载类型 1：继续使用 2 作废主单
     */
    private Integer uninstallType = 1;

    /**
     * 信控：操作内容
     */

    private String operationalContext;

    /**
     * 信控，费用拒绝原因
     */

    private String rejectReason;

    private String orgCodeThree;

}
