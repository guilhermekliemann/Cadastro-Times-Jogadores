package sistemacadastro;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import sistemacadastro.model.Time;
import sistemacadastro.pilha.PilhaTime;

public class CadastroTime extends javax.swing.JFrame {

    public static PilhaTime<Time> pilhaTimes = new PilhaTime<>(30);
    public static int idTime = 0;
    
    public CadastroTime() {

        initComponents();
        this.setLocationRelativeTo(null);
        this.pack();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        jTable1.setRowSorter(new TableRowSorter(model));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        Time timeTeste = new Time(0, "Internacional", "Mano Menezes", "Beira Rio");
        Time.addTimeLista(timeTeste);
        pilhaTimes.push(timeTeste);
        
        preencherTabela();

    }

    private void preencherTabela() {

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setNumRows(0);
        
        for(int i = 0; i != Time.getListaTimes().size(); i++) {
            model.addRow(new Object[]{
                Time.getListaTimes().get(i).getId(),
                Time.getListaTimes().get(i).getNome(),
                Time.getListaTimes().get(i).getTecnico(),
                Time.getListaTimes().get(i).getEstadio()
            });
        }
        
    }
    
    private void preencheTabelaPilha() {
        
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setNumRows(0);
        
        int aux = pilhaTimes.getPosicaoTopo();
        for(int i = -1; i < pilhaTimes.getPosicaoTopo(); pilhaTimes.pop()) {
            model.addRow(new Object[] {
                pilhaTimes.top().getId(),
                pilhaTimes.top().getNome(),
                pilhaTimes.top().getTecnico(),
                pilhaTimes.top().getEstadio()
            });
        }
        pilhaTimes.setPosicaoTopo(aux);
        
    }
    
    private boolean validarDados() {
        
        String valida = "";
        idTime++;
        
        if (nome.getText() == null || nome.getText().isEmpty()) {
            valida += "[ Nome ]";
        }
        if (tecnico.getText() == null || tecnico.getText().isEmpty()) {
            valida += "[ Técnico ]";
        }
        if (estadio.getText() == null || estadio.getText().isEmpty()) {
            valida += "[ Estádio ]";
        }
        if (valida != "") {
            JOptionPane.showMessageDialog(null, "Atenção, os campos a seguir são obrigatórios:\n" + valida);
            return false;
        }
        return true;
        
    }
    
    private void limpaCampos() {
        
        nome.setText("");
        tecnico.setText("");
        estadio.setText("");
        
    }
    
    private void limpaCamposSelect() {
        
        if(jTable1.getSelectedRow() != -1) {
            nome.setText("");
            tecnico.setText("");
            estadio.setText("");
        }
        
    }
    
    private void bubbleSort() {
        
        for(int i = 0; i < Time.getListaTimes().size(); i++) {
            for(int j = Time.getListaTimes().size() - 1; j > i; j--) {
                if(Time.getListaTimes().get(i).getNome().compareToIgnoreCase(Time.getListaTimes().get(j).getNome()) > 0) {
                    Time aux = Time.getListaTimes().get(i);
                    Time.getListaTimes().set(i, Time.getListaTimes().get(j));
                    Time.getListaTimes().set(j, aux);
                }
            }
        }
        
    }
    
    private ArrayList<Time> quickSort(ArrayList<Time> lista, int a, int b) {
        
        if (a >= b) {
            return lista;
        }

        Time pivo = lista.get(b);

        int esquerda = a;
        int direita = b;

        while (esquerda < direita) {
            while(lista.get(esquerda).compareTo(pivo) < 0) {
                esquerda++;
            }

            while(lista.get(direita).compareTo(pivo) > 0) {
                direita--;
            }

            if (direita > esquerda) {
                Collections.swap(lista, esquerda, direita);
            }
        }

        quickSort(lista, a, direita-1);
        quickSort(lista, direita+1, b);

        return lista;

    }
    
