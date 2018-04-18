package practice2;

import java.math.BigDecimal;
import java.util.List;

public class Receipt {

    public Receipt() {
        tax = new BigDecimal(0.1);
        tax = tax.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal tax;

    public double CalculateGrandTotal(List<Product> products, List<OrderItem> items) {
        BigDecimal subTotal = calculateSubtotal(products, items).subtract(calculateDiscountTotal(products,items));
        BigDecimal taxTotal = calculateTaxTotal(subTotal);
        BigDecimal grandTotal = subTotal.add(taxTotal);
        return setScaleToDouble(grandTotal);
    }

    private BigDecimal calculateTaxTotal(BigDecimal subTotal){
        return subTotal.multiply(tax);
    }

    private double setScaleToDouble(BigDecimal grandTotal){
       return grandTotal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    private BigDecimal calculateDiscountTotal(List<Product> products, List<OrderItem> items) {
        BigDecimal discountTotal = new BigDecimal(0);

        for (Product product : products) {
            OrderItem curItem = findOrderItemByProduct(items, product);

            discountTotal=discountTotal.add(product.getPrice()
                    .multiply(product.getDiscountRate())
                    .multiply(new BigDecimal(curItem.getCount())));

        }
        return discountTotal;
    }

    private OrderItem findOrderItemByProduct(List<OrderItem> items, Product product) {

        OrderItem curItem = null;
        for (OrderItem item : items) {
            if (item.getCode() == product.getCode()) {
                curItem = item;
                break;
            }
        }
        return curItem;
    }

    private BigDecimal calculateSubtotal(List<Product> products, List<OrderItem> items) {
        BigDecimal subTotal = new BigDecimal(0);
        for (Product product : products) {
            OrderItem item = findOrderItemByProduct(items, product);
            BigDecimal itemTotal = product.getPrice().multiply(new BigDecimal(item.getCount()));
            subTotal = subTotal.add(itemTotal);
        }
        return subTotal;
    }
}
