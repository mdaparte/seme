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
package gov.sandia.seme.impl.datachannels;

import gov.sandia.seme.framework.DataOutOfFrameException;
import gov.sandia.seme.framework.ChannelType;
import gov.sandia.seme.framework.DataChannel;
import gov.sandia.seme.framework.DataStatus;
import gov.sandia.seme.framework.Descriptor;
import gov.sandia.seme.framework.MissingDataPolicy;
import gov.sandia.seme.framework.Step;
import gov.sandia.seme.util.DescribableImpl;
import gov.sandia.seme.util.LazyModulusArray;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.log4j.Logger;

/**
 * Provides a simple value-based data channel. This type of data channel
 * provides real-number values which are used in event detection, or in pattern
 * recognition algorithms, or which are inputs to {@link CompositeValue}
 * channels. Simple values come with the following options:
 * <p>
 * <b>Set points:</b> provide a range of values which are acceptable; when a
 * value falls outside the set-points, checking the status of this channel will
 * return a {@literal EDataStatus.OUT_OF_CTL_LIMIT} status value.
 * <p>
 * <b>Valid range:</b> provides a range of values which are valid; for example,
 * a pH reading of 15 is a physical impossibility, and such a reading should not
 * be used. Values which fall outside the valid range will set the channel's
 * status to {@literal EDataStatus.OUT_OF_VALID_RANGE}.
 * <p>
 * <b>Precision:</b> provides a minimum sigma value for event detection
 * algorithms. A value change that is less-than-or-equal-to the precision of a
 * channel is never considered an event (unless it places the value outside the
 * set points or valid range, which is a special case).
 * <p>
 * <b>Units:</b> is for information purposes only; CANARY-EDS does <i>not</i>
 * do units conversion. Ever.
 * <p>
 * <b>Alarms:</b> provides a list of {@literal FlagChannel}s that modify this
 * data channel's behavior, usually by signaling a physical problem with a
 * sensor.
 * <p>
 * @author dbhart
 * @author $LastChangedBy$
 * @version $Rev$, $Date$
 */
