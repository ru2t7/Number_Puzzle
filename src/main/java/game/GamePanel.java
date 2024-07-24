package game;

import controller.Controller;
import model.Coordinates;
import model.Game;

import javax.swing.*;
import java.awt.*;
import java.util.*;

import static java.lang.System.nanoTime;

    /*
       Game Panel manages the functionality of the game, it implements Runnable in order to constantly read the user's
       input and update the board as needed
    */
public class GamePanel extends JPanel implements Runnable{

    private final Color tileColor =new Color(157, 201, 237);
    private final String[] sortedStrings; //Used to compare with current board to identify when the tiles are sorted
    Controller controller;
    public String username;
    public int numberOfMoves=0;
    public long time=0;
    public int n; // The dimension of the board, aka. 3x3/4x4/5x5
    public boolean end=false; //Variable for interrupting the game, used for testing purposes
    public final int tileSize=150; // Tile pixel size
    public int screenWidth;
    public int screenHeight;

    //Current position of the empty tile,that acts as the moving tile
    int tileOX=0;
    int tileOY=0;
    Map<Coordinates,String> tiles; //The tiles are represented by their position/coordinates and the content, aka their number
    Thread gameThread;
    KeyHandler keyHandler=new KeyHandler();

    public GamePanel(Controller controller,int n,String username){

        this.controller=controller;
        this.n=n; screenWidth=n*tileSize; screenHeight=n*tileSize;
        this.username=username;
        this.tiles=new TreeMap<>(); fillRandomTiles();
        this.setFocusable(true);
        this.sortedStrings=createSortedString(n);
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        startGameThread();
        this.addKeyListener(keyHandler);
    }

    public void startGameThread(){
        gameThread=new Thread(this);
        gameThread.start();
    }

    /*
    The thread runs for the duration of the game, constantly updating the tiles taking into account the keyboard actions,
    and redrawing the board.
    The game ends when the board is sorted or the user interrupts the game using the end variable
    At the end of the game we get the number of moves and the time it took to complete the game and call the controller to
    open the end of game view.
     */

    @Override
    public void run() {
        long startTime=nanoTime();
        while(gameThread!=null){
            if(tiles.values().toString().equals(Arrays.toString(sortedStrings))||end) {
                end = false;
                break;
            }
            update();
            repaint();
        }
        long endTime=nanoTime();
        time=endTime-startTime;
        controller.openEndOfGame( new Game(username, numberOfMoves,(double)time/1000000000,n));

    }

    /*
    Depending on the key pressed by the user, we test if the move is valid and update the empty position of the empty tile.
     */

    public void update(){
            if(keyHandler.left){
                if(tileOX<screenWidth-tileSize){updateTile(1,0);}
                keyHandler.left=false;
            }
            if(keyHandler.right){
                if(tileOX>tileSize-1){updateTile(-1,0);}
                keyHandler.right=false;
            }
            if(keyHandler.down){
                if(tileOY>tileSize-1) {updateTile(0,-1);}
                keyHandler.down=false;
            }
            if(keyHandler.up){
                if(tileOY<screenHeight-tileSize) {updateTile(0,1);}
                keyHandler.up=false;
            }

    }

    /*
    Repainting each tile
     */

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D) g;
        for(int i=0;i<n;i++) {
            for (int j = 0; j < n; j++) {
                drawTile(g2,i,j);
            }
        }
        g2.dispose();
    }

    /*
    To draw the tile we draw a blue square and the corresponding number of top
    */
    private void drawTile(Graphics2D g2,int i, int j){
        g2.setColor(tileColor);
        g2.fillRect(i*tileSize+5,j*tileSize+5,tileSize-10,tileSize-10);
        g2.setColor(Color.BLACK);
        String string=tiles.get(new Coordinates(i*tileSize,j*tileSize));
        g2.setFont(new Font("Monospaced", Font.BOLD, 20));
        g2.drawString(string,i*tileSize+tileSize/2,j*tileSize+tileSize/2);
    }
    /*
    To update the position of the tile we simply switch the values of the tiles at the current/pld coordinates and at
    the new coordinates int the direction of the key.
    We perform this in the tiles map.
     */
    private void updateTile(int i, int j){
        Coordinates oldCoordinates=new Coordinates(tileOX,tileOY);
        Coordinates newCoordinates=new Coordinates(tileOX+i*tileSize,tileOY+j*tileSize);
        String replaceValue=tiles.get(newCoordinates);
        tiles.replace(oldCoordinates," ",replaceValue);
        tiles.replace(newCoordinates,replaceValue," ");
        tileOX=tileOX+i*tileSize;
        tileOY=tileOY+j*tileSize;
        numberOfMoves++;
    }
    /*
    Creating a random new board
     */
    private void fillRandomTiles(){
        ArrayList<String> strings=new ArrayList<>(n*n-1);
        for(int i=1;i<n*n;i++){
            strings.add(String.valueOf(i));
        }
        Collections.shuffle(strings);

        int m=0;
        for(int i=0; i< n ;i++) {
            for (int j = 0; j < n; j++) {
                if(i==n-1 && j==n-1){
                    tileOX=(n-1)*tileSize;
                    tileOY=(n-1)*tileSize;
                    this.tiles.put(new Coordinates(i*tileSize, j*tileSize), " ");
                }
                else{
                    this.tiles.put(new Coordinates(i * tileSize, j * tileSize), strings.get(m++));
                }
            }
        }
    }

    /*
    Creating a sorted String representation of the tiles to compare with the current board and identify when
    the board has been sorted and the game needs to end.
     */
    private String[] createSortedString(int n){
        String[] strings=new String[n*n];
        int m=0;
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                strings[m++]=String.valueOf(j*n+i+1);
        strings[n*n-1]=" ";
        return strings;
    }
}
