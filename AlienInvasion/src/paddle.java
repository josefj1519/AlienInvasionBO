import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class paddle extends Rectangle implements java.awt.event.KeyListener
{
  double dx;
  double speed = 10.0D;
  java.awt.Color c1;
  static boolean isLeft;
  static boolean isRight;
  int keyLeft;
  int keyRight;
  
  public paddle(int x, int y, int key_left, int key_right) { super(x, y, 100, 15);
    dx = 0.0D;
    
    isLeft = false;
    keyLeft = key_left;
    keyRight = key_right;
  }
  
  public void moveAndDraw(Graphics2D win) {
    dx = 0.0D;
    if (isRight) {
      dx = speed;
    }
    if (isLeft) {
      dx = (-speed);
    }
    if ((getX() + dx > 890.0D) || (getX() + dx < 0.0D)) {
      dx = 0.0D;
    }
    
    translate((int)dx, 0);
    win.setColor(bComp.c0);
    win.draw(this);
  }
  


  public void keyPressed(KeyEvent e)
  {
    if (e.getKeyCode() == keyLeft)
    {
      isLeft = true;
    }
    else if (e.getKeyCode() == keyRight)
    {
      isRight = true;
    }
  }
  
  public void keyReleased(KeyEvent e)
  {
    if (e.getKeyCode() == keyLeft) {
      isLeft = false;
    }
    else if (e.getKeyCode() == keyRight) {
      isRight = false;
    }
  }
  
  public void keyTyped(KeyEvent e) {}
}
