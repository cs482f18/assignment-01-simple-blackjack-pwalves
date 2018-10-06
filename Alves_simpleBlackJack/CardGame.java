package com.example.peter.simpleblackjack;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * Holds game logic and helping methods for the blackjack game.
 * @author Peter Alves
 * @version 1.0
 */
public class CardGame {

    Player user = new Player(true);
    Player dealer = new Player(false);
    Card[] deck;
    ArrayList<Integer> dealtCardsIndex = new ArrayList<Integer>();

    /**
     * Makes a CardGame with only 1 deck.
     */
    public CardGame(){
        Log.w("PAPROGRAM", "Made Game");
        CardFactory myFac = new CardFactory();
        deck = myFac.makeDeck(1);
        Log.w("PAPROGRAM", "After Made Game");

    }

    /**
     * Makes a CardGame with a specified number of decks
     * @param numDecks
     */
    public CardGame(int numDecks){
        CardFactory myFac = new CardFactory();
        deck = myFac.makeDeck(numDecks);
    }

    /**
     * Deals the designated player a random card in the deck without replacement and returns that card.
     * @param myPlayer
     * @return Card that was dealt.
     */
    public Card deal(Player myPlayer){
        Random myRand = new Random();
        int cardIndex = 0;
        do{
            cardIndex = myRand.nextInt(deck.length);
        }while(dealtCardsIndex.contains(cardIndex));

        dealtCardsIndex.add(cardIndex);
        return myPlayer.addCard(deck[cardIndex]);
    }

    /**
     * Deals the designated player a card and returns that card.
     * @param myPlayer
     * @return Card that was dealt.
     */
    public Card hit(Player myPlayer){
        Card dealt = deal(myPlayer);
        return dealt;
    }

    /**
     * Checks to see if the designated player has an ace.
     * @param myPlayer
     * @return True if the player has an ace in their hand.
     */
    public boolean checkAce(Player myPlayer){

        return myPlayer.hasAce();
    }

}
