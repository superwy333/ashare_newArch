package constant;

import java.awt.*;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017-05-24 .
 */
public class Constant {

    public static String EDOC_NO_FOOTER_EXCEPTION = "EXCEPTION";

    public static String JDE_DBNSP = "CRPDTA"; //CRPDTA/PRODDTA

    public static class IsProcessed{
        //未处理
        public static final String NO_PROCESSED = "0";
        //已处理（执行采集操作）
        public static final String PROCESSED_ALREADY = "1";
        //已采集（采集操作完成）
        public static final String ALREADY_SCAN = "2";
        //已同步
        public static final String ALREADY_SYCHRO = "3";
    }


    public static class EdocSource {

        /**
         * 上传
         */
        public static final String UPLOAD = "1";

        /**
         * 扫描
         */
        public static final String SCAN = "0";
    }


    public static class isReplace {

        public static final String YES = "1";

        public static final String NO = "0";
        /**
         * 已替换
         */
        public static final String REPLACE = "2";
    }

    //====================reviewInfo中的review_state=======================
    public static class ReviewInfoStatus {
        //新建
        public static final String NEW_SCAN="11";
        //重扫
        public static final String AGAIN_SCAN="31";
        //重扫撤销
        public static final String AGAIN_SCAN_REVOKE="310";
        //补扫
        public static final String ADD_SCAN="32";
        //补扫撤销
        public static final String ADD_SCAN_REVOKE="320";
        //完成
        public static final String FINISH="40";
        //作废
        public static final String CANCEL="41";
        //撤销
        public static final String REVOKE = "42";
        //从VSS删除AP类发票
        public static final String DELETE = "44";
        //实物退单
        public static final String BACK_BILL = "51";

        //AP类多寄待确认
        public static final String MORE_MAIL_0 = "60";
        //AP类确认多寄
        public static final String MORE_MAIL_1 = "61";
        //AP类确认未多寄
        public static final String MORE_MAIL_2 = "62";
        //AP类信息待复核
        public static final String CHECK_0 = "70";
        //AP类信息复核有误
        public static final String CHECK_1 = "71";
        //AP类信息复核无误
        public static final String CHECK_2 = "72";
        //AP类待推送至vss
        public static final String WAIT_TO_VSS = "80";
    }
    //退票方式
    public static class BackBillMethod{
        //自取
        public static final String SELF = "1";
        //邮寄
        public static final String MAIL = "2";
    }


    //=================发票认证相关======================
    public static class EdocAuthStatus{
        //待认证
        public static final String WAIT_AUTH = "00";
        //已推送至集中认证平台
        public static final String PUSH_AUTH = "10";
        //已拉取认证结果（全部认证成功）
        public static final String RESULT_ALL_SUCC = "20";
        //已拉取认证结果（未全部认证成功）
        public static final String RESULT_PART_SUCC = "21";
        //已推送至费用系统
        public static final String PUSH_EXPENSE = "30";


        //===============发票认证结果============
        //认证成功
        public static final String AUTH_SUCC = "40";
        //认证失败
        public static final String AUTH_FAIL = "41";
        //认证中
        public static final String AUTH_IN = "42";
    }


    //=================单证影像数据上传图片扫描操作代码======================
    public static class DataUploadStatus {
        //网页端上传
        public static final String STATE_WEB = "12";
        //新建扫描（待扫描）
        public static final String STATE_NEW="11";
        //重扫
        public static final String STATE_RE="31";
        // 补扫
        public static final String STATE_ADD="32";
        // 替换
        public static final String STATE_REPLACE="33";
    }

    //=================客户端接口标识=====================
    public static class ClientTitleCode {
        //获取最新版本
        public static final String CLIENT_GET_LASTED_VERSION = "get_version";
        //登录接口
        public static final String CLIENT_LOGIN_SYSTEM="login";
        //加载配置
        public static final String CLIENT_LOAD_CONFIG_INFO="load_config";
        //影像数据上传前判断一下数据是否支持进入系统
        public static final String CLIENT_BEFORE_UPLOAD_CHECK ="before_check";
        //上传影像信息
        public static final String CLIENT_UPLOAD_PICTURE ="upload_picture";
        //上传发票信息
        public static final String CLIENT_UP_INVOICE_INFO ="up_invoice";

