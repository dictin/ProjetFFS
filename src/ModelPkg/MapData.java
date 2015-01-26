package ModelPkg;

import ModelPkg.WildObjects.FoodSource;
import ModelPkg.WildObjects.WildObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class MapData {

    /**
     * Variable contenant la taille en largeur et longueur de la map
     */
    public static final int MAP_SIZE=30;
    /**
     * Tableau contenant les cases de jeu
     */
    private static Case[][] map = new Case[MAP_SIZE][MAP_SIZE];
    /**
     * Position de de la ruche
     */
    private static int hivePosition;
    /**
     * Liste d'animaux actifs
     */
    private static ArrayList<Animal> animalList = new ArrayList<Animal>();
    /**
     * ArrayList de sources d'odeur actives
     */
    static ArrayList<Case> casesWithSmellSources = new ArrayList<Case>();
    /**
     * Identifiant unique affecté à toutes les odeurs
     */
    private static long uniqueIDCounter;

    /**
     *  ArrayList contenant les nouvelles à afficher à l'écran
     */
    private static ArrayList<String> newsList = new ArrayList<String>();
    /**
     * Tableau
     */
    private static int[] fourmilierActualRaceStats= new int[]{13,13,13};
    private static int[] fourmilierFixRaceStats1 = new int[]{13,13,13};
    private static int[] fourmilierFixRaceStats2= new int[]{13,13,13};
    private static int[] fourmilierFixRaxceStats3 = new int[]{13,13,13};
    private static int [] costFourmilier = new int[]{100,100,100,100};

    /**
     * Méthode qui actualise les odeurs dans toutes les cases
     */
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

    /**
     * Méthode qui vérifie s'il y une odeur identique et si oui, renvoit l'intensité de cette odeur
     * @param smell
     * @param selectedCase
     * @return
     */
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

    /**
     * Méthode qui vérifie si l'intensité d'une source est assez grande pour disperser son odeur
     * @param selectedCase case d'où provient la source
     */
    public static void disperseSmellSources(Case selectedCase) {
        for (int i = 0; i < selectedCase.getSortedSmellSourceArrayList().size(); i++) {
            Smell smell = selectedCase.getSortedSmellSourceArrayList().get(i).clone();
            selectedCase.addToSortedSmellArrayList(smell);
            if (smell.getIntensity() >= 10) {
                disperseSmell(selectedCase, smell);
            }
        }
    }

    /**
     * Méthode qui disperse l'odeur d'une source aux cases autours de la source
     * @param sourceCase Source de l'odeur
     * @param smell Odeur de la case
     */
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

    /**
     *Méthode qui retourne une liste avec les cases qui ont des sources d'odeur
     * @return liste de case qui sont des sources d'odeur
     */
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

    /**
     * Méthode qui place aléatoirement des objets sauvages sur la carte et qui place la base au milieu
     */
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
                    map[i][j] = new Case(new Point(i, j), null, new FoodSource(random.nextInt(200)));
                } else if (caseType < 96) {
                    map[i][j] = new Case(new Point(i, j), null, new WildObject(WildObject.TREE_ID, true));
                } else if (caseType < 97) {
                    map[i][j] = new Case(new Point(i, j), null, new WildObject(WildObject.WATER_ID, true));
                } else if (caseType < 98) {
                    map[i][j] = new Case(new Point(i, j), null, new WildObject(WildObject.ROCK_ID, true));
                } else if (caseType < 100) {
                    map[i][j] = new Case(new Point(i, j), null, new WildObject(WildObject.HOLE_ID, true));
                } else {
                    map[i][j] = new Case(new Point(i, j), null, new WildObject(0, true));
                }
            }
        }

        map[hivePosition][hivePosition] = new Case(new Point(hivePosition, hivePosition), null, new WildObject(WildObject.HIVE_ID, true));
        map[hivePosition + 1][hivePosition] = new Case(new Point(hivePosition + 1, hivePosition), null, new WildObject(WildObject.HIVE_ID, true));
        map[hivePosition][hivePosition + 1] = new Case(new Point(hivePosition, hivePosition + 1), null, new WildObject(WildObject.HIVE_ID, true));
        map[hivePosition + 1][hivePosition + 1] = new Case(new Point(hivePosition + 1, hivePosition + 1), null, new WildObject(WildObject.HIVE_ID, true));
        newsList.add(0, "Bienvenu à FFS!");
        newsList.add(1, "Niveau 1");
        newsList.add(2, "#YOLO");


    }

    /**
     *
     * @param subsection
     * @param smellThreshold
     * @return
     */
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

    /**
     *Renvoit un tableau contenant la case d'origine et ses cases adjacentes
     * @param origin
     * @return
     */
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


    /**
     * Méthode qui retourne la position où a été créé le fourmilier
     * @return position du fourmilier
     */
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

    /**
     * Méthode qui retourne une case à une position donnée
     * @param point position de la case demandé
     * @return la case à la position demandé
     */
    public static Case getCase(Point point) {
        return MapData.map[point.x][point.y];

    }

    /**
     * Méthode qui ajoute des information à la liste de nouvelles
     * @param news information à afficher
     */
    public static void addNewsList(String news) {
        newsList.add(news);
    }

    /**
     * Méthode qui retourne la liste de nouvelles
     * @return liste des nouvelles
     */
    public static ArrayList<String> getNewsList() {
        return newsList;
    }

    /**
     * Méthode qui retourne les stats du fourmilier de base
     * @return un tableau avec les statistiques du fourmilier de base
     */
    public static int[] getFourmilierActualRaceStats1Tab() {
    return fourmilierActualRaceStats;
    }

    /**
     * Méthode qui retourne la statistique du fourmilier de base
     * @param position position de la statistique dans le tableau de statistique
     * @return la statistique
     */
    public static int getFourmilierActualRaceStats(int position) {
        return fourmilierActualRaceStats[position];
    }

    /**
     * Méthode qui retourne les statistiques de la race1
     * @return un tableau avec des statistiques
     */
    public static int[] getFourmilierFixRaceStats1() {
        return fourmilierFixRaceStats1;
    }

    /**
     * Méthode qui modifie les statistique de la race1
     * @param fourmilierFixRaceStats1 nouvelles statistiques de la race1
     */
    public static void setFourmilierFixRaceStats1(int[] fourmilierFixRaceStats1) {
        System.out.println(fourmilierFixRaceStats1[0]);
        MapData.fourmilierFixRaceStats1 = fourmilierFixRaceStats1;
    }

    /**
     * Méthode qui retourne les statistiques de la race2
     * @return un tableau avec des statistiques
     */
    public static int[] getFourmilierFixRaceStats2() {
        return fourmilierFixRaceStats2;
    }
    /**
     * Méthode qui modifie les statistique de la race2
     * @param fourmilierFixRaceStats2 nouvelles statistiques de la race2
     */
    public static void setFourmilierFixRaceStats2(int[] fourmilierFixRaceStats2) {
        MapData.fourmilierFixRaceStats2 = fourmilierFixRaceStats2;
    }
    /**
     * Méthode qui retourne les statistiques de la race3
     * @return un tableau avec des statistiques
     */
    public static int[] getFourmilierFixRaxceStats3() {
        return fourmilierFixRaxceStats3;
    }
    /**
     * Méthode qui modifie les statistique de la race3
     * @param fourmilierFixRaceStats3 nouvelles statistiques de la race3
     */
    public static void setFourmilierFixRaxceStats3(int[] fourmilierFixRaceStats3) {
        MapData.fourmilierFixRaxceStats3 = fourmilierFixRaxceStats3;
    }

    /**
     * Méthode qui modifie et qui retourne un nouvel identification
     * @return indentification
     */
    public static long getUniqueID(){
        uniqueIDCounter++;
        return uniqueIDCounter;
    }

    /**
     * Méthode qui modifie le coût d'une race de fourmilier
     * @param position position de la race à modifier le prix dans le tableau des prix
     * @param newCost nouveau prix des fourmiliers de cette race
     */
    public static void setCostFourmilier(int position, int newCost) {
        MapData.costFourmilier[position] = newCost;
    }

    /**
     * Méthode qui retourne le coût de la race de fourmilier à la position dans le tableau de prix
     * @param position position de la race à modifier le prix dans le tableau des prix
     * @return
     */
    public static int getCostFourmilier(int position){
        return costFourmilier[position];
    }

    /**
     * Méthode qui enlève tous les objets sauvages de la carte puis qui remplie la map avec des nouveaux objets sauvages à
     * des endroits différents.
     */
    public static void changeLevel(){
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
                int foodQuantity = random.nextInt(200);
                if (caseType < 90) {
                    map[i][j].setTerrain(new WildObject(WildObject.EMPTY_ID, true));
                }
                else if (caseType < 93) {
                    map[i][j].setTerrain(new FoodSource(foodQuantity));
                } else if (caseType < 96) {
                    map[i][j].setTerrain(new WildObject(WildObject.TREE_ID, true));
                } else if (caseType < 97) {
                    map[i][j].setTerrain(new WildObject(WildObject.WATER_ID, true));
                } else if (caseType < 98) {
                    map[i][j].setTerrain(new WildObject(WildObject.ROCK_ID, true));
                } else if (caseType < 100) {
                    map[i][j].setTerrain(new WildObject(WildObject.HOLE_ID, true));
                } else {
                    map[i][j] = new Case(new Point(i, j), null, new WildObject(0, true));
                }

            }
        }
        map[hivePosition][hivePosition].setTerrain(new WildObject(WildObject.HIVE_ID, true));
        map[hivePosition+1][hivePosition].setTerrain(new WildObject(WildObject.HIVE_ID, true));
        map[hivePosition][hivePosition+1].setTerrain(new WildObject(WildObject.HIVE_ID, true));
        map[hivePosition+1][hivePosition+1].setTerrain(new WildObject(WildObject.HIVE_ID, true));
    }

    /**
     * Retourne le ArrayList contenant les animaux actifsé
     * @return ArrayList
     */
    public static ArrayList<Animal> getAnimalList() {
        return animalList;
    }
}