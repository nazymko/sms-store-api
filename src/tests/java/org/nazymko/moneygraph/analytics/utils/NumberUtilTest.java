package org.nazymko.moneygraph.analytics.utils;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Created by a.nazimko on 17.03.2017.
 */
public class NumberUtilTest {

    @Test
    public void isDecimal() throws Exception {
        Assertions.assertThat(NumberUtil.isDecimal("10.001")).isTrue();
        Assertions.assertThat(NumberUtil.isDecimal("-10.001")).isTrue();
        Assertions.assertThat(NumberUtil.isDecimal("10001")).isTrue();
        Assertions.assertThat(NumberUtil.isDecimal("10_001")).isFalse();
        Assertions.assertThat(NumberUtil.isDecimal("1n")).isFalse();
        Assertions.assertThat(NumberUtil.isDecimal("number100")).isFalse();

    }

}