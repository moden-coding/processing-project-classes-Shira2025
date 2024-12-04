import java.util.ArrayList;

import processing.core.*;

public class App extends PApplet {
    ArrayList<Ball> balls;
    // Ball first;

    public static void main(String[] args) {
        PApplet.main("App");

    }

    public void setup() {
        balls = new ArrayList<>();
        background(150, 200, 255);
        // first = new Ball(40, 400, this);
    }

    public void settings() {
        size(800, 800); // Set the size of the window
    }

    public void draw() {
        // gridMaker(); // should not be on top
        ballMaker();
    }

    public void ballMaker() {
        int x = 30;
        int y = 100;
        while (balls.size() < 3) {
            x = x + 100;
            Ball ball = new Ball(x, y, this);
            balls.add(ball);
            ball.display();
        }
        while (balls.size() < 6) {
            x = 130;
            y = y + 100;
            Ball ball = new Ball(x, y, this);
            balls.add(ball);
            ball.display();
        }
        while (balls.size() < 9) {
            y = y + 100;
            Ball ball = new Ball(x, y, this);
            balls.add(ball);
            ball.display();
        }
    }

    public void gridMaker(){
        int x = 100;
        int y = 100;
        Grid grid = new Grid (x,y,600,600,this);
        grid.display();
    }
}