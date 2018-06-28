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

import org.cdsframework.util.UuidUtils;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author sdn
 */
public class UuidUtilsTest {

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
     * Test of isUuid method, of class UuidUtils.
     * @throws Exception
     */
    @Test
    public void testIsUuid() throws Exception {
        System.out.println("isUuid");
        String testString = "550e8400-e29b-41d4-a716-446655440000";
        boolean testStringExpResult = true;
        boolean result = UuidUtils.isUuid(testString);
        System.out.println("RESULT: " + result);
        assertEquals(testStringExpResult, result);
        testString = "550e8400e29b41d4a716446655440000";
        testStringExpResult = true;
        result = UuidUtils.isUuid(testString);
        System.out.println("RESULT: " + result);
        assertEquals(testStringExpResult, result);
        testString = "1233256481911";
        testStringExpResult = false;
        result = UuidUtils.isUuid(testString);
        System.out.println("RESULT: " + result);
        assertEquals(testStringExpResult, result);
    }
}
