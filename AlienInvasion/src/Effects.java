import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Effects
{
  Brick[] cluster = new Brick[50];
  Random r0 = new Random();
  Color c2;
  int color0;
  
  public Effects(int x, int y)
  {
    for (int i = 0; i < cluster.length; i++)
    {
      double dx = Math.random() * 50.0D * (int)Math.pow(-1.0D, (int)(Math.random() * 2.0D));
      double dy = Math.random() * 50.0D * (int)Math.pow(-1.0D, (int)(Math.random() * 2.0D));
      int xPos = x + (int)(Math.random() * 25.0D) * (int)Math.pow(-1.0D, (int)(Math.random() * 2.0D));
      int yPos = y + (int)(Math.random() * 25.0D) * (int)Math.pow(-1.0D, (int)(Math.random() * 2.0D));
      

      color0 = r0.nextInt(3);
      
      if (color0 == 0) {
        c2 = Color.RED;
      }
      if (color0 == 1)
        c2 = Color.YELLOW;
      if (color0 == 2) {
        c2 = Color.ORANGE;
      }
      cluster[i] = new Brick(xPos, yPos, (int)dx, (int)dy, c2);
    }
  }
  


  public void fall(Brick r1)
  {
    //dy += 1;
  }
  


  public void moveAndDraw(Graphics2D win)
  {
    for (int i = 0; i < cluster.length; i++) {
      Brick b1 = cluster[i];
      b1.moveAndDraw(win);
      fall(b1);
    }
  }
}
