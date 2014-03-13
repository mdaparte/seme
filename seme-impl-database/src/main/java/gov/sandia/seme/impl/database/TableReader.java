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

import gov.sandia.seme.framework.InputConnection;
import gov.sandia.seme.framework.Step;
import gov.sandia.seme.util.MessagableImpl;
import org.apache.log4j.Logger;

/**
 *
 * @author dbhart
 */
public class TableReader extends MessagableImpl implements InputConnection {
    private static final Logger LOG = Logger.getLogger(TableReader.class);
    
   // DriverManager dmng;
    
    private boolean used = false;
    private boolean connected = false;
    private boolean registered = false;
    
    private String inputType = null;
    private String connType = null;
    private String driverClass = null;
    private boolean debug = false;
    
    //-----------------------------------
    private boolean isConnected = false;
    private boolean isRegistered = false;
    private boolean isInitialized = false;
    //provate boolean IsInput = false;
    //provate boolean IsOutput = false;
    //provate boolean IsControl = false;
    //private int CurrMsgID = 0
    //OutputQueue = []
    //RecievedQueue = []
    //SentQueue = []
    //ControlQueue = []
    //messageCenter = cws.MessageCenter();
    //private int IdleCount                 = 0;
    //LastDateTimeSeen                      = '';
    //provate boolean RcvdNewData           = false;
    //private int AverageDataProcessingDelay = 20;
    //provate boolean IsWaiting             = false;
    //provate boolean msgsClear             = false;
    //clear_delay = 2;
    //configuration
    
    //provate boolean IsInpInit             = false;
    //provate boolean IsOutInit             = false;
    //provate boolean IsCtlInit             = false;
    //ConnID
    
    //-- Input Uses
    //provate boolean isUsed                = false;
    //provate boolean isActive              = false;
    //input_fields = {};
    
    //--Messenger Uses
    //addMessageCS
    //getMessageCS
    //inputStrm
    //outputStrm
    //sockAddr
    //provate boolean dbUpdated             = false;
    //private int dbUpdateDone              = 0;
    //LogFID
    //queue
    //private String extraMessage           = "";
    //fileCache
    
    //addResultsCS
    //addParamRsCS
    //sqlCreate
    //sqlA
    private int haveGotData                 = 0;
    
    private String sqlDateConvertA          = "";
    private String sqlDateConvertB          = "";
    private String sqlDateConvertC          = "";
    private String sqlDateDatatype          = "DATETIME";
    
    //tableFldIDs = [];
    //tableParNum = [];
    
    private String  connID                  = "NEWCONN";
    private int     connIDNum               = 0;
    //private String  connType              = "";
    private String  connUrl                 = "";
    private String  canaryID                = "CANARY";
    private String  driverConfig            = "";
    //private String  driverClass           = "";
    private String  driverDatasourceClass   = "";
    private String  connInstance            = "";
    private String  connIPAddress           = "";
    private int     connPort                = 0;
    private String  connUsername            = "";
    private String  connPassword            = "";
    private boolean connInteractive         = false;
    private String  connToDateFunc          = "";
    private String  connToDateFmt           = "";
    //time
    private String logPath                  = "";
    private String dataDirPath              = "";
    private String runMode                  = "";
    //GUIHandle
    private int state                       = 0;
    private int dataToSend                  = 0;
    private int tsToBackfill                = 0;
    private String connState                = "enabled";
    private boolean useContinue             = false;
    
    //-- Messenger Specific
    private String msgrType                 = "";
    private boolean bDone                   = false;
    private int curTs                       = 0;
    private int TimeDrift                   = 0;
    private int dataDone                    = 0;
    
    //-- Input Specific
    private String inputID                  = "";
    //private String inputType              = "";
    private String inputFormat              = "";
    private String inputTable               = "CWS_INPUT";
    private String timestepField            = "TIME_STEP";
    private String sqlQueryA                = "";
    private String sqlQueryAA               = "";
    private String sqlQueryB                = "";
    private String sqlQueryC                = "";
    private String sqlQueryD1               = "";
    private String sqlQueryD2               = "";
    private boolean IsBatch                 = false;
    
    //-- Output Specific
    private String outputID                 = "";
    private String outputType               = "";
    private String outputFormat             = "";
    private String outputTable              = "CWS_OUTPUT";
    
    //-- Database Schema Parameters
    private String  dbRtFnTimestep          = "TIME_STEP";
    private String  dbRtFnParameterTag      = "TAG_NAME";
    private String  dbRtFnParameterValue    = "VALUE";
    private boolean dbRtFnParameterQuality  = false;
    
    private String  dbWtOnCondition         = "all";
    private String  dbWtFnTimestep          = "TIME_STEP";
    private String  dbWtFnInstanceID        = "INSTANCE_ID";
    private String  dbWtFnStationID         = "LOCATION_ID";
    private boolean dbWtFnAlgorithmID       = false;
    private boolean dbWtFnParameterID       = false;
    private boolean dbWtFnParameterResid    = false;
    private boolean dbWtFnParameterTag      = false;
    private String  dbWtFnEventCode         = "DETECTION_INDICATOR";
    private String  dbWtFnEventProb         = "DETECTION_PROBABILITY";
    private String  dbWtFnEventContrib      = "CONTRIBUTING_PARAMETERS";
    private String  dbWtFnComments          = "ANALYSIS_COMMENTS";
    private boolean dbWtFnPatternID         = false;
    private boolean dbWtFnPatternProb       = false;
    private boolean dbWtFnPatternID2        = false;
    private boolean dbWtFnPatternProb2      = false;
    private boolean dbWtFnPatternID3        = false;
    private boolean dbWtFnPatternProb3      = false;
    private boolean dbReadCurrent           = false;
    
    
    //-----------------------------------
    
    public TableReader(String label, int delay) {
        super(label, delay);
    }
    
    
    /**
     * Establishes a connection to the web, database, or file.
     */
    public void connect() {
        LOG.trace("Create JDBC Connection");
        try{
            if(this.isConnected()) return;
            if(!this.isRegistered()) this.registerDriver();
  //---------------------------------------
            
            
            
  //---------------------------------------            
        }catch(Exception e){
            LOG.trace("JDBC Error in Connection Creation.");
            System.out.println("JDBC Error in Connection Creation.");
        }
    }
    /**
     * Disconnects from the web, database, or file.
     */
    public void disconnect(){
        
    }
    
    public void update(){
        
    }
    
    /**
     * Register the connection driver.
     */
    public void registerDriver(){
        switch(this.connType.toLowerCase()){
            case "jdbc":
            case "db":
                try{
                    if(!this.driverClass.isEmpty()){
                        Class.forName(this.driverClass).newInstance();
                        this.registered = true;
                    }
                }catch(Exception e){
                    LOG.trace(e.toString());
                    System.out.println("Could not load driver: " + this.driverClass + "\n" + e.toString());
                    this.registered = false;
                }
        }
    }
    
    /**
     * Initializes the input for the table reader.
     */
    public void initializeInput(){
        if(!this.isUsed()){
            if(debug) LOG.trace("DataSource Unused - Exiting");
            return;
        }
        if(!this.isConnected()){
            this.connect();
        }
        if(this.inputType==null){
            this.inputType = this.connType;
        }
    }
    
    /**
     * Checks to see if the connection is being used.
     * @return True if used, False if not. 
     */
    public boolean isUsed(){
        return this.used;
    }
    
    /**
     * Checks whether a database is connected or not.
     * @return True if connected, False if not.
     */
    public boolean isConnected(){
        return this.connected;
    }
    
    /**
     * Checks whether a database driver has been registered or not.
     * @return True if registered, False if not.
     */
    public boolean isRegistered(){
        return this.registered;
    }
    
    
    public String getSourceLocation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setSourceLocation(String location) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isInputConstrainedToCurrentStep() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setInputConstrainedToCurrentStep(boolean contrain) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int readInputAndProduceMessages() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int readInputAndProduceMessages(Step stepPar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
