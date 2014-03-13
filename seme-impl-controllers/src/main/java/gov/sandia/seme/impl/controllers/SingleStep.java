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
package gov.sandia.seme.impl.controllers;

import gov.sandia.seme.framework.ComponentType;
import gov.sandia.seme.framework.ConfigurationException;
import gov.sandia.seme.framework.Descriptor;
import gov.sandia.seme.framework.Step;
import gov.sandia.seme.util.ControllerImpl;
import org.apache.log4j.Logger;

/**
 * @if doxyUser
 * @page userSingleStep Configuration Details: Using controllers.SingleStep
 *
 * @endif
 */
/**
 * Provides Controller to run on a single step. The controller will then
 * serialize the Engine to a continuation file and exit. This is typically only
 * used when the application de-serializes or loads state prior to running the
 * controller.
 *
 * @internal
 * @author dbhart
 * @author $LastChangedBy$
 * @version $Rev$, $Date$
 */
public class SingleStep extends ControllerImpl {

    private static final Logger LOG = Logger.getLogger(Batch.class);

    static final long serialVersionUID = 4082133680255940892L;
    private int curIndex = -1;

    @Override
    public void configure(Descriptor desc) throws ConfigurationException {
        LOG.info("Configuring controller");
        super.configure(desc); //To change body of generated methods, choose Tools | Templates.
        if (this.dynamic) {
            throw new ConfigurationException(
                    "Single step mode runs cannot use dynamic step start/final values.");
        }
        if (this.stepStart.getValue() == null) {
            throw new ConfigurationException(
                    "Single step runs must have a starting step value specified.");
        }
    }

    @Override
    public Descriptor getConfiguration() {
        Descriptor d = new Descriptor();
        d.setName(this.getName());
        d.setClassName(this.getClass().getCanonicalName());
        d.setComponentType("CONTROLLER");
        d.setType(ComponentType.CONTROLLER);
        return d;
    }

    @Override
    public void loadState() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {
        running = true;
        LOG.debug("Entering run() method of the Batch Controlelr");
        while (this.paused) {
            try {
                Thread.currentThread().wait(pauseDelay);
            } catch (InterruptedException ex) {
                LOG.error("Interrupted thread exception", ex);
            }
        }
        curIndex += 1;
        Class c = stepBase.getClass();
        Step batchStep = null;
        try {
            batchStep = (Step) c.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            LOG.fatal("Failed to create new Step of type " + c.getName());
        }
        batchStep.setOrigin(stepBase.getOrigin());
        batchStep.setStepSize(stepBase.getStepSize());
        batchStep.setValue(stepBase.getOrigin());
        batchStep.setFormat(stepBase.getFormat());
        batchStep.setIndex(curIndex);
        LOG.debug("Running Step(" + batchStep.toString()
                + ")");
        engine.setCurrentStep(batchStep);
        engine.call();
        running = false;
    }

    @Override
    public void saveState() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
