/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ProductManagement;

import java.util.ArrayList;

import model.MarketModel.Channel;
import model.MarketModel.Market;
import model.MarketModel.MarketChannelAssignment;

/**
 *
 * @author kal bugrara
 */
public class SolutionOfferCatalog {
    ArrayList<SolutionOffer> solutionoffers;


public SolutionOfferCatalog() {
     solutionoffers = new ArrayList<>();
}

public SolutionOffer newSolutionOffer(MarketChannelAssignment mca){
    SolutionOffer so = new SolutionOffer(mca);
    solutionoffers.add(so);
    return so;
}

public ArrayList<SolutionOffer> getSolutionoffers() {
    return solutionoffers;
}

public ArrayList<SolutionOffer> findSolutionsForMarket(Market m, Channel c, int Budget){
    ArrayList<SolutionOffer> foundsolutions = new ArrayList<SolutionOffer>();
    for(SolutionOffer so: solutionoffers){
        if(so.getMarketChannelComb().getMarket().equals(m) && so.getMarketChannelComb().getChannel().equals(c) && so.getPrice()<=Budget){
            foundsolutions.add(so);
        }
    }
    return foundsolutions;
}


//solution catalog----solution offer------market-channel assignment
//this method will identify all solution offers meant for 
// customers in a market coming in over a channel

//this app will extract the market and channel combo from
// the user profile and use it to pull all offers

//public ArrayList<SolutionOffer> findSolutionsForMarketChannel(MarketChannelAssignment mca){
    // ArrayList<SolutionOffer> foundsolutions = new ArrayList<SolutionOffer>();
    // ArrayList<SolutionOffer> foundsolutions = new ArrayList();
    // for(SolutionOffer so: solutionoffers){
       // if  (so.isOfferTargetedToMarketChannel(mcc)==true){
            // foundsolutions.add(so);
        // }

// find all solution offers available in the market/channel combo
    // return foundsolutions;
      //}






}
