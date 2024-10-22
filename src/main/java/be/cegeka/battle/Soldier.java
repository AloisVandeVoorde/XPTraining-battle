package be.cegeka.battle;


import com.google.common.base.Strings;

public class Soldier {

    private final String name;
    private final Weapon weapon;

    public Soldier(String name) {
        this(name, Weapon.BARE_FISTS);
    }

    public Soldier(String name, Weapon weapon) {
        if (Strings.isNullOrEmpty(name) || Strings.isNullOrEmpty(name.trim())) {
            throw new IllegalArgumentException("A soldier must have a name");
        }
        this.name = name;
        this.weapon = weapon;
    }

    String getName() {
        return this.name;
    }

    public Weapon getWeapon() {
        return this.weapon;
    }
}