        public static final String CLIENT_CREATE_BATCH_NO = "batch_no";

        public static final String CLIENT_END_UP = "end";
    }

    //==================批次号生产规则的头部代码================================
    public static class BizTypeToolCode {
        //增值税专用发票AP类型的批次号开头
        public static final String AP_VATS_INVOICE_CODE_HEADER = "INA";
        //增值税普通发票AP类型的批次号开头
        public static final String AP_VATN_INVOICE_CODE_HEADER = "INB";
        //ap形式发票批次号开头
        public static final String AP_PROFORMA_INVOICE_CODE_HEADER = "INC";
        //ap普通发票批次号开头
        public static final String AP_NORMAL_INVOICE_CODE_HEADER = "IND";
        //费用增值税专用发票批次号开头
        public static final String EXPENSE_VATS_INVOICE_CODE_HEADER = "INE";
        //银行类默认头部
        public static final String BANK_BATCH_HEADER = "IBK";

        public static final String OTHER_SHOW = "OTH";
        //默认header
        public static final String DEFAULT_HEADER = "DFA";
    }

    //======================================业务管理相关================================
    /**
     * 业务类型
     */
    public static class BizTypeCode{
        //ap业务代码
        public static final String AP_TYPE_CODE = "BIZ_AP";
        //费用业务代码
        public static final String EXPENSE_TYPE_CODE = "BIZ_EXPENSE";
        //银行手续业务代码
        public static final String BANK_TYPE_CODE = "BIZ_BANK";
        //其他业务代码
        public static final String OTHER_TYPE_CODE = "BIZ_OTHER";
        //报账单业务代码
        public static  final  String ACCOUNT_TYPE_CODE = "01";
        //银行回单业务代码
        public static  final  String BANK_TICKET_TYPE_CODE = "03";
    }

    /**
     * 业务类型Enum
     */
    public static enum BizTypeCodeEnum {
        BIZ_AP(BizTypeCode.AP_TYPE_CODE, "AP", "AP类"),
        BIZ_EXPENSE(BizTypeCode.EXPENSE_TYPE_CODE, "EXP", "费用类"),
        BIZ_BANK(BizTypeCode.BANK_TYPE_CODE, "BK", "银行类"),
        BIZ_OTHER(BizTypeCode.OTHER_TYPE_CODE, "OTHER", "其他类");

        private String bizTypeCode;
        private String bizTypeText;
        private String bizTypeTextCh;
        private static Map<String, BizTypeCodeEnum> lookup;

        public String getBizTypeCode() {
            return bizTypeCode;
        }

        public String getBizTypeText() {
            return bizTypeText;
        }

        public String getBizTypeTextCh() {
            return bizTypeTextCh;
        }

        public static String text(String code) {
            return lookup.get(code).getBizTypeText();
        }

        public static String textCh(String code) {
            return lookup.get(code).getBizTypeTextCh();
        }

        private BizTypeCodeEnum(String code, String text, String textCh) {
            this.bizTypeCode = code;
            this.bizTypeText = text;
            this.bizTypeTextCh = textCh;
        }

        static {
            lookup = new HashMap();
            for (BizTypeCodeEnum et : EnumSet.allOf(BizTypeCodeEnum.class)) {
                lookup.put(et.getBizTypeCode(), et);
            }
        }
    }

    /**
     * 单证类型
     */
    public static class BillTypeCode{
        //增值税专用发票(发票联)
        public static final String VATS_INVOICE = "VAT_S_INV";
        //增值税专用发票(抵扣联)
        public static final String VATS_INVOICE_DEDU = "VAT_S_INV_DEDU";
        //增值税普通发票
        public static final String VATN_INVOICE = "VAT_N_INV";
        //形式发票
        public static final String PROFORMA_INVOICE = "P_INV";
        //普通发票
        public static final String NORMAL_INVOICE = "NORMAL_INV";
        //条码封面
        public static final String BARCODE_COVER = "COVER";
        //其他附件
        public static final String OTHER_ATTACHMENT = "ATTACHMENT";
        //普通单证
        public static final String NORMAL_BILL = "1001";

