package com.example.peter.simpleblackjack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Model view controller for blackjack game. Mediates between the user and CardGame.
 * @author Peter Alves
 * @version 1.0
 * @since 1.0
 */
public class MainActivity extends AppCompatActivity {

    private CardGame myGame;

    private Player user;
    private Player dealer;

    private int pCards;
    private int dCards;

    private int pVal;
    private int dVal;

    private TextView pTotal;
    private TextView dTotal;

    private TextView gameState;

    private TextView pCard1;
    private TextView pCard2;
    private TextView pCard3;
    private TextView pCard4;
    private TextView pCard5;

    private TextView dCard1;
    private TextView dCard2;
    private TextView dCard3;
    private TextView dCard4;
    private TextView dCard5;

    private boolean gameOver;


    /**
     * Called on app creation. Sets the first state using setStage().
     * @return void
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        pTotal = (TextView) findViewById(R.id.playerValue);
        dTotal = (TextView) findViewById(R.id.compValue);
        gameState = (TextView) findViewById(R.id.gameOutput);

        this.setStage();
    }

    /**
     * Sets the game to a beginning state. Each player gets 2 cards, their value is displayed, and blackjack state is checked.
     * @return void
     */
    public void setStage(){
        myGame = new CardGame(1);
        pCards = 0;
        dCards = 0;
        gameOver = false;

        gameState.setText("");

        pCard1 = (TextView) findViewById(R.id.playerCard1);
        pCard2 = (TextView) findViewById(R.id.playerCard2);
        pCard3 = (TextView) findViewById(R.id.playerCard3);
        pCard4 = (TextView) findViewById(R.id.playerCard4);
        pCard5 = (TextView) findViewById(R.id.playerCard5);

        this.resetCard(pCard1);
        this.resetCard(pCard2);
        this.resetCard(pCard3);
        this.resetCard(pCard4);
        this.resetCard(pCard5);

        dCard1 = (TextView) findViewById(R.id.compCard1);
        dCard2 = (TextView) findViewById(R.id.compCard2);
        dCard3 = (TextView) findViewById(R.id.compCard3);
        dCard4 = (TextView) findViewById(R.id.compCard4);
        dCard5 = (TextView) findViewById(R.id.compCard5);

        this.resetCard(dCard1);
        this.resetCard(dCard2);
        this.resetCard(dCard3);
        this.resetCard(dCard4);
        this.resetCard(dCard5);

        user = new Player(true);
        dealer = new Player(false);

        this.deal(user, pCard1);
        this.deal(user, pCard2);
        this.deal(dealer, dCard1);
        this.deal(dealer, dCard2);

        if(this.hasBlackJack(user) && this.hasBlackJack(dealer)){
            gameState.setText("The Player and Dealer have Blackjack, it's a Tie!");
            gameOver = true;
        }
        else if(this.hasBlackJack(user)){
            gameState.setText("The Player has Blackjack! They Win!");
            gameOver = true;
        }
        else if(this.hasBlackJack(dealer)){
            gameState.setText("The Dealer has Blackjack! They Win!");
            gameOver = true;
        }

    }

    /**
     * Activated when the user presses the "New Game" button. Resets the game and all related objects.
     * @param view
     * @return void
     */
    public void newGame(android.view.View view){
        setStage();
    }

    /**
     * Sets the specified Card to the background color and erases the value assigned to the card.
     * @param myCard
     * @return void
     */
    public void resetCard(TextView myCard){
        int colorBG = getResources().getColor(R.color.colorBG);
        myCard.setBackgroundColor(colorBG);
        myCard.setText("");
    }

    /**
     * Activated when the player presses the "Hit!" button. If the game is not over and the player has fewer than 5 cards, then the player receives a new card.
     * @param view
     * @return void
     *
     */
    public void playerHit(android.view.View view){

        if(!gameOver) {
            if (pCards == 2) {
                deal(user, pCard3);
            } else if (pCards == 3) {
                deal(user, pCard4);
            } else if (pCards == 4) {
                deal(user, pCard5);
            }
            if(!this.hasBusted(user)){
                updatePlayerVals();
            }
        }
    }

    /**
     * It then sets the game state to reflect who busted.
     * @param user
     * @return True if the player's hand exceeds 21 and all aces in a players hand have been set to 1.
     *
     */
    private boolean hasBusted(Player user){
        if(user.showHandValue()>21) {
            if (!myGame.checkAce(user)) {
                if (user.showUser()) {
                    gameState.setText("The Player has Busted! Game Over!");
                    gameOver = true;
                } else {
                    gameState.setText("The Dealer has Busted! Game Over!");
                    gameOver = true;
                }
                return true;
            }
        }
        updatePlayerVals();
        return false;
    }

    /**
     * Deals a card to the specified player. The card is then drawn in the specified card slot on screen. The current value of the players is then calculated and written.
     * @param user
     * @param currCard
     * @return void
     *
     */
    private void deal(Player user, TextView currCard){
        Card c = myGame.hit(user);
        currCard.setText(c.toString());
        currCard.setBackgroundColor(getResources().getColor(R.color.colorFront));
        updatePlayerVals();

        if(user.showUser()){
            pCards++;
        }
        else{
            dCards++;
        }
    }

    /**
     * Returns true if the player won, false if the dealer won or if the game is not yet over.
     * @return user.showHandValue()>dealer.showHandValue()
     *
     */
    public boolean didPlayerWin(){
        if(!gameOver){
            return user.showHandValue()>dealer.showHandValue();
        }
        else{
            return false;
        }
    }

    /**
     * Only called upon the initial 2 card setup. Determines if the player has 21, and since it is only called when the player has 2 cards, the player must have blackjack.
     * @param user
     * @return user.showHandValue() == 21
     *
     */
    public boolean hasBlackJack(Player user){
        return user.showHandValue() == 21;
    }

    /**
     * Activates on "Stand" button press. Runs the dealer's turn. The dealer does not hit on values 17 and above.
     * @param  view
     * @return void
     */
    public void playerStand(android.view.View view){

        if(!gameOver){
            while(!hasBusted(dealer) && dealer.showHandValue()<17 && dCards<=5){
                if (dCards == 2) {
                    deal(dealer, dCard3);
                } else if (dCards == 3) {
                    deal(dealer, dCard4);
                } else if (dCards == 4) {
                    deal(dealer, dCard5);
                }
            }
            if(didPlayerWin() || hasBusted(dealer)){
                gameState.setText("The Player Wins!");
            }
            else if(user.showHandValue() == dealer.showHandValue()){
                gameState.setText("It's a Tie!");
            }
            else{
                gameState.setText("The Dealer Wins!");
            }
            gameOver = true;
        }
    }

    /**
     * This method updates the values on the screen for both players, using their most recent hand values.
     * @return void
     *
     */
    public void updatePlayerVals(){

        pVal = user.showHandValue();
        pTotal.setText(Integer.toString(pVal));

        dVal = dealer.showHandValue();
        dTotal.setText(Integer.toString(dVal));
    }
}
