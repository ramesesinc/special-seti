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

public abstract class AbstractCrudModel  {
    
    @Binding
    def binding;

    @Controller
    def workunit;
        
    @Invoker
    def invoker;
    
    @Caller
    def caller;
    
    @SubWindow
    def subWindow;
    
    @Script("User")
    def userInfo;
            
    @Service("PersistenceService")
    def persistenceSvc;
    
    @Service("QueryService")
    def qryService;
    
    @Script("ListTypes")
    def listTypes;
    
    def _lov;
    public def getLov() {
        if(_lov==null) {
            _lov = new LovModel(getConnection());
        }
        return _lov;
    }
    
    String role;
    String domain;
    String permission;
    List styleRules = [];
    def schema;
    def mode;
    
    boolean hasCallerProperty( property ) {
        return hasCallerProperty( property, caller ); 
    }
    boolean hasCallerProperty( property, bean ) {
        if ( bean == null ) return false; 
        return bean.metaClass.hasProperty(bean, property ); 
    }    
    boolean hasCallerMethod( property ) {
        return hasCallerMethod( property, caller ); 
    }     
    boolean hasCallerMethod( property, bean ) {
        if ( bean == null ) return false; 
        return bean.metaClass.respondsTo(bean, property ); 
    }     
    
    public boolean getDebug() {
        def g = workunit.info.workunit_properties.debug;
        if( g ) {
            try {
                return (g+"").toBoolean();
            }
            catch(e){
                return false;
            }
        }
        else {
            return false;
        }
    }
    
    private String _schemaName_ ;
    
    def secProvider = ClientContext.getCurrentContext().getSecurityProvider();
    
    public abstract String getFormType();
    
    //entity context is used in the expression
    public abstract def getEntityContext();
    
    public String getSchemaName() {
        if( _schemaName_ )
            return _schemaName_;
        else    
            return workunit?.info?.workunit_properties?.schemaName;
    }
    
    public void setSchemaName( String s ) {
        this._schemaName_ = s;
    }
    
    String _entitySchemaName_;   //used in case the view schema is not the same as entity schema
    public String getEntitySchemaName() {
        if( !_entitySchemaName_ ) {
            return workunit.info.workunit_properties.entitySchemaName;
        }
        return _entitySchemaName_;
    } 
    
    public void setEntitySchemaName( String s ) {
        this._entitySchemaName_ = s;
    }
    
    
    public def getPersistenceService() {
        String conn = getConnection();
        if( conn!=null && conn.trim().length() > 0 ) {
            return InvokerProxy.instance.create("PersistenceService", null, conn );
        }
        else {
            return persistenceSvc;
        }
    }
    
    public def getQueryService() {
        String conn = getConnection();
        if( conn!=null && conn.trim().length() > 0 ) {
            return InvokerProxy.instance.create("QueryService", null, conn );
        }
        else {
            return qryService;
        }
    }
    
    private String _connection;
    
    public String getConnection() {
        if( _connection !=null && _connection.trim().length() > 0 ) return _connection;
        if( workunit?.workunit?.module?.properties?.connection ) {
            _connection = workunit.workunit.module.properties.connection;
        }
        return _connection;
    }
    
    public void setConnection(String c) {
        _connection = c;
    }
    
    @FormTitle
    String getWindowTitle() {
        if( invoker.properties.windowTitle ) {
            return ExpressionResolver.getInstance().evalString(invoker.properties.windowTitle,this);
        }
        else {
            return getTitle();
        }
    }
    
    String getTitle() {
        if( invoker.properties.formTitle ) {
            return ExpressionResolver.getInstance().evalString(invoker.properties.formTitle,this);
        }
        if( invoker.caption ) {
            return invoker.caption;
        }
        return getSchemaName();
    }
    
    @FormId
    String getFormId() {
        if( invoker.properties.formId ) {
            return ExpressionResolver.getInstance().evalString(invoker.properties.formId,this);
        }
        return workunit.workunit.id;
    }
    
    //this is used for getting the actions
    public String getFormName() {
        if( workunit.info.workunit_properties.formName ) {
            return workunit.info.workunit_properties.formName;
        }
        return schemaName+":"+ getFormType();
    }
        
    void initRole() {
        domain = invoker.domain;
        role = invoker.role;
    }
    
    /*
    def extActions;
    public List getExtActions() {
        if(extActions) return extActions;
        def actions1 = []; 
        try { 
            Inv.lookup("formActions", null, { o-> 
                if(o.workunitid == invoker.workunitid) {
                    actions1 << createAction( o );
                } 
                return false;
            } as InvokerFilter ); 
        } catch(Throwable t) {
            System.out.println("[WARN] error lookup invokers caused by " + t.message);
        } 
        def actions2 = [];
        try { 
            def actionProvider = ClientContext.currentContext.actionProvider; 
            actions2 = actionProvider.lookupActions( schemaName+":"+ getFormType() +":formActions" );
        } catch(Throwable t) {
            System.out.println("[WARN] error lookup invokers caused by " + t.message);
        }
        extActions = (actions1.unique() + actions2.unique());
        return extActions.sort{ (it.index==null? 0: it.index) };
    }    
    */
   
    void updateWindowProperties() {
        //if(invoker.properties.target == 'window') {
            if(subWindow!=null) {
                subWindow.update( [title: getTitle() ] );
            }
        //}
    }
    
    final def createAction( inv ) {
        def a = new Action( inv.getName() == null? inv.getCaption()+"" :inv.getName() );
        
        def invProps = [:]; 
        invProps += inv.properties;
        a.name = inv.action;
        a.caption = inv.caption;
        if ( inv.index ) a.index = inv.index; 
        
        a.icon = invProps.remove("icon");
        a.immediate = "true".equals(invProps.remove("immediate")+"");
        a.visibleWhen = invProps.remove("visibleWhen");
        a.update = "true".equals(invProps.remove("update")+"");
        
        def mnemonic = invProps.remove("mnemonic");
        if ( mnemonic ) a.mnemonic( mnemonic.toString().charAt(0) );
        
        def tooltip = invProps.remove("tooltip");
        if ( tooltip ) a.tooltip = tooltip;
        
        if ( !invProps.isEmpty() ) a.properties += invProps;

        a.properties.put("Action.Invoker", inv);
        return a;
    }    
    
    //shared security features
    boolean isCreateAllowed() { 
        def allowCreate = invoker.properties.allowCreate;
        if(!allowCreate ) allowCreate = workunit.info.workunit_properties.allowCreate;  
        if( allowCreate ) {
            if (allowCreate == 'false') return false;
            if (allowCreate.startsWith('#{')){
                try {
                    boolean t = ExpressionResolver.getInstance().evalBoolean(allowCreate, getExprParams() );
                    if(t == false) return false;
                }
                catch(ign){
                    println 'Expression Error: ' + allowCreate;
                    return false;
                }
            }
        }
        if( !role ) return true;
        def createPermission = workunit.info.workunit_properties.createPermission;  
        if(createPermission==null) createPermission = "create";
        if(createPermission!=null) createPermission = schemaName+"."+createPermission;
        return secProvider.checkPermission( domain, role, createPermission );
    }
    
    boolean isFilterAllowed() {
        def allowed = workunit.info.workunit_properties.allowFilter;  
        if(!allowed) return true;
        return allowed.toBoolean();
    }
    
    boolean isShowColsAllowed() {
        def allowed = workunit.info.workunit_properties.allowShowCols;  
        if(!allowed) return true;
        return allowed.toBoolean();
    }
    
    boolean isPrintAllowed() {
        def allowed = workunit.info.workunit_properties.allowPrint;  
        if(!allowed) return true;
        return allowed.toBoolean();
    }
    
    boolean isOpenAllowed() {
        def allowOpen = invoker.properties.allowOpen;
        if(!allowOpen) allowOpen = workunit.info.workunit_properties.allowOpen;  
        if( allowOpen ) {
            if (allowOpen == 'false') return false;
            if (allowOpen.startsWith('#{')){
                try {
                    boolean t = ExpressionResolver.getInstance().evalBoolean(allowOpen, getExprParams() );
                    if(t == false) return false;
                } catch(ign){
                    println 'Expression Error: ' + allowOpen;
                    return false;
                }
            }
        }
        if( !role ) return true;
        def openPermission = workunit.info.workunit_properties.openPermission; 
        if(openPermission==null) openPermission = "open";
        if(openPermission!=null) openPermission = schemaName+"."+openPermission;
        return secProvider.checkPermission( domain, role, openPermission );
    }

    boolean isEditAllowed() { 
        def allowEdit = invoker.properties.allowEdit;
        if(!allowEdit) allowEdit = workunit.info.workunit_properties.allowEdit;        
         if( allowEdit ) {
            if (allowEdit == 'false') return false;
            if (allowEdit.startsWith('#{')){
                try {
                    boolean t = ExpressionResolver.getInstance().evalBoolean(allowEdit, getExprParams() );
                    if(t == false) return false;
                }
                catch(ign){
                    println 'Expression Error: ' + allowEdit;
                    return false;
                }
            }
        }
        if( !role ) return true;
        def editPermission = workunit.info.workunit_properties.editPermission; 
        if(editPermission==null) editPermission = "edit";
        if(editPermission!=null) editPermission = schemaName+"."+editPermission;
        return secProvider.checkPermission( domain, role, editPermission );
    }

