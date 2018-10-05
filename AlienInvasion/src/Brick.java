import java.awt.Graphics2D;

public class Brick extends java.awt.Rectangle
{
  int dx;
  int dy;
  java.awt.Color c1;
  
  public Brick(int x, int y, int dx, int dy, java.awt.Color c1)
  {
    super(x, y, 3, 3);
    this.dx = dx;
    this.dy = dy;
    this.c1 = c1;
  }
  
  public void moveAndDraw(Graphics2D win)
  {
    translate(dx, dy);
    win.setColor(c1);
    win.fill(this);
    
    if ((getX() < 0.0D) || (getX() > 1000.0D)) {
      dx = (-dx);
    }
    if (intersects(bComp.paddle0)) {
      dy = 0;
      dx = 0;
    }
    if ((intersects(bComp.paddle0)) && (paddle.isLeft)) {
      dy = 0;
      dx = 5;
    }
    if ((intersects(bComp.paddle0)) && (paddle.isRight)) {
      dy = 0;
      dx = -5;
    }
  }
}
