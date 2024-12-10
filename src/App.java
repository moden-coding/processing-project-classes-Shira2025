import java.util.ArrayList;

import processing.core.*;

public class App extends PApplet {
    ArrayList<Block> blocks;
    int moves = 0;
    int time = 0; // need milis in here

    public static void main(String[] args) {
        PApplet.main("App");

    }

    public void setup() {
        background(143, 155, 176);
        blocks = new ArrayList<>();
        gridMaker();
        blockMaker();
    }

    public void settings() {
        size(800, 800); // Set the size of the window
    }

    public void draw() {
        for (Block B : blocks) {
            B.display();
        }
    }

    public void gridMaker() { /// draw the grid outline
        int x = 100;
        int y = 100;
        strokeWeight(5);
        Grid grid = new Grid(x, y, 600, 600, this);
        grid.display();
    }

    public void blockMaker() { /// draw the grid
        int y = 100;
        for (int rows = 0; rows < 3; rows++) {
            int x = 100;
            for (int elements = 0; elements < 3; elements++) {
                Block block = new Block(x, y, 200, 200, this);
                blocks.add(block);
                x += 200;
            }
            y += 200;

        }
        blocks.get(8).changeVisible(); // hide the last ball
    }

    public void mousePressed() {
        for (int i = 0; i < blocks.size(); i++) {
            Block b = blocks.get(i);
            if (b.isMouseInside(mouseX, mouseY)) {
                System.out.println("mouse pressed");
                // check right
                if (i % 3 != 2) {
                    Block rightBlock = blocks.get(i + 1);
                    if (rightBlock.ballVisible() == false) {
                        rightBlock.changeVisible();
                        b.changeVisible();
                        moves++;
                    }
                }
                // check left
                if (i % 3 != 0) {
                    Block leftBlock = blocks.get(i - 1);
                    if (leftBlock.ballVisible() == false) {
                        leftBlock.changeVisible();
                        b.changeVisible();
                        moves++;
                    }
                }
                // check up
                if (i / 3 != 0) {
                    Block upBlock = blocks.get(i - 3);
                    if (upBlock.ballVisible() == false) {
                        upBlock.changeVisible();
                        b.changeVisible();
                        moves++;
                    }
                }
                // check down
                if (i / 3 != 2) {
                    Block downBlock = blocks.get(i + 3);
                    if (downBlock.ballVisible() == false) {
                        downBlock.changeVisible();
                        b.changeVisible();
                        moves++;
                    }
                }
            }
        }
    }
}
