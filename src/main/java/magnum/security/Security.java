package magnum.security;

import java.util.Objects;

public class Security {
    String symbol;
    String companyNameEN;
    String companyNameAR;
    String companyName;
    String market_type;
    String tradingNameEn;
    String tradingNameAr;
    String isin;
    String bond_type;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCompanyNameEN() {
        return companyNameEN;
    }

    public void setCompanyNameEN(String companyNameEN) {
        this.companyNameEN = companyNameEN;
    }

    public String getCompanyNameAR() {
        return companyNameAR;
    }

    public void setCompanyNameAR(String companyNameAR) {
        this.companyNameAR = companyNameAR;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getMarket_type() {
        return market_type;
    }

    public void setMarket_type(String market_type) {
        this.market_type = market_type;
    }

    public String getTradingNameEn() {
        return tradingNameEn;
    }

    public void setTradingNameEn(String tradingNameEn) {
        this.tradingNameEn = tradingNameEn;
    }

    public String getTradingNameAr() {
        return tradingNameAr;
    }

    public void setTradingNameAr(String tradingNameAr) {
        this.tradingNameAr = tradingNameAr;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public String getBond_type() {
        return bond_type;
    }

    public void setBond_type(String bond_type) {
        this.bond_type = bond_type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Security stock = (Security) o;

        if (!Objects.equals(symbol, stock.symbol)) return false;
        if (!Objects.equals(companyNameEN, stock.companyNameEN))
            return false;
        if (!Objects.equals(companyNameAR, stock.companyNameAR))
            return false;
        if (!Objects.equals(companyName, stock.companyName)) return false;
        if (!Objects.equals(market_type, stock.market_type)) return false;
        if (!Objects.equals(tradingNameEn, stock.tradingNameEn))
            return false;
        if (!Objects.equals(tradingNameAr, stock.tradingNameAr))
            return false;
        return Objects.equals(isin, stock.isin);
    }

    @Override
    public int hashCode() {
        int result = symbol != null ? symbol.hashCode() : 0;
        result = 31 * result + (companyNameEN != null ? companyNameEN.hashCode() : 0);
        result = 31 * result + (companyNameAR != null ? companyNameAR.hashCode() : 0);
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (market_type != null ? market_type.hashCode() : 0);
        result = 31 * result + (tradingNameEn != null ? tradingNameEn.hashCode() : 0);
        result = 31 * result + (tradingNameAr != null ? tradingNameAr.hashCode() : 0);
        result = 31 * result + (isin != null ? isin.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Security{" +
                "symbol='" + symbol + '\'' +
                ", companyNameEN='" + companyNameEN + '\'' +
                ", companyNameAR='" + companyNameAR + '\'' +
                ", companyName='" + companyName + '\'' +
                ", market_type='" + market_type + '\'' +
                ", tradingNameEn='" + tradingNameEn + '\'' +
                ", tradingNameAr='" + tradingNameAr + '\'' +
                ", isin='" + isin + '\'' +
                '}';
    }
}
