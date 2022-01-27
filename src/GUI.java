import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    GUI(){
        //Frame
        JFrame frame=new JFrame();
        frame.setSize(500, 500);
        frame.setTitle("F1 CHAMPIONSHIP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

        //Label
        JLabel label = new JLabel();
        frame.setLayout(new BorderLayout());
        ImageIcon image2 = new ImageIcon("f1.jpg");
        frame.setIconImage(image2.getImage());
        label.setIcon(image2);
        label.setSize(500, 500);
        frame.add(label);
        frame.setVisible(true);
        label.setVerticalAlignment(JLabel.TOP);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 4);
        label.setBorder(border);

        //Button
        JButton button=new JButton();
        button.setBounds(140,405,200,50);
        button.setText("FORMULA 1");
        button.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 20));
        button.setBackground(Color.RED);
        button.setForeground(Color.WHITE);
        button.setFocusable(false);
        //Action Listener to go to the next frame
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==button){
                    //closing the current frame
                    frame.dispose();
                    new GUI_Table();
                }
            }
        });
        frame.add(button);

        }

        public static void main(String[]args){
            new GUI();
        }


}
