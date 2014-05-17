package ModelPkg;

import ModelPkg.WildObjects.FoodSource;
import ModelPkg.WildObjects.WildObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class MapData {

    private static Case[][] map = new Case[30][30];
    private static int hivePosition;
    private static ArrayList<Animal> animalList = new ArrayList<Animal>();
    static ArrayList<Case> casesWithSmellSources = new ArrayList<Case>();

    public static ArrayList<Animal> getAnimalList() {
        return animalList;
    }

    private static ArrayList<String> newsList = new ArrayList<String>();
    private static int[] fourmilierActualRaceStats= new int[]{13,13,13};
    private static int[] fourmilierFixRaceStats1 = new int[]{13,13,13};
    private static int[] fourmilierFixRaceStats2= new int[]{13,13,13};
    private static int[] fourmilierFixRaxceStats3 = new int[]{13,13,13};
    private static int [] costFourmilier = new int[]{100,100,100,100};

    public static void updateSmells() {

        Case selectedCase = null;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                selectedCase = map[i][j];
                selectedCase.clearSortedSmellArrayList();
            }
        }


        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                selectedCase = map[i][j];
                if (selectedCase.getOccupant() != null) {
                    if (!selectedCase.getSortedSmellSourceArrayList().isEmpty()) {
                        selectedCase.removeSmellSource(selectedCase.getOccupant().getSmell());
                    }
                    SmellSource clonedSmell = selectedCase.getOccupant().getSmell().clone();
                    selectedCase.addToSortedSmellSourceArrayList(clonedSmell);
                }

                if (selectedCase.getWildObject().getType()!=WildObject.EMPTY_ID){
                    if (!selectedCase.getSortedSmellSourceArrayList().isEmpty()){
                        selectedCase.removeSmellSource(selectedCase.getWildObject().getSmellSource());
                    }
                    SmellSource clonedSmell=selectedCase.getWildObject().getSmellSource().clone();
                    selectedCase.addToSortedSmellSourceArrayList(clonedSmell);
                }
            }
        }

        casesWithSmellSources = getCasesWithSmellSources();
        if (!casesWithSmellSources.isEmpty()) {
            for (int i = 0; i < casesWithSmellSources.size(); i++) {
                disperseSmellSources(casesWithSmellSources.get(i));
                casesWithSmellSources.get(i).fadeSourceSmells();
            }
        }
    }

    public static int getSmellThreshold(Smell smell, Case selectedCase) {
        int threshold = 0;
        if (!selectedCase.getSortedSmellArrayList().isEmpty()) {
            for (Smell selectedSmell : selectedCase.getSortedSmellArrayList()) {
                if (selectedSmell.getID() == smell.getID()) {
                    threshold = selectedSmell.getIntensity();
                }
            }
        }
        return threshold;
    }

    public static void disperseSmellSources(Case selectedCase) {
        for (int i = 0; i < selectedCase.getSortedSmellSourceArrayList().size(); i++) {
            Smell smell = selectedCase.getSortedSmellSourceArrayList().get(i).clone();
            selectedCase.addToSortedSmellArrayList(smell);
            if (smell.getIntensity() >= 10) {
                disperseSmell(selectedCase, smell);
            }
        }
    }

    public static void disperseSmell(Case sourceCase, Smell smell) {

        Case[][] subsection = getSubsection2(sourceCase.getPosition());
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                Case selectedCase = subsection[i + 1][j + 1];
                if (selectedCase != null) {
                    int smellThreshold = getSmellThreshold(smell, selectedCase);
                    //Les coins. .38 est le multiplicateur .5 par la distance d'une diagonale
                    if (smellThreshold < smell.getIntensity() * .38) {
                        Smell dissipatedSmell = smell.clone();
                        if (i == j || i == -j) {
                            dissipatedSmell.dissipateIntensity(38);
                        } else if (smellThreshold < smell.getIntensity() * .5) {
                            dissipatedSmell.dissipateIntensity(50);
                        }

                        //TODO remove this else all is all right
                        else {
                            System.out.println("ERROR");
                        }

                        if (smellThreshold != 0) {
                            subsection[i + 1][j + 1].eraseInferiorSmellOfSameID(dissipatedSmell);
                        }
                        subsection[i + 1][j + 1].addToSortedSmellArrayList(dissipatedSmell);
                        if (dissipatedSmell.getIntensity()>=10){
                            disperseSmell(subsection[i + 1][j + 1], dissipatedSmell);
                        }
                    }
                }
            }
        }
    }

    private static ArrayList<Case> getCasesWithSmellSources() {
        ArrayList<Case> caseThatHaveASourceSmell = new ArrayList<Case>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                Case selectedCase = map[i][j];
                if (!selectedCase.getSortedSmellSourceArrayList().isEmpty()) {
                    caseThatHaveASourceSmell.add(map[i][j]);
                }
            }
        }
        return caseThatHaveASourceSmell;
    }

    public static void initialize() { //8% arbre 2% roche 3% eau 2% trou
        Random random = new Random();
        int maxLength = 30;
        MapData.hivePosition = maxLength / 2;
        for (int i = 0; i < maxLength; i++) {
            for (int j = 0; j < maxLength; j++) {
                int caseType = random.nextInt(100);
                if (caseType < 90) {
                    map[i][j] = new Case(new Point(i, j), null, new WildObject(WildObject.EMPTY_ID, true));
                }
                else if (caseType < 93) {
                    map[i][j] = new Case(new Point(i, j), null, new FoodSource(WildObject.FOOD_ID, random.nextInt(200)));
                } else if (caseType < 96) {
                    map[i][j] = new Case(new Point(i, j), null, new WildObject(WildObject.TREE_ID, true));
                } else if (caseType < 97) {
                    map[i][j] = new Case(new Point(i, j), null, new WildObject(WildObject.WATER_ID, true));
                } else if (caseType < 98) {
                    map[i][j] = new Case(new Point(i, j), null, new WildObject(WildObject.ROCK_ID, true));
                } else if (caseType < 100) {
                    map[i][j] = new Case(new Point(i, j), null, new WildObject(WildObject.HOLE_ID, true));
                } else {
                    System.out.println("wtf");
                    map[i][j] = new Case(new Point(i, j), null, new WildObject(0, true));
                }
            }
        }

        map[hivePosition][hivePosition] = new Case(new Point(hivePosition, hivePosition), null, new WildObject(5, true));
        map[hivePosition + 1][hivePosition] = new Case(new Point(hivePosition + 1, hivePosition), null, new WildObject(5, true));
        map[hivePosition][hivePosition + 1] = new Case(new Point(hivePosition, hivePosition + 1), null, new WildObject(5, true));
        map[hivePosition + 1][hivePosition + 1] = new Case(new Point(hivePosition + 1, hivePosition + 1), null, new WildObject(5, true));
        newsList.add(0, "Bienvenu à FFS!");
        newsList.add(1, "Niveau 1");
        newsList.add(2, "#YOLO");


    }

    public static Case[][] getSmellableSubsection(Case[][] subsection, int smellThreshold){
        Case[][] smellableSubsection=new Case[3][3];
        for (int i=0; i<subsection.length;i++){
            for (int j=0; j<subsection.length;j++){
                smellableSubsection[i][j]=subsection[i][j].semiClone();
            }
        }
        for (int i=0; i<subsection.length; i++){
            for  (int j=0; j<subsection.length;j++){
                ArrayList<Smell> smellInCase=(ArrayList<Smell>)subsection[i][j].getSortedSmellArrayList().clone();
                for (int k=0; k<smellInCase.size();k++){
                    if (smellInCase.get(k).getIntensity()<smellThreshold){
                        smellInCase.remove(k);
                        k--;
                    }
                }
            }
        }
        return subsection;
    }

    public static Case[][] getSubsection2(Point origin) {
        Case[][] subsection = new Case[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                try {
                    subsection[i][j] = map[origin.x + i - 1][origin.y + j - 1];
                } catch (NullPointerException | ArrayIndexOutOfBoundsException exception) {
                    subsection[i][j] = null;
                }
            }
        }

        return subsection;
    }

    public static Case[][] getSubsection(Point origin) {
        int radius = 3;
        Case[][] returnArray = new Case[(2 * radius) + 1][(2 * radius) + 1];
        int subI = 0;
        int subJ = 0;

        for (int i = origin.x - radius; i <= origin.x + radius; i++) {
            for (int j = origin.y - radius; j <= origin.y + radius; j++) {
                try {
                    returnArray[subI][subJ] = MapData.map[i][j];
                } catch (ArrayIndexOutOfBoundsException e) {
                    returnArray[subI][subJ] = null;
                }
                subJ++;
            }
            subI++;
        }

        return returnArray;
    }

    public static Point getSpawnPoint() {
        Point spawn = null;
        for (int i = -1; i < 3; i++) {
            for (int j = -1; j < 3; j++) {
                Point point = new Point(MapData.hivePosition + i, MapData.hivePosition + j);
                if (MapData.getCase(point).getOccupant() == null && MapData.getCase(point).getWildObject().getType() == 0) {
                    spawn = point;
                }
            }
        }

        return spawn;
    }

    public static Case getCase(Point point) {
        return MapData.map[point.x][point.y];

    }

    public static void addNewsList(String news) {
        newsList.add(news);
    }

    public static ArrayList<String> getNewsList() {
        return newsList;
    }

    public static int[] getFourmilierActualRaceStats1Tab() {
    return fourmilierActualRaceStats;
    }

    public static int getFourmilierActualRaceStats(int position) {
        return fourmilierActualRaceStats[position];
    }

    public static int[] getFourmilierFixRaceStats1() {
        return fourmilierFixRaceStats1;
    }

    public static void setFourmilierFixRaceStats1(int[] fourmilierFixRaceStats1) {
        MapData.fourmilierFixRaceStats1 = fourmilierFixRaceStats1;
    }

    public static int[] getFourmilierFixRaceStats2() {
        return fourmilierFixRaceStats2;
    }

    public static void setFourmilierFixRaceStats2(int[] fourmilierFixRaceStats2) {
        MapData.fourmilierFixRaceStats2 = fourmilierFixRaceStats2;
    }

    public static int[] getFourmilierFixRaxceStats3() {
        return fourmilierFixRaxceStats3;
    }

    public static void setFourmilierFixRaxceStats3(int[] fourmilierFixRaxceStats3) {
        MapData.fourmilierFixRaxceStats3 = fourmilierFixRaxceStats3;
    }

    public static int[] getCostFourmilierTab() {
        return costFourmilier;
    }

    public static void setCostFourmilier(int position, int newCost) {
        MapData.costFourmilier[position] = newCost;
    }
    public static int getCostFourmilier(int position){
        return costFourmilier[position];
    }
    public static void changeLevel(){
        System.out.println("Trying to update");
        Case selectedCase = null;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                selectedCase = map[i][j];
                selectedCase.setTerrain(new WildObject(WildObject.EMPTY_ID, true));
            }
        }
        Random random = new Random();
        int maxLength = 30;
        MapData.hivePosition = maxLength / 2;
        for (int i = 0; i < maxLength; i++) {
            for (int j = 0; j < maxLength; j++) {
                int caseType = random.nextInt(100);
                if (caseType < 90) {
                    map[i][j].setTerrain(new WildObject(WildObject.EMPTY_ID, true));
                }
                //TODO Retiré les commentaires si la création d'une nouvelle map fonctionne
                else if (caseType < 93) {
                    map[i][j].setTerrain(new FoodSource(WildObject.FOOD_ID, 1));
                } else if (caseType < 96) {
                    map[i][j].setTerrain(new WildObject(WildObject.TREE_ID, true));
                } else if (caseType < 97) {
                    map[i][j].setTerrain(new WildObject(WildObject.WATER_ID, true));
                } else if (caseType < 98) {
                    map[i][j].setTerrain(new WildObject(WildObject.ROCK_ID, true));
                } else if (caseType < 100) {
                    map[i][j].setTerrain(new WildObject(WildObject.HOLE_ID, true));
                } else {
                    System.out.println("wtf");
                    map[i][j] = new Case(new Point(i, j), null, new WildObject(0, true));
                }

            }
        }
        System.out.println("création de la base");
        map[hivePosition][hivePosition].setTerrain(new WildObject(WildObject.HIVE_ID, true));
        map[hivePosition+1][hivePosition].setTerrain(new WildObject(WildObject.HIVE_ID, true));
        map[hivePosition][hivePosition+1].setTerrain(new WildObject(WildObject.HIVE_ID, true));
        map[hivePosition+1][hivePosition+1].setTerrain(new WildObject(WildObject.HIVE_ID, true));
        System.out.println("finish updating!");
    }
}