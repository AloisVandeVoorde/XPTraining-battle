package be.cegeka.battle;

public class War {
    private final Army army1;
    private final Army army2;

    public War(Army army1, Army army2) {
        this.army1 = army1;
        this.army2 = army2;
    }

    public Army getWinner() {
        while (!army1.isDefeated() && !army2.isDefeated()) {
            Soldier soldier1 = army1.getFrontMan().orElseThrow(IllegalStateException::new);
            Soldier soldier2 = army2.getFrontMan().orElseThrow(IllegalStateException::new);

            Soldier winner = new BattleService(soldier1, soldier2).getWinner();
            if (winner.equals(soldier1)) {
                army2.removeFrontMan();
            } else {
                army1.removeFrontMan();
            }
        }
        return army1.isDefeated() ? army2 : army1;
    }
}
