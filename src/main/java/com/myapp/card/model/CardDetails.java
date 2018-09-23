package com.myapp.card.model;

import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.DefaultValue;
import java.util.Objects;

public class CardDetails {
    @NotNull
    private String name;
    @NotNull
    @Size(min=16,max = 16 ,message = "Please enter valid credit card number")
    private String cardNumber;
    @NotNull
    @DefaultValue("0")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private int limit;

    public CardDetails() {
    }

    public CardDetails(@NotNull String name, @NotNull @Size(min = 16, max = 16, message = "Please enter valid credit card number") String cardNumber, @NotNull int limit) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.limit = limit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CardDetails)) return false;
        CardDetails that = (CardDetails) o;
        return getLimit() == that.getLimit() &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getCardNumber(), that.getCardNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCardNumber(), getLimit());
    }

    @Override
    public String toString() {
        return "CardDetails{" +
                "name='" + name + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", limit=" + limit +
                '}';
    }
}
