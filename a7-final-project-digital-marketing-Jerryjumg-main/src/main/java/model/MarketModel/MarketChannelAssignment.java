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
public class MarketChannelAssignment {
    
    Market market;
    Channel channel;
    int adbudget;
    int targetrevenue;
    int actualrevenue;
    //ArrayList<SolutionOffer> solutionoffers = new ArrayList<SolutionOffer>();
    
    public MarketChannelAssignment(Market m, Channel c){
        
        market = m;
        channel = c;
        adbudget = 0;
        //targetrevenue = 0;
        //revenue = 0;

    }
    
    public Market getMarket(){
        return market;
    }

    public Channel getChannel(){
        return channel;
    }
}
