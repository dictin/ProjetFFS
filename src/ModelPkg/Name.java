package ModelPkg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Chloé on 14-03-03.
 */

public class Name {

    /**
     * Liste des noms possibles
     */
    private static ArrayList<String> nameList = new ArrayList<String>();
    /**
     * Liste des générations associés aux noms
     */
    private static int[] genList = new int[25];

    /**
     * Méthode qui lit le fichier remplis de nom et qui les ajoutent à la list de nom
     */
    public static void initialize(){

        String line="";

        BufferedReader fileEnter = null;
        try {
            fileEnter = new BufferedReader(new FileReader("Names.txt"));
            line = fileEnter.readLine();
            String name;
            while (line != null){
                name = line;
                nameList.add(name);
                genList[nameList.indexOf(name)] = 1;
                line = fileEnter.readLine();
            }
            fileEnter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode qui retourne le nom à l'index demandé
     * @param index position du nom demandé
     * @return le nom à l'index demandé
     */
    public static String getName (int index){

        String name = Name.nameList.get(index);
        return name;
    }

    /**
     * Méthode qui retourne la génération du nom à l'index demandé
     * @param index position de la génération demandé
     * @return la génération à l'index demandé
     */
    public static int getGen (int index){

        int generation = Name.genList[index];
        Name.genList[index] = generation +1;
        return generation;
    }


}