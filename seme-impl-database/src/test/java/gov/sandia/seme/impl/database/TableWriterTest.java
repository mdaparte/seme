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
package gov.sandia.seme.impl.database;

import gov.sandia.seme.framework.Step;
import junit.framework.TestCase;
import org.junit.Ignore;

/**
 *
 * @author dbhart
 */
@Ignore
public class TableWriterTest extends TestCase {

    public TableWriterTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of consumeMessagesAndWriteOutput method, of class TableWriter.
     */
    public void testConsumeMessagesAndWriteOutput_0args() {
        System.out.println("consumeMessagesAndWriteOutput");
        TableWriter instance = null;
        int expResult = 0;
        int result = instance.consumeMessagesAndWriteOutput();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of consumeMessagesAndWriteOutput method, of class TableWriter.
     */
    public void testConsumeMessagesAndWriteOutput_Step() {
        System.out.println("consumeMessagesAndWriteOutput");
        Step stepPar = null;
        TableWriter instance = null;
        int expResult = 0;
        int result = instance.consumeMessagesAndWriteOutput(stepPar);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDestinationLocation method, of class TableWriter.
     */
    public void testGetLocation() {
        System.out.println("getLocation");
        TableWriter instance = null;
        String expResult = "";
        String result = instance.getDestinationLocation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDestinationLocation method, of class TableWriter.
     */
    public void testSetLocation() {
        System.out.println("setLocation");
        String location = "";
        TableWriter instance = null;
        instance.setDestinationLocation(location);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isOutputConstrainedToCurrentStep method, of class TableWriter.
     */
    public void testIsOutputConstrainedToCurrentStep() {
        System.out.println("isOutputConstrainedToCurrentStep");
        TableWriter instance = null;
        boolean expResult = false;
        boolean result = instance.isOutputConstrainedToCurrentStep();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOutputConstrainedToCurrentStep method, of class TableWriter.
     */
    public void testSetOutputConstrainedToCurrentStep() {
        System.out.println("setOutputConstrainedToCurrentStep");
        boolean constrain = false;
        TableWriter instance = null;
        instance.setOutputConstrainedToCurrentStep(constrain);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
