package app;

import com.moandjiezana.toml.Toml;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Config {
    public final static Map<String, Map<String, Object>> Cache = new HashMap<>();

    private Config() {
    }

    public static void initCache(String... args) {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException("Arguments is not correct.");
        }
        for (var arg : args) {
            if (arg.startsWith("ROOT_PATH=")) {
                init(arg);
                return;
            }
        }
        throw new IllegalArgumentException("Arguments is not correct.");
    }

    private static void init(String rootPath) {
        var configFileRootPath = String.format("%s%s%s%s%s", rootPath.split("=")[1], File.separator, "application", File.separator, "configuration");
        try (var walk = Files.walk(Paths.get(configFileRootPath))) {
            var result = walk.filter(Files::isRegularFile)
                    .filter(f -> f.toString().toLowerCase().endsWith(".toml"))
                    .collect(Collectors.toList());
            result.forEach(Config::readToCache);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Arguments is not correct.");
        }
    }

    private static void readToCache(Path path) {
        var fileName = path.getFileName().toString();
        Cache.put(fileName.substring(0, fileName.length() - 5), new Toml().read(path.toFile()).toMap());
    }
}
