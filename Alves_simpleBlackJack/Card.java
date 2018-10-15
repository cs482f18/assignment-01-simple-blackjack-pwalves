package com.example.peter.simpleblackjack;

import android.util.Log;

/**
 * Class for Card objects. Cards contain their suit, number, value, and symbol.
 * @author Peter Alves
 * -- add date in the @version tag such as @version 1.0 10/06.2018
 * @version 1.0
 */
public class Card {

    /** Put a description of the instance variable */
    private int suit = -1;
    /** The numeric value of the Card */
    private int number = -1;
    private int value = -1;
    private String symbol = "INVALID";

    /**
     * Creates a card object given the number on the card and the suit of the card.
     * nSuit ranges from 0 to 3.
     * nNumber ranges from 0 to 12.
     * -- write a description of the parameter like the following
     * @param nSuit the numeric suite value of the card
     * @param nNumber the numeric rank of the card
     */
    public Card(int nSuit, int nNumber){
        if(!isValidCard(nSuit, nNumber)){
            Log.w("PAPROGRAM", "Invalid Card");
        }
        else{
            // These are the places you should be using the switch/case
            suit = nSuit;
            number = nNumber;
            if(nNumber >=10){
                value = 10;
            }
            else if(nNumber == 0){
                value = 11;
            }
            else{
                value = nNumber+1;
            }
            if(nNumber == 12){
                symbol = "K";
            }
            else if(nNumber == 11){
                symbol = "Q";
            }
            else if(nNumber == 10){
                symbol = "J";
            }
            else if(nNumber == 0){
                symbol = "A";
            }
            else{
                symbol = Integer.toString(nNumber+1);
            }
        }
    }

    /**
     * Determines if the inputs will create a valid card.
     * @param nSuit  -- param description
     * @param nNumber -- param description
     * @return True if the inputs are valid, false if not.
     */
    public boolean isValidCard(int nSuit, int nNumber){
        if(nSuit >= 0 && nSuit <= 3){
            if(nNumber >= 0 && nNumber <= 12){
                return true;
            }
        }
        return false;
    }

    /**
     * Changes the value of the card, specifically if the card is an ace.
     * @return True if the card was an ace and had not had its value changed yet.
     */
    public boolean switchAce(){
        if(number == 0){
            if(value == 1){
                value = 11;
            }
            else{
                value = 1;
            }
            return true;
        }
        return false;
    }

    /**
     * Returns the current value of the Card.
     * @return int value
     */
    public int getValue(){
        return value;
    }

    /**
     * Specifies the String to print when printing a Card.
     * @return "Card Number Card Suit"
     */
    @Override
    public String toString(){
        String pSuit = "NONE";
        if(suit == 0){
            pSuit = "♠";
        }
        else if(suit == 1){
            pSuit = "♡";
        }
        else if(suit == 2){
            pSuit = "♣";
        }
        else if(suit == 3){
            pSuit = "♢";
        }
        return symbol + pSuit;
    }
}
