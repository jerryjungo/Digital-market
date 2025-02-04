/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.OrderManagement;

import java.util.ArrayList;

import model.CustomerManagement.CustomerProfile;
import model.MarketModel.MarketChannelAssignment;
import model.ProductManagement.Product;
import model.ProductManagement.SolutionOffer;
import model.SalesManagement.SalesPersonProfile;

/**
 *
 * @author kal bugrara
 */
public class Order {

    ArrayList<OrderItem> orderItems;
    ArrayList<SolutionOffer> solutionoffers; // it's already bundled
    CustomerProfile customer;
    SalesPersonProfile salesperson;
    MarketChannelAssignment mca;
    String status;

    public Order() {
    }

    public Order(CustomerProfile cp) {
        orderItems = new ArrayList<OrderItem>();
        solutionoffers = new ArrayList<SolutionOffer>();
        customer = cp;
        customer.addCustomerOrder(this); // we link the order to the customer
        salesperson = null;
        status = "in process";
    }

    public Order(CustomerProfile cp, SalesPersonProfile ep) {
        orderItems = new ArrayList<OrderItem>();
        customer = cp;
        salesperson = ep;
        customer.addCustomerOrder(this); // we link the order to the customer
        salesperson.addSalesOrder(this);
    }

    public OrderItem newOrderItem(Product p, int actualPrice, int q) {
        OrderItem oi = new OrderItem(p, actualPrice, q);
        orderItems.add(oi);
        return oi;
    }

    // order total is the sumer of the order item totals
    public int getOrderTotal() {
        int sum = 0;
        for (OrderItem oi : orderItems) {
            sum = sum + oi.getOrderItemTotal();
        }
        return sum;
    }

    public int getSolutionTotal() {
        int sum = 0;
        for (SolutionOffer so : solutionoffers) {
            sum = sum + so.getPrice();
        }
        return sum;
    }

    public int getOrderPricePerformance() {
        int sum = 0;
        for (OrderItem oi : orderItems) {
            sum = sum + oi.calculatePricePerformance(); // positive and negative values
        }
        return sum;
    }

    public int getNumberOfOrderItemsAboveTarget() {
        int sum = 0;
        for (OrderItem oi : orderItems) {
            if (oi.isActualAboveTarget() == true) {
                sum = sum + 1;
            }
        }
        return sum;
    }

    // sum all the item targets and compare to the total of the order
    public boolean isOrderAboveTotalTarget() {
        int sum = 0;
        for (OrderItem oi : orderItems) {
            sum = sum + oi.getOrderItemTargetTotal(); // product targets are added
        }
        if (getOrderTotal() > sum) {
            return true;
        } else {
            return false;
        }

    }

    public void CancelOrder() {
        status = "Cancelled";
    }

    public void Submit() {
        status = "Submitted";
    }

    public int getNumberOfItems() {
        return orderItems.size();
    }

    public String getCustomerId() {
        return customer.getCustomerId();
    }
    public void addSolutionOffer(SolutionOffer so) {
        solutionoffers.add(so);
    }
       public ArrayList<SolutionOffer> getSolutionoffers() {
        return solutionoffers;
    }
}
