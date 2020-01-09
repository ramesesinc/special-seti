package com.rameses.seti2.models;

import com.rameses.common.*;
import com.rameses.rcp.common.*;
import com.rameses.rcp.annotations.*;
import com.rameses.osiris2.common.*;
import com.rameses.rcp.framework.ClientContext;
import com.rameses.rcp.constant.*;
import com.rameses.util.*;
import com.rameses.rcp.annotations.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.reports.*;
import java.rmi.server.UID;

/***
* modes
*    This is used for entities with objid
*/
public class EntityFormReportModel extends FormReportModel {
    
    def entity;
    
    public def getQuery() {
        if(entity==null) entity = caller?.entity;
        return [ objid: entity.objid ];
    }
    
   
}

     