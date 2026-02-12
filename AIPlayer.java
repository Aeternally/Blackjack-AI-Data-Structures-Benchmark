public class AIPlayer {
    private Hand hand;
    private HashTable hashMemory;
    private BSTree bstMemory;
    private boolean useBST;
    private int stand;
    private int hit;

    public int getStand() {
        return this.stand;
    }

    public int getHit() {
        return this.hit;
    }

    public AIPlayer(HashTable memory) {
        this.hand = new Hand();
        this.hashMemory = memory;
        this.useBST = false;
    }

    public AIPlayer(BSTree memory) {
        this.hand = new Hand();
        this.bstMemory = memory;
        this.useBST = true;
    }

    public Hand getHand() {
        return hand;
    }

    private String buildStateKey(Card visiblePlayerCard, int aiTotal, int hits, int stands) {
        return aiTotal + "|" + visiblePlayerCard.face + "|" + hits + "|" + stands;
    }

    public boolean takeTurn(Deck deck, Object playerObj, int hits, int stands) {
        Hand otherHand;
        if (playerObj instanceof HumanPlayer) {
            otherHand = ((HumanPlayer) playerObj).getHand();
        } else if (playerObj instanceof SimulatedPlayer) {
            otherHand = ((SimulatedPlayer) playerObj).getHand();
        } else {
            throw new IllegalArgumentException("Invalid player type");
        }

        int aiTotal = hand.count();
        Card visibleCard = otherHand.getFirstCard();
        String key = buildStateKey(visibleCard, aiTotal, hits, stands);

        Stat stat;
        if (useBST) {
            stat = bstMemory.containsKey(key) ? bstMemory.get(key) : new Stat();
        } else {
            stat = hashMemory.containsKey(key) ? (Stat) hashMemory.get(key) : new Stat();
        }

        if(aiTotal < visibleCard.getValue()) {
            hand.addCard(deck.dealCard());
            this.hit++;
            return false;
        }
        double hitRate = stat.hitWinRate();
        double standRate = stat.standWinRate();

        double bustRisk = Math.max(0, (aiTotal - 15) / 6.0);

        boolean hit = hitRate > standRate + bustRisk;
        //Comment this out if you are playing a real game or simulating. This is to see the AI thought process when playing against them.
        //System.out.printf("AI [%s] HWR: %.2f  SWR: %.2f  Risk: %.2f => %s\n", key, hitRate, standRate, bustRisk, hit ? "HIT" : "STAND");

        if (hit) {
            hand.addCard(deck.dealCard());
            this.hit++;
            return false;
        } else {
            this.stand++;
            return true;
        }
    }

    public void updateResult(Card visibleCard, int finalTotal, int hits, int stands, boolean didHit, boolean didWin) {
        String key = buildStateKey(visibleCard, finalTotal, hits, stands);

        if (useBST) {
            if (!bstMemory.containsKey(key)) bstMemory.put(key, new Stat());
            bstMemory.get(key).record(didHit, didWin);
        } else {
            if (!hashMemory.containsKey(key)) hashMemory.put(key, new Stat());
            ((Stat) hashMemory.get(key)).record(didHit, didWin);
        }
    }
} 
