package ModelPkg.PkgItems;


public enum Items {

    //Équipement permanent qui peut être installé entre les niveaux pour un boost constant
    //TODO Changer les prix
    GENMOD_HP_0(0, "Coagulation rapide", BoostEffect.LOW_HP_BOOST,20),
    GENMOD_HP_1(1, "Redondance organique", BoostEffect.MED_HP_BOOST,20),
    GENMOD_HP_2(2, "R\u00E9g\u00E9n\u00E9r\u00E9scence c\u00E9lulaire", BoostEffect.HIGH_HP_BOOST,20),

    GENMOD_SPD_3(3,"Syst\u00E8me nerveux autonomique", BoostEffect.LOW_SPD_BOOST,20),
    GENMOD_SPD_4(4, "Pivot d'articulation ind\u00E9pendant", BoostEffect.MED_SPD_BOOST,20),
    GENMOD_SPD_5(5, "Mouvement bip\u00E9dal", BoostEffect.HIGH_SPD_BOOST,20),

    GENMOD_ATK_6(6, "Griffes perforantes", BoostEffect.LOW_ATK_BOOST,20),
    GENMOD_ATK_7(7, "Enzymes Anticoagullants", BoostEffect.MED_ATK_BOOST,20),
    GENMOD_ATK_8(8, "Machoire renforc\u00E9e", BoostEffect.HIGH_ATK_BOOST,20),

    GENMOD_SMELLSENS_9(9, "Gel\u00E9 pour nez am\u00E9lior\u00E9", BoostEffect.LOW_SMELLSENS_BOOST,20),
    GENMOD_SMELLSENS_10(10, "Narines r\u00E9tractives", BoostEffect.MED_SMELLSENS_BOOST,20),
    GENMOD_SMELLSENS_11(11, "Double piff", BoostEffect.HIGH_SMELLSENS_BOOST,20),

    GENMOD_SMELLSTR_12(12, "Bouche qui pue", BoostEffect.LOW_SMELLSTR_BOOST,20),
    GENMOD_SMELLSTR_13(13, "Fourrure avec odeur de pourritures", BoostEffect.MED_SMELLSTR_BOOST,20),
    GENMOD_SMELLSTR_14(14, "D\u00E9odorisant à senteur de melon d'eau", BoostEffect.HIGH_SMELLSTR_BOOST,20),
    GENMOD_SMELLSTR_15(15, "Parfum Chanel no5", BoostEffect.SUPER_SMELLSTR_BOOST,20),

    GENMOD_DEF_16(16, "Tunique moisie", BoostEffect.LOW_DEF_BOOST,20),
    GENMOD_DEF_17(17, "Plastron ancestrale des chamans du d\u00E9sert", BoostEffect.MED_DEF_BOOST,20),
    GENMOD_DEF_18(18, "Armure d'Adamantium", BoostEffect.HIGH_DEF_BOOST,20),

    GENMOD_END_19(19, "Chest/Bras Chest/Bras Dos Chest/Bras Chest/Bras", BoostEffect.LOW_END_BOOST,20),
    GENMOD_END_20(20, "Articulations robotis\u00E9es", BoostEffect.MED_END_BOOST,20),
    GENMOD_END_21(21, "Abdominaux de Clint Eastwood", BoostEffect.HIGH_END_BOOST,20),

    GENMOD_GBQT_22(22, "Pierre du destrier", BoostEffect.LOW_GBQT_BOOST,20),
    GENMOD_GBQT_23(23, "Poche de kangourou", BoostEffect.MED_GBQT_BOOST,20),
    GENMOD_GBQT_24(24, "Tenser's floating disk", BoostEffect.HIGH_GBQT_BOOST,20),
    GENMOD_GBQT_25(25, "Sac sans fond", BoostEffect.SUPER_GBQT_BOOST,20),

    //Items Temporaires

    POTION_HP_26(26, "Potion de vie, petite", BoostEffect.TEMP_LOW_HP_BOOST,20),
    POTION_HP_27(27, "Potion de vie, moyenne", BoostEffect.TEMP_MED_HP_BOOST,20),
    POTION_HP_28(28, "Potion de vie, grande", BoostEffect.TEMP_HIGH_HP_BOOST,20),

    POTION_SPD_29(29, "Potion de vitesse, petite", BoostEffect.TEMP_LOW_SPD_BOOST,20),
    POTION_SPD_30(30, "Potion de vitesse, moyenne", BoostEffect.TEMP_MED_SPD_BOOST,20),
    POTION_SPD_31(31, "Potion de vitesse, grande", BoostEffect.TEMP_HIGH_SPD_BOOST,20),

    POTION_ATK_32(32, "Potion d'attaque, petite", BoostEffect.TEMP_LOW_ATK_BOOST,20),
    POTION_ATK_33(33, "Potion d'attaque, moyenne", BoostEffect.TEMP_MED_ATK_BOOST,20),
    POTION_ATK_34(34, "Potion d'attaque, grande", BoostEffect.TEMP_HIGH_ATK_BOOST,20),

    POTION_SMELLSENS_35(35, "Potion de sensibilit\u00E9, petite", BoostEffect.TEMP_LOW_SMELLSENS_BOOST,20),
    POTION_SMELLSENS_36(36, "Potion de sensibilit\u00E9, moyenne", BoostEffect.TEMP_MED_SMELLSENS_BOOST,20),
    POTION_SMELLSENS_37(37, "Potion de sensibilit\u00E9, grande", BoostEffect.TEMP_HIGH_SMELLSENS_BOOST,20),

    POTION_SMELLSTR_38(38, "Potion d'odeur, petite", BoostEffect.TEMP_LOW_SMELLSTR_BOOST,20),
    POTION_SMELLSTR_39(39, "Potion d'odeur, moyenne", BoostEffect.TEMP_MED_SMELLSTR_BOOST,20),
    POTION_SMELLSTR_40(40, "Potion d'odeur, grande", BoostEffect.TEMP_HIGH_SMELLSTR_BOOST,20),
    POTION_SMELLSTR_41(41, "Potion d'odeur, super", BoostEffect.TEMP_SUPER_SMELLSTR_BOOST,20),

    POTION_DEF_42(42, "Potion de d\u00E9fense, petite", BoostEffect.TEMP_LOW_DEF_BOOST,20),
    POTION_DEF_43(43, "Potion de d\u00E9fense, moyenne", BoostEffect.TEMP_MED_DEF_BOOST,20),
    POTION_DEF_44(44, "Potion de d\u00E9fense, grande", BoostEffect.TEMP_HIGH_DEF_BOOST,20),

    POTION_END_45(45, "Potion d'endurance, petite", BoostEffect.TEMP_LOW_END_BOOST,20),
    POTION_END_46(46, "Potion d'endurance, moyenne", BoostEffect.TEMP_MED_END_BOOST,20),
    POTION_END_47(47, "Potion d'endurance, grande", BoostEffect.TEMP_HIGH_END_BOOST,20),

    POTION_GBQT_48(48, "Potion de portage, petite", BoostEffect.TEMP_LOW_GBQT_BOOST,20),
    POTION_GBQT_49(49, "Potion de portage, moyenne", BoostEffect.TEMP_MED_GBQT_BOOST,20),
    POTION_GBQT_50(50, "Potion de portage, grande", BoostEffect.TEMP_HIGH_GBQT_BOOST,20),
    POTION_GBQT_51(51, "Potion de portage, super", BoostEffect.TEMP_SUPER_GBQT_BOOST,20),;



    private int itemID;
    private String name;
    private ItemEffect effect;
    private int winnings = 0;
    private int price;


    private Items(int itemID, String name, ItemEffect effect, int price){
        this.itemID =itemID;
        this.name = name;
        this.effect =effect;
        this.price = price;
    }

    public ItemEffect getEffect() {
        return effect;
    }



    public void firstActivation(){
        System.out.println("Item activation");
        ItemEffect effect = this.getEffect();
        if (effect instanceof BoostEffect){
            TempItemInstance instance = ((BoostEffect) effect).getTempInstance();
            //Ajouter l'instance temporaire au arraylist du joueur
        }else if (effect instanceof LotteryEffects){
            this.winnings = ((LotteryEffects) effect).activateLottery();
        }
    }

    public String getName() {
        return this.name;
    }

    public int getWinnings() {
        return winnings;
    }

    public int getPrice() {
        return price;
    }



}
