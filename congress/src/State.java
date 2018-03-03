

/**
 * State
 */
public class State implements Comparable<State> {
    String name;
    int population;
    int seat = 1;
    double divisor = Math.sqrt(2);

    public State(int population, String name) {
        this.name = name;
        this.population = population;
    }

    public String getName() {
        return this.name;
    }
    public String getSeat() {
        return "" + this.seat;
    }

    public void setSeat() {
        this.seat++;
    }

    public void setDivisor(){
        this.divisor = Math.sqrt(seat*(seat+1));
    }

    public int compareTo(State o) {
        if ((this.population / this.divisor) > (o.population / o.divisor)) {
            return 1;
        } else if ((this.population / this.divisor) < (o.population / o.divisor)) {
            return -1;

        } else {
            return 0;
        }
    }
}
