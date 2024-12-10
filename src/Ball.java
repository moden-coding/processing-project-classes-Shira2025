import processing.core.*;

public class Ball {
   private int x;
   private int y;
   private PApplet canvas; // access to canvas
   private int color;
   private float radius;
   public int number;

   public Ball(int number, int xPos, int yPos, float radius, PApplet c) {
      x = xPos;
      y = yPos;
      canvas = c;
      this.radius = radius;
      this.number = number;
      color = canvas.color(52, 40, 156);// color = col; randomColor()
   }

   public Ball(int xPos, int yPos, PApplet c) {
      x = xPos;
      y = yPos;
      canvas = c;
      this.radius = 50;
      color = canvas.color(52, 40, 156);// color = col; randomColor()
   }

   public void display() {
      canvas.fill(color);
      canvas.circle(x, y, radius * 2); // circle() wants the diameter
   }

   public void update() {

   }

   public int randomColor() {
      return canvas.color(canvas.random(255), canvas.random(255), canvas.random(255)); // random color
   }

   boolean isMouseInside(float mouseX, float mouseY) { // chat gpt
      // Calculate the squared distance between the mouse and the circle's center
      float dx = mouseX - x;
      float dy = mouseY - y;
      float distanceSquared = dx * dx + dy * dy;

      // Compare squared distance to squared radius
      float radiusSquared = radius * radius;
      if (x == 200 && y == 200) {
      }
      return distanceSquared <= radiusSquared;
   }

   // void setColor(int newColor) { 
   //    this.color = newColor;
   // }
}
