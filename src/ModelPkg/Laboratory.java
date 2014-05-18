package ModelPkg;


public class Laboratory {

    private static int speedEnduranceBefore = MapData.getFourmilierActualRaceStats(0);
    private static int attackDefenceBefore = MapData.getFourmilierActualRaceStats(1);
    private static int sensitivitySmellStrengthBefore = MapData.getFourmilierActualRaceStats(2);
    private static int cost = 0;

    private static boolean isFinish = true;
    private static boolean reset = false;

    /**
     * Retourne la valeur de la compétence vitesse/endurance avant les modifications
     * @return vitesse/endurance initiale
     */
    public static int getSpeedEnduranceBefore() {
        return speedEnduranceBefore;
    }

    /**
     * Méthode qui permet de modifié la compétence vitesse/endurance de départ
     * @param speedEnduranceBefore nouvelle vitesse/Endurance de départ
     */
    public static void setSpeedEnduranceBefore(int speedEnduranceBefore) {
        Laboratory.speedEnduranceBefore = speedEnduranceBefore;
    }

    /**
     * Retourne la valeur de la compétence attaque/défense avant les modifications
     * @return attaque/défense initiale
     */
    public static int getAttackDefenceBefore() {
        return attackDefenceBefore;
    }

    /**
     * Méthode qui permet de modifié la compétence  attaque/défense de départ
     * @param attackDefenceBefore nouvelle  attaque/défense de départ
     */
    public static void setAttackDefenceBefore(int attackDefenceBefore) {
        Laboratory.attackDefenceBefore = attackDefenceBefore;
    }

    /**
     * Retourne la valeur de la compétence odeur/odorat avant les modifications
     * @return odeur/odorat  initiale
     */
    public static int getSensitivitySmellStrengthBefore() {
        return sensitivitySmellStrengthBefore;
    }

    /**
     * Méthode qui permet de modifié la compétence odeur/odorat de départ
     * @param sensitivitySmellStrengthBefore nouvelle  odeur/odorat de départ
     */
    public static void setSensitivitySmellStrengthBefore(int sensitivitySmellStrengthBefore) {
        Laboratory.sensitivitySmellStrengthBefore = sensitivitySmellStrengthBefore;
    }

    /**
     * Méthode qui modifie le coût futur de la nouvelle race de fourmilier
     * @param cost nouveau coût
     */
    public static void setCost(int cost) {
        Laboratory.cost = cost;
    }

    /**
     * Méthode qui retourne si le joueur a clické sur le bouton "terminer"
     * @return true si le joueur a terminé, flase si le joueur n'a pas terminé
     *
     */
    public static boolean isFinish() {
        return isFinish;
    }

    /**
     * Méthode qui modifie la valeur de si le joueur a clické sur le bouton "terminer"
     * @param finish si le joueur a clicé sur  "terminer" ou non
     */
    public static void setFinish(boolean finish) {
        isFinish =finish;
    }

}
