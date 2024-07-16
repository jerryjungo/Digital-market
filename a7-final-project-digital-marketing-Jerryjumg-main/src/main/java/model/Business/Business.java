/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import model.CustomerManagement.ChannelCatalog;
import model.CustomerManagement.CustomerDirectory;
import model.CustomerManagement.CustomerProfile;
import model.CustomerManagement.MarketCatalog;
import model.MarketModel.Channel;
import model.MarketModel.Market;
import model.MarketingManagement.MarketingPersonDirectory;
import model.OrderManagement.MasterOrderList;
import model.Personnel.EmployeeDirectory;
import model.Personnel.PersonDirectory;
import model.ProductManagement.ProductSummary;
import model.ProductManagement.ProductsReport;
import model.ProductManagement.SolutionOffer;
import model.ProductManagement.SolutionOfferCatalog;
import model.SalesManagement.SalesPersonDirectory;
import model.Supplier.Supplier;
import model.Supplier.SupplierDirectory;
import model.UserAccountManagement.UserAccountDirectory;

/**
 *
 * @author kal bugrara
 */
public class Business {

    String name;
    PersonDirectory persondirectory;
    MasterOrderList masterorderlist;
    SupplierDirectory suppliers;
    MarketCatalog marketcatalog;
    ChannelCatalog channelcatalog;
    SolutionOfferCatalog solutionoffercatalog;
    CustomerDirectory customerdirectory;
    EmployeeDirectory employeedirectory;
    SalesPersonDirectory salespersondirectory;
    UserAccountDirectory useraccountdirectory;
    MarketingPersonDirectory marketingpersondirectory;

    public Business(String n) {
        name = n;
        masterorderlist = new MasterOrderList();
        suppliers = new SupplierDirectory();
        solutionoffercatalog = new SolutionOfferCatalog();
        persondirectory = new PersonDirectory();
        customerdirectory = new CustomerDirectory(this);
        salespersondirectory = new SalesPersonDirectory(this);
        useraccountdirectory = new UserAccountDirectory();
        marketingpersondirectory = new MarketingPersonDirectory(this);
        employeedirectory = new EmployeeDirectory(this);
        marketcatalog = new MarketCatalog();
        channelcatalog = new ChannelCatalog();

    }

    public int getSalesVolume() {
        return masterorderlist.getSalesVolume();

    }

    public PersonDirectory getPersonDirectory() {
        return persondirectory;
    }

    public UserAccountDirectory getUserAccountDirectory() {
        return useraccountdirectory;
    }
    public MarketingPersonDirectory getMarketingPersonDirectory() {
        return marketingpersondirectory;
    }

    public SupplierDirectory getSupplierDirectory() {
        return suppliers;
    }

    public ProductsReport getSupplierPerformanceReport(String n) {
        Supplier supplier = suppliers.findSupplier(n);
        if (supplier == null) {
            return null;
        }
        return supplier.prepareProductsReport();

    }

    public ArrayList<ProductSummary> getSupplierProductsAlwaysAboveTarget(String n) {

        ProductsReport productsreport = getSupplierPerformanceReport(n);
        return productsreport.getProductsAlwaysAboveTarget();

    }

    public int getHowManySupplierProductsAlwaysAboveTarget(String n) {
        ProductsReport productsreport = getSupplierPerformanceReport(n); // see above
        int i = productsreport.getProductsAlwaysAboveTarget().size(); //return size of the arraylist
        return i;
    }

    public CustomerDirectory getCustomerDirectory() {
        return customerdirectory;
    }

    public SalesPersonDirectory getSalesPersonDirectory() {
        return salespersondirectory;
    }

    public MasterOrderList getMasterOrderList() {
        return masterorderlist;
    }
        public EmployeeDirectory getEmployeeDirectory() {
        return employeedirectory;
    }

    public void printShortInfo(){
        System.out.println("Checking what's inside the business hierarchy.");
        suppliers.printShortInfo();
        customerdirectory.printShortInfo();
        masterorderlist.printShortInfo();
    }

    public MarketCatalog getMarketcatalog() {
        return marketcatalog;
    }

    public ChannelCatalog getChannelcatalog() {
        return channelcatalog;
    }
    
    public SolutionOfferCatalog getSolutionoffercatalog() {
        return solutionoffercatalog;
    }

    public void generateRevenueByMarket(Market market) {
        System.out.println("Revenue by Market: " + market.getName());
        HashMap<Channel, Integer> revenueByChannel = new HashMap<>();
       
       for(CustomerProfile cp: getCustomerDirectory().getCustomerList() ) {
         for (SolutionOffer so : cp.getOrders().get(0).getSolutionoffers()) {
            if (so.getMarketChannelComb().getMarket().equals(market)) {
                Channel channel = so.getMarketChannelComb().getChannel();
                if (revenueByChannel.containsKey(channel)) {
                    revenueByChannel.put(channel, revenueByChannel.get(channel) + so.getPrice());
                } else {
                    revenueByChannel.put(channel, so.getPrice());
                }
            }
        }
    }
        double totalRevenue = 0;
        for (Channel channel : revenueByChannel.keySet()) {
            System.out.println(channel.getName() + ": " + revenueByChannel.get(channel));
            totalRevenue += revenueByChannel.get(channel);
        }
        System.out.printf("%-35s | %-35s\n", "Channel", "Revenue");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
        for (Channel channel : revenueByChannel.keySet()) {
            System.out.printf("%-35s | %-35s\n", channel.getName(), revenueByChannel.get(channel));
        }
        System.out.println("-------------------------------------------------------");
        System.out.printf("%-35s %-35s\n", "Total Revenue", totalRevenue);
    }

    public void generateRevenueByChannel(Channel channel) {
        System.out.println("Revenue by Channel: " + channel.getName());
        HashMap<Market, Integer> revenueByMarket = new HashMap<>();
        for(CustomerProfile cp: getCustomerDirectory().getCustomerList() ) {
         for (SolutionOffer so : cp.getOrders().get(0).getSolutionoffers()) {
            if (so.getMarketChannelComb().getChannel().equals(channel)) {
                Market market = so.getMarketChannelComb().getMarket();
                if (revenueByMarket.containsKey(market)) {
                    revenueByMarket.put(market, revenueByMarket.get(market) + so.getPrice());
                } else {
                    revenueByMarket.put(market, so.getPrice());
                }
            }
        }
    }
        double totalRevenue = 0;
        for (Market market : revenueByMarket.keySet()) {
            System.out.println(market.getName() + ": " + revenueByMarket.get(market));
            totalRevenue += revenueByMarket.get(market);
        }
        System.out.printf("%-35s | %-35s\n", "Market", "Revenue");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
        for (Market market : revenueByMarket.keySet()) {
            System.out.printf("%-35s | %-35s\n", market.getName(), revenueByMarket.get(market));
        }
        System.out.println("-----------------------------------------------------");
        System.out.printf("%-35s %-35s\n", "Total Revenue", totalRevenue);
    }

    public void generateRevenueByAds(Market market) {
        System.out.println("Revenue by Ads: " + market.getName());
        HashMap<Channel, Integer> revenueByChannel = new HashMap<>();
        for(CustomerProfile cp: getCustomerDirectory().getCustomerList() ) {
         for (SolutionOffer so : cp.getOrders().get(0).getSolutionoffers()) {
            if (so.getMarketChannelComb().getMarket().equals(market) && so.getAds()) {
                Channel channel = so.getMarketChannelComb().getChannel();
                if (revenueByChannel.containsKey(channel)) {
                    revenueByChannel.put(channel, revenueByChannel.get(channel) + so.getPrice());
                } else {
                    revenueByChannel.put(channel, so.getPrice());
                }
            }
        }
    }
        double totalRevenue = 0;
        for (Channel channel : revenueByChannel.keySet()) {
            System.out.println(channel.getName() + ": " + revenueByChannel.get(channel));
            totalRevenue += revenueByChannel.get(channel);
        }
        System.out.printf("%-35s | %-35s\n", "Channel", "Revenue");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
        for (Channel channel : revenueByChannel.keySet()) {
            System.out.printf("%-35s | %-35s\n", channel.getName(), revenueByChannel.get(channel));
        }
        System.out.println("-----------------------------------------------------");
        System.out.printf("%-35s %-35s\n", "Total Revenue", totalRevenue);
    }

    public void generateRevenueBySolutionBundles(Market market) {
        System.out.println("Revenue by Solution Bundles: " + market.getName());
        ArrayList<SolutionOffer> solutionOffers = new ArrayList<>();
        for(SolutionOffer so: solutionoffercatalog.getSolutionoffers()){
            if(so.getMarketChannelComb().getMarket().equals(market)){
                solutionOffers.add(so);
            }
        }
        System.out.println("Please choose a solution bundle:" );
        for(int i = 0; i < solutionOffers.size(); i++){
            System.out.println(i+1 + ". " + solutionOffers.get(i).toString());
        }
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        double totalRevenue = 0;
        System.out.printf("%-120s | %-35s\n", "Solution Bundle", "Revenue($)");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
        if(choice < solutionOffers.size() && choice > 0){
            for(CustomerProfile cp: getCustomerDirectory().getCustomerList() ) {
                for (SolutionOffer so : cp.getOrders().get(0).getSolutionoffers()) {
                    if (so.equals(solutionOffers.get(choice-1))) {

                        totalRevenue += so.getPrice();
                    }
                }
            }
            System.out.printf("%-120s | %-35s\n", solutionOffers.get(choice-1).toString(), totalRevenue);
                    System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
            
        }
        else{
            System.out.println("Invalid input");
            generateRevenueBySolutionBundles(market);
        }
    }
}
