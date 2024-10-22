package be.cegeka.battle;

public class War {
    private Army winner;
    private final Army attacker;
    private final Army defender;

    public War(Army attacker, Army defender) {
        this.attacker = attacker;
        this.defender = defender;
    }

    public void fight() {
        while (!attacker.isDefeated() && !defender.isDefeated()) {
            fightRound();
        }

        this.winner = attacker.isDefeated() ? defender : attacker;
        this.winner.reportVictory();
    }

    public Army getWinner() {
        if (this.winner == null) {
            throw new IllegalStateException("War has not been fought yet");
        }
        return this.winner;
    }

    private void fightRound() {
        Soldier soldier1 = attacker.getFrontMan().orElseThrow(IllegalStateException::new);
        Soldier soldier2 = defender.getFrontMan().orElseThrow(IllegalStateException::new);

        Battle battle = new Battle(soldier1, soldier2);
        battle.fight();

        Soldier winner = battle.getWinner();
        if (winner.equals(soldier1)) {
            defender.removeFrontMan();
        } else {
            attacker.removeFrontMan();
        }
    }


}
