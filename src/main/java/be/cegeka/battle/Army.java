package be.cegeka.battle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Army {
    private List<Soldier> soldiers = new ArrayList<>();
    private IHeadQuarters hq;

    public Army(IHeadQuarters hq) {
        this.soldiers = new ArrayList<>();
        this.hq = hq;
    }

    public void addSoldier(Soldier soldier) {
        int soldierId = hq.ReportEnlistment(soldier.getName());

        soldier.setId(soldierId);
        this.soldiers.add(soldier);
    }

    public List<Soldier> getSoldiers() {
        return this.soldiers;
    }

    public Optional<Soldier> getFrontMan() {
        if (this.soldiers.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(soldiers.getFirst());
    }

    public void removeFrontMan() {
        hq.ReportCasualty(this.soldiers.getFirst().getId().orElseThrow(IllegalStateException::new));
        if (!this.soldiers.isEmpty()) {
            this.soldiers.removeFirst();
        }

    }

    public boolean isDefeated() {
        return this.soldiers.isEmpty();
    }

    public void reportVictory() {
        hq.ReportVictory(this.soldiers.size());
    }
}
