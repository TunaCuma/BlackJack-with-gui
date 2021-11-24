import java.util.Scanner;
public class Main{

    public static void main(String[] args) {
        GUI gui = new GUI();
        Scanner in = new Scanner(System.in);

        Hand dealer = new Hand();
        Hand player = new Hand();
        

        System.out.println("Starting the game with the following deck:");

        System.out.println(Hand.getDeck());
        System.out.println("Dealer is dealing cards...");

        
        for(int i=0;i<2;i++) //deal 2 cards to Dealer
        {
            dealer.takeCard();
            player.takeCard();
        }

        //printing decks
        System.out.println("Players deck:");
        System.out.println(player.getCards());
        System.out.println("Dealers deck:");
        System.out.println(dealer.getCards().charAt(0)+"?");

        gui.runGUI(player.getCards(), dealer.getCards());
        
        Boolean keepPlay = true;
        do
        {   
            //starting with player
            System.out.print("\nPlease enter your choice:\n1) Hit\n2) Stand\n");
            //String choice = in.nextLine();
            int n = gui.option();
            n++;
            System.out.println(n);

            if(n == 1)
            {
                System.out.println("\nHit! Dealer is giving the player a card...");
                    
                player.takeCard();

                System.out.println("Player's hand:");
                System.out.println(player.getCards());

                gui.updatePlayerHand(player.getCards());

                if (player.getSum() > 21)
                {
                    System.out.println("\nPlayer scored over 21. Player lost!");
                    gui.alert("Player scored over 21. Player lost!");
                    keepPlay = false;
                }
                else if(player.getSum() == 21)
                {
                    System.out.println("\nPlayer scored 21. Player wins!");
                    gui.alert("Player scored 21. Player wins!");
                    keepPlay = false;
                }
            }
            else if(n == 2)
            {
                //its dealers turn when player decides to stand
                System.out.printf("\nStand! Player's turn is now over. Player's hand sums to %d. \n", player.getSum());
                System.out.println("Dealers deck:");
                System.out.println(dealer.getCards());
                

                while (dealer.getSum() < 21 && dealer.getSum() <= player.getSum())
                {
                    System.out.println("Dealer is drawing cards...");       
                    dealer.takeCard();
                    System.out.println("Dealers deck:");
                    System.out.println(dealer.getCards());
                    gui.updateDealerHand(dealer.getCards());
                }

                if (dealer.getSum() > 21)
                {
                    System.out.println("\nDealer scored over 21. Player wins!");
                    gui.alert("Dealer scored over 21. Player wins!");
                }
                else if(dealer.getSum() == 21)
                {
                    System.out.println("\nDealer scored 21. Player lost!");
                    gui.alert("Dealer scored 21. Player lost!");
                }
                else
                {
                    System.out.println("\nDealer scored more than player. Player lost!");
                    gui.alert("Dealer scored more than player. Player lost!");
                }
                keepPlay = false;
            }
        }   
        while(keepPlay);
        System.out.println(Hand.getDeck());
        in.close();
    }
}