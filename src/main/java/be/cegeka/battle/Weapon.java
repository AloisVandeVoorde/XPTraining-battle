package be.cegeka.battle;

public enum Weapon {
    AXE(3),
    SWORD(2),
    SPEAR(2),
    BARE_FISTS(1),
    TWO_HANDED_SWORD(5),
    BROAD_AXE(AXE.getDamage() + 2),
    TRIDENT(SPEAR.getDamage()*3),
    MAGIC_POTION(0);

    private final int damage;

    Weapon(int damage) {
        this.damage = damage;
    }

    private int getDamage() {
        return damage;
    }

    public int getDamage(Weapon against) {
        if (this == against)
            return this.getDamage();

        if (this == MAGIC_POTION)
            return against.getDamage(MAGIC_POTION)%2 == 0? 10 : 0;

        return this.getDamage();
    }
}
