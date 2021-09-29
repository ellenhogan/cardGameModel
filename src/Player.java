public class Player
{
    private CardPile myCards;
    private String name;


    public Player(String n)
    {
        myCards = new CardPile();
        name = n;
    }

    /**
     * shuffles up the cards in the cardsToTake and adds all of them
     * to myCards.
     * @param cardsToTake - the pile of cards to add to my hand
     */
    public void takeCardsAndShuffle(CardPile cardsToTake)
    {
        cardsToTake.shuffle();
        myCards.takeAllCardsFromPile(cardsToTake);
    }

    /**
     * remove the first card from myCards and return it.
     * @return - the card that was previously on the top of the pile.
     */
    public Card getTopCard()
    {
        Card dealtCard = myCards.dealCard();
        return dealtCard;
    }

    /**
     * remove first 3 cards from myCards, put them into a new CardPile
     * and return that card pile. (If we have three cards... if not,
     * give what you can.)
     * @return - a pile of 3 cards
     */
    public CardPile get3Cards()
    {
        CardPile cp = new CardPile();
        for (int i=0; i<3; i++)
            if (myCards.hasCard())
                cp.addCard(myCards.dealCard());
        return cp;
    }

    /**
     * count how many cards are in myCards
     * @return - the number of cards.
     */
    public int getNumCards()
    {
        return myCards.size();
    }

    public String getName()
    {
        return name;
    }
}
