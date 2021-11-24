import java.util.Random;

public class Hand {
    
    private int sumOfCards = 0;
    private String Cards = "";
    private static String deck = deckInitialize();

    Random rand = new Random();

    public Hand(){
    }

    public Hand(String Cards){
        setCards(Cards);
    }

    //setters

    public void setCards(String Cards){
        this.Cards = Cards;
    }

    //getters

    public int getSum(){
        return sumOfCards;
    }

    public String getCards(){
        return Cards;
    }

    public static String getDeck(){
        return deck;
    }
    
    //methods

    public String toString(){
        return Cards;
    }

    public static String deckInitialize(){
        String deck = "";
        for(int i=1;i<10;i++) //initilazing deck
        {
            for(int j=0;j<4;j++)
            {
                deck += String.valueOf(i);
            }
        }
        return deck;
    }

    public static void removeFromDeck(int index){
        deck = deck.substring(0, index) + deck.substring(index+1,deck.length());
    }

    public void takeCard(){
        int randomChoice = rand.nextInt(deck.length());
        int index = randomChoice;
        this.Cards += deck.charAt(index);
        this.sumOfCards += Character.getNumericValue(deck.charAt(index));
        removeFromDeck(index);
    }
}