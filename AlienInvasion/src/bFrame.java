import javax.swing.JFrame;


public class bFrame
{
  public bFrame() {}
  
  public static void main(String[] args)
  {
    JFrame j1 = new JFrame();
    j1.setTitle("Breakout: The Game");
    j1.setDefaultCloseOperation(3);
    j1.setSize(1000, 650);
    j1.setLocationRelativeTo(null);
    
    j1.add(new bComp());
    
    j1.setVisible(true);
  }
}
