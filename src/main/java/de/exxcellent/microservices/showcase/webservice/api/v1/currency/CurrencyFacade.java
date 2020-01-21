package de.exxcellent.microservices.showcase.webservice.api.v1.currency;

import de.exxcellent.microservices.showcase.common.errorhandling.ErrorCode;
import de.exxcellent.microservices.showcase.common.errorhandling.exception.BusinessException;
import de.exxcellent.microservices.showcase.common.validation.Preconditions;
import de.exxcellent.microservices.showcase.core.currency.api.CurrencyBCI;
import de.exxcellent.microservices.showcase.core.currency.api.types.CurrencyTO;
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
 * Provides the REST API for the currency component.
 *
 * @author Felix Riess, eXXcellent solutions consulting & software gmbh
 * @since 21.01.2020
 */
@Path("/api/v1/currencies")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CurrencyFacade {
    /**
     * The {@link Logger} of this {@link CurrencyFacade}.
     */
    private static final Logger LOG = LoggerFactory.getLogger(CurrencyFacade.class);

    private CurrencyBCI currencyService;

    @Inject
    CurrencyFacade(final CurrencyBCI currencyService) {
        this.currencyService = currencyService;
    }

    /**
     * Get all available currencies.
     *
     * @return a {@link Set} containing all currencies as {@link CurrencyTO}s.
     * @exception BusinessException with {@link ErrorCode#EMPTY_LIST_ERROR} if no currencies are available to produce HTTP 204.
     */
    @GET
    public Set<CurrencyTO> getCurrencies() {
        LOG.info("Resource to get all currencies triggered");
        final Set<CurrencyTO> currencies = this.currencyService.getCurrencies();
        if(currencies.isEmpty()) {
            throw new BusinessException(ErrorCode.EMPTY_LIST_ERROR, "No currencies are existing");
        } else {
            return currencies;
        }
    }

    /**
     * Get the currency with the provided short name (ISO code).
     *
     * @param shortName the short name (ISO Code) of the currency to be returned (3 characters, not {@code null}).
     * @return the currency with the provided short name as {@link CurrencyTO}.
     */
    @GET
    @Path("{shortName}")
    public CurrencyTO getCurrency(@PathParam("shortName") final String shortName) {
        Preconditions.checkNotNull(shortName, CurrencyValidation.CURRENCY_SHORT_NAME_NOT_NULL);
        Preconditions.checkStringLength(shortName, 3, CurrencyValidation.CURRENCY_SHORT_NAME_LENGTH);
        LOG.info("Resource to get currency with short name {} triggered", shortName);
        return this.currencyService.getCurrency(shortName);
    }

    /**
     * Create a new currency from the provided information.
     *
     * @param currency the information about the currency to be created as {@link CurrencyTO}. (must be valid, see {@link CurrencyValidation#validateCurrencyTO(CurrencyTO)}).
     * @return all available currencies including the new one as {@link Set} of {@link CurrencyTO}s.
     */
    @POST
    public Set<CurrencyTO> createCurrency(final CurrencyTO currency) {
        CurrencyValidation.validateCurrencyTO(currency);
        LOG.info("Resource to create new currency {} with short name {} triggered", currency.getName(), currency.getShortName());
        return this.currencyService.addCurrency(currency);
    }
}
