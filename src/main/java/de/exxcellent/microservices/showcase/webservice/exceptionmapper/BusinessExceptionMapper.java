package de.exxcellent.microservices.showcase.webservice.exceptionmapper;

import de.exxcellent.microservices.showcase.common.errorhandling.ErrorCode;
import de.exxcellent.microservices.showcase.common.errorhandling.exception.BusinessException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Maps {@link BusinessException}s to HTTP responses.
 *
 * @author Felix Riess, eXXcellent solutions consulting & software gmbh
 * @since 21.01.2020
 */
@Provider
public class BusinessExceptionMapper implements ExceptionMapper<BusinessException> {

    @Override
    public Response toResponse(final BusinessException e) {
        final Response.Status responseStatus;
        final String errorDescription = e.getErrorCode().getDescription();

        if(errorDescription.equals(ErrorCode.EMPTY_LIST_ERROR.getDescription())) {
            responseStatus = Response.Status.NO_CONTENT;
        } else if (errorDescription.equals(ErrorCode.NOT_FOUND_ERROR.getDescription())) {
            responseStatus = Response.Status.NOT_FOUND;
        } else {
            responseStatus = Response.Status.BAD_REQUEST;
        }
        return Response.status(responseStatus)
                       .entity(e.getMessage())
                       .type(MediaType.TEXT_PLAIN_TYPE)
                       .build();
    }
}
