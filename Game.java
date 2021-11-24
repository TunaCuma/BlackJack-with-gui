

import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Font;

public class Game {

  ArrayList<Card> dealerHand; //this is the arraylist for the dealer's hand.
  ArrayList<Card> playerHand; //this is the arraylist for the player's hand.

  public boolean faceDown; //this boolean value will tell the program if we have the card facedown or faceup.

  JFrame frame; //we create a JFrame.
  GameComponent atmosphereComponent; //Two GameComponents: one for the background images, buttons, and the overall atmosphere.
  GameComponent cardComponent; //Other for the cards printing out.

  JButton btnExit;

  public Game(JFrame f) {//with this constructor, we initialize the instant fields accordingly. This constructor gets a JFrame as a parameter.
    dealerHand = new ArrayList<Card>();
    playerHand = new ArrayList<Card>();
    atmosphereComponent = new GameComponent(dealerHand, playerHand);
    frame = f;
    faceDown = true;
  }

  public void formGame(String pH, String dH) {//this method will help us create the background of our game.
    frame.setSize(1130, 665);
    frame.setLocationRelativeTo(null); //this centers the JFrame on the screen.
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false); //we make it impossible for the computer user to resize the Frame.

    btnExit = new JButton("EXIT CASINO");
    btnExit.setBounds(930, 240, 190, 50);
    btnExit.setFont(new Font("Comic Sans MS", Font.BOLD, 16));

    frame.add(btnExit);

    btnExit.addActionListener(new ActionListener() { //we add a action listener to the exit button. 
      public void actionPerformed(ActionEvent e) {
		  System.exit(0); //we exit the program.
      }
    });

    atmosphereComponent = new GameComponent(dealerHand, playerHand); //we initialize the GameComponent that will be the overall atmosphere of our game.
    atmosphereComponent.setBounds(0, 0, 1130, 665);  //we set the bounds of the component.
    frame.add(atmosphereComponent); //we add the component to the frame.
    frame.setVisible(true); //we make the frame visible.
	
	startGame(pH, dH);
  }
  
  public void updatePlayerHand(String pH){
	  playerHand.add(new Card(2, Integer.parseInt("" + pH.charAt(pH.length() -1 )) -1));
	  faceDown = true;
  }
  
  public void updateDealerHand(String dH){
	  dealerHand.add(new Card(2, Integer.parseInt("" + dH.charAt(dH.length() -1 )) -1));
	  faceDown = false;
  }
  
  public void alert(String mess){
	  JOptionPane.showMessageDialog(frame, mess);
  }
  
  public int option(){
    Object[] options = {"Hit!",
                    "Stand."};
    int n = JOptionPane.showOptionDialog(frame,
    "Choose your move",
    "Choose",
    JOptionPane.YES_NO_CANCEL_OPTION,
    JOptionPane.QUESTION_MESSAGE,
    null,
    options,
    options[1]);
    return n;
  }

  public void startGame(String pH, String dH) { //this method starts the game: the cards are drawn and are printed out.
	playerHand.add(new Card(2, Integer.parseInt("" + pH.charAt(0)) -1));
	playerHand.add(new Card(2, Integer.parseInt("" + pH.charAt(1)) -1));
	dealerHand.add(new Card(2, Integer.parseInt("" + dH.charAt(0)) -1));
	dealerHand.add(new Card(2, Integer.parseInt("" + dH.charAt(1)) -1));

    cardComponent = new GameComponent(dealerHand, playerHand); //we initialize our component which accepts two card arraylists.
    cardComponent.setBounds(0, 0, 1130, 665); //we set the bounds of our component.
    frame.add(cardComponent); //we add the component to the grame.
    frame.setVisible(true); //we make the frame visible.
  }
}