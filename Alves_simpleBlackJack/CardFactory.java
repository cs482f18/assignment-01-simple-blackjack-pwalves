package com.example.peter.simpleblackjack;

import android.util.Log;

/**
 * Makes Cards to be used by other classes.
 * @author Peter Alves
 * @version 1.0  -- date -- 
 */
public class CardFactory {

    private int DECKSIZE = 52;
    private int SUITSIZE = 13;

    /**
     * Makes a deck of cards given the number of decks to make.
     * @param deckNum -- description
     * @return Card array containing all of the cards made.
     */
    public Card [] makeDeck(int deckNum){
        Card myDeck []= new Card [DECKSIZE * deckNum];
        int nNumber = -1;
        int nSuit = -1;

        for(int i = 0; i<deckNum; i++){
            for(int j = 0; j<DECKSIZE; j++){
                nNumber = j%SUITSIZE;
                nSuit = (int) (j/SUITSIZE);
                myDeck[(j+i*52)] = new Card(nSuit, nNumber);
                //Log.w("PAPROGRAM", "Card Made :" + myDeck[(j+i*52)]);
            }
        }
        return myDeck;
    }
}
