import processing.core.*;

public class Ball {
   private int x;
   private int y;
   private int size;
   private PApplet canvas; // access to canvas
   private int color;

   // private float radius; // chat gpt
   public Ball(int xPos, int yPos, PApplet c) { // float radius,
      x = xPos;
      y = yPos;
      size = 100;
      canvas = c;
      // this.radius = radius; // chat gpt
      color = canvas.color(52, 40, 156);// color = col; randomColor()
   }

   public void display() {
      canvas.fill(color);
      canvas.circle(x, y, size);
   }

   public void update() {

   }

   public int randomColor() {
      return canvas.color(canvas.random(255), canvas.random(255), canvas.random(255)); // random color
   }

}

// boolean isMouseInside(float mouseX, float mouseY) { // chat gpt
// float dx = mouseX - x;
// float dy = mouseY - y;
// float distanceSquared = dx * dx + dy * dy;
// float radiusSquared = radius * radius;
// return distanceSquared <= radiusSquared;
