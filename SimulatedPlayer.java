public class SimulatedPlayer {
    private Hand hand;

    public SimulatedPlayer() {
        this.hand = new Hand();
    }

    public Hand getHand() {
        return hand;
    }

    public boolean takeTurn(Deck deck, HashTable stats) {
        boolean standing = false;
        while (!standing && !hand.bust()) {
            int total = hand.count();
            boolean hit = total < 17;
            if (hit) {
                hand.addCard(deck.dealCard());
            } else {
                standing = true;
            }
        }
        return true;
    }
} 
