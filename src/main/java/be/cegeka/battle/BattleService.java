package be.cegeka.battle;

public record BattleService (Soldier attacker, Soldier defender) {
    public Soldier getWinner() {
        if (attacker.getWeapon().getDamage() == defender.getWeapon().getDamage())
            return attacker;

        if (attacker.getWeapon().getDamage() > defender.getWeapon().getDamage()) {
            return attacker;
        } else {
            return defender;
        }
    }
}
