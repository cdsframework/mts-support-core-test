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

import org.cdsframework.client.MtsClient;
import org.cdsframework.client.support.AdminMGRClient;
import org.cdsframework.client.support.SecurityMGRClient;
import org.cdsframework.dto.SessionDTO;
import org.cdsframework.mts.support.core.test.util.TestUtils;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author HLN Consulting, LLC
 */
public class SecurityMGRTest {

    private MtsClient mtsClient = null;

    private AdminMGRClient adminMGR;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        try {
            System.err.println("initializing; mts client");
            mtsClient = TestUtils.getMtsClient();
            System.err.println("mtsClient.getSession().getSessionId()=" + mtsClient.getSession().getSessionId());
        } catch (Exception e) {
            System.err.println("An Exception has occurred; Message:" + e.getMessage());
            System.err.println(e);
        }
    }

    @After
    public void tearDown() {
        try {
            mtsClient.getManager(SecurityMGRClient.class).logout(mtsClient.getSession());
        } catch (Exception e) {
            System.err.println("An Exception has occurred; Message:" + e.getMessage());
            System.err.println(e);
        }
    }

    /**
     * Test of getSrcSystemId method, of class AdminMGRRemote.
     *
     * @throws Exception
     */
    @Test
    public void testLogout() throws Exception {
        try {
            SessionDTO session = mtsClient.getSession();
            String sessionId = session.getSessionId();
            System.out.println("sessionId=" + sessionId);
            SessionDTO sessionDTO = new SessionDTO();
            sessionDTO.setSessionId(sessionId);
            mtsClient.getManager(SecurityMGRClient.class).logout(sessionDTO);
        } catch (Exception e) {
            System.err.println("An Exception has occurred; Message:" + e.getMessage());
            System.err.println(e);
        }
    }

    @Test
    public void testIsSessionValid() throws Exception {
        SessionDTO session = mtsClient.getSession();
        String sessionId = session.getSessionId();
        String badSessionId = "bassessionid";
        SessionDTO badSession = new SessionDTO();
        badSession.setSessionId(badSessionId);
        boolean sessionValid = mtsClient.getManager(SecurityMGRClient.class).isSessionValid(session);
        System.out.println("sessionValid=" + sessionValid);
        assertTrue(sessionValid);
        boolean badSessionValid = mtsClient.getManager(SecurityMGRClient.class).isSessionValid(badSession);
        System.out.println("badSessionValid=" + badSessionValid);
        assertFalse(badSessionValid);
    }
}
