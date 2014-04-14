package ModelPkg;

import javax.swing.*;
import java.util.Hashtable;

public class CreationHashTable {

    private static Hashtable<JButton,String> creationHashtable = new Hashtable<JButton, String>();

    public static void initialize(JButton[] jButtons, String[] strings){
        int length = jButtons.length;

        for(int i = 0; i<length; i++){
            CreationHashTable.creationHashtable.put(jButtons[i],strings[i]);
        }
    }

    public static String getAssociatedValue(JButton jButton){
        return CreationHashTable.creationHashtable.get(jButton);
    }

}
