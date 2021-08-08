/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema_de_gerenciamento;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Rafael
 */
public class GerarRelatorio extends javax.swing.JFrame {

    /**
     * Creates new form GerarRelatorio
     */
    
    DefaultTableModel model;
    
    public GerarRelatorio() {
        initComponents();
        fillComboBox();
    }
    public void fillComboBox()
    {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/gestao_de_taxas","root","1234");
            PreparedStatement pst = con.prepareStatement("select cname from course");
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                    combo_cursosdisponiveis.addItem(rs.getString("cname"));                
            }
            
                    
        } catch (Exception e) {
            e.printStackTrace();
        }       
        
    }
    public  void setRegistrosDaTabelaCurso()
    {
        String cname = combo_cursosdisponiveis.getSelectedItem().toString();
        
        Float quantidadeTotal = 0.0f;
        
        try {
            Connection con = BDConexao.getConnection();
            PreparedStatement pst = con.prepareStatement("select  * from fees_details where course_name = ?");
            pst.setString(1, cname);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
            
            String numRecibo = rs.getString("reciept_no");
            String numLista = rs.getString("roll_no");
            String nomeEstudante = rs.getString("student_name");
            String curso = rs.getString("course_name");
            float quantidade = rs.getFloat("total_amount");
            String observacao = rs.getString("remark");
            
            quantidadeTotal = quantidadeTotal + quantidade;
            
            Object[] obj ={numRecibo,numLista,nomeEstudante,curso,quantidade,observacao};
            model = (DefaultTableModel)tbl_relatorio.getModel();
            model.addRow(obj);
            
            }
            
            lbl_cursoSelecionado.setText(cname);
            lbl_totalArrecadado.setText(quantidadeTotal.toString());
            lbl_totalempalavras.setText(ConversorDeNumerosParaPalavras.convert(quantidadeTotal.intValue()));
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }       
        
    }
    public void limparTabela()
    {
        DefaultTableModel model = (DefaultTableModel)tbl_relatorio.getModel();
        model.setRowCount(1);
    }
    
    public void exportarParaExcel()
    {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet ws = wb.createSheet();
        
        TreeMap<String,Object[]> map = new TreeMap<>();
        
        map.put("0", new Object[]{model.getColumnName(0),model.getColumnName(1),model.getColumnName(2),
        model.getColumnName(3),model.getColumnName(4),model.getColumnName(5)});
        
        for(int i = 1; i < model.getRowCount(); i++)
        {
             map.put(Integer.toString(i), new Object[]{model.getValueAt(i, 0),model.getValueAt(i, 1),model.getValueAt(i, 2),
             model.getValueAt(i, 3),model.getValueAt(i, 4),model.getValueAt(i, 5),});
        }
        
        Set<String> id = map.keySet();
        
        XSSFRow fRow;
        
        int rowId = 0;
        
        for(String key : id)
        {
            fRow = ws.createRow(rowId++);
            
            Object[] value = map.get(key);
            
            int cellId =0;
            
            for(Object object : value)
            {
                XSSFCell cell = fRow.createCell(cellId++);
                cell.setCellValue(object.toString());
            }
        }
        
        try (FileOutputStream fos = new FileOutputStream(new File(txt_caminhoDoArquivo.getText()))){
            
            wb.write(fos);
            JOptionPane.showMessageDialog(this, "Arquivo exportado com sucesso :"+txt_caminhoDoArquivo.getText());            
        } catch (Exception e) {
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
        jLabel1 = new javax.swing.JLabel();
        combo_cursosdisponiveis = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txt_caminhoDoArquivo = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_relatorio = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        lbl_totalArrecadado = new javax.swing.JLabel();
        lbl_totalempalavras = new javax.swing.JLabel();
        lbl_cursoSelecionado = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

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

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Selecionar Curso :");

        combo_cursosdisponiveis.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        combo_cursosdisponiveis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        jButton1.setBackground(new java.awt.Color(0, 103, 103));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Pesquisar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 103, 103));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Imprimir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 103, 103));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Mostrar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 103, 103));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Exportar para Excel");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        tbl_relatorio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "Num. Recibo", "Núm. da lista", "Nome do Estudante", "Curso", "Quantidade", "Observação"
            }
        ));
        jScrollPane1.setViewportView(tbl_relatorio);

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));

        lbl_totalArrecadado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_totalArrecadado.setForeground(new java.awt.Color(255, 255, 255));

        lbl_totalempalavras.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_totalempalavras.setForeground(new java.awt.Color(255, 255, 255));

        lbl_cursoSelecionado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_cursoSelecionado.setForeground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Selecionar Curso :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Quantidade Total Em Palavras");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Quantidade total arrecadada :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_totalempalavras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_totalArrecadado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_cursoSelecionado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jLabel5)
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_cursoSelecionado, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_totalArrecadado, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_totalempalavras, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 856, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(14, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(combo_cursosdisponiveis, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(txt_caminhoDoArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(75, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(combo_cursosdisponiveis, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_caminhoDoArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 880, 700));

        setSize(new java.awt.Dimension(1340, 739));
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

    private void btnEditarCursoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarCursoMouseClicked
        EditarCurso edtCurso = new EditarCurso();
        edtCurso.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnEditarCursoMouseClicked

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

    private void btnTodososRegistrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTodososRegistrosMouseClicked
        VerRegistros verregistros = new VerRegistros();
        verregistros.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTodososRegistrosMouseClicked

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

    private void btnInicio6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicio6MouseClicked
        Pagina_inicial pgInicial = new Pagina_inicial();
        pgInicial.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnInicio6MouseClicked

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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        limparTabela();
        setRegistrosDaTabelaCurso();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      
        PrinterJob job = PrinterJob.getPrinterJob();
            job.setJobName("RELATORIO VENDAS");
            
            
            MessageFormat header = new MessageFormat("Relatorios das vendas! ");
            MessageFormat footer=new MessageFormat("page{0,number,integer}");
        try {
            tbl_relatorio.print(JTable.PrintMode.FIT_WIDTH, header, footer);
            
        } catch (Exception e) {
            e.getMessage();
        }           
            
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(this);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        String date = dateFormat.format(new Date());
        
        try {
            File f = fileChooser.getSelectedFile();
            String path = f.getAbsolutePath();
            
            path = path+"_"+date+".xlsx"; 
            txt_caminhoDoArquivo.setText(path);
        } catch (Exception e) {
           e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        exportarParaExcel();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnListadeCursosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnListadeCursosMouseClicked
        EditarCurso eCurso = new EditarCurso();
        eCurso.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnListadeCursosMouseClicked

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
            java.util.logging.Logger.getLogger(GerarRelatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerarRelatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerarRelatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerarRelatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GerarRelatorio().setVisible(true);
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
    private javax.swing.JComboBox<String> combo_cursosdisponiveis;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_cursoSelecionado;
    private javax.swing.JLabel lbl_totalArrecadado;
    private javax.swing.JLabel lbl_totalempalavras;
    private javax.swing.JPanel panelEditarCurso;
    private javax.swing.JPanel panelInicio6;
    private javax.swing.JPanel panelListadeCursos;
    private javax.swing.JPanel panelPesquisarRegistro;
    private javax.swing.JPanel panelSair;
    private javax.swing.JPanel panelTodosRegistros;
    private javax.swing.JPanel panelVoltar;
    private javax.swing.JPanel panelsidebar;
    private javax.swing.JTable tbl_relatorio;
    private javax.swing.JTextField txt_caminhoDoArquivo;
    // End of variables declaration//GEN-END:variables
}
