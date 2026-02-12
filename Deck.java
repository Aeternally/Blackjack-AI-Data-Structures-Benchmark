public class Deck {
    Card[] cards;
    int size;
    Card topCard;

    public Deck() {
        char[] faces = {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};
        char[] suits = {'C', 'D', 'H', 'S'};
        cards = new Card[52];
        int index = 0;

        for (char suit : suits) {
            for (char face : faces) {
                cards[index++] = new Card(face, suit);
            }
        }

        size = 52;
        shuffle();
        topCard = cards[0];
    }

    public void shuffle() {
        for (int i = cards.length - 1; i > 0; i--) {
            int j = (int)(Math.random() * (i + 1));
            Card temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
        topCard = size > 0 ? cards[0] : null;
    }

    public Card dealCard() {
        if (size == 0) {
            return null;
        }
        Card dealt = cards[52 - size];
        size--;
        topCard = size > 0 ? cards[52 - size] : null;
        return dealt;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int cardsRemaining() {
        return size;
    }
}