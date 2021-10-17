package com.dimasguilherme.utils;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.core.SecurityContext;

import com.dimasguilherme.client.Client;
import com.dimasguilherme.exception.MyApplicationException;

public class Validators {
    public static void isValidEmailAddressRegex(String email) throws MyApplicationException {
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                return;
            }
        }
        throw new MyApplicationException("E-mail invalido");
    }

    public static void isClientAuthenticated(SecurityContext securityContext, String email)
            throws MyApplicationException {
        if (!securityContext.isUserInRole("admin")) {
            Optional<Client> clientOptional = Client.findByIdOptional(email);
            Client client = clientOptional.orElse(null);
            if (client == null || !client.getEmail().equals(securityContext.getUserPrincipal().getName())) {
                throw new MyApplicationException(
                        "O cliente logado nao pode acessar esse recurso para o e-mail informado.");
            }
        }
    }
}
