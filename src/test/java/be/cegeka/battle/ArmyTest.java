package be.cegeka.battle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ArmyTest {
    private IHeadQuarters hq;
    private Army army1;
    private Soldier soldier1;

    @BeforeEach
    void setUp() {
        hq = mock(IHeadQuarters.class);

        soldier1 = new Soldier("soldier");
        when(hq.ReportEnlistment(soldier1.getName())).thenReturn(1);
        army1 = new Army(hq);
        army1.addSoldier(soldier1);
    }

    @Test
    void Army_givenSoldiers_whenAddSoldier_thenSoldierIsAddedToArmy() {
        assertThat(army1.getSoldiers()).contains(soldier1);
    }

    @Test
    void Army_givenArmyWithSoldiers_whenGetLeader_thenReturnFirstAddedSoldier(){
        Soldier soldier2 = new Soldier("soldier2");
        when(hq.ReportEnlistment(soldier2.getName())).thenReturn(2);

        army1.addSoldier(soldier2);

        assertThat(army1.getFrontMan().orElse(null)).isEqualTo(soldier1);
    }

    @Test
    void War_givenTwoArmies_whenWar_thenStrongestArmyWins(){
        Soldier soldier2 = new Soldier("soldier2", Weapon.AXE);
        Soldier soldier3 = new Soldier("soldier3", Weapon.SWORD);
        Soldier soldier4 = new Soldier("soldier4", Weapon.SWORD);
        Soldier soldier5 = new Soldier("soldier5", Weapon.SWORD);
        Army army2 = new Army(hq);

        when(hq.ReportEnlistment(soldier2.getName())).thenReturn(2);
        when(hq.ReportEnlistment(soldier3.getName())).thenReturn(3);
        when(hq.ReportEnlistment(soldier4.getName())).thenReturn(4);
        when(hq.ReportEnlistment(soldier5.getName())).thenReturn(5);

        army1.addSoldier(soldier2);
        army2.addSoldier(soldier3);
        army2.addSoldier(soldier4);
        army2.addSoldier(soldier5);

        War war = new War(army1, army2);
        war.fight();

        assertThat(war.getWinner()).isEqualTo(army1);
    }

    @Test
    void Army_givenArmyWithHQ_whenSoldierAdded_thenGetIdFromHQ(){
        assertThat(soldier1.getId().orElse(null)).isEqualTo(1);
    }

    @Test
    void HQ_givenArmy_whenSoldierDied_thenReportCasualty(){

        army1.removeFrontMan();
        verify(hq).ReportCasualty(1);
    }

    @Test
    void HQ_givenArmyInWar_whenVictory_thenReportVictory(){
        Soldier soldier2 = new Soldier("soldier", Weapon.AXE);
        when(hq.ReportEnlistment(soldier2.getName())).thenReturn(2);
        Army army2 = new Army(hq);
        army2.addSoldier(soldier2);

        War war = new War(army1, army2);
        war.fight();

        verify(hq).ReportVictory(1);
    }
}
