import java.util.Scanner;

public class CardGameRunner2021
{
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Player 1, what is your name? ");
        String name1 = keyboard.nextLine();
        Player p1 = new Player(name1);
        System.out.print("Player 2, what is your name? ");
        String name2 = keyboard.nextLine();
        Player p2 = new Player(name2);
        Referee ref = new Referee(p1,p2);

        ref.dealCards();
        ref.playGame();

//        System.out.println("--------- temp stuff");
//        Player testP = new Player("Testy");
//        System.out.println(testP.getName());
//        System.out.println(testP.getNumCards());
//        CardPile testPile = new CardPile();
//        testPile.addCard(new Card());
//        testP.takeCardsAndShuffle(testPile);
//        System.out.println("Now I have "+testP.getNumCards()+" cards.");
//        System.out.println("getting card from "+testP.getName());
//        System.out.println(testP.getTopCard());

    }

}
