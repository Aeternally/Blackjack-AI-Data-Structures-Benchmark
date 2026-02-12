import java.util.Scanner;

public class HumanPlayer {
    private Hand hand;
    private Scanner scanner;

    public HumanPlayer() {
        this.hand = new Hand();
        this.scanner = new Scanner(System.in);
    }

    public Hand getHand() {
        return hand;
    }

    public boolean takeTurn(Deck deck, HashTable stats) {
        boolean standing = false;
        while (!standing && !hand.bust()) {
            System.out.println("\nYour hand:");
            hand.printHand();
            System.out.print("Hit or stand? ");
            String input = scanner.nextLine().trim().toLowerCase();
            boolean hit = input.equals("hit");
            if (hit) {
                hand.addCard(deck.dealCard());
            } else if (input.equals("stand")) {
                standing = true;
            } else {
                System.out.println("Invalid input. Type 'hit' or 'stand'.");
            }

            String k = String.valueOf(hand.count());
            if (!stats.containsKey(k)) stats.put(k, new Stat());
            ((Stat) stats.get(k)).record(hit, !hand.bust());
        }
        return true;
    }
} 
