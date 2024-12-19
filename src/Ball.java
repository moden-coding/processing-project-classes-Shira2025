import processing.core.*;

public class Ball {
   private int x;
   private int y;
   private PApplet canvas; // access to canvas
   private float radius;
   public int number;
   private int color;

   public Ball(int xPos, int yPos, int color, PApplet c) { // making ball
      x = xPos;
      y = yPos;
      canvas = c;
      this.radius = 50;
      this.color = color;
      // color = canvas.color(128, 138, 171);// color = col; randomColor()
   }

   public void display() {
      canvas.fill(color);
      canvas.circle(x, y, radius * 2);
   }

   boolean isMouseInside(float mouseX, float mouseY) { // CHAT GPT math
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

   public int getColor() { // getting the color of ball
      return color;
   }

   public void setColor(int c) { // setting the color of ball
      color = c;
   }
}
