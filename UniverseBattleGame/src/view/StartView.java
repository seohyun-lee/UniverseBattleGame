package view;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;//글씨쓰기
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class StartView extends JFrame {
	
	private JPanel contentPane;
    private Image backgroundImage;	//배경이미지
	private JLabel titleLabel;		//제목이미지
	
	private JButton startbtn;		//시작버튼<-SelectPlayerView()를 visible시킨다.
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartView frame = new StartView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StartView() {
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
		contentPane.setLayout(null); //배치 관리자를 삭제<-컴포넌트를 Absolute Layout으로 배치하고자.
		setLocationRelativeTo(null); //JFrame을 화면 정중앙에 배치

		//제목이미지
        titleLabel = new JLabel();
        titleLabel.setBounds(200, 110, 390, 260);
        titleLabel.setIcon(new ImageIcon(getClass().getResource("/images/title.png")));	//상대 경로 사용(중요)
        contentPane.add(titleLabel);
        
		//시작버튼
        startbtn = new JButton("START");
        startbtn.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
        startbtn.setBounds(300, 420, 160, 50);
        contentPane.add(startbtn);
        startbtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                AudioView.audio.play(101);	//클릭 효과음 재생
    			new SelectPlayerView().setVisible(true);
    			setVisible(false);
        	}
        });
        
        //배경음악 창을 게임 창의 바로 아래쪽에 띄우기 위해 contentPane의 x, y값을 받아와서 사용했다.
		AudioView.audio.setLocation(contentPane.getX()+645, contentPane.getY()+675);
		AudioView.audio.setVisible(true);
		AudioView.audio.play(0);	//배경음악(intro_music) 재생
	}

}
