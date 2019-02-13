/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rameses.seti2.views;

/**
 *
 * @author ramesesinc
 */
public class CrudListPage extends javax.swing.JPanel {

    /**
     * Creates new form CrudListPage
     */
    public CrudListPage() {
        initComponents();
        btnDelete.setToolTipText("Delete");
        btnCreate.setToolTipText("New");
        btnOpen.setToolTipText("Open");
        btnPrint.setToolTipText("Print");
        btnRefresh.setToolTipText("Refresh");
        
        btnFilter.setToolTipText("Filter Criteria");
        btnSelectColumn.setToolTipText("Select Columns");        
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
        xLabel1 = new com.rameses.rcp.control.XLabel();
        jPanel2 = new javax.swing.JPanel();
        xPanel2 = new com.rameses.rcp.control.XPanel();
        jToolBar1 = new javax.swing.JToolBar();
        btnCancel1 = new com.rameses.rcp.control.XButton();
        btnCreate = new com.rameses.rcp.control.XButton();
        btnOpen = new com.rameses.rcp.control.XButton();
        btnDelete = new com.rameses.rcp.control.XButton();
        btnPrint = new com.rameses.rcp.control.XButton();
        btnFilter = new com.rameses.rcp.control.XButton();
        btnSelectColumn = new com.rameses.rcp.control.XButton();
        btnRefresh = new com.rameses.rcp.control.XButton();
        btnRefresh1 = new com.rameses.rcp.control.XButton();
        btnRefresh2 = new com.rameses.rcp.control.XButton();
        xActionBar1 = new com.rameses.rcp.control.XActionBar();
        xLabel2 = new com.rameses.rcp.control.XLabel();
        xSubFormPanel1 = new com.rameses.rcp.control.XSubFormPanel();
        jPanel3 = new javax.swing.JPanel();
        xPanel1 = new com.rameses.rcp.control.XPanel();
        xActionTextField1 = new com.rameses.rcp.control.XActionTextField();
        jToolBar2 = new javax.swing.JToolBar();
        xButton2 = new com.rameses.rcp.control.XButton();
        xButton1 = new com.rameses.rcp.control.XButton();
        jPanel6 = new javax.swing.JPanel();
        listPanel1 = new com.rameses.seti2.components.ListPanel();

        setPreferredSize(new java.awt.Dimension(850, 450));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 3, 0));
        jPanel1.setLayout(new java.awt.BorderLayout());

        xLabel1.setExpression("#{title}");
        xLabel1.setBackground(new java.awt.Color(255, 255, 255));
        xLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 2, 10, 5));
        xLabel1.setFontStyle("font-size:14; font-weight:bold;");
        xLabel1.setOpaque(true);
        jPanel1.add(xLabel1, java.awt.BorderLayout.NORTH);

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 0, 0, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(400, 28));
        jPanel2.setLayout(new java.awt.BorderLayout());

        xPanel2.setLayout(new com.rameses.rcp.control.layout.XLayout());

        jToolBar1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        btnCancel1.setCaption("");
        btnCancel1.setName("showMenu"); // NOI18N
        btnCancel1.setAutoRefresh(false);
        btnCancel1.setFocusable(false);
        btnCancel1.setIconResource("images/menu.png");
        btnCancel1.setImmediate(true);
        btnCancel1.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jToolBar1.add(btnCancel1);

        btnCreate.setCaption("");
        btnCreate.setName("create"); // NOI18N
        btnCreate.setVisibleWhen("#{createAllowed==true}");
        btnCreate.setAccelerator("ctrl N");
        btnCreate.setFocusable(false);
        btnCreate.setIconResource("images/toolbars/create.png");
        btnCreate.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jToolBar1.add(btnCreate);

        btnOpen.setCaption("");
        btnOpen.setDepends(new String[] {"selectedItem"});
        btnOpen.setName("open"); // NOI18N
        btnOpen.setVisibleWhen("#{openAllowed==true}");
        btnOpen.setAccelerator("ctrl O");
        btnOpen.setAutoRefresh(false);
        btnOpen.setFocusable(false);
        btnOpen.setIconResource("images/toolbars/open.png");
        btnOpen.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jToolBar1.add(btnOpen);

        btnDelete.setCaption("");
        btnDelete.setDepends(new String[] {"selectedItem"});
        btnDelete.setName("removeEntity"); // NOI18N
        btnDelete.setVisibleWhen("#{deleteAllowed==true}");
        btnDelete.setFocusable(false);
        btnDelete.setIconResource("images/toolbars/trash.png");
        btnDelete.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jToolBar1.add(btnDelete);

        btnPrint.setCaption("");
        btnPrint.setName("print"); // NOI18N
        btnPrint.setVisibleWhen("#{printAllowed}");
        btnPrint.setAccelerator("ctrl P");
        btnPrint.setAutoRefresh(false);
        btnPrint.setFocusable(false);
        btnPrint.setIconResource("images/toolbars/printer.png");
        btnPrint.setImmediate(true);
        btnPrint.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jToolBar1.add(btnPrint);

        btnFilter.setCaption("");
        btnFilter.setName("showFilter"); // NOI18N
        btnFilter.setVisibleWhen("#{filterAllowed}");
        btnFilter.setAccelerator("ctrl F");
        btnFilter.setAutoRefresh(false);
        btnFilter.setFocusable(false);
        btnFilter.setIconResource("images/toolbars/filter.png");
        btnFilter.setImmediate(true);
        btnFilter.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jToolBar1.add(btnFilter);

        btnSelectColumn.setCaption("");
        btnSelectColumn.setName("selectColumns"); // NOI18N
        btnSelectColumn.setVisibleWhen("#{showColsAllowed}");
        btnSelectColumn.setAutoRefresh(false);
        btnSelectColumn.setFocusable(false);
        btnSelectColumn.setIconResource("images/toolbars/table.png");
        btnSelectColumn.setImmediate(true);
        btnSelectColumn.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jToolBar1.add(btnSelectColumn);

        btnRefresh.setCaption("");
        btnRefresh.setName("refresh"); // NOI18N
        btnRefresh.setAccelerator("ctrl R");
        btnRefresh.setAutoRefresh(false);
        btnRefresh.setFocusable(false);
        btnRefresh.setIconResource("images/toolbars/refresh.png");
        btnRefresh.setImmediate(true);
        btnRefresh.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jToolBar1.add(btnRefresh);

        btnRefresh1.setCaption("");
        btnRefresh1.setName("syncPush"); // NOI18N
        btnRefresh1.setVisibleWhen("#{ showSyncUpload == true }");
        btnRefresh1.setAccelerator("ctrl R");
        btnRefresh1.setAutoRefresh(false);
        btnRefresh1.setFocusable(false);
        btnRefresh1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRefresh1.setIconResource("images/toolbars/syncpush.png");
        btnRefresh1.setImmediate(true);
        btnRefresh1.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnRefresh1.setToolTipText("Sync Upload");
        btnRefresh1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnRefresh1);

        btnRefresh2.setCaption("");
        btnRefresh2.setName("syncPull"); // NOI18N
        btnRefresh2.setVisibleWhen("#{ showSyncDownload == true }");
        btnRefresh2.setAccelerator("ctrl R");
        btnRefresh2.setAutoRefresh(false);
        btnRefresh2.setFocusable(false);
        btnRefresh2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRefresh2.setIconResource("images/toolbars/syncpull.png");
        btnRefresh2.setImmediate(true);
        btnRefresh2.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnRefresh2.setToolTipText("Sync Download");
        btnRefresh2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRefresh2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefresh2ActionPerformed(evt);
            }
        });
        jToolBar1.add(btnRefresh2);

        xPanel2.add(jToolBar1);

        xActionBar1.setFormName("formName");
        xActionBar1.setName("listActions"); // NOI18N
        xActionBar1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0));
        xPanel2.add(xActionBar1);

        xLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        xLabel2.setName("filterText"); // NOI18N
        xLabel2.setCellPadding(new java.awt.Insets(5, 0, 0, 5));
        xLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        xLabel2.setForeground(new java.awt.Color(204, 0, 0));
        xLabel2.setText("xLabel2");
        xPanel2.add(xLabel2);

        jPanel2.add(xPanel2, java.awt.BorderLayout.WEST);

        xSubFormPanel1.setHandler("queryForm");

        javax.swing.GroupLayout xSubFormPanel1Layout = new javax.swing.GroupLayout(xSubFormPanel1);
        xSubFormPanel1.setLayout(xSubFormPanel1Layout);
        xSubFormPanel1Layout.setHorizontalGroup(
            xSubFormPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 264, Short.MAX_VALUE)
        );
        xSubFormPanel1Layout.setVerticalGroup(
            xSubFormPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        jPanel2.add(xSubFormPanel1, java.awt.BorderLayout.CENTER);

        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        jPanel3.setLayout(new com.rameses.rcp.control.layout.CenterLayout());

        xPanel1.setLayout(new java.awt.BorderLayout());

        xActionTextField1.setActionName("search");
        xActionTextField1.setName("searchText"); // NOI18N
        xActionTextField1.setVisibleWhen("#{allowSearch == true}");
        xActionTextField1.setFocusKeyStroke("F3");
        xActionTextField1.setMaxLength(50);
        xActionTextField1.setPreferredSize(new java.awt.Dimension(180, 20));
        xPanel1.add(xActionTextField1, java.awt.BorderLayout.WEST);

        jToolBar2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);

        xButton2.setCaption("");
        xButton2.setName("showInfo"); // NOI18N
        xButton2.setAutoRefresh(false);
        xButton2.setBackground(new java.awt.Color(255, 255, 255));
        xButton2.setFocusable(false);
        xButton2.setIconResource("images/info.png");
        xButton2.setImmediate(true);
        jToolBar2.add(xButton2);

        xButton1.setCaption("\\");
            xButton1.setName("showHelp"); // NOI18N
            xButton1.setAutoRefresh(false);
            xButton1.setBackground(new java.awt.Color(255, 255, 255));
            xButton1.setFocusable(false);
            xButton1.setIconResource("images/help.png");
            xButton1.setImmediate(true);
            jToolBar2.add(xButton1);

            xPanel1.add(jToolBar2, java.awt.BorderLayout.EAST);

            jPanel3.add(xPanel1);

            jPanel2.add(jPanel3, java.awt.BorderLayout.EAST);

            jPanel1.add(jPanel2, java.awt.BorderLayout.SOUTH);

            add(jPanel1, java.awt.BorderLayout.PAGE_START);

            jPanel6.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 5, 5), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204))));
            jPanel6.setLayout(new java.awt.BorderLayout());
            jPanel6.add(listPanel1, java.awt.BorderLayout.CENTER);

            add(jPanel6, java.awt.BorderLayout.CENTER);
        }// </editor-fold>//GEN-END:initComponents

    private void btnRefresh2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefresh2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRefresh2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.control.XButton btnCancel1;
    private com.rameses.rcp.control.XButton btnCreate;
    private com.rameses.rcp.control.XButton btnDelete;
    private com.rameses.rcp.control.XButton btnFilter;
    private com.rameses.rcp.control.XButton btnOpen;
    private com.rameses.rcp.control.XButton btnPrint;
    private com.rameses.rcp.control.XButton btnRefresh;
    private com.rameses.rcp.control.XButton btnRefresh1;
    private com.rameses.rcp.control.XButton btnRefresh2;
    private com.rameses.rcp.control.XButton btnSelectColumn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private com.rameses.seti2.components.ListPanel listPanel1;
    private com.rameses.rcp.control.XActionBar xActionBar1;
    private com.rameses.rcp.control.XActionTextField xActionTextField1;
    private com.rameses.rcp.control.XButton xButton1;
    private com.rameses.rcp.control.XButton xButton2;
    private com.rameses.rcp.control.XLabel xLabel1;
    private com.rameses.rcp.control.XLabel xLabel2;
    private com.rameses.rcp.control.XPanel xPanel1;
    private com.rameses.rcp.control.XPanel xPanel2;
    private com.rameses.rcp.control.XSubFormPanel xSubFormPanel1;
    // End of variables declaration//GEN-END:variables
}
