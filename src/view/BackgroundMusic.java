package view;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class BackgroundMusic extends JFrame {

    private Clip bgm;
    private Clip sfx;
    private JSlider volumeSlider;
    
    public static BackgroundMusic music = new BackgroundMusic();
    
    private BackgroundMusic() {
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
        });
    }

    public void play(int i) {
    	if (i==0)
    		bgmPlay("./src/music/intro_music.wav");
    	else if (i==1)
    		bgmPlay("./src/music/musician_select_music.wav");
    	else if (i==2)
    		bgmPlay("./src/music/battle_music.wav");
    	else if (i==3)
    		bgmPlay("./src/music/game_over_music.wav");
    	else if (i==4)
    		bgmPlay("./src/music/next_game_music.wav");
    	else if (i==5)
    		bgmPlay("./src/music/game_clear_music.wav");
    	else if (i==6)
    		bgmPlay("./src/music/boss_music.wav");
    	
    	if (i==101)
    		sfxPlay("./src/music/click_sfx.wav");
    	else if (i==102)
    		sfxPlay("./src/music/select_sfx.wav");

    	else if (i==201)
    		sfxPlay("./src/music/attack_sfx.wav");
    	else if (i==202)
    		sfxPlay("./src/music/defense_sfx.wav");
    	else if (i==203)
    		sfxPlay("./src/music/rest_sfx.wav");
    	else if (i==204)
    		sfxPlay("./src/music/special_sfx.wav");
    	else if (i==211)
    		sfxPlay("./src/music/attacked_sfx.wav");
    }

    private void bgmPlay(String file) {
    	if (bgm != null && bgm.isRunning()) {
            bgm.stop();
            bgm.close();
        }
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(file));
            bgm = AudioSystem.getClip();
            bgm.open(audioStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        bgm.start();
        bgm.loop(Clip.LOOP_CONTINUOUSLY);
        float volume = volumeSlider.getValue() / 100.0f;
        setVolume(volume);
    }
    
    public void sfxPlay(String file) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(file));
            sfx = AudioSystem.getClip();
            sfx.open(audioStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        sfx.start();
        float volume = volumeSlider.getValue() / 100.0f;
        setVolume(volume);
    }

    private void setVolume(float volume) {
        if (bgm != null && bgm.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl gainControl = (FloatControl) bgm.getControl(FloatControl.Type.MASTER_GAIN);
            float minVolume = gainControl.getMinimum();
            float maxVolume = gainControl.getMaximum();
            float adjustedVolume = minVolume + (maxVolume - minVolume) * volume;
            gainControl.setValue(adjustedVolume);
        }
    }
}