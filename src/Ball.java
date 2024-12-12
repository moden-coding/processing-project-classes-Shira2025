import java.util.ArrayList;

import processing.core.*;

public class Ball {
   private int x;
   private int y;
   private PApplet canvas; // access to canvas
   private float radius;
   public int number;
   private int color;

   public Ball(int xPos, int yPos, int color, PApplet c) { // int color
      x = xPos;
      y = yPos;
      canvas = c;
      this.radius = 50;
      this.color = color;
      // color = canvas.color(128, 138, 171);// color = col; randomColor()
   }

   public void display() {
      canvas.fill(color);
      canvas.circle(x, y, radius * 2); // circle() wants the diameter
   }

   // public int[] getColor() {
   //    return color;
   // }

   // public void setColor() {
      
   // }

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
}
