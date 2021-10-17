package com.dimasguilherme.product;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/product")
@RegisterRestClient
public interface ProductService {
    @GET
    @Path("/{id}/")
    @Produces(MediaType.APPLICATION_JSON)
    public Product get(final @PathParam("id") String id);

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Page list(final @QueryParam("page") int page);
}
