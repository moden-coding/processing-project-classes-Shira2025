import processing.core.*;
public class Ball { 

   private int x;
   private int y;
   private int size;
   private PApplet canvas; // access to canvas 
   private int color;


   public Ball(int xPos, int yPos, PApplet c) {
      x = xPos;
      y = yPos;
      size = 50;
      canvas = c;
      color = canvas.color(randomColor());//255,0,0
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
