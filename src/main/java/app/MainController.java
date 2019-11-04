package app;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Pane mainPane;

    @FXML
    private Label info;

    private Double mainPaneHeight;
    private Double mainPaneWidth;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        var main = Config.Cache.get("main");
        this.mainPaneHeight = Double.valueOf(main.get("window-height").toString());
        this.mainPaneWidth = Double.valueOf(main.get("window-width").toString());
        var radius = Double.valueOf(main.get("button-radius").toString());
        var margin = Double.valueOf(main.get("window-margin").toString());

        mainPane.setPrefHeight(this.mainPaneHeight);
        mainPane.setPrefWidth(this.mainPaneWidth);
        mainPane.setStyle(MainStyle.DEFAULT_BACKGROUND_COLOR);

        info.layoutXProperty().bind(mainPane.widthProperty().subtract(info.widthProperty()).divide(2));
        info.layoutYProperty().bind(mainPane.heightProperty().subtract(info.heightProperty()).divide(2));
        info.setStyle(MainStyle.BACKGROUND_COLOR_ON_MOUSE);
        info.setOpacity(0);
        info.setAlignment(Pos.BASELINE_CENTER);

        var coordinate = Util.getCoordinate(mainPaneHeight, mainPaneWidth, radius, margin);
        var children = mainPane.getChildren();
        for (var i = 0; i < coordinate.length; i++) {
            final var count = i;
            var button = (Button) children.get(count);
            button.setLayoutX((coordinate[count][0]));
            button.setLayoutY((coordinate[count][1]));
            button.setStyle(MainStyle.BUTTON_COLOR[count]);
            button.setText(main.get("main-pane-button-text-" + count).toString());
            var active = main.get("main-pane-button-active-" + count).toString();
            button.setOpacity(0.5D);
            if (!MainStyle.ACTIVE.equals(active)) {
                continue;
            }
            var animationOnMouseEntered = new Transition() {
                {
                    setCycleDuration(Duration.millis(500));
                    setInterpolator(Interpolator.EASE_BOTH);
                }

                @Override
                protected void interpolate(double frac) {
                    info.setOpacity(Math.min(1.0D, info.getOpacity() + frac));
                    button.setOpacity(Math.min(1.0D, button.getOpacity() + frac));
                    button.setScaleX(Math.min(MainStyle.SCALE_ON_MOUSE, button.getScaleX() + frac));
                    button.setScaleY(Math.min(MainStyle.SCALE_ON_MOUSE, button.getScaleY() + frac));
                    button.setScaleZ(Math.min(MainStyle.SCALE_ON_MOUSE, button.getScaleZ() + frac));
                }
            };
            var animationOnMouseExited = new Transition() {
                {
                    setCycleDuration(Duration.millis(500));
                    setInterpolator(Interpolator.EASE_BOTH);
                }

                @Override
                protected void interpolate(double frac) {
                    info.setOpacity(Math.max(0.0D, info.getOpacity() - frac));
                    button.setOpacity(Math.max(0.8D, button.getOpacity() - frac));
                    button.setScaleX(Math.max(MainStyle.SCALE_DEFAULT, button.getScaleX() - frac));
                    button.setScaleY(Math.max(MainStyle.SCALE_DEFAULT, button.getScaleY() - frac));
                    button.setScaleZ(Math.max(MainStyle.SCALE_DEFAULT, button.getScaleZ() - frac));
                }
            };
            button.setOnMouseEntered(e -> {
                info.setText(main.get("center-label-info-" + count).toString());
                animationOnMouseEntered.play();
                animationOnMouseExited.stop();
            });
            button.setOnMouseExited(e -> {
                info.setText("");
                animationOnMouseEntered.stop();
                animationOnMouseExited.play();
            });
            // TODO

        }
    }
}
