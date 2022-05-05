
package io.github.prismwork.emitrades.config;

import io.github.prismwork.prismconfig.api.PrismConfig;
import io.github.prismwork.prismconfig.api.config.DefaultDeserializers;
import io.github.prismwork.prismconfig.api.config.DefaultSerializers;
import io.github.prismwork.prismconfig.libs.jankson.Comment;

import java.io.*;

public class EMITradesConfig {
    public static Config load(File file) {
        if (!file.getName().endsWith(".json5"))
            throw new RuntimeException("Failed to read config");
        Config cfg = null;