import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class MainPage extends JFrame{
   
   static public final int FRAME_WIDTH = 600;
   static public final int FRAME_HEIGHT = 500; 
   
   static public String category;
   
   JPanel panel = new JPanel();

   
   public MainPage() {
      display();
      this.setTitle("������ ��");      // Ÿ��Ʋ ����
      
      //����ȭ�� �߾ӿ� ��ġ�ϰ�
      Dimension frameSize = this.getSize();   //������(�ڹ� ȭ��) ũ��
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();   //����� ũ��
      this.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
      
      
      
      this.setVisible(true);
      
   }
   
   //=============================================================================================================
   public void display() {
      Font f2 = new Font("���� �߳�ü",Font.BOLD,15);
      Font f1 = new Font("���� �߳�ü",Font.BOLD,13);
      
      
      JLabel lb1 = new JLabel("-------------------------������ ��-------------------------");     
      lb1.setForeground(Color.WHITE);
      lb1.setFont(f2);
      
      //������ �ҷ�����
      ImageIcon icon = new ImageIcon("�̹���/�ѽ�.png");
      JButton bt1 = new JButton("�ѽ�",icon); 
      bt1.setPreferredSize(new Dimension(110,50));
      setButton(bt1, f1);
      clickBtn(bt1);      // ��ưŬ���޼ҵ�
        
      
      
      ImageIcon icon2 = new ImageIcon("�̹���/�Ͻ�.png");   
      JButton bt2 = new JButton("�Ͻ�",icon2); 
      bt2.setPreferredSize(new Dimension(110,50));
      setButton(bt2, f1);
      clickBtn(bt2);
        
      ImageIcon icon3 = new ImageIcon("�̹���/�߽�.png");   
      JButton bt3 = new JButton("�߽�",icon3);  
      bt3.setPreferredSize(new Dimension(110,50));
      setButton(bt3, f1);
      clickBtn(bt3);
         
      ImageIcon icon4 = new ImageIcon("�̹���/���.png");   
      JButton bt4 = new JButton("���",icon4); 
      bt4.setPreferredSize(new Dimension(110,50));
      setButton(bt4, f1);
      clickBtn(bt4);
         
      ImageIcon icon5 = new ImageIcon("�̹���/ī��.png");
      JButton bt5 = new JButton("ī��",icon5);    
      bt5.setPreferredSize(new Dimension(110,50));
      setButton(bt5, f1);
      clickBtn(bt5);
         
      ImageIcon icon6 = new ImageIcon("�̹���/���ö�.png");
      JButton bt6 = new JButton("���ö�", icon6);     
      bt6.setPreferredSize(new Dimension(110,50));
      setButton(bt6, f1);
      clickBtn(bt6);
      
      panel.setBackground(new Color(0xF5D08A));
      setSize(400,200);
      
      
      panel.add(lb1);
      panel.add(bt1);panel.add(bt2);
      panel.add(bt3);panel.add(bt4);
      panel.add(bt5);panel.add(bt6);
      add(panel);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
   }
   
   //=============================================================================================================
   // ī�װ� ��ư Ŭ�� �̺�Ʈ
   public void clickBtn(JButton btn) {
      btn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            JButton b = (JButton)e.getSource();
            category = b.getText();
            new Restaurant();
            setVisible(false);
         }
      });
   }
   
   //=============================================================================================================
   // ��ư�� ����, ��Ʈ��, ��Ʈ����
   public void setButton(JButton btn, Font f2) {
      btn.setBackground(Color.WHITE);
      btn.setForeground(Color.BLACK);
      
      btn.setFont(f2);
   }
   
  }