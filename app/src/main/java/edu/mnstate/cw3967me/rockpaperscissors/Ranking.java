package edu.mnstate.cw3967me.rockpaperscissors;

/**
 store each ranking
 Mariko Noguchi
 11/28/2016
 */
public class Ranking {
    //objects(data) to be saved in db -- attribute are id & comment
    private long id;
    private String name;
    private String score;

    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getScore(){
        return score;
    }
    public void setScore(String score){
        this.score = score;
    }

    //Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString(){
        return name;
    }
}
