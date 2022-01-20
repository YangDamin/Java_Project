import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
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
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class MenuView extends JFrame{
   
   JScrollPane menuList_Scroll;
   JTable table;
   JLabel rname;
   String category, phone;
   
   Vector<Vector> out;
   Vector<String> in, title;
   ArrayList<String> storename = new ArrayList<String>();
   
   Connection conn = null;
   String user = "scott";
   String pw = "tiger";
   String url = "jdbc:oracle:thin:@localhost:1521:orcl";
   Statement stat, statStore;
   
   ResultSet rs, rs_pnum;
   ResultSetMetaData rsmd;
   
   public MenuView(){
      display();
      this.setTitle("������ ��");
      this.setLayout(null);      
      this.setSize(650, 650);
      
      //����ȭ�� �߾ӿ� ��ġ�ϰ�
      Dimension frameSize = this.getSize();   //������(�ڹ� ȭ��) ũ��
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();   //����� ũ��
      this.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
         
      
      this.setVisible(true);
   }
   
   
   //=============================================================================================================
   public void display() {
      //��Ʈ
      Font f_bold = new Font("���� �߳�ü", Font.BOLD, 30);
      Font f_menu = new Font("���� �߳�ü", Font.PLAIN, 15);
      Font f_plain = new Font("���� �߳�ü", Font.PLAIN, 13);
      
      JPanel contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
       contentPane.setLayout(null);
       contentPane.setBackground(Color.WHITE);
      
       
       JPanel panel = new JPanel();
       panel.setBounds(0, 0, 650, 80);
       panel.setBackground(Color.WHITE);
       contentPane.add(panel);
      
      
       //�ڷΰ��� ��ư
       ImageIcon icon2 = new ImageIcon("�̹���/�ڷ�.png");
        JButton btnBack = new JButton("�ڷΰ���", icon2);
        btnBack.setBounds(10, 10, 110, 30);
        btnBack.setBackground(new Color(0xDCDCDC));
        btnBack.setFont(f_plain);
        backButton(btnBack);
        add(btnBack);
        
      
      //�Ĵ� �̸�
      rname = new JLabel(Restaurant.storename);        // * Restaurant ��ư�� �̸� �������� �ɵ�    
      panel.add(rname);
      rname.setFont(f_bold);
      
      panel.setBackground(Color.white);
      
      // �޴���
      // - �޴� ����Ʈ
      JPanel panel2 = new JPanel();
      panel2.setBounds(0, 80, 650, 550);
      contentPane.add(panel2);
      panel2.setBackground(Color.WHITE);
      
      title = new Vector<String>();
      out = new Vector<Vector>();
      
      title.add("�޴�");
      title.add("����");
      
      getData();
      
      
      DefaultTableModel model = new DefaultTableModel(out,title) {
         public boolean isCellEditable(int rowIndex, int mColIndex) {
            return false;
         }
      };      // ���̺� �� ���� ���ϰ�
      
      JTable table = new JTable(model);
      table.getTableHeader().setReorderingAllowed(false);      // ���̺� ��� �������̰�
      table.setRowHeight(25);
      table.setFont(f_plain);
      
      // ���̺� ��� ��Ʈ
      JTableHeader Theader = table.getTableHeader();
      Theader.setBackground(new Color(0xF5D08A));
      Theader.setFont(f_menu);
      
      menuList_Scroll = new JScrollPane(table);
      menuList_Scroll.setPreferredSize(new Dimension(500,450));
      panel2.add(menuList_Scroll);
      
      
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      
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
            
            String cName=rname.getText();
            int len=cName.length()-1;
            cName=cName.substring(1, len);
            
            String query = "select menu,price from fulllist where sid in (select sid from storelist where store ='"+cName+"')";
            
            
            rs = stat.executeQuery(query);      // ������ �����ϸ� ����� ��ȯ
            rsmd = rs.getMetaData();

            
            // �޴� ���
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
   //�ڷΰ��� ��ư Ŭ�� �̺�Ʈ
   public void backButton(JButton btnBack) {         
      btnBack.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            new Restaurant();
            setVisible(false);
         }
      });
   }
   
}