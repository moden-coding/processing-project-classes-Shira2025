import java.util.ArrayList;

import processing.core.*;

public class Block {
    private int height;
    private int width;
    private int x;
    private int y;
    private int color;
    public boolean isEmpty;
    Ball myBall;
    private PApplet canvas; // access to canvas
    public boolean ballVisible;
    public int ballColor;

    public Block(int xPos, int yPos, int Width, int Height, PApplet c, int color, int ballColor) {
        x = xPos;
        y = yPos;
        width = Width;
        height = Height;
        canvas = c;
        this.ballColor = ballColor;
        myBall = new Ball(x + width / 2, y + width / 2,ballColor, canvas);
        this.color = color;
        ballVisible = true;  
    }

    public void display() {
        canvas.fill(color);
        canvas.rect(x, y, width, height);
        if (ballVisible) {
            myBall.display();
        }

    }

    public void changeVisible() {
        ballVisible = !ballVisible;
    }

    public boolean isMouseInside(int mouseX, int mouseY) {
        return myBall.isMouseInside(mouseX, mouseY);
    }

    public boolean ballVisible() {
        return ballVisible;
    }
}
