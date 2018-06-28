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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import org.cdsframework.util.DateUtils;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.TimeZone;
import org.cdsframework.dto.AppDTO;
import org.cdsframework.dto.UserDTO;
import org.cdsframework.enumeration.DTOState;
import org.cdsframework.exceptions.MtsException;
import org.cdsframework.util.DTOUtils;
import org.cdsframework.util.ObjectUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DateUtilsTest {

    private static Date testDate = new Date(86400000L * 365 * 31);
    private static Timestamp timestamp = new Timestamp(86400000L * 365 * 31);

    public DateUtilsTest() {
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

    @Test
    public void testDateISO8601() {
        //yyyyMMddHHmmssz
        String dateIso = "19940613160001MST";
        Date now = new Date();
        String isoDte = DateUtils.getFormattedDate(now, DateUtils.ISO8601_DATETIME_FORMAT);
        System.out.println("isoDte=" + isoDte);

        Date d = DateUtils.parseDateFromString(dateIso, DateUtils.ISO8601_DATETIME_FORMAT);
        System.out.println("d=" + d);

        dateIso = "19940613160001";
        d = DateUtils.parseDateFromString(dateIso, DateUtils.ISO8601_DATETIME_FORMAT);
        System.out.println("d=" + d);

        dateIso = "19940613160001";
        d = DateUtils.parseDateFromString(dateIso, DateUtils.ISO8601_DATETIME_FORMAT);
        System.out.println("d=" + d);

        isoDte = DateUtils.getFormattedDate(now, DateUtils.ISO8601_DATETIME_FORMAT);
        System.out.println("isoDte=" + isoDte);
        
        
    }
    
    /**
     * Test of getDateDiffCombo method, of class DateUtils.
     */
    @Test
    public void testGetDateDiffCombo() {
        System.out.println("test getDateDiffCombo");
        GregorianCalendar c1 = new GregorianCalendar();
        GregorianCalendar c2 = new GregorianCalendar();
        c1.set(2000, 1, 1);
        c2.set(2010, 1, 2);
        System.out.println("Date: " + DateUtils.getDateDiffCombo(c1.getTime(), c2.getTime()));

    }

    /**
     * Test of getYear method, of class DateUtils.
     */
    @Test
    public void testGetYear() {
        System.out.println("test getYear");
        int expResult = 2000;
        int result = DateUtils.getYear(testDate);
        assertEquals(expResult, result);
    }

    /**
     * Test of getMonth method, of class DateUtils.
     */
    @Test
    public void testGetMonth() {
        System.out.println("test getMonth");
        int expResult = 12;
        int result = DateUtils.getMonth(testDate);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDay method, of class DateUtils.
     */
    @Test
    public void testGetDay() {
        System.out.println("test getDay");
        int expResult = 23;
        int result = DateUtils.getDay(testDate);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDateLastOfMonth method, of class DateUtils.
     */
    @Test
    public void testGetDateLastOfMonth() {
        System.out.println("test getDateLastOfMonth");
        Date expResult = new Date((86400000L * 11323) - 68400000);
        Date result = DateUtils.getDateLastOfMonth(testDate);
        assertEquals(expResult, result);
    }

    /**
     * Test of getLastDayOfMonth method, of class DateUtils.
     */
    @Test
    public void testGetLastDayOfMonth() {
        System.out.println("test getLastDayOfMonth");
        int p_year = 2000;
        int p_month = 2;
        int expResult = 29;
        int result = DateUtils.getLastDayOfMonth(p_year, p_month);
        assertEquals(expResult, result);
    }

    /**
     * Test of getFormattedDate method, of class DateUtils.
     */
    @Test
    public void testGetFormattedDate_Date() {
        System.out.println("test getFormattedDate");
        String expResult = "2000-12-23";
        String result = DateUtils.getFormattedDate(testDate);
        assertEquals(expResult, result);
    }

    /**
     * Test of getFormattedDateInMask method, of class DateUtils.
     */
    @Test
    public void testGetFormattedDateInMask() {
        System.out.println("test getFormattedDateInMask");
        String expResult = "12/23/2000";
        String result = DateUtils.getFormattedDateInMask(testDate);
        assertEquals(expResult, result);
    }

    /**
     * Test of getFormattedModDateMask method, of class DateUtils.
     */
    @Test
    public void testGetFormattedModDateMask() {
        System.out.println("test getFormattedModDateMask");
        String expResult = "20001223190000";
        String result = DateUtils.getFormattedModDateMask(testDate);
        assertEquals(expResult, result);
    }

    /**
     * Test of getLogDate method, of class DateUtils.
     */
    @Test
    public void testGetLogDate() {
        System.out.println("test getLogDate");
        Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
        String result = DateUtils.getLogDate();
        Matcher matcher = pattern.matcher(result);
        assertTrue(matcher.matches());
    }

    /**
     * Test of getFormattedDate method, of class DateUtils.
     */
    @Test
    public void testGetFormattedDate_Date_String() {
        System.out.println("test getFormattedDate");
        String expResult = "12/23/2000";
        String result = DateUtils.getFormattedDate(testDate, DateUtils.DATEINMASK);
        assertEquals(expResult, result);
    }

    /**
     * Test of getLastModDateTime method, of class DateUtils.
     */
    @Test
    public void testGetLastModDateTime() {
        System.out.println("test getLastModDateTime");
        Date result = DateUtils.getLastModDateTime(timestamp);
        assertEquals(testDate, result);
    }

    /**
     * Test 1 of parseDateFromString method, of class DateUtils.
     *
     * @throws ParseException
     */
    @Test
    public void testParseDateFromString1() throws ParseException {
        System.out.println("test parseDateFromString1");
        String p_input = "12/23/2000 19:00";
        String p_fmt_string = "MM/dd/yyyy HH:mm";
        Date result = DateUtils.parseDateFromString(p_input, p_fmt_string);
        assertEquals(testDate, result);
    }

    /**
     * Test 4 of parseDateFromString method, of class DateUtils.
     *
     * @throws ParseException
     */
    @Test
    public void testParseDateFromString4() throws ParseException {
        System.out.println("test parseDateFromString4");
        String p_input = "12/23/00 19:00";
        String p_fmt_string = "MM/dd/yyyy HH:mm";
        try {
            Date result = DateUtils.parseDateFromString(p_input, p_fmt_string);
            fail("Expected IllegalArgumentException but got " + result.toString());
        } catch (Exception e) {
            if (!(e instanceof IllegalArgumentException)) {
                fail("Expected IllegalArgumentException but got " + e.getClass().getName() + " - " + e.getMessage());
            }
        }
    }

    /**
     * Test 5 of parseDateFromString method, of class DateUtils.
     *
     * @throws ParseException
     */
    @Test
    public void testParseDateFromString5() throws ParseException {
        System.out.println("test parseDateFromString5");
        String p_input = "01/01/00";
        String p_fmt_string = "MM/dd/yyyy";
        try {
            Date result = DateUtils.parseDateFromString(p_input, p_fmt_string);
            fail("Expected IllegalArgumentException but got " + result.toString());
        } catch (Exception e) {
            if (!(e instanceof IllegalArgumentException)) {
                fail("Expected IllegalArgumentException but got " + e.getClass().getName() + " - " + e.getMessage());
            }
        }
    }

    /**
     * Test of isDate method, of class DateUtils.
     */
    @Test
    public void testIsDate() {
        System.out.println("test isDate");
        String p_inputTrue = "12/23/2000 19:00";
        String p_inputFalse = "2000/12/23 19:00";
        String p_fmt_string = "MM/dd/yyyy HH:mm";
        assertFalse(DateUtils.isDate(p_inputFalse, p_fmt_string));
        assertTrue(DateUtils.isDate(p_inputTrue, p_fmt_string));
    }

    /**
     * Test of isValidTenCharDateWithWildcards method, of class DateUtils.
     */
    @Test
    public void testIsValidTenCharDateWithWildcards() {
        System.out.println("test isValidTenCharDateWithWildcards");
        assertTrue(DateUtils.isValidTenCharDateWithWildcards("01/**/20**"));
        assertFalse(DateUtils.isValidTenCharDateWithWildcards("01/*/20**"));
    }

    /**
     * Test of getDaysDifference method, of class DateUtils.
     */
    @Test
    public void testGetDaysDifference() {
        System.out.println("test getDaysDifference");
        String earlierDate = "12/22/2000";
        String laterDate = "12/24/2000";
        long expResult = 2L;
        long result = DateUtils.getDaysDifference(earlierDate, laterDate);
        assertEquals(expResult, result);
    }

    /**
     * Test of getMillisecondDifference method, of class DateUtils.
     */
    @Test
    public void testGetMillisecondDifference() {
        System.out.println("test getMillisecondDifference");
        String date1 = "12/24/2000";
        String date2 = "12/22/2000";
        long expResult = 172800000L;
        long result = DateUtils.getMillisecondDifference(date1, date2);
        assertEquals(expResult, result);
    }

    /**
     * Test of calcDateDiffMonths method, of class DateUtils.
     */
    @Test
    public void testCalcDateDiffMonths() {
        System.out.println("test calcDateDiffMonths");
        Date date1 = new Date(86400000L * 365 * 31);
        Date date2 = new Date(86400000L * 360 * 31);
        int expResult = 5;
        int result = DateUtils.calcDateDiffMonths(date1, date2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getElapsedSeconds method, of class DateUtils.
     */
    @Test
    public void testGetElapsedSeconds() {
        System.out.println("test getElapsedSeconds");
        Date startTime = new Date(86400000L * 365 * 31);
        Date endTime = new Date(86400000L * 364 * 31);
        long expResult = 2678400L;
        long result = DateUtils.getElapsedSeconds(startTime, endTime);
        assertEquals(expResult, result);
    }

    @Test
    public void testAgeInYears() {
        Date earlierDate = DateUtils.parseDateFromString("12/1/2000", "MM/dd/yyyy");
        Date laterDate = DateUtils.parseDateFromString("12/1/1880", "MM/dd/yyyy");
        int ageInYears = DateUtils.getUnitsBetweenDates(laterDate, earlierDate, Calendar.YEAR, 0, 0);
        assertEquals(ageInYears, 120);
    }

    @Test
    public void testAgeInYearsX() {
        Date earlierDate = DateUtils.parseDateFromString("12/15/1857", "MM/dd/yyyy");
        Date laterDate = DateUtils.parseDateFromString("12/15/1922", "MM/dd/yyyy");
        int age = DateUtils.getUnitsBetweenDates(laterDate, earlierDate, Calendar.YEAR, 0, 0);
        assertEquals(age, -65);
        age = DateUtils.getUnitsBetweenDates(earlierDate, laterDate, Calendar.YEAR, 0, 0);
        assertEquals(age, 65);
    }

    @Test
    public void testGetDateDiffYMD_5Day() {
        Date earlierDate = DateUtils.parseDateFromString("12/15/1857", "MM/dd/yyyy");
        Date laterDate = DateUtils.parseDateFromString("12/10/1858", "MM/dd/yyyy");
        String value = DateUtils.getDateDiffYMD(earlierDate, laterDate, true, true);
        System.out.println("test1: " + value);
        assertEquals(value, "1y -5d");

        earlierDate = DateUtils.parseDateFromString("07/01/1855", "MM/dd/yyyy");
        laterDate = DateUtils.parseDateFromString("12/27/1857", "MM/dd/yyyy");
        value = DateUtils.getDateDiffYMD(earlierDate, laterDate, true, true);
        System.out.println("test2: " + value);
        assertEquals(value, "2y 6m -5d");

        earlierDate = DateUtils.parseDateFromString("12/31/1857", "MM/dd/yyyy");
        laterDate = DateUtils.parseDateFromString("12/26/1858", "MM/dd/yyyy");
        value = DateUtils.getDateDiffYMD(earlierDate, laterDate, true, true);
        System.out.println("test3: " + value);
        assertEquals(value, "1y -5d");

        earlierDate = DateUtils.parseDateFromString("12/31/1857", "MM/dd/yyyy");
        laterDate = DateUtils.parseDateFromString("12/25/1858", "MM/dd/yyyy");
        value = DateUtils.getDateDiffYMD(earlierDate, laterDate, true, true);
        System.out.println("test4: " + value);
        assertEquals(value, "11m 25d");

        earlierDate = DateUtils.parseDateFromString("03/01/1859", "MM/dd/yyyy");
        laterDate = DateUtils.parseDateFromString("02/25/1860", "MM/dd/yyyy");
        value = DateUtils.getDateDiffYMD(earlierDate, laterDate, true, true);
        System.out.println("test5: " + value);
        assertEquals(value, "1y -5d");

        earlierDate = DateUtils.parseDateFromString("03/01/1860", "MM/dd/yyyy");
        laterDate = DateUtils.parseDateFromString("02/24/1861", "MM/dd/yyyy");
        value = DateUtils.getDateDiffYMD(earlierDate, laterDate, true, true);
        System.out.println("test6: " + value);
        assertEquals(value, "1y -5d");

    }

    @Test
    public void testGetDateDiffYMD_4Day() {
        Date earlierDate = DateUtils.parseDateFromString("12/15/1857", "MM/dd/yyyy");
        Date laterDate = DateUtils.parseDateFromString("12/11/1858", "MM/dd/yyyy");
        String value = DateUtils.getDateDiffYMD(earlierDate, laterDate, true, true);
        System.out.println("test1: " + value);
        assertEquals(value, "1y -4d");

        earlierDate = DateUtils.parseDateFromString("07/01/1855", "MM/dd/yyyy");
        laterDate = DateUtils.parseDateFromString("12/28/1857", "MM/dd/yyyy");
        value = DateUtils.getDateDiffYMD(earlierDate, laterDate, true, true);
        System.out.println("test2: " + value);
        assertEquals(value, "2y 6m -4d");

        earlierDate = DateUtils.parseDateFromString("12/31/1857", "MM/dd/yyyy");
        laterDate = DateUtils.parseDateFromString("12/27/1858", "MM/dd/yyyy");
        value = DateUtils.getDateDiffYMD(earlierDate, laterDate, true, true);
        System.out.println("test3: " + value);
        assertEquals(value, "1y -4d");

        earlierDate = DateUtils.parseDateFromString("03/01/1859", "MM/dd/yyyy");
        laterDate = DateUtils.parseDateFromString("02/26/1860", "MM/dd/yyyy");
        value = DateUtils.getDateDiffYMD(earlierDate, laterDate, true, true);
        System.out.println("test4: " + value);
        assertEquals(value, "1y -4d");

        earlierDate = DateUtils.parseDateFromString("03/01/1860", "MM/dd/yyyy");
        laterDate = DateUtils.parseDateFromString("02/25/1861", "MM/dd/yyyy");
        value = DateUtils.getDateDiffYMD(earlierDate, laterDate, true, true);
        System.out.println("test5: " + value);
        assertEquals(value, "1y -4d");

        earlierDate = DateUtils.parseDateFromString("12/01/2009", "MM/dd/yyyy");
        laterDate = DateUtils.parseDateFromString("11/27/2010", "MM/dd/yyyy");
        value = DateUtils.getDateDiffYMD(earlierDate, laterDate, true, true);
        System.out.println("test6: " + value);
        assertEquals(value, "1y -4d");

    }

    @Test
    public void testDatePrecision() throws ParseException {
        System.out.println("testDatePrecision");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        long time = dateFormat.parse("23/09/2007").getTime();
        Timestamp ts = new Timestamp(time);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2007, 8, 23, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date date = calendar.getTime();
        System.out.println("ts: " + ts);
        System.out.println("date: " + date);
        System.out.println("compare ts to date: " + ts.equals(date));
        System.out.println("compare date to ts: " + date.equals(ts));
        assertTrue(date.equals(ts));
        assertFalse(ts.equals(date));
    }
    
    @Test
    public void testUTCDate() {
        String utcDate = "2016-05-29T04:00:00.000+0000";
        System.out.println(utcDate.length());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        Object dd = null;
        try {
            dd = sdf.parse("2016-05-29T04:00:00.000+0000");
            System.out.println(dd);
            System.out.println(DateUtils.getFormattedDate( ((Date) dd), DateUtils.ISO8601_DATETIME_FORMAT));
            dd = sdf.parse("2013-05-29T20:39:22.000+0000");
            System.out.println(dd);
            System.out.println(DateUtils.getFormattedDate( ((Date) dd), DateUtils.ISO8601_DATETIME_FORMAT));
        } catch (ParseException e) {
            e.printStackTrace();
        }        

        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        //sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");        
        dd = null;
        try {
            dd = sdf.parse("2016-05-29T04:00:00.000+0000");
            System.out.println(dd);
            System.out.println(DateUtils.getFormattedDate( ((Date) dd), DateUtils.ISO8601_DATETIME_FORMAT));
            dd = sdf.parse("2013-05-29T20:39:22.000+0000");
            System.out.println(dd);
            System.out.println(DateUtils.getFormattedDate( ((Date) dd), DateUtils.ISO8601_DATETIME_FORMAT));
        } catch (ParseException e) {
            e.printStackTrace();
        }        
    }    
    
    @Test
    public void testISO8601DateTimeFormat() throws ParseException {
        String s = "20130529203922EDT";
        System.out.println(s.length());        
        Date parseISODatetimeFormat = DateUtils.parseISODatetimeFormat("20130529203922EDT");
        System.out.println(parseISODatetimeFormat);
        /*
        parseISODatetimeFormat = DateUtils.parseISODatetimeFormat("20130529203922");
        System.out.println(parseISODatetimeFormat);        
        */
        //parseISODatetimeFormat = DateUtils.parseISODatetimeFormat("201305292039");
        //System.out.println(parseISODatetimeFormat);        
        //parseISODatetimeFormat = DateUtils.parseISODatetimeFormat("2013052920");
        //System.out.println(parseISODatetimeFormat);        
        parseISODatetimeFormat = DateUtils.parseISODatetimeFormat("20130529");
        System.out.println(parseISODatetimeFormat);        
        
        
    }
    

}
