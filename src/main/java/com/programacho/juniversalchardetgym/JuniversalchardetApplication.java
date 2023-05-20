package com.programacho.juniversalchardetgym;

import org.mozilla.universalchardet.UniversalDetector;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JuniversalchardetApplication {
    public static void main(String[] args) {
        // UTF-8
        detectCharset(Paths.get("text/UTF-8.txt"));

        // UTF-8 with simple way
        detectCharsetWithSimpleWay(Paths.get("text/UTF-8.txt"));

        // Shift_JIS
        detectCharset(Paths.get("text/Shift_JIS.txt"));

        // EUC-JP with simple way
        detectCharset(Paths.get("text/EUC-JP.txt"));
    }

    private static void detectCharset(Path path) {
        try (InputStream is = Files.newInputStream(path)) {
            UniversalDetector detector = new UniversalDetector();

            try {
                detector.handleData(is.readAllBytes());
                detector.dataEnd();

                String encoding = detector.getDetectedCharset();
                if (encoding != null) {
                    System.out.println("Encoding: " + encoding);
                } else {
                    System.out.println("No encoding detected");
                }
            } finally {
                detector.reset();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void detectCharsetWithSimpleWay(Path path) {
        try {
            String encoding = UniversalDetector.detectCharset(path);
            if (encoding != null) {
                System.out.println("Encoding (with simple way): " + encoding);
            } else {
                System.out.println("No encoding detected");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
