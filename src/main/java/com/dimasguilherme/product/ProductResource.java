package com.dimasguilherme.product;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/product")
public class ProductResource {
    @Inject
    @RestClient
    ProductService service;

    @GET
    @Path("/{id}/")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Product get(final @PathParam("id") String id) {
        return service.get(id);
    }

    @GET
    @Path("/")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Page list(final @QueryParam("page") int page) {
        return service.list(page);
    }
}
