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
import javax.swing.JLabel;//�۾�����
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class StartView extends JFrame {
	
	private JPanel contentPane;
    private Image backgroundImage;	//����̹���
	private JLabel titleLabel;		//�����̹���
	
	private JButton startbtn;		//���۹�ư<-SelectPlayerView()�� visible��Ų��.
    
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
		contentPane.setLayout(null); //��ġ �����ڸ� ����<-������Ʈ�� Absolute Layout���� ��ġ�ϰ���.
		setLocationRelativeTo(null); //JFrame�� ȭ�� ���߾ӿ� ��ġ

		//�����̹���
        titleLabel = new JLabel();
        titleLabel.setBounds(200, 110, 390, 260);
        titleLabel.setIcon(new ImageIcon(getClass().getResource("/images/title.png")));	//��� ��� ���(�߿�)
        contentPane.add(titleLabel);
        
		//���۹�ư
        startbtn = new JButton("START");
        startbtn.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
        startbtn.setBounds(300, 420, 160, 50);
        contentPane.add(startbtn);
        startbtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                AudioView.audio.play(101);	//Ŭ�� ȿ���� ���
    			new SelectPlayerView().setVisible(true);
    			setVisible(false);
        	}
        });
        
        //������� â�� ���� â�� �ٷ� �Ʒ��ʿ� ���� ���� contentPane�� x, y���� �޾ƿͼ� ����ߴ�.
		AudioView.audio.setLocation(contentPane.getX()+645, contentPane.getY()+675);
		AudioView.audio.setVisible(true);
		AudioView.audio.play(0);	//�������(intro_music) ���
	}

}
