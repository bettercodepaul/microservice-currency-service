package de.exxcellent.microservices.showcase.common.validation;

import de.exxcellent.microservices.showcase.common.errorhandling.ErrorCode;
import de.exxcellent.microservices.showcase.common.errorhandling.exception.BusinessException;
import de.exxcellent.microservices.showcase.common.errorhandling.exception.TechnicalException;

/**
 * A simple helper class to check arguments and to throw a {@link BusinessException} if a condition is hurt.
 *
 * @author Felix Riess, eXXcellent solutions consulting & software gmbh
 * @since 21.01.2020
 */
public final class Preconditions {
    /**
     * private constructor to hide implicit public one
     * @exception TechnicalException if class is tried to be instantiated.
     */
    private Preconditions() {
        throw new TechnicalException(ErrorCode.ILLEGAL_ACCESS_ERROR, "Preconditions is a utility class with static methods and must not be instantiated");
    }

    /**
     * Check if given reference is {@code null}. Call {@link #fail(String)} with the provided message if reference is {@code null}.
     *
     * @param reference the reference to be not {@code null}.
     * @param errorMessage the error message to use if reference is {@code null}.
     * @param <T> the generic type of the reference.
     * @return the reference if everything is fine.
     */
    public static <T> T checkNotNull(final T reference, final String errorMessage) {
        if(reference == null) {
            fail(errorMessage);
        } // else: reference is not null. return it.
        return reference;
    }

    /**
     * Check if given reference string has the given length. Call {@link #fail(String)} with the provided message if reference string is shorter or longer.
     *
     * @param reference the reference string to have the given length.
     * @param length the target length of the reference string.
     * @param errorMessage te error message to use if reference string has another length.
     */
    public static void checkStringLength(final String reference, final int length, final String errorMessage) {
        if(reference.length() != length) {
            fail(errorMessage);
        }
    }

    /**
     * Fail with the provided message.
     *
     * @param message the message to be thrown as {@link BusinessException}.
     */
    private static void fail(final String message) {
        throw new BusinessException(ErrorCode.INVALID_ARGUMENT_ERROR, message);
    }
}
