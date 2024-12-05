import java.util.ArrayList;

import processing.core.*;

public class App extends PApplet {
    ArrayList<Ball> balls;
    ArrayList<Block> blocks;

    public static void main(String[] args) {
        PApplet.main("App");

    }

    public void setup() {
        balls = new ArrayList<>();
        // gridMaker(); // should not be on top
        ballMaker();
        // blockMaker();

    }

    public void settings() {
        size(800, 800); // Set the size of the window
    }

    public void draw() {
        for (Ball b : balls) {
            b.display();
        }
        for (Block B : blocks) {
            B.display();
        }

    }

    public void ballMaker() { // make a 9 balls in a 3x3 row
        int y = 200;
        for (int rows = 0; rows < 3; rows++) {
            int x = 200;
            for (int elements = 0; elements < 3; elements++) {
                Ball ball = new Ball(x, y, this);
                balls.add(ball);
                x += 200;
            }
            y += 200;
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
        // int x = 100;
        // int y = 100;
        // while (blocks.size() < 9){
        // x = x + 200;
        // y = y + 200;
        // // strokeWeight(30);
        // Block block = new Block (x,y,200,200,this);
        // blocks.add(block);
    }
}
