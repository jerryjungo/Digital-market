package model.MarketModel;
import model.MarketModel.Market;

public class MarketCatalog {
    Market market;
    float markup;

    MarketCatalog(String characterstics, float markup){
        Market market = new Market(characterstics,markup);
        this.markup = markup;
    }
    public Market getMarket() {
        return market;
    }

    public float getMarkup() {
        return markup;
    }
}
