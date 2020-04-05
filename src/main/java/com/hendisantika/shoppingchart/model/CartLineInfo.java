package com.hendisantika.shoppingchart.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * Project : shopping-chart
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 20/07/18
 * Time: 06.54
 * To change this template use File | Settings | File Templates.
 */
@Data
@AllArgsConstructor
public class CartLineInfo {
    private ProductInfo productInfo;
    private int quantity;

    public CartLineInfo() {
        this.quantity = 0;
    }

    public double getAmount() {
        return this.productInfo.getPrice() * this.quantity;
    }

}
