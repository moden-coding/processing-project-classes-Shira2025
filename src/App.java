import java.util.ArrayList;

import processing.core.*;

public class App extends PApplet {
    ArrayList<Ball> balls;
    ArrayList<Block> blocks;
    boolean moveBall = false;
    ArrayList<Integer> positions;

    public static void main(String[] args) {
        PApplet.main("App");

    }

    public void setup() {
        background(128, 28, 89);
        balls = new ArrayList<>();
        blocks = new ArrayList<>();
        positions= new ArrayList<>();
        // gridMaker();
        blockMaker();
        ballMaker();

    }

    public void settings() {
        size(800, 800); // Set the size of the window
    }

    public void draw() {
        for (Block B : blocks) {
            B.display();
        }
        for (Ball b : balls) {
            b.display();
        }
    }

    public void ballMaker() { // make a 8 balls in a 3x3 row not working
        int y = 200;
        for (int row = 0; row < 3; row++) {
            int x = 200;
            for (int column = 0; column < 3; column++) {
                if (row == 2 && column == 2) {
                    break;
                }
                Ball ball = new Ball(x, y, this);
                positions.add(x);
                positions.add(y);
                balls.add(ball);
                System.out.println("made one here");
                x += 200;
            }
            y += 200;
        }
        System.out.println(positions);
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
    }
}