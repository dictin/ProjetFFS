package ModelPkg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Chloé on 14-03-03.
 */
public  class Name {

    private static ArrayList<String> nameList = new ArrayList<String>();
    private static ArrayList<Integer> genList = new ArrayList<Integer>();

    public Name(){
        BufferedReader fileEnter = null;
            try {
                fileEnter = new BufferedReader(new FileReader("Names.txt"));
                String line = fileEnter.readLine();
                String name;
                while (line != null){
                    name = line;
                    nameList.add(name);
                    genList.add(1);
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
            int generation = Name.genList.get(index);
            Name.genList.remove(index);
            Name.genList.add(index, generation++);
            return generation;
        }


        }



