package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class BombermanGame extends Application {

    public static int WIDTH = 25;
    public static int HEIGHT = 16;
    public static int _width = 0;
    public static int _height = 0;
    public static int _level = 1;

    private Bomber player = new Bomber(1, 1, Sprite.player_right.getFxImage());
    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();
    private Stage mainStage;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        this.mainStage = stage;
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        Image icon = new Image("file:src/uet/oop/bomberman/logo/Minh Anh.png");
        stage.getIcons().add(icon);
        stage.setTitle("BombermanGame");
        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        createMap();
        Entity bomber = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomber);
        scene.setOnKeyPressed(event -> ((Bomber) bomber).handleKeyPressedEvent(event.getCode()));
        scene.setOnKeyReleased(event -> ((Bomber) bomber).handleKeyReleasedEvent(event.getCode()));

    }

    public void createMap() {
        final File fileName = new File("D:\\Duc\\BTL_OOP_2\\res\\levels\\Level1.txt");
        try (FileReader inputFile = new FileReader(fileName)) {
            Scanner sc = new Scanner(inputFile);

            StringTokenizer tokens = new StringTokenizer(sc.nextLine());
            _level = Integer.parseInt(tokens.nextToken());
            _height = Integer.parseInt(tokens.nextToken());
            _width = Integer.parseInt(tokens.nextToken());

            while (sc.hasNextLine()) {
                entities.add(new Balloon(4,4,Sprite.balloom_left1.getFxImage()));
                entities.add(new Balloon(9, 9, Sprite.balloom_left1.getFxImage()));
                entities.add(new Balloon(22,6,Sprite.balloom_left1.getFxImage()));
                entities.add(new Oneal(7,6,Sprite.oneal_left1.getFxImage()));
                entities.add(new Oneal(13,8,Sprite.oneal_left1.getFxImage()));
                for (int i = 0; i < _height; ++i) {
                    StringTokenizer tokenizer = new StringTokenizer(sc.nextLine());

                    for (int j = 0; j < _width; j++) {
                        int s = Integer.parseInt(tokenizer.nextToken());
                        Entity entity;
                        switch (s) {
                            /**case 1:
                                entity = new Portal(j, i, Sprite.grass.getFxImage());
                                s = 0;
                                break;*/
                            case 2:
                                entity = new Wall(j, i, Sprite.wall.getFxImage());
                                break;
                            case 3:
                                entity = new Brick(j, i, Sprite.brick.getFxImage());
                                break;
                            /**case 6:
                                entity = new SpeedItem(j, i, Sprite.brick.getFxImage());
                                break;*/
                            /**case 7:
                                entity = new FlameItem(j, i, Sprite.brick.getFxImage());
                                break;*/
                            default:
                                entity = new Grass(j, i, Sprite.grass.getFxImage());
                        }
                        stillObjects.add(entity);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        entities.forEach(Entity::update);
        stillObjects.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
