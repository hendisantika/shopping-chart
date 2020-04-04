package com.hendisantika.shoppingchart.validator;

import com.hendisantika.shoppingchart.dao.ProductDAO;
import com.hendisantika.shoppingchart.entity.Product;
import com.hendisantika.shoppingchart.form.ProductForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by IntelliJ IDEA.
 * Project : shopping-chart
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 22/07/18
 * Time: 05.59
 * To change this template use File | Settings | File Templates.
 */
@Component
public class ProductFormValidator implements Validator {

    @Autowired
    private ProductDAO productDAO;

    // This validator only checks for the ProductForm.
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == ProductForm.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductForm productForm = (ProductForm) target;

        // Check the fields of ProductForm.
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "NotEmpty.productForm.code");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.productForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty.productForm.price");

        String code = productForm.getCode();
        if (code != null && code.length() > 0) {
            if (code.matches("\\s+")) {
                errors.rejectValue("code", "Pattern.productForm.code");
            } else if (productForm.isNewProduct()) {
                Product product = productDAO.findProduct(code);
                if (product != null) {
                    errors.rejectValue("code", "Duplicate.productForm.code");
                }
            }
        }
    }

}