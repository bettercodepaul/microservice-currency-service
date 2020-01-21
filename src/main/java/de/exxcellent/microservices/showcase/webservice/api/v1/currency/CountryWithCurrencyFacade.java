package de.exxcellent.microservices.showcase.webservice.api.v1.currency;

import de.exxcellent.microservices.showcase.common.errorhandling.ErrorCode;
import de.exxcellent.microservices.showcase.common.errorhandling.exception.BusinessException;
import de.exxcellent.microservices.showcase.common.validation.Preconditions;
import de.exxcellent.microservices.showcase.core.currency.api.CurrencyBCI;
import de.exxcellent.microservices.showcase.core.currency.api.types.CountryWithCurrencyCTO;
import de.exxcellent.microservices.showcase.core.currency.impl.access.CurrencyValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Set;

/**
 * Provides a REST API to manage countries with their currencies.
 *
 * @author Felix Riess, eXXcellent solutions consulting & software gmbh
 * @since 21.01.2020
 */
@Path("/api/v1/countries-with-currency")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CountryWithCurrencyFacade {
    /**
     * The {@link Logger} for this {@link CountryWithCurrencyFacade}.
     */
    private static final Logger LOG = LoggerFactory.getLogger(CountryWithCurrencyFacade.class);

    private CurrencyBCI currencyService;

    @Inject
    CountryWithCurrencyFacade(final CurrencyBCI currencyService) {
        this.currencyService = currencyService;
    }

    /**
     * Get all available countries with their currency.
     *
     * @return a {@link Set} of countries with their currency as {@link CountryWithCurrencyCTO}.
     * @exception BusinessException with {@link ErrorCode#EMPTY_LIST_ERROR} if no countries with their currency are defined to produce a HTTP 204.
     */
    @GET
    public Set<CountryWithCurrencyCTO> getCountriesWithCurrency() {
        LOG.info("Resource to get countries with their currency triggered");
        final Set<CountryWithCurrencyCTO> countriesWithCurrency = this.currencyService.getCountriesWithCurrency();
        if(countriesWithCurrency.isEmpty()) {
            throw new BusinessException(ErrorCode.EMPTY_LIST_ERROR, "No countries with currencies are available");
        } else {
            return countriesWithCurrency;
        }
    }

    /**
     * Get the currency of the country with the provided short name.
     *
     * @param countryShortName the short name of the country where the currency should be returned (3 characters, not {@code null}).
     * @return the currency of the country with the provided short name as {@link CountryWithCurrencyCTO}.
     */
    @GET
    @Path("{countryShortName}")
    public CountryWithCurrencyCTO getCountryWithCurrency(@PathParam("countryShortName") final String countryShortName) {
        Preconditions.checkNotNull(countryShortName, "Country short name must not be null");
        Preconditions.checkStringLength(countryShortName, 3, "Country short name must have 3 characters");
        LOG.info("Resource to get currency of country with short name {} triggered", countryShortName);
        return this.currencyService.getCountryWithCurrency(countryShortName);
    }

    /**
     * Add a currency to a country.
     *
     * @param countryWithCurrency the information about the country and the currency as {@link CountryWithCurrencyCTO}.
     * @return all available countries with their currency as {@link Set} of {@link CountryWithCurrencyCTO} including the new one.
     */
    @POST
    public Set<CountryWithCurrencyCTO> createCountryWithCurrency(final CountryWithCurrencyCTO countryWithCurrency) {
        Preconditions.checkNotNull(countryWithCurrency, "Country With Currency must not be null");
        Preconditions.checkNotNull(countryWithCurrency.getCountryShortName(), "Country short name must not be null");
        Preconditions.checkStringLength(countryWithCurrency.getCountryShortName(), 3, "Country short name must have 3 characters");
        LOG.info("Currency: {}", countryWithCurrency.getCurrency());
        CurrencyValidation.validateCurrencyTO(countryWithCurrency.getCurrency());
        LOG.info("Resource to add currency {} to country with short name {} triggered", countryWithCurrency.getCurrency().getName(), countryWithCurrency.getCountryShortName());
        return this.currencyService.addCountryWithCurrency(countryWithCurrency);
    }
}
