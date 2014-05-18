package ModelPkg;

import javax.swing.*;
import java.util.Hashtable;

public class CreationHashTable {

    private static Hashtable<JButton,String> creationHashtable = new Hashtable<JButton, String>();

    /**
     *Méthode qui initialise le creationHashTable
     * @param jButtons bouton à mettre dans le HashTable
     * @param strings string à mettre dans le HashTable
     */
    public static void initialize(JButton[] jButtons, String[] strings){
        int length = jButtons.length;

        for(int i = 0; i<length; i++){
            CreationHashTable.creationHashtable.put(jButtons[i],strings[i]);
        }
    }

    /**
     * Retourne le String associé au bouton selon le HashTable
     * @param jButton bouton du HashTable(clé)
     * @return valeur associé à la clé
     */
    public static String getAssociatedValue(JButton jButton){
        return CreationHashTable.creationHashtable.get(jButton);
    }

}
