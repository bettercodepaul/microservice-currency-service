package de.exxcellent.microservices.showcase.core.currency.impl.persistence;

import de.exxcellent.microservices.showcase.core.currency.impl.persistence.model.CurrencyET;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * A repository interface for storage operations.
 *
 * @author Felix Riess, eXXcellent solutions consulting & software gmbh
 * @since 21.01.2020
 */
public interface CurrencyRepository {
    /**
     * Get all available currencies.
     *
     * @return all currencies as {@link Set} of {@link CurrencyET}s.
     */
    Set<CurrencyET> findAll();

    /**
     * Get a currency by its short name (ISO Code).
     *
     * @param shortName the short name (ISO Code) of the currency to be returned (not {@code null}).
     * @return an {@link Optional} containing the currency with the provided short name as {@link CurrencyET}.
     */
    Optional<CurrencyET> findByShortName(final String shortName);

    /**
     * Adds the given currency to the known currencies.
     * Does not add duplicates.
     *
     * @param currency the currency to add as {@link CurrencyET}.
     * @return all available currencies including the new one as {@link Set} of {@link CurrencyET}.
     */
    Set<CurrencyET> addCurrency(final CurrencyET currency);

    /**
     * Get all available countries with their currency.
     * @return a {@link Map} containing the country short name as {@link String} and its currency as {@link CurrencyET}.
     */
    Map<String, CurrencyET> findAllCountriesWithCurrency();

    /**
     * Adds a country with its currency to the known countries with currencies.
     *
     * @param countryShortName the short name of the country as {@link String} (not {@code null}).
     * @param currency the currency of the country as {@link CurrencyET}.
     * @return all available countries with their currencies including the new one as {@link Map}.
     */
    Map<String, CurrencyET> addCountryWithCurrency(final String countryShortName, final CurrencyET currency);

    /**
     * Get the currency of a country.
     *
     * @param countryShortName the short name of the country where the currency should be returned (not {@code null}).
     * @return an {@link Optional} containing the currency of the given country as {@link CurrencyET}.
     */
    Optional<CurrencyET> findCurrencyByCountry(final String countryShortName);
}
