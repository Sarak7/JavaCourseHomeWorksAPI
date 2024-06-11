package org.example.HW5.dto;

import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import org.example.HW5.models.Employees;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.net.URI;

@Provider
public class PoweredByFiler implements ContainerRequestFilter {



    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        URI uri = requestContext.getUriInfo().getAbsolutePath();
        System.out.println(uri);
    }
}