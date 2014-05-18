package ControllerPkg;

import ModelPkg.CreationHashTable;

import javax.swing.*;

public class HashtableController {


    /**
     * Contrôleur du Hashtable de la création des fourmiliers
     * @param jButtons le bouton à cliquer pour créer une certaine créature
     * @param strings le nom de la créature à faire apparaître
     */
    public static void InitializeHashtable(JButton[] jButtons, String[] strings){
        CreationHashTable.initialize(jButtons, strings);
    }
}
