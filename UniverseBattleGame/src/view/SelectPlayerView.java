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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import player.*;
import java.awt.Color;

public class SelectPlayerView extends JFrame {

	private JPanel contentPane;
    private Image backgroundImage;	//배경이미지
	private JLabel titleLabel;		//제목이미지
	
    private JButton btn1;			//singer 선택버튼
    private JButton btn2;			//guitarist 선택버튼
    private JButton btn3;			//bassist 선택버튼
    private JButton btn4;			//drummer 선택버튼
    private JButton btn5;			//배틀시작버튼

    public static int monCnt=1;		//첫번째 몬스터부터 플레이
    Musician p1;					//뮤지션 객체;
    Monster p2;						//괴물 객체;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectPlayerView frame = new SelectPlayerView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SelectPlayerView() {
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
				
		//제목이미지
        titleLabel = new JLabel();
        titleLabel.setBounds(215, 72, 341, 85);
        titleLabel.setIcon(new ImageIcon(getClass().getResource("/images/selectplayer.png")));
        contentPane.add(titleLabel);
        
        //버튼들
		btn1 = new JButton("SINGER");
		btn1.setForeground(new Color(255, 255, 255));
		btn1.setBackground(new Color(0, 0, 64));
		btn1.setFont(new Font("Onyx", Font.PLAIN, 33));
		btn1.setBounds(100, 180, 120, 200);
        contentPane.add(btn1);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AudioView.audio.play(102);
        		p1 = new Musician("singer");
        		btn1.setBackground(new Color(0, 0, 130));
        		btn2.setBackground(new Color(0, 0, 64));
        		btn3.setBackground(new Color(0, 0, 64));
        		btn4.setBackground(new Color(0, 0, 64));
        	}
        });

        btn2 =  new JButton("GUITARIST");
        btn2.setForeground(new Color(255, 255, 255));
        btn2.setBackground(new Color(0, 0, 64));
        btn2.setFont(new Font("Onyx", Font.PLAIN, 33));
        btn2.setBounds(250, 180, 120, 200);
        contentPane.add(btn2);
        btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AudioView.audio.play(102);
        		p1 = new Musician("guitarist");
                btn2.setBackground(new Color(0, 0, 130));
        		btn1.setBackground(new Color(0, 0, 64));
        		btn3.setBackground(new Color(0, 0, 64));
        		btn4.setBackground(new Color(0, 0, 64));
        	}
        });

        btn3 =  new JButton("BASSIST");
        btn3.setForeground(new Color(255, 255, 255));
        btn3.setBackground(new Color(0, 0, 64));
        btn3.setFont(new Font("Onyx", Font.PLAIN, 33));
        btn3.setBounds(400, 180, 120, 200);
        contentPane.add(btn3);
        btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AudioView.audio.play(102);
        		p1 = new Musician("bassist");
        		btn3.setBackground(new Color(0, 0, 130));
        		btn1.setBackground(new Color(0, 0, 64));
        		btn2.setBackground(new Color(0, 0, 64));
        		btn4.setBackground(new Color(0, 0, 64));
        	}
        });

        btn4 =  new JButton("DRUMMER");
        btn4.setBackground(new Color(0, 0, 64));
        btn4.setForeground(new Color(255, 255, 255));
        btn4.setFont(new Font("Onyx", Font.PLAIN, 33));
        btn4.setBounds(550, 180, 120, 200);
        contentPane.add(btn4);
        btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AudioView.audio.play(102);
        		p1 = new Musician("drummer");
        		btn4.setBackground(new Color(0, 0, 130));
        		btn1.setBackground(new Color(0, 0, 64));
        		btn2.setBackground(new Color(0, 0, 64));
        		btn3.setBackground(new Color(0, 0, 64));
        	}
        });

        //플레이어(btn1~btn4)중 하나도 선택하지 않고 배틀시작(btn5)을 클릭했을 때 뜨는 안내문구
        JLabel label = new JLabel("SELECT PLAYER FIRST!");
        label.setForeground(new Color(255, 128, 128));
        label.setFont(new Font("Baskerville Old Face", Font.PLAIN, 14));
        label.setBounds(308, 480, 162, 12);
        contentPane.add(label);
        label.setVisible(false);
        
        //배틀시작버튼
        btn5 = new JButton("LET'S BATTLE!!");
        btn5.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
        btn5.setBounds(300, 420, 172, 50);
        contentPane.add(btn5);        
        btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AudioView.audio.play(101);	//클릭 효과음 재생
				if(p1 != null) { // p1이 선택된 후에만 BattleView로 이동할 수 있다
					//monCnt의 값 컨트롤은 NextGameView, GameClearView에서 이루어진다
					p2 = new Monster("monster"+monCnt);
					new BattleView(p1, p2).setVisible(true);
	        		setVisible(false);
				} else {
					label.setVisible(true);
			        contentPane.add(label);
				}
        	}
        });
        
        //배경음악
		AudioView.audio.play(1);
	}
}
