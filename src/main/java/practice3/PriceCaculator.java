package practice3;

import java.math.BigDecimal;
import java.util.List;

public class PriceCaculator {

    public BigDecimal calculate(List<OrderLineItem> orderLineItemList, List<BigDecimal> discounts, BigDecimal tax) {

        BigDecimal subTotal = getSubTotal(orderLineItemList).subtract(getDiscountTotal(discounts));
        BigDecimal tax1 = getTaxTotal(subTotal, tax);
        return getGrandTotal(subTotal, tax1);
    }

    public BigDecimal getSubTotal(List<OrderLineItem> orderLineItemList) {
        BigDecimal subTotal = new BigDecimal(0);
        for (OrderLineItem lineItem : orderLineItemList) {
            subTotal = subTotal.add(lineItem.getPrice());
        }
        return subTotal;
    }

    public BigDecimal getGrandTotal(BigDecimal subTotal, BigDecimal tax) {
        return subTotal.add(tax);
    }

    public BigDecimal getDiscountTotal( List<BigDecimal> discounts) {
        BigDecimal discountTotal = new BigDecimal(0);
        for (BigDecimal discount : discounts) {
            discountTotal = discountTotal.add(discount);
        }
        return discountTotal;
    }

    public BigDecimal getTaxTotal(BigDecimal subTotal, BigDecimal tax) {
        return subTotal.multiply(tax);
    }
}
