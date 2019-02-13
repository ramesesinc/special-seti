/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rameses.seti2.components;

import com.rameses.common.PropertyResolver;
import com.rameses.rcp.common.Column;
import com.rameses.rcp.control.XComponentPanel;
import com.rameses.rcp.ui.annotations.ComponentBean;

/**
 *
 * @author elmonazareno
 */
@ComponentBean("com.rameses.seti2.components.DataListComponent")
public class DataList extends XComponentPanel {

    private Column[] columns;     
    private int rows = 20;
    private String items;
    private String visibleWhen;
    private String styleRule;
    private String handler; 
    private String actionContext;
    private String menuContext; 
    private String formActions;
    
    
    public Column[] getColumns() { return columns; }
    public void setColumns(Column[] columns) { 
        this.columns = columns; 
        if ( datatable != null ) {
            datatable.setColumns( this.columns ); 
        } 
    } 
    /**
     * Creates new form DataList
     */
    public DataList() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        actionBar = new com.rameses.rcp.control.XActionBar();
        datatable = new com.rameses.rcp.control.XDataTable();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(0, 20));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jPanel2.setLayout(new java.awt.BorderLayout());

        actionBar.setName("formActions"); // NOI18N
        actionBar.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0));
        jPanel2.add(actionBar, java.awt.BorderLayout.CENTER);

        add(jPanel2, java.awt.BorderLayout.PAGE_START);

        datatable.setHandler("listModel");
        datatable.setItems("");
        datatable.setName("selectedItem"); // NOI18N
        add(datatable, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.control.XActionBar actionBar;
    private com.rameses.rcp.control.XDataTable datatable;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }
    
    public boolean isShowHorizontalLines() {
        return (datatable == null ? true : datatable.isShowHorizontalLines()); 
    }
    public void setShowHorizontalLines( boolean showHorizontalLines ) {
        if ( datatable != null ) {
            datatable.setShowHorizontalLines( showHorizontalLines ); 
        }
    }
    
    public boolean isShowVerticalLines() {
        return (datatable == null ? true : datatable.isShowVerticalLines()); 
    }
    public void setShowVerticalLines( boolean showVerticalLines ) {
        if ( datatable != null ) {
            datatable.setShowVerticalLines( showVerticalLines ); 
        }
    }
    
    public String getVisibleWhen() { return visibleWhen; }
    public void setVisibleWhen( String visibleWhen ) {
        this.visibleWhen = visibleWhen; 
    }
    
    public String getStyleRule() { return styleRule; } 
    public void setStyleRule( String styleRule ) {
        this.styleRule = styleRule;
    }
    
    public String getId() { 
        return (datatable == null ? null: datatable.getId());
    } 
    public void setId( String id ) {
        if ( datatable != null ) {
            datatable.setId(id); 
        }
    }
    
    public String getActionContext() { return actionContext; } 
    public void setActionContext( String actionContext ) {
        this.actionContext = actionContext; 
        if ( actionBar != null ) {
            actionBar.setName( actionContext ); 
            actionBar.setFormName(""); 
        } 
    } 
    
    public String getMenuContext() { return menuContext; } 
    public void setMenuContext( String menuContext ) {
        this.menuContext = menuContext; 
    }
    
    public int getRowHeight() {
        return (datatable == null ?  null : datatable.getRowHeight());
    }
    public void setRowHeight( int rowHeight ) {
        if ( datatable != null ) {
            datatable.setRowHeight(rowHeight);
        }
    }
    
    public int getRows() { return rows; } 
    public void setRows( int rows ) {
        this.rows = rows;
    }
    
    /**
     * @return the formActions
     */
    public String getFormActions() {
        return formActions;
    }

    /**
     * @param formActions the formActions to set
     */
    public void setFormActions(String formActions) {
        this.formActions = formActions;
    }
    
    protected void initComponentBean(com.rameses.rcp.common.ComponentBean bean) { 
        bean.setProperty("formActionContext", getFormActions() ); 
        bean.setProperty("actionContext", getActionContext()); 
        bean.setProperty("menuContext", getMenuContext()); 
        bean.setProperty("rows", getRows()); 
        bean.setProperty("items", getItems()); 
        Object sr = getProperty(getStyleRule()); 
        bean.setProperty("stylerule", (sr == null ? getStyleRule() : sr)); 
        bean.setProperty("ui", this); 
        String handlerName = getHandler();
        if ( handlerName != null && handlerName.trim().length() > 0 ) {
            Object listModel = PropertyResolver.getInstance().getProperty(bean, "listModel");
            setProperty(getHandler(), listModel, getBean());
        }       
    } 
    
    public void setProperty( String name, Object value ) { 
        if ( name != null && name.trim().length() > 0 ) {
            Object b = getBinding(); 
            Object bean = (b == null ? null : getBinding().getBean()); 
            setProperty(name, value, bean); 
        }
    }
    public void setProperty( String name, Object value, Object bean ) { 
        if ( name != null && name.trim().length() > 0 ) {
            PropertyResolver.getInstance().setProperty(bean, name, value);
        }
    }
    
    public void notifyDepends( String name ) {
        if ( name != null && name.trim().length() > 0 ) {
            Object b = getBinding(); 
            if ( b != null ) {
                getBinding().notifyDepends( name ); 
            }
        }
    }

    /**
     * @return the handler
     */
    public String getHandler() {
        return handler;
    }

    /**
     * @param handler the handler to set
     */
    public void setHandler(String handler) {
        this.handler = handler;
    }

    public void setEnabled( boolean b ) {
        datatable.setEnabled(b);
        actionBar.setEnabled(b);
    }

    public boolean isEnabled() {
        return datatable.isEnabled();
    }
    
    
}