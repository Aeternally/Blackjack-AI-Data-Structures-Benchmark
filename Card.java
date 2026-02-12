public class Card{
    char face;
    char suit;

    public Card(char face, char suit){
        this.face = face;
        this.suit = suit;
    }

    public char getFace(){
        return face;
    }

    public char getSuit(){
        return suit;
    }

    private String suitSymbol(char suit) {
        switch (suit) {
            case 'H': return "♥";
            case 'D': return "♦";
            case 'C': return "♣";
            case 'S': return "♠";
            default:  return "?";
        }
    }

    public String getCard() {
        return this.face + suitSymbol(this.suit) + " ";
    }

    public int getValue() {
        int total = 0;
        int aceCount = 0;
        if (face >= '2' && face <= '9') {
                total += face - '0';
            } else if (face == 'T' || face == 'J' || face == 'Q' || face == 'K') {
                total += 10;
            } else if (face == 'A') {
                total += 11;
                aceCount++;
            }

        while (total > 21 && aceCount > 0) {
            total -= 10;
            aceCount--;
        }

        return total;
    }

    public void printCard() {
        System.out.print(this.face + suitSymbol(this.suit) + " ");
        System.out.println();
    }
}