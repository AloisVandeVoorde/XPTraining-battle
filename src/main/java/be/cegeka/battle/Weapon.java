package be.cegeka.battle;

public enum Weapon {
    AXE(3),
    SWORD(2),
    SPEAR(2),
    BARE_FISTS(1);

    private int damage;

    Weapon(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