        //银行回单
        public static final String BANK_TICKET = "BANK_TICKET";
        //增值税电子普通发票
        public static final String EVAT_N_E_INV = "EVAT_N_E_INV";
        //多发票
        public static final String MULTI_INVS = "MULTI_INVS";
        //费用凭证
        public static final String FEE_CENDENTIALS = "FEE_CENDENTIALS";
        //支付凭证
        public static final String PAY_CENDENTIALS = "PAY_CENDENTIALS";
        //封面
        public static final String NORMAL_COVER = "ACCT_COVER";
    }

    //====================公司信息获取========================
    public static class GetCompanyType{
        //获取公司
        public static final String COMPANY_ONLY = "10";
        //获取供应商
        public static final String SUPPLIER_ONLY="01";
        //供应商和公司
        public static final String SUPPLIER_COMPANY="11";
    }
    //==============发票类型======================
    public static class InvoiceType{
        //形式发票
        public static final String P_INV="3";
        //增值税专业发票
        public static final String VATS_INV="1";
        //增值税普通发票
        public static final String VATN_INV="2";
        //电子普通发票
        public static final String INV_NORMAL="4";
        //多发票
        public static final String MUTIPART_NORMAL="10";
    }


    //===========================扫描任务相关======================
    /**
     * 校验状态
     */
    public static class CheckStatus {
        //待校验
        public static final String WAITTING = "01";
        //校验通过
        public static final String PASS = "11";
        //校验不通过
        public static final String NOT_PASS = "12";
    }
    /**
     * 匹配状态
     */
    public static class MatchStatus {
        //待匹配
        public static final String NOMATCH = "0";
        //待匹配
        public static final String WAITTING = "01";
        //匹配成功
        public static final String SUCCESS = "11";
        //匹配不成功
        public static final String FAIL = "12";
    }



    /**
     * EdocHeader的bizStatus
     */
    public static class BizStatus{
        //待采集，用于创建影像接口时，标注单据状态
        public static final String WAIT_SCAN = "07";
        //正在上传影像
        public static final String ON_UP_DATA = "00";
        //已采集
        public static final String WAIT_CONFIRM="01";
        //待补录
        public static final String WAIT_ADD_DATA = "02";
        //档案岗补扫,,专门为档案岗补扫使用
        public static final String ARCHIVE_WAIT_ADD_DATA = "102";
        //待扫描岗确认
        public static final String WAIT_SCANER_CONFIRM = "03";
        //待校验岗确认
        public static final String WAIT_CHECKOR_CONFIRM = "04";
        //校验岗已确认
        public static final String CHECKOR_ALREADY_CONFIRM = "05";
        //待推送
        public static final String WAIT_PUSH="11";
        //待作废
        public static final String WAIT_INVALID = "12";
        //作废
        public static final String INVALID="21";
        //重扫
        public static final String AGAIN_SCAN="32";
        //单票重扫
        public static final String SINGLE_AGAIN_SCAN="31";
        // 已通知
        public static final String INFORMED = "06";
        //多寄确认之后，在扫描岗重新等待推送到VSS
        public static final String WAIT_REPUSH_VSS = "32";
        //完成
        public static final String END="22";
        //撤销
        public static final String REVOKE="23";
        //推送完成
        public static final String PUSH_SUCC = "31";
        //推送失败
        public static final String PUSH_FAILED = "40";
        //有冲突发票的扫描任务
        public static final String CONFILICT_EDOCNO = "50";

        //待归档
        public static final String WAIT_ARCHIVED = "25";

        //归档中
        public static final String TOBEARCHIVED = "27";
        //待分盒
        public static final String WAIT_BOXING = "26";
        //已归档
        public static final String ARCHIVED = "28";

        //待匹配
        public static final String WAIT_MATCH = "33";

        //凭证冲销
        public static final String WRITE_OFF = "99";
    }

    //===========================扫描任务相关======================
    /**
     * 校验状态
     */
    public static class InvoiceCheckStatus {
        //待校验
        public static final String WAITTING = "01";
        //校验通过
        public static final String PASS = "11";
        //校验不通过
        public static final String NOT_PASS = "12";
        //发票冲突
        public static final String CONFLICT = "13";
    }

