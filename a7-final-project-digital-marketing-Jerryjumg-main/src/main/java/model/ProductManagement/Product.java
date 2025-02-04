/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ProductManagement;

import java.util.ArrayList;

import model.OrderManagement.OrderItem;

/**
 *
 * @author kal bugrara
 */
public class Product {
    private String name;
    private int floorPrice;
    private int ceilingPrice;
    private int targetPrice;
    ArrayList<OrderItem> orderItems;

    public Product(int tp, int fp, int cp) {

        floorPrice = fp;
        ceilingPrice = cp;
        targetPrice = tp;
        orderItems = new ArrayList<OrderItem>();
    }

    public Product(String n,int tp, int fp, int cp) {
        name = n;
        floorPrice = fp;
        ceilingPrice = cp;
        targetPrice = tp;
        orderItems = new ArrayList<OrderItem>();
    }

    public Product updateProduct(int tp, int fp, int cp) {
        floorPrice = fp;
        ceilingPrice = cp;
        targetPrice = tp;
        return this; // returns itself
    }

    public int getTargetPrice() {
        return targetPrice;
    }

    public void addOrderItem(OrderItem oi) {
        orderItems.add(oi);
    }

    // Number of item sales above target
    public int getNumberOfProductSalesAboveTarget() {
        int sum = 0;
        for (OrderItem oi : orderItems) {
            if (oi.isActualAboveTarget() == true)
                sum = sum + 1;
        }
        return sum;
    }

    public int getNumberOfProductSalesBelowTarget() {
        int sum = 0;
        for (OrderItem oi : orderItems) {
            if (oi.isActualBelowTarget() == true)
                sum = sum + 1;
        }
        return sum;
    }

    public boolean isProductAlwaysAboveTarget() {

        for (OrderItem oi : orderItems) {
            if (oi.isActualAboveTarget() == false)
                return false; //
        }
        return true;
    }
    // calculates the revenues gained or lost (in relation to the target)
    // For example, if target is at $2000 and actual is $2500 then revenue gained
    // is $500 above the expected target. If the actual is $1800 then the lose will
    // be $200
    // Add all these difference to get the total including wins and loses

    public int getOrderPricePerformance() {
        int sum = 0;
        for (OrderItem oi : orderItems) {
            sum = sum + oi.calculatePricePerformance(); // positive and negative values
        }
        return sum;
    }

    public int getSalesVolume() {
        int sum = 0;
        for (OrderItem oi : orderItems) {
            sum = sum + oi.getOrderItemTotal(); // positive and negative values
        }
        return sum;
    }

    public void setName(String n) {
        name = n;
    }

    @Override
    public String toString() {
        if (name.length() > 20)
            return name.substring(0, 20)+"...";
        else
        return name;
    }

    public String getName() {
        if (name.length() > 20)
            return name.substring(0, 20)+"...";
        else
            return name;
    }

    public int getFloorPrice() {
        return floorPrice;
    }

    public int getCeilingPrice() {
        return ceilingPrice;
    }

}
