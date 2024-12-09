import java.util.ArrayList;

import processing.core.*;

public class App extends PApplet {
    ArrayList<Block> blocks;

    public static void main(String[] args) {
        PApplet.main("App");

    }

    public void setup() {
        background(128, 28, 89);
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
        Grid grid = new Grid(x, y, 600, 600, this);
        grid.display();
    }

    public void blockMaker() { /// draw the grid outline
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
        System.out.println("mouse pressed");
        for (int i = 0; i < blocks.size(); i++) {
            Block b = blocks.get(i);
            if (b.isMouseInside(mouseX, mouseY)) {
                System.out.println("mouse inside");
                Block rightBlock = blocks.get(i+1);
                if(i % 3 != 2 && rightBlock.ballVisible() == false){
                    System.out.println("block visible");
                    rightBlock.changeVisible();
                    b.changeVisible();
                }
                
               
            }
        }
    }

}
