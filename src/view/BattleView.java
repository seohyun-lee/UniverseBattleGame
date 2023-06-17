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
    private Image backgroundImage; //����̹���
	private JPanel panel1;
	private JPanel panel2;

	private JLabel label1;
	private JLabel label2;
	private JLabel sparkLabel;
	
	private JButton btnATK; //attack
	private JButton btnDEF; //defense
	private JButton btnRST; //rest
	private JButton btnSPC; //special
	
	private JProgressBar bar1;
	private JProgressBar bar2;

	private JScrollPane scrollPane;
	private JTextArea textArea;

	// �� view������ �׽����� ���� singer, monster1�� ��������
	Musician p1 = new Musician("singer");
	Monster p2 = new Monster("monster1");

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public BattleView() {
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
		// ��� �̹��� ����
        String imagePath = "./src/images/universe.jpg";
        backgroundImage = new ImageIcon(imagePath).getImage();
		
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); //JFrame ȭ�� �߾ӿ� ��ġ
		
		
		//Musician
		panel1 = new JPanel();
		panel1.setBounds(40, 40, 310, 335);
		panel1.setLayout(null);
		contentPane.add(panel1);
		panel1.setBackground(new Color(255, 0, 0, 0));

		label1 = new JLabel();
		label1.setBounds(0, 0, 310, 310);
		label1.setIcon(new ImageIcon(p1.getImgFile()));
		panel1.add(label1);
		
		bar1 = new JProgressBar();
		bar1.setMaximum(1000);
		bar1.setForeground(Color.GREEN);
		bar1.setValue(1000);
		bar1.setBounds(0, 310, 290, 25);
		panel1.add(bar1);
		
		//Monster
		panel2 = new JPanel();
		panel2.setBounds(410, 40, 310, 335);
		panel2.setLayout(null);
		contentPane.add(panel2);
		panel2.setBackground(new Color(255, 0, 0, 0));

		label2 = new JLabel();
		label2.setBounds(0, 0, 310, 310);
		label2.setIcon(new ImageIcon(p2.getImgFile())); //�ӽ�
		panel2.add(label2);
		
		bar2 = new JProgressBar();
		bar2.setMaximum(1000);
		bar2.setForeground(Color.RED);
		bar2.setValue(1000);
		bar2.setBounds(20, 310, 290, 25);
		panel2.add(bar2);
		
		//spark �̹���
        sparkLabel = new JLabel();
        sparkLabel.setBounds(270, 50, 300, 300);
        sparkLabel.setIcon(new ImageIcon("./src/images/spark.png"));
        contentPane.add(sparkLabel);

		//scrollPane, textArea
		scrollPane = new JScrollPane();
		scrollPane.setBounds(280, 410, 450, 140);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		textArea.setForeground(new Color(255, 255, 255));
		textArea.setBackground(new Color(0, 0, 64));
		scrollPane.setViewportView(textArea);
		
		//��ư��
		btnATK = new JButton("Attack");
		btnATK.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		btnATK.setBounds(30, 410, 110, 50);
		contentPane.add(btnATK);
		
		btnDEF = new JButton("Defense");
		btnDEF.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		btnDEF.setBounds(150, 410, 110, 50);
		contentPane.add(btnDEF);
		
		btnRST = new JButton("Rest");
		btnRST.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		btnRST.setBounds(30, 490, 110, 50);
		contentPane.add(btnRST);
		
		btnSPC = new JButton("Special");
		btnSPC.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		btnSPC.setBounds(150, 490, 110, 50);
		contentPane.add(btnSPC);
		
		// ���� ��ư ������
        btnATK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BackgroundMusic.music.play(201);        		
                p1.attack(p2);
                bar2.setValue(p2.getHP());
                textArea.append(p1.name+"�� "+p2.name+"�� �����մϴ�.\n");

                // ������ Monster�� ü���� 0�� ��� ó��: ������ ���Ͱ� �ƴ϶�� NextGameView, ������ ���Ͷ�� GameClearView
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
                }
            }
        });

        // ��� ��ư ������
        btnDEF.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BackgroundMusic.music.play(202);
            	p1.defense();
                textArea.append(p1.name+"�� ������ ����� �غ� �մϴ�.\n");
            }
        });
        
        // �޽� ��ư ������
        btnRST.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BackgroundMusic.music.play(203);
            	p1.rest();
                bar1.setValue(p1.getHP());
                textArea.append(p1.name+"�� �޽��� ���� ü���� ȸ���մϴ�.\n");
            }
        });
        
        // ����� ��ư ������
        btnSPC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BackgroundMusic.music.play(204);
            	p1.special(p2);
                bar2.setValue(p2.getHP());
                textArea.append(p1.name+"�� "+p2.name+"���� ���� ���� ������ ����߽��ϴ�!\n");
                textArea.append(p2.name+"���� ����� ���ظ� �������ϴ�.\n");
                
                // ������ Monster�� ü���� 0�� ��� ó��: ������ ���Ͱ� �ƴ϶�� NextGameView, ������ ���Ͷ�� GameClearView
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
        
        // Monster�� ���� ��ư
		JButton btnMSTATK = new JButton("Monster's ATTACK");
		btnMSTATK.setForeground(new Color(255, 0, 0));
		btnMSTATK.setFont(new Font("Baskerville Old Face", Font.PLAIN, 12));
		btnMSTATK.setBounds(160, 0, 150, 30);
		panel2.add(btnMSTATK);
		
		// ������
		btnMSTATK.addActionListener(new ActionListener()  {
            public void actionPerformed(ActionEvent e) {
                BackgroundMusic.music.play(211);
            	p2.attack(p1);
                bar1.setValue(p1.getHP());
                textArea.append(p2.name+"�� "+p1.name+"�� �����մϴ�.\n");
                
                // Musician�� ü���� 0�� ��� ó��: �й������Ƿ� GameOverView
                if (p1.getHP() == 0) {
                    new GameOverView().setVisible(true);
                    setVisible(false);
                }
            }
        });
		
        //�������
		if(SelectPlayerView.monCnt < 4) {
			BackgroundMusic.music.play(2);
		} else {
			BackgroundMusic.music.play(6);
		}
	}
	
	public BattleView(Musician player1, Monster player2) {

		this();
		p1 = player1;
		p2 = player2;
		// bar1�� �ִ밪�� ���簪�� setting
		bar1.setMaximum(p1.getHP());
		bar2.setMaximum(p2.getHP());
		bar1.setValue(bar1.getMaximum());
		bar2.setValue(bar2.getMaximum());

		// �̹�������!
		label1.setIcon(new ImageIcon(p1.getImgFile()));
		label2.setIcon(new ImageIcon(p2.getImgFile()));
		
	}
}
