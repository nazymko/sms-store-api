package org.nazymko.moneygraph.testing;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by a.nazimko on 16.03.2017.
 */
public class ReadFileUtil {

    public static String read(String filename) {
        final StringBuilder builder = new StringBuilder();

        final InputStream inputStream;
        try {
            inputStream = ReadFileUtil.class.getClassLoader()
                    .getResource(filename).openStream();

            final Scanner scanner = new Scanner(inputStream);

            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine());

                if (scanner.hasNextLine()) {
                    builder.append(System.getProperty("line.separator"));
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