    boolean isDeleteAllowed() { 
        def allowDelete = invoker.properties.allowDelete;  
        if(!allowDelete) allowDelete = workunit.info.workunit_properties.allowDelete;  
        if( allowDelete ) {
            if (allowDelete == 'false') return false;
            if (allowDelete.startsWith('#{')){
                try {
                    boolean t = ExpressionResolver.getInstance().evalBoolean(allowDelete, getExprParams() );
                    if(t == false) return false;
                }
                catch(ign){
                    println 'Expression Error: ' + allowDelete;
                    return false;
                }
            }
        }
        if( !role ) return true;
        def deletePermission = workunit.info.workunit_properties.deletePermission; 
        if(deletePermission==null) deletePermission = "delete";
        if(deletePermission!=null) deletePermission = schemaName+"."+deletePermission;
        return secProvider.checkPermission( domain, role, deletePermission );
    }
    
    def getExprParams() {
        return [entity:getEntityContext(), context: this, mode: mode];
    }
    
    boolean isViewReportAllowed() { 
        def allowViewReport = workunit.info.workunit_properties.allowViewReport;  
        if( allowViewReport ) {
            if (allowViewReport == 'false') return false;
            if (allowViewReport.startsWith('#{')){
                try {
                    boolean t = ExpressionResolver.getInstance().evalBoolean(allowViewReport, getExprParams() );
                    if(t == false) return false;
                }
                catch(ign){
                    println 'Expression Error: ' + allowViewReport;
                    return false;
                }
            }
        }
        return true;
    }
    
    def showMenu() {
        showMenu( null );
    }
    
    def showMenu( inv ) {
        def menu = inv?.properties?.context;
        if( menu==null ) menu = "menuActions";
        def op = showDropdownMenu(menu);
        if(menu=="menuActions") {
            op.add( new com.rameses.seti2.models.PopupAction(caption:'Close', name:'_close', obj:this, binding:binding) );
        }
        return op;
    }
    
    def showDropdownMenu(String tag) {
        def op = new PopupMenuOpener();
        //op.add( new ListAction(caption:'New', name:'create', obj:this, binding: binding) );
        try {
            def invf = { inv->
                def vWhen = inv.properties.visibleWhen;
                if(!vWhen) return true;  
                try {
                    return ExpressionResolver.getInstance().evalBoolean(vWhen, getExprParams() );
                }
                catch(ign){
                    println 'Expression Error: ' + vWhen;
                    return false;
                }
            } as InvokerFilter;
            op.addAll( Inv.lookupOpeners(schemaName+":" + getFormType() + ":" + tag, [entity:entityContext], invf) );
        } catch(Throwable ign){;}
        return op;
    }
    
    //information about the user
    public def getUser() {
        def app = userInfo.env;
        return [objid: app.USERID, name: app.NAME, fullname: app.FULLNAME, username: app.USER ];
    }
    
    public String getUserid() {
        return userInfo.env.USERID;
    }
    
    def showInfo() {
        throw new Exception("No info handler found");
    }
        
    def showHelp() {
        throw new Exception("No help handler found");
    }
    
    protected boolean pageExists(String pageName) {
        if( !workunit.views ) return false;
        def z = workunit.views.find{ it.name == pageName };
        if(z) return true;
        return false;
    }
    
    //hook example: business_application:form:reports
    def viewReport() {
        def op = new PopupMenuOpener();
        try {
            //def list1 = InvokerUtil.lookupOpeners( inv.properties.category, [entity:entity] );
            //op.addAll( list1 );
            String _rptType = schemaName+":reports";
            if( getFormType() ) _rptType = schemaName+":" + getFormType() + ":reports";
            
            def list = Inv.lookupOpeners(_rptType, [entity:entityContext]);
            list.each {
                boolean _include = true;
                if(it.properties.visibleWhen) {
                    try {
                        def vw = it.properties.visibleWhen;
                        boolean t = ExpressionResolver.getInstance().evalBoolean(vw, getExprParams() );
                        if(t != true) _include = false;
                    }
                    catch(ee) {
                        System.out.println("Error in viewReport " + ee.getMessage() );
                        return;
                    }
                }
                if( _include ) {
                    if( it.properties.id !=null  ) {
                        def _id = it.properties.id;
                        if(!op.items.find{ it.properties.id == _id } ) op.add( it );
                    }
                    else {
                        op.add( it );
                    }
                }
            }
        } catch(Throwable ign){;}
        if(op.items.size()==0) throw new Exception("No reports defined in category ");
        return op;
    }
    
    //additional to get the current page
    public def getCurrentPage() {
        return workunit.workunit.currentPage.name;
    }    
    
    String getInvokerProperty( String name, boolean cascade ) { 
        if ( !name ) return null; 
        if ( invoker == null ) return null; 
        
        def val = invoker.properties.get( name ); 
        if ( val ) return val;
        
        if ( !cascade ) return null; 
        if ( workunit?.info?.workunit_properties == null ) return null; 
        
        return workunit.info.workunit_properties.get( name ); 
    }
}
        