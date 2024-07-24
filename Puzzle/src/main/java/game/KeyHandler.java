package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
   Reads the user's inputs and signals them to the Game Panel through the boolean values
*/
public class KeyHandler implements KeyListener {
    public boolean up,down,left,right;
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int code=e.getKeyCode();
        if(code==KeyEvent.VK_UP){
            this.up=true;
        }


        if(code==KeyEvent.VK_DOWN){
            this.down=true;
        }


        if(code==KeyEvent.VK_LEFT){
            this.left=true;
        }


        if(code==KeyEvent.VK_RIGHT){
            this.right=true;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
