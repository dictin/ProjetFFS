package ModelPkg.PkgEvents;

/**
 * Created by Chloé on 14-03-24.
 */
public class Time{

    // À chaque x tours, on change de jour
    int day = 1;
    static int turn = 0;

    public Time(){

    }
    public int getDay() {
        return day;
    }

    public static int getTurn() {
        return turn;
    }

    public void addDay() {
        this.day = day++;
    }

    public void addTurn() {
        this.turn = turn++;
    }
}

