package ModelPkg;

/**
 * Created by Chloé on 14-03-17.
 */
public class Time{

    // À chaque x tours, on change de jour
    int day = 1;
    int turn = 0;
    
    public Time(){
        
    }
    public int getDay() {
        return day;
    }

    public int getTurn() {
        return turn;
    }   
    
    public void addDay() {
        this.day = day++;
    }

    public void addTurn() {
        this.turn = turn++;
    }
}
