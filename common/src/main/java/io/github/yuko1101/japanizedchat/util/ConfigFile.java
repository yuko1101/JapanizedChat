package io.github.yuko1101.japanizedchat.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

public class ConfigFile {
    public final File file;

    public ConfigFile(String path) {
        this.file = new File("config/japanizedchat/" + path);
    }

    public void ensureExists() {
        try {
            var parent = this.file.getParentFile();
            if ((parent.exists() || parent.mkdirs()) && !this.file.exists()) {
                Files.copy(Objects.requireNonNull(ConfigFile.class.getClassLoader().getResourceAsStream(this.file.getName())), this.file.toPath());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