    /**
     * EdocHeader的bizStatus
     */
    public static class BatchStatus{
        //正在上传影像
        public static final String ON_UP_DATA = "00";
        //待校验
        public static final String WAIT_CONFIRM="01";
        //02在批次中表示待打印
        public static final String WAIT_PRINT = "02";
        //03在批次中表示已经打印
        public static final String PRINT_END = "03";
    }

    public static class EdocScanOperation {
        //单据重扫
        public static final String EDOC_RE_SCAN = "1";
        //单据补扫
        public static final String EDOC_ADD_SCAN = "2";
        //单张影像重扫
        public static final String EDOC_SINGLE_IMAGE_RE_SCAN = "3";
    }

    /**
     * EdocHeader的edocPhysicalStatus
     */
    public static class EdocPhysicalStatus{
        //正常寄送中
        public static final String NORMAL_SENDING = "00";

        //退单寄送中
        public static final String BACK_SENDING = "10";

        //正常签收
        public static final String NORMAL_RECEIVED = "01";

        //未操作 - 正常签收(无影像操作)
        public static final String UNHANDEL_NORMAL_RECEIVED = "101";

        //退单签收
        public static final String BACK_RECEIVED = "11";
        //未交单
        public static final String TO_SEND = "99";
        //已打包待寄送
        public static final String WAIT_SEND = "20";
        //待签收
        public static final String WAIT_RECEIVER = "21";
        //已签收
        public static final String RECEIVER = "22";
    }

    /**
     * 扫描任务推送结果编码
     */
    public static class PushResultCode {
        //推送至sap成功
        public static final String SAP_S = "01:1";
        //推送至sap失败
        public static final String SAP_F = "01:0";
        //推送至fssc成功
        public static final String FSSC_S = "02:1";
        //推送至fssc失败
        public static final String FSSC_F = "02:0";
        //推送至SharePoint成功
        public static final String SHAREPOINT_S = "03:1";
        //推送至SharePoint失败
        public static final String SHAREPOINT_F = "03:0";
    }

    //==============================通用定义==============================
    /**
     * 是否
     */
    public static class YesNo{
        //是
        public static final String YES = "1";
        //否
        public static final String NO = "0";
    }
    /**
     * 删除标志
     */
    public static class DeleteFlag{
        //已经删除
        public static final int DEL_YES=1;
        //未删除
        public static final int DEL_NO=0;
    }

    /**
     * 图片类型
     */
    public static class PictureType {
        public static final String JPG="jpg";
        public static final String PNG="png";
        public static final String TIF="tif";
    }

    //======================扫描任务校验配置===========================
    public static class BizCheckClass{

        public static final String INVOICE_RULE_CHECK="0";

        public static final String INVOICE_DETAIL_CHECK="1";

        public static final String INVOICE_REAL_CHECK="0";

        public static final String NUM_CHECK="0";

        public static final String MATCHED_CHECK="0";
    }

    //=================公司与供应商之间的维度控制=====================
    public static class DptCode{
        //公司
        public static String COMPANY_DPT_CODE="DPT_COMPANY";
        //供应商
        public static String VENDOR_DPT_CODE="DPT_VENDOR";
        //业务类型
        public static String BIZ_DPT_CODE = "DPT_BIZ";
    }

    //====================由于对于批次信息和任务信息都放在HEADER表里面实现了分级==============================
    public static class HeaderLevel{
        //批次等级
        public static int BATCH_LEVEL = 1;
        //报账单等级
        public static int BILL_LEVEL = 2;
    }

    public static class OcrType{

        public static String OCR_NONE="NONE";

        public static String OCR_INVOICE="INVOICE";

        public static String OCR_BARCODE="BARCODE";

        public static String OCR_CODE39 = "CODE39";

        public static String OCR_PDF417 = "PDF417";

        public static String OCR_QRCODE = "QRCODE";

        public static String OCR_TEXT = "TEXT";
    }

    public static class EdocNoMoreLength{

        public static int EXPENSE_LENGTH = 18;

        public static int PROOF_LENGTH = 21;

        public static int NO_PROOF_LENGTH = 16;

        public static int SALE_CONTRACT_LENGTH = 17;

        public static int NORMAL_CONTRACT_LENGTH = 16;
    }

    //==================发票金额的币种=====================
    public static class EdocCurrency{
        //默认币种
        public static String DEFAULT_CURRENCY = "CNY";
        //美元
        public static String USD = "USD";
        //欧元
        public static String EUR = "EUR";
        //英镑
        public static String GBP = "GBP";
        //日元
        public static String JPY = "JPY";
        //韩元
        public static String KRW = "KRW";
        //加元
        public static String CAD = "CAD";
    }

    public static class ImagePath{

        /*public static String ROOT_PATH = "/home/ashare_repo";*/
//        public static String ROOT_PATH = "/home/leon/edoc/image";
        public static String ROOT_PATH = "/ashare/renrenle/image";

    }

    //==========================第三方接口相关============================
    public static class ThirdInterface{
        /**
         * sharepoint操作方式
         */
        public static String OPT_ADD = "I";//新增
        public static String OPT_DEL = "D";//删除

        /**
         * SAP发票类型
         */
        public static String VAT_S = "S";//增专
        public static String VAT_N = "C";//增普
        public static String VAT_X = "X";//形式发票
        public static String VAT_T = "T";//普通发票

        /**
         * 推送至vss的类型
         */
        public static String VSS_IMPORTTYPE_0 = "0";//新建
        public static String VSS_IMPORTTYPE_10 = "10";//复核信息-确认无误后导入
        public static String VSS_IMPORTTYPE_11 = "11";//复核信息-修正后导入
        public static String VSS_IMPORTTYPE_20 = "20";//错寄核查-未错寄导入
        public static String VSS_IMPORTTYPE_21 = "21";//错寄核查-确认错寄导入

        /**
         * 返回结果
         */
        public static String RESULT_SUCC = "S";//成功
        public static String RESULT_FAIL = "E";//失败

        /**
         * 推送开关
         */
        public static String PUSH_OFF = "off";//关闭推送
        public static String PUSH_ON = "on";//开启推送
    }


    /**
     * 认证模块系统来源复制
     * 来源系系统
     * TODO: 2017/8/18
     */
    public static final class InvSource {

        /**
         * AP应付系统
         */
        public final static String AP = "AP";

        /**
         * 费用
         */
        public final static String EXPENSES = "EXPENSES";

        /**
         * 银行类费用
         */
        public final static String BANK = "BANK";


    }

    /**
     * 人人乐发票来源
     */
    public static final class rrInvSource {

        /**
         * 客户端上传
         */
        public static final String CLIENT = "1";
        /**
         * 人工录入
         */
        public static final String MANUALL = "2";

    }

    /**
     * 推送失败最大次数
     */
    public final static Integer PUSH_FAILED_MAX_COUNT = 5;



    /**
     * 水印类型
     * 下面各类型请根据实际情况修改
     */
    public static enum WatermarkType {
        TYPE1("type1", "类型1"),TYPE2("type2", "类型2"),TYPE3("type3", "类型3"),TYPE4("type4", "类型4");

        private String value;
        private String desc;

        WatermarkType(String value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public String getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }

        public static WatermarkType getByValue(String value) {
            for (WatermarkType type : WatermarkType.values()){
                if(value.equals(type.getValue())){
                    return type;
                }
            }
            return null;
        }
    }

    //文本颜色
    public static enum WatermarkTextColor {
        LIGHT_GRAY("LIGHT_GRAY", "浅灰", Color.lightGray),GRAY("GRAY", "灰", Color.gray),DARK_GRAY("DARK_GRAY", "深灰", Color.darkGray)
        ,BLACK("BLACK", "黑", Color.black),RED("RED", "红", Color.red),PINK("PINK", "粉红", Color.pink) ,ORANGE("ORANGE", "橙", Color.orange)
        ,YELLOW("YELLOW", "黄", Color.yellow),GREEN("GREEN", "绿", Color.green),MAGENTA("MAGENTA", "品红", Color.magenta)
        ,CYAN("CYAN", "青", Color.cyan),BLUE("BLUE", "蓝", Color.blue);

        private String value;
        private String desc;
        private Color color;

        WatermarkTextColor(String value, String desc, Color color) {
            this.value = value;
            this.desc = desc;
            this.color = color;
        }

        public String getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }

        public Color getColor() { return color; }

