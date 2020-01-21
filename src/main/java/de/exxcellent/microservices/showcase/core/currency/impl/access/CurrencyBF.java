package de.exxcellent.microservices.showcase.core.currency.impl.access;

import de.exxcellent.microservices.showcase.common.validation.Preconditions;
import de.exxcellent.microservices.showcase.core.currency.api.CurrencyBCI;
import de.exxcellent.microservices.showcase.core.currency.api.types.CountryWithCurrencyCTO;
import de.exxcellent.microservices.showcase.core.currency.api.types.CurrencyTO;
import de.exxcellent.microservices.showcase.core.currency.impl.business.CurrencyICI;
import de.exxcellent.microservices.showcase.core.currency.impl.persistence.model.CurrencyET;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The business facade (BF) for the currency component.
 *
 * @author Felix Riess, eXXcellent solutions consulting & software gmbh
 * @since 21.01.2020
 */
@ApplicationScoped
public class CurrencyBF implements CurrencyBCI {

    private CurrencyICI currencyManager;

    @Inject
    CurrencyBF(final CurrencyICI currencyManager) {
        this.currencyManager = currencyManager;
    }

    @Override
    public Set<CurrencyTO> getCurrencies() {
        return this.currencyManager.getCurrencies()
                                   .stream()
                                   .map(CurrencyMapper::toTO)
                                   .collect(Collectors.toSet());
    }

    @Override
    public CurrencyTO getCurrency(final String shortName) {
        Preconditions.checkNotNull(shortName, CurrencyValidation.CURRENCY_SHORT_NAME_NOT_NULL);
        Preconditions.checkStringLength(shortName, 3, CurrencyValidation.CURRENCY_SHORT_NAME_LENGTH);
        return CurrencyMapper.toTO(this.currencyManager.getCurrency(shortName));
    }

    @Override
    public Set<CurrencyTO> addCurrency(final CurrencyTO currency) {
        CurrencyValidation.validateCurrencyTO(currency);
        final CurrencyET currencyET = CurrencyMapper.fromTO(currency);
        return this.currencyManager.addCurrency(currencyET)
                                   .stream()
                                   .map(CurrencyMapper::toTO)
                                   .collect(Collectors.toSet());
    }

    @Override
    public Set<CountryWithCurrencyCTO> getCountriesWithCurrency() {
        return this.currencyManager.getCountriesWithCurrency()
                                   .entrySet()
                                   .stream()
                                   .map(c -> new CountryWithCurrencyCTO(c.getKey(), CurrencyMapper.toTO(c.getValue())))
                                   .collect(Collectors.toSet());
    }

    @Override
    public CountryWithCurrencyCTO getCountryWithCurrency(final String countryShortName) {
        Preconditions.checkNotNull(countryShortName, "Country short name must not be null");
        Preconditions.checkStringLength(countryShortName, 3, "Country short name must have 3 characters");
        final CurrencyTO currency = CurrencyMapper.toTO(this.currencyManager.getCountryWithCurrency(countryShortName));
        return new CountryWithCurrencyCTO(countryShortName, currency);
    }

    @Override
    public Set<CountryWithCurrencyCTO> addCountryWithCurrency(final CountryWithCurrencyCTO countryWithCurrency) {
        Preconditions.checkNotNull(countryWithCurrency, "Country with currency must not be null");
        Preconditions.checkNotNull(countryWithCurrency.getCountryShortName(), "Country short name must not be null");
        Preconditions.checkStringLength(countryWithCurrency.getCountryShortName(), 3, "Country short name must have 3 characters");
        CurrencyValidation.validateCurrencyTO(countryWithCurrency.getCurrency());
        final CurrencyET currency = CurrencyMapper.fromTO(countryWithCurrency.getCurrency());
        return this.currencyManager.addCountryWithCurrency(countryWithCurrency.getCountryShortName(), currency)
                                   .entrySet()
                                   .stream()
                                   .map(c -> new CountryWithCurrencyCTO(c.getKey(), CurrencyMapper.toTO(c.getValue())))
                                   .collect(Collectors.toSet());
    }
}
