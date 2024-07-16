import java.util.ArrayList;
import java.util.Scanner;
import model.Business.Business;
import model.CustomerManagement.CustomerProfile;
import model.OrderManagement.Order;
import model.ProductManagement.SolutionOffer;

public class DisplaySolutionOffer {
CustomerProfile customerProfile;
Business business;
Order order;
String  CYAN_BOLD = "\033[1;36m";  // CYAN
String  RESET = "\033[0m";  // Text Reset

   public DisplaySolutionOffer(Business business, CustomerProfile customerProfile) {
        this.business = business;
        this.customerProfile = customerProfile;
        order = new Order(customerProfile);
   }

   public void display(){
       ArrayList<SolutionOffer> solutionOffers = business.getSolutionoffercatalog().findSolutionsForMarket(customerProfile.getMarket(), customerProfile.getChannel(), customerProfile.getBudget());
       int i=1; 
       System.out.println("");
       System.out.printf("%-2s %-35s | %-35s | %-35s | %-35s\n","No","Item 1","Item 2","Item 3","Total price");
       System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
       for(SolutionOffer so: solutionOffers){
        if(so.getAds()){
            System.out.printf("%-2s %-35s | %-35s | %-35s | %-35s\n",i,so.getProducts().get(0).getName()+" with Ads",so.getProducts().get(1).getName()+" with Ads",so.getProducts().get(2).getName()+" with Ads",so.getPrice());
        }
          else { System.out.printf(CYAN_BOLD+"%-2s %-35s | %-35s | %-35s | %-35s\n"+RESET,i,so.getProducts().get(0).getName(),so.getProducts().get(1).getName(),so.getProducts().get(2).getName(),so.getPrice());}
            i++;
        }
       Scanner scanner = new Scanner(System.in);
         System.out.println("Select a book collection to purchase");
            int choice = scanner.nextInt();
            if(choice<solutionOffers.size() && choice>0){
                System.out.println("You have purchased \n"+ solutionOffers.get(choice-1).toString());
                order.getSolutionoffers().add(solutionOffers.get(choice-1));
                System.out.println("Would you like to purchase another book collection? (Y/N)");
                String answer = scanner.next();
                if(answer.equals("Y")){
                    display();
                }
                else{
                    System.out.println("Thank you for shopping with us!");
                    displayTotal();
                }
            }
            else{
                System.out.println("Invalid input");
                display();
            }
}
    private void displayTotal(){
        System.out.printf(CYAN_BOLD+"%-35s %-35s %-35s %-35s\n"+RESET,"Item 1","Item 2","Item 3","Total price($)");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
        for (SolutionOffer so : order.getSolutionoffers()) {
            System.out.printf("%-35s %-35s %-35s %-35s\n",so.getProducts().get(0).getName(),so.getProducts().get(1).getName(),so.getProducts().get(2).getName(),so.getPrice());
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Your total is $"+order.getSolutionTotal());
    }

}
