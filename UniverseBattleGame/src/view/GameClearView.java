package view;

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
    private Image backgroundImage;	//����̹���
	private JLabel titleLabel;		//�����̹���
	private JLabel image;			//Musicians �׸��̹���
	private JButton resetBtn;		//���� ��ư -> StartView�� �̵�

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
		
		//�����̹���
		titleLabel = new JLabel();
		titleLabel.setBounds(140, 70, 600, 100);
		titleLabel.setIcon(new ImageIcon(getClass().getResource("/images/gameclear.png")));
        contentPane.add(titleLabel);
        
        //musicians �̹���
        image = new JLabel();
        image.setBounds(55, 170, 620, 270);
        image.setIcon(new ImageIcon(getClass().getResource("/images/musicians.png")));
        contentPane.add(image);
		
        //���� ��ư
        resetBtn = new JButton("RESET");
        resetBtn.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
        resetBtn.setBounds(300, 450, 160, 50);
        contentPane.add(resetBtn);
        resetBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                AudioView.audio.play(101);
    			new StartView().setVisible(true);
    			setVisible(false);
    	        //���� ��ȣ �ʱ�ȭ
    	        SelectPlayerView.monCnt=1;
        	}
        });        

        //�������
		AudioView.audio.play(5);
	}
}
