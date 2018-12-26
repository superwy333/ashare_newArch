package utils;

import java.math.BigDecimal;

/** 金税计算
 * Created by Charles on 2017/6/14.
 */
public class TaxCalculator {

    /*
     * 人民币小数精确的位数
     */
    private static final int CURRENCY_RMB_SCALE = 2;

    /**
     * 金税计算时默认小数精确位数
     */
    private static final int DEFALUT_CACULATOR_SCALE = 12;

    /**
     * 根据金额和税率，计算税额（保留2为小数）
     * @param amount
     * @param taxRate
     * @return
     */
    public static BigDecimal getTaxByAmount(BigDecimal amount, BigDecimal taxRate) {
        return ArithmeticUtil.mul(amount, taxRate, CURRENCY_RMB_SCALE);
    }

    /**
     * 根据含税金额和税率，计算金额（保留2为小数）
     * @param taxAmount
     * @param taxRate
     * @return
     */
    public static BigDecimal getAmount(BigDecimal taxAmount, BigDecimal taxRate) {
        return ArithmeticUtil.div(taxAmount, ArithmeticUtil.add(BigDecimal.ONE, taxRate), CURRENCY_RMB_SCALE);
    }

    /**
     * 根据含税金额和税率，计算税额（保留2为小数）
     * @param  taxAmount 含税金额
     * @param taxRate 税率
     * @return
     */
    public static BigDecimal getTaxByTaxAmount(BigDecimal taxAmount, BigDecimal taxRate) {
        return ArithmeticUtil.mul(
                ArithmeticUtil.div(taxAmount, ArithmeticUtil.add(BigDecimal.ONE, taxRate), DEFALUT_CACULATOR_SCALE),
                taxRate,
                CURRENCY_RMB_SCALE);
    }

    /**
     * 根据金额和数量，计算单价，保留scale指定小数位
     * @param amount 金额（不含税）
     * @param quantity 数量
     * @param scale 保留小数位
     * @return 单价
     */
    public static BigDecimal getPriceByAmountAndQuantity(BigDecimal amount, BigDecimal quantity, int scale) {
        return ArithmeticUtil.div(amount, quantity, scale);
    }

    /**
     * 根据含税金额和数量，计算含税单价，保留scale指定小数位
     * @param taxAmount 含税金额
     * @param quantity 总数量
     * @param scale 保留小数位
     * @return 含税单价
     */
    public static BigDecimal getTaxPriceByTaxAmountAndQuantity(BigDecimal taxAmount, BigDecimal quantity, int scale) {
        return ArithmeticUtil.div(taxAmount, quantity, scale);
    }

    /**
     * 根据金额和单价，计算数量，保留scale指定小数位
     * @param amount 金额（不含税）
     * @param price 单价
     * @param scale 数量保留小数位
     * @return 数量
     */
    public static BigDecimal getQuantityByAmountAndPrice(BigDecimal amount, BigDecimal price, int scale) {
        return ArithmeticUtil.div(amount, price, scale);
    }

    /**
     * 根据金额和税率，计算税额（保留2为小数）
     * @param amount
     * @param taxRate
     * @return
     */
    public static BigDecimal getcalcTaxByAmount(BigDecimal amount, BigDecimal taxRate) {
        return ArithmeticUtil.mul(amount, taxRate, DEFALUT_CACULATOR_SCALE);
    }
}
