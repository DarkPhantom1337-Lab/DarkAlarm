package ua.darkphantom1337;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args) {
        System.out.println("Starting Alarm");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 16);
        cal.set(Calendar.HOUR_OF_DAY, 04);
        cal.set(Calendar.MINUTE, 55);
        startAlarm(cal.getTime());
        cal.set(Calendar.DAY_OF_MONTH, 16);
        cal.set(Calendar.HOUR_OF_DAY, 05);
        cal.set(Calendar.MINUTE, 00);
        startAlarm(cal.getTime());
        cal.set(Calendar.DAY_OF_MONTH, 16);
        cal.set(Calendar.HOUR_OF_DAY, 05);
        cal.set(Calendar.MINUTE, 05);
        startAlarm(cal.getTime());
        cal.set(Calendar.DAY_OF_MONTH, 16);
        cal.set(Calendar.HOUR_OF_DAY, 05);
        cal.set(Calendar.MINUTE, 10);
        startAlarm(cal.getTime());
        cal.set(Calendar.DAY_OF_MONTH, 16);
        cal.set(Calendar.HOUR_OF_DAY, 05);
        cal.set(Calendar.MINUTE, 15);
        startAlarm(cal.getTime());
        cal.set(Calendar.DAY_OF_MONTH, 16);
        cal.set(Calendar.HOUR_OF_DAY, 05);
        cal.set(Calendar.MINUTE, 20);
        System.out.println("Signals set");
        startAlarm(cal.getTime());
        System.out.println("Alarm started...");
        System.out.println("Wait signal...");
    }

    public static void startAlarm(Date startdate) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                setSystemVolume(100);
                playAudio();
            }
        }, startdate);
    }

    public static void playAudio() {
        try {
            File soundFile = new File("C:\\Hard_Rock_Sofa_Dirty_Shade_-_Collapsar (online-audio-converter.com).wav"); //Звуковой файл
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.setFramePosition(0);
            clip.start();
            Thread.sleep(clip.getMicrosecondLength() / 400);
            clip.stop();
            clip.close();
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException exc) {
            exc.printStackTrace();
        } catch (InterruptedException exc) {
        }
    }

    public static void setSystemVolume(int volume) {
        if (volume < 0 || volume > 100) {
            throw new RuntimeException("Error: " + volume + " is not a valid number. Choose a number between 0 and 100");
        } else {
            double endVolume = 755.75 * volume;
            Runtime rt = Runtime.getRuntime();
            Process pr;
            try {
                pr = rt.exec("C:\\nircmd.exe setsysvolume " + endVolume);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
