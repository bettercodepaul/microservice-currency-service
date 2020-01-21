package de.exxcellent.microservices.showcase.common.errorhandling;

/**
 * An interface to be implemented by all error codes.
 *
 * @author Felix Riess, eXXcellent solutions consulting & software gmbh
 * @since 21.01.2020
 */
public interface IErrorCode {
    /**
     * Get the {@link ErrorCategory} of this specific error.
     * @return the {@link ErrorCategory}.
     */
    ErrorCategory getErrorCategory();

    /**
     * Get a human readable description of this specific error.
     * @return the description as {@link String}.
     */
    String getDescription();
}
