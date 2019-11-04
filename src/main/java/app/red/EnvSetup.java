package app.red;

import javafx.stage.Stage;

public class EnvSetup {
    private static EnvSetup envSetup = null;
    private EnvData data;
    private EnvController controller;
    private Stage envSetupStage;

    private EnvSetup() {

    }

    public static EnvData showEnvSetup() {
        if (envSetup == null) {
            envSetup = new EnvSetup();
        }


        return null;
    }

    public void hide() {
        if (this.envSetupStage != null) {
            this.envSetupStage.hide();
        }
    }
}
