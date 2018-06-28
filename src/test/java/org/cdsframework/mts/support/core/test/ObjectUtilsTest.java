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

import java.math.BigDecimal;
import org.cdsframework.util.NumberUtils;
import org.cdsframework.util.ObjectUtils;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author HLN Consulting, LLC
 */
public class ObjectUtilsTest {

    public ObjectUtilsTest() {
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
    public void testObjectToIntegerMaxInteger() {
        Integer maxInteger = new Integer(Integer.MAX_VALUE);
        Integer objectToInteger = ObjectUtils.objectToInteger(maxInteger);
        assertTrue(objectToInteger == maxInteger.intValue());
    }    
    
    @Test
    public void testObjectToIntegerMaxLong() {
        Long maxLong = new Long(Long.MAX_VALUE);
        try {
            ObjectUtils.objectToInteger(maxLong);
            fail("Value of Long was converted to an Integer");
        }
        catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().indexOf("Received a Long") >= 0);
        }
    }    
    
    @Test
    public void testObjectToIntegerLong() {
        Long valueOf = Long.valueOf(922307L);
        Integer objectToInteger = ObjectUtils.objectToInteger(valueOf);
        assertTrue(objectToInteger == valueOf.intValue());
    }
    
    @Test
    public void testObjectToInteger() {
        Integer valueOf = Integer.valueOf(100);
        Integer objectToInteger = ObjectUtils.objectToInteger(valueOf);
        assertTrue(objectToInteger == valueOf.intValue());
    }
    
    @Test
    public void testObjectToIntegerString() {
        Integer maxInteger = new Integer(Integer.MAX_VALUE);
        Integer objectToInteger = ObjectUtils.objectToInteger(maxInteger.toString());
        assertTrue(objectToInteger == maxInteger.intValue());
    }    

    @Test
    public void testObjectToIntegerBigDecimal() {
        BigDecimal bigDecimal = new BigDecimal(523123);            
        Integer objectToInteger = ObjectUtils.objectToInteger(bigDecimal);
        assertTrue(objectToInteger == bigDecimal.intValue());
    }    

    @Test
    public void testObjectToIntegerMaxBigDecimal() {
        BigDecimal maxBigDecimal = new BigDecimal(Float.MAX_VALUE);            
        try {
            ObjectUtils.objectToInteger(maxBigDecimal);
            fail("Value of BigDecimal was converted to an Integer");
        }
        catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().indexOf("Received a BigDecimal") >= 0);
        }
    }    
    
    @Test
    public void testObjectToIntegerError() {
        Long valueOf = Long.valueOf(-1L);
        Integer objectToInteger = ObjectUtils.objectToInteger(valueOf);
        assertTrue(objectToInteger == valueOf.intValue());
    }    



    @Test
    public void testObjectToLongMaxLong() {
        Long maxLong = Long.valueOf(Long.MAX_VALUE);
        Long objectToLong = ObjectUtils.objectToLong(maxLong);
        assertTrue(objectToLong == maxLong.longValue());
    }    
    
    @Test
    public void testObjectToLong() {
        Long valueOf = Long.valueOf(614123123L);
        Long objectToLong = ObjectUtils.objectToLong(valueOf);
        assertTrue(objectToLong == valueOf.longValue());
    }    

    @Test
    public void testObjectToLongInteger() {
        Integer valueOf = Integer.valueOf(1212);
        Long objectToLong = ObjectUtils.objectToLong(valueOf);
        assertTrue(objectToLong == valueOf.intValue());
    }    
    
    @Test
    public void testObjectToLongBigDecimalError() {
        BigDecimal bigDecimal = new BigDecimal(0);
        Long objectToLong = ObjectUtils.objectToLong(bigDecimal);
        assertTrue(objectToLong == bigDecimal.longValue());
    }    

    @Test
    public void testObjectToLongString() {
        Long valueOf = Long.valueOf(Long.MAX_VALUE);
        Long objectToLong = ObjectUtils.objectToLong(valueOf.toString());
        assertTrue(objectToLong == valueOf.longValue());
    }    

    @Test
    public void testObjectToLongBigDecimal() {
        BigDecimal bigDecimal = new BigDecimal(523123);            
        Long objectToLong = ObjectUtils.objectToLong(bigDecimal);
        assertTrue(objectToLong == bigDecimal.longValue());
    }    
    
    @Test
    public void testObjectToLongMaxBigDecimal() {
        BigDecimal maxBigDecimal = new BigDecimal(Float.MAX_VALUE);            
        try {
            ObjectUtils.objectToLong(maxBigDecimal);
            fail("Value of BigDecimal was converted to an Long");
        }
        catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().indexOf("Received a BigDecimal") >= 0);
        }
    }    
    

}
