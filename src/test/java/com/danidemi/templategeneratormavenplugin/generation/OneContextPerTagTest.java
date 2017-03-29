package com.danidemi.templategeneratormavenplugin.generation;

import com.danidemi.templategeneratormavenplugin.generation.IncludeAllRowFilter;
import com.danidemi.templategeneratormavenplugin.generation.OneContextPerTag;
import org.junit.Test;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class OneContextPerTagTest {

    @Test
    public void shouldGroupRowsByTags() {

        // given
        File csv = new File(getClass().getResource("/organizational.csv").getFile());

        OneContextPerTag sut = new OneContextPerTag(csv.getAbsolutePath(), new IncludeAllRowFilter());
        sut.addTagExpression("@{Head}");
        sut.addTagExpression("@{Worker}");

        // when
        Iterator<Map<String, Object>> ctxs = sut.iterator();

        // then
        {
            // all rows about Ashley Delarosa
            Map<String, Object> ctx = ctxs.next();
            assertTrue( ctx.keySet().toString(), ctx.containsKey("File") );
            List<Map<String, Object>> rows = (List<Map<String, Object>>) ctx.get("File");
            assertRow(rows, 0, "HQ", "Dafne Nuvolari", "Ashley Delarosa");
            assertRow(rows, 1, "Account", "Ashley Delarosa", "San Hye-young");
            assertRow(rows, 2, "Account", "Ashley Delarosa", "Nabila Fahim");
            assertEquals( rows.toString(),3, rows.size());
        }
        {
            // all rows about Dafne Nuvolari
            Map<String, Object> ctx = ctxs.next();
            assertTrue( ctx.keySet().toString(), ctx.containsKey("File") );
            List<Map<String, Object>> rows = (List<Map<String, Object>>) ctx.get("File");
            assertRow(rows, 0, "HQ", "Dafne Nuvolari", "Ashley Delarosa");
            assertRow(rows, 1, "HQ", "Dafne Nuvolari", "Ciara Wright");
            assertRow(rows, 2, "HQ", "Dafne Nuvolari", "Ganda Ramasamy");
            assertEquals( rows.toString(),3, rows.size());
        }
        {
            // all rows about Ciara Wright
            Map<String, Object> ctx = ctxs.next();
            assertTrue( ctx.keySet().toString(), ctx.containsKey("File") );
            List<Map<String, Object>> rows = (List<Map<String, Object>>) ctx.get("File");
            assertRow(rows, 0, "HQ", "Dafne Nuvolari", "Ciara Wright");
            assertRow(rows, 1, "IT", "Ciara Wright", "Sumiko Hiratasuka");
            assertRow(rows, 2, "IT", "Ciara Wright", "Adela Dvorak");
            assertEquals(3, rows.size());
        }
        {
            // all rows about Ganda Ramasamy
            Map<String, Object> ctx = ctxs.next();
            assertTrue( ctx.keySet().toString(), ctx.containsKey("File") );
            List<Map<String, Object>> rows = (List<Map<String, Object>>) ctx.get("File");
            assertRow(rows, 0, "HQ", "Dafne Nuvolari", "Ganda Ramasamy");
            assertRow(rows, 1, "Sale", "Ganda Ramasamy", "Sezen Muhtar");
            assertRow(rows, 2, "Sale", "Ganda Ramasamy", "Gabriela Telles Salgado");
            assertEquals(3, rows.size());
        }
        {
            // all rows about Sumiko Hiratasuka
            Map<String, Object> ctx = ctxs.next();
            assertTrue( ctx.keySet().toString(), ctx.containsKey("File") );
            List<Map<String, Object>> rows = (List<Map<String, Object>>) ctx.get("File");
            assertRow(rows, 0, "IT", "Ciara Wright", "Sumiko Hiratasuka");
            assertEquals(1, rows.size());
        }
        {
            // all rows about Adela Dvorak
            Map<String, Object> ctx = ctxs.next();
            assertTrue( ctx.keySet().toString(), ctx.containsKey("File") );
            List<Map<String, Object>> rows = (List<Map<String, Object>>) ctx.get("File");
            assertRow(rows, 0, "IT", "Ciara Wright", "Adela Dvorak");
            assertEquals(1, rows.size());
        }
        {
            // all rows about San Hye-young
            Map<String, Object> ctx = ctxs.next();
            assertTrue( ctx.keySet().toString(), ctx.containsKey("File") );
            List<Map<String, Object>> rows = (List<Map<String, Object>>) ctx.get("File");
            assertRow(rows, 0, "Account", "Ashley Delarosa", "San Hye-young");
            assertEquals(1, rows.size());
        }
        {
            // all rows about Nabila Fahim
            Map<String, Object> ctx = ctxs.next();
            assertTrue( ctx.keySet().toString(), ctx.containsKey("File") );
            List<Map<String, Object>> rows = (List<Map<String, Object>>) ctx.get("File");
            assertRow(rows, 0, "Account", "Ashley Delarosa", "Nabila Fahim");
            assertEquals(1, rows.size());
        }
        {
            // all rows about Nabila Fahim
            Map<String, Object> ctx = ctxs.next();
            assertTrue( ctx.keySet().toString(), ctx.containsKey("File") );
            List<Map<String, Object>> rows = (List<Map<String, Object>>) ctx.get("File");
            assertRow(rows, 0, "Sale", "Ganda Ramasamy", "Sezen Muhtar");
            assertEquals(1, rows.size());
        }
        {
            // all rows about Gabriela Telles Salgado
            Map<String, Object> ctx = ctxs.next();
            assertTrue( ctx.keySet().toString(), ctx.containsKey("File") );
            List<Map<String, Object>> rows = (List<Map<String, Object>>) ctx.get("File");
            assertRow(rows, 0, "Sale", "Ganda Ramasamy", "Gabriela Telles Salgado");
            assertRow(rows, 1, "Sale", "Gabriela Telles Salgado", "Ida Pettersson");
            assertEquals(2, rows.size());
        }


    }

    private void assertRow(List<Map<String, Object>> rows, int index, String expectedDepartment, String expectedHead, String expectedSubordinate) {
        assertNotNull(rows);
        assertNotNull(expectedDepartment);
        assertNotNull(expectedHead);
        assertNotNull(expectedSubordinate);
        assertEquals(rows.toString(), expectedDepartment, rows.get(index).get("Department") );
        assertEquals(rows.toString(), expectedHead, rows.get(index).get("Head") );
        assertEquals(rows.toString(), expectedSubordinate, rows.get(index).get("Worker") );
    }

}