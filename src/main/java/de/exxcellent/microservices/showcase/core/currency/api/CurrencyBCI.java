package de.exxcellent.microservices.showcase.core.currency.api;

import de.exxcellent.microservices.showcase.core.currency.api.types.CountryWithCurrencyCTO;
import de.exxcellent.microservices.showcase.core.currency.api.types.CurrencyTO;

import java.util.Set;

/**
 * Business component interface (BCI) for the currency component
 *
 * @author Felix Riess, eXXcellent solutions consulting & software gmbh
 * @since 21.01.2020
 */
public interface CurrencyBCI {
    /**
     * Get all available currencies.
     *
     * @return a {@link Set} with all currencies as {@link CurrencyTO}.
     */
    Set<CurrencyTO> getCurrencies();

    /**
     * Get a currency by its short name (ISO Code).
     *
     * @param shortName the short name (ISO Code) of the currency to be returned (3 characters, not {@code null}).
     * @return the currency with the provided short name as {@link CurrencyTO}.
     * @exception de.exxcellent.microservices.showcase.common.errorhandling.exception.BusinessException with {@link de.exxcellent.microservices.showcase.common.errorhandling.ErrorCode#NOT_FOUND_ERROR} if no currency with the provided short name is existing.
     */
    CurrencyTO getCurrency(final String shortName);

    /**
     * Add a currency.
     * Does not generate duplicates.
     *
     * @param currency the currency to be added as {@link CurrencyTO}. (must be valid, see {@link de.exxcellent.microservices.showcase.core.currency.impl.access.CurrencyValidation#validateCurrencyTO(CurrencyTO)}).
     * @return all currencies including the added currency as {@link Set} of {@link CurrencyTO}s.
     */
    Set<CurrencyTO> addCurrency(final CurrencyTO currency);

    /**
     * Get all available countries with their currencies.
     *
     * @return a {@link Set} with all countries and their currencies as {@link CountryWithCurrencyCTO}.
     */
    Set<CountryWithCurrencyCTO> getCountriesWithCurrency();

    /**
     * Get a country with its currency by its short name.
     *
     * @param countryShortName the short name of the country to be returned (3 characters, not {@code null}).
     * @return the country with the provided short name and its currency as {@link CountryWithCurrencyCTO}.
     * @exception de.exxcellent.microservices.showcase.common.errorhandling.exception.BusinessException with {@link de.exxcellent.microservices.showcase.common.errorhandling.ErrorCode#NOT_FOUND_ERROR} if no country with the provided short name is existing.
     */
    CountryWithCurrencyCTO getCountryWithCurrency(final String countryShortName);

    /**
     * Add a country and its currency.
     *
     * @param countryWithCurrency the country and its currency to be added as {@link CountryWithCurrencyCTO}.
     * @return all countries with their currency as {@link Set} of {@link CountryWithCurrencyCTO}s.
     */
    Set<CountryWithCurrencyCTO> addCountryWithCurrency(final CountryWithCurrencyCTO countryWithCurrency);
}
