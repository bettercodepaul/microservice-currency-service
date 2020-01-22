package de.exxcellent.microservices.showcase.core.currency.impl.persistence;

import de.exxcellent.microservices.showcase.common.validation.Preconditions;
import de.exxcellent.microservices.showcase.core.currency.impl.access.CurrencyValidation;
import de.exxcellent.microservices.showcase.core.currency.impl.persistence.model.CurrencyET;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * The implementation of {@link CurrencyRepository} for a runtime storage for currencies.
 *
 * @author Felix Riess, eXXcellent solutions consulting & software gmbh
 * @since 21.01.2020
 */
@ApplicationScoped
public class CurrencyRuntimeStorage implements CurrencyRepository {
    /**
     * The {@link Map} containing all known currencies as value and country short names as key.
     */
    Map<String, CurrencyET> countriesWithCurrency;
    /**
     * A {@link Set} containing all known currencies.
     */
    Set<CurrencyET> currencies;

    /**
     * Constructor.
     * Initializes some dummy data.
     */
    public CurrencyRuntimeStorage() {
        this.countriesWithCurrency = new HashMap<>();
        this.currencies = new HashSet<>();
        initData();
    }

    private void initData() {
        final CurrencyET euro = new CurrencyET("EUR", "Euro");
        final CurrencyET pound = new CurrencyET("GPD", "Pound");
        this.currencies.add(euro);
        this.currencies.add(pound);
        this.countriesWithCurrency.putIfAbsent("GER", euro);
        this.countriesWithCurrency.putIfAbsent("FRA", euro);
        this.countriesWithCurrency.putIfAbsent("SCO", pound);
    }

    @Override
    public Set<CurrencyET> findAll() {
        return this.currencies;
    }

    @Override
    public Optional<CurrencyET> findByShortName(final String shortName) {
        Preconditions.checkNotNull(shortName, CurrencyValidation.CURRENCY_SHORT_NAME_NOT_NULL);
        return this.currencies.stream().filter(c -> shortName.equalsIgnoreCase(c.getShortName())).findFirst();
    }

    @Override
    public Set<CurrencyET> addCurrency(final CurrencyET currency) {
        CurrencyValidation.validateCurrencyET(currency);
        this.currencies.add(currency);
        return this.currencies;
    }

    @Override
    public Map<String, CurrencyET> findAllCountriesWithCurrency() {
        return this.countriesWithCurrency;
    }

    @Override
    public Map<String, CurrencyET> addCountryWithCurrency(final String countryShortName, final CurrencyET currency) {
        Preconditions.checkNotNull(countryShortName, "Country short name must not be null");
        CurrencyValidation.validateCurrencyET(currency);
        this.currencies.add(currency);
        this.countriesWithCurrency.putIfAbsent(countryShortName, currency);
        return this.countriesWithCurrency;
    }

    @Override
    public Optional<CurrencyET> findCurrencyByCountry(final String countryShortName) {
        Preconditions.checkNotNull(countryShortName, "Country short name must not be null");
        return Optional.ofNullable(this.countriesWithCurrency.get(countryShortName));
    }
}
