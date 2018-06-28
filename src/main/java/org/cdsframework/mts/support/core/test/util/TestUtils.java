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
package org.cdsframework.mts.support.core.test.util;

import java.io.FileInputStream;
import java.io.IOException;
import org.cdsframework.client.MtsClient;
import org.cdsframework.exceptions.AuthenticationException;
import org.cdsframework.exceptions.AuthorizationException;
import org.cdsframework.exceptions.MtsException;
import org.cdsframework.exceptions.NotFoundException;
import java.util.Properties;
import javax.naming.NamingException;
import org.cdsframework.util.LogUtils;

/**
 *
 * @author HLN Consulting, LLC
 */
public class TestUtils {

    private static final LogUtils logger = new LogUtils(TestUtils.class);
    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(new FileInputStream("src/test/resources/test.properties"));
        } catch (IOException e) {
            logger.error(e);
        }
    }

    public static MtsClient getMtsClient() throws MtsException, NotFoundException, AuthenticationException, AuthorizationException, NamingException {
        return new MtsClient(
                properties.getProperty("TEST_USERNAME"),
                properties.getProperty("TEST_PASSWORD"),
                properties.getProperty("TEST_APP"),
                properties.getProperty("TEST_HOST"),
                properties.getProperty("TEST_PORT"));
    }
}
