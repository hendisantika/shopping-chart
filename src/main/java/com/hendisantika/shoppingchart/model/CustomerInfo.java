package com.hendisantika.shoppingchart.model;

import com.hendisantika.shoppingchart.form.CustomerForm;

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
public class CustomerInfo {
    private String name;
    private String address;
    private String email;
    private String phone;

    private boolean valid;

    public CustomerInfo() {

    }

    public CustomerInfo(CustomerForm customerForm) {
        this.name = customerForm.getName();
        this.address = customerForm.getAddress();
        this.email = customerForm.getEmail();
        this.phone = customerForm.getPhone();
        this.valid = customerForm.isValid();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
