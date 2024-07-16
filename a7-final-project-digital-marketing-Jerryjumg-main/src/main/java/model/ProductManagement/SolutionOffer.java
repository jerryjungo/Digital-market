/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ProductManagement;

import java.util.ArrayList;

import model.MarketModel.MarketChannelAssignment;

/**
 *
 * @author kal bugrara
 */
public class SolutionOffer {
    ArrayList<Product> products;
    boolean ads;

    int price;// floor, ceiling, and target ideas
    MarketChannelAssignment marketChannelComb;

 

    public SolutionOffer(MarketChannelAssignment m) {
        marketChannelComb = m;
        products = new ArrayList<Product>();
        //products = new ArrayList();
    }

    public void addProduct(Product p) {
        products.add(p);
    }

    public void setPrice(int p) {
        price = p;

    }
     public ArrayList<Product> getProducts() {
        return products;
    }
       
    public int getPrice() {
        price=0;
        for(Product p: products){
            price+=p.getTargetPrice();
        }
        price= (int) (price*marketChannelComb.getChannel().getDiscount()* marketChannelComb.getMarket().getMarkup());
        return price;
    }
       public MarketChannelAssignment getMarketChannelComb() {
        return marketChannelComb;
    }
    

    @Override
    public String toString() {
        String f= "%-35s | %-35s | %-35s ";
        String result = String.format(f,products.stream().toArray());
        return result;
    }
   public boolean getAds() {
        return ads;
    }

    public void setAds(boolean ads) {
        this.ads = ads;
    }

}
