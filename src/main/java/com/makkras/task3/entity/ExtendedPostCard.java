package com.makkras.task3.entity;

import com.makkras.task3.entity.enumsource.CardType;
import com.makkras.task3.entity.enumsource.CountryName;
import com.makkras.task3.entity.enumsource.ThemeType;
import com.makkras.task3.entity.enumsource.ValuableType;

import java.time.Year;

public class ExtendedPostCard extends CustomPostCard{
    private CardType cardType;
    private CountryName countryName;
    private String author;

    public ExtendedPostCard(String id, ThemeType themeType, boolean wasSent, Year year, ValuableType valuableType, CardType cardType, CountryName countryName, String author) {
        super(id, themeType, wasSent, year, valuableType);
        this.cardType = cardType;
        this.countryName = countryName;
        this.author = author;
    }

    public ExtendedPostCard() {
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public CountryName getCountryName() {
        return countryName;
    }

    public void setCountryName(CountryName countryName) {
        this.countryName = countryName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ExtendedPostCard that = (ExtendedPostCard) o;

        if (cardType != that.cardType) return false;
        if (countryName != that.countryName) return false;
        return author != null ? author.equals(that.author) : that.author == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (cardType != null ? cardType.hashCode() : 0);
        result = 31 * result + (countryName != null ? countryName.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ExtendedPostCard{");
        sb.append("id=").append(id);
        sb.append(", themeType=").append(themeType);
        sb.append(", wasSent=").append(wasSent);
        sb.append(", year=").append(year);
        sb.append(", valuableType=").append(valuableType);
        sb.append(", cardType=").append(cardType);
        sb.append(", countryName=").append(countryName);
        sb.append(", author='").append(author).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
