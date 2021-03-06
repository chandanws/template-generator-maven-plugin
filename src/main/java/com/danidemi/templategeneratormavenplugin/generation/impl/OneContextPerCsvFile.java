package com.danidemi.templategeneratormavenplugin.generation.impl;

/*-
 * #%L
 * template-generator-maven-plugin
 * %%
 * Copyright (C) 2017 Studio DaniDemi
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.danidemi.templategeneratormavenplugin.generation.ContextCreator;
import com.danidemi.templategeneratormavenplugin.generation.RowSource;
import com.danidemi.templategeneratormavenplugin.model.ContextModel;
import com.danidemi.templategeneratormavenplugin.model.ContextModelBuilder;
import com.danidemi.templategeneratormavenplugin.model.IRowModel;

import java.util.Arrays;

import static com.danidemi.templategeneratormavenplugin.utils.Preconditions.checkArgument;

/**
 * Creates one context for the whole file.
 */
public class OneContextPerCsvFile implements ContextCreator {

    private final RowSource rowSource;
    private final ContextModelBuilder basic;

    public OneContextPerCsvFile(RowSource rowSource, ContextModelBuilder basic) {
        checkArgument(rowSource!=null);
        this.rowSource = rowSource;
        this.basic = basic;
    }

    @Override public Iterable<ContextModel> contexts() {
        ContextModelBuilder builder = (ContextModelBuilder)basic.clone();
        for (IRowModel row : rowSource) {
            builder.add(row);
        }
        return Arrays.asList(builder.build());
    }

}
