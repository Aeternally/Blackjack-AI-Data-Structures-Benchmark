public class BSTSimulator {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        int rounds = 100000;
        int aiWins = 0;
        int playerWins = 0;
        int ties = 0;

        BSTree memory = new BSTree();
        AIPlayer ai = new AIPlayer(memory);
        SimulatedPlayer player = new SimulatedPlayer();

        for (int i = 0; i < rounds; i++) {
            Deck deck = new Deck();
            ai.getHand().clearHand();
            player.getHand().clearHand();

            ai.getHand().addCard(deck.dealCard());
            ai.getHand().addCard(deck.dealCard());
            player.getHand().addCard(deck.dealCard());
            player.getHand().addCard(deck.dealCard());

            boolean aiStanding = false;
            boolean playerStanding = false;
            int aiHits = 0;
            int aiStands = 0;

            while (!ai.getHand().bust() && !player.getHand().bust()) {
                if (!aiStanding) {
                    aiStanding = ai.takeTurn(deck, player, aiHits, aiStands);
                    if (!aiStanding) aiHits++;
                    else aiStands++;
                }
                if (!playerStanding) playerStanding = player.takeTurn(deck, new HashTable(1));
                if (aiStanding && playerStanding) break;
            }

            int aiTotal = ai.getHand().count();
            int playerTotal = player.getHand().count();
            Card visible = player.getHand().getFirstCard();

            int result = compare(aiTotal, playerTotal);
            boolean aiWon = result > 0;

            ai.updateResult(visible, aiTotal, aiHits, aiStands, aiHits > 0, aiWon);

            if (result > 0) aiWins++;
            else if (result < 0) playerWins++;
            else ties++;
        }
        long endTime = System.currentTimeMillis();
        long elapsed = endTime - startTime;
        System.out.println("Hit Number: " + ai.getHit());
        System.out.println("Stand Number: " + ai.getStand());
        System.out.println("[BSTree] Rounds: " + rounds);
        System.out.println("AI Wins: " + aiWins);
        System.out.println("Player Wins: " + playerWins);
        System.out.println("Ties: " + ties);
        System.out.println("Elapsed Time: " + elapsed + " ms");
    }

    private static int compare(int a, int b) {
        if (a > 21 && b > 21) return 0;
        if (a > 21) return -1;
        if (b > 21) return 1;
        return Integer.compare(a, b);
    }
} 
