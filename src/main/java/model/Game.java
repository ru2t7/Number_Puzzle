package model;

/*
   Game stores the main statistics of the game
*/
public class Game{
    public String username;
    public int numberOfMoves;
    public double time;
    public int n;
    public Game(String username, int numberOfMoves, double time, int n){
        this.username=username;
        this.numberOfMoves=numberOfMoves;
        this.time=time;
        this.n=n;
    }
}