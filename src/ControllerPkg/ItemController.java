package ControllerPkg;

import ModelPkg.PkgItems.Items;

public class ItemController {

    public ItemController(){

    }

    public String[] getItemList(){
        String[] itemNames = new String[Items.values().length];
        for(int i= 0; i < Items.values().length; i++){
            itemNames[i] = Items.values()[i].getName();
        }

        return itemNames;
    }
}
