import processing.core.*;
public class Grid {
    private int height;
    private int width;
    private int x;
    private int y;
    private int color;
    private PApplet canvas; // access to canvas


public Grid(int xPos, int yPos,int Width,int Height, PApplet c){
    x = xPos;
    y = yPos;
    width = Width;
    height = Height;
    canvas = c;
    color = canvas.color(170, 173, 171); // gray
}


public void display() {
    canvas.fill(color);
    canvas.rect(x,y,width,height);
 }
}
