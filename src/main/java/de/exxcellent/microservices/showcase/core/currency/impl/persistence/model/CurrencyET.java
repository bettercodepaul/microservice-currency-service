package de.exxcellent.microservices.showcase.core.currency.impl.persistence.model;

import de.exxcellent.microservices.showcase.common.validation.Preconditions;
import de.exxcellent.microservices.showcase.core.currency.impl.access.CurrencyValidation;

import java.io.Serializable;
import java.util.Objects;

/**
 * The entity type (ET) representing a currency.
 *
 * @author Felix Riess, eXXcellent solutions consulting & software gmbh
 * @since 21.01.2020
 */
public class CurrencyET implements Serializable {
    /**
     * generated serialVersionUID.
     */
    private static final long serialVersionUID = -1483989109609229733L;

    /**
     * the short name (ISO Code) of the currency. (3 characters long)
     */
    private final String shortName;
    /**
     * the name of the currency.
     */
    private final String name;

    /**
     * Constructor.
     *
     * @param shortName the short name (ISO Code) of the currency (3 characters long, not {@code null}).
     * @param name the name of the currency (not {@code null}).
     */
    public CurrencyET(final String shortName, final String name) {
        Preconditions.checkNotNull(shortName, CurrencyValidation.CURRENCY_SHORT_NAME_NOT_NULL);
        Preconditions.checkNotNull(name, CurrencyValidation.CURRENCY_NAME_NOT_NULL);
        Preconditions.checkStringLength(shortName, 3, CurrencyValidation.CURRENCY_SHORT_NAME_LENGTH);
        this.shortName = shortName;
        this.name = name;
    }

    /**
     * Get the short name of this {@link CurrencyET}.
     *
     * @return the {@link CurrencyET#shortName}.
     */
    public String getShortName() {
        return this.shortName;
    }

    /**
     * Get the name of this {@link CurrencyET}.
     *
     * @return the {@link CurrencyET#name}.
     */
    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CurrencyET that = (CurrencyET) o;
        return Objects.equals(this.shortName, that.shortName) &&
                        Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.shortName, this.name);
    }

    @Override
    public String toString() {
        return "CurrencyET{" +
                        "shortName='" + this.shortName + '\'' +
                        ", name='" + this.name + '\'' +
                        '}';
    }
}
