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

import gov.sandia.seme.framework.InitializationException;
import gov.sandia.seme.framework.Descriptor;
import gov.sandia.seme.framework.Message;
import gov.sandia.seme.framework.MessageType;
import gov.sandia.seme.framework.OutputConnection;
import gov.sandia.seme.framework.Step;
import gov.sandia.seme.util.MessagableImpl;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 * Write {@literal RESULT} type {@literal Message}s to a CSV-formatted file.
 *
 * The following tags are recognized configuration tags:
 * <table><tr><th>Option tag</th><th>Type (in bold) and description</th></tr>
 * <tr><td>location</td><td><b>String</b>: URL or file location.</td></tr>
 * <tr><td>columnKeys</td><td><b>List of Strings</b>: an ordered list of keys
 * that should be read from the message and output by columns in the specified
 * order. The key "step" will output the step value of the message; A key of
 * "tag" will output the message id.<p>
 * If this entry is omitted, the default keys, as defined by the framework or
 * program's {@literal Components} object, will be queried and used.</td></tr>
 * <tr><td>columnHeaders</td><td><b>List of Strings</b>: an ordered list of
 * column headers. This is optional, and the output key is the default
 * header.</td></tr>
 * <tr><td>columnFormats</td><td><b>List of Strings</b>: an ordered list of
 * formats that should be used (C/C++ printf-style codes). If this is omitted,
 * then all key-values will be be output as the default {@literal toString()}
 * values.</td></tr>
 * <tr><td> </td><td><b> </b>: </td></tr>
 * </table>
 *
 * @author dbhart
 */
public class CSVWriter extends MessagableImpl implements OutputConnection {

    private static final Logger LOG = Logger.getLogger(CSVWriter.class);
    private String[] resultKeyList = null;
    private String[] resultKeyHeaders = null;
    private String resultFormatString = null;

    @Override
    public void configure(Descriptor config) {
        super.configure(config);
        HashMap options = config.getOptions();
        this.location = (String) options.get("location");
    }

    /**
     *
     * @param step
     * @return
     */
    @Override
    public int consumeMessagesAndWriteOutput(Step step) {
        int count = 0;
        Message newData;
        newData = this.pollMessageFromInbox(step);
        while (newData != null) {
            LOG.trace(newData);
            count = count + 1;
            if (this.location != null) {
                if (newData.getType() == MessageType.RESULT) {
                    try {
                        File loc = new File(location).getAbsoluteFile();
                        FileWriter outFile;
                        PrintWriter out;
                        outFile = new FileWriter(loc, true);
                        out = new PrintWriter(outFile);
                        // "Step,Station Tag,Event Code,Event Probability,Contributing Parameters,Workflow Name,Algorithm Name\n");
                        String contribParams = new String();
                        ArrayList byChParams = (ArrayList) newData.getData().get(
                                "byChannelParameters");
                        ArrayList byChContrib = (ArrayList) newData.getData().get(
                                "byChannelContribToEvents");
                        if (byChParams != null) {
                            for (int iPar = 0; iPar < byChParams.size(); iPar++) {
                                if ((Boolean) byChContrib.get(iPar)) {
                                    contribParams += (String) byChParams.get(
                                            iPar) + " ";
                                }
                            }
                        }
                        Object[] outputObjectArray = new Object[this.resultKeyList.length];
                        int i = 0;
                        for (String key : this.resultKeyList) {
                            switch (key) {
                                case "step":
                                    outputObjectArray[i] = newData.getStep().toString();
                                    break;
                                case "tag":
                                    outputObjectArray[i] = newData.getTag();
                                    break;
                                default:
                                    Object val = newData.getData().get(key);
                                    if (val != null) {
                                        outputObjectArray[i] = val.toString();
                                    } else {
                                        outputObjectArray[i] = null;
                                    }
                                    break;
                            }
                            i++;
                        }
                        out.printf(this.resultFormatString, outputObjectArray);
                        out.flush();
                        out.close();
                    } catch (IOException ex) {
                        LOG.error(
                                "Probelm writing to output file ''" + this.location + "''",
                                ex);
                    }
                } else if (newData.getType() == MessageType.VALUE) {
                }
            }
            if (step == null) {
                newData = this.pollMessageFromInbox();
            } else {
                newData = this.pollMessageFromInbox(step);
            }
        }
        LOG.debug(
                "Consumed and wrote " + count + " RESULT messages for Step=" + step + ".");
        return count;
    }

    @Override
    public Descriptor getConfiguration() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initialize() throws InitializationException {
        try {
            File loc = new File(location).getAbsoluteFile();
            FileWriter outFile;
            PrintWriter out;
            outFile = new FileWriter(loc);
            out = new PrintWriter(outFile);
            if (this.resultKeyList == null || this.resultKeyList.length == 0) {
                this.resultKeyList = this.getComponentFactory().getResultMessageDataKeys();
            }
            if (this.resultKeyHeaders == null || this.resultKeyHeaders.length != this.resultKeyList.length) {
                this.resultKeyHeaders = this.resultKeyList;
            }
            for (String resultKeyHeader : this.resultKeyHeaders) {
                out.printf("%s,", resultKeyHeader);
            }
            if (this.resultFormatString == null) {
                this.resultFormatString = "";
                for (String resultKeyList1 : this.resultKeyList) {
                    this.resultFormatString += "%s,";
                }
                this.resultFormatString += "\n";
            }
            out.printf("\n");
            out.flush();
            out.close();
        } catch (IOException ex) {
            LOG.fatal("fatal error initializing " + this.name + "for output", ex);
            throw new InitializationException(
                    "error initializing " + this.name + "for output use");
        }
    }

    /**
     *
     * @return
     */
    @Override
    public int consumeMessagesAndWriteOutput() {
        return consumeMessagesAndWriteOutput(null);
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isOutputConstrainedToCurrentStep() {
        return true;
    }

    /**
     *
     * @param constrain
     */
    @Override
    public void setOutputConstrainedToCurrentStep(boolean constrain) {
        // TODO: fixme
    }

    /**
     *
     * @return
     */
    @Override
    public String getDestinationLocation() {
        return location;
    }

    /**
     *
     * @param location
     */
    @Override
    public void setDestinationLocation(String location) {
        this.location = location;
    }

}
