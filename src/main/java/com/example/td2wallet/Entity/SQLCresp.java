package com.example.td2wallet.Entity;

import java.math.BigDecimal;

public class SQLCresp {
    private String category_name;
    private BigDecimal total_amount;

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public BigDecimal getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(BigDecimal total_amount) {
        this.total_amount = total_amount;
    }

    public SQLCresp(String category_name, BigDecimal total_amount) {
        this.category_name = category_name;
        this.total_amount = total_amount;
    }

    @Override
    public String toString() {
        return "SQLCresp{" +
                "category_name='" + category_name + '\'' +
                ", total_amount=" + total_amount +
                '}';
    }
}
