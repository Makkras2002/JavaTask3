package com.makkras.task3.entity;

import com.makkras.task3.entity.enumsource.ThemeType;
import com.makkras.task3.entity.enumsource.ValuableType;

import java.time.Year;

public abstract class CustomPostCard{
    public String id;
    public ThemeType themeType;
    public boolean wasSent;
    public Year year;
    public ValuableType valuableType;

    public CustomPostCard(String id, ThemeType themeType, boolean wasSent, Year year, ValuableType valuableType) {
        this.id = id;
        this.themeType = themeType;
        this.wasSent = wasSent;
        this.year = year;
        this.valuableType = valuableType;
    }
    public CustomPostCard(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ThemeType getThemeType() {
        return themeType;
    }

    public void setThemeType(ThemeType themeType) {
        this.themeType = themeType;
    }

    public boolean isWasSent() {
        return wasSent;
    }

    public void setWasSent(boolean wasSent) {
        this.wasSent = wasSent;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public ValuableType getValuableType() {
        return valuableType;
    }

    public void setValuableType(ValuableType valuableType) {
        this.valuableType = valuableType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomPostCard that = (CustomPostCard) o;

        if (wasSent != that.wasSent) return false;
        if (themeType != that.themeType) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;
        return valuableType == that.valuableType;
    }

    @Override
    public int hashCode() {
        int result = themeType != null ? themeType.hashCode() : 0;
        result = 31 * result + (wasSent ? 1 : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (valuableType != null ? valuableType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CustomPostCard{");
        sb.append("id=").append(id);
        sb.append(", themeType=").append(themeType);
        sb.append(", wasSent=").append(wasSent);
        sb.append(", year=").append(year);
        sb.append(", valuableType=").append(valuableType);
        sb.append('}');
        return sb.toString();
    }
}
