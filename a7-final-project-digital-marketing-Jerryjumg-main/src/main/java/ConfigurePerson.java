import java.util.Scanner;

import model.Business.Business;
import model.CustomerManagement.CustomerProfile;
import model.Personnel.Person;

public class ConfigurePerson {
CustomerProfile customerProfile;
   Business business;

    public ConfigurePerson(Business business) {
         this.business = business;
    }

   public void askName(){
       System.out.println("What is your name?");
       Scanner scanner = new Scanner(System.in);
         String name = scanner.nextLine();
            customerProfile = new CustomerProfile(new Person(name));
   }
   
   public void askMarket(){
       System.out.println("What background are you from?");
       Scanner scanner = new Scanner(System.in);
       for (int i = 1; i <= business.getMarketcatalog().getMarkets().size(); i++) {
           System.out.println(i + ". " + business.getMarketcatalog().getMarkets().get(i-1).getName());
       }
         int market = scanner.nextInt();
         if (market < business.getMarketcatalog().getMarkets().size() && market > 0) {
            customerProfile.setMarket(business.getMarketcatalog().getMarkets().get(market-1));
         }
         else {
            System.out.println("Invalid input");   // outside the scope
            askMarket();
         }
   }

   public void askChannel(){
       System.out.println("Which media did you hear about us?");
         Scanner scanner = new Scanner(System.in);
            for (int i = 1; i <= business.getChannelcatalog().getChannels().size(); i++) {
                System.out.println(i + ". " + business.getChannelcatalog().getChannels().get(i-1).getName());
            }
            int channel = scanner.nextInt();
            if (channel < business.getChannelcatalog().getChannels().size() && channel > 0) {
                customerProfile.setChannel(business.getChannelcatalog().getChannels().get(channel-1));
            }
            else {
                System.out.println("Invalid input");   
                askChannel();
            }
   }

   public void askBudget(){
         System.out.println("What is your budget?");
         System.out.println("1. $$");
         System.out.println("2. $$$");
         System.out.println("3. $$$$");
        Scanner scanner = new Scanner(System.in);
        int budget = scanner.nextInt();
        if (budget == 1) {
            customerProfile.setBudget(300);
        }
        else if (budget == 2) {
            customerProfile.setBudget(500);
        }
        else if (budget == 3) {
            customerProfile.setBudget(Integer.MAX_VALUE);
        }
        else {
            System.out.println("Invalid input");   
            askBudget();
        }
                
   }

   public CustomerProfile getCustomerProfile() {
        return customerProfile;
    }

}
