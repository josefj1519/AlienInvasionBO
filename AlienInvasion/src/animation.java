import java.awt.Color;
import java.awt.Graphics2D;

public class animation {
  int time;
  int time1;
  int time_move;
  int x;
  int xpos;
  int ypos;
  int count;
  static int y;
  
  public animation() { y = 65336;
    time = 2;
    time1 = 2;
    time_move = 2;
    x = 1;
    xpos = 450;
    ypos = 350;
  }
  



  public void moveAndDraw(Graphics2D win)
  {
    if (!bComp.selected) {
      x += 1;
      y = 65336;
    }
    if (bComp.selected) {
      x = -15;
      y -= 2;
    }
    if (y < 64861) {
      bComp.selected = false;
    }
    win.setColor(Color.GREEN);
    win.fillArc(350 + x, 350 + y, 300, 25, 180, 65356);
    win.fillArc(xpos + x, 300 + y, 100, 100, 180, 65356);
    
    if (xpos + x == 1100) {
      x = 64836;
    }
    

    time += 1;
    win.setColor(Color.black);
    

    if (time / 2 < 50) {
      win.fillOval(435 + x, 353 + y, 10, 10);
      win.fillOval(545 + x, 353 + y, 10, 10);
    }
    else if (time / 2 < 100) {
      win.fillOval(415 + x, 353 + y, 10, 10);
      win.fillOval(465 + x, 353 + y, 10, 10);
      win.fillOval(515 + x, 353 + y, 10, 10);
      win.fillOval(565 + x, 353 + y, 10, 10);
    }
    if (time / 2 > 100) {
      time = 2;
    }
    
    time1 += 1;
    
    win.setColor(Color.GREEN);
    if (time1 / 2 >= 20)
    {

      if ((time1 / 2 < 40) && (!bComp.selected)) {
        win.drawRoundRect(450 + x, 375 + y, 100, 15, 30, 30);
      } else if ((time1 / 2 < 60) && (!bComp.selected)) {
        win.drawRoundRect(450 + x, 375 + y, 100, 15, 30, 30);
        win.drawRoundRect(430 + x, 410 + y, 140, 15, 30, 30);
      }
      else if ((time1 / 2 < 80) && (!bComp.selected)) {
        win.drawRoundRect(450 + x, 375 + y, 100, 15, 30, 30);
        win.drawRoundRect(430 + x, 410 + y, 140, 15, 30, 30);
        win.drawRoundRect(410 + x, 445 + y, 180, 15, 30, 30);
      }
      else if (time1 / 2 < 120) {
        win.drawRoundRect(450 + x, 375 + y, 100, 15, 30, 30);
        win.drawRoundRect(430 + x, 410 + y, 140, 15, 30, 30);
        win.drawRoundRect(410 + x, 445 + y, 180, 15, 30, 30);
        win.drawRoundRect(390 + x, 480 + y, 220, 15, 30, 30);
      }
    }
    if (time1 / 2 > 120) {
      time1 = 2;
    }
  }
}
