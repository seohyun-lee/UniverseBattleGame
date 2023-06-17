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

public class GameClearView extends JFrame {

	private JPanel contentPane;
    private Image backgroundImage; //배경이미지

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameClearView frame = new GameClearView();
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
	public GameClearView() {
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
		
		//글자 이미지
		JLabel title = new JLabel();
		title.setBounds(140, 70, 600, 100);
		title.setIcon(new ImageIcon("./src/images/gameclear.png"));
        contentPane.add(title);
        
        //musicians 이미지
        JLabel image = new JLabel();
        image.setBounds(55, 170, 620, 270);
        image.setIcon(new ImageIcon("./src/images/musicians.png"));
        contentPane.add(image);
		
        JButton returnbtn = new JButton("RESET");
        returnbtn.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
        returnbtn.setBounds(300, 450, 160, 50);
        contentPane.add(returnbtn);
        returnbtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                BackgroundMusic.music.play(101);
    			new StartView().setVisible(true);
    			setVisible(false);
    	        //몬스터 번호 초기화
    	        SelectPlayerView.monCnt=1;
        	}
        });        

        //배경음악
		BackgroundMusic.music.play(5);
	}
}
