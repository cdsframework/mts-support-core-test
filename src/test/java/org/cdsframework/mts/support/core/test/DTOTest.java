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

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import org.cdsframework.annotation.GeneratedValue;
import org.cdsframework.annotation.Id;
import org.cdsframework.base.BaseDTO;
import org.cdsframework.dto.AppDTO;
import org.cdsframework.dto.SessionDTO;
import org.cdsframework.dto.UserDTO;
import org.cdsframework.enumeration.GenerationSource;
import org.cdsframework.exceptions.AnnotationException;
import org.cdsframework.mts.support.core.test.support.Test1;
import org.cdsframework.mts.support.core.test.support.Test2;
import org.cdsframework.util.ClassUtils;
import org.cdsframework.util.DTOUtils;
import org.cdsframework.util.LogUtils;
import org.cdsframework.util.PasswordHash;
import org.cdsframework.util.StringUtils;
import org.cdsframework.util.support.DeepCopy;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DTOTest {

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

    private class ParentDTO extends BaseDTO {

        @GeneratedValue
        @Id
        private String key1;
        @GeneratedValue
        @Id
        private Integer key2;
        @GeneratedValue
        @Id
        private Long key3;

        public void setKey1(String key1) {
            this.key1 = key1;
        }

        public void setKey2(Integer key2) {
            this.key2 = key2;
        }

        public void setKey3(Long key3) {
            this.key3 = key3;
        }

        public String getKey1() {
            return key1;
        }

        public Integer getKey2() {
            return key2;
        }

        public Long getKey3() {
            return key3;
        }
    }

    private class ChildOneDTO extends BaseDTO {

        @GeneratedValue
        @Id
        private String key0;
        @GeneratedValue(source = GenerationSource.FOREIGN_CONSTRAINT, sourceClass = ParentDTO.class, fieldName = "key3")
        @Id
        private Long key1;
        @GeneratedValue(source = GenerationSource.FOREIGN_CONSTRAINT, sourceClass = ParentDTO.class, fieldName = "key2")
        private Integer key2;
        @GeneratedValue(source = GenerationSource.FOREIGN_CONSTRAINT, sourceClass = ParentDTO.class, fieldName = "key1")
        private String key3;

        public String getKey0() {
            return key0;
        }

        public void setKey0(String key0) {
            this.key0 = key0;
        }

        public void setKey1(Long key1) {
            this.key1 = key1;
        }

        public void setKey2(Integer key2) {
            this.key2 = key2;
        }

        public void setKey3(String key3) {
            this.key3 = key3;
        }

        public Long getKey1() {
            return key1;
        }

        public Integer getKey2() {
            return key2;
        }

        public String getKey3() {
            return key3;
        }
    }

    private class ChildTwoDTO extends BaseDTO {

        @GeneratedValue(source = GenerationSource.SEQUENCE)
        @Id
        private String key0;
        @GeneratedValue(source = GenerationSource.FOREIGN_CONSTRAINT, sourceClass = ParentDTO.class)
        @Id
        private ParentDTO key1;

        public void setKey1(ParentDTO key1) {
            this.key1 = key1;
        }

        public String getKey0() {
            return key0;
        }

        public void setKey0(String key0) {
            this.key0 = key0;
        }

        public ParentDTO getKey1() {
            return key1;
        }
    }

    @Test
    public void testBaseDTOMultiDTOPrimaryKey() throws Exception {
        String key1 = "ghost";
        Integer key2 = 99999;
        Long key3 = 88888L;
        ParentDTO parentDTO = new ParentDTO();
        Map<String, Object> parentPK = new HashMap<String, Object>();
        parentPK.put("key1", key1);
        parentPK.put("key2", key2);
        parentPK.put("key3", key3);
        parentDTO.setPrimaryKey(parentPK);

        String key0 = "busters";
        Map<String, Object> childPK = new HashMap<String, Object>();
        childPK.put("key0", key0);
        childPK.put("key1", parentDTO);
        ChildTwoDTO childDTO = new ChildTwoDTO();
        childDTO.setPrimaryKey(childPK);

        assertEquals(childDTO.getKey0(), key0);
        assertEquals(childDTO.getKey1(), parentDTO);

        Map<String, Object> primaryKey = (Map<String, Object>) childDTO.getPrimaryKey();
        for (Entry<String, Object> entry : primaryKey.entrySet()) {
            System.out.println("Child PK Map entry: " + entry.getKey() + " - " + entry.getValue());
        }
    }

    @Test
    public void testBaseDTOMultiPrimaryKey() throws Exception {
        String key0 = "busters";
        String key1 = "ghost";
        Integer key2 = 99999;
        Long key3 = 88888L;
        ParentDTO parentDTO = new ParentDTO();
        Map<String, Object> pk = new HashMap<String, Object>();
        pk.put("key1", key1);
        pk.put("key2", key2);
        pk.put("key3", key3);
        parentDTO.setPrimaryKey(pk);

        System.out.println("ParentDTO key 1: " + parentDTO.getKey1());
        assertEquals(key1, parentDTO.getKey1());

        System.out.println("ParentDTO key 2: " + parentDTO.getKey2());
        assertEquals(key2, parentDTO.getKey2());

        System.out.println("ParentDTO key 3: " + parentDTO.getKey3());
        assertEquals(key3, parentDTO.getKey3());

        Map<String, Object> parentPK = (Map<String, Object>) parentDTO.getPrimaryKey();

        for (Entry<String, Object> entry : parentPK.entrySet()) {
            System.out.println("Parent PK Map entry: " + entry.getKey() + " - " + entry.getValue());
        }

        assertTrue(parentPK.containsKey("key1"));
        assertTrue(parentPK.containsKey("key2"));
        assertTrue(parentPK.containsKey("key3"));

        assertEquals(key1, parentPK.get("key1"));
        assertEquals(key2, parentPK.get("key2"));
        assertEquals(key3, parentPK.get("key3"));

        ChildOneDTO childDTO = new ChildOneDTO();
        childDTO.setKey0(key0);

        childDTO.setForeignKey(ParentDTO.class, parentPK);

        System.out.println("ChildDTO key 1: " + childDTO.getKey1());
        assertEquals(key3, childDTO.getKey1());

        System.out.println("ChildDTO key 2: " + childDTO.getKey2());
        assertEquals(key2, childDTO.getKey2());

        System.out.println("ChildDTO key 3: " + childDTO.getKey3());
        assertEquals(key1, childDTO.getKey3());

        assertEquals(key1, childDTO.getForeignKey(ParentDTO.class, "key1"));
        assertEquals(key2, childDTO.getForeignKey(ParentDTO.class, "key2"));
        assertEquals(key3, childDTO.getForeignKey(ParentDTO.class, "key3"));

        Map<String, Object> childPK = (Map<String, Object>) childDTO.getPrimaryKey();

        for (Entry<String, Object> entry : childPK.entrySet()) {
            System.out.println("Child PK Map entry: " + entry.getKey() + " - " + entry.getValue());
        }

        assertTrue(childPK.containsKey("key0"));
        assertTrue(childPK.containsKey("key1"));

        assertEquals(key0, childPK.get("key0"));
        assertEquals(key3, childPK.get("key1"));

    }

    @Test
    public void testClassForName() throws Exception {
        AppDTO rt = new AppDTO();
        String qc = "DtoByAppName";
        String qcPath = rt.getClass().getCanonicalName();
        String fullPath = qcPath + "$" + qc;
        System.out.println(fullPath);
        System.out.println(Class.forName(fullPath));
        System.out.println(AppDTO.AllAppNames.class.getCanonicalName());
    }

    @Test
    public void testBaseDTOHashMap() throws Exception {
        BaseDTO baseDTO = new BaseDTO() {
        };
        BaseDTO baseDTO1 = new BaseDTO() {
        };
        BaseDTO baseDTO2 = new BaseDTO() {
        };
        BaseDTO baseDTO3 = new BaseDTO() {
        };
        List<BaseDTO> list = new ArrayList<BaseDTO>();
        list.add(baseDTO1);
        list.add(baseDTO2);
        list.add(baseDTO3);
        BaseDTO baseDTO4 = new BaseDTO() {
        };
        BaseDTO baseDTO5 = new BaseDTO() {
        };
        BaseDTO baseDTO6 = new BaseDTO() {
        };
        list = new ArrayList<BaseDTO>();
        list.add(baseDTO4);
        list.add(baseDTO5);
        list.add(baseDTO6);
    }

    @Test
    public void testDeepCopy() throws Exception {
        UserDTO user1 = new UserDTO();
        user1.setFirstName("Alfred");

        UserDTO user2 = DeepCopy.copy(user1);
        user2.setFirstName("Harry");

        System.out.println("UserDTO Values: " + user1.getFirstName() + " - " + user2.getFirstName());
    }

    @Test
    public void testForDTOsWithNoPrimaryKey()
            throws IOException, ClassNotFoundException, InstantiationException,
            IllegalAccessException, NoSuchMethodException, IllegalArgumentException,
            InvocationTargetException, AnnotationException, Exception {
        System.out.println("testing for DTOs With No Primary Key");
        Class[] classes = ClassUtils.getClasses("org.cdsframework.dto");
        System.out.println("# of classes found: " + classes.length);
        for (Class clazz : classes) {
            System.out.println("checking: " + clazz.getCanonicalName());
            if (clazz.getSuperclass() == BaseDTO.class
                    && !clazz.getName().contains("org.cdsframework.dto.DTOTest")) {
                System.out.println("testing " + clazz.getName() + " for a primary key annotation...");
                BaseDTO tmpInstance = null;
                try {
                    Object tmp = clazz.newInstance();
                    tmpInstance = (BaseDTO) clazz.newInstance();
                } catch (Exception e) {
                    System.out.println(e.getCause());
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                    throw e;
                }
                boolean noIdExists = false;
                try {
                    noIdExists = tmpInstance.isNoId();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                if (noIdExists) {
                    System.out.println(clazz.getSimpleName() + " has NoId set.");
                    continue;
                }
                List<Field> pkFields = tmpInstance.getPrimaryKeyFields();
                if (pkFields.isEmpty()) {
                    System.out.println(clazz.getSimpleName() + " does not have a primary key set!");
                }
                assertTrue(pkFields.size() > 0);
                String labels = "";
                for (Field field : pkFields) {
                    labels = labels + field.getName() + "_";
                }
                System.out.println(clazz.getSimpleName() + " PK field: " + labels.substring(0, labels.length() - 1));
                for (Field field : ClassUtils.getNonBaseDTODeclaredFields(clazz)) {
                    GeneratedValue generatedValue = field.getAnnotation(GeneratedValue.class);
                    if (generatedValue != null) {
                        if (generatedValue.source() == GenerationSource.FOREIGN_CONSTRAINT) {
                            for (Class klass : generatedValue.sourceClass()) {
                                System.out.println("Foreign Source Class: " + field.getName() + " - " + klass);
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    public void testDTOChildSettersGetters() throws Exception {
        UserDTO userDTO = new UserDTO();

        userDTO.setUsername("odin");
        userDTO.setPasswordHash(PasswordHash.createHash("FREAKY_BONEHEAD"));

        userDTO.setFirstName("Odin");
        userDTO.setLastName("Stargaiser");
    }

    @Test
    public void testDTOgetPrimaryKey() throws Exception {
        System.out.println("testing DTO getPrimaryKey");
        String id1 = "KANGAROO";
        AppDTO appDTO = new AppDTO();
        appDTO.setAppId(id1);
        assertEquals(id1, appDTO.getPrimaryKey());
    }

    @Test
    public void testDTOPKGetters() throws Exception {
        System.out.println("testing DTO PKGetters");
        assertEquals(DTOUtils.getPKGeneratedValue(AppDTO.class).source(), GenerationSource.AUTO);
        assertTrue(DTOUtils.isPKGeneratedSourceAuto(AppDTO.class));
    }

//    @Test
//    public void testHasPrimaryKeyMulti() throws Exception {
//        output.println("testing hasPrimaryKey multi key");
//        ClinicQueueDetailDTO clinicQueueDetailDTO = new ClinicQueueDetailDTO();
//        clinicQueueDetailDTO.getPrimaryKeyFields();
//        assertTrue(!clinicQueueDetailDTO.hasPrimaryKey());
//        clinicQueueDetailDTO.setClinicQueueId(0L);
//        ClinicStationLkDTO clinicStationDTO1 = new ClinicStationLkDTO();
//        clinicStationDTO1.setStationId(0);
//        clinicQueueDetailDTO.setClinicStationLkDTO(clinicStationDTO1);
//        assertTrue(!clinicQueueDetailDTO.hasPrimaryKey());
//        ClinicStationLkDTO clinicStationDTO2 = new ClinicStationLkDTO();
//        clinicStationDTO2.setStationId(450);
//        clinicQueueDetailDTO.setClinicStationLkDTO(clinicStationDTO2);
//        assertTrue(!clinicQueueDetailDTO.hasPrimaryKey());
//        clinicQueueDetailDTO.setClinicQueueId(450L);
//        assertTrue(clinicQueueDetailDTO.hasPrimaryKey());
//    }
//
//    @Test
//    public void testAutoIntIdAssignment() throws Exception {
//        output.println("testing autoIntIdAssignment");
//        SystemParameterDTO systemParameterDTO = new SystemParameterDTO();
//        assertEquals(systemParameterDTO.getPrimaryKey(), 0);
//        assertTrue(systemParameterDTO.isPKGeneratedSourceAuto());
//        assertTrue(systemParameterDTO.autoSetPrimaryKeys());
//        assertTrue(systemParameterDTO.getParameterId() > 0);
//        assertTrue(systemParameterDTO.getParameterId() < Integer.MAX_VALUE);
//        try {
//            systemParameterDTO.autoSetPrimaryKeys();
//            fail("systemParameterDTO.autoSetPrimaryKey shouldn't have worked. There was already a value set.");
//        } catch (MtsException e) {
//        }
//    }
    @Test
    public void testAutoStringIdAssignment() throws Exception {
        System.out.println("testing autoStringIdAssignment");
        SessionDTO sessionDTO = new SessionDTO();
        assertEquals(sessionDTO.getPrimaryKey(), new String());
        assertTrue(sessionDTO.isPKGeneratedSourceAuto());
        assertTrue(sessionDTO.autoSetPrimaryKeys());
        assertNotSame(sessionDTO.getPrimaryKey(), new String());
        try {
            sessionDTO.autoSetPrimaryKeys();
            fail("sessionDTO.autoSetPrimaryKey shouldn't have worked. There was already a value set.");
        } catch (IllegalStateException e) {
            // expected
        }
    }
//
//    /**
//     * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
//     *
//     * @param packageName The base package
//     * @return The classes
//     * @throws ClassNotFoundException
//     * @throws IOException
//     */
//    private Class[] getClasses(String packageName)
//            throws Exception {
//        String path = packageName.replace('.', '/');
//        ClassLoader cloader = getClass().getClassLoader();
//        URL resource = cloader.getResource(path);
//        ArrayList<Class> classes = new ArrayList<Class>();
//        if (resource != null) {
//            File file = new File(new URI(resource.getPath().split("!")[0]));
//            JarFile jarFile = new JarFile(file);
//            if (jarFile != null) {
//                Enumeration<JarEntry> entries = jarFile.entries();
//                while (entries.hasMoreElements()) {
//                    JarEntry nextJarEntry = entries.nextElement();
//                    String name = nextJarEntry.getName();
//                    if (name.startsWith(path) && name.endsWith(".class") && !name.endsWith("package-info.class") && !name.contains("$")) {
//                        name = name.replace("/", ".").substring(0, name.length() - 6);
//                        classes.add(Class.forName(name));
//                    }
//                }
//            }
//        }
//        return classes.toArray(new Class[classes.size()]);
//    }

//    /**
//     * Test of TestBadChildDTOQueryClass method, assigns child FacilityDTOs to a PatientDTO
//     * calls PatientMGR.add to test the error
//     * @throws Exception
//     */
//    @Test
//    public void testBadChildDTOQueryClass() throws Exception {
//        final String METHODNAME = "testBadChildDTOQueryClass ";
//        output.println("testing " + METHODNAME + " Start");
//
//        PatientDTO patientDTO = null;
//        try {
//            patientDTO = this.addPatient("Glowy", "Sallis", DateUtils.parseDateFromString("01/26/1987", "MM/dd/yyyy"), "M",
//                    true);
//        } catch (MtsException e) {
//            assertTrue(e.getMessage().equals("A MtsException has occurred, Message: The ChildrenDTOs queryClass FacilityContactDTO.ByFacilityCode was not registered with a Business Object. Did you forget to registered a Business Object?"));
//        } catch (Exception e) {
//            fail("EXCEPTION: " + e.getClass() + " - " + e.getMessage());
//        } finally {
//            output.println("testing " + METHODNAME + " Stop");
//        }
//    }
//
//    /**
//     * Test of TestBadChildDTOQueryClass method, assigns child FacilityDTOs to a PatientDTO
//     * calls PatientMGR.add to test the error
//     * @throws Exception
//     */
//    @Test
//    public void testOperationDTOState() throws Exception {
//        final String METHODNAME = "testOperationDTOState ";
//        output.println("testing " + METHODNAME + " Start");
//
//        PatientDTO patientDTO = null;
//        try {
//            patientDTO = this.addPatient("Glowy", "Sallis", DateUtils.parseDateFromString("01/26/1987", "MM/dd/yyyy"), "M",
//                    true);
//        } catch (MtsException e) {
//            assertTrue(e.getMessage().equals("A MtsException has occurred, Message: The ChildrenDTOs queryClass interface FacilityContactDTO.ByFacilityCode was not registered with a Business Object. Did you forget to registered a Business Object?"));
//        } catch (Exception e) {
//            fail("EXCEPTION: " + e.getClass() + " - " + e.getMessage());
//        } finally {
//            output.println("testing " + METHODNAME + " Stop");
//        }
//    }
//
//    private PatientDTO addPatient(String firstName, String lastName, Date dob, String gender,
//            boolean addContactsToPatient) throws Exception {
//        final String METHODNAME = "addPatient ";
//        output.println(METHODNAME + " Start");
//
//        PatientDTO patientDTO = new PatientDTO();
//        try {
//            patientDTO.setFirstName(firstName);
//            patientDTO.setLastName(lastName);
//            patientDTO.setGender(gender);
//            patientDTO.setDob(dob);
//
//            // Add some Contacts
//            patientDTO.setChildrenDTOs(FacilityContactDTO.ByFacilityCode.class, (List) FacilityMGRRemoteTest.getFacilityContacts("9009X01"));
//
//            output.println("patientDTO.getOperationDTOState()=" + patientDTO.getOperationDTOState());
//            output.println("patientDTO.getDTOStates()=" + patientDTO.getDTOStates());
//            showPatient(patientDTO);
//
//        } finally {
//            output.println(METHODNAME + " Stop");
//        }
//        return patientDTO;
//    }
//
//    private void showPatient(PatientDTO patientDTO) throws Exception {
//        final String METHODNAME = "showPatient ";
//
//        output.println(METHODNAME + "patientDTO.getFirstName=" + patientDTO.getFirstName());
//        output.println(METHODNAME + "patientDTO.getLastName=" + patientDTO.getLastName());
//        output.println(METHODNAME + "patientDTO.getAltFirstName=" + patientDTO.getAltFirstName());
//        output.println(METHODNAME + "patientDTO.getAltLastName=" + patientDTO.getAltLastName());
//        output.println(METHODNAME + "patientDTO.getPatientId()=" + patientDTO.getPatientId());
//        output.println(METHODNAME + "patientDTO.getDTOStates()=" + patientDTO.getDTOStates());
//
//    }
    @Test
    public void blahtest() throws Exception {
        System.out.println("Test1 isAutoRetrieve: " + DTOUtils.isAutoRetrieve(Test1.class, ParentDTO.class));
        System.out.println("Test2 isAutoRetrieve: " + DTOUtils.isAutoRetrieve(Test2.class, ParentDTO.class));
        System.out.println("Test1 isChildNotFoundAllowed: " + DTOUtils.isChildNotFoundAllowed(Test1.class, ParentDTO.class));
        System.out.println("Test2 isChildNotFoundAllowed: " + DTOUtils.isChildNotFoundAllowed(Test2.class, ParentDTO.class));
    }
}
