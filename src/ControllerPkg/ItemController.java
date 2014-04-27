package ControllerPkg;

import ModelPkg.PkgItems.Items;

import java.util.ArrayList;

public class ItemController {




    ArrayList<Integer> objetBougthList = new ArrayList<Integer>();


    public ItemController(){

    }

    public String[] getItemList(){
        String[] itemNames = new String[Items.values().length];
        for(int i= 0; i < Items.values().length; i++){
            itemNames[i] = Items.values()[i].getName();
        }

        return itemNames;
    }
    public Items getBougthItem(int i){
        Items item = Items.values()[i];
        return(item);
    }
    public void setObjetBougthList(int i) {
        this.objetBougthList.add(i);
        System.out.println("adddinnggg");
        System.out.println("New size: "+objetBougthList.size());


    }
    public int getObjetBougth(int i){
        return objetBougthList.get(i);
    }
    public ArrayList<Integer> getObjetBougthList() {
        return objetBougthList;
    }

}
