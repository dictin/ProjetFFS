package ModelPkg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Chloé on 14-03-03.
 */

public class Name {

    private static ArrayList<String> nameList = new ArrayList<String>();
    private static int[] genList = new int[25];

    public static void initialize(){

        String line="";

        BufferedReader fileEnter = null;
        try {
            fileEnter = new BufferedReader(new FileReader("Names.txt"));
            line = fileEnter.readLine();
            String name;
            while (line != null){
                name = line;
                System.out.println(line);
                nameList.add(name);
                genList[nameList.indexOf(name)] = 1;
                line = fileEnter.readLine();
            }
            fileEnter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getName (int index){

        String name = Name.nameList.get(index);
        return name;
    }

    public static int getGen (int index){

        int generation = Name.genList[index];
        Name.genList[index] = generation +1;
        return generation;
    }


}