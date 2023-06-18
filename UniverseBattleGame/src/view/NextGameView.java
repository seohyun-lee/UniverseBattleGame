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
    private Image backgroundImage;	//배경이미지
	private JLabel titleLabel;		//제목이미지	
    private JButton continueBtn;	//계속하기 버튼 -> SelectPlayerView로 이동
    private JButton returnBtn;		//돌아가기 버튼 -> StartView로 이동

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

	public NextGameView() {
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

		//승리 텍스트
		titleLabel = new JLabel("YOU WIN!!!");
		titleLabel.setForeground(new Color(255, 255, 255));
		titleLabel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 80));
		titleLabel.setBounds(173, 158, 451, 105);
		contentPane.add(titleLabel);

		//다음 몬스터 깨기
		SelectPlayerView.monCnt++;
		
		//계속하기
		continueBtn = new JButton("CONTINUE");
		continueBtn.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		continueBtn.setBounds(300, 350, 160, 50);
		contentPane.add(continueBtn);
		continueBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		AudioView.audio.play(101);
        		new SelectPlayerView().setVisible(true);
        		setVisible(false);
        	}
    	});
        
		//돌아가기
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
		AudioView.audio.play(4);
	}
}
