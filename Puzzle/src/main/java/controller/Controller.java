package controller;

import model.Game;
import view.ChoseView;
import view.EndOfGameView;
import view.GameView;
import view.RankingView;

import java.util.*;

    /*
       Controller Class manages the views and rankings
    */
public class Controller {

    /*
    Rankings are stored in a Map, with the key being the degree of the game 3x3/4x4/5x5
     */
    Map<Integer, List<Object>> rankings;
    ChoseView choseView;
    EndOfGameView endOfGameView;
    RankingView rankingView;
    GameView gameView;

    public Controller(){
        rankings=new HashMap<>();
    }

    /*
    Opening a new game
    Checking for username
    Closing old view, creating and opening new view
     */
    public void openGame(int n) {
        String username=choseView.getUsername();
        if(username.isEmpty())
            choseView.showError("No username");
        else {
            choseView.setVisibility(false);
            gameView=new GameView(this,n,username);
            gameView.setVisibility(true);
        }
    }
    /*
    Opening End of Game View
    Adding the Game to the Rankings
    Closing old view, creating and opening new view
     */
    public void openEndOfGame(Game game) {
        addRanking(game);
        gameView.setVisibility(false);
        endOfGameView=new EndOfGameView(this);
        endOfGameView.setStatistics(game);
        endOfGameView.setVisibility(true);
    }

    /*
    Opening Rankings
    Closing old view, creating and opening new view
    Displaying Rankings in Tables
     */
    public void openRankings() {

        endOfGameView.setVisibility(false);
        rankingView=new RankingView(this);
        rankingView.setVisibility(true);
        if(rankings.containsKey(3))
            rankingView.displayDataOBJ(rankings.get(3),rankingView.getTable3());
        if(rankings.containsKey(4))
            rankingView.displayDataOBJ(rankings.get(4),rankingView.getTable4());
        if(rankings.containsKey(5))
            rankingView.displayDataOBJ(rankings.get(5),rankingView.getTable5());
    }

    /*
    Opening the Chose View for new user
    Closing old view, creating and opening new view
     */
    public void playAgain(){
        if(endOfGameView!=null)
            endOfGameView.setVisibility(false);
        if(rankingView!=null)
            rankingView.setVisibility(false);
        choseView=new ChoseView(this);
        choseView.setVisibility(true);
    }

    /*
       Adding Game to the Rankings
       Automatically sorting the games by number of moves first and then time
    */
    public void addRanking(Game game) {
        if(!rankings.containsKey(game.n))
            rankings.put(game.n,new ArrayList<>());
        rankings.get(game.n).add(game);
        rankings.get(game.n).sort((o1, o2) -> {
            Game g1=(Game)o1;
            Game g2=(Game)o2;
            int moves=Integer.compare(g1.numberOfMoves,g2.numberOfMoves);
            if (moves!=0)
                return moves;
            return Double.compare(g1.time,g2.time);
        });

    }
    public void setChoseView(ChoseView choseView) {
        this.choseView = choseView;
    }

}
