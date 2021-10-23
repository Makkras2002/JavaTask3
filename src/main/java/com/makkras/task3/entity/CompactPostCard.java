package com.makkras.task3.entity;

import com.makkras.task3.entity.enumsource.ThemeType;
import com.makkras.task3.entity.enumsource.ValuableType;

import java.time.Year;

public class CompactPostCard extends CustomPostCard{
    private String compactPostCode;

    public CompactPostCard(int id, ThemeType themeType, boolean wasSent, Year year, ValuableType valuableType, String compactPostCode) {
        super(id, themeType, wasSent, year, valuableType);
        this.compactPostCode = compactPostCode;
    }
    public CompactPostCard(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CompactPostCard that = (CompactPostCard) o;

        return compactPostCode != null ? compactPostCode.equals(that.compactPostCode) : that.compactPostCode == null;
    }

    public String getCompactPostCode() {
        return compactPostCode;
    }

    public void setCompactPostCode(String compactPostCode) {
        this.compactPostCode = compactPostCode;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (compactPostCode != null ? compactPostCode.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CompactPostCard{");
        sb.append("compactPostCode='").append(compactPostCode).append('\'');
        sb.append(", id=").append(id);
        sb.append(", themeType=").append(themeType);
        sb.append(", wasSent=").append(wasSent);
        sb.append(", year=").append(year);
        sb.append(", valuableType=").append(valuableType);
        sb.append('}');
        return sb.toString();
    }

}
