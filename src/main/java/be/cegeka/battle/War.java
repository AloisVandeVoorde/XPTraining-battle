package be.cegeka.battle;

public class War {
    private final Army attacker;
    private final Army defender;

    public War(Army attacker, Army defender) {
        this.attacker = attacker;
        this.defender = defender;
    }

    public Army fight() {
        while (!attacker.isDefeated() && !defender.isDefeated()) {
            fightRound();
        }

        return getWinner();
    }

    private Army getWinner() {
        if (attacker.isDefeated()) {
            defender.reportVictory();
            return defender;
        }
        attacker.reportVictory();
        return attacker;
    }

    private void fightRound() {
        Soldier soldier1 = attacker.getFrontMan().orElseThrow(IllegalStateException::new);
        Soldier soldier2 = defender.getFrontMan().orElseThrow(IllegalStateException::new);

        Soldier winner = new BattleService(soldier1, soldier2).getWinner();
        if (winner.equals(soldier1)) {
            defender.removeFrontMan();
        } else {
            attacker.removeFrontMan();
        }
    }


}
