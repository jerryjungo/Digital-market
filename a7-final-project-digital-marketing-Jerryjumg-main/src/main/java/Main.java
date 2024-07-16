import model.Business.Business;
import model.CustomerManagement.CustomerDirectory;
import model.CustomerManagement.CustomerProfile;
import model.OrderManagement.Order;
import model.OrderManagement.OrderItem;
import model.Personnel.Person;
import model.ProductManagement.Product;
import model.ProductManagement.ProductCatalog;
import model.ProductManagement.ProductsReport;
import model.Supplier.Supplier;
import model.Supplier.SupplierDirectory;
import model.ProductManagement.SolutionOffer;
import model.ProductManagement.SolutionOfferCatalog;
import model.MarketModel.Market;
import model.MarketModel.MarketChannelAssignment;
import model.MarketModel.Channel;

import com.github.javafaker.Faker;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

   // create a business
   Business business = new Business("Jerrys Book Store");


   
   // define markets
   ArrayList<String> characteristics = new ArrayList<String>();
   characteristics.add("Students");
   characteristics.add("Professionals");
   characteristics.add("Teachers");
   characteristics.add("Writers");

       
    Market m1 = new Market("Education", 0.7f);
    Market m2 = new Market("Business", 1.2f);
    Market m3 = new Market("Creative", 0.9f);
    Market m4 = new Market("Academic", 1f);
    ArrayList<Market> markets =business.getMarketcatalog().getMarkets();
    markets.add(m1);
    markets.add(m2);
    markets.add(m3);
    markets.add(m4);
    //System.out.println(business.getMarketcatalog().getMarkets().size());

   // create channels
    ArrayList<Channel> channels = business.getChannelcatalog().getChannels();
    Channel youtubeChannel = new Channel("youtube", 0.97F);
    Channel tiktokChannel = new Channel("tiktok", 0.8F );
    Channel facebookChannel = new Channel("facebook", 0.9F);
    Channel instagramChannel = new Channel("instagram", 1.1F);
    Channel twitterChannel = new Channel("twitter", 1.2F);
    Channel googlechromeChannel = new Channel("googlechrome", 1.3F);
    channels.add(youtubeChannel);
    channels.add(tiktokChannel);
    channels.add(facebookChannel);
    channels.add(instagramChannel);
    channels.add(twitterChannel);
    channels.add(googlechromeChannel);

    Faker faker = new Faker();
    //Define market/channel combination

    ArrayList<MarketChannelAssignment> assignments = new ArrayList<>();

        for (Market market : new Market[]{m1, m2, m3, m4}) {
            for (Channel channel : channels) {
                assignments.add(new MarketChannelAssignment(market, channel));
            }
    }

    Random random = new Random();
    //use faker to generate 100 products(book names) 
    ArrayList<Product> products = new ArrayList<Product>();
    for (int i = 0; i < 100; i++) {
        Product p = new Product(faker.book().title(), random.nextInt(70,150), 70, 150);
        products.add(p); 
    }

    // bundle 3 books into a solution offer(3 books = 1 solution offer)
    ArrayList<SolutionOffer> solutionOffers = business.getSolutionoffercatalog().getSolutionoffers();
    int j=0;
    for (MarketChannelAssignment assignment : assignments) {
       for (int k = 0; k < 10; k++) {
        SolutionOffer so = new SolutionOffer(assignment);
        for (int i = 0; i < 3; i++) {
            if (j >= products.size()) {
                j=0;
            }
            so.addProduct(products.get(j));
            j++;
        }
        so.setAds(random.nextBoolean());
        solutionOffers.add(so);
    }
    }

    // Adding customers
    ArrayList<CustomerProfile> customers = business.getCustomerDirectory().getCustomerList();
    for (int i = 0; i < 100; i++) {
        Person person = new Person(faker.name().fullName());
        CustomerProfile cp = new CustomerProfile(person);
        customers.
        add(cp);
    }
    // Assigning customers to markets
    for (CustomerProfile customer : customers) {
        customer.setMarket(markets.get(random.nextInt(0, markets.size())));
    }
    // Assigning customers to channels
    for (CustomerProfile customer : customers) {
        customer.setChannel(channels.get(random.nextInt(0, channels.size())));
    }

    //add solution offers to customers
    for (CustomerProfile customer : customers) {
        ArrayList<SolutionOffer> offers = business.getSolutionoffercatalog().findSolutionsForMarket(customer.getMarket(), customer.getChannel(), Integer.MAX_VALUE);
        customer.getOrders().add(new Order(customer));
        for(int i=0; i<3; i++){
            customer.getOrders().get(0).addSolutionOffer(offers.get(random.nextInt(0, offers.size())));
        }
    }
    
    Dashboard dashboard = new Dashboard(business);
    dashboard.show();



    // //assign price for each bundle/market/channel combination
    // for (SolutionOffer so : solutionOffers) {
    //     so.setPrice(faker.number().numberBetween(20, 1000));
    //     //System.out.println(so.getPrice());
    // }

    
    // formatting UI
    
    // Scanner sc = new Scanner(System.in);
    // boolean exitCode = false;
    // while (!exitCode) {
    //     // System.out.println("Welcome to Jerrys Book Store.");
    //     // System.out.println("---------------------------------------------");
    //     // System.out.println("Please choose genre of books you are interested in:");
    //     // System.out.println("1.Education");
    //     // System.out.println("2.Business");
    //     // System.out.println("3.Creative");
    //     // System.out.println("4.Academic");
    //     // System.out.println("5.Exit");
  
        //String input = sc.next();
  
         // System.out.println(input);
     
    // ads for different customers 
    // ----students: "Fuel your academic journey! Dive into our vast collection of textbooks, guides, and novels.
    // Unleash your potential with knowledge. 
    // Visit now and embark on a learning adventure!"
    
    //--- professionals:
    //"Elevate your career with insightful reads! Explore our business, leadership, and self-development books. 
    //Stay ahead of the curve and achieve professional excellence. Shop now for success!"

    //--- teachers:
    //"Inspire minds with our curated selection of educational gems! Find teaching resources, lesson planners, and engaging reads. 
    //Nurture a love for learning in your classroom. Explore today!"

    //--- writers:
    //"Ignite your creativity! Our bookstore is a haven for writers.
    // Immerse yourself in a world of writing guides, inspiration, and literary treasures. Unleash your imagination – explore now!"
   


    // solution offers for ALL markets and channels
    // if (input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") ) {
    //     System.out.println("Please choose the book collections you want to buy:");
    //     System.out.println("---------------------------------------------------------------------------------------------------------");
    //     //System.out.printf("%-2s %-35s %-35s %-35s %-35s","No","Item 1","Item 2","Item 3","Total price");
    // System.out.printf("%-2s %-35s %-35s %-35s %-35s%n","No","Item 1","Item 2","Item 3","Total price");


    // System.out.println("Enter your favorite book collections:" );
    // // scanner method to get the student option with price
    // Scanner scanner = new Scanner(System.in);
    // ArrayList<SolutionOffer> chosenOffers = new ArrayList<>();
    // while (true) {
    //     for (int i = 0; i < solutionOffers.size(); i++) {
    //     SolutionOffer solutionOffer = solutionOffers.get(i);
    //     //total += solutionOffer.getPrice();
    //     System.out.println(i + 1 + ". " + solutionOffer + " " + solutionOffer.getPrice());
    // }
    //     int chosenIndex = scanner.nextInt();
    //     if (chosenIndex == 0 ) {   //input.equals("0")
    //         break;   
    //     }
     
    //     int total =0;
    //     if (chosenIndex >= 0 && chosenIndex < solutionOffers.size()) {
    //         SolutionOffer chosenOffer = solutionOffers.get(chosenIndex-1);
    //         chosenOffers.add(chosenOffer);
    //         total += chosenOffer.getPrice(); // add price to current cart
    //         System.out.println("Added " + chosenOffer + " to cart.Current total: " + total + " dollars");
    //         System.out.println("Enter any number to continue shopping or 0 to exit.");
    //         int chosenIndex2 = scanner.nextInt();
    //         if (chosenIndex2 == 0 ) {
    //             break;
    //         } else {
    //             continue;
    //         }

    //        }  else {
    //         System.out.println("Invalid choice. Please choose a valid number.");
    //     }
    // }
    
    
    // int total = 0;
    // for (SolutionOffer offer : chosenOffers) {
    //     total += offer.getPrice();
    // }

    // System.out.println("Total price of chosen offers: " + total + " dollars");
    // System.out.println("Thank you, have a nice day.");
    // System.out.println("---------------------------------------------------------------------------------------------------------");

    // // //Autogenerate sales orders
    // // generate suppliers
    // ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
    // for (int i = 0; i < 5; i++) {
    //     Supplier s = new Supplier(faker.company().name());
    //     suppliers.add(s);
    // }
    // // generate customers
    // ArrayList<CustomerProfile> customers = business.getCustomerDirectory().getCustomerList();
    // for (int i = 0; i < 50; i++) {
    //     Person person = new Person(faker.name().fullName());
    //     CustomerProfile cp = new CustomerProfile(person);
    //     customers.add(cp);
    // }
    // // generate orders
    // ArrayList<Order> orders = new ArrayList<Order>();
    // for (int i = 0; i < 5; i++) {
    //     Order o = new Order(customers.get(i));
    //     orders.add(o);
    // }

    // // generate orderitems
    // ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
    // for (int i = 0; i < 5; i++) {
    //     OrderItem oi = new OrderItem(products.get(i), faker.number().numberBetween(20, 1000), faker.number().numberBetween(1, 10));
    //     orderItems.add(oi);
    // }

    // // get sales orders
    // for(int i=0; i< customers.size() ; i++){
    // System.out.println("--------Customer Sales Orders--------");
    // System.out.println("Customer Name:" + customers.get(i).getPerson());
    // System.out.println("Orderitem:" + orderItems.get(i).getSelectedProduct());
    // System.out.println("Paid Price | Quantity:" + orderItems.get(i).getActualPrice() + " | " + orderItems.get(i).getQuantity());

    // }
    
     



    //   }

    //   if (input.equals("2"))
    //     orderReport.printOrderReport();

    //   if (input.equals("3"))
    //     exitCode = true;
    // }

       
    





 
    
   
   
    }  
 }  
   
//     }  
// }


// Market building or solution selling
// Define business
// Define market
// Define channels
// Define market/channel combination(very important)
// Manage solution catalog
//   Define solution bundles for different markets channel combinations
//   Select products for the solution bundles
//   Assign price for each bundle/market/channel combination
// Classify customers in certain market/channel combination
// Order solutions from the solution catalog based on customer market profile

// Market bundling or solution selling
// Customer logs in through a channel
// System identifies customer as belonging to specific market
// New solution order is created using the market-channel combination
// Solutions tailored for the market-channel 
// combination order solutions from the solution catalog based on customer market profile