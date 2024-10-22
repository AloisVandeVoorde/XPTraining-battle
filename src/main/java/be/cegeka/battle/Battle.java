package be.cegeka.battle;

public class Battle {
    private Soldier winner;
    private final Soldier attacker;
    private final Soldier defender;

    public Battle(Soldier attacker, Soldier defender) {
        this.attacker = attacker;
        this.defender = defender;
    }

    public void fight() {
        Weapon attackerWeapon = attacker.getWeapon();
        Weapon defenderWeapon = defender.getWeapon();

        int effectiveDamage = 0;
        if (attackerWeapon.getEffectiveAgainst().isPresent() && attackerWeapon.getEffectiveAgainst().get() == defenderWeapon) {
            effectiveDamage = 3;
        }

        if (
            attackerWeapon == defenderWeapon ||
            (attackerWeapon.getDamage(defenderWeapon) + effectiveDamage > defenderWeapon.getDamage(attackerWeapon))
        ) {
            this.winner = attacker;
        } else {
            this.winner = defender;
        }
    }

    public Soldier getWinner() {
        if (this.winner == null) {
            throw new IllegalStateException("Battle has not been fought yet");
        }
       return this.winner;
    }
}
