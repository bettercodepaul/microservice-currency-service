package de.exxcellent.microservices.showcase.common.errorhandling;

/**
 * Some common error codes for the currency service application
 *
 * @author Felix Riess, eXXcellent solutions consulting & software gmbh
 * @since 21.01.2020
 */
public enum ErrorCode implements IErrorCode {
    /**
     * Technical errors.
     */
    INTERNAL_ERROR(ErrorCategory.TECHNICAL, "Internal Error"),
    UNEXPECTED_ERROR(ErrorCategory.TECHNICAL, "Unexpected Error"),
    ILLEGAL_ACCESS_ERROR(ErrorCategory.TECHNICAL, "Illegal Access Error"),
    /**
     * Business errors.
     */
    INVALID_ARGUMENT_ERROR(ErrorCategory.BUSINESS, "Invalid Argument Error"),
    NOT_FOUND_ERROR(ErrorCategory.BUSINESS, "Not Found Error"),
    ALREADY_EXISTING_ERROR(ErrorCategory.BUSINESS, "Already Existing Error"),
    EMPTY_LIST_ERROR(ErrorCategory.BUSINESS, "Empty List Error"),
    /**
     * Undefined errors.
     */
    UNDEFINED(ErrorCategory.UNDEFINED, "Undefined Error"),
    ;
    /**
     * The {@link ErrorCategory} of this {@link ErrorCode}.
     */
    private final ErrorCategory category;
    /**
     * The description of this {@link ErrorCode}
     */
    private final String description;

    /**
     * Constructor.
     *
     * @param category the {@link ErrorCategory} of this {@link ErrorCode}.
     * @param description the description of this {@link ErrorCode}.
     */
    ErrorCode(final ErrorCategory category, final String description) {
        this.category = category;
        this.description = description;
    }

    @Override
    public ErrorCategory getErrorCategory() {
        return this.category;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
