package groupchattingapplication;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.net.*;

public class User3 extends JFrame implements ActionListener, Runnable{
    JPanel p1;
    JTextField t1;
    JButton b1,b2;
    static JTextArea a1;
    
    BufferedWriter writer;
    BufferedReader reader;
    
//    static Socket s;
//    static DataInputStream Din;
//    static DataOutputStream Dout;

    User3() {
        
//        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("C:\\Users\\#Priyanshu Palliwal\\Desktop\\chatapp\\ChatApplication\\src\\chatapplication\\icons\\3.png"));
//        Image i2 = i1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
//        ImageIcon i3 = new ImageIcon(i2);
//        JLabel l1 = new JLabel(i3);
//        l1.setBounds(5, 17, 30, 30);
//        add(l1);
        setSize(450, 700);
        setLocation(655,30);
        getContentPane().setBackground(Color.white);
        
        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(Color.cyan);
        p1.setBounds(0,0,450,70);
        add(p1);
        
//        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("chatapplication/icons/3icon.png"));
//        Image i2 = i1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
//        ImageIcon i3 = new ImageIcon(i2);
//        JLabel l4 = new JLabel(i3);
//        l4.setBounds(5, 17, 30, 30);
//        p1.add(l4);

        t1 = new JTextField();
        t1.setBounds(5, 640, 280, 50);
        t1.setFont(new Font("SAN_SERIF",Font.PLAIN,18));
        add(t1);
        
        b1 = new JButton("Send");
        b1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        b1.setBackground(Color.CYAN);
        b1.setBounds(290, 640,75, 50);
        b1.addActionListener(this);
        add(b1);
        
        b2 = new JButton("Close");
        b2.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        b2.setBackground(Color.RED);
        b2.setForeground(Color.white);
        b2.setBounds(370,640,75,50);
        add(b2);
        
        a1 = new JTextArea();
        a1.setBounds(5, 75, 440, 560);
        a1.setBackground(Color.pink);
        a1.setFont(new Font("SAN_SERIF",Font.PLAIN,18));
        a1.setEditable(false);
        a1.setLineWrap(true);
        a1.setWrapStyleWord(true);
        add(a1);
        
        b2.addMouseListener(new MouseAdapter(){
           public void mouseClicked(MouseEvent ae){
               System.exit(0);
           }
        });
                
        JLabel l1,l2; 
        l1=new JLabel("H       Group Chat...");
        l1.setFont(new Font("SAN_SERIF",Font.BOLD,40));
        l1.setBounds(10,5,440,70);  
        l2=new JLabel("");  
//        l2.setBounds(50,100,100,30);  
        p1.add(l1); 
        add(l2);
        l1.setLayout(null);
        setUndecorated(true);
        
        
        try{
            Socket SocketClient = new Socket("localhost", 2005);
            writer = new BufferedWriter(new OutputStreamWriter(SocketClient.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(SocketClient.getInputStream()));
        }catch(Exception e){
            
        }
    
}
    
public void actionPerformed(ActionEvent ae){
        String str = "Hrithik\n"+">>> "+t1.getText();
        try{
            writer.write(str);
            writer.write("\r\n");
            writer.flush();
        }catch(Exception e){}
        t1.setText("");
    }
    
    public void run(){
        try{
            String msg = "";
            while((msg = reader.readLine()) != null){
                a1.append(msg + "\n");
            }
        }catch(Exception e){}
    }
    
    public static void main(String[] args){
        User3 one = new User3();
        one.setVisible(true);
        Thread t1 = new Thread(one);
        t1.start();
    }
    
}
