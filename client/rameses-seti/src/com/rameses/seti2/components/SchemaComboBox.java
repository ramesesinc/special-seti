/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rameses.seti2.components;

import com.rameses.rcp.control.XComponentPanel;

/**
 *
 * @author elmonazareno
 */
@com.rameses.rcp.ui.annotations.ComponentBean("com.rameses.seti2.components.SchemaComboBoxComponent")
public class SchemaComboBox extends XComponentPanel {

    private String expression;
    private String visibleWhen;
    private String disableWhen;
    
    /**
     * Creates new form SchemaComboBox
     */
    public SchemaComboBox() {
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

        xComboBox1 = new com.rameses.rcp.control.XComboBox();

        setLayout(new java.awt.BorderLayout());

        xComboBox1.setDisableWhen("#{ disableWhen }");
        xComboBox1.setExpression("#{ expression }");
        xComboBox1.setItems("list");
        xComboBox1.setName("value"); // NOI18N
        xComboBox1.setVisibleWhen("#{ visibleWhen }");
        xComboBox1.setAllowNull(false);
        xComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xComboBox1ActionPerformed(evt);
            }
        });
        add(xComboBox1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void xComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_xComboBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.control.XComboBox xComboBox1;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the expression
     */
    public String getExpression() {
        return expression;
    }

    /**
     * @param expression the expression to set
     */
    public void setExpression(String expression) {
        this.expression = expression;
    }

    /**
     * @return the visibleWhen
     */
    public String getVisibleWhen() {
        return visibleWhen;
    }

    /**
     * @param visibleWhen the visibleWhen to set
     */
    public void setVisibleWhen(String visibleWhen) {
        this.visibleWhen = visibleWhen;
    }

    /**
     * @return the disableWhen
     */
    public String getDisableWhen() {
        return disableWhen;
    }

    /**
     * @param disableWhen the disableWhen to set
     */
    public void setDisableWhen(String disableWhen) {
        this.disableWhen = disableWhen;
    }
}
