/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.MarketModel;

import java.lang.reflect.Array;

/**
 *
 * @author kal bugrara
 */
public class Channel {
    // String channeltype;
    // int price;
  

    // public Channel(String t, int price) {
    //     this.channeltype = t;
    //     this.price = price;

    // }

    
         final String name;
         final float discount;
    
        public Channel(String name, float discount) {
            this.name = name;
            this.discount = discount;
        }
    
        public String getName() {
            return name;
        }
    
        public float getDiscount() {
            return discount;
        }
    }

