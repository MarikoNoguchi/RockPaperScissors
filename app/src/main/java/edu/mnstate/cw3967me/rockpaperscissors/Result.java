package edu.mnstate.cw3967me.rockpaperscissors;

/**
store each results
 Mariko Noguchi
 10/31/2016.
 */
public class Result {
    private String user = "";//whether the user won
    private String computer = "";//whether computer won
    private String round = "";//number of the round

    public void setUser(String result){ user = result;}
    public String getUser(){return user;}

    public void setComputer(String result){ computer = result;}
    public String getComputer(){return computer;}

    public void setRound(String round){this.round = round;}
    public String getRound(){return round;}
}
