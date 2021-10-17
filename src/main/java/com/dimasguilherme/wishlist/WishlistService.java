package com.dimasguilherme.wishlist;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.dimasguilherme.product.Product;
import com.dimasguilherme.product.ProductService;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.client.exception.ResteasyWebApplicationException;

@ApplicationScoped
public class WishlistService {
    @Inject
    @RestClient
    ProductService productService;

    public void add(SecurityContext securityContext, String productId) {
        try {
            productService.get(productId);
        } catch (ResteasyWebApplicationException e) {
            throw new WebApplicationException("Nao foi possivel localizar o produto", Response.Status.BAD_REQUEST);
        }
        WishlistItem item = new WishlistItem();
        item.setEmail(securityContext.getUserPrincipal().getName());
        item.setProductId(productId);
        WishlistItem.persist(item);
    }

    public List<Product> listByEmail(String email) {
        List<WishlistItem> list = WishlistItem.list("email like ?1", email);
        ArrayList<Product> listProducts = new ArrayList<>();
        for (WishlistItem l : list) {
            listProducts.add(productService.get(l.getProductId()));
        }
        return listProducts;
    }
}
