package ModelPkg;

import ModelPkg.WildObjects.WildObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class MapData {

    private static Case[][] map = new Case[30][30];
    private static int hivePosition;
    private static ArrayList<Animal> animalList=new ArrayList<Animal>();
    static ArrayList<Case> casesWithSmellSources=new ArrayList<Case>();
    public static ArrayList<Animal> getAnimalList() {
        return animalList;
    }
    private static ArrayList<String> newsList = new ArrayList<String>();



    public static void updateSmells(){
        Case selectedCase;
        //Wipes temporary smells
        for (int i=0;i<map.length;i++){
            for (int j=0; j<map[i].length;j++){
                selectedCase=map[i][j];
                selectedCase.getSortedSmellArrayList().clear();
            }
        }
        //Adds smell sources
        for (int i=0;i<map.length;i++){
            for (int j=0; j<map[i].length;j++){
                selectedCase=map[i][j];
                //TODO overwrite same smell in case
                if (selectedCase.getOccupant()!=null&&!selectedCase.getSmellSourceArrayList().isEmpty()){
                    selectedCase.removeSmellsource(selectedCase.getOccupant().getSmell());
                    selectedCase.getSmellSourceArrayList().add(selectedCase.getOccupant().getSmell());

                    System.out.print("Occupant non null, smell:");
                    System.out.println(selectedCase.getOccupant().getSmell().getType());
                }
                if(selectedCase.getWildObject()!=null&&!selectedCase.getSmellSourceArrayList().isEmpty()){
                    selectedCase.removeSmellsource(selectedCase.getWildObject().getSmellSource());
                    selectedCase.getSmellSourceArrayList().add(selectedCase.getWildObject().getSmellSource());

                    System.out.print("Wild object non null, smell:");
                    System.out.println(selectedCase.getWildObject().getSmellSource().getType());
                }
            }
        }
        casesWithSmellSources=getCasesWithSmellSources();
        if (casesWithSmellSources!=null){
            for (int i=0;i<casesWithSmellSources.size();i++){
                disperseSmellSources(casesWithSmellSources.get(i));
                casesWithSmellSources.get(i).fadeSourceSmells();
            }
        }


        //TODO remove this test when done

        for (int i=0; i<map.length;i++){
            for (int j=0;j<map[i].length;j++){
                ArrayList<Smell> testList=map[i][j].getSortedSmellArrayList();
                if (testList.size()!=0){
                    if (testList.size()!=1){
                        System.out.println("Ca sent bon et c'est bien.");
                    }
                //System.out.print(testList.size() + " ");
//                System.out.print(testList.get(0).getIntensity() + " ");
                }
            }
            //System.out.println();
        }

    }

    public static void disperseSmellSources(Case selectedCase){
        for (int i=0;i<selectedCase.getSmellSourceArrayList().size();i++){
            Smell smell=selectedCase.getSmellSourceArrayList().get(i);
            selectedCase.getSortedSmellArrayList().add(smell);
            disperseSmell(selectedCase, smell);
        }
    }

    public static int getSmellThreshold(Smell smell, Case selectedCase){
        int threshold=0;
        if (!selectedCase.getSortedSmellArrayList().isEmpty()){
            for (Smell selectedSmell:selectedCase.getSortedSmellArrayList()){
                if (selectedSmell.getID()==smell.getID()){
                    threshold=selectedSmell.getIntensity();
                }
            }
        }
        return threshold;
    }

    public static void disperseSmell(Case sourceCase, Smell smell){
        if (smell.getIntensity()>=10){
        Case[][] subsection=getSubsection2(sourceCase.getPosition());
        for (int i=-1; i<2;i++){
            for (int j=-1; j<2;j++){
                Case selectedCase=subsection[i+1][j+1];
                if (selectedCase!=null){
                int smellThreshold=getSmellThreshold(smell, selectedCase);
                    //Les coins. .38 est le multiplicateur .5 par la distance d'une diagonale
                if (smellThreshold<smell.getIntensity()*.38){
                    Smell dissipatedSmell=smell.clone();
                    if(i==j||i==-j){
                        dissipatedSmell.dissipateIntensity(38);
                    }
                    else if(smellThreshold<smell.getIntensity()*.5){
                        dissipatedSmell.dissipateIntensity(50);
                }
                    else{
                        System.out.println("That was not supposed to happen.");
                    }
                    if (smellThreshold!=0){
                    subsection[i+1][j+1].eraseInferiorSmellOfSameID(dissipatedSmell);
                    }
                    subsection[i+1][j+1].getSortedSmellArrayList().add(dissipatedSmell);
                    disperseSmell(subsection[i+1][j+1], dissipatedSmell);
            }
        }
            }
        }
    }
    }

    private static ArrayList<Case> getCasesWithSmellSources(){
        ArrayList<Case> caseThatHaveASourceSmell=new ArrayList<Case>();
        for (int i=0; i<30;i++){
            for (int j=0;j<30;j++){
                Case selectedCase=map[i][j];
                if (!selectedCase.getSmellSourceArrayList().isEmpty()){
                    caseThatHaveASourceSmell.add(map[i][j]);
                }
            }
        }
        if (!caseThatHaveASourceSmell.isEmpty()){
        return caseThatHaveASourceSmell;
        }
        else{
            return null;
        }
    }

    private void initialize2(int nbOfTrees, int nbOfRocks, int nbOfWater, int nbOfHoles){
        Random casePicker=new Random(30*30);
        int nbOfSpecialCases=nbOfHoles+nbOfRocks+nbOfTrees+nbOfWater;
        if (nbOfSpecialCases>30*30){
            System.out.println("Erreur: Trop de cases speciales, loupe infinie creee.");
        }
        ArrayList<Integer> specialCases=new ArrayList<Integer>();
        ArrayList<Integer> alreadyPicked=new ArrayList<Integer>();
        for (int i=0; i<nbOfSpecialCases; i++){
            boolean caseAlreadyFull=false;
            do {
                caseAlreadyFull=false;
                int caseID=casePicker.nextInt();
                if (alreadyPicked.size()!=0){
                for (int j=0;j<alreadyPicked.size();j++){
                    if (caseID==alreadyPicked.get(j)){
                        caseAlreadyFull=true;
                    }
                }
                    if (!caseAlreadyFull){
                        specialCases.add(caseID);
                    }
                }
            }while (caseAlreadyFull);
        }
        //TODO utiliser les ID du arrayList pour remplir les cases appropriées (posiX=ID%30, posiY=floor(ID/30))
    }

    public static void initialize() { //8% arbre 2% roche 3% eau 2% trou
        Random random = new Random();
        int maxLength = 30;
        MapData.hivePosition = maxLength/2;
        for (int i = 0; i < maxLength; i++){
            for(int j = 0; j < maxLength; j++){
                int caseType = random.nextInt(100);
                if (caseType < 93){
                    map[i][j] = new Case(new Point(i,j), null, new WildObject(0, true));
                }else if (caseType < 96){
                    map[i][j] = new Case(new Point(i,j), null, new WildObject(2, true));
                }else if (caseType < 97){
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
        newsList.add(0, "Bienvenu à FFS!");
        newsList.add(1, "Niveau 1");
        newsList.add(2, "#YOLO");


    }

    public static Case[][] getSubsection2(Point origin){
        Case[][] subsection = new Case[3][3];
        for (int i =0; i<3; i++){
            for (int j=0; j<3; j++){
                try{
                subsection[i][j]=map[origin.x+i-1][origin.y+j-1];
                }catch(NullPointerException | ArrayIndexOutOfBoundsException exception){

                    subsection[i][j]=null;
                }
            }
        }
        return subsection;
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

    public static Point getSpawnPoint(){
        Point spawn = null;
        for(int i = -1; i < 3; i++){
            for(int j = -1; j < 3; j++){
                Point point = new Point(MapData.hivePosition+i,MapData.hivePosition+j);
                if(MapData.getCase(point).getOccupant() == null && MapData.getCase(point).getWildObject().getType() == 0){
                    spawn = point;
                }
            }
        }

        return spawn;
    }

    public static Case getCase(Point point){
        //System.out.println(point.x+";"+point.y);
        return MapData.map[point.x][point.y];

    }
    public  static void addNewsList(String news) {
        newsList.add(news);
    }
    public static ArrayList<String> getNewsList() {
        return newsList;
    }

    public static void setAnimalList(ArrayList<Animal> animalList) {
        MapData.animalList = animalList;
    }
}
