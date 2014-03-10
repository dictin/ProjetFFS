package ModelPkg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by Chloé on 14-03-03.
 */
public class Name {
    private ArrayList<String> nameList = new ArrayList<String>();
    private ArrayList<Integer> genList = new ArrayList<Integer>();

    public Name(){

        //TODO Read file.
        String ligne="";

        BufferedReader fileEnter = null;
            try {
                fileEnter = new BufferedReader(new FileReader("Names.txt"));
                while (ligne != null){
                    int valeur = Integer.parseInt(ligne);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }


}
