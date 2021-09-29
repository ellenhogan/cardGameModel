public class Referee
{
    private int whoseTurn;
    private CardPile deck, pool;
    private Player player1, player2;

    public Referee(Player p1, Player p2)
    {
        whoseTurn = 1;
        deck = CardPile.makeDeck(CardPile.ACE_HIGH);
        System.out.println("Made deck with "+deck.size()+" cards.");
        player1 = p1;
        player2 = p2;
        pool = new CardPile();
    }

    /**
     * Gives 26 cards to each player.
     */
    public void dealCards()
    {
        CardPile cp1 = new CardPile();
        CardPile cp2 = new CardPile();
        while (deck.hasCard())
        {
            cp1.addCard(deck.dealCard());
            cp2.addCard(deck.dealCard());
        }
        player1.takeCardsAndShuffle(cp1);
        player2.takeCardsAndShuffle(cp2);
    }

    /**
     * Create a new deck of 52 cards;
     * remove all the cards from the players; empty the pool
     */
    public void resetGame()
    {
        deck = CardPile.makeDeck(CardPile.ACE_HIGH);
        while (player1.getNumCards() > 0)
            player1.getTopCard();
        while (player2.getNumCards() > 0)
            player2.getTopCard();
        pool = new CardPile();
    }

    /**
     * runs the game loop, asking players for cards, comparing them,
     * and giving cards back to the victors until the game is over.
     */
    public void playGame()
    {
        Card cardFrom1, cardFrom2;
        while (true)
        {
            System.out.println("------------------------------------------------------------");
            System.out.println(player1.getName()+": "+player1.getNumCards()+" card(s)\t"+
                    player2.getName()+": "+player2.getNumCards()+" card(s)");
            cardFrom1 = player1.getTopCard();
            cardFrom2 = player2.getTopCard();
            pool.addCard(cardFrom1);
            pool.addCard(cardFrom2);

            System.out.println(player1.getName()+" throws: "+cardFrom1+".\t"+player2.getName()+" throws: "+cardFrom2+".");

            while (cardFrom1.getValue() == cardFrom2.getValue()) // note: if they weren't equal, this will be skipped.
            {
                System.out.println("It's a tie! On to battle.....");
                CardPile warPile1 = player1.get3Cards();
                System.out.println("\t"+player1.getName()+" committed "+warPile1.size()+" card(s) to the battle.");
                CardPile warPile2 = player2.get3Cards();
                System.out.println("\t"+player2.getName()+" committed "+warPile2.size()+" card(s) to the battle.");

                pool.takeAllCardsFromPile(warPile1);
                pool.takeAllCardsFromPile(warPile2);

                Card c1 = player1.getTopCard();
                if (c1 == null) // there wasn't a 4th card to add.
                {
                    c1 = warPile1.dealCard(); // grab a card from the previous 3-ish. No need to add this to pool; we already did.
                    if (c1 == null)
                        c1 = cardFrom1;
                }
                else
                {
                    pool.addCard(c1);
                }

                Card c2 = player2.getTopCard();
                if (c2 == null) // there wasn't a 4th card to add.
                {
                    c2 = warPile2.dealCard(); // grab a card from the previous 3-ish. No need to add this to pool; we already did.
                    if (c2 == null) // if the three cards were empty, then CardFrom2 was player2's last card.
                        c2 = cardFrom2;  // also already added to the pool....
                }
                else
                {
                    pool.addCard(c2);
                }
                cardFrom1 = c1;
                cardFrom2 = c2;

                System.out.println("\t"+player1.getName() + " fights with: "+cardFrom1+"\t"
                        +player2.getName()+" fights with "+cardFrom2);

            }

            if (cardFrom1.getValue() > cardFrom2.getValue())
            {
                System.out.println("Cards go to "+player1.getName()+".");
                player1.takeCardsAndShuffle(pool);
            }
            else
            {
                System.out.println("Cards go to "+player2.getName()+".");
                player2.takeCardsAndShuffle(pool);
            }

            if (player1.getNumCards() == 0)
            {
                System.out.println(player2.getName() + " wins!");
                break;
            }
            if (player2.getNumCards() == 0)
            {
                System.out.println(player1.getName() + " wins!");
                break;
            }

        }
        System.out.println("Game over.");
    }
}

