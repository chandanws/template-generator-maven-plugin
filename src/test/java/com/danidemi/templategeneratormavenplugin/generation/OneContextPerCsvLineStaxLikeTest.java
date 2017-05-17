package com.danidemi.templategeneratormavenplugin.generation;

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

import com.danidemi.templategeneratormavenplugin.generation.impl.CsvRowSource;
import com.danidemi.templategeneratormavenplugin.generation.impl.OneContextPerCsvLineStaxLike;
import com.danidemi.templategeneratormavenplugin.model.ContextModel;
import com.danidemi.templategeneratormavenplugin.model.IRowModel;
import org.junit.Test;

import java.io.InputStreamReader;
import java.util.Iterator;

import static com.danidemi.templategeneratormavenplugin.TestUtils.mockPrototype;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class OneContextPerCsvLineStaxLikeTest {

    @Test public void produceContextFromAnotherCsvFile() {

        OneContextPerCsvLineStaxLike sut = new OneContextPerCsvLineStaxLike(
                new CsvRowSource(
                        new InputStreamReader( Object.class.getResourceAsStream("/codeAndCurrency.csv") )
                ),
                mockPrototype()
        );

        Iterator<ContextModel> ctxIt = sut.contexts().iterator();
        IRowModel row;
        ContextModel ctx;

        ctx = ctxIt.next();
        row = ctx.rowIterator().iterator().next();
        assertThat( row.getData().get("Code").toString(), equalTo("EUR") );
        assertThat( row.getData().get("Currency").toString(), equalTo("Euro") );
        assertThat( row.getMeta().getSourceCount(), equalTo(1L) );
        assertThat( row.getMeta().getSourceIndex(), equalTo(0L) );
        assertThat( row.getMeta().getIndex(), equalTo(0L) );
        assertThat( row.getMeta().getCount(), equalTo(1L) );

        ctx = ctxIt.next();
        row = ctx.rowIterator().iterator().next();
        assertThat( row.getData().get("Code").toString(), equalTo("USD") );
        assertThat( row.getData().get("Currency").toString(), equalTo("Dollar") );
        assertThat( row.getMeta().getSourceCount(), equalTo(2L) );
        assertThat( row.getMeta().getSourceIndex(), equalTo(1L) );
        assertThat( row.getMeta().getIndex(), equalTo(0L) );
        assertThat( row.getMeta().getCount(), equalTo(1L) );

        ctx = ctxIt.next();
        row = ctx.rowIterator().iterator().next();
        assertThat( row.getData().get("Code").toString(), equalTo("GBP") );
        assertThat( row.getData().get("Currency").toString(), equalTo("Pound") );
        assertThat( row.getMeta().getSourceCount(), equalTo(3L) );
        assertThat( row.getMeta().getSourceIndex(), equalTo(2L) );
        assertThat( row.getMeta().getIndex(), equalTo(0L) );
        assertThat( row.getMeta().getCount(), equalTo(1L) );

        assertThat(ctxIt.hasNext(), is(false));

    }

    @Test public void produceAContextForEachRowInCsvFile() {

        OneContextPerCsvLineStaxLike sut = new OneContextPerCsvLineStaxLike(
                new CsvRowSource(
                        new InputStreamReader( Object.class.getResourceAsStream("/codeAndCountry.csv") ) ), mockPrototype());

        Iterator<ContextModel> ctxIt = sut.contexts().iterator();
        IRowModel ctx;
        ContextModel ctxx;

        ctxx = ctxIt.next();
        ctx = ctxx.rowIterator().iterator().next();
        assertThat( ctx.getData().get("Code").toString(), equalTo("IT") );
        assertThat( ctx.getData().get("Country").toString(), equalTo("Italy") );
        assertThat( ctx.getData().get("Continent").toString(), equalTo("Europe") );
        assertThat( ctx.getMeta().getSourceIndex(), equalTo(0L) );
        assertThat( ctx.getMeta().getSourceCount(), equalTo(1L) );
        assertThat( ctx.getMeta().getIndex(), equalTo(0L) );
        assertThat( ctx.getMeta().getCount(), equalTo(1L) );

        ctxx = ctxIt.next();
        ctx = ctxx.rowIterator().iterator().next();
        assertThat( ctx.getData().get("Code").toString(), equalTo("DE") );
        assertThat( ctx.getData().get("Country").toString(), equalTo("Germany") );
        assertThat( ctx.getData().get("Continent").toString(), equalTo("Europe") );
        assertThat( ctx.getMeta().getSourceIndex(), equalTo(1L) );
        assertThat( ctx.getMeta().getSourceCount(), equalTo(2L) );
        assertThat( ctx.getMeta().getIndex(), equalTo(0L) );
        assertThat( ctx.getMeta().getCount(), equalTo(1L) );

        ctxx = ctxIt.next();
        ctx = ctxx.rowIterator().iterator().next();
        assertThat( ctx.getData().get("Code").toString(), equalTo("FR") );
        assertThat( ctx.getData().get("Country").toString(), equalTo("France") );
        assertThat( ctx.getData().get("Continent").toString(), equalTo("Europe") );
        assertThat( ctx.getMeta().getSourceIndex(), equalTo(2L) );
        assertThat( ctx.getMeta().getSourceCount(), equalTo(3L) );
        assertThat( ctx.getMeta().getIndex(), equalTo(0L) );
        assertThat( ctx.getMeta().getCount(), equalTo(1L) );

        ctxx = ctxIt.next();
        ctx = ctxx.rowIterator().iterator().next();
        assertThat( ctx.getData().get("Code").toString(), equalTo("ES") );
        assertThat( ctx.getData().get("Country").toString(), equalTo("Spain") );
        assertThat( ctx.getData().get("Continent").toString(), equalTo("Europe") );
        assertThat( ctx.getMeta().getSourceIndex(), equalTo(3L) );
        assertThat( ctx.getMeta().getSourceCount(), equalTo(4L) );
        assertThat( ctx.getMeta().getIndex(), equalTo(0L) );
        assertThat( ctx.getMeta().getCount(), equalTo(1L) );

        ctxx = ctxIt.next();
        ctx = ctxx.rowIterator().iterator().next();
        assertThat( ctx.getData().get("Code").toString(), equalTo("US") );
        assertThat( ctx.getData().get("Country").toString(), equalTo("United States") );
        assertThat( ctx.getData().get("Continent").toString(), equalTo("North America") );
        assertThat( ctx.getMeta().getSourceIndex(), equalTo(4L) );
        assertThat( ctx.getMeta().getSourceCount(), equalTo(5L) );
        assertThat( ctx.getMeta().getIndex(), equalTo(0L) );
        assertThat( ctx.getMeta().getCount(), equalTo(1L) );

        ctxx = ctxIt.next();
        ctx = ctxx.rowIterator().iterator().next();
        assertThat( ctx.getData().get("Code").toString(), equalTo("EG") );
        assertThat( ctx.getData().get("Country").toString(), equalTo("Egypt") );
        assertThat( ctx.getData().get("Continent").toString(), equalTo("Africa") );
        assertThat( ctx.getMeta().getSourceIndex(), equalTo(5L) );
        assertThat( ctx.getMeta().getSourceCount(), equalTo(6L) );
        assertThat( ctx.getMeta().getIndex(), equalTo(0L) );
        assertThat( ctx.getMeta().getCount(), equalTo(1L) );

        ctxx = ctxIt.next();
        ctx = ctxx.rowIterator().iterator().next();
        assertThat( ctx.getData().get("Code").toString(), equalTo("SA") );
        assertThat( ctx.getData().get("Country").toString(), equalTo("South Africa") );
        assertThat( ctx.getData().get("Continent").toString(), equalTo("Africa") );
        assertThat( ctx.getMeta().getSourceIndex(), equalTo(6L) );
        assertThat( ctx.getMeta().getSourceCount(), equalTo(7L) );
        assertThat( ctx.getMeta().getIndex(), equalTo(0L) );
        assertThat( ctx.getMeta().getCount(), equalTo(1L) );

        ctxx = ctxIt.next();
        ctx = ctxx.rowIterator().iterator().next();
        assertThat( ctx.getData().get("Code").toString(), equalTo("CH") );
        assertThat( ctx.getData().get("Country").toString(), equalTo("China") );
        assertThat( ctx.getData().get("Continent").toString(), equalTo("Asia") );
        assertThat( ctx.getMeta().getSourceIndex(), equalTo(7L) );
        assertThat( ctx.getMeta().getSourceCount(), equalTo(8L) );
        assertThat( ctx.getMeta().getIndex(), equalTo(0L) );
        assertThat( ctx.getMeta().getCount(), equalTo(1L) );

        ctxx = ctxIt.next();
        ctx = ctxx.rowIterator().iterator().next();
        assertThat( ctx.getData().get("Code").toString(), equalTo("RU") );
        assertThat( ctx.getData().get("Country").toString(), equalTo("Russia") );
        assertThat( ctx.getData().get("Continent").toString(), equalTo("Asia") );
        assertThat( ctx.getMeta().getSourceIndex(), equalTo(8L) );
        assertThat( ctx.getMeta().getSourceCount(), equalTo(9L) );
        assertThat( ctx.getMeta().getIndex(), equalTo(0L) );
        assertThat( ctx.getMeta().getCount(), equalTo(1L) );

        assertThat(ctxIt.hasNext(), is(false));

    }

}
