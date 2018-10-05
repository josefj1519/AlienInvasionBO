import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;










public class bComp
  extends GameDriverV3
  implements KeyListener
{
  Font[] text = new Font[4];
  SoundDriver s1;
  static boolean selected;
  Rectangle bg = new Rectangle(0, 0, 1000, 650);
  Rectangle death = new Rectangle(0, 648, 1000, 1);
  Rectangle ui = new Rectangle(0, 0, 1000, 50);
  
  boolean explosion;
  
  ArrayList<Rectangle> bullet0 = new ArrayList();
  
  animation a1 = new animation();
  Effects particle;
  static paddle paddle0;
  menuSelector select;
  bricks[] brick0 = new bricks[500];
  ball ball0;
  boolean fire;
  boolean isSpace;
  static boolean dead;
  int gameState;
  int xpos;
  int ypos;
  static int score;
  static int lvl;
  static int brick_num; static int lives; int bullet_num; static Color c0 = Color.GREEN;
  
  public bComp()
  {
    selected = false;
    addKeyListener(this);
    gameState = 0;
    explosion = false;
    
    text[0] = new Font("Monospaced", 1, 60);
    text[1] = new Font("Monospaced", 0, 30);
    text[2] = new Font("Monospaced", 2, 30);
    text[3] = new Font("Monospaced", 0, 20);
    
    String[] str = new String[2];
    str[0] = "woosh.wav";
    str[1] = "xfile2.wav";
    s1 = new SoundDriver(str);
    
    if (gameState == 0) {
      s1.loop(1);
    }
    
    bullet_num = 6;
    xpos = 450;ypos = 585;lives = 3;
    dead = false;fire = false;isSpace = false;
    select = new menuSelector(38, 40);
    addKeyListener(select);
    score = 0;
    brick_num = 0;
    lvl = 0;
  }
  


  public void standardReset()
  {
    paddle0 = new paddle(xpos, ypos, 65, 68);
    ball0 = new ball();
    addKeyListener(paddle0);
    gameState = 1;
    bullet0.clear();
    bullet_num = 6;
  }
  



  public void draw(Graphics2D win)
  {
    win.setColor(Color.BLACK);
    win.fill(bg);
    

    if ((select.count() == 0) && (animation.y < 64861) && (gameState == 0)) {
      lives = 3;
      bullet_num = 6;
      lvl = 0;
      brick_num = 0;
      brick0[lvl] = new bricks();
      standardReset();
      animation.y = 65336;
    }
    if ((select.count() == 1) && (animation.y < 64861) && (gameState == 0))
    {
      lives = 3;
      bullet_num = 6;
      lvl = 1;
      brick_num = 0;
      brick0[lvl] = new bricks();
      standardReset();
      animation.y = 65336;
    }
    if ((select.count() == 2) && (animation.y < 64861) && (gameState == 0)) {
      gameState = 2;
      animation.y = 65336;
    }
    



    if (gameState == 0) {
      win.setColor(c0);
      
      select.moveAndDraw(win);
      win.setFont(text[0]);
      win.drawString("Breakout: Alien Invasion", 60, 75);
      win.setFont(text[1]);
      win.drawString("Standard Game", 370, 525 + animation.y);
      win.drawString("Random Demo", 385, 575 + animation.y);
      win.drawString("Instructions", 377, 625 + animation.y);
      win.drawString("Press ENTER to enter a mode", 50, 575);
      a1.moveAndDraw(win);
    }
    

    if ((gameState == 1) && (!dead) && (!selected))
    {

      win.setColor(Color.GRAY);
      win.draw(ui);
      win.setColor(Color.BLACK);
      win.fill(death);
      
      brick0[lvl].moveAndDraw(win);
      paddle0.moveAndDraw(win);
      ball0.moveAndDraw(win);
      bullets(win);
      



      getIntersection();
      brickIntersection(win);
      if (explosion) {
        particle.moveAndDraw(win);
      }
      score(win);
      numOfLives(win);
    }
    



    if (dead) {
      isDead(win);
    }
    


    if ((gameState == 2) && (!selected)) {
      win.setFont(text[0]);
      win.setColor(c0);
      win.drawString("WELCOME!", 25, 50);
      win.setFont(text[1]);
      win.drawString("This is Breakout: Alien Invasion!", 30, 100);
      win.setFont(text[3]);
      win.drawString("To win destroy all UFOs (bricks).  Use the giant baseball bat (paddle)", 30, 150);
      win.drawString("to hit the alien bomb (ball) away from the earth. You also have a ballistic", 30, 180);
      win.drawString("misiles to shoot at them aliens! However, don't let those aliens get too close", 30, 210);
      win.drawString("to earth!", 30, 240);
      win.setFont(text[1]);
      win.drawString("Shooting: Space  Paddle: Left=A Right=D", 30, 280);
      win.drawString("Leave the game anytime with Backspace", 30, 310);
      win.setColor(bricks.dark_green);
      win.drawString("DARK GREEN = 3 HITS", 30, 390);
      win.setColor(bricks.green);
      win.drawString("GREEN = 2 HITS", 30, 420);
      win.setColor(bricks.light_green);
      win.drawString("LIGHT GREEN = 1 HIT", 30, 450);
      win.setColor(Color.RED);
      win.drawString("RED = LIFE", 30, 480);
      win.setColor(c0);
      win.drawString("Press BACKSPACE to get back to the menu", 50, 575);
    }
  }
  
  public void getIntersection()
  {
    if (ball0.intersects(paddle0)) {
      int direction = collisionDirection(paddle0, ball0, ball.dx, ball.dy);
      if ((direction == 0) || (direction == 2)) {
        ball.dx = -ball.dx;
      }
      else {
        ball.dy = -ball.dy;
      }
    }
  }
  
  public void brickIntersection(Graphics2D win)
  {
    for (int i = 0; i < bricks.sBrick.length; i++) {
      for (int j = 0; j < bricks.sBrick[i].length; j++) {
        if (brick_num == 162) {
          lvl += 1;
          brick_num = 0;
          brick0[lvl] = new bricks();
          
          standardReset();
        }
        
        if (ball0.intersects(bricks.sBrick[i][j]))
        {
          int direction = collisionDirection(bricks.sBrick[i][j], ball0, ball.dx, ball.dy);
          
          if (((direction == 0) || (direction == 2)) && (bricks.brick_dead[i][j] == false)) {
            checkColor(i, j, win);
            ball.dx = -ball.dx;
          }
          else if (bricks.brick_dead[i][j] == false) {
            checkColor(i, j, win);
            
            ball.dy = -ball.dy;
          }
        }
      }
    }
  }
  




  public int collisionDirection(Rectangle stationary, Rectangle projectile, int dx, int dy)
  {
    int previousXPos = (int)projectile.getX() - dx;
    int previousYPos = (int)projectile.getY() - dy;
    int height = (int)projectile.getHeight();
    int width = (int)projectile.getWidth();
    int result = 0;
    
    if ((previousYPos + height <= stationary.getY()) && (projectile.getY() + height >= stationary.getY()))
    {
      result = 1;
    } else if ((previousXPos + width <= stationary.getX()) && (projectile.getX() + width >= stationary.getX()))
    {
      result = 2;
    } else if ((previousYPos >= stationary.getY() + height) && (projectile.getY() <= stationary.getY() + height))
    {
      result = 3;
    }
    

    return result;
  }
  




  public int numOfLives(Graphics2D win)
  {
    if (ball0.intersects(death)) {
      lives -= 1;
      if (lives == 0) {
        dead = true;
      }
      standardReset();
    }
    if (lives == 0) {
      dead = true;
    }
    return lives;
  }
  
  public void isDead(Graphics2D win)
  {
    win.setColor(Color.BLACK);
    win.fill(bg);
    win.setColor(c0);
    win.setFont(text[0]);
    win.drawString("Earth is Destroyed", 50, 100);
    win.drawString("Your score was " + score, 50, 200);
    String level = "levels";
    if (lvl == 0) {
      level = "level";
    }
    win.drawString("You went through " + (lvl + 1) + " " + level, 50, 300);
    win.setFont(text[1]);
    win.drawString("Press BACKSPACE to get back to the menu", 50, 575);
  }
  


  public void bullets(Graphics2D win)
  {
    if (bullet_num > 6) {
      bullet_num = 6;
    }
    
    if ((isSpace) && 
      (bullet_num > 0)) {
      bullet0.add(new Rectangle((int)paddle0.getX() + 40, (int)paddle0.getY(), 10, 10));
      bullet_num -= 1;
      isSpace = false;
    }
    

    for (int i = 0; i < bullet0.size(); i++) {
      ((Rectangle)bullet0.get(i)).translate(0, -2);
      
      if (bullet_num < 0) {
        bullet_num = 0;
      }
      win.setColor(c0);
      win.draw((Shape)bullet0.get(i));
    }
    

    for (int i = 0; i < bullet0.size(); i++) {
      for (int x = 0; x < bricks.sBrick.length; x++) {
        for (int y = 0; y < bricks.sBrick[0].length; y++)
        {
          if (((((Rectangle)bullet0.get(i)).intersects(bricks.sBrick[x][y])) || (((Rectangle)bullet0.get(i)).getY() < 0.0D)) && (
            (bricks.brick_dead[x][y] == false) || (((Rectangle)bullet0.get(i)).getY() < 0.0D))) {
            if (bricks.brick_dead[x][y] == false) {
              checkColor(x, y, win);
            }
            bullet0.remove(i);
            
            bullet_num += 1;
            y = bricks.sBrick[x].length;
            x = bricks.sBrick.length;
            i = bullet0.size();
          }
        }
      }
    }
  }
  







  public void score(Graphics2D win)
  {
    String speed = "Turtle";
    win.setColor(bricks.light_green);
    win.setFont(text[1]);
    win.drawString("LIVES:" + lives, 25, 35);
    if (bricks.time > 8000) {
      speed = "SANIC";

    }
    else if (bricks.time > 6000) {
      speed = "Kinda Fast";

    }
    else if (bricks.time > 4000) {
      speed = "Jogging";
    }
    else if (bricks.time > 2000)
      speed = "Prius";
    win.drawString("BULLETS: ", 190, 35);
    win.drawString("SCORE:" + score, 390, 35);
    win.drawString("SpeedLVL:" + speed, 567, 35);
    String s = "Infin.";
    



    win.drawString(s, 340, 35);
  }
  










  public void keyPressed(KeyEvent e)
  {
    if (e.getKeyCode() == 10) {
      selected = true;
      if (gameState == 0) {
        if (select.count() == 0) {
          selected = true;
        }
        



        if (select.count() == 1) {
          selected = true;
        }
        



        if (select.count() == 2) {
          selected = true;
        }
      }
    }
    




    if (e.getKeyCode() == 32)
    {



      isSpace = true;
    }
    
    if (e.getKeyCode() == 8) {
      gameState = 0;
      dead = false;
      score = 0;
    }
  }
  



  public void keyReleased(KeyEvent e)
  {
    e.getKeyCode();
  }
  








  public void keyTyped(KeyEvent e) {}
  








  public void checkColor(int i, int j, Graphics2D win)
  {
    if (bricks.dark_green.equals(bricks.brick_color[i][j])) {
      bricks.brick_color[i][j] = bricks.green;
      
      score += 1;
    }
    else if (bricks.green.equals(bricks.brick_color[i][j])) {
      bricks.brick_color[i][j] = bricks.light_green;
      score += 1;
    }
    else {
      if (bricks.health.equals(bricks.brick_color[i][j])) {
        lives += 1;
      }
      bricks.brick_color[i][j] = Color.BLACK;
      
      particle = new Effects((int)bricks.sBrick[i][j].getX(), (int)bricks.sBrick[i][j].getY());
      explosion = true;
      bricks.brick_dead[i][j] = true;
      brick_num += 1;
      score += 1;
    }
  }
}
