package main;

import controller.Controller;
import view.ChoseView;



public class Main {
    public static void main(String[] args) {
        Controller controller=new Controller();
        ChoseView choseView=new ChoseView(controller);
        choseView.setVisibility(true);
        controller.setChoseView(choseView);
    }
}
