package com.dimasguilherme.wishlist;

import java.io.Serializable;

public class WishlistItemId implements Serializable {
    private String email;
    private String productId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        WishlistItemId id = (WishlistItemId) o;

        if (id.getEmail() != this.email) {
            return false;
        }

        if (id.getProductId() != this.productId) {
            return false;
        }

        return true;
    }
}