    private void buscaSequencial() {
        
        boolean achou = false;
        ArrayList<Time> aux = new ArrayList<>();
        Time time = new Time();

        for(int i = 0; i < Time.getListaTimes().size(); i++) {
            String proximo = Time.getListaTimes().get(i).getNome();

            if (proximo.equalsIgnoreCase(txtBuscaSequencial.getText().trim())) {
                achou = true;
                aux.add(Time.getListaTimes().get(i));
                time.setId(Time.getListaTimes().get(i).getId());
                time.setNome(Time.getListaTimes().get(i).getNome());
                time.setTecnico(Time.getListaTimes().get(i).getTecnico());
                time.setEstadio(Time.getListaTimes().get(i).getEstadio());
                break;
            }
        }
        
        if(achou == false) {
            JOptionPane.showMessageDialog(null, "Não foi encontrado o time com o nome [" + txtBuscaSequencial.getText() + "].");
        } else {
            JOptionPane.showMessageDialog(null, "Time encontrado!\n" +
                                                  "\n- ID: " + time.getId() +
                                                  "\n- NOME: " + time.getNome() +
                                                  "\n- TÉCNICO: " + time.getTecnico() +
                                                  "\n- ESTÁDIO: " + time.getEstadio());
        }
        
        txtBuscaSequencial.setText("");

    }
    
    private void buscaBinaria() {
        
        boolean achou = false;
        int esquerda = 0, direita = Time.getListaTimes().size()-1;
        Time time = new Time();

        while(esquerda <= direita) {
            int meio = esquerda + (direita - esquerda) / 2;

            if(Time.getListaTimes().get(meio).getId() == (Integer) spinnerBuscaBinaria.getValue()) {
                achou = true;
                time.setId(Time.getListaTimes().get(meio).getId());
                time.setNome(Time.getListaTimes().get(meio).getNome());
                time.setTecnico(Time.getListaTimes().get(meio).getTecnico());
                time.setEstadio(Time.getListaTimes().get(meio).getEstadio());
                JOptionPane.showMessageDialog(null, "Time encontrado!\n" +
                                                  "\n- ID: " + time.getId() +
                                                  "\n- NOME: " + time.getNome() +
                                                  "\n- TÉCNICO: " + time.getTecnico() +
                                                  "\n- ESTÁDIO: " + time.getEstadio());
                break;
            }
            if(Time.getListaTimes().get(meio).getId() < (Integer) spinnerBuscaBinaria.getValue()) {
                esquerda = meio + 1;
            } else {
                direita = meio - 1;
            }
        }

        if(achou == false) {
            JOptionPane.showMessageDialog(null, "Não foi encontrado o time com o ID [" + spinnerBuscaBinaria.getValue() + "].");
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinner1 = new javax.swing.JSpinner();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nome = new javax.swing.JTextField();
        btnCadastrar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tecnico = new javax.swing.JTextField();
        estadio = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtBuscaSequencial = new javax.swing.JTextField();
        btnBuscaSequencial = new javax.swing.JButton();
        btnBuscaBinaria = new javax.swing.JButton();
        spinnerBuscaBinaria = new javax.swing.JSpinner();
        btnBubbleSort = new javax.swing.JButton();
        btnQuickSort = new javax.swing.JButton();
        btnPilha = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel1.setText("Cadastro de Time");

        jLabel2.setText("Nome:");

        btnCadastrar.setBackground(new java.awt.Color(0, 204, 102));
        btnCadastrar.setText("Cadastrar");
        btnCadastrar.setBorder(null);
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        btnExcluir.setBackground(new java.awt.Color(255, 102, 102));
        btnExcluir.setText("Excluir");
        btnExcluir.setBorder(null);
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        jLabel3.setText("Técnico:");

        jLabel4.setText("Estádio:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(nome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tecnico, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(estadio, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(318, 318, 318))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(314, 314, 314))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(316, 316, 316)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(9, 9, 9)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(estadio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(83, 83, 83))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "NOME", "TÉCNICO", "ESTÁDIO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jDesktopPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnBuscaSequencial.setBackground(new java.awt.Color(255, 255, 153));
        btnBuscaSequencial.setText("Busca Sequencial pelo Nome");
        btnBuscaSequencial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaSequencialActionPerformed(evt);
            }
        });

