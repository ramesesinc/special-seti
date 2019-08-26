package com.rameses.seti2.models;
 
import com.rameses.common.*;
import com.rameses.rcp.common.*;
import com.rameses.rcp.annotations.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;
import com.rameses.rcp.framework.ClientContext;
import com.rameses.rcp.constant.*;
import java.rmi.server.*;
import com.rameses.util.*;

public class LovModel extends HashMap {
    
    String connection;
    
    public LovModel(String conn) {
        super();
        connection = conn;
    }
    
    public def get( def n ) {
        return LOV.get( n.toUpperCase(), connection )*.key;
    }   
    
}
        