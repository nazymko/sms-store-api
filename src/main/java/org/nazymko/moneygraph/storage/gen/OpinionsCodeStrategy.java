package org.nazymko.moneygraph.storage.gen;

import org.jooq.tools.StringUtils;
import org.jooq.util.Definition;

/**
 * Created by a.nazimko on 07.03.2017.
 */
public class OpinionsCodeStrategy extends org.jooq.util.DefaultGeneratorStrategy {
    @Override
    public String getJavaClassName(Definition definition, Mode mode) {

        if (mode == Mode.DAO) {
            StringBuilder result = new StringBuilder("Jooq");

            // [#4562] Some characters should be treated like underscore
            result.append(StringUtils.toCamelCase(
                    definition.getOutputName()
                            .replace(' ', '_')
                            .replace('-', '_')
                            .replace('.', '_')
            ));

            result.append("Dao");

            return result.toString();
        }

        return super.getJavaClassName(definition, mode);
    }
}
