/**
 * The MTS support core test project contains client unit tests for the core CDS Framework Middle Tier Service.
 *
 * Copyright (C) 2016 New York City Department of Health and Mental Hygiene, Bureau of Immunization
 * Contributions by HLN Consulting, LLC
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU
 * Lesser General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version. You should have received a copy of the GNU Lesser
 * General Public License along with this program. If not, see <http://www.gnu.org/licenses/> for more
 * details.
 *
 * The above-named contributors (HLN Consulting, LLC) are also licensed by the New York City
 * Department of Health and Mental Hygiene, Bureau of Immunization to have (without restriction,
 * limitation, and warranty) complete irrevocable access and rights to this project.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; THE
 * SOFTWARE IS PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING,
 * BUT NOT LIMITED TO, WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE COPYRIGHT HOLDERS, IF ANY, OR DEVELOPERS BE LIABLE FOR
 * ANY CLAIM, DAMAGES, OR OTHER LIABILITY OF ANY KIND, ARISING FROM, OUT OF, OR IN CONNECTION WITH
 * THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * For more information about this software, see https://www.hln.com/services/open-source/ or send
 * correspondence to ice@hln.com.
 */
package org.cdsframework.mts.support.core.test;

import java.text.ParseException;
import org.cdsframework.exceptions.MtsException;
import org.cdsframework.util.NumberUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class NumberUtilsTest {

    public NumberUtilsTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Tests that the getRandomLong method from the NumberUtils class returns
     * a number between 1L and 9999999999L.
     */
    @Test
    public void testGetRandomLongWithoutParameter() {
        System.out.println("testing getRandomLongWithoutParameter");
        int i = 0;
        while (i < 100) {
            long result = NumberUtils.getRandomLong();
            System.out.println(result);
            assertTrue(result <= 9999999999L);
            assertTrue(result > 0L);
            i++;
        }
    }

    /**
     * Tests that the getRandomLong method from the NumberUtils class returns
     * a number between 1L and 10L.
     */
    @Test
    public void testGetRandomLongWithParameter() {
        System.out.println("testing getRandomLongWithParameter");
        int i = 0;
        while (i < 100) {
            long result = NumberUtils.getRandomLong(10);
            System.out.println(result);
            assertTrue(result <= 10);
            assertTrue(result > 0L);
            i++;
        }
    }
    
    @Test
    public void testObjectToInteger() throws MtsException, ParseException {
        System.out.println("testing testObjectToInteger");
        String s = "2147483647";
        Integer t = new Integer(s);
        Integer obj = NumberUtils.objectToInteger(t);
        assertTrue(obj.equals(t));

        obj = NumberUtils.objectToInteger(s);
        assertTrue(obj.equals(t));
    }
    
    @Test
    public void testObjectToLong() throws MtsException, ParseException {
        System.out.println("testing testObjectToLong");
        String s = "9223372036854775807";
        Long t = new Long(s);
        Long obj = NumberUtils.objectToLong(t);
        assertTrue(obj.equals(t));

        obj = NumberUtils.objectToLong(s);
        assertTrue(obj.equals(t));
    }

    @Test
    public void testObjectToFloat() throws MtsException, ParseException {
        System.out.println("testing testObjectToFloat");
        String s = "12415123123.67124123";
        Float t = new Float(s);
        Float obj = NumberUtils.objectToFloat(t);
        assertTrue(obj.equals(t));

        obj = NumberUtils.objectToFloat(s);
        assertTrue(obj.equals(t));
    }

    @Test
    public void testObjectToDouble() throws MtsException, ParseException {
        System.out.println("testing testObjectToDoublet");
        String s = "12415123123.6712";
        Double t = new Double(s);
        Double obj = NumberUtils.objectToDouble(t);
        assertTrue(obj.equals(t));

        obj = NumberUtils.objectToDouble(s);
        assertTrue(obj.equals(t));
    }

}