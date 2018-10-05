import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class menuSelector implements KeyListener
{
  static int ccount;
  int count;
  int keyUp;
  int keyDown;
  Polygon[] cur = new Polygon[2];
  
  public menuSelector(int key_up, int key_down) { ccount = 0;
    count = 0;
    cur[0] = new Polygon(new int[] { 300, 300, 330 }, new int[] { animation.y + 500, animation.y + 530, animation.y + 515 }, 3);
    cur[1] = new Polygon(new int[] { 670, 670, 640 }, new int[] { animation.y + 500, animation.y + 530, animation.y + 515 }, 3);
    keyUp = key_up;
    keyDown = key_down;
  }
  
  public int count()
  {
    return ccount;
  }
  

  public void moveAndDraw(Graphics2D win)
  {
    win.setColor(bComp.c0);
    

    win.draw(cur[0]);
    win.draw(cur[1]);
    if (bComp.selected) {
      count += 1;
      
      cur[0].translate(0, -2);
      cur[1].translate(0, -2);
    }
    if (!bComp.selected) {
      while (count > 0) {
        count -= 1;
        cur[0].translate(0, 2);
        cur[1].translate(0, 2);
      }
    }
  }
  



  public void keyPressed(KeyEvent e) {}
  



  public void keyReleased(KeyEvent e)
  {
    if (e.getKeyCode() == keyDown) {
      if (!bComp.selected) {
        ccount += 1;
        if ((ccount <= 2) && 
          (!bComp.selected)) {
          cur[0].translate(0, 48);
          cur[1].translate(0, 48);
        }
        
        if (ccount >= 3) {
          ccount = 2;
        }
      }
    }
    else if ((e.getKeyCode() == keyUp) && 
      (!bComp.selected)) {
      ccount -= 1;
      if ((ccount >= 0) && 
        (!bComp.selected)) {
        cur[0].translate(0, -48);
        cur[1].translate(0, -48);
      }
      
      if (ccount < 0) {
        ccount = 0;
      }
    }
  }
  
  public void keyTyped(KeyEvent e) {}
}
