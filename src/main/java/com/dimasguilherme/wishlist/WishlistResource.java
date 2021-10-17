package com.dimasguilherme.wishlist;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.dimasguilherme.exception.MyApplicationException;
import com.dimasguilherme.product.Product;
import com.dimasguilherme.utils.Validators;

@Path("/wishlist")
public class WishlistResource {
    @Inject
    WishlistService service;

    @GET
    @Path("/add/{productId}/")
    @RolesAllowed("client")
    @Transactional
    public Response add(@Context SecurityContext securityContext, final @PathParam("productId") String productId) {
        service.add(securityContext, productId);
        return Response.ok("Produto adicionado na lista de favoritos").status(200).build();
    }

    @GET
    @Path("/remove/{productId}/")
    @RolesAllowed("client")
    @Transactional
    public Response remove(@Context SecurityContext securityContext, final @PathParam("productId") String productId) {
        WishlistItem.delete("email like ?1 and product_id like ?2", securityContext.getUserPrincipal().getName(),
                productId);
        return Response.ok("Produto removido da lista de favoritos").status(200).build();
    }

    @GET
    @Path("/{email}/")
    @RolesAllowed({ "admin", "client" })
    public List<Product> listByEmail(@Context SecurityContext securityContext, final @PathParam("email") String email)
            throws MyApplicationException {
        Validators.isClientAuthenticated(securityContext, email);
        return service.listByEmail(email);
    }
}
