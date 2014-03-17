package ModelPkg;

import java.awt.*;
import java.util.Random;

public class MapData {

    private static Case[][] map = new Case[30][30];


    public MapData(){
        this.initialize();
    }

    private void initialize() { //8% arbre 2% roche 3% eau 2% trou
        Random random = new Random(100);
        int maxLength = 30;
        int hivePosition = maxLength/2;
        for (int i = 0; i < maxLength; i++){
            for(int j = 0; j < maxLength; j++){
                int caseType = random.nextInt();
                if (caseType < 85){
                    map[i][j] = new Case(new Point(i,j), null, new WildObject(0, true));
                }else if (caseType < 93){
                    map[i][j] = new Case(new Point(i,j), null, new WildObject(2, true));
                }else if (caseType < 96){
                    map[i][j] = new Case(new Point(i,j), null, new WildObject(3, true));
                }else if (caseType < 98){
                    map[i][j] = new Case(new Point(i,j), null, new WildObject(1, true));
                }else if (caseType < 100){
                    map[i][j] = new Case(new Point(i,j), null, new WildObject(4, true));
                }else{
                    map[i][j] = new Case(new Point(i,j), null, new WildObject(0, true));
                }

            }
        }

        map[hivePosition][hivePosition] = new Case(new Point(hivePosition,hivePosition), null, new WildObject(5, true));
        map[hivePosition+1][hivePosition] = new Case(new Point(hivePosition+1,hivePosition), null, new WildObject(5, true));
        map[hivePosition][hivePosition+1] = new Case(new Point(hivePosition,hivePosition+1), null, new WildObject(5, true));
        map[hivePosition+1][hivePosition+1] = new Case(new Point(hivePosition+1,hivePosition+1), null, new WildObject(5, true));


    }

    public static Case[][] getSubsection(Point origin){
        int radius = 3;
        Case[][] returnArray = new Case[(2*radius)+1][(2*radius)+1];
        int subI = 0;
        int subJ = 0;

        for (int i = origin.x-radius; i <= origin.x+radius; i++){
            for (int j = origin.y-radius; j <= origin.y+radius; j++){
                try{
                returnArray[subI][subJ] = MapData.map[i][j];
                } catch (ArrayIndexOutOfBoundsException e){
                    returnArray[subI][subJ] = null;
                }
                subJ++;
            }
            subI++;
        }

        return returnArray;
    }

    public static Case getCase(Point point){
        return MapData.map[point.x][point.y];

    }

}
