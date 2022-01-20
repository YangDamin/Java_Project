import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Random;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Restaurant extends JFrame{
   
   static String category;
   static String storename;
   static String randomText;
   
   
   Vector<Vector> out;
   Vector<String> in, title;
   
   Connection conn = null;
   String user = "scott";
   String pw = "tiger";
   String url = "jdbc:oracle:thin:@localhost:1521:orcl";
   Statement stat;
   
   
   
   public Restaurant() {
      this.category = MainPage.category;
      display();
      this.setTitle("������ ��");
      this.setSize(MainPage.FRAME_WIDTH, MainPage.FRAME_HEIGHT);
      
      //����ȭ�� �߾ӿ� ��ġ�ϰ�
      Dimension frameSize = this.getSize();   //������(�ڹ� ȭ��) ũ��
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();   //����� ũ��
      this.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
      
      this.setVisible(true);
   }
   
   
   //=============================================================================================================
   public void display() {
      
      
      Font f_bold = new Font("���� �߳�ü", Font.BOLD, 30);
      Font f_plain = new Font("���� �߳�ü", Font.PLAIN, 13);
      Font f_plain2 = new Font("���� �߳�ü", Font.PLAIN, 12);
      
      JPanel contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
       contentPane.setLayout(null);
       contentPane.setBackground(Color.WHITE);
      
       
       JPanel panel = new JPanel();
       panel.setBounds(0, 0, 600, 81);
       contentPane.add(panel);

      //�ڷΰ��� ��ư
       ImageIcon icon2 = new ImageIcon("�̹���/�ڷ�.png");
      JButton btnBack = new JButton("�ڷΰ���", icon2);
      btnBack.setBounds(10, 10, 110, 30);
      btnBack.setBackground(new Color(0xDCDCDC));
      btnBack.setFont(f_plain);
      backButton(btnBack);
      add(btnBack);
      
      
//    ImageIcon img = new ImageIcon("image/�ѽ�.png");      

    //����ī�װ� �̸�
//    JLabel rname = new JLabel(this.category, img, SwingConstants.CENTER);
//    rname.setFont(f_bold);
//    panel.add(rname);
    
      if(this.category.equals("�ѽ�")) {
          ImageIcon img = new ImageIcon("�̹���/�ѽ�1.png"); 
          JLabel rname = new JLabel( img, SwingConstants.CENTER);
           rname.setFont(f_bold);
           panel.add(rname);
       }else if(this.category.equals("�Ͻ�")) {
          ImageIcon img = new ImageIcon("�̹���/�Ͻ�1.png"); 
          JLabel rname = new JLabel( img, SwingConstants.CENTER);
           rname.setFont(f_bold);
           panel.add(rname);
       }else if(this.category.equals("�߽�")) {
          ImageIcon img = new ImageIcon("�̹���/�߽�1.png"); 
          JLabel rname = new JLabel( img, SwingConstants.CENTER);
           rname.setFont(f_bold);
           panel.add(rname);
       }else if(this.category.equals("���")) {
          ImageIcon img = new ImageIcon("�̹���/���1.png"); 
          JLabel rname = new JLabel( img, SwingConstants.CENTER);
           rname.setFont(f_bold);
           panel.add(rname);
       }else if(this.category.equals("ī��")) {
          ImageIcon img = new ImageIcon("�̹���/ī��1.png"); 
          JLabel rname = new JLabel( img, SwingConstants.CENTER);
           rname.setFont(f_bold);
           panel.add(rname);
       }else {
          ImageIcon img = new ImageIcon("�̹���/���ö�1.png"); 
          JLabel rname = new JLabel( img, SwingConstants.CENTER);
           rname.setFont(f_bold);
           panel.add(rname);
       }
      
            
      //����ī�װ� �̸�
     
      JLabel rname = new JLabel(this.category);
      rname.setFont(f_bold);
      panel.add(rname);
      panel.setBackground(Color.WHITE);
      
      
      
      
      //�Ĵ� ��ư - �Ĵ� �������� �� ���
      JPanel panel2 = new JPanel();
      panel2.setBounds(0, 85, 600, 300);
      contentPane.add(panel2);
      panel2.setBackground(Color.WHITE);
      
      
      
      // �Ĵ� ��ư - DB ����
      out = new Vector<Vector>();
      getData();
      JButton[] store = new JButton[out.size()];      // * �����ͺ��̽��� �ִ� �Ĵ�� ������ ������ ��
      
      for(int i=0; i<store.length; i++) {
         Vector name = out.get(i);
         store[i] = new JButton(name.toString());   // * �����ͺ��̽��� �ִ� �Ĵ�� �̸����� �ٲٱ�
         store[i].setPreferredSize(new Dimension(150,50));
         store[i].setBackground(new Color(0xF5D08A));
         store[i].setFont(f_plain2);
         panel2.add(store[i]);
         storeButton(store[i]);
      }
      
      // �Ĵ� ���� ��õ 
      JPanel panel3 = new JPanel();
       panel3.setBounds(0, 380, 600, 40);
       contentPane.add(panel3);
       
       ImageIcon icon = new ImageIcon("�̹���/����.png");
       JButton btnRand = new JButton("�Ĵ� ���� ��õ", icon);
       btnRand.setBackground(new Color(0xfeccc9));
       btnRand.setFont(f_plain);
       btnRand.setForeground(Color.WHITE);
       panel3.add(btnRand);
       panel3.setBackground(Color.WHITE);
       
       randomButton(btnRand, store);
       
       
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       
       
       // db ���� ����
       addWindowListener(new WindowAdapter() {
            private void windowClosing() {
               try {
                  stat.close();
                  conn.close();
                  
                  setVisible(false);
                  dispose();
                  System.exit(0);
               }catch(Exception e) {
                  
               }
            }
         });
         pack();
         setVisible(true);

   }
   
    //=============================================================================================================
    // DB �����͸� ������ out ���Ϳ� �ֱ�
   public void getData() {
       try {
          Class.forName("oracle.jdbc.driver.OracleDriver");
          conn = DriverManager.getConnection(url, user, pw);
          
          stat = conn.createStatement();
          
          String query = "select store from storelist where sid in (select sid from fulllist where cid in (select cid from categorylist where category='" + this.category + "'))";
                   
          ResultSet rs = stat.executeQuery(query);      // ������ �����ϸ� ����� ��ȯ
          ResultSetMetaData rsmd = rs.getMetaData();
         
         
          // MenuView
          while(rs.next()) {
              in = new Vector<String>();
              for(int i=1; i<= rsmd.getColumnCount(); i++) {
                 in.add(rs.getString(i));
              }
              out.add(in);
           }
       }catch(Exception e) {
          e.printStackTrace();
       }
    }
   
   //=============================================================================================================
   //�Ĵ� ���� ��õ ��ư �̺�Ʈ
   public void randomButton(JButton btnRandom, JButton[] store2) {
      btnRandom.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            Random rnd = new Random();
            int cnt = rnd.nextInt(store2.length);   // �Ĵ� ������ ���� ���� ���� ����
            storename = store2[cnt].getText();
            new MenuView();
            setVisible(false);
            
         }
      });
      
      
   }
   
   
   //=============================================================================================================
   //�ڷΰ��� ��ư Ŭ�� �̺�Ʈ
   public void backButton(JButton btnBack) {         
      btnBack.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            new MainPage();
            setVisible(false);
         }
      });
   }

   
   //=============================================================================================================
   // �Ĵ� ��ư Ŭ�� �̺�Ʈ
   public void storeButton(JButton btnStore) {
      btnStore.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            JButton b = (JButton)e.getSource();
            storename = b.getText();
            new MenuView();
            setVisible(false);
         }
      });
   }
   
}