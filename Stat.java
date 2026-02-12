public class Stat {
    int total;
    int hitWins;
    int standWins;

    public Stat() {
        this.total = 0;
        this.hitWins = 0;
        this.standWins = 0;
    }

    public void record(boolean hit, boolean win) {
        total++;
        if (hit && win) hitWins++;
        if (!hit && win) standWins++;
    }

    public double hitWinRate() {
        return total == 0 ? 0.55 : (double) hitWins / total;
    }

    public double standWinRate() {
        return total == 0 ? 0.45 : (double) standWins / total;
    }
} 
