package de.exxcellent.microservices.showcase.core.currency.impl.access;

import de.exxcellent.microservices.showcase.common.errorhandling.ErrorCode;
import de.exxcellent.microservices.showcase.common.errorhandling.exception.TechnicalException;
import de.exxcellent.microservices.showcase.common.validation.Preconditions;
import de.exxcellent.microservices.showcase.core.currency.api.types.CurrencyTO;
import de.exxcellent.microservices.showcase.core.currency.impl.persistence.model.CurrencyET;

/**
 * A simple helper class to validate {@link CurrencyTO}s and {@link CurrencyET}s.
 *
 * @author Felix Riess, eXXcellent solutions consulting & software gmbh
 * @since 21.01.2020
 */
public final class CurrencyValidation {
    public static final String CURRENCY_NOT_NULL = "Currency must not be null";
    public static final String CURRENCY_SHORT_NAME_NOT_NULL = "Currency short name must not be null";
    public static final String CURRENCY_NAME_NOT_NULL = "Currency name must not be null";
    public static final String CURRENCY_SHORT_NAME_LENGTH = "Currency short name must have 3 characters";

    /**
     * private constructor to hide implicit public one.
     * @exception TechnicalException if class is tried to be instantiated.
     */
    private CurrencyValidation() {
        throw new TechnicalException(ErrorCode.ILLEGAL_ACCESS_ERROR, "CurrencyValidation is a utility class with static methods and must not be instantiated");
    }

    /**
     * Validates the given {@link CurrencyTO}.
     * The following is checked:
     * <ol>
     *     <li>{@link CurrencyTO} must not be {@code null}</li>
     *     <li>{@link CurrencyTO#getName()} must not be {@code null}</li>
     *     <li>{@link CurrencyTO#getShortName()} must not be {@code null}</li>
     *     <li>{@link CurrencyTO#getShortName()} must have length 3</li>
     * </ol>
     *
     * @param currency the {@link CurrencyTO} to be validated.
     */
    public static void validateCurrencyTO(final CurrencyTO currency) {
        Preconditions.checkNotNull(currency, CURRENCY_NOT_NULL);
        Preconditions.checkNotNull(currency.getShortName(), CURRENCY_SHORT_NAME_NOT_NULL);
        Preconditions.checkNotNull(currency.getName(), CURRENCY_NAME_NOT_NULL);
        Preconditions.checkStringLength(currency.getShortName(), 3, CURRENCY_SHORT_NAME_LENGTH);
    }

    /**
     * Validates the given {@link CurrencyET}.
     * The following is checked:
     * <ol>
     *     <li>{@link CurrencyET} must not be {@code null}</li>
     *     <li>{@link CurrencyET#getName()} must not be {@code null}</li>
     *     <li>{@link CurrencyET#getShortName()} must not be {@code null}</li>
     *     <li>{@link CurrencyET#getShortName()} must have length 3</li>
     * </ol>
     *
     * @param currency the {@link CurrencyET} to be validated.
     */
    public static void validateCurrencyET(final CurrencyET currency) {
        Preconditions.checkNotNull(currency, CURRENCY_NOT_NULL);
        Preconditions.checkNotNull(currency.getShortName(), CURRENCY_SHORT_NAME_NOT_NULL);
        Preconditions.checkNotNull(currency.getName(), CURRENCY_NAME_NOT_NULL);
        Preconditions.checkStringLength(currency.getShortName(), 3, CURRENCY_SHORT_NAME_LENGTH);
    }
}
