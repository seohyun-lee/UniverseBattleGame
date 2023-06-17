package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class NextGameView extends JFrame {

	private JPanel contentPane;
    private Image backgroundImage; //배경이미지

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NextGameView frame = new NextGameView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NextGameView() {
        setTitle("Universe Battle Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 780, 600);		
		contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }

            }
        };		
		// 배경 이미지 설정
        String imagePath = "./src/images/universe.jpg";
        backgroundImage = new ImageIcon(imagePath).getImage();
        
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
        JLabel loseText = new JLabel("YOU WIN!!!");
        loseText.setForeground(new Color(255, 255, 255));
        loseText.setFont(new Font("Baskerville Old Face", Font.PLAIN, 80));
        loseText.setBounds(173, 158, 451, 105);
        contentPane.add(loseText);

        //다음 몬스터 깨기
        SelectPlayerView.monCnt++;
        
        JButton continuebtn = new JButton("CONTINUE");
        continuebtn.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
        continuebtn.setBounds(300, 350, 160, 50);
        contentPane.add(continuebtn);
        continuebtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                BackgroundMusic.music.play(101);
    			new SelectPlayerView().setVisible(true);
    			setVisible(false);
        	}
        });
        
        JButton returnbtn = new JButton("RETURN");
        returnbtn.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
        returnbtn.setBounds(300, 420, 160, 50);
        contentPane.add(returnbtn);
        returnbtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                BackgroundMusic.music.play(101);
    			new StartView().setVisible(true);
    			setVisible(false);
        	}
        });        

        //배경음악
		BackgroundMusic.music.play(4);
	}
}
