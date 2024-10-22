package be.cegeka.battle;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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


}