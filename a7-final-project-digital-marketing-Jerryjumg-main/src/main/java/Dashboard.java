
import java.util.Scanner;

import model.Business.Business;

public class Dashboard {
Business business; 


   public Dashboard(Business b) {
        business = b;
   }
   
   public void show(){
     System.out.println("Welcome to the Dashboard");
     System.out.println("1. Show Revenue");
     System.out.println("2. Customer Orders");
     Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                showRevenue();
                break;
            case 2:
                showCustomerOrders();
                break;
            default:
                System.out.println("Invalid input");
                show();
                break;
        }
   }

   private void showCustomerOrders(){
      ConfigurePerson configurePerson = new ConfigurePerson(business);
        configurePerson.askName();
        configurePerson.askMarket();
        configurePerson.askChannel();
        configurePerson.askBudget();
        DisplaySolutionOffer displaySolutionOffer = new DisplaySolutionOffer(business, configurePerson.getCustomerProfile());
        displaySolutionOffer.display();
   }


   private void showRevenue(){
        System.out.println("Make a choice:");
        System.out.println("1. Show Revenue by Market");
        System.out.println("2. Show Revenue by Channel");
        System.out.println("3. Show Revenue by Ads");
        System.out.println("4. Show Revenue by Solution Bundles");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if (choice == 1) {
            showRevenueByMarket();
        }
        else if (choice == 2) {
            showRevenueByChannel();
        }
        else if (choice == 3) {
            showRevenueByAds();
        }
        else if (choice == 4) {
            showRevenueBySolutionBundles();
        }
        else {
            System.out.println("Invalid input");
            showRevenue();
        }
   }

   private void showRevenueByMarket(){
        System.out.println("Make a choice:");
        for (int i = 1; i <= business.getMarketcatalog().getMarkets().size(); i++) {
            System.out.println(i + ". " + business.getMarketcatalog().getMarkets().get(i-1).getName());
        }
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if (choice < business.getMarketcatalog().getMarkets().size() && choice > 0) {
            business.generateRevenueByMarket(business.getMarketcatalog().getMarkets().get(choice-1));
        }
        else {
            System.out.println("Invalid input");
            showRevenueByMarket();
        }
    
   }
   private void showRevenueByChannel(){
        System.out.println("Make a choice:");
        for (int i = 1; i <= business.getChannelcatalog().getChannels().size(); i++) {
            System.out.println(i + ". " + business.getChannelcatalog().getChannels().get(i-1).getName());
        }
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if (choice < business.getChannelcatalog().getChannels().size() && choice > 0) {
            business.generateRevenueByChannel(business.getChannelcatalog().getChannels().get(choice-1));
        }
        else {
            System.out.println("Invalid input");
            showRevenueByChannel();
        }
   }

   private void showRevenueByAds(){
        System.out.println("Make a choice:");
        for (int i = 1; i <= business.getMarketcatalog().getMarkets().size(); i++) {
            System.out.println(i + ". " + business.getMarketcatalog().getMarkets().get(i-1).getName());
        }
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if (choice < business.getMarketcatalog().getMarkets().size() && choice > 0) {
            business.generateRevenueByAds(business.getMarketcatalog().getMarkets().get(choice-1));
        }
        else {
            System.out.println("Invalid input");
            showRevenueByMarket();
        }
   }
   private void showRevenueBySolutionBundles(){
        System.out.println("Make a choice:");
        for (int i = 1; i <= business.getMarketcatalog().getMarkets().size(); i++) {
            System.out.println(i + ". " + business.getMarketcatalog().getMarkets().get(i-1).getName());
        }
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if (choice < business.getMarketcatalog().getMarkets().size() && choice > 0) {
            business.generateRevenueBySolutionBundles(business.getMarketcatalog().getMarkets().get(choice-1));
        }
        else {
            System.out.println("Invalid input");
            showRevenueByMarket();
        }
   }
}

