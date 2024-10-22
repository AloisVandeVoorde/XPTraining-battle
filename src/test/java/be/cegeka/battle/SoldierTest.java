package be.cegeka.battle;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SoldierTest {

    @Test
    void construction_aSoldierMustHaveAName() {
        Soldier soldier = new Soldier("name");

        assertThat(soldier.getName()).isEqualTo("name");
    }

    @Test
    void construction_aSoldierMustHaveAName_cannotBeNull() {
        assertThatThrownBy(() -> new Soldier(null))
                .hasMessage("A soldier must have a name")
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void construction_aSoldierMustHaveAName_cannotBeEmpty() {
        assertThatThrownBy(() -> new Soldier(""))
                .hasMessage("A soldier must have a name")
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void construction_aSoldierMustHaveAName_cannotBeBlank() {
        assertThatThrownBy(() -> new Soldier("     "))
                .hasMessage("A soldier must have a name")
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void Soldier_givenSoldier_whenGetWeapon_thenReturnBareFists(){
        Soldier soldier = new Soldier("name");

        assertThat(soldier.getWeapon()).isEqualTo(Weapon.BARE_FISTS);
    }

    @Test
    void Soldier_givenSoldierWithWeapon_whenGetWeapon_thenReturnWeapon() {
        Soldier soldier = new Soldier("name", Weapon.AXE);

        assertThat(soldier.getWeapon()).isEqualTo(Weapon.AXE);
    }

    @Test
    void Soldier_givenSoldiersWithDifferentWeapons_whenSoldierAttacks_thenTheOneWithTheStrongestWeaponWins() {
        Soldier soldierWithAxe = new Soldier("soldierWithAxe", Weapon.AXE);
        Soldier soldierWithSword = new Soldier("soldierWithSword", Weapon.SWORD);
        BattleService battle = new BattleService(soldierWithSword,soldierWithAxe);

        assertThat(battle.getWinner()).isEqualTo(soldierWithAxe);
    }

    @Test
    void Soldier_givenSoldiersWithSameWeapons_whenSoldierAttacks_thenTheOneThatAttacksWins() {
        Soldier soldierWithAxe = new Soldier("soldierWithAxe", Weapon.AXE);
        Soldier soldierWithAxe2 = new Soldier("soldierWithAxe2", Weapon.AXE);

        BattleService battle = new BattleService(soldierWithAxe,soldierWithAxe2);

        assertThat(battle.getWinner()).isEqualTo(soldierWithAxe);
    }

    @Test
    void Army_givenSoldiers_whenAddSoldier_thenSoldierIsAddedToArmy() {
        Army army = new Army();
        Soldier soldier = new Soldier("soldier");

        army.addSoldier(soldier);

        assertThat(army.getSoldiers()).contains(soldier);
    }

    @Test
    void Army_givenArmyWithSoldiers_whenGetLeader_thenRetrunFirstAddedSoldier(){
        Army army = new Army();
        Soldier soldier = new Soldier("soldier");
        Soldier soldier2 = new Soldier("soldier2");

        army.addSoldier(soldier);
        army.addSoldier(soldier2);

        assertThat(army.getFrontMan().orElse(null)).isEqualTo(soldier);
    }

    @Test
    void War_givenTwoArmies_whenWar_thenStrongestArmyWins(){
        Army army1 = new Army();
        Soldier soldier1 = new Soldier("soldier1", Weapon.AXE);
        Soldier soldier2 = new Soldier("soldier2", Weapon.AXE);
        army1.addSoldier(soldier1);
        army1.addSoldier(soldier2);

        Army army2 = new Army();
        Soldier soldier3 = new Soldier("soldier3", Weapon.SWORD);
        Soldier soldier4 = new Soldier("soldier4", Weapon.SWORD);
        Soldier soldier5 = new Soldier("soldier5", Weapon.SWORD);
        army2.addSoldier(soldier3);
        army2.addSoldier(soldier4);
        army2.addSoldier(soldier5);

        War war = new War(army1, army2);

        assertThat(war.getWinner()).isEqualTo(army1);
    }
}