/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema_de_gerenciamento;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 *
 * @author Rafael
 */
public class AtualizarTaxasDetalhes extends JFrame {
    
    

    /**
     * Creates new form AdicionarTaxas
     */
    public AtualizarTaxasDetalhes() {
        initComponents();
        displayDinheiroPrimeiro();
        fillComboBox();
        
        int NaoObterRecibo = getNaoObterRecibo();
        txt_numeroRecibo.setText(Integer.toString(NaoObterRecibo));
        
        setRegistros();
    }
    
    public void displayDinheiroPrimeiro()// mostrar o dinheiro primeiro
    {
        lbl_numerodd.setVisible(false);
        lbl_numeroCheque.setVisible(false);
        lbl_nomeBanco.setVisible(false);
        
        txt_ddNumero.setVisible(false);
        txt_chequenumero.setVisible(false);
        txt_nomeBanco.setVisible(false);
    }
    
    public boolean validation()
    {
        if (txt_reciboDe.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Por favor entre com seu nome de usuario!");
            return false;
        }
        if (txt_quantidade.getText().equals("") )
        {
            JOptionPane.showMessageDialog(this, "Por favor entre com a quantia (*EM NUMEROS*)");
            return false;
        }
        if (combo_mododePagamanento.getSelectedItem().toString().equalsIgnoreCase("Cheque"))
        {
            if(txt_chequenumero.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this, "Por favor entre com numero de cheque!");
                return false;
            }
            if(txt_nomeBanco.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this, "Por favor entre com nome do banco!");
                return false;
            }
        }
        if (combo_mododePagamanento.getSelectedItem().toString().equalsIgnoreCase("DD"))
        {
            if(txt_ddNumero.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this, "Por favor entre com numero DD!");
                return false;
            }
            if(txt_nomeBanco.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this, "Por favor entre com nome do banco!");
                return false;
            }        
        }
        if (combo_mododePagamanento.getSelectedItem().toString().equalsIgnoreCase("Cartão"))
        {
            if(txt_nomeBanco.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this, "Por favor entre com nome do banco!");
                return false;
            }
        }
       return true;       
    }
    
    public void fillComboBox()
    {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/gestao_de_taxas","root","1234");
            PreparedStatement pst = con.prepareStatement("select cname from course");
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                    combo_curso.addItem(rs.getString("cname"));                
            }
            
                    
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
    public int getNaoObterRecibo(){
    
        int naoObterRecibo = 0;
        try {
            Connection con = BDConexao.getConnection();
            PreparedStatement pst = con.prepareStatement("select max(reciept_no) from fees_details");
            ResultSet rs = pst.executeQuery();
            
            if (rs.next() == true) {
                naoObterRecibo = rs.getInt(1);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return naoObterRecibo+1;
        
    }
    
    public String atualizarData(){
        
        String status = "";
    
        int recieptNo = Integer.parseInt(txt_numeroRecibo.getText());
        String studentName = txt_reciboDe.getText();
        String rollNo = txt_numeroCliente.getText();
        String paymentMode = combo_mododePagamanento.getSelectedItem().toString();
        String chequeNo = txt_chequenumero.getText();
        String bankName = txt_nomeBanco.getText();
        String ddNo = txt_ddNumero.getText();
        String courseName = txt_nomeCurso.getText();
        String gstin = txt_numeroGST.getText();
        float totalAmount = Float.parseFloat(txt_total.getText());
        float initialAmount = Float.parseFloat(txt_quantidade.getText());        
        float cgst = Float.parseFloat(txt_CGST.getText());
        float sgst = Float.parseFloat(txt_SGST.getText());
        String totalInWords = txt_totalemDinheiro.getText();
        String remark = txt_observacao.getText();
        int year1 = Integer.parseInt(txt_primeiroAno.getText());
        int year2 = Integer.parseInt(txt_segundoAno.getText());
        
        
        try {
            Connection con = BDConexao.getConnection();
            PreparedStatement pst = con.prepareStatement("update fees_details set student_name = ?, roll_no = ?,"
            + "payment_mode = ?,cheque_no = ?, bank_name =?, dd_no = ?,course_name =?, gstin = ?,total_amount = ?,"
            + "amount = ?,cgst =?,sgst = ?,total_in_words = ?,remark = ?,year1 = ?,year2=? where reciept_no=?");
            
           
            pst.setString(1, studentName);
            pst.setString(2, rollNo);
            pst.setString(3, paymentMode);
            pst.setString(4, chequeNo);
            pst.setString(5, bankName);
            pst.setString(6, ddNo);
            pst.setString(7, courseName);            
            pst.setString(8, gstin);            
            pst.setFloat(9, totalAmount );            
            pst.setFloat(10, initialAmount);
            pst.setFloat(11, cgst);
            pst.setFloat(12, sgst);
            pst.setString(13, totalInWords);
            pst.setString(14, remark);
            pst.setInt(15, year1);
            pst.setInt(16, year2);
            pst.setInt(17, recieptNo);
            
            int rowCount = pst.executeUpdate();
            if(rowCount == 1){
                status = "sucesso";
            }else{
                status = "erro!";
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            
            
        }
        return status;
    }
    public void setRegistros(){
        
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/gestao_de_taxas","root","1234");
            PreparedStatement pst = con.prepareStatement("select * from fees_details order by reciept_no desc fetch first 1 rows only");
            ResultSet rs = pst.executeQuery();
            rs.next();
            txt_numeroRecibo.setText(rs.getString("reciept_no"));
            combo_mododePagamanento.setSelectedItem(rs.getString("payment_mode"));
            txt_chequenumero.setText(rs.getString("cheque_no"));
            txt_ddNumero.setText(rs.getString("dd_no"));
            txt_nomeBanco.setText(rs.getString("bank_name"));
            txt_reciboDe.setText(rs.getString("student_name"));
            txt_primeiroAno.setText(rs.getString("year1"));
            txt_segundoAno.setText(rs.getString("year2"));
            combo_curso.setSelectedItem(rs.getString("course_name"));
            txt_numeroCliente.setText(rs.getString("roll_no"));
            txt_quantidade.setText(rs.getString("amount"));
            txt_SGST.setText(rs.getString("sgst"));
            txt_CGST.setText(rs.getString("cgst"));
            txt_total.setText(rs.getString("total_amount"));
            txt_totalemDinheiro.setText(rs.getString("total_in_words"));
            txt_observacao.setText(rs.getString("remark"));
            
        } catch (Exception e) {
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
        panelParent = new javax.swing.JPanel();
        lbl_numeroCheque = new javax.swing.JLabel();
        lbl_numerodd = new javax.swing.JLabel();
        lbl_nomeBanco = new javax.swing.JLabel();
        txt_numeroGST = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_ddNumero = new javax.swing.JTextField();
        painelFilho = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_numeroCliente = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_primeiroAno = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        combo_curso = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txt_total = new javax.swing.JTextField();
        txt_totalemDinheiro = new javax.swing.JTextField();
        txt_quantidade = new javax.swing.JTextField();
        txt_CGST = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        txt_SGST = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txt_nomeCurso = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_observacao = new javax.swing.JTextArea();
        jLabel20 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        btn_imprimir = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txt_reciboDe = new javax.swing.JTextField();
        txt_segundoAno = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_numeroRecibo = new javax.swing.JTextField();
        combo_mododePagamanento = new javax.swing.JComboBox<>();
        txt_nomeBanco = new javax.swing.JTextField();
        txt_chequenumero = new javax.swing.JTextField();

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

        panelParent.setBackground(new java.awt.Color(0, 153, 153));
        panelParent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_numeroCheque.setBackground(new java.awt.Color(0, 0, 0));
        lbl_numeroCheque.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_numeroCheque.setForeground(new java.awt.Color(255, 255, 255));
        lbl_numeroCheque.setText("Cheque no:");
        panelParent.add(lbl_numeroCheque, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        lbl_numerodd.setBackground(new java.awt.Color(0, 0, 0));
        lbl_numerodd.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_numerodd.setForeground(new java.awt.Color(255, 255, 255));
        lbl_numerodd.setText("DD no:");
        panelParent.add(lbl_numerodd, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        lbl_nomeBanco.setBackground(new java.awt.Color(0, 0, 0));
        lbl_nomeBanco.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_nomeBanco.setForeground(new java.awt.Color(255, 255, 255));
        lbl_nomeBanco.setText("Nome do Banco:");
        panelParent.add(lbl_nomeBanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        txt_numeroGST.setBackground(new java.awt.Color(0, 0, 0));
        txt_numeroGST.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_numeroGST.setForeground(new java.awt.Color(255, 255, 255));
        txt_numeroGST.setText("22HVSJH55");
        panelParent.add(txt_numeroGST, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 100, -1, -1));

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Modo de Pagamento:");
        panelParent.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Data:");
        panelParent.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, -1, -1));

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Numero de indentificação");
        panelParent.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 80, -1, -1));

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("GSTIN:");
        panelParent.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 50, -1, -1));

        txt_ddNumero.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        panelParent.add(txt_ddNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 180, -1));

        painelFilho.setBackground(new java.awt.Color(0, 153, 153));
        painelFilho.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Segue o pagamento na faculdade para o ano ");
        painelFilho.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("até");
        painelFilho.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 50, 30, -1));

        txt_numeroCliente.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        painelFilho.add(txt_numeroCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 90, 110, 30));

        jLabel12.setBackground(new java.awt.Color(0, 0, 0));
        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Numero de Cliente:");
        painelFilho.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, -1, 30));

        txt_primeiroAno.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        painelFilho.add(txt_primeiroAno, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, 90, -1));

        jLabel13.setBackground(new java.awt.Color(0, 0, 0));
        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Recibo do:");
        painelFilho.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        combo_curso.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        combo_curso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_cursoActionPerformed(evt);
            }
        });
        painelFilho.add(combo_curso, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 340, 30));

        jLabel14.setBackground(new java.awt.Color(0, 0, 0));
        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Quantidade:");
        painelFilho.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 140, -1, -1));
        painelFilho.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 460, 220, 20));
        painelFilho.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 760, 20));

        jLabel15.setBackground(new java.awt.Color(0, 0, 0));
        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("SGST 9 %");
        painelFilho.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 260, -1, -1));

        jLabel16.setBackground(new java.awt.Color(0, 0, 0));
        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Observação:");
        painelFilho.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 390, -1, -1));

        jLabel17.setBackground(new java.awt.Color(0, 0, 0));
        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Custos (taxas):");
        painelFilho.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 140, -1, -1));

        txt_total.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        painelFilho.add(txt_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 310, 210, -1));

        txt_totalemDinheiro.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        painelFilho.add(txt_totalemDinheiro, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 350, 320, -1));

        txt_quantidade.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txt_quantidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_quantidadeActionPerformed(evt);
            }
        });
        painelFilho.add(txt_quantidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 180, 210, -1));

        txt_CGST.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        painelFilho.add(txt_CGST, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 220, 210, -1));
        painelFilho.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 760, 20));

        txt_SGST.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        painelFilho.add(txt_SGST, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 260, 210, -1));

        jLabel18.setBackground(new java.awt.Color(0, 0, 0));
        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Numero de serie:");
        painelFilho.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));

        txt_nomeCurso.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        painelFilho.add(txt_nomeCurso, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, 270, -1));

        jLabel19.setBackground(new java.awt.Color(0, 0, 0));
        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Assinatura do Remetente:");
        painelFilho.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 460, -1, -1));

        txt_observacao.setColumns(20);
        txt_observacao.setRows(5);
        jScrollPane1.setViewportView(txt_observacao);

        painelFilho.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 390, 320, 120));

        jLabel20.setBackground(new java.awt.Color(0, 0, 0));
        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Total em palavras:");
        painelFilho.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, -1, -1));
        painelFilho.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 300, 220, 20));

        btn_imprimir.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        btn_imprimir.setText("Imprimir");
        btn_imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_imprimirActionPerformed(evt);
            }
        });
        painelFilho.add(btn_imprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 490, -1, -1));

        jLabel21.setBackground(new java.awt.Color(0, 0, 0));
        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Curso :");
        painelFilho.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        jLabel22.setBackground(new java.awt.Color(0, 0, 0));
        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("CGST 9 %");
        painelFilho.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 220, -1, -1));

        txt_reciboDe.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        painelFilho.add(txt_reciboDe, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 270, -1));

        txt_segundoAno.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        painelFilho.add(txt_segundoAno, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 50, 90, -1));

        panelParent.add(painelFilho, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 870, 530));

        jLabel10.setBackground(new java.awt.Color(0, 0, 0));
        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Numero de Recibo:");
        panelParent.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        txt_numeroRecibo.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        panelParent.add(txt_numeroRecibo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 180, -1));

        combo_mododePagamanento.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        combo_mododePagamanento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "Cheque", "Dinheiro", "Cartão" }));
        combo_mododePagamanento.setSelectedIndex(2);
        combo_mododePagamanento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_mododePagamanentoActionPerformed(evt);
            }
        });
        panelParent.add(combo_mododePagamanento, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 180, 30));

        txt_nomeBanco.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        panelParent.add(txt_nomeBanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 130, 180, -1));

        txt_chequenumero.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        panelParent.add(txt_chequenumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 180, -1));

        getContentPane().add(panelParent, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 870, 700));

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

    private void btnSairMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairMouseEntered
         Color clr = new Color(0,153,153);
        panelSair.setBackground(clr);
    }//GEN-LAST:event_btnSairMouseEntered

    private void btnSairMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairMouseExited
        Color clr = new Color(0,103,103);
        panelSair.setBackground(clr);
    }//GEN-LAST:event_btnSairMouseExited

    private void btnInicio6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicio6MouseEntered
         Color clr = new Color(0,153,153);
        panelInicio6.setBackground(clr);
    }//GEN-LAST:event_btnInicio6MouseEntered

    private void btnInicio6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicio6MouseExited
        Color clr = new Color(0,103,103);
        panelInicio6.setBackground(clr);
    }//GEN-LAST:event_btnInicio6MouseExited

    private void combo_mododePagamanentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_mododePagamanentoActionPerformed
       
        if (combo_mododePagamanento.getSelectedIndex() == 0 ) {
            
            lbl_numerodd.setVisible(true);
            txt_ddNumero.setVisible(true);
            lbl_numeroCheque.setVisible(false);
            txt_chequenumero.setVisible(false);
            lbl_nomeBanco.setVisible(true);
            txt_nomeBanco.setVisible(true);
            
        }
        if (combo_mododePagamanento.getSelectedIndex() == 1 ) {
            
            lbl_numerodd.setVisible(false);
            txt_ddNumero.setVisible(false);
            lbl_numeroCheque.setVisible(true);
            txt_chequenumero.setVisible(true);
            lbl_nomeBanco.setVisible(true);
            txt_nomeBanco.setVisible(true);
            
        }
        if (combo_mododePagamanento.getSelectedIndex() == 2 ) {
            
            lbl_numerodd.setVisible(false);
            txt_ddNumero.setVisible(false);
            lbl_numeroCheque.setVisible(false);
            txt_chequenumero.setVisible(false);
            lbl_nomeBanco.setVisible(false);
            txt_nomeBanco.setVisible(false);
            
        }
        if (combo_mododePagamanento.getSelectedItem().equals("Cartão")) {
            
            lbl_numerodd.setVisible(false);
            txt_ddNumero.setVisible(false);
            lbl_numeroCheque.setVisible(false);
            txt_chequenumero.setVisible(false);
            lbl_nomeBanco.setVisible(true);
            txt_nomeBanco.setVisible(true);
            
        }
    }//GEN-LAST:event_combo_mododePagamanentoActionPerformed

    private void btn_imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_imprimirActionPerformed
        // TODO add your handling code here:
        if (validation() == true )
        {
            String result = atualizarData();
            JOptionPane.showMessageDialog(this, " Registro atualizado com sucesso ");
            ImprimirRecibo imprimir = new ImprimirRecibo();
            imprimir.setVisible(true);
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(this, " Registro nao foi atualizado! ");
        }
    }//GEN-LAST:event_btn_imprimirActionPerformed

    private void txt_quantidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_quantidadeActionPerformed
        // TODO add your handling code here:
        Float amnt = Float.parseFloat(txt_quantidade.getText());
        
        Float cgst = (float) (amnt * 0.09);
        Float sgst = (float) (amnt * 0.09);
        
        txt_CGST.setText(cgst.toString());
        txt_SGST.setText(sgst.toString());
        
        float total = amnt + cgst + sgst;
        
        txt_total.setText(Float.toString(total));
        
        txt_totalemDinheiro.setText(ConversorDeNumerosParaPalavras.convert((int)total)+" a pagar!");
    }//GEN-LAST:event_txt_quantidadeActionPerformed

    private void combo_cursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_cursoActionPerformed
        // TODO add your handling code here:
        txt_nomeCurso.setText(combo_curso.getSelectedItem().toString());
    }//GEN-LAST:event_combo_cursoActionPerformed

    private void btnEditarCursoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarCursoMouseClicked
        EditarCurso edtCurso = new EditarCurso();
        edtCurso.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnEditarCursoMouseClicked

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
            java.util.logging.Logger.getLogger(AtualizarTaxasDetalhes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AtualizarTaxasDetalhes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AtualizarTaxasDetalhes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AtualizarTaxasDetalhes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AtualizarTaxasDetalhes().setVisible(true);
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
    private javax.swing.JButton btn_imprimir;
    private javax.swing.JLabel btnpesquisarRegistro;
    private javax.swing.JComboBox<String> combo_curso;
    private javax.swing.JComboBox<String> combo_mododePagamanento;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lbl_nomeBanco;
    private javax.swing.JLabel lbl_numeroCheque;
    private javax.swing.JLabel lbl_numerodd;
    private javax.swing.JPanel painelFilho;
    private javax.swing.JPanel panelEditarCurso;
    private javax.swing.JPanel panelInicio6;
    private javax.swing.JPanel panelListadeCursos;
    private javax.swing.JPanel panelParent;
    private javax.swing.JPanel panelPesquisarRegistro;
    private javax.swing.JPanel panelSair;
    private javax.swing.JPanel panelTodosRegistros;
    private javax.swing.JPanel panelVoltar;
    private javax.swing.JPanel panelsidebar;
    private javax.swing.JTextField txt_CGST;
    private javax.swing.JTextField txt_SGST;
    private javax.swing.JTextField txt_chequenumero;
    private javax.swing.JTextField txt_ddNumero;
    private javax.swing.JTextField txt_nomeBanco;
    private javax.swing.JTextField txt_nomeCurso;
    private javax.swing.JTextField txt_numeroCliente;
    private javax.swing.JLabel txt_numeroGST;
    private javax.swing.JTextField txt_numeroRecibo;
    private javax.swing.JTextArea txt_observacao;
    private javax.swing.JTextField txt_primeiroAno;
    private javax.swing.JTextField txt_quantidade;
    private javax.swing.JTextField txt_reciboDe;
    private javax.swing.JTextField txt_segundoAno;
    private javax.swing.JTextField txt_total;
    private javax.swing.JTextField txt_totalemDinheiro;
    // End of variables declaration//GEN-END:variables
}
