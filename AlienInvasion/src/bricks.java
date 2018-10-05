import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;

public class bricks extends Rectangle
{
  static Rectangle[][] sBrick = new Rectangle[9][18];
  static Color[][] brick_color = new Color[9][18];
  static boolean[][] brick_dead = new boolean[9][18];
  Random r1 = new Random();
  Random r2 = new Random();
  int width;
  int height;
  int heart_count;
  int speed; double dy; static int time; static Color light_green = new Color(200, 255, 120);
  static Color green = new Color(0, 255, 0);
  static Color dark_green = new Color(0, 102, 51);
  static Color health = Color.RED;
  
  public bricks()
  {
    dy = 1.0D;
    width = 54;
    height = 25;
    heart_count = 0;
    
    if (bComp.lvl == 0) {
      for (int i = 0; i < sBrick.length; i++) {
        for (int j = 0; j < sBrick[i].length; j++) {
          int heart = r2.nextInt(100);
          brick_dead[i][j] = false;
          sBrick[i][j] = new Rectangle(55 * j, 26 * (i + 1) + 25, width, height);
          if (i < 3) {
            if (((heart == 39) || (heart == 38)) && (heart_count < 2)) {
              heart_count += 1;
              brick_color[i][j] = health;
            }
            else {
              brick_color[i][j] = dark_green;
            }
          }
          else if (i < 6) {
            if (((heart == 39) || (heart == 38)) && (heart_count < 2)) {
              heart_count += 1;
              brick_color[i][j] = health;
            }
            else {
              brick_color[i][j] = green;
            }
          }
          else if (i < 9) {
            if (((heart == 39) || (heart == 38)) && (heart_count < 2)) {
              heart_count += 1;
              brick_color[i][j] = health;
            }
            else {
              brick_color[i][j] = light_green;
            }
            
          }
          
        }
        
      }
    } else {
      randLvl();
    }
    time = 0;
    speed = 75;
  }
  
  public void moveAndDraw(java.awt.Graphics2D win) {
    time += 1;
    if (time > 8000) {
      speed = 1;
    }
    else if (time > 6000) {
      speed = 15;

    }
    else if (time > 4000) {
      speed = 30;
    }
    else if (time > 2000) {
      speed = 50;
    }
    


    for (int i = 0; i < sBrick.length; i++) {
      for (int j = 0; j < sBrick[i].length; j++) {
        if (health.equals(brick_color[i][j])) {
          win.setColor(brick_color[i][j]);
          win.fill(sBrick[i][j]);
          if (time % speed == 0)
            sBrick[i][j].translate(0, (int)dy);
          if ((sBrick[i][j].getY() == 450.0D) && (brick_dead[i][j] == false)) {
            dy = 0.0D;
          }
        }
        else {
          win.setColor(brick_color[i][j]);
          win.fill(sBrick[i][j]);
          if (time % speed == 0)
            sBrick[i][j].translate(0, (int)dy);
          if ((sBrick[i][j].getY() == 600.0D) && (brick_dead[i][j] == false)) {
            dy = 0.0D;
            bComp.lives = 0;
          }
        }
      }
    }
  }
  




  public void randLvl()
  {
    for (int i = 0; i < sBrick.length; i++) {
      for (int j = 0; j < sBrick[i].length; j++) {
        int heart = r2.nextInt(100);
        int color = r1.nextInt(4);
        brick_dead[i][j] = false;
        sBrick[i][j] = new Rectangle(55 * j, 26 * (i + 1) + 25, width, height);
        if (color == 0) {
          if (((heart == 39) || (heart == 38)) && (heart_count < 2)) {
            heart_count += 1;
            brick_color[i][j] = health;
          }
          else {
            brick_color[i][j] = dark_green;
          }
        }
        if (color == 1) {
          if (((heart == 39) || (heart == 38)) && (heart_count < 2)) {
            heart_count += 1;
            brick_color[i][j] = health;
          }
          else {
            brick_color[i][j] = green;
          }
        }
        if (color == 2) {
          if (((heart == 39) || (heart == 38)) && (heart_count < 2)) {
            heart_count += 1;
            brick_color[i][j] = health;
          }
          else {
            brick_color[i][j] = light_green;
          }
        }
        if (color == 3) {
          if (((heart == 39) || (heart == 38)) && (heart_count < 2)) {
            heart_count += 1;
            brick_color[i][j] = health;
          }
          else {
            bComp.brick_num += 1;
            brick_color[i][j] = Color.BLACK;
            brick_dead[i][j] = true;
          }
        }
      }
    }
  }
}
