package com.dimasguilherme.client;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.dimasguilherme.exception.MyApplicationException;
import com.dimasguilherme.utils.Validators;

@Path("/client")
public class ClientResource {
    @POST
    @Transactional
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insert(Client client) throws MyApplicationException {
        Validators.isValidEmailAddressRegex(client.getEmail());
        Client.insert(client);
        return Response.ok(client).status(201).build();
    }

    @GET
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Client> listAll() {
        return Client.listAll();
    }

    @GET
    @RolesAllowed({ "admin", "client" })
    @Path("/{email}/")
    @Produces(MediaType.APPLICATION_JSON)
    public Client getById(@Context SecurityContext securityContext, final @PathParam("email") String email)
            throws MyApplicationException {
        Validators.isClientAuthenticated(securityContext, email);
        return (Client) Client.findByIdOptional(email).orElseThrow();
    }

    @DELETE
    @Path("/{email}/")
    @RolesAllowed("admin")
    @Transactional
    public void delete(final @PathParam("email") String email) {
        Client.deleteById(email);
    }

    @PUT
    @Path("/{email}/")
    @RolesAllowed({ "admin", "client" })
    @Transactional
    public void uptade(@Context SecurityContext securityContext, final @PathParam("email") String email, Client client)
            throws MyApplicationException {
        Validators.isValidEmailAddressRegex(client.getEmail());
        Validators.isClientAuthenticated(securityContext, email);
        Client.update("name = ?1, email = ?2 where email like ?3", client.getName(), client.getEmail(), email);
    }
}
