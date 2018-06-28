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

import java.util.Date;
import javax.naming.NamingException;
import org.cdsframework.client.MtsClient;
import org.cdsframework.client.support.GeneralMGRClient;
import org.cdsframework.dto.AppDTO;
import org.cdsframework.dto.PropertyBagDTO;
import org.cdsframework.exceptions.AuthenticationException;
import org.cdsframework.exceptions.AuthorizationException;
import org.cdsframework.exceptions.ConstraintViolationException;
import org.cdsframework.exceptions.MtsException;
import org.cdsframework.exceptions.NotFoundException;
import org.cdsframework.exceptions.ValidationException;

/**
 *
 * @author HLN Consulting, LLC
 */
public class AppDTOTest {
    
    /**
     * @param args the command line arguments
     * 
     * to execute
     * java -cp mts-support-core-test-1.0.0.jar:mts-support-core.jar:aspectj-tools-1.0.3.jar:
     * glassfish-embedded-all-4.1.1.jar:joda-time-2.1.jar:cdsframework-common-1.0.1.jar:
     * log4j-1.2.17.jar:aspectjrt-1.8.7.jar 
     * org.cdsframework.mts.support.core.test.util.AppDTOTest username password appname hostname port
     * @throws org.cdsframework.exceptions.MtsException
     * @throws org.cdsframework.exceptions.NotFoundException
     * @throws org.cdsframework.exceptions.AuthenticationException
     * @throws org.cdsframework.exceptions.ConstraintViolationException
     * @throws javax.naming.NamingException
     * @throws org.cdsframework.exceptions.ValidationException
     * @throws org.cdsframework.exceptions.AuthorizationException
     */
    public static void main(String[] args) throws MtsException, NotFoundException, AuthenticationException, AuthorizationException, NamingException, ValidationException, ConstraintViolationException {
        System.out.println("initializing; mts client");
        String username = null;
        String password = null;
        String appname = null;
        String hostname = null;
        String port = null;
        MtsClient mtsClient = null;
        if (args != null) {
            if (args.length > 4) {
                username = args[0];
                password = args[1];
                appname = args[2];                
                hostname = args[3];
                port = args[4];                

                mtsClient = new MtsClient(username, password, appname, hostname, port);
                System.out.println("mtsClient.getSession().getSessionId()=" + mtsClient.getSession().getSessionId());
                AppDTO appDTO = new AppDTO();
                appDTO.setAppName(new Date().toString());
                GeneralMGRClient generalMGRClient = mtsClient.getGeneralMGR();
                appDTO = generalMGRClient.save(appDTO, mtsClient.getSession(), new PropertyBagDTO());
                System.out.println("appDTO.getAppName()=" + appDTO.getAppName() + " appDTO=" + appDTO);
                appDTO.delete();
                System.out.println("appDTO deleted");
                generalMGRClient.save(appDTO, mtsClient.getSession(), new PropertyBagDTO());
            }
            else {
                System.out.println("please provide username, password, appname, hostname, port args");
            }
        }
    }
    
}
