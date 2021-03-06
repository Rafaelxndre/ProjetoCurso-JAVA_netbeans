/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema_de_gerenciamento;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Rafael
 */
public class EditarCurso extends javax.swing.JFrame {

    /**
     * Creates new form EditarCurso
     */
    
    DefaultTableModel model;
    
    public EditarCurso() {
        initComponents();
        setRegistrosDaTabelaCurso();
    }
    
    public  void setRegistrosDaTabelaCurso()
    {
        try {
            Connection con = BDConexao.getConnection();
            PreparedStatement pst = con.prepareStatement("select  * from course");
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
            
            String codigoCurso = rs.getString("id");
            String nomedoCurso = rs.getString("cname");
            String precoDoCurso = rs.getString("cost");
            Object[] obj ={codigoCurso,nomedoCurso,precoDoCurso};
            model = (DefaultTableModel)tbl_cursotabela.getModel();
            model.addRow(obj);
            
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }       
        
    }
    
    public void limparTabela()
    {
        DefaultTableModel model = (DefaultTableModel)tbl_cursotabela.getModel();
        model.setRowCount(0);
    }
    
    public void addCurso(int id,String cname, double cost)
    {
        try {
            Connection con = BDConexao.getConnection();
            PreparedStatement pst = con.prepareStatement("insert into course values(?,?,?)");
            pst.setInt(1, id);
            pst.setString(2, cname);
            pst.setDouble(3, cost);
            
            int rowCount = pst.executeUpdate();
            if(rowCount == 1){
                JOptionPane.showMessageDialog(this, "Curso adicionado com sucesso!");
                limparTabela();
                setRegistrosDaTabelaCurso();
            }else{
                JOptionPane.showMessageDialog(this, "Erro ao adicionar o curso!");
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar o curso!");
            e.printStackTrace();
        }
    }
    
    public void atualizar(int id,String cname, double cost)
    {
        try {
            Connection con = BDConexao.getConnection();
            PreparedStatement pst = con.prepareStatement("update course set cname = ?,cost = ? where id = ?");
            
            pst.setString(1, cname);
            pst.setDouble(2, cost);
            pst.setInt(3, id);
            
            int rowCount = pst.executeUpdate();
            if(rowCount == 1){
                JOptionPane.showMessageDialog(this, "Curso atualizado com sucesso!");
                limparTabela();
                setRegistrosDaTabelaCurso();
            }else{
                JOptionPane.showMessageDialog(this, "Erro ao atualizar o curso!");
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar o curso!");
            e.printStackTrace();
        }
    }
    public void deletar(int id)
    {
        try {
            Connection con = BDConexao.getConnection();
            PreparedStatement pst = con.prepareStatement("delete from course where id = ?");
            
            pst.setInt(1, id);
            
            int rowCount = pst.executeUpdate();
            if(rowCount == 1){
                JOptionPane.showMessageDialog(this, "Curso excluido com sucesso!");
                limparTabela();
                setRegistrosDaTabelaCurso();
            }else{
                JOptionPane.showMessageDialog(this, "Erro ao excluir o curso!");
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir o curso!");
            e.printStackTrace();
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelsidebar = new javax.swing.JPanel();
        panelPesquisarRegistro = new javax.swing.JPanel();
        btnpesquisarRegistro = new javax.swing.JLabel();
        panelEditarCurso = new javax.swing.JPanel();
        btnEditarCurso = new javax.swing.JLabel();
        panelListadeCursos = new javax.swing.JPanel();
        btnListadeCursos = new javax.swing.JLabel();
        panelTodosRegistros = new javax.swing.JPanel();
        btnTodososRegistros = new javax.swing.JLabel();
        panelVoltar = new javax.swing.JPanel();
        btnVoltar = new javax.swing.JLabel();
        panelInicio6 = new javax.swing.JPanel();
        btnInicio6 = new javax.swing.JLabel();
        panelSair = new javax.swing.JPanel();
        btnSair = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_cursotabela = new javax.swing.JTable();
        txt_cursoCodigo = new javax.swing.JTextField();
        txt_cursoNome = new javax.swing.JTextField();
        txt_cursoPreco = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelsidebar.setBackground(new java.awt.Color(0, 102, 102));
        panelsidebar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelPesquisarRegistro.setBackground(new java.awt.Color(0, 102, 102));
        panelPesquisarRegistro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelPesquisarRegistro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnpesquisarRegistro.setFont(new java.awt.Font("Sylfaen", 0, 30)); // NOI18N
        btnpesquisarRegistro.setForeground(new java.awt.Color(255, 255, 255));
        btnpesquisarRegistro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistema_de_gerenciamento/imagens/search2.png"))); // NOI18N
        btnpesquisarRegistro.setText("Pesquisar Registro");
        btnpesquisarRegistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnpesquisarRegistroMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnpesquisarRegistroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnpesquisarRegistroMouseExited(evt);
            }
        });
        panelPesquisarRegistro.add(btnpesquisarRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 360, 70));

        panelsidebar.add(panelPesquisarRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 380, 70));

        panelEditarCurso.setBackground(new java.awt.Color(0, 102, 102));
        panelEditarCurso.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelEditarCurso.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEditarCurso.setFont(new java.awt.Font("Sylfaen", 0, 30)); // NOI18N
        btnEditarCurso.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarCurso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistema_de_gerenciamento/imagens/edit2.png"))); // NOI18N
        btnEditarCurso.setText("Editar curso");
        btnEditarCurso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditarCursoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEditarCursoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEditarCursoMouseExited(evt);
            }
        });
        panelEditarCurso.add(btnEditarCurso, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 360, 70));

        panelsidebar.add(panelEditarCurso, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 380, 70));

        panelListadeCursos.setBackground(new java.awt.Color(0, 102, 102));
        panelListadeCursos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelListadeCursos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnListadeCursos.setFont(new java.awt.Font("Sylfaen", 0, 30)); // NOI18N
        btnListadeCursos.setForeground(new java.awt.Color(255, 255, 255));
        btnListadeCursos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistema_de_gerenciamento/imagens/list_1.png"))); // NOI18N
        btnListadeCursos.setText("Lista de Cursos");
        btnListadeCursos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnListadeCursosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnListadeCursosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnListadeCursosMouseExited(evt);
            }
        });
        panelListadeCursos.add(btnListadeCursos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 360, 70));

        panelsidebar.add(panelListadeCursos, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 380, 70));

        panelTodosRegistros.setBackground(new java.awt.Color(0, 102, 102));
        panelTodosRegistros.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelTodosRegistros.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnTodososRegistros.setFont(new java.awt.Font("Sylfaen", 0, 30)); // NOI18N
        btnTodososRegistros.setForeground(new java.awt.Color(255, 255, 255));
        btnTodososRegistros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistema_de_gerenciamento/imagens/view all record.png"))); // NOI18N
        btnTodososRegistros.setText("Ver Todos os Registros");
        btnTodososRegistros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTodososRegistrosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnTodososRegistrosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTodososRegistrosMouseExited(evt);
            }
        });
        panelTodosRegistros.add(btnTodososRegistros, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 360, 70));

        panelsidebar.add(panelTodosRegistros, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 380, 70));

        panelVoltar.setBackground(new java.awt.Color(0, 102, 102));
        panelVoltar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelVoltar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnVoltar.setFont(new java.awt.Font("Sylfaen", 0, 30)); // NOI18N
        btnVoltar.setForeground(new java.awt.Color(255, 255, 255));
        btnVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistema_de_gerenciamento/imagens/left-arrow.png"))); // NOI18N
        btnVoltar.setText("Voltar");
        btnVoltar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVoltarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVoltarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVoltarMouseExited(evt);
            }
        });
        panelVoltar.add(btnVoltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 360, 70));

        panelsidebar.add(panelVoltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 520, 380, 70));

        panelInicio6.setBackground(new java.awt.Color(0, 102, 102));
        panelInicio6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelInicio6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnInicio6.setFont(new java.awt.Font("Sylfaen", 0, 30)); // NOI18N
        btnInicio6.setForeground(new java.awt.Color(255, 255, 255));
        btnInicio6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistema_de_gerenciamento/imagens/home.png"))); // NOI18N
        btnInicio6.setText("  Inicio");
        btnInicio6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInicio6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnInicio6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnInicio6MouseExited(evt);
            }
        });
        panelInicio6.add(btnInicio6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 360, 70));

        panelsidebar.add(panelInicio6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 380, 70));

        panelSair.setBackground(new java.awt.Color(0, 102, 102));
        panelSair.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelSair.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSair.setFont(new java.awt.Font("Sylfaen", 0, 30)); // NOI18N
        btnSair.setForeground(new java.awt.Color(255, 255, 255));
        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistema_de_gerenciamento/imagens/logout.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSairMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSairMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSairMouseExited(evt);
            }
        });
        panelSair.add(btnSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 360, 70));

        panelsidebar.add(panelSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 600, 380, 70));

        getContentPane().add(panelsidebar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 700));

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        tbl_cursotabela.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        tbl_cursotabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod. Curso", "Nome do Curso", "Pre??o do Curso"
            }
        ));
        tbl_cursotabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_cursotabelaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_cursotabela);

        txt_cursoCodigo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txt_cursoNome.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txt_cursoPreco.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nome do Curso :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Codigo Curso :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Pre??o do Curso :");

        jButton1.setBackground(new java.awt.Color(0, 102, 102));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("ATUALIZAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 102, 102));
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("ADICIONAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 102, 102));
        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("DELETAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistema_de_gerenciamento/imagens/back1.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("EDITAR DETALHES DO CURSO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_cursoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_cursoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_cursoPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGap(184, 184, 184)
                            .addComponent(jLabel5))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_cursoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cursoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cursoPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(78, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 880, 700));

        setSize(new java.awt.Dimension(1340, 737));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnpesquisarRegistroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnpesquisarRegistroMouseEntered
        // TODO add your handling code here:
        Color clr = new Color(0,153,153);
        panelPesquisarRegistro.setBackground(clr);
    }//GEN-LAST:event_btnpesquisarRegistroMouseEntered

    private void btnpesquisarRegistroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnpesquisarRegistroMouseExited
        Color clr = new Color(0,103,103);
        panelPesquisarRegistro.setBackground(clr);
    }//GEN-LAST:event_btnpesquisarRegistroMouseExited

    private void btnEditarCursoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarCursoMouseEntered
        Color clr = new Color(0,153,153);
        panelEditarCurso.setBackground(clr);
    }//GEN-LAST:event_btnEditarCursoMouseEntered

    private void btnEditarCursoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarCursoMouseExited
        Color clr = new Color(0,103,103);
        panelEditarCurso.setBackground(clr);
    }//GEN-LAST:event_btnEditarCursoMouseExited

    private void btnListadeCursosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnListadeCursosMouseEntered
        Color clr = new Color(0,153,153);
        panelListadeCursos.setBackground(clr);
    }//GEN-LAST:event_btnListadeCursosMouseEntered

    private void btnListadeCursosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnListadeCursosMouseExited
        Color clr = new Color(0,103,103);
        panelListadeCursos.setBackground(clr);
    }//GEN-LAST:event_btnListadeCursosMouseExited

    private void btnTodososRegistrosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTodososRegistrosMouseEntered
        Color clr = new Color(0,153,153);
        panelTodosRegistros.setBackground(clr);
    }//GEN-LAST:event_btnTodososRegistrosMouseEntered

    private void btnTodososRegistrosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTodososRegistrosMouseExited
        Color clr = new Color(0,103,103);
        panelTodosRegistros.setBackground(clr);
    }//GEN-LAST:event_btnTodososRegistrosMouseExited

    private void btnVoltarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVoltarMouseEntered
        Color clr = new Color(0,153,153);
        panelVoltar.setBackground(clr);
    }//GEN-LAST:event_btnVoltarMouseEntered

    private void btnVoltarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVoltarMouseExited
        Color clr = new Color(0,103,103);
        panelVoltar.setBackground(clr);
    }//GEN-LAST:event_btnVoltarMouseExited

    private void btnInicio6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicio6MouseEntered
        Color clr = new Color(0,153,153);
        panelInicio6.setBackground(clr);
    }//GEN-LAST:event_btnInicio6MouseEntered

    private void btnInicio6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicio6MouseExited
        Color clr = new Color(0,103,103);
        panelInicio6.setBackground(clr);
    }//GEN-LAST:event_btnInicio6MouseExited

    private void btnSairMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairMouseEntered
        Color clr = new Color(0,153,153);
        panelSair.setBackground(clr);
    }//GEN-LAST:event_btnSairMouseEntered

    private void btnSairMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairMouseExited
        Color clr = new Color(0,103,103);
        panelSair.setBackground(clr);
    }//GEN-LAST:event_btnSairMouseExited

    private void tbl_cursotabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_cursotabelaMouseClicked
        
        int rowNo = tbl_cursotabela.getSelectedRow();
        TableModel model = tbl_cursotabela.getModel();
        
        txt_cursoCodigo.setText(model.getValueAt(rowNo, 0).toString());
        txt_cursoNome.setText((String)model.getValueAt(rowNo, 1));
        txt_cursoPreco.setText(model.getValueAt(rowNo, 2).toString());
    }//GEN-LAST:event_tbl_cursotabelaMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        int id = Integer.parseInt(txt_cursoCodigo.getText());
        String nomecurso = txt_cursoNome.getText();
        double preco = Double.parseDouble(txt_cursoPreco.getText());
        
        addCurso(id, nomecurso, preco);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int id = Integer.parseInt(txt_cursoCodigo.getText());
        String nomecurso = txt_cursoNome.getText();
        double preco = Double.parseDouble(txt_cursoPreco.getText());
        
        atualizar(id, nomecurso, preco);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int id = Integer.parseInt(txt_cursoCodigo.getText());
        
        deletar(id);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        Pagina_inicial pgInicial = new Pagina_inicial();
        pgInicial.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void btnInicio6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicio6MouseClicked
        Pagina_inicial pgInicial = new Pagina_inicial();
        pgInicial.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnInicio6MouseClicked

    private void btnpesquisarRegistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnpesquisarRegistroMouseClicked
        PesquisarRegistros pqsRegistro = new PesquisarRegistros();
        pqsRegistro.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnpesquisarRegistroMouseClicked

    private void btnTodososRegistrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTodososRegistrosMouseClicked
        VerRegistros verregistros = new VerRegistros();
        verregistros.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTodososRegistrosMouseClicked

    private void btnListadeCursosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnListadeCursosMouseClicked
        EditarCurso eCurso = new EditarCurso();
        eCurso.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnListadeCursosMouseClicked

    private void btnEditarCursoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarCursoMouseClicked
        EditarCurso eCurso = new EditarCurso();
        eCurso.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnEditarCursoMouseClicked

    private void btnVoltarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVoltarMouseClicked
        Pagina_inicial pgInicial = new Pagina_inicial();
        pgInicial.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVoltarMouseClicked

    private void btnSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairMouseClicked
        System.exit(0);
    }//GEN-LAST:event_btnSairMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EditarCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditarCurso().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnEditarCurso;
    private javax.swing.JLabel btnInicio6;
    private javax.swing.JLabel btnListadeCursos;
    private javax.swing.JLabel btnSair;
    private javax.swing.JLabel btnTodososRegistros;
    private javax.swing.JLabel btnVoltar;
    private javax.swing.JLabel btnpesquisarRegistro;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel panelEditarCurso;
    private javax.swing.JPanel panelInicio6;
    private javax.swing.JPanel panelListadeCursos;
    private javax.swing.JPanel panelPesquisarRegistro;
    private javax.swing.JPanel panelSair;
    private javax.swing.JPanel panelTodosRegistros;
    private javax.swing.JPanel panelVoltar;
    private javax.swing.JPanel panelsidebar;
    private javax.swing.JTable tbl_cursotabela;
    private javax.swing.JTextField txt_cursoCodigo;
    private javax.swing.JTextField txt_cursoNome;
    private javax.swing.JTextField txt_cursoPreco;
    // End of variables declaration//GEN-END:variables
}