        public static WatermarkTextColor getByValue(String value) {
            for (WatermarkTextColor type : WatermarkTextColor.values()){
                if(value.equals(type.getValue())){
                    return type;
                }
            }
            return null;
        }
    }


    /**
     * 发票验真状态
     */
    public static class InvoiceCheckReal {
        /**
         * 待验真
         */
        public static final String WAITING_CHECK = "0";
        /**
         * 验真中
         */
        public static final String CHECKING = "1";
        /**
         * 验真失败
         */
        public static final String CHECK_FAILED = "2";
        /**
         * 存疑
         */
        public static final String DOUBTFUL = "3";
        /**
         * 真票
         */
        public static final String REAL = "4";

    }

    public  static class AccountingBoxArchivedStatus{
        //未归档
        public static final String ARCHIVED_NO = "0";
        //已归档
        public static final String ARCHIVED_YES = "1";
    }

    public  static class AccountingBoxStatus{
        //分册后分盒
        public static final String SECTION = "2";
        //单据分盒
        public static final String BOXING = "1";
    }

    /**
     * 共享接口编号
     */
    public  static class FsscServiceId{
        //是否待认证
        public static final String IS_WAIT_CERT = "BIZ_API_YX_FPRZ";
        //获取凭证
        public static final String GET_VOUCHER = "BIZ_API_YX_PZXX";
        //是否可归档
        public static final String IS_CAN_ARCHIVE = "BIZ_API_YX_GDZT";
        //获取费用类型
        public static final String GET_COST_TYPE = "MDM_API_OA_YWSX";
    }

    public static class FsscEdocStatus{
        // 未处理
        public static final Integer UN_HANDLE = 0;


        // 可认证
        public static final Integer CAN_CERT = 1;


        // 可归档
        public static final Integer CAN_ARCHIVE = 1;

        //已获取费用凭证
        public static final Integer HAS_EXPENSE_VOUCHER = 1;
        // 已获取支付凭证
        public static final Integer HAS_PAY_VOUCHER = 2;
        // 已获取凭证PDF
        public static final Integer HAS_PDF = 3;
    }

    public  static class CredentialsType{
        //费用凭证
        public static final String EXPEN_CRED = "1";
        //支付凭证
        public static final String PAY_CRED = "2";
    }

    public static class FitCheckTaskStatus {
        //未处理
        public static final String WAITING_DEAL = "0";
        //已处理
        public static final String DEAL = "1";
        //回退未处理
        public static final String BACK_WAITING_DEAL = "2";
    }

    /**
     * 单据扫描上传种类
     */
    public static class UploadType {
        // 扫描+上传
        public static final String SCAN_ADN_UPLOAD = "0";
        // 仅扫描
        public static final String SCAN_ONLY = "1";
        // 仅上传
        public static final String UPLOAD_ONLY = "2";
        // 既没有扫描也没有上传
        public static final String NO_SCAN_NO_UPLOAD = "3";
    }

    /**
     * 用户组
     */
    public static class UserGroupCode {
        /**
         * 档案岗用户组代码
         */
        public static final String ARCHIVE_GROUP = "GROUP_NEWHOPE_ARCHIVE";
    }

    public static final String SYSTEM_ID = "EDOC";
    public static final String EBS_ID = "EBS";
    public static final String JDE_ID = "JDE";
    public static final String OA_ID = "OA";


    /**
     * operation_record.record_operation_code
     */
    public static class OperationRecordType {
        // 凭证冲销
        public static final String CANCEL_ARCHIVE = "cancelArc";
        // 邮包退回
        public static final String SEND_BACK_BAG = "sendBackBag";
    }

    /**
     * RENRENLE封单状态
     */
    public static class Seal {
        public static final String UNSEALED = "1";
        public static final String SEALED = "2";

    }

    public static class Review {
        /**
         * 2:审核中
         */
        public static final String TASK_AUDIT = "2";
        /**
         * 3:审核通过
         */
        public static final String TASK_PASS = "3";
        /**
         * 4:审核不通过
         */
        public static final String TASK_FAILED = "4";
    }

    /**
     * 对外接口记录标识
     */
    public static class getCode {
        public static final String success = "Y";
        public static final String fail= "N";
    }
}
