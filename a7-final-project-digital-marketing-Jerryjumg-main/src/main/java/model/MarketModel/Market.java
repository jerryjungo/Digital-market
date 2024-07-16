/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.MarketModel;

import java.util.ArrayList;
import model.ProductManagement.SolutionOffer;

/**
 *
 * @author kal bugrara
 */
public class Market {
    ArrayList<SolutionOffer> so;
    float markup;
    ArrayList<MarketChannelAssignment> channels;
    ArrayList<String> characteristics;
    ArrayList<Market> submarkets;
    int size;
    String name;

    public Market(String s , float m) {
        characteristics = new ArrayList<String>();
        name = s;
        markup = m;
    }
    
    public float getMarkup() {
        return markup;
    }

    public ArrayList<String> getCharacteristics() {
        return characteristics;
    }
    
    public String getName() {
        return name;
    }
}
