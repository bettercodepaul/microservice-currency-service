package de.exxcellent.microservices.showcase.core.currency.impl.access;

import de.exxcellent.microservices.showcase.common.errorhandling.ErrorCode;
import de.exxcellent.microservices.showcase.common.errorhandling.exception.TechnicalException;
import de.exxcellent.microservices.showcase.core.currency.api.types.CurrencyTO;
import de.exxcellent.microservices.showcase.core.currency.impl.persistence.model.CurrencyET;

/**
 * Maps {@link CurrencyTO}s to {@link CurrencyET}s and vice versa.
 *
 * @author Felix Riess, eXXcellent solutions consulting & software gmbh
 * @since 21.01.2020
 */
public final class CurrencyMapper {

    /**
     * private constructor to hide implicit public one.
     * @exception TechnicalException if class is tried to be instantiated.
     */
    private CurrencyMapper() {
        throw new TechnicalException(ErrorCode.ILLEGAL_ACCESS_ERROR, "CurrencyMapper is a utility class with static methods and must not be instantiated");
    }

    /**
     * Maps a {@link CurrencyET} to a {@link CurrencyTO}.
     *
     * @param currency the {@link CurrencyET} to be mapped. (must be valid, see {@link CurrencyValidation#validateCurrencyET(CurrencyET)}).
     * @return the {@link CurrencyTO} containing the information from the {@link CurrencyET}.
     */
    public static CurrencyTO toTO(final CurrencyET currency) {
        CurrencyValidation.validateCurrencyET(currency);
        return new CurrencyTO(currency.getShortName(), currency.getName());
    }

    /**
     * Maps a {@link CurrencyTO} to a {@link CurrencyET}.
     *
     * @param currency the {@link CurrencyTO} to be mapped. (must be valid, see {@link CurrencyValidation#validateCurrencyTO(CurrencyTO)}).
     * @return the {@link CurrencyET} containing the information from the {@link CurrencyTO}.
     */
    public static CurrencyET fromTO(final CurrencyTO currency) {
        CurrencyValidation.validateCurrencyTO(currency);
        return new CurrencyET(currency.getShortName(), currency.getName());
    }

}
