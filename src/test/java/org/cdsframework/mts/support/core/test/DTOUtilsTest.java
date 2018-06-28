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

import java.lang.reflect.Field;
import org.cdsframework.base.BaseDTO;
import org.cdsframework.util.DTOUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DTOUtilsTest {

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

    public class BareDTO extends BaseDTO {

    }

    @Test
    public void testBareDTOFlags() throws Exception {
        System.out.println("testing testBaseDTOFlags...");

        boolean addsChild = DTOUtils.addsChild(BareDTO.class);
        assertTrue(addsChild);
        System.out.println("DTOUtils.addsChild: " + addsChild);

        boolean deletesChild = DTOUtils.deletesChild(BareDTO.class);
        assertTrue(deletesChild);
        System.out.println("DTOUtils.deletesChild: " + deletesChild);

        boolean updatesChild = DTOUtils.updatesChild(BareDTO.class);
        assertTrue(updatesChild);
        System.out.println("DTOUtils.updatesChild: " + updatesChild);

        boolean isAddAllowed = DTOUtils.isAddAllowed(BareDTO.class);
        assertTrue(isAddAllowed);
        System.out.println("DTOUtils.isAddAllowed: " + isAddAllowed);

        boolean isUpdateAllowed = DTOUtils.isUpdateAllowed(BareDTO.class);
        assertTrue(isUpdateAllowed);
        System.out.println("DTOUtils.isUpdateAllowed: " + isUpdateAllowed);

        boolean isDeleteAllowed = DTOUtils.isDeleteAllowed(BareDTO.class);
        assertTrue(isDeleteAllowed);
        System.out.println("DTOUtils.isDeleteAllowed: " + isDeleteAllowed);

        boolean isCached = DTOUtils.isCached(BareDTO.class);
        assertFalse(isCached);
        System.out.println("DTOUtils.isCached: " + isCached);

        boolean isNoId = DTOUtils.isNoId(BareDTO.class);
        assertFalse(isNoId);
        System.out.println("DTOUtils.isNoId: " + isNoId);

        try {
            boolean isPKGeneratedSourceAuto = DTOUtils.isPKGeneratedSourceAuto(BareDTO.class);
            fail("should not have reached this point on isPKGeneratedSourceAuto");
        } catch (IllegalStateException e) {
            assertTrue(true);
            System.out.println("DTOUtils.isPKGeneratedSourceAuto: proper exception thrown...");
        }

        try {
            boolean isPKGeneratedSourceSequence = DTOUtils.isPKGeneratedSourceSequence(BareDTO.class);
            fail("should not have reached this point on isPKGeneratedSourceSequence");
        } catch (IllegalStateException e) {
            assertTrue(true);
            System.out.println("DTOUtils.isPKGeneratedSourceSequence: proper exception thrown...");
        }

        boolean isReadOnly = DTOUtils.isReadOnly(BareDTO.class);
        assertFalse(isReadOnly);
        System.out.println("DTOUtils.isReadOnly: " + isReadOnly);

        boolean isReferenceDTOsExist = DTOUtils.isReferenceDTOsExist(BaseDTO.class);
        assertFalse(isReferenceDTOsExist);
        System.out.println("DTOUtils.isReferenceDTOsExist: " + isReferenceDTOsExist);

        boolean isAutoRetrieve = DTOUtils.isAutoRetrieve(BareDTO.class, BaseDTO.class);
        assertFalse(isAutoRetrieve);
        System.out.println("DTOUtils.isAutoRetrieve: " + isAutoRetrieve);

        boolean isChildNotFoundAllowed = DTOUtils.isChildNotFoundAllowed(BareDTO.class, BaseDTO.class);
        assertTrue(isChildNotFoundAllowed);
        System.out.println("DTOUtils.isChildNotFoundAllowed: " + isChildNotFoundAllowed);

        try {
            String getPKGeneratedSourceSequence = DTOUtils.getPKGeneratedSourceSequence(BareDTO.class);
            fail("should not have reached this point on getPKGeneratedSourceSequence");
        } catch (IllegalStateException e) {
            assertTrue(true);
            System.out.println("DTOUtils.getPKGeneratedSourceSequence: proper exception thrown...");
        }

        Field foreignKeyField = DTOUtils.getForeignKeyField(BareDTO.class, BaseDTO.class);
        assertNull(foreignKeyField);
        System.out.println("DTOUtils.getForeignKeyField: " + foreignKeyField);

        try {
            System.out.println("" + DTOUtils.getPrimaryKeyClasses(BareDTO.class));
            fail("should not have reached this point on getPrimaryKeyClasses");
        } catch (IllegalStateException e) {
            assertTrue(true);
            System.out.println("DTOUtils.getPrimaryKeyClasses: proper exception thrown: " + e.getCause());
        }

        try {
            System.out.println("" + DTOUtils.getPrimaryKeyFields(BareDTO.class));
            fail("should not have reached this point on getPrimaryKeyFields");
        } catch (IllegalStateException e) {
            assertTrue(true);
            System.out.println("DTOUtils.getPrimaryKeyFields: proper exception thrown: " + e.getMessage());
        }

        System.out.println("" + DTOUtils.getReferenceDTOs(BareDTO.class));

        System.out.println("" + DTOUtils.getParentBehavior(BareDTO.class));

    }

//    // reflection vs methodHandle vs lambda vs native - only runs when you compile under Java 8
//
//    private static final int ITERATIONS = 50000000;
//    private static final int WARM_UP = 10;
//
//    @Test
//    public void testReflectiveCode() throws Exception, Throwable {
//        final int[] dummy = new int[4];
//
//        Method reflected = DTOUtilsTest.class
//                .getDeclaredMethod("myMethod", int.class, int.class);
//        final MethodHandles.Lookup lookup = MethodHandles.lookup();
//        MethodHandle mh = lookup.unreflect(reflected);
//
//        MethodType methodType = MethodType.methodType(IntBinaryOperator.class);
//        MethodType type = mh.type();
//
//        CallSite metafactory = LambdaMetafactory.metafactory(lookup, "applyAsInt", methodType, type, mh, type);
//        MethodHandle target = metafactory.getTarget();
//
//        IntBinaryOperator lambda = (IntBinaryOperator) target.invokeExact();
//
//        for (int i = 0; i < WARM_UP; i++) {
//            dummy[0] += testDirect(dummy[0]);
//            dummy[1] += testLambda(dummy[1], lambda);
//            dummy[2] += testMH(dummy[1], mh);
//            dummy[3] += testReflection(dummy[2], reflected);
//        }
//        long t0 = System.nanoTime();
//        dummy[0] += testDirect(dummy[0]);
//        long t1 = System.nanoTime();
//        dummy[1] += testLambda(dummy[1], lambda);
//        long t2 = System.nanoTime();
//        dummy[2] += testMH(dummy[1], mh);
//        long t3 = System.nanoTime();
//        dummy[3] += testReflection(dummy[2], reflected);
//        long t4 = System.nanoTime();
//        System.out.printf("direct: %.2fs, lambda: %.2fs, mh: %.2fs, reflection: %.2fs%n",
//                (t1 - t0) * 1e-9, (t2 - t1) * 1e-9, (t3 - t2) * 1e-9, (t4 - t3) * 1e-9);
//
//        // do something with the results
//        if (dummy[0] != dummy[1] || dummy[0] != dummy[2] || dummy[0] != dummy[3]) {
//            throw new AssertionError();
//        }
//    }
//
//    private static int testMH(int v, MethodHandle mh) throws Throwable {
//        for (int i = 0; i < ITERATIONS; i++) {
//            v += (int) mh.invokeExact(1000, v);
//        }
//        return v;
//    }
//
//    private static int testReflection(int v, Method mh) throws Throwable {
//        for (int i = 0; i < ITERATIONS; i++) {
//            v += (int) mh.invoke(null, 1000, v);
//        }
//        return v;
//    }
//
//    private static int testDirect(int v) {
//        for (int i = 0; i < ITERATIONS; i++) {
//            v += myMethod(1000, v);
//        }
//        return v;
//    }
//
//    private static int testLambda(int v, IntBinaryOperator lambda) {
//        for (int i = 0; i < ITERATIONS; i++) {
//            v += lambda.applyAsInt(1000, v);
//        }
//        return v;
//    }
//
//    private static int myMethod(int a, int b) {
//        return a < b ? a : b;
//    }
}
