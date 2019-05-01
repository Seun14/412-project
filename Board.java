import java.util.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel
  implements ActionListener {
  
  private final int B_WIDTH = 1485;
  private final int B_HEIGHT = 756;
  private final int INITIAL_X = 75;
  private final int INITIAL_Y = 660;
  private final int DELAY = 25;
  
  private Image piece;
  private Image board;
  private Timer timer;
  private int x, y;
  Random rand = new Random();
  int a = rand.nextInt(10)+1;
  int b = rand.nextInt(5)+1;
  int c = rand.nextInt(10)+1;
  int d = a*b*c;
  String prompt = a + " * " + b+"x = " + d + " \n Solve for x";
  
  
  int lastSpace=0;
  int targetSpace=rand.nextInt(6)+1;
  int change=0;
  int answer=0;
  boolean answered = false;
  Scanner input = new Scanner(System.in);
  
  public Board() {
    
    initBoard();
  }
  
  private void loadImage() {
    ImageIcon i = new ImageIcon("WIPBoard.png");
    board = i.getImage();
    ImageIcon ii = new ImageIcon("piece.png");
    piece = ii.getImage();
    
  }
  
  private void initBoard() {
    
    
    setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
    
    loadImage();
    
    x = INITIAL_X;
    y = INITIAL_Y;
    
    timer = new Timer(DELAY, this);
    timer.start();
  }
  
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    drawBoard(g);
    drawPiece(g);
    drawText(g);
  }
  private void drawBoard(Graphics g) {
    
    g.drawImage(board, 0, 0, this);
    Toolkit.getDefaultToolkit().sync();
  }
  private void drawPiece(Graphics g) {
    
    g.drawImage(piece, x, y, this);
    Toolkit.getDefaultToolkit().sync();
  }
  private void drawText(Graphics g) {
    
    Graphics2D g2 = (Graphics2D)g;
    
    String roll = " " + targetSpace + " ";
    g2.drawString(roll,1190,280);
    g2.drawString(prompt,353,220);
    Toolkit.getDefaultToolkit().sync();
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    System.out.println(answered + " " + targetSpace + " " + lastSpace);
    boolean move1= false;
    boolean move2= false;
    boolean move3= false;
    boolean move4= false;
    if(answered==false){
      answer= input.nextInt();
      if(answer==c)
        answered=true;
      else
        prompt = prompt + "\n Wrong! Try again!";
    }
    if(answered==true){
      if(change>=150){
        lastSpace++;
        change=0;
      }
      if(lastSpace==targetSpace){
        targetSpace=rand.nextInt(6)+1;
        lastSpace=0;
        change=0;
        answered = false;
        a = rand.nextInt(10)+1;
        b = rand.nextInt(5)+1;
        c = rand.nextInt(10)+1;
        d = a*b*c;
        prompt = a + " * " + b+"x = " + d + " \n Solve for x";
      }  
      if(x==75){
        move1=true;
        move4=false;
      }
      if(y==80){
        move2=true;
        move1=false;
      }
      if(x==1445){
        move3=true;
        move2=false;
      }
      if(y==675){
        move4=true;
        move3=false;
      }
      if(move1==true){
        y -= 1;
        //System.out.println("1");
        change++;
      }
      
      if(move2==true){
       // System.out.println("2");
        x += 1;
        change++;
      }
      if(move3==true){
       // System.out.println("3");
        y += 1;
        change++;
      }
      if(move4==true){
      //  System.out.println("4"); 
        x -= 1;
        change++;
      }
    }
    
    //System.out.println(answered);
    
    repaint();
  }
}