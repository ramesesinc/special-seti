package com.rameses.seti2.models;

import com.rameses.rcp.common.*;
import com.rameses.rcp.annotations.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;
import com.rameses.rcp.framework.ClientContext;
import com.rameses.common.*;
import com.rameses.rcp.constant.*;
import java.rmi.server.*;
import com.rameses.util.*;
import com.rameses.seti2.models.CrudFormModel;

import com.rameses.client.notification.socketio.*;

public class WorkflowTaskModel extends CrudFormModel implements WorkflowTaskListener {
    
    @Service("WorkflowTaskService")
    def workflowTaskSvc;
    
    @Service("WorkflowTaskNotificationService")
    def workflowTaskNotificationSvc;
    
    def task;
    String processName;
    List transitions = [];
    
    def messagelist = [];
    
    public def getWorkflowTaskService() {
        return workflowTaskSvc;
    }
    
    def getExprParams() {
        def m = super.getExprParams();
        m.task = task;
        return m;
    }
    
    /*** 
     * default behavior is it will reload the entity after signal.
     * If you do not want this behavior you must override afterSignal. 
     ****/
    public void afterSignal( def transition, def result ) {
        reload();
    }
    
    //do some overrides here if you want something to do in the client before clicking signal
    public boolean beforeSignal( def param  ) {
        return true;
    }
    
    protected void buildMessage() {
        //message is only viewed by the owner.
        messagelist.clear();
        if( task?.assignee?.objid ==  user.objid) {
            if(task?.message) messagelist.add( task.message );
        }
    }
    
    public String getProcessName() {
        String procName = workunit.info.workunit_properties.processName;
        if(!procName) procName = schemaName;
        return procName;
    }

    public def open() {
        //we need to do this so it will not affect the original entity from the list.
        def tsk = null;
        def n = entity;
        entity = [:];
        n.each { k,v->
            if(k=="task") {
                tsk = v;
            }
            else {
                entity.put(k,v);
            }
        }
        
        super.open(false);
        if( entity.taskid ) tsk = [taskid: entity.taskid ];
        if( tsk?.taskid ) { 
            task = workflowTaskService.findTask( [processname: getProcessName(), taskid: tsk.taskid ] );
            if ( task ) {
                buildTransitionActions(task);  
                buildMessage();
                if( pageExists(task.state)) {
                    return task.state;
                }
            }
        } 
        afterOpen();
        registerNotification();
        return null;
    }
    
    boolean refreshingScreen = false;

    public def signal( def transition ) {
        transition.processname = getProcessName();
        transition.taskid = task.taskid;
        transition.refid = task.refid;
        refreshingScreen = true;
        task = workflowTaskService.signal( transition );
        if( task.taskid ) {
            transitions.clear();
            if( task.transitions ) {
                buildTransitionActions(task);
            }
        }
        else {
            task = [:];
        }
        binding.refresh();
        buildMessage();
        refreshingScreen = false;
        afterSignal(transition, task);
        if( pageExists(task.state)) {
            return task.state;
        }
        return "default";
        
        /*
        def newTask = workflowTaskService.signal( transition );
        //refresh the list
        try {
            if( hasCallerProperty('listHandler')) {
                caller.listHandler.reload();
            }
        } catch(Throwable e){;}
        */
       return null;
    }
    
    final void buildTransitionActions( def tsk ) {
         if(! tsk) return; 
         if( tsk.state == 'end' ) return; 
         if( !tsk.assignee?.objid && tsk.role != null ) {
            def h = {
                def m = [:];
                m.processname = getProcessName();
                m.taskid = task.taskid;
                m.refid = task.refid;
                refreshingScreen = true;
                def res = workflowTaskService.assignToMe(m);
                task.assignee = res.assignee;
                task.startdate = res.startdate;
                transitions.clear();
                buildTransitionActions(task);
                refreshingScreen = false;
            }
            transitions << new WorkflowAssignToMeAction( tsk, h );
        }
        else {
            def h = { t->
                return signal(t);
            }
            tsk.transitions.each{ 
                transitions << new WorkflowTransitionAction( it, tsk, h, this ) 
            }
        }
    }
    
    public List getFormActions() {
        def list = new ArrayList();
        list.addAll(transitions); 
        return list;
    }
    
    public boolean getShowNavigation() {
        return false;
    }
    
    public boolean isCreateAllowed() {
        return false;
    }
    
    public boolean isEditAllowed() {
        return false;
    }

    public boolean isViewReportAllowed() { 
        return false;
    }

    //This is to display the standard workflow actions
    public List getNavActions() {
        def actions2 = [];
        try { 
            def actionProvider = ClientContext.currentContext.actionProvider; 
            actions2 = actionProvider.lookupActions( "workflowtask:navActions" );
        } catch(Throwable t) {
            System.out.println("[WARN] error lookup invokers caused by " + t.message);
        }
        return actions2.sort{ (it.index==null? 0: it.index) };
    }     
    
    def showTaskInfo() {
        return Inv.lookupOpener("workflowtask:showinfo");
    }

    public InvokerFilter getSectionFilter() {
        return null;
    }
    
    def getSections() {
        try {
            return Inv.lookupOpeners(getSchemaName() + ":section",[:],sectionFilter);
        } 
        catch(Exception ex){;}
    }
    
    void changeAssignee() {
        //task.domain;
        //task.assignee;
        //task.role;

        def h = { o->
            def m = [processname: processName, taskid: task.taskid, assignee : o ];
            workflowTaskSvc.changeAssignee( m );
            open();
            binding.refresh();
        }
        def q = [domain: task.domain, role: task.role];
        Modal.show("sys_user_role:lookup", [query: q,  onselect: h ] );
    }
    
    
    //we are now dependent on this routine.
    def notifyHandler = [
        onMessage: { msg ->
            println msg;
            if(!refreshingScreen) return;
            if( msg.refid == entity.objid ) {
                reload();
            }
        }
    ] as DefaultNotificationHandler;

    public void registerNotification() {
        TaskNotificationClient.getInstance().register(getProcessName(), notifyHandler );
    }
    
    @Close
    void onClose() {
        TaskNotificationClient.getInstance().unregister( notifyHandler );
    }


}

