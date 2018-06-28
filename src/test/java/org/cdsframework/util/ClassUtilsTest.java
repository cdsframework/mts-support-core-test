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
package org.cdsframework.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import org.cdsframework.dto.SystemPropertyDTO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author HLN Consulting, LLC
 */
public class ClassUtilsTest {

    private static final LogUtils logger = LogUtils.getLogger(ClassUtilsTest.class);

    public class ExtendedSystemPropertyDTO extends SystemPropertyDTO {

        private static final long serialVersionUID = -59164895275116631L;

        private String randomProperty;

        /**
         * Get the value of randomProperty
         *
         * @return the value of randomProperty
         */
        public String getRandomProperty() {
            return randomProperty;
        }

        /**
         * Set the value of randomProperty
         *
         * @param randomProperty new value of randomProperty
         */
        public void setRandomProperty(String randomProperty) {
            this.randomProperty = randomProperty;
        }

    }

    public ClassUtilsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getDeclaredFieldsTest() {
        final String METHODNAME = "getDeclaredFieldsTest ";
        List<Field> declaredFields = Arrays.asList(ExtendedSystemPropertyDTO.class.getDeclaredFields());
        for (Field item : declaredFields) {
            logger.info(METHODNAME, "item.getName()=", item.getName(), "; item.getDeclaringClass()=", item.getDeclaringClass());
        }
        assertTrue(declaredFields.size() == 3);
        logger.info("-------------------------------------------------------------------------------------------");
        declaredFields = ClassUtils.getNonBaseDTODeclaredFields(ExtendedSystemPropertyDTO.class);
        for (Field item : declaredFields) {
            logger.info(METHODNAME, "item.getName()=", item.getName(), "; item.getDeclaringClass()=", item.getDeclaringClass());
        }
        assertTrue(declaredFields.size() == 20);
    }

    @Test
    public void getDeclaredFieldTest() throws NoSuchFieldException {
        final String METHODNAME = "getDeclaredFieldTest ";
        String testFieldName = "propertyId";
        Field declaredField;
        try {
            declaredField = ExtendedSystemPropertyDTO.class.getDeclaredField(testFieldName);
            logger.info(METHODNAME, "declaredField.getName()=", declaredField.getName(), "; declaredField.getDeclaringClass()=", declaredField.getDeclaringClass());
        } catch (NoSuchFieldException e) {
            assertEquals(e.getClass(), NoSuchFieldException.class);
        }

        logger.info("-------------------------------------------------------------------------------------------");
        testFieldName = "randomProperty";

        declaredField = ExtendedSystemPropertyDTO.class.getDeclaredField(testFieldName);
        assertNotNull(declaredField);
        assertEquals(declaredField.getDeclaringClass(), ExtendedSystemPropertyDTO.class);
        logger.info(METHODNAME, "declaredField.getName()=", declaredField.getName(), "; declaredField.getDeclaringClass()=", declaredField.getDeclaringClass());

        logger.info("-------------------------------------------------------------------------------------------");
        testFieldName = "propertyId";

        declaredField = ClassUtils.getDeclaredField(ExtendedSystemPropertyDTO.class, testFieldName);
        assertNotNull(declaredField);
        assertEquals(declaredField.getDeclaringClass(), SystemPropertyDTO.class);
        logger.info(METHODNAME, "declaredField.getName()=", declaredField.getName(), "; declaredField.getDeclaringClass()=", declaredField.getDeclaringClass());

        logger.info("-------------------------------------------------------------------------------------------");

        testFieldName = "randomProperty";
        declaredField = ClassUtils.getDeclaredField(ExtendedSystemPropertyDTO.class, testFieldName);
        assertNotNull(declaredField);
        assertEquals(declaredField.getDeclaringClass(), ExtendedSystemPropertyDTO.class);
        logger.info(METHODNAME, "declaredField.getName()=", declaredField.getName(), "; declaredField.getDeclaringClass()=", declaredField.getDeclaringClass());
    }

}
