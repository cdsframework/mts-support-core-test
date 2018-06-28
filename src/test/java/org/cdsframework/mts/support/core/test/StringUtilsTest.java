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

import org.cdsframework.util.StringUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StringUtilsTest {

    public StringUtilsTest() {
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

    public static String stripCRLF(String inputString) {
        String patternString = "%0D|%0d|%0A|%0a";
        String replacementString = "";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(inputString);
        String output = matcher.replaceAll(replacementString);
        return output;
    }

    /**
     * Test of formatTelephoneWithOptionalExt
     *
     */
    @Test
    public void testFormatTelephoneWithOptionalExt() throws Exception {
        System.out.println(StringUtils.formatTelephoneWithOptionalExt("(555) 666-2345", "Ext. 234"));
    }

    /**
     * Test of stripCRLF
     *
     */
    @Test
    public void testStripCRLF() throws Exception {
        String testString = "/uat/servlet/PC?Foobar%0D%0d%0A%0aAppScanHeader:%20AppScanValue/1.2-3%0DSecondAppScanHeader:%20whatever=Foobar%0DAppScanHeader:%20AppScanValue/1.2-3%0DSecondAppScanHeader:%20whatever";
        String testStringExpResult = "";
        System.out.println(stripCRLF(testString));
    }

    /**
     * Test of unCamelize method, of class StringFunctions.
     * @throws Exception
     */
    @Test
    public void testUnCamelize() throws Exception {
        System.out.println("unCamelize");
        String testString = "Sending Application:NamespaceID";
        String testStringExpResult = "Sending Application:Namespace ID";
        String result = StringUtils.unCamelize(testString);
        System.out.println("RESULT: " + result);
        assertEquals(testStringExpResult, result);
        testString = "Responsible Observer:AssigningFacility:UniversalIDType";
        testStringExpResult = "Responsible Observer:Assigning Facility:Universal ID Type";
        result = StringUtils.unCamelize(testString);
        System.out.println("RESULT: " + result);
        assertEquals(testStringExpResult, result);
    }

    /**
     * Test of getShaHashFromString method, of class StringFunctions.
     * @throws Exception
     */
    @Test
    public void testGetPasswordHash() throws Exception {
        System.out.println("getPasswordHash");
        String password = "AirBagPilot256";
        System.out.println("password: " + password);
        String expResult = "73522ead848883f9d0c155e10cb892ec7e7ae23febcf487cf9879936fc165096";
        System.out.println("expResult: " + expResult);
        String result = StringUtils.getShaHashFromString(password);
        System.out.println("result: " + result);
        assertEquals(expResult, result);
    }

    /**
     * Test of getHashId method, of class StringFunctions.
     * @throws Exception
     */
    @Test
    public void testGetHashId_0args() throws Exception {
        System.out.println("getHashId - 0args");
        int expResult = 32;
        String hashId = StringUtils.getHashId();
        System.out.println("hashId: " + hashId);
        int result = hashId.length();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHashId method, of class StringFunctions.
     */
    @Test
    public void testGetHashId_int() {
        System.out.println("getHashId - int");
        int length = 66;
        int expResult = 66;
        String hashId = StringUtils.getHashId(length);
        System.out.println("hashId: " + hashId);
        int result = hashId.length();
        assertEquals(expResult, result);
    }

    /**
     * Test of addNameIndex method, of class StringUtils.
     */
    @Test
    public void testAddNameIndexPreviousIndex() {
        System.out.println("addNameIndex");
        String name = "gh [3456] ost   [    1234   ]    ";
        String expResult = "gh [3456] ost [1235]";
        String result = StringUtils.addNameIndex(name);
        assertEquals(expResult, result);
    }

    @Test
    public void testAddNameIndexNoIndex() {
        System.out.println("addNameIndex");
        String name = "gh [3456] ost   ";
        String expResult = "gh [3456] ost [1]";
        String result = StringUtils.addNameIndex(name);
        assertEquals(expResult, result);
    }

    @Test
    public void testAddNameIndexStringIndex() {
        System.out.println("addNameIndex");
        String name = "gh [3456] ost   [   dfadfgdf   ]    ";
        String expResult = "gh [3456] ost   [   dfadfgdf   ] [1]";
        String result = StringUtils.addNameIndex(name);
        assertEquals(expResult, result);
    }

    @Test
    public void testStripNonAlphaNumberic() {
        System.out.println("testStripNonAlphaNumberic");
        String maidenName = "camachovega";
        maidenName = "camacho-vega";
        String strippedMaidenName = StringUtils.stripNonAlphaNumberic(maidenName, false, false, true);
        System.out.println(strippedMaidenName);
    }
}
