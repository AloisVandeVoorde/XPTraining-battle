package be.cegeka.battle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Army {
    private List<Soldier> soldiers = new ArrayList<>();

    public Army() {
        this.soldiers = new ArrayList<>();
    }

    public void addSoldier(Soldier soldier) {
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
        if (!this.soldiers.isEmpty()) {
            this.soldiers.removeFirst();
        }
    }

    public boolean isDefeated() {
        return this.soldiers.isEmpty();
    }
}
