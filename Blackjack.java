import java.util.Scanner;

public class Blackjack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BSTree memory = new BSTree();
        AIPlayer ai = new AIPlayer(memory);
        HumanPlayer player = new HumanPlayer();
        Hand aiHand;

        while (true) {
            Deck deck = new Deck();
            player.getHand().clearHand();
            ai.getHand().clearHand();
            aiHand = ai.getHand();

            player.getHand().addCard(deck.dealCard());
            aiHand.addCard(deck.dealCard());
            player.getHand().addCard(deck.dealCard());
            aiHand.addCard(deck.dealCard());

            int hits = 0, stands = 0;
            boolean playerStanding = false, aiStanding = false;

            System.out.println("\nYour hand:");
            player.getHand().printHand();
            System.out.println("Opponent shows: " + aiHand.getFirstCard().getCard());
            while (!player.getHand().bust() && !aiHand.bust()) {
                if (!aiStanding) {
                    aiStanding = ai.takeTurn(deck, player, hits, stands);
                    if (!aiStanding) hits++;
                    else stands++;
                }

                if (!playerStanding) {
                    System.out.print("Hit or stand? ");
                    String input = scanner.nextLine().trim().toLowerCase();
                    if (input.equals("hit")) {
                        player.getHand().addCard(deck.dealCard());
                        System.out.println("\nYour hand:");
                        player.getHand().printHand();
                    } else if (input.equals("stand")) {
                        playerStanding = true;
                    } else {
                        System.out.println("Invalid input. Type 'hit' or 'stand'.");
                    }
                }

                if (aiStanding && playerStanding) break;
            }

            int playerTotal = player.getHand().count();
            int aiTotal = aiHand.count();
            Card visible = player.getHand().getFirstCard();
            int result = compare(aiTotal, playerTotal);
            boolean aiWon = result > 0;
            ai.updateResult(visible, aiTotal, hits, stands, hits > 0, aiWon);

            System.out.println("\nFinal hands:");
            System.out.println("Your total: " + playerTotal);
            System.out.println("AI total: " + aiTotal);
            if (result > 0) System.out.println("AI wins.");
            else if (result < 0) System.out.println("You win!");
            else System.out.println("It's a tie.");

            System.out.print("Play again? (y/n): ");
            if (!scanner.nextLine().trim().toLowerCase().equals("y")) break;
        }

        scanner.close();
    }

    private static int compare(int a, int b) {
        if (a > 21 && b > 21) return 0;
        if (a > 21) return -1;
        if (b > 21) return 1;
        return Integer.compare(a, b);
    }
} 
