package com.dimasguilherme.product;

import java.util.List;

public class Page {
    private Meta meta;
    private List<Product> products;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
