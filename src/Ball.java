import processing.core.*;
public class Ball { 

   private int x;
   private int y;
   private int size;
   private PApplet canvas; // access to canvas 
   private int color;


   public Ball(int xPos, int yPos, PApplet c) { // int col
      x = xPos;
      y = yPos;
      size = 100;
      canvas = c;
      color = canvas.color(randomColor());// color = col;
   }

   public void display() {
      canvas.circle(x, y, size);
      canvas.fill(color);
   }
     

   public void update(){

      }
   
   public int randomColor(){
      return canvas.color(canvas.random(255),canvas.random(255),canvas.random(255)); // random color 
   }


}
