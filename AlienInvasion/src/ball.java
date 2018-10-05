import java.awt.Graphics2D;
import java.awt.Rectangle;

public class ball extends Rectangle
{
  static int dy;
  static int dx;
  int speed;
  int rand;
  int length;
  
  public ball()
  {
    super(450, 470, 15, 15);
    length = 15;
    speed = 4;
    
    dx = speed;
    dy = -speed;
  }
  





  public void moveAndDraw(Graphics2D win)
  {
    if (getY() < 50.0D) {
      dy = -dy;
    }
    if (getX() + length > 985.0D) {
      dx = -dx;
    }
    if (getX() < 0.0D) {
      dx = -dx;
    }
    
    System.out.println(dy);
    translate(dx, dy);
    win.setColor(java.awt.Color.GREEN);
    win.draw(this);
  }
}
