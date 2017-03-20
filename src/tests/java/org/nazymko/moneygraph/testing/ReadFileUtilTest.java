package org.nazymko.moneygraph.testing;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Created by a.nazimko on 16.03.2017.
 */
public class ReadFileUtilTest {
    @Test
    public void read() throws Exception {
        final String read = ReadFileUtil.read("test.txt");

        Assertions.assertThat(read).isEqualTo("test.file");
    }

}