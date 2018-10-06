package com.example.peter.simpleblackjack;

import java.util.ArrayList;

/**
 * Represents a player of the game, either the user or the computer player
 * @author Peter Alves
 * @version 1.0
 */
public class Player {

    private boolean user = false;
    private ArrayList<Card> hand = new ArrayList<Card>();

    /**
     * Creates a Player based on whether or not the player is the user or not.
     * @param isUser
     */
    public Player(boolean isUser){
        user = isUser;
    }

    /**
     * Returns who this player is.
     * @return True if user, false if dealer.
     */
    public boolean showUser(){
        return user;
    }

    /**
     * Looks at their hand and determines the value of the hand.
     * @return The player's current value.
     */
    public int showHandValue(){
        int handValue = 0;
        for(Card myCard : hand){
            handValue += myCard.getValue();
        }
        return handValue;
    }

    /**
     * Adds a designated card to the player's hand
     * @param nCard
     * @return The Card that was added to their hand.
     */
    public Card addCard(Card nCard){
       if(hand.size()>=5){
           return null;
       }
       else{
           hand.add(nCard);
           return nCard;
       }
    }

    /**
     * Determines if the player has an ace in their hand, and then switches the value of the Ace if possible.
     * @return True if there was an ace to be found and switched, false if not.
     */
    public boolean hasAce(){
        for (Card myCard : hand){
            if(myCard.getValue() == 11){
                myCard.switchAce();
                return true;
            }
        }
        return false;
    }

    /**
     * Determines what the player will return if printed.
     * @return "Player : ##"
     */
    @Override
    public String toString(){
        if(user){
            return "Player : " + this.showHandValue();
        }
        else{
            return "Dealer : " + this.showHandValue();
        }
    }
}
