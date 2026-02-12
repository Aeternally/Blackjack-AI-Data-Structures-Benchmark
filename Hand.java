public class Hand {
    Card[] cards;
    int size;

    public Hand() {
        cards = new Card[11];
        size = 0;
    }

    public void addCard(Card c) {
        if (size < cards.length) {
            cards[size] = c;
            size++;
        }
    }

    public Card getFirstCard() {
        return size > 0 ? cards[0] : null;
    }


    public boolean blackjack() {
        return size == 2 && count() == 21;
    }
    
    public void clearHand() {
        for (int i = 0; i < size; i++) {
            cards[i] = null;
        }
        size = 0;
    }

    public boolean bust() {
        return count() > 21;
    }

    public Card reveal() {
        return cards[0];
    }


    public void printHand() {
        System.out.println();
        for (int i = 0; i < size; i++) {
            cards[i].printCard();
        }
        System.out.println();
    }

    public int count() {
        int total = 0;
        int aceCount = 0;

        for (int i = 0; i < size; i++) {
            char face = cards[i].face;
            if (face >= '2' && face <= '9') {
                total += face - '0';
            } else if (face == 'T' || face == 'J' || face == 'Q' || face == 'K') {
                total += 10;
            } else if (face == 'A') {
                total += 11;
                aceCount++;
            }
        }

        while (total > 21 && aceCount > 0) {
            total -= 10;
            aceCount--;
        }

        return total;
    }
}
