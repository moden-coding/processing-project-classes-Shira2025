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

    public Block(int xPos, int yPos, int Width, int Height, PApplet c, int color, int ballColor) { // block
        x = xPos;
        y = yPos;
        width = Width;
        height = Height;
        canvas = c;
        this.ballColor = ballColor;
        myBall = new Ball(x + width / 2, y + width / 2, ballColor, canvas);
        this.color = color;
        ballVisible = true;
    }

    public void display() { // showing to canvas 
        canvas.fill(color);
        canvas.rect(x, y, width, height);
        if (ballVisible) {
            myBall.display();
        }

    }

    public void changeVisible() {// making the previous ball not visible or visible 
        ballVisible = !ballVisible;
    }

    public boolean isMouseInside(int mouseX, int mouseY) { // checking if mouse inside ball 
        return myBall.isMouseInside(mouseX, mouseY);
    }

    public boolean ballVisible() { // the ball visiblity 
        return ballVisible;
    }

    public int getBallColor() { // givng the ball color 
        return myBall.getColor();
    }

    public void setBallColor(int color) { // setting ball color 
        myBall.setColor(color);
    }
}
