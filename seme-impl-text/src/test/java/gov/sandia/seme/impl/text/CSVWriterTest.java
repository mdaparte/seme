/* 
 * Copyright 2014 Sandia Corporation.
 * Under the terms of Contract DE-AC04-94AL85000 with Sandia Corporation, the U.S.
 * Government retains certain rights in this software.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * This software was written as part of an Inter-Agency Agreement between Sandia
 * National Laboratories and the US EPA NHSRC.
 */
package gov.sandia.seme.impl.text;

import gov.sandia.seme.impl.text.CSVWriter;
import gov.sandia.seme.framework.Descriptor;
import gov.sandia.seme.framework.Step;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author dbhart
 */
@Ignore
public class CSVWriterTest {

    public CSVWriterTest() {
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

    /**
     * Test of configure method, of class CSVWriter.
     */
    @Test
    public void testConfigure() {
        System.out.println("configure");
        Descriptor config = null;
        CSVWriter instance = null;
        instance.configure(config);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of consumeMessagesAndWriteOutput method, of class CSVWriter.
     */
    @Test
    public void testConsumeMessagesAndWriteOutput_Step() {
        System.out.println("consumeMessagesAndWriteOutput");
        Step step = null;
        CSVWriter instance = null;
        int expResult = 0;
        int result = instance.consumeMessagesAndWriteOutput(step);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initialize method, of class CSVWriter.
     */
    @Test
    public void testInitialize() throws Exception {
        System.out.println("initialize");
        CSVWriter instance = null;
        instance.initialize();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of consumeMessagesAndWriteOutput method, of class CSVWriter.
     */
    @Test
    public void testConsumeMessagesAndWriteOutput_0args() {
        System.out.println("consumeMessagesAndWriteOutput");
        CSVWriter instance = null;
        int expResult = 0;
        int result = instance.consumeMessagesAndWriteOutput();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isOutputConstrainedToCurrentStep method, of class CSVWriter.
     */
    @Test
    public void testIsOutputConstrainedToCurrentStep() {
        System.out.println("isOutputConstrainedToCurrentStep");
        CSVWriter instance = null;
        boolean expResult = false;
        boolean result = instance.isOutputConstrainedToCurrentStep();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOutputConstrainedToCurrentStep method, of class CSVWriter.
     */
    @Test
    public void testSetOutputConstrainedToCurrentStep() {
        System.out.println("setOutputConstrainedToCurrentStep");
        boolean constrain = false;
        CSVWriter instance = null;
        instance.setOutputConstrainedToCurrentStep(constrain);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDestinationLocation method, of class CSVWriter.
     */
    @Test
    public void testGetLocation() {
        System.out.println("getLocation");
        CSVWriter instance = null;
        String expResult = "";
        String result = instance.getDestinationLocation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDestinationLocation method, of class CSVWriter.
     */
    @Test
    public void testSetLocation() {
        System.out.println("setLocation");
        String location = "";
        CSVWriter instance = null;
        instance.setDestinationLocation(location);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
