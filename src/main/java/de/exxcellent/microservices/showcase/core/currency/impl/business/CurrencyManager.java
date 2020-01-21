package de.exxcellent.microservices.showcase.core.currency.impl.business;

import de.exxcellent.microservices.showcase.common.errorhandling.ErrorCode;
import de.exxcellent.microservices.showcase.common.errorhandling.exception.BusinessException;
import de.exxcellent.microservices.showcase.common.validation.Preconditions;
import de.exxcellent.microservices.showcase.core.currency.impl.access.CurrencyValidation;
import de.exxcellent.microservices.showcase.core.currency.impl.persistence.CurrencyRepository;
import de.exxcellent.microservices.showcase.core.currency.impl.persistence.model.CurrencyET;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Manages currencies. Implementation of {@link CurrencyICI}.
 *
 * @author Felix Riess, eXXcellent solutions consulting & software gmbh
 * @since 21.01.2020
 */
@ApplicationScoped
public class CurrencyManager implements CurrencyICI {
    private static final Logger LOG = LoggerFactory.getLogger(CurrencyManager.class);
    private CurrencyRepository currencyRepository;

    @Inject
    CurrencyManager(final CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Set<CurrencyET> getCurrencies() {
        LOG.info("Query storage to get all currencies");
        return this.currencyRepository.findAll();
    }

    @Override
    public CurrencyET getCurrency(final String shortName) {
        Preconditions.checkNotNull(shortName, CurrencyValidation.CURRENCY_SHORT_NAME_NOT_NULL);
        Preconditions.checkStringLength(shortName, 3, CurrencyValidation.CURRENCY_SHORT_NAME_LENGTH);
        LOG.info("Query storage for currency with short name {}", shortName);
        final Optional<CurrencyET> optionalCurrency = this.currencyRepository.findByShortName(shortName);
        if(optionalCurrency.isPresent()) {
            final CurrencyET currency = optionalCurrency.get();
            LOG.info("Returning currency {} for short name {} from storage", currency.getName(), currency.getShortName());
            return currency;
        } else {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "Currency with short name " + shortName + " is not existing");
        }
    }

    @Override
    public Set<CurrencyET> addCurrency(final CurrencyET currency) {
        addCurrencyIfNotExisting(currency);
        return this.currencyRepository.findAll();
    }

    @Override
    public Map<String, CurrencyET> getCountriesWithCurrency() {
        LOG.info("Query storage to get all countries with their currency");
        return this.currencyRepository.findAllCountriesWithCurrency();
    }

    @Override
    public CurrencyET getCountryWithCurrency(final String countryShortName) {
        Preconditions.checkNotNull(countryShortName, "Country short name must not be null");
        Preconditions.checkStringLength(countryShortName, 3, "Country short name must have 3 characters");
        LOG.info("Query storage for currency of country with short name {}", countryShortName);
        Optional<CurrencyET> optionalCurrencyET = this.currencyRepository.findCurrencyByCountry(countryShortName);
        if(optionalCurrencyET.isPresent()) {
            final CurrencyET currency = optionalCurrencyET.get();
            LOG.info("Returning currency {} for country with short name {}", currency.getName(), countryShortName);
            return currency;
        } else {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "No currency is existing for country with short name " + countryShortName);
        }
    }

    @Override
    public Map<String, CurrencyET> addCountryWithCurrency(final String countryShortName, final CurrencyET currency) {
        Preconditions.checkNotNull(countryShortName, "Country short name must not be null");
        Preconditions.checkStringLength(countryShortName, 3, "Country short name must have 3 characters");
        CurrencyValidation.validateCurrencyET(currency);
        addCurrencyIfNotExisting(currency);
        LOG.info("Adding currency {} to country with short name {}", currency.getName(), countryShortName);
        return this.currencyRepository.addCountryWithCurrency(countryShortName, currency);
    }

    private void addCurrencyIfNotExisting(final CurrencyET currency) {
        CurrencyValidation.validateCurrencyET(currency);
        LOG.info("Query storage for currency with short name {} to avoid generating duplicates", currency.getShortName());
        final Optional<CurrencyET> optionalCurrencyET = this.currencyRepository.findByShortName(currency.getShortName());
        if(optionalCurrencyET.isPresent()) {
            final CurrencyET existingCurrency = optionalCurrencyET.get();
            LOG.info("Currency with short name {} is already existing in storage: {}", existingCurrency.getShortName(), existingCurrency.getName());
            if(!existingCurrency.getName().equalsIgnoreCase(currency.getName())) {
                // another currency with this short name is already existing. No other currency with this short name can be created!
                throw new BusinessException(ErrorCode.INVALID_ARGUMENT_ERROR, "A currency with the short name " + currency.getShortName()
                                + " is already existing: " + existingCurrency.getName() + ". Cannot create two currencies with the same short name");
            } // else: currency with this name is already existing and must not be added again.
        } else {
            // no currency with the provided short name is present. Create a new one.
            LOG.info("Adding new currency {} with short name {} to storage", currency.getName(), currency.getShortName());
            this.currencyRepository.addCurrency(currency);
        }
    }
}
