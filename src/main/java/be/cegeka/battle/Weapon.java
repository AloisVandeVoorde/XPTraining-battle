package be.cegeka.battle;

import java.util.Optional;

public enum Weapon {
    AXE(3, "SPEAR"),
    SWORD(2, "AXE"),
    SPEAR(2, "SWORD"),
    BARE_FISTS(1, null),
    TWO_HANDED_SWORD(5, null),
    BROAD_AXE(AXE.getDamage() + 2, null),
    TRIDENT(SPEAR.getDamage()*3, null),
    MAGIC_POTION(0, null);

    private final int damage;
    private final String effectiveAgainst;

    Weapon(int damage, String effectiveAgainst) {
        this.damage = damage;
        this.effectiveAgainst = effectiveAgainst;
    }

    private int getDamage() {
        return damage;
    }

    public Optional<Weapon> getEffectiveAgainst() {
        if (effectiveAgainst == null)
            return Optional.empty();

        return Optional.of(Weapon.valueOf(effectiveAgainst));
    }

    public int getDamage(Weapon against) {
        if (this == against)
            return this.getDamage();

        if (this == MAGIC_POTION)
            return against.getDamage(MAGIC_POTION)%2 == 0? 10 : 0;

        return this.getDamage();
    }
}
