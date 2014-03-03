package ModelPkg.PkgItems;


public enum Items {

    //Équipement permanent qui peut être installé entre les niveaux pour un boost constant

    GENMOD_HP_0(0, "Coagulation rapide", BoostEffect.LOW_HP_BOOST),
    GENMOD_HP_1(1, "Redondance organique", BoostEffect.MED_HP_BOOST),
    GENMOD_HP_2(2, "R\u00E9g\u00E9n\u00E9r\u00E9scence c\u00E9lulaire", BoostEffect.HIGH_HP_BOOST),
    GENMOD_SPD_3(3,"Syst\u00E8me nerveux autonomique", BoostEffect.LOW_SPD_BOOST),
    GENMOD_SPD_4(4, "Pivot d'articulation ind\u00E9pendant", BoostEffect.MED_SPD_BOOST),
    GENMOD_SPD_5(5, "Mouvement bip\u00E9dal", BoostEffect.HIGH_SPD_BOOST),
    GENMOD_ATK_6(6, "Griffes perforantes", BoostEffect.LOW_ATK_BOOST),
    GENMOD_ATK_7(7, "Enzymes Anticoagullants", BoostEffect.MED_ATK_BOOST),
    GENMOD_ATK_8(8, "Machoire renforc\u00E9e", BoostEffect.HIGH_ATK_BOOST),


    /*

    LOW_ATK_BOOST(2, 1),
    MED_ATK_BOOST(2, 3),
    HIGH_ATK_BOOST(2, 5),

    LOW_SMELLSENS_BOOST(3, 1),
    MED_SMELLSENS_BOOST(3, 2),
    HIGH_SMELLSENS_BOOST(3, 3),

    LOW_SMELLSTR_BOOST(4, 5),
    MED_SMELLSTR_BOOST(4, 10),
    HIGH_SMELLSTR_BOOST(4, 20),
    SUPER_SMELLSTR_BOOST(4, 30),

    LOW_DEF_BOOST(5, 1),
    MED_DEF_BOOST(5, 3),
    HIGH_DEF_BOOST(5, 5),

    LOW_END_BOOST(6, 10),
    MED_END_BOOST(6, 20),
    HIGH_END_BOOST(6, 30),

    LOW_GBQT_BOOST(7, 10),
    MED_GBQT_BOOST(7, 20),
    HIGH_GBQT_BOOST(7, 30),
    SUPER_GBQT_BOOST(7, 50);
     */

    //Items Temporaires

    POTION_HP_26(26, "Potion de vie, petite", BoostEffect.TEMP_LOW_HP_BOOST),
    POTION_HP_27(27, "Potion de vie, moyenne", BoostEffect.TEMP_MED_HP_BOOST),
    POTION_HP_28(28, "Potion de vie, grande", BoostEffect.TEMP_HIGH_HP_BOOST),

    POTION_SPD_29(29, "Potion de vitesse, petite", BoostEffect.TEMP_LOW_SPD_BOOST),
    POTION_SPD_30(30, "Potion de vitesse, moyenne", BoostEffect.TEMP_MED_SPD_BOOST),
    POTION_SPD_31(31, "Potion de vitesse, grande", BoostEffect.TEMP_HIGH_SPD_BOOST),

    POTION_ATK_32(32, "Potion d'attaque, petite", BoostEffect.TEMP_LOW_ATK_BOOST),
    POTION_ATK_33(33, "Potion d'attaque, moyenne", BoostEffect.TEMP_MED_ATK_BOOST),
    POTION_ATK_34(34, "Potion d'attaque, grande", BoostEffect.TEMP_HIGH_ATK_BOOST),

    POTION_SMELLSENS_35(35, "Potion de sensibilit\u00E9, petite", BoostEffect.TEMP_LOW_SMELLSENS_BOOST),
    POTION_SMELLSENS_36(36, "Potion de sensibilit\u00E9, moyenne", BoostEffect.TEMP_MED_SMELLSENS_BOOST),
    POTION_SMELLSENS_37(37, "Potion de sensibilit\u00E9, grande", BoostEffect.TEMP_HIGH_SMELLSENS_BOOST),

    POTION_SMELLSTR_38(35, "Potion de sensibilit\u00E9, petite", BoostEffect.TEMP_LOW_SMELLSENS_BOOST),
    POTION_SMELLSTR_39(36, "Potion de sensibilit\u00E9, moyenne", BoostEffect.TEMP_MED_SMELLSENS_BOOST),
    POTION_SMELLSTR_40(37, "Potion de sensibilit\u00E9, grande", BoostEffect.TEMP_HIGH_SMELLSENS_BOOST),









    private int itemID;
    private String name;
    private ItemEffect effect;

    private Items(int itemID, String name, ItemEffect effect){

    }

    public ItemEffect getEffect() {
        return effect;
    }

    public void firstActivation(){
        ItemEffect effect = this.getEffect();
        if (effect instanceof BoostEffect){
            TempItemInstance instance = ((BoostEffect) effect).getTempInstance();
            //Ajouter l'instance temporaire au arraylist du joueur
        }else if (effect instanceof LotteryEffects){
            effect.activate();
        }
    }
}
