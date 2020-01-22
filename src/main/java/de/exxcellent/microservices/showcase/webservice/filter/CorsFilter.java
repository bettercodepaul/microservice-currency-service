package de.exxcellent.microservices.showcase.webservice.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Filter to allow CORS
 *
 * @author Felix Riess, eXXcellent solutions consulting & software gmbh
 * @since 22.01.2020
 */
@Provider
public class CorsFilter implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        containerResponseContext.getHeaders().add(
                        "Access-Control-Allow-Origin", "*");
        containerResponseContext.getHeaders().add(
                        "Access-Control-Allow-Credentials", "true");
        containerResponseContext.getHeaders().add(
                        "Access-Control-Allow-Headers",
                        "origin, content-type, accept, authorization");
        containerResponseContext.getHeaders().add(
                        "Access-Control-Allow-Methods",
                        "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }
}
