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
package org.cdsframework.mts.support.core.test.validation;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author HLN Consulting, LLC
 */
public class ValidationTest {
    private Validator validator;
    
    public ValidationTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        this.validator = vf.getValidator();        
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testMinMax() {
        System.out.println("testMinMax");
        ValidationPojo validationPojo = new ValidationPojo();
        validationPojo.setTestInteger(null);
        validationPojo.setTestString("test");
        Set<ConstraintViolation<ValidationPojo>> violations = this.validator.validate(validationPojo);
        for (ConstraintViolation constraintViolation : violations) {
             System.out.println(constraintViolation.getMessage());
        }
        assertTrue(violations.isEmpty());
    }

    @Test    
    public void testMinMaxWithMax() {
        System.out.println("testMinMaxWithMax");
        ValidationPojo validationPojo = new ValidationPojo();
        validationPojo.setTestInteger(32);
        validationPojo.setTestString("test");
        Set<ConstraintViolation<ValidationPojo>> violations = this.validator.validate(validationPojo);
        for (ConstraintViolation constraintViolation : violations) {
             System.out.println(constraintViolation.getMessage());
        }
        assertTrue(violations.size() == 1);
    }
    

    @Test    
    public void testMinMaxWithin() {
        System.out.println("testMinMaxWithin");
        ValidationPojo validationPojo = new ValidationPojo();
        validationPojo.setTestInteger(22);
        validationPojo.setTestString("test");
        Set<ConstraintViolation<ValidationPojo>> violations = this.validator.validate(validationPojo);
        for (ConstraintViolation constraintViolation : violations) {
             System.out.println(constraintViolation.getMessage());
        }
        assertTrue(violations.isEmpty());
    }    
    
    @Test
    public void testString() {
        System.out.println("testString");
        ValidationPojo validationPojo = new ValidationPojo();
        validationPojo.setTestString("   ");
        Set<ConstraintViolation<ValidationPojo>> violations = this.validator.validate(validationPojo);
        for (ConstraintViolation constraintViolation : violations) {
             System.out.println(constraintViolation.getMessage());
        }
        assertTrue(violations.size()==1);
    }
}
