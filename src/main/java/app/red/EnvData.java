package app.red;

public class EnvData {
    private String env;
    private String subEnv;

    public EnvData() {
    }

    public EnvData(String env, String subEnv) {
        this.env = env;
        this.subEnv = subEnv;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getSubEnv() {
        return subEnv;
    }

    public void setSubEnv(String subEnv) {
        this.subEnv = subEnv;
    }
}
