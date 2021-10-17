package com.dimasguilherme.wishlist;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "whishlist_itens")
@IdClass(WishlistItemId.class)
public class WishlistItem extends PanacheEntityBase {
    @Id
    private String email;
    @Id
    @Column(name = "product_id")
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

}
