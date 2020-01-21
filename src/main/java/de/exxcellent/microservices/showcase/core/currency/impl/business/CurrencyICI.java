package de.exxcellent.microservices.showcase.core.currency.impl.business;

import de.exxcellent.microservices.showcase.core.currency.impl.persistence.model.CurrencyET;

import java.util.Map;
import java.util.Set;

/**
 * The inner-component interface (ICI) of the currency component.
 *
 * @author Felix Riess, eXXcellent solutions consulting & software gmbh
 * @since 21.01.2020
 */
public interface CurrencyICI {
    /**
     * Get all currencies.
     *
     * @return all currencies as {@link Set} of {@link CurrencyET}s.
     */
    Set<CurrencyET> getCurrencies();

    /**
     * Get a currency by its short name (ISO code).
     *
     * @param shortName the short name of the currency to be returned. (3 characters, not {@code null}).
     * @return the currency with the provided short name as {@link CurrencyET}.
     * @exception de.exxcellent.microservices.showcase.common.errorhandling.exception.BusinessException with {@link de.exxcellent.microservices.showcase.common.errorhandling.ErrorCode#NOT_FOUND_ERROR} if no currency with the provided short name is existing.
     */
    CurrencyET getCurrency(final String shortName);

    /**
     * Add a currency.
     * No duplicates will be created.
     *
     * @param currency the currency to be added as {@link CurrencyET}. (must be valid, see {@link de.exxcellent.microservices.showcase.core.currency.impl.access.CurrencyValidation#validateCurrencyET(CurrencyET)}).
     * @return all available currencies including the newly added as {@link Set} of {@link CurrencyET}s.
     */
    Set<CurrencyET> addCurrency(final CurrencyET currency);

    /**
     * Get all countries with their currency.
     *
     * @return a {@link Map} containing the country short name as key and its currency as value (as {@link CurrencyET}).
     */
    Map<String, CurrencyET> getCountriesWithCurrency();

    /**
     * Get the currency of a country by its short name.
     *
     * @param countryShortName the short name of the country (3 characters, not {@code null}).
     * @return the currency of the country with the provided short name as {@link CurrencyET}.
     * @exception de.exxcellent.microservices.showcase.common.errorhandling.exception.BusinessException with {@link de.exxcellent.microservices.showcase.common.errorhandling.ErrorCode#NOT_FOUND_ERROR} if no country with the provided short name is existing.
     */
    CurrencyET getCountryWithCurrency(final String countryShortName);

    /**
     * Add a country with its currency.
     *
     * @param countryShortName the short name of the country to be added (3 characters, not {@code null}).
     * @param currency the currency of the country to be added as {@link CurrencyET}.
     * @return all available countries with their currency including the newly added as {@link Map}.
     */
    Map<String, CurrencyET> addCountryWithCurrency(final String countryShortName, final CurrencyET currency);
}
