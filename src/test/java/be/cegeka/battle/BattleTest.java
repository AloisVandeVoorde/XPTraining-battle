package be.cegeka.battle;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BattleTest {
    @Test
    void Battle_givenSoldiersWithDifferentWeapons_whenSoldierFight_thenTheOneWithTheStrongestWeaponWins() {
        Soldier soldierWithAxe = new Soldier("soldierWithAxe", Weapon.AXE);
        Soldier soldierWithSword = new Soldier("soldierWithSword", Weapon.SWORD);

        Battle battle = new Battle(soldierWithSword,soldierWithAxe);
        battle.fight();

        assertThat(battle.getWinner()).isEqualTo(soldierWithAxe);
    }

    @Test
    void Battle_givenSoldiersWithSameWeapons_whenSoldiersFight_thenTheOneThatAttacksWins() {
        Soldier soldierWithAxe = new Soldier("soldierWithAxe", Weapon.AXE);
        Soldier soldierWithAxe2 = new Soldier("soldierWithAxe2", Weapon.AXE);

        Battle battle = new Battle(soldierWithAxe,soldierWithAxe2);
        battle.fight();

        assertThat(battle.getWinner()).isEqualTo(soldierWithAxe);
    }

    @Test
    void Battle_givenSoldierWithMagickPotion_whenSoldierFightsEvenOutput_thenSoldierWins() {
        Soldier soldierWithMagickPotion = new Soldier("soldierWithMagickPotion", Weapon.MAGIC_POTION);
        Soldier soldierWithAxe = new Soldier("soldierWithAxe", Weapon.SWORD);

        Battle battle = new Battle(soldierWithAxe,soldierWithMagickPotion);
        battle.fight();

        assertThat(battle.getWinner()).isEqualTo(soldierWithMagickPotion);
    }

    @Test
    void Battle_givenSoldierWithMagickPotion_whenSoldierFightsOddOutput_thenSoldierLoses() {
        Soldier soldierWithMagickPotion = new Soldier("soldierWithMagickPotion", Weapon.MAGIC_POTION);
        Soldier soldierWithAxe = new Soldier("soldierWithAxe", Weapon.AXE);

        Battle battle = new Battle(soldierWithAxe,soldierWithMagickPotion);
        battle.fight();

        assertThat(battle.getWinner()).isEqualTo(soldierWithAxe);
    }
}
