

//THIS IS A PROJECT DONE BY UZAY MACAR THAT IS DUE TO 08.01.2016 12:00.
//THIS PROJECT IS A FLUENT BLACKJACK GAME THAT HAS A MENU AND A GAMING COMPONENT.
//UZAY MACAR CODED BOTH THE INSIDE GAME MECHANICS (CARD, DECK, AND GAME CLASSES)
//AND OUTSIDE COMPONENTS (THE LAYOUT, BUTTONS, MENU, ETC.)

import javax.swing.JFrame;

public class GUI {
  public static JFrame gameFrame = new JFrame(); //This is the frame in which the real blackjack game will be played.
  public static Game newGame = new Game(gameFrame); //we initialize a 'Game' in order to control, start, and calculate the blackjack game.

  public Thread gameCheckThread;
  
  public void runGUI(String pH, String dH){
	
	gameCheckThread = new Thread(new Runnable() {
        public String pH;
		public String dH;

        public Runnable init(String pH, String dH) {
            this.pH = pH;
			this.dH = dH;
            return this;
        }

        @Override
        public void run() {
			gameFrame.getContentPane().removeAll(); //we remove everything from the frame.
			newGame = new Game(gameFrame); //we initialize a new game on the same frame.
			newGame.formGame(this.pH, this.dH); //we set the atmosphere of the game(which is everything except the cards.)
        }
    }.init(pH, dH));
	
	gameCheckThread.start();
	gameRefreshThread.start();
	
  };
  
  public void updatePlayerHand(String pH){
	  newGame.updatePlayerHand(pH);
  }
  
  public void updateDealerHand(String dH){
	  newGame.updateDealerHand(dH);
  }
  
  public void alert(String mess){
	  newGame.alert(mess);
  }
  
  public int option(){
    return newGame.option();
  }

  public static Thread gameRefreshThread = new Thread () { //this first thread (each thread must have a run method) serves to continuously [since we put true inside the while loop, it will continue forever.] refresh the component.
    public void run () {
      while(true){
        newGame.atmosphereComponent.refresh(newGame.faceDown);
      }
    }
  };

}

