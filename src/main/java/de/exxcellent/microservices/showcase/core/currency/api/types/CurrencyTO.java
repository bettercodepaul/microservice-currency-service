package de.exxcellent.microservices.showcase.core.currency.api.types;

import de.exxcellent.microservices.showcase.common.validation.Preconditions;
import de.exxcellent.microservices.showcase.core.currency.impl.access.CurrencyValidation;

import java.io.Serializable;

/**
 * A transport object representing a currency.
 *
 * @author Felix Riess, eXXcellent solutions consulting & software gmbh
 * @since 21.01.2020
 */
public class CurrencyTO implements Serializable {
    /**
     * generated serialVersionUID
     */
    private static final long serialVersionUID = 432841563642673310L;
    /**
     * the short name (ISO Code) of the currency.
     */
    private String shortName;
    /**
     * the name of the currency
     */
    private String name;

    /**
     * empty constructor for JSON mapping.
     */
    public CurrencyTO() {

    }

    /**
     * Constructor.
     *
     * @param shortName the short name (ISO Code) of the currency (3 characters, not {@code null}).
     * @param name the name of the currency (not {@code null}).
     */
    public CurrencyTO(final String shortName, final String name) {
        Preconditions.checkNotNull(shortName, CurrencyValidation.CURRENCY_SHORT_NAME_NOT_NULL);
        Preconditions.checkNotNull(name, CurrencyValidation.CURRENCY_NAME_NOT_NULL);
        Preconditions.checkStringLength(shortName, 3, CurrencyValidation.CURRENCY_SHORT_NAME_LENGTH);
        this.shortName = shortName;
        this.name = name;
    }

    public String getShortName() {
        return this.shortName;
    }

    public void setShortName(final String shortName) {
        Preconditions.checkNotNull(shortName, CurrencyValidation.CURRENCY_SHORT_NAME_NOT_NULL);
        Preconditions.checkStringLength(shortName, 3, CurrencyValidation.CURRENCY_SHORT_NAME_LENGTH);
        this.shortName = shortName;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        Preconditions.checkNotNull(name, CurrencyValidation.CURRENCY_NAME_NOT_NULL);
        this.name = name;
    }

    @Override
    public String toString() {
        return "CurrencyTO{" +
                        "shortName='" + this.shortName + '\'' +
                        ", name='" + this.name + '\'' +
                        '}';
    }
}
