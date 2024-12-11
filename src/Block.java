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
    private int[] colors = {
        235, 52, 110,
        237, 111, 43,
        237, 201, 40,
        37, 217, 76,
        36, 201, 163,
        34, 107, 201,
        112, 28, 201,
        201, 26, 196,
        138, 30, 70};

    public Block(int xPos, int yPos, int Width, int Height, PApplet c) {
        x = xPos;
        y = yPos;
        width = Width;
        height = Height;
        canvas = c;
        myBall = new Ball(x + width / 2, y + width / 2, canvas);
        color = canvas.color(125, 118, 117);
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
 
    public boolean ballVisible(){
        return ballVisible;
    }
}
