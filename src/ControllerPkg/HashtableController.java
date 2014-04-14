package ControllerPkg;

import ModelPkg.CreationHashTable;

import javax.swing.*;

public class HashtableController {

    public static void InitializeHashtable(JButton[] jButtons, String[] strings){
        CreationHashTable.initialize(jButtons, strings);
    }
}
