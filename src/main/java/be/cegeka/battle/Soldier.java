package be.cegeka.battle;
import com.google.common.base.Strings;

import java.util.Optional;

public class Soldier {

    private final String name;
    private final Weapon weapon;
    private Integer Id = null;

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

    public void setId(int soldierId) {
        this.Id = soldierId;
    }

    public Optional<Integer> getId() {
        return Optional.ofNullable(this.Id);
    }
}
