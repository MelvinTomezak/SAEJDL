package test;
// Test qui ne marche pas à cause d'un changement à réparer.
import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.assertions.api.Assertions.assertThat;
import interfaces.saelavrai.AccueilMain;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.Start;

import java.util.concurrent.TimeoutException;

// Test qui ne marche pas à cause d'un changement à réparer.
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestNoticeAnglais {
    Stage stage;

    @Start
    public void start() {
        Platform.runLater(() -> {
            TestNoticeAnglais.this.stage = new Stage();
            try {
                FxToolkit.setupStage((sta) -> {
                    try {
                        new AccueilMain().start(TestNoticeAnglais.this.stage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        });
    }
    @AfterEach
    void afterEachTest(FxRobot robot) throws TimeoutException {
        FxToolkit.cleanupStages();
        robot.release(new KeyCode[]{});
        robot.release(new MouseButton[]{});
    }
    @Test
    void test_lancement() {
        AccueilMain.launch(AccueilMain.class);
    }

    @Test
    public void test_should_never_fail() {
        AccueilMain.launch(AccueilMain.class);
        assertThat(true).isTrue();
    }

    @Test
    void testAccueil(FxRobot robot) {
        AccueilMain.launch(AccueilMain.class);
        Button myButton = robot.lookup("#Accueil").queryAs(Button.class);
        robot.clickOn(myButton);
    }
    @Test
    void testTranslate(FxRobot robot) {
        AccueilMain.launch(AccueilMain.class);
        Button myButton = robot.lookup("#Translate").queryAs(Button.class);
        robot.clickOn(myButton);
    }

    @Test
    public void shouldWindowHeightEquals600() { assertEquals(600, stage.getScene().getHeight()); }

    @Test
    public void shouldWindowWidthEquals1000() { assertEquals(  1000, stage.getScene().getWidth()); }
}