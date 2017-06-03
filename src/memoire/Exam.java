package memoire;

import java.awt.HeadlessException;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import net.proteanit.sql.DbUtils;
import org.apache.poi.hslf.record.Record;

public class Exam extends javax.swing.JFrame {

    Connection con;
    ResultSet Result;
    PreparedStatement pred;
    PreparedStatement predu;
    private ExamModel exam;
    ButtonGroup GroupB = new ButtonGroup();

    public Exam() {
        initComponents();
        con = ConnectionDB.OpenConnection();
        setLocationRelativeTo(this);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        chargeExamList();

        GroupB = new ButtonGroup();
        GroupB.add(AddEXM);
        GroupB.add(EditEXM);
        GroupB.add(ArchiveEXM);

        TableEXM.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (!lse.getValueIsAdjusting() && TableEXM.getSelectedRow() >= 0) {
                    exam = new ExamModel(TableEXM.getSelectedRow(), TableEXM.getModel());
                    if (EditEXM.isSelected()) {
                        try {
                            DateEXM.setDate(exam.Date);
                        } catch (PropertyVetoException ex) {
                            Logger.getLogger(Exam.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        NameMod.setText(exam.Name);
                        nbrPar.setSelectedIndex(exam.PartsCount - 2);
                       // nbrPar.setSelectedIndex(exam.PartsCount - 1);
                    }
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sqlDateModel1 = new org.jdatepicker.impl.SqlDateModel();
        sqlDateModel2 = new org.jdatepicker.impl.SqlDateModel();
        datePicker1 = new com.michaelbaranov.microba.calendar.DatePicker();
        UpDate = new javax.swing.JPanel();
        NameMod = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        nbrPar = new javax.swing.JComboBox<>();
        NbrDeg = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        goedit = new javax.swing.JButton();
        Add = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        NameExam = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableEXM = new javax.swing.JTable();
        Update = new javax.swing.JButton();
        DateEXM = new com.michaelbaranov.microba.calendar.DatePicker();
        jPanel1 = new javax.swing.JPanel();
        AddEXM = new javax.swing.JRadioButton();
        EditEXM = new javax.swing.JRadioButton();
        ArchiveEXM = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        UpDate.setBackground(new java.awt.Color(128, 83, 162));
        UpDate.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Module", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Vivaldi", 1, 36), new java.awt.Color(94, 215, 242))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(47, 209, 25));
        jLabel3.setText("NumberDegree :");

        nbrPar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2", "3", "4" }));
        nbrPar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                nbrParItemStateChanged(evt);
            }
        });
        nbrPar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nbrParActionPerformed(evt);
            }
        });

        NbrDeg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2", "3", "4" }));
        NbrDeg.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                NbrDegItemStateChanged(evt);
            }
        });
        NbrDeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NbrDegActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(47, 209, 25));
        jLabel2.setText("NumberPart :");

        jLabel1.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(47, 209, 25));
        jLabel1.setText("Name Module :");

        goedit.setBackground(new java.awt.Color(213, 199, 220));
        goedit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        goedit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoire/1495511725_accessories-text-editor.png"))); // NOI18N
        goedit.setText("Editor Page  ");
        goedit.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        goedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goeditActionPerformed(evt);
            }
        });

        Add.setBackground(new java.awt.Color(213, 199, 220));
        Add.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoire/1495336616_plus.png"))); // NOI18N
        Add.setText("Add  ");
        Add.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 204, 51));
        jLabel4.setText("Date of Exam :");

        jLabel6.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 204, 51));
        jLabel6.setText("Name of Exam :");

        TableEXM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Exam ID", "Name Of Exam", "Date Of Exam Performance", "Name Of Module", "Number of part", "Number of degree"
            }
        ));
        TableEXM.setRowHeight(20);
        jScrollPane2.setViewportView(TableEXM);

        Update.setBackground(new java.awt.Color(213, 199, 220));
        Update.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoire/1495328076_InterfaceExpendet-01.png"))); // NOI18N
        Update.setText("Update");
        Update.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateActionPerformed(evt);
            }
        });

        DateEXM.setKeepTime(false);

        jPanel1.setBackground(new java.awt.Color(128, 83, 162));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Selections", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Trebuchet MS", 1, 24), new java.awt.Color(153, 255, 0))); // NOI18N

        AddEXM.setBackground(new java.awt.Color(128, 83, 162));
        AddEXM.setFont(new java.awt.Font("Urdu Typesetting", 1, 14)); // NOI18N
        AddEXM.setForeground(new java.awt.Color(255, 255, 51));
        AddEXM.setText("Add Exam");
        AddEXM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddEXMActionPerformed(evt);
            }
        });

        EditEXM.setBackground(new java.awt.Color(128, 83, 162));
        EditEXM.setFont(new java.awt.Font("Urdu Typesetting", 1, 14)); // NOI18N
        EditEXM.setForeground(new java.awt.Color(255, 255, 51));
        EditEXM.setText("Edit Exam");
        EditEXM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditEXMActionPerformed(evt);
            }
        });

        ArchiveEXM.setBackground(new java.awt.Color(128, 83, 162));
        ArchiveEXM.setFont(new java.awt.Font("Urdu Typesetting", 1, 14)); // NOI18N
        ArchiveEXM.setForeground(new java.awt.Color(255, 255, 51));
        ArchiveEXM.setText("Archive Exams");
        ArchiveEXM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ArchiveEXMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(EditEXM)
                    .addComponent(AddEXM)
                    .addComponent(ArchiveEXM))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(AddEXM)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EditEXM)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ArchiveEXM))
        );

        javax.swing.GroupLayout UpDateLayout = new javax.swing.GroupLayout(UpDate);
        UpDate.setLayout(UpDateLayout);
        UpDateLayout.setHorizontalGroup(
            UpDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UpDateLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(UpDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UpDateLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(UpDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(UpDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(NameMod)
                            .addComponent(NameExam)
                            .addComponent(DateEXM, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(UpDateLayout.createSequentialGroup()
                        .addGroup(UpDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(UpDateLayout.createSequentialGroup()
                                .addComponent(Update, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(Add, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addComponent(goedit, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(UpDateLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, UpDateLayout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(nbrPar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(NbrDeg, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(0, 43, Short.MAX_VALUE))))
        );
        UpDateLayout.setVerticalGroup(
            UpDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UpDateLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(UpDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UpDateLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UpDateLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(NameMod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(DateEXM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UpDateLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(UpDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(NameExam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(UpDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nbrPar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(NbrDeg, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(UpDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(goedit, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Add, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Update, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(UpDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(UpDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
        // TODO add your handling code here:
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            java.sql.Date examSqlDate = new java.sql.Date(DateEXM.getDate().getTime());
            java.sql.Date nowSqlDate = java.sql.Date.valueOf(LocalDate.now());

            String ModuleName = NameMod.getText();
            if (ModuleName.length() == 0) {
                JOptionPane.showMessageDialog(this, "Module Name is Empty");
                return;
            }
            if (examSqlDate.before(nowSqlDate)) {
                JOptionPane.showMessageDialog(this, "Invalid Date OR Field Empty");
                return;
            }
            if (NameExam.getText().length() == 0) {
                JOptionPane.showMessageDialog(this, "Exam Name is Empty");
                return;
            }
            if (AddEXM.isSelected() == false) {
                JOptionPane.showMessageDialog(this, "Must be selected Radio Add Exam");
                return;
            }

            String qeury = "select Count(*) from [DB_MEMIOR].[dbo].[Exam] where   NameEXM = ? AND IDUser = ?  ";
            pred = con.prepareStatement(qeury);
            pred.setString(1, NameExam.getText());
            pred.setInt(2, Login.id_user);
            Result = pred.executeQuery();
            if (Result.next()) {
                if (Result.getInt(1) == 0) {
                    String query = "INSERT INTO [DB_MEMIOR].[dbo].[Exam] (NameEXM,NbrPart,NbrDeg,DatePro,NameModule,IDUser)values (?,?,?,?,?,?)";
                    pred = con.prepareStatement(query);
                    pred.setString(1, NameExam.getText());
                    pred.setInt(2, nbrPar.getSelectedIndex() + 2);
                    pred.setInt(3, NbrDeg.getSelectedIndex() + 2);
                    pred.setDate(4, new java.sql.Date(DateEXM.getDate().getTime()));
                    pred.setString(5, NameMod.getText());
                    pred.setInt(6, Login.id_user);
                    pred.execute();
                    chargeExamList();
                    JOptionPane.showMessageDialog(null, "The INSERT process Was SuccessFul");

                } else {
                    JOptionPane.showMessageDialog(null, "This Exam Already Exists");
                }
                /* Exercises exo = new Exercises();
            exo.setVisible(true);
            this.hide();*/
            } else {
                JOptionPane.showMessageDialog(null, "This date is not valid");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }//GEN-LAST:event_AddActionPerformed

    private void goeditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goeditActionPerformed

        if (TableEXM.getSelectionModel().isSelectionEmpty() == false) {
            ExamModel exam = new ExamModel(TableEXM.getSelectedRow(), TableEXM.getModel());
            //  System.out.println(NBRPART);
            Exercises exo = new Exercises(exam);

            exo.DisableEnable(false);
            //ConnectDialog d = new ConnectDialog(this,Dialog.ModalityType.DOCUMENT_MODAL);
            exo.setVisible(true);
            this.setVisible(false);

        } else {
            JOptionPane.showMessageDialog(null, "Please Select Exam");
        }
    }//GEN-LAST:event_goeditActionPerformed

    private void NbrDegItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_NbrDegItemStateChanged
        //  nbrPar.setSelectedIndex(NbrDeg.getSelectedIndex());
    }//GEN-LAST:event_NbrDegItemStateChanged

    private void nbrParItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_nbrParItemStateChanged
        // NbrDeg.setSelectedIndex(nbrPar.getSelectedIndex());
    }//GEN-LAST:event_nbrParItemStateChanged

    private void nbrParActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nbrParActionPerformed
        NbrDeg.setSelectedIndex(nbrPar.getSelectedIndex());
    }//GEN-LAST:event_nbrParActionPerformed

    private void NbrDegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NbrDegActionPerformed
        nbrPar.setSelectedIndex(NbrDeg.getSelectedIndex());
    }//GEN-LAST:event_NbrDegActionPerformed

    private void EditEXMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditEXMActionPerformed
        DateEXM.setEnabled(true);
        NameMod.setEnabled(true);
        nbrPar.setEnabled(true);
        NbrDeg.setEnabled(true);
        Add.setEnabled(false);
        NameExam.setEnabled(false);
        Update.setEnabled(true);
        chargeExamList();
    }//GEN-LAST:event_EditEXMActionPerformed

    private void ArchiveEXMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ArchiveEXMActionPerformed
        DateEXM.setEnabled(false);
        NameMod.setEnabled(false);
        nbrPar.setEnabled(false);
        NbrDeg.setEnabled(false);
        Add.setEnabled(false);
        NameExam.setEnabled(false);
        Update.setEnabled(false);
        chargeExamList();

    }//GEN-LAST:event_ArchiveEXMActionPerformed

    private void AddEXMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddEXMActionPerformed
        DateEXM.setEnabled(true);
        NameMod.setEnabled(true);
        nbrPar.setEnabled(true);
        NbrDeg.setEnabled(true);
        Add.setEnabled(true);
        NameExam.setEnabled(true);
        Update.setEnabled(false);

        chargeExamList();
    }//GEN-LAST:event_AddEXMActionPerformed

    private void UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActionPerformed
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            java.sql.Date examSqlDate = new java.sql.Date(DateEXM.getDate().getTime());
            java.sql.Date nowSqlDate = java.sql.Date.valueOf(LocalDate.now());

            String ModuleName = NameMod.getText();
            if (TableEXM.getSelectionModel().isSelectionEmpty()) {
                JOptionPane.showMessageDialog(this, "Select Row the Exam in Table ");
                return;
            }

            if (ModuleName.length() == 0) {
                JOptionPane.showMessageDialog(this, "Module Name is Empty");
                return;
            }
            if (EditEXM.isSelected() == false) {
                JOptionPane.showMessageDialog(this, "Must be selected  Radio Edit Exam");
                return;
            }
            if (examSqlDate.before(nowSqlDate)) {
                JOptionPane.showMessageDialog(this, "Invalid Date OR Field Empty");
                return;
            }

            String query = "UPDATE [DB_MEMIOR].[dbo].[Exam]  SET  NbrPart = ?,NbrDeg = ?,DatePro = ?,NameModule  = ? where IDEXM =? and IDUser = ?";
            predu = con.prepareStatement(query);
            predu.setInt(1, Integer.valueOf(nbrPar.getSelectedIndex() + 2));
            predu.setInt(2, Integer.valueOf(NbrDeg.getSelectedIndex() + 2));
            predu.setDate(3, new java.sql.Date(DateEXM.getDate().getTime()));
            predu.setString(4, NameMod.getText());
            predu.setInt(5, exam.Id);
            predu.setInt(6, Login.id_user);
            predu.execute();
            chargeExamList();
            JOptionPane.showMessageDialog(null, "The UPDATE process Was SuccessFul");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }//GEN-LAST:event_UpdateActionPerformed
    public void chargeExamList() {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate localDate = LocalDate.now();
            java.sql.Date sqlDateT = java.sql.Date.valueOf(Date.valueOf(LocalDate.now()).toString());

            if (ArchiveEXM.isSelected()) {
                String qeury = "SELECT IDEXM AS \"N°\",NameEXM AS \"Name Of Exam\",DatePro AS \"Exam Date\",NameModule AS \"Module Name\",NbrPart AS \"N° Parts\",NbrDeg AS \"N° Degree\" from [DB_MEMIOR].[dbo].[Exam] where DatePro < ?  and IDUser = ? ";
                pred = con.prepareStatement(qeury);
                pred.setDate(1, sqlDateT);
                pred.setInt(2, Login.id_user);

            } else {
                String qeury = "SELECT IDEXM AS \"N°\",NameEXM AS \"Name Of Exam\",DatePro AS \"Exam Date\",NameModule AS \"Module Name\",NbrPart AS \"N° Parts\",NbrDeg AS \"N° Degree\" from [DB_MEMIOR].[dbo].[Exam] where DatePro > ? and IDUser = ?  ";
                pred = con.prepareStatement(qeury);
                pred.setDate(1, sqlDateT);
                pred.setInt(2, Login.id_user);
            }

            Result = pred.executeQuery();

            TableEXM.setModel(DbUtils.resultSetToTableModel(Result));
            pred.close();
            Result.close();
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public javax.swing.JButton getgoedit() {
        return goedit;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add;
    private javax.swing.JRadioButton AddEXM;
    private javax.swing.JRadioButton ArchiveEXM;
    private com.michaelbaranov.microba.calendar.DatePicker DateEXM;
    private javax.swing.JRadioButton EditEXM;
    private javax.swing.JTextField NameExam;
    private javax.swing.JTextField NameMod;
    private javax.swing.JComboBox<String> NbrDeg;
    private javax.swing.JTable TableEXM;
    private javax.swing.JPanel UpDate;
    private javax.swing.JButton Update;
    private com.michaelbaranov.microba.calendar.DatePicker datePicker1;
    private javax.swing.JButton goedit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> nbrPar;
    private org.jdatepicker.impl.SqlDateModel sqlDateModel1;
    private org.jdatepicker.impl.SqlDateModel sqlDateModel2;
    // End of variables declaration//GEN-END:variables
}
