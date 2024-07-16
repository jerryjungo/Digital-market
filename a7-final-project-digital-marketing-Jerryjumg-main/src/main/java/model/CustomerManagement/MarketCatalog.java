/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.CustomerManagement;

import java.util.ArrayList;

import model.MarketModel.Market;

/**
 *
 * @author kal bugrara
 */
public class MarketCatalog {
    
   ArrayList<Market> markets;
   public MarketCatalog(){
       markets = new ArrayList<Market>();
   }

    public ArrayList<Market> getMarkets() {
        return markets;
    }
    
}