public class SimpleChannel extends DescribableImpl implements DataChannel,
                                                              Serializable {

    private static final Logger LOG = Logger.getLogger(SimpleChannel.class);
    ArrayList<DataChannel> alarms;
    final boolean copyMissing = false;
    int dataFrameSize;
    LazyModulusArray dataValues;
    String name;
    HashMap options;
    ArrayList<String> requires;
    String tag;
    boolean warnedNoStatus = false;

    /**
     * <p>
     */
    public SimpleChannel() {
        this.dataFrameSize = 100;
    }

    @Override
    public void addNewValue(Object value, Step step) {
        double val = ((Number) value).doubleValue();
        int idx = step.getIndex();
        try {
            this.dataValues.set(idx, val);
        } catch (DataOutOfFrameException ex) {
            LOG.error(this.name + ": out of frame exception, "
                    + "please set or increase the frameSize option.", ex);
        }
    }

    @Override
    public void addRequires(String tag) {
        LOG.warn(this.name + ": required tags not implemented yet");
    }

    @Override
    public void configure(Descriptor desc) {
        this.name = desc.getName();
        this.tag = desc.getTag();
        this.options = desc.getOptions();
        for (Iterator it = options.keySet().iterator(); it.hasNext();) {
            String key = (String) it.next();
            Object val = options.get(key);
            switch (key) {
                case "name":
                case "tag":
                case "className":
                    break;
                case "parameter":
                    this.metaData.put(key, val);
                    break;
                case "description":
                    this.metaData.put(key, val);
                    break;
                case "setPointHigh":
                    this.metaData.put(key, val);
                    break;
                case "setPointLow":
                    this.metaData.put(key, val);
                    break;
                case "validRangeHigh":
                    this.metaData.put(key, val);
                    break;
                case "validRangeLow":
                    this.metaData.put(key, val);
                    break;
                case "precision":
                    this.metaData.put(key, val);
                    break;
                case "units":
                    this.metaData.put(key, val);
                    break;
                case "frameSize":
                    this.dataFrameSize = (int) options.get(key);
                    break;
                case "newDataStyle":
                    this.setNewDataStyle((MissingDataPolicy) options.get(key));
                    break;
                default:
                    this.metaData.put(key, val);
                    LOG.warn(
                            this.name + ": unknown option (" + key + " = " + options.get(
                                    key).toString() + "})");
                    break;
            }
        }
        this.dataValues = new LazyModulusArray(this.dataFrameSize);
        this.dataValues.setCopyMissing(this.copyMissing);
    }

    @Override
    public String getClassName() {
        return "SimpleChannel";
    }

    @Override
    public Descriptor getConfiguration() {
        HashMap config = new HashMap();
        config.put("tag", this.getTag());
        config.put("className", this.getClassName());
        if (this.getStringOpt("parameter") != null) {
            config.put("parameter", this.getStringOpt("parameter"));
        }
        if (this.getStringOpt("description") != null) {
            config.put("description", this.getStringOpt("description"));
        }
        for (Iterator it = options.keySet().iterator(); it.hasNext();) {
            String key = (String) it.next();
            config.put(key, options.get(key).toString());
        }
        return null;
        //        return config;
    }

    @Override
    public double getDoubleValue(int index) {
        try {
            return this.dataValues.get(index);
        } catch (DataOutOfFrameException ex) {
            LOG.error(this.name + ": out of frame exception, "
                    + "please set or increase the frameSize option.", ex);
        }
        return Double.NEGATIVE_INFINITY;
    }

    @Override
    public int getIntegerValue(int index) {
        try {
            return (int) this.dataValues.get(index);
        } catch (DataOutOfFrameException ex) {
            LOG.error(this.name + ": out of frame exception, "
                    + "please set or increase the frameSize option.", ex);
        }
        return Integer.MIN_VALUE;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * <p>
     * @param style
     */
    @Override
    public void setNewDataStyle(MissingDataPolicy style) {
        switch (style) {
            case TYPICAL:
                this.dataValues.setCopyMissing(false);
                break;
            case CHANGES:
                this.dataValues.setCopyMissing(true);
                break;
            default:
                this.dataValues.setCopyMissing(false);
                break;
        }
        this.options.put("newDataStyle", style.toString());
    }

    @Override
    public ArrayList<String> getRequires() {
        ArrayList<String> req = new ArrayList();
        req.add(this.tag);
        return req;
    }

    @Override
    public void setRequires(ArrayList<String> requires) {
        LOG.warn(this.name + ": required tags not implemented yet");
    }

    @Override
    public DataStatus getStatus() {
        if (!this.warnedNoStatus) {
            this.warnedNoStatus = true;
            LOG.warn(this.name + ": getStatus() not implemented yet");
        }
        return DataStatus.NORMAL;
    }

    @Override
    public String getStringValue(int index) {
        try {
            return new Double(this.dataValues.get(index)).toString();
        } catch (DataOutOfFrameException ex) {
            LOG.error(this.name + ": out of frame exception, "
                    + "please set or increase the frameSize option.", ex);
        }
        return null;
    }

    @Override
    public String getTag() {
        return this.tag;
    }

    @Override
    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public ChannelType getType() {
        return ChannelType.VALUE;
    }

    @Override
    public Object getValue(int index) {
        try {
            return this.dataValues.get(index);
        } catch (DataOutOfFrameException ex) {
            LOG.error(this.name + ": out of frame exception,"
                    + " please set or increase the frameSize option.", ex);
        }
        return null;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void linkChannel(DataChannel chan) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeRequires(String tag) {
        LOG.warn(this.name + ": required tags not implemented yet");
    }

    @Override
    public void unlinkChannel(DataChannel chan) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
