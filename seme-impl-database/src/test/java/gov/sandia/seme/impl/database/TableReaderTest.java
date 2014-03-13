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
public class TableReaderTest extends TestCase {

    public TableReaderTest(String testName) {
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
     * Test of getSourceLocation method, of class TableReader.
     */
    public void testGetLocation() {
        System.out.println("getLocation");
        TableReader instance = null;
        String expResult = "";
        String result = instance.getSourceLocation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSourceLocation method, of class TableReader.
     */
    public void testSetLocation() {
        System.out.println("setLocation");
        String location = "";
        TableReader instance = null;
        instance.setSourceLocation(location);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isInputConstrainedToCurrentStep method, of class TableReader.
     */
    public void testIsInputConstrainedToCurrentStep() {
        System.out.println("isInputConstrainedToCurrentStep");
        TableReader instance = null;
        boolean expResult = false;
        boolean result = instance.isInputConstrainedToCurrentStep();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInputConstrainedToCurrentStep method, of class TableReader.
     */
    public void testSetInputConstrainedToCurrentStep() {
        System.out.println("setInputConstrainedToCurrentStep");
        boolean contrain = false;
        TableReader instance = null;
        instance.setInputConstrainedToCurrentStep(contrain);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readInputAndProduceMessages method, of class TableReader.
     */
    public void testReadInputAndProduceMessages_0args() {
        System.out.println("readInputAndProduceMessages");
        TableReader instance = null;
        int expResult = 0;
        int result = instance.readInputAndProduceMessages();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readInputAndProduceMessages method, of class TableReader.
     */
    public void testReadInputAndProduceMessages_Step() {
        System.out.println("readInputAndProduceMessages");
        Step stepPar = null;
        TableReader instance = null;
        int expResult = 0;
        int result = instance.readInputAndProduceMessages(stepPar);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
