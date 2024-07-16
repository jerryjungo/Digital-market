/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.CustomerManagement;

import java.util.ArrayList;

import model.MarketModel.Channel;

/**
 *
 * @author kal bugrara
 */
public class ChannelCatalog {
    ArrayList<Channel> channels;
    public ChannelCatalog(){
        channels = new ArrayList<Channel>();
    }

    public ArrayList<Channel> getChannels() {
        return channels;
    }


}
