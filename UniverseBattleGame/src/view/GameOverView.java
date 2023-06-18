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

public class GameOverView extends JFrame {

	private JPanel contentPane;
    private Image backgroundImage;	//배경이미지
	private JLabel titleLabel;		//제목이미지
	private JButton returnBtn;		//돌아가기 버튼 -> StartView로 이동

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameOverView frame = new GameOverView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GameOverView() {
        setTitle("Universe Battle Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 780, 600);
		backgroundImage = new ImageIcon(getClass().getResource("/images/universe.jpg")).getImage();		
		contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };        
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
        
		//패배 텍스트
        titleLabel = new JLabel("YOU LOSE...");
        titleLabel.setForeground(new Color(210, 210, 210));
        titleLabel.setFont(new Font("Baskerville Old Face", Font.BOLD | Font.ITALIC, 70));
        titleLabel.setBounds(178, 172, 434, 105);
        contentPane.add(titleLabel);
        
        //돌아가기 버튼
        returnBtn = new JButton("RETURN");
        returnBtn.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
        returnBtn.setBounds(300, 420, 160, 50);
        contentPane.add(returnBtn);
        returnBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                AudioView.audio.play(101);
    			new StartView().setVisible(true);
    			setVisible(false);
        	}
        });
        
        //배경음악
		AudioView.audio.play(3);
	}
}
