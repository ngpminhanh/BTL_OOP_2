package uet.oop.bomberman;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Sound {
    static Media s1 = new Media(new File("res/sound/bomb_explosion.wav").toURI().toString());
    static Media s2 = new Media(new File("res/sound/bomberman_die.wav").toURI().toString());
    static Media s3 = new Media(new File("res/sound/default_music.wav").toURI().toString());
    static Media s4 = new Media(new File("res/sound/eat_item.wav").toURI().toString());
    static Media s5 = new Media(new File("res/sound/enemy_die.wav").toURI().toString());
    static Media s6 = new Media(new File("res/sound/level_up.wav").toURI().toString());

    public static MediaPlayer bom_no = new MediaPlayer(s1);
    public static MediaPlayer nv_chet = new MediaPlayer(s2);
    public static MediaPlayer nhac_nen = new MediaPlayer(s3);
    public static MediaPlayer an_item = new MediaPlayer(s4);
    public static MediaPlayer quai_chet = new MediaPlayer(s5);
    public static MediaPlayer qua_man = new MediaPlayer(s6);

}