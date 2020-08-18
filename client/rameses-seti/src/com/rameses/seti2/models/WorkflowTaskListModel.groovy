package com.rameses.seti2.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;
import com.rameses.rcp.framework.ClientContext;
import com.rameses.common.*;
import com.rameses.rcp.constant.*;
import java.rmi.server.*;
import java.util.concurrent.LinkedBlockingQueue

import com.rameses.util.*;
import com.rameses.seti2.models.*;
import com.rameses.client.notification.socketio.*;

public class WorkflowTaskListModel extends com.rameses.seti2.models.CrudListModel {
    
    @Service("WorkflowTaskNotificationService")
    def workflowTaskNotificationSvc;
    
    def _wfTaskListService;
    
    String _notificationid = null;
    public String getNotificationid() {
        return _notificationid;
    }

    public def getWfTaskListService() {
        if( _wfTaskListService == null ) {
            String conn = getConnection();
            if( conn !=null && conn.trim().length() > 0  ) {
                _wfTaskListService = InvokerProxy.instance.create("WorkflowTaskListService", null, conn );
            }
            else {
                _wfTaskListService = InvokerProxy.instance.create("WorkflowTaskListService");                
            }
        }
        return _wfTaskListService;
    }
    
    
    public def getQueryService() {
        return wfTaskListService;
    }

    public String getProcessName() {
        return workunit.info.workunit_properties.processName;
    }

    public void init() {
        if( !getProcessName() ) 
            throw new Exception("Please indicate a processName");
        if( workunit.info.workunit_properties.notificationid ) {
            _notificationid = workunit.info.workunit_properties.notificationid;
        }    
        else if(invoker.properties.notificationid ) {
            _notificationid = invoker.properties.notificationid;
        }
        super.init();  
        registerNotification();
    }
    
    def atomicBoolean = new java.util.concurrent.atomic.AtomicBoolean(false);
    def notifyHandler = [
        onMessage: { msg ->
            if( atomicBoolean.compareAndSet(false, true)) {
                reload();
                binding.refresh();
                atomicBoolean.set(false);
            }
        }
    ] as DefaultNotificationHandler;
    
    public void registerNotification() {
        if( getNotificationid()!=null ) {
            TaskNotificationClient.getInstance().register(getNotificationid(), notifyHandler );
        } 
        
    }
    
    @Close
    void onClose() {
        if( getNotificationid()!=null ) {
            TaskNotificationClient.getInstance().unregister(notifyHandler );
        }    
    }
    
    public void beforeQuery( def m ) {
        m.processname = getProcessName();
    }
    
    public def beforeFetchNodes( def m ) {
        m.processname = getProcessName();
        if( getNotificationid()!=null) m.notificationid =getNotificationid(); 
        m.uicontext = "tasklist";
        m.domain = domain;
        return null;
    }
    
    
}