package de.exxcellent.microservices.showcase.webservice.exceptionmapper;

import de.exxcellent.microservices.showcase.common.errorhandling.exception.TechnicalException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Maps {@link TechnicalException}s to HTTP responses
 *
 * @author Felix Riess, eXXcellent solutions consulting & software gmbh
 * @since 21.01.2020
 */
@Provider
public class TechnicalExceptionMapper implements ExceptionMapper<TechnicalException> {

    @Override
    public Response toResponse(final TechnicalException e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                       .entity(e.getMessage())
                       .type(MediaType.TEXT_PLAIN_TYPE)
                       .build();
    }
}
