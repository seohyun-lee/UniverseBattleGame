
package view;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.net.URL;


public class AudioView extends JFrame {

    private Clip bgm = null;
    private Clip sfx = null;
    private JSlider volumeSlider;
    
    public static AudioView audio = new AudioView();
    
    private AudioView() {
    	getContentPane().setBackground(new Color(0, 0, 64));
    	setBackground(new Color(0, 0, 64));
        setTitle("BGM");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(240, 64);

        volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 80);
        volumeSlider.setBackground(new Color(0, 0, 64));

        JPanel sliderPanel = new JPanel();
        sliderPanel.setBackground(new Color(0, 0, 64));
        sliderPanel.add(volumeSlider);

        getContentPane().add(sliderPanel, BorderLayout.CENTER);

        volumeSlider.addChangeListener(e -> {
            if (bgm != null && bgm.isRunning()) {
            	float volume = volumeSlider.getValue() / 100.0f;
                setVolume(volume);
            }
            if (sfx != null && sfx.isRunning()) {
            	float volume = volumeSlider.getValue() / 100.0f;
                setVolume(volume);
            }
        });
    }
    

    public void play(int i) {
    	//bgmPlay(getClass().getResource("/sounds/intro_music.wav").toString());
    	
    	if (i==0)
    		bgmPlay(getClass().getResource("/sounds/intro_music.wav"));
    	else if (i==1)
    		bgmPlay(getClass().getResource("/sounds/musician_select_music.wav"));    	
    	else if (i==2)
    		bgmPlay(getClass().getResource("/sounds/battle_music.wav"));
    	else if (i==3)
    		bgmPlay(getClass().getResource("/sounds/game_over_music.wav"));
    	else if (i==4)
    		bgmPlay(getClass().getResource("/sounds/next_game_music.wav"));
    	else if (i==5)
    		bgmPlay(getClass().getResource("/sounds/game_clear_music.wav"));
    	else if (i==6)
    		bgmPlay(getClass().getResource("/sounds/boss_music.wav"));
    	
    	if (i==101)
    		sfxPlay(getClass().getResource("/sounds/click_sfx.wav"));
    	else if (i==102)
    		sfxPlay(getClass().getResource("/sounds/select_sfx.wav"));

    	if (i==201)
    		sfxPlay(getClass().getResource("/sounds/attack_sfx.wav"));
    	else if (i==202)
    		sfxPlay(getClass().getResource("/sounds/defense_sfx.wav"));
    	else if (i==203)
    		sfxPlay(getClass().getResource("/sounds/rest_sfx.wav"));
    	else if (i==204)
    		sfxPlay(getClass().getResource("/sounds/special_sfx.wav"));
    	else if (i==211)
    		sfxPlay(getClass().getResource("/sounds/attacked_sfx.wav"));
    }

    private void bgmPlay(URL url) {
    	if (bgm != null && bgm.isRunning()) {
            bgm.stop();
            bgm.close();
        }
        try {        	
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
            bgm = AudioSystem.getClip();
            bgm.open(audioStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        bgm.start();
        bgm.loop(Clip.LOOP_CONTINUOUSLY);
        float volume = volumeSlider.getValue() / 100.0f;
        setVolume(volume);
    	//System.out.println(url); //출력되는 배경음악 확인
    }

    private void sfxPlay(URL url) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
            sfx = AudioSystem.getClip();
            sfx.open(audioStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sfx.start();
        float volume = volumeSlider.getValue() / 100.0f;
        setVolume(volume);
    	//System.out.println(url); //출력되는 효과음 확인
    }

    private void setVolume(float volume) {
        if (bgm != null && bgm.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl gainControl = (FloatControl) bgm.getControl(FloatControl.Type.MASTER_GAIN);
            float minVolume = gainControl.getMinimum();
            float maxVolume = gainControl.getMaximum();
            float adjustedVolume = minVolume + (maxVolume - minVolume) * volume;
            gainControl.setValue(adjustedVolume);
        }
        if (sfx != null && sfx.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl gainControl = (FloatControl) sfx.getControl(FloatControl.Type.MASTER_GAIN);
            float minVolume = gainControl.getMinimum();
            float maxVolume = gainControl.getMaximum();
            float adjustedVolume = minVolume + (maxVolume - minVolume) * volume;
            gainControl.setValue(adjustedVolume);
        }
    }
}