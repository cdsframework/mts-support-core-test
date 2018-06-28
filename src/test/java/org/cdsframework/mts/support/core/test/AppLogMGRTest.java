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

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;
import org.cdsframework.client.MtsClient;
import org.cdsframework.client.support.AppLogMGRClient;
import org.cdsframework.client.support.GeneralMGRClient;
import org.cdsframework.client.support.SecurityMGRClient;
import org.cdsframework.dto.AppDTO;
import org.cdsframework.dto.PropertyBagDTO;
import org.cdsframework.enumeration.LogLevel;
import org.cdsframework.mts.support.core.test.util.TestUtils;
import org.cdsframework.util.ObjectUtils;
import org.cdsframework.util.StringUtils;
import org.junit.Test;

/**
 *
 * @author HLN Consulting LLC
 */
public class AppLogMGRTest extends TestCase {
    private MtsClient mtsClient = null;
    private static final Logger logger = Logger.getLogger(AppLogMGRTest.class.getName());

    @Override
    protected void setUp() throws Exception {
        try {
            logger.log(Level.INFO, "initializing; mts client");
//            mtsClient = new MtsClient(MTS_USERNAME, MTS_PASSWORD, MTS_APP, MTS_HOST, MTS_PORT);
            mtsClient = TestUtils.getMtsClient();
            logger.log(Level.INFO, "mtsClient.getSession().getSessionId()=" + mtsClient.getSession().getSessionId());
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "An Exception has occurred; Message:" + ex.getMessage(), ex);
        }

    }

    @Override
    protected void tearDown() throws Exception {
        mtsClient.getManager(SecurityMGRClient.class).logout(mtsClient.getSession());
    }     
    
     @Test
     public void testAppLog() {
        try {
            logger.log(Level.INFO, "testAppLog");
            AppLogMGRClient appLogMGRClient = mtsClient.getManager(AppLogMGRClient.class);
            appLogMGRClient.queueAppErrorLog(new Exception("Error Exception"), mtsClient.getSession());
            appLogMGRClient.queueAppInfoLog("Test Info", mtsClient.getSession());
            AppDTO appDTO = new AppDTO();
            appLogMGRClient.queueAppLog(LogLevel.INFO, appDTO, mtsClient.getSession(), new PropertyBagDTO());
            appLogMGRClient.queueAppLog(LogLevel.INFO, "This is a test message", mtsClient.getSession(), new PropertyBagDTO());
            appLogMGRClient.queueAppLog(LogLevel.INFO, new Exception("This is an Exception"), appDTO, mtsClient.getSession(), new PropertyBagDTO());
            byte[] objectBytes = ObjectUtils.serializeObject(appDTO);
            appLogMGRClient.queueAppLog(LogLevel.INFO, new Exception("This is an Exception"), appDTO.getClass().getCanonicalName(), 
                    objectBytes, mtsClient.getSession(), new PropertyBagDTO());
            appLogMGRClient.queueAppLog(LogLevel.INFO, "This is a test message", 
                    StringUtils.getStackTrace(new Exception("This is an Exception")), appDTO.getClass().getCanonicalName(), 
                    objectBytes, mtsClient.getSession(), new PropertyBagDTO());
            appDTO.setAppName(new Date().toString());
            GeneralMGRClient generalMGRClient = mtsClient.getGeneralMGR();
            appDTO = generalMGRClient.save(appDTO, mtsClient.getSession(), new PropertyBagDTO());
            logger.log(Level.INFO, "appDTO.getAppName()=" + appDTO.getAppName() + " appDTO=" + appDTO);
            appDTO.delete();
            generalMGRClient.save(appDTO, mtsClient.getSession(), new PropertyBagDTO());
            
            
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "An Exception has occurred; " + ex.getClass().getCanonicalName() + " Message:" + ex.getMessage(), ex);
        }
     }
    
}
