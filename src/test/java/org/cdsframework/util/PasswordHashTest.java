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

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
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
public class PasswordHashTest {

    public PasswordHashTest() {
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
    public void createHashTest() throws NoSuchAlgorithmException, InvalidKeySpecException {
        List<String> hashes = new ArrayList<String>();
        // Print out 10 hashes
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            hashes.add(PasswordHash.createHash("p\r\nassw0Rd!"));
            System.out.println("time for hash creation: " + ((System.nanoTime() - start)/1000000.0));
        }

        for (String hash : hashes) {
            System.out.println(hash);
            int i = 0;
            for (String ohash : hashes) {
                if (hash.equals(ohash)) {
                    i++;
                }
            }
            if (i > 1) {
                System.out.println(hashes);
                fail("Found more than one items in the array that matched the hash: " + hash);
            }
        }
    }

    @Test
    public void validatePasswordTest() throws NoSuchAlgorithmException, InvalidKeySpecException {

        // Test password validation
        System.out.println("Running tests...");
        for (int i = 0; i < 10; i++) {
            String password = "" + i;
            long start = System.nanoTime();
            String hash = PasswordHash.createHash(password);
            System.out.println("time for hash creation: " + ((System.nanoTime() - start)/1000000.0));
            start = System.nanoTime();
            String secondHash = PasswordHash.createHash(password);
            System.out.println("time for hash creation: " + ((System.nanoTime() - start)/1000000.0));
            if (hash.equals(secondHash)) {
                fail("FAILURE: TWO HASHES ARE EQUAL!");
            }
            String wrongPassword = "" + (i + 1);
            if (PasswordHash.validatePassword(wrongPassword, hash)) {
                fail("FAILURE: WRONG PASSWORD ACCEPTED!");
            }
            if (!PasswordHash.validatePassword(password, hash)) {
                fail("FAILURE: GOOD PASSWORD NOT ACCEPTED!");
            }
        }
    }
}
