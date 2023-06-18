package view;

import java.awt.Color;
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
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import player.*;

public class BattleView extends JFrame {

	private JPanel contentPane;
    private Image backgroundImage;	//배경이미지
    
	private JPanel panel1;		//p1을 위한 JPanel
	private JPanel panel2;		//p2를 위한 JPanel
	private JLabel label1;		//p1을 위한 JLabel
	private JLabel label2;		//p2를 위한 JLabel
	private JLabel sparkLabel;	//spark 이미지를 띄우기 위한 JLabel
	
	private JButton btnATK; 	//Attack 버튼 (Musician의)
	private JButton btnDEF; 	//Defense 버튼
	private JButton btnRST;		//Rest 버튼
	private JButton btnSPC; 	//Special 버튼
	private JButton btnMSTATK;	//Monster의 공격버튼
	
	private JProgressBar bar1;	//p1을 위한 JProgressBar
	private JProgressBar bar2;	//p1를 위한 JProgressBar

	private JScrollPane scrollPane;
	private JTextArea textArea;

	//이 view에서의 테스트 실행을 위해 singer, monster1로 초기화했음
	Musician p1 = new Musician("singer");
	Monster p2 = new Monster("monster1");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BattleView frame = new BattleView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BattleView() {
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
		
		
		//Musician(p1)
		panel1 = new JPanel();
		panel1.setBounds(40, 40, 310, 335);
		panel1.setLayout(null);
		contentPane.add(panel1);
		panel1.setBackground(new Color(255, 0, 0, 0));

		label1 = new JLabel();
		label1.setBounds(0, 0, 310, 310);
		label1.setIcon(new ImageIcon(getClass().getResource(p1.getImgFile())));		
		panel1.add(label1);
		
		bar1 = new JProgressBar();
		bar1.setMaximum(1000);
		bar1.setForeground(Color.GREEN);
		bar1.setValue(1000);
		bar1.setBounds(0, 310, 290, 25);
		panel1.add(bar1);
		
		//Monster(p2)
		panel2 = new JPanel();
		panel2.setBounds(410, 40, 310, 335);
		panel2.setLayout(null);
		contentPane.add(panel2);
		panel2.setBackground(new Color(255, 0, 0, 0));

		label2 = new JLabel();
		label2.setBounds(0, 0, 310, 310);
		label2.setIcon(new ImageIcon(getClass().getResource(p2.getImgFile())));
		panel2.add(label2);
		
		bar2 = new JProgressBar();
		bar2.setMaximum(1000);
		bar2.setForeground(Color.RED);
		bar2.setValue(1000);
		bar2.setBounds(20, 310, 290, 25);
		panel2.add(bar2);
		
		//spark 이미지
        sparkLabel = new JLabel();
        sparkLabel.setBounds(270, 50, 300, 300);
        sparkLabel.setIcon(new ImageIcon(getClass().getResource("/images/spark.png")));
        contentPane.add(sparkLabel);

		//scrollPane, textArea
		scrollPane = new JScrollPane();
		scrollPane.setBounds(280, 410, 450, 140);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		textArea.setForeground(new Color(255, 255, 255));
		textArea.setBackground(new Color(0, 0, 64));
		scrollPane.setViewportView(textArea);
		
		//p1가 사용하는 버튼들과 리스너들
		//Attack 버튼
		btnATK = new JButton("Attack");
		btnATK.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		btnATK.setBounds(30, 410, 110, 50);
		contentPane.add(btnATK);
		btnATK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AudioView.audio.play(201);        		
                p1.attack(p2);
                bar2.setValue(p2.getHP());
                textArea.append(p1.name+"이 "+p2.name+"을 공격합니다.\n");

                // 공격후 Monster의 체력이 0인 경우 처리: 마지막 몬스터가 아니라면 NextGameView, 마지막 몬스터라면 GameClearView
                if (p2.getHP() == 0) {
                	if(SelectPlayerView.monCnt < 4) {
                        new NextGameView().setVisible(true);
                	} else {
                		new GameClearView().setVisible(true);
                	}
                    setVisible(false);
                }
                if(p1.ATKCount==3) {
                    btnSPC.setEnabled(true);
                    textArea.append("=="+p1.name+"에게 특별한 힘이 차올랐습니다!==\n");
                }
            }
        });
		
		//Defense 버튼
		btnDEF = new JButton("Defense");
		btnDEF.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		btnDEF.setBounds(150, 410, 110, 50);
		contentPane.add(btnDEF);
		btnDEF.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AudioView.audio.play(202);
            	p1.defense();
                textArea.append(p1.name+"이 공격을 방어할 준비를 합니다.\n");
            }
        });
		
		//Rest 버튼
		btnRST = new JButton("Rest");
		btnRST.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		btnRST.setBounds(30, 490, 110, 50);
		contentPane.add(btnRST);
		btnRST.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AudioView.audio.play(203);
            	p1.rest();
                bar1.setValue(p1.getHP());
                textArea.append(p1.name+"이 휴식을 취해 체력을 회복합니다.\n");
            }
        });
		
		//Special 버튼
		btnSPC = new JButton("Special");
		btnSPC.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		btnSPC.setBounds(150, 490, 110, 50);
		contentPane.add(btnSPC);
		btnSPC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AudioView.audio.play(204);
            	p1.special(p2);
                bar2.setValue(p2.getHP());
                textArea.append(p1.name+"이 특별한 힘으로 "+p2.name+"를 공격합니다.\n");
                textArea.append("아주 강한 공격! "+p2.name+"에게 상당한 피해를 입혔습니다.\n");
                
                // 공격후 Monster의 체력이 0인 경우 처리: 마지막 몬스터가 아니라면 NextGameView, 마지막 몬스터라면 GameClearView
                if (p2.getHP() == 0) {
                	if(SelectPlayerView.monCnt < 4) {
                        new NextGameView().setVisible(true);
                	} else {
                		new GameClearView().setVisible(true);
                	}
                    setVisible(false);
                }
                btnSPC.setEnabled(false);
            }
        });
		        
        //Monster의 공격버튼
		btnMSTATK = new JButton("Monster's ATTACK");
		btnMSTATK.setForeground(new Color(255, 0, 0));
		btnMSTATK.setFont(new Font("Baskerville Old Face", Font.PLAIN, 12));
		btnMSTATK.setBounds(160, 0, 150, 30);
		panel2.add(btnMSTATK);
		btnMSTATK.addActionListener(new ActionListener()  {
            public void actionPerformed(ActionEvent e) {
                AudioView.audio.play(211);
            	p2.attack(p1);
                bar1.setValue(p1.getHP());
                textArea.append(p2.name+"이 "+p1.name+"을 공격합니다.\n");
                
                // Musician의 체력이 0인 경우 처리: 패배했으므로 GameOverView
                if (p1.getHP() == 0) {
                    new GameOverView().setVisible(true);
                    setVisible(false);
                }
            }
        });
		
		//배경음악-마지막 몬스터(보스)일때만 배경음악 다르게 출력
		if(SelectPlayerView.monCnt == 4) {
			AudioView.audio.play(6);
		} else {
			AudioView.audio.play(2);
		}
	}
	
	public BattleView(Musician player1, Monster player2) {

		this();
		p1 = player1;
		p2 = player2;
		//bar1, bar2의 최대값과 현재값 setting
		bar1.setMaximum(p1.getHP());
		bar2.setMaximum(p2.getHP());
		bar1.setValue(bar1.getMaximum());
		bar2.setValue(bar2.getMaximum());

		//이미지 세팅
		label1.setIcon(new ImageIcon(getClass().getResource(p1.getImgFile())));		
		label2.setIcon(new ImageIcon(getClass().getResource(p2.getImgFile())));;
        
	}
}
