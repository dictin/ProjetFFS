package ControllerPkg;

import ModelPkg.CreationHashTable;

import javax.swing.*;

public class HashtableController {

    //TODO What's this??
    /**
     *
     * @param jButtons
     * @param strings
     */
    public static void InitializeHashtable(JButton[] jButtons, String[] strings){
        CreationHashTable.initialize(jButtons, strings);
    }
}
