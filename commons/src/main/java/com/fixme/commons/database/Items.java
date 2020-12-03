package com.fixme.commons.database;

public class Items {
    private String code;
    private String product;

    public Items(String code, String product){
        this.code = code;
        this.product = product;
    }

    public String getCode() {
        return code;
    }

    public String getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "Items{" +
                "code='" + code + '\'' +
                ", product='" + product + '\'' +
                '}';
    }
}
