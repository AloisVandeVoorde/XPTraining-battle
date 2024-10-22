package be.cegeka.battle;

public interface IHeadQuarters {
    int ReportEnlistment(String soldierName);

    void ReportCasualty(int soldierId);

    void ReportVictory(int remainingNumberOfSoldiers);
}