        btnBuscaBinaria.setBackground(new java.awt.Color(255, 255, 153));
        btnBuscaBinaria.setText("Busca Binária por ID");
        btnBuscaBinaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaBinariaActionPerformed(evt);
            }
        });

        btnBubbleSort.setBackground(new java.awt.Color(51, 153, 255));
        btnBubbleSort.setText("Ordenar por Bubble Sort usando o Nome");
        btnBubbleSort.setBorder(null);
        btnBubbleSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBubbleSortActionPerformed(evt);
            }
        });

        btnQuickSort.setBackground(new java.awt.Color(0, 153, 255));
        btnQuickSort.setText("Ordenar por Quick Sort usando o ID");
        btnQuickSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuickSortActionPerformed(evt);
            }
        });

        btnPilha.setBackground(new java.awt.Color(0, 153, 255));
        btnPilha.setText("Ordenar usando Pilha de Cadastro");
        btnPilha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPilhaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtBuscaSequencial, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(266, 266, 266))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(221, 221, 221)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBubbleSort, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnQuickSort, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnPilha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBuscaBinaria, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                            .addComponent(btnBuscaSequencial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(266, 266, 266)
                        .addComponent(spinnerBuscaBinaria, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscaSequencial, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscaSequencial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscaBinaria, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinnerBuscaBinaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(btnBubbleSort, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnQuickSort, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPilha, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed

        if(!validarDados()) {
            idTime--;
            return;
        } else {
            Time time = new Time(idTime, nome.getText(), tecnico.getText(), estadio.getText());
            Time.addTimeLista(time);
            pilhaTimes.push(time);
            JOptionPane.showMessageDialog(null, "Time [" + nome.getText()+ "] cadastrado com sucesso!");
            limpaCampos();
            preencherTabela();
        }

    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed

        if (jTable1.getSelectedRow() != -1) {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            for(int i = 0; i < Time.getListaTimes().size(); i++) {
                Time aux = Time.getListaTimes().get(i);
                if(aux.getNome().equals(nome.getText())) {
                    Time.getListaTimes().remove(aux);
                    break;
                }
            }
            JOptionPane.showMessageDialog(null, "Time [" + nome.getText()+ "] excluído com sucesso!");
            limpaCampos();
            preencherTabela();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um time para excluir!");
        }

    }//GEN-LAST:event_btnExcluirActionPerformed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased

        if (jTable1.getSelectedRow() != -1) {
            nome.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
            tecnico.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
            estadio.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString());
        }

    }//GEN-LAST:event_jTable1KeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        if (jTable1.getSelectedRow() != -1) {
            nome.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
            tecnico.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
            estadio.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString());
        }

    }//GEN-LAST:event_jTable1MouseClicked

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        
        limpaCamposSelect();
        
    }//GEN-LAST:event_jPanel1MouseClicked

    private void btnBubbleSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBubbleSortActionPerformed
        
        bubbleSort();
        preencherTabela();
        
    }//GEN-LAST:event_btnBubbleSortActionPerformed

    private void btnBuscaSequencialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaSequencialActionPerformed
        
        buscaSequencial();
        
    }//GEN-LAST:event_btnBuscaSequencialActionPerformed

    private void btnBuscaBinariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaBinariaActionPerformed
        
        buscaBinaria();
        
    }//GEN-LAST:event_btnBuscaBinariaActionPerformed

    private void btnQuickSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuickSortActionPerformed
        
        quickSort(Time.getListaTimes(),0, Time.getListaTimes().size()-1);
        preencherTabela();
        
    }//GEN-LAST:event_btnQuickSortActionPerformed

    private void btnPilhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPilhaActionPerformed
        
        preencheTabelaPilha();
        
    }//GEN-LAST:event_btnPilhaActionPerformed
    
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroTime().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBubbleSort;
    private javax.swing.JButton btnBuscaBinaria;
    private javax.swing.JButton btnBuscaSequencial;
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnPilha;
    private javax.swing.JButton btnQuickSort;
    private javax.swing.JTextField estadio;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField nome;
    private javax.swing.JSpinner spinnerBuscaBinaria;
    private javax.swing.JTextField tecnico;
    private javax.swing.JTextField txtBuscaSequencial;
    // End of variables declaration//GEN-END:variables

    
}
