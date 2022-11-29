package sistemacadastro;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import sistemacadastro.model.Jogador;
import sistemacadastro.model.Time;
import sistemacadastro.pilha.PilhaJogadores;

public class CadastroJogador extends javax.swing.JFrame {
    
    public static PilhaJogadores<Jogador> pilhaJogadores = new PilhaJogadores<>(30);
    public static int idJogador = 0;
    
    public CadastroJogador() {

        initComponents();
        this.setLocationRelativeTo(null);
        this.pack();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        jTable1.setRowSorter(new TableRowSorter(model));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        Jogador jogadorTeste = new Jogador(0, "Andrés Nicolás D'Alessandro", "41", "1,74", "68", "Meia", Time.getListaTimes().get(0));
        Jogador.addJogadorLista(jogadorTeste);
        pilhaJogadores.push(jogadorTeste);
        
        preencherTabela();
        
    }

    public void preencherTabela() {

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setNumRows(0);
        
        for(int i = 0; i != Jogador.getListaJogadores().size(); i++) {
            model.addRow(new Object[]{
                Jogador.getListaJogadores().get(i).getId(),
                Jogador.getListaJogadores().get(i).getNome(),
                Jogador.getListaJogadores().get(i).getIdade(),
                Jogador.getListaJogadores().get(i).getAltura(),
                Jogador.getListaJogadores().get(i).getPeso(),
                Jogador.getListaJogadores().get(i).getPosicao(),
                Jogador.getListaJogadores().get(i).getTime().getNome()
            });
        }
        
    }
    
    public void preencheTabelaPilha() {
        
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setNumRows(0);
        
        int aux = pilhaJogadores.getPosicaoTopo();
        for(int i = -1; i < pilhaJogadores.getPosicaoTopo(); pilhaJogadores.pop()) {
            model.addRow(new Object[] {
                pilhaJogadores.top().getId(),
                pilhaJogadores.top().getNome(),
                pilhaJogadores.top().getIdade(),
                pilhaJogadores.top().getAltura(),
                pilhaJogadores.top().getPeso(),
                pilhaJogadores.top().getPosicao(),
                pilhaJogadores.top().getTime().getNome()
            });
        }
        pilhaJogadores.setPosicaoTopo(aux);
        
    }
    
    private boolean validarDados() {
        String valida = "";
        idJogador++;
        
        if (nome.getText() == null || nome.getText().isEmpty()) {
            valida += "[ Nome ]";
        }
        if (idade.getText() == null || idade.getText().isEmpty()) {
            valida += "[ Idade ]";
        }
        if (altura.getText() == null || altura.getText().isEmpty()) {
            valida += "[ Altura ]";
        }
        if (peso.getText() == null || peso.getText().isEmpty()) {
            valida += "[ Peso ]";
        }
        if (posicao.getText() == null || posicao.getText().isEmpty()) {
            valida += "[ Posição ]";
        }
        if (cbTime.getSelectedItem() == "Selecione") {
            valida += "[ Time ]";
        }
        if (valida != "") {
            JOptionPane.showMessageDialog(null, "Atenção, os campos a seguir são obrigatórios:\n" + valida);
            return false;
        }
        return true;
    }
    
    private void limpaCampos() {
        
        nome.setText("");
        idade.setText("");
        altura.setText("");
        peso.setText("");
        posicao.setText("");
        
    }
    
    private void limpaCamposSelect() {
        
        if(jTable1.getSelectedRow() != -1) {
            nome.setText("");
            idade.setText("");
            altura.setText("");
            peso.setText("");
            posicao.setText("");
        }
        
    }
    
    private void bubbleSort() {
        
        for(int i = 0; i < Jogador.getListaJogadores().size(); i++) {
            for(int j = Jogador.getListaJogadores().size() - 1; j > i; j--) {
                if(Jogador.getListaJogadores().get(i).getNome().compareToIgnoreCase(Jogador.getListaJogadores().get(j).getNome()) > 0) {
                    Jogador aux = Jogador.getListaJogadores().get(i);
                    Jogador.getListaJogadores().set(i, Jogador.getListaJogadores().get(j));
                    Jogador.getListaJogadores().set(j, aux);
                }
            }
        }
        
    }
    
    private ArrayList<Jogador> quickSort(ArrayList<Jogador> lista, int a, int b) {
        
        if (a >= b) {
            return lista;
        }

        Jogador pivo = lista.get(b);

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
        ArrayList<Jogador> aux = new ArrayList<>();
        Jogador jogador = new Jogador();

        for(int i = 0; i < Jogador.getListaJogadores().size(); i++) {
            String proximo = Jogador.getListaJogadores().get(i).getNome();

            if (proximo.equalsIgnoreCase(txtBuscaSequencial.getText().trim())) {
                achou = true;
                aux.add(Jogador.getListaJogadores().get(i));
                jogador.setId(Jogador.getListaJogadores().get(i).getId());
                jogador.setNome(Jogador.getListaJogadores().get(i).getNome());
                jogador.setIdade(Jogador.getListaJogadores().get(i).getIdade());
                jogador.setAltura(Jogador.getListaJogadores().get(i).getAltura());
                jogador.setPeso(Jogador.getListaJogadores().get(i).getPeso());
                jogador.setPosicao(Jogador.getListaJogadores().get(i).getPosicao());
                jogador.setTime(Jogador.getListaJogadores().get(i).getTime());
                break;
            }
        }
        
        if(achou == false) {
            JOptionPane.showMessageDialog(null, "Não foi encontrado o jogador com o nome [" + txtBuscaSequencial.getText() + "].");
        } else {
            JOptionPane.showMessageDialog(null, "Jogador encontrado!\n" +
                                                  "\n- ID: " + jogador.getId() +
                                                  "\n- NOME: " + jogador.getNome() +
                                                  "\n- IDADE: " + jogador.getIdade() +
                                                  "\n- ALTURA: " + jogador.getAltura() +
                                                  "\n- PESO: " + jogador.getPeso() +
                                                  "\n- POSIÇÃO: " + jogador.getPosicao() +
                                                  "\n- TIME: " + jogador.getTime().getNome());
        }
        
        txtBuscaSequencial.setText("");

    }
    
    private void buscaBinaria() {
        
        boolean achou = false;
        int esquerda = 0, direita = Jogador.getListaJogadores().size()-1;
        Jogador jogador = new Jogador();

        while(esquerda <= direita) {
            int meio = esquerda + (direita - esquerda) / 2;

            if(Jogador.getListaJogadores().get(meio).getId() == (Integer) spinnerBuscaBinaria.getValue()) {
                achou = true;
                jogador.setId(Jogador.getListaJogadores().get(meio).getId());
                jogador.setNome(Jogador.getListaJogadores().get(meio).getNome());
                jogador.setIdade(Jogador.getListaJogadores().get(meio).getIdade());
                jogador.setAltura(Jogador.getListaJogadores().get(meio).getAltura());
                jogador.setPeso(Jogador.getListaJogadores().get(meio).getPeso());
                jogador.setPosicao(Jogador.getListaJogadores().get(meio).getPosicao());
                jogador.setTime(Jogador.getListaJogadores().get(meio).getTime());
                JOptionPane.showMessageDialog(null, "Jogador encontrado!\n" +
                                                  "\n- ID: " + jogador.getId() +
                                                  "\n- NOME: " + jogador.getNome() +
                                                  "\n- IDADE: " + jogador.getIdade() +
                                                  "\n- ALTURA: " + jogador.getAltura() +
                                                  "\n- PESO: " + jogador.getPeso() +
                                                  "\n- POSIÇÃO: " + jogador.getPosicao() +
                                                  "\n- TIME: " + jogador.getTime().getNome());
                break;
            }
            if(Jogador.getListaJogadores().get(meio).getId() < (Integer) spinnerBuscaBinaria.getValue()) {
                esquerda = meio + 1;
            } else {
                direita = meio - 1;
            }
        }

        if(achou == false) {
            JOptionPane.showMessageDialog(null, "Não foi encontrado o jogador com o ID [" + spinnerBuscaBinaria.getValue() + "].");
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
        jLabel5 = new javax.swing.JLabel();
        idade = new javax.swing.JTextField();
        altura = new javax.swing.JTextField();
        peso = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        posicao = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cbTime = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnPilha = new javax.swing.JButton();
        txtBuscaSequencial = new javax.swing.JTextField();
        btnBuscaSequencial = new javax.swing.JButton();
        btnBuscaBinaria = new javax.swing.JButton();
        spinnerBuscaBinaria = new javax.swing.JSpinner();
        btnBubbleSort = new javax.swing.JButton();
        btnQuickSort = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel1.setText("Cadastro de Jogador");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

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

        jLabel3.setText("Idade:");

        jLabel4.setText("Altura:");

        jLabel5.setText("Peso:");

        jLabel6.setText("Posição:");

        jLabel7.setText("Time:");

        cbTime.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));
        cbTime.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                cbTimeAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(245, 245, 245)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.CENTER)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(nome, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idade, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(altura, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(peso, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(posicao, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbTime, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(315, 315, 315)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(318, 318, 318)
                        .addComponent(jLabel7)))
                .addContainerGap(246, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(9, 9, 9)
                .addComponent(jLabel2)
                .addGap(6, 6, 6)
                .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jLabel3)
                .addGap(6, 6, 6)
                .addComponent(idade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(altura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jLabel5)
                .addGap(3, 3, 3)
                .addComponent(peso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(posicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "NOME", "IDADE", "ALTURA", "PESO", "POSIÇÃO", "TIME"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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
            jTable1.getColumnModel().getColumn(0).setMaxWidth(30);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jDesktopPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btnPilha.setBackground(new java.awt.Color(0, 153, 255));
        btnPilha.setText("Ordenar usando Pilha de Cadastro");
        btnPilha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPilhaActionPerformed(evt);
            }
        });

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

        btnQuickSort.setBackground(new java.awt.Color(51, 153, 255));
        btnQuickSort.setText("Ordenar por Quick Sort usando o ID");
        btnQuickSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuickSortActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(219, 219, 219)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBuscaSequencial, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnBubbleSort, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnQuickSort, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPilha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBuscaBinaria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBuscaSequencial, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(spinnerBuscaBinaria, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
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
            idJogador--;
            return;
        } else {
            for(int i = 0; i < Time.getListaTimes().size(); i++) {
                if(Time.getListaTimes().get(i).getNome().equals(cbTime.getSelectedItem())) {
                    Jogador jogador = new Jogador(idJogador, nome.getText(), idade.getText(), altura.getText(), peso.getText(), posicao.getText(), Time.getListaTimes().get(i));
                    Jogador.addJogadorLista(jogador);
                    pilhaJogadores.push(jogador);
                }
            }
            JOptionPane.showMessageDialog(null, "Jogador [" + nome.getText() + "] cadastrado com sucesso!");
            limpaCampos();
            preencherTabela();
        }
        
        
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed

        if (jTable1.getSelectedRow() != -1) {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            
            for(int i = 0; i < Jogador.getListaJogadores().size(); i++) {
                Jogador aux = Jogador.getListaJogadores().get(i);
                if(aux.getNome().equals(nome.getText())) {
                    Jogador.getListaJogadores().remove(aux);
                    break;
                }
            }
            
            JOptionPane.showMessageDialog(null, "Jogador [" + nome.getText() + "] excluído com sucesso!");
            limpaCampos();
            preencherTabela();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um jogador para excluir!");
        }

    }//GEN-LAST:event_btnExcluirActionPerformed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased

        if (jTable1.getSelectedRow() != -1) {
            nome.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
            idade.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
            altura.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString());
            peso.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString());
            posicao.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString());
        }

    }//GEN-LAST:event_jTable1KeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        if (jTable1.getSelectedRow() != -1) {
            nome.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
            idade.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
            altura.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString());
            peso.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString());
            posicao.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString());
        }

    }//GEN-LAST:event_jTable1MouseClicked

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        
        limpaCamposSelect();
        
    }//GEN-LAST:event_jPanel1MouseClicked

    private void cbTimeAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_cbTimeAncestorAdded
        
        for(int i = 0; i < Time.getListaTimes().size(); i++) {
            cbTime.addItem(Time.getListaTimes().get(i).getNome());
        }
        
    }//GEN-LAST:event_cbTimeAncestorAdded

    private void btnPilhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPilhaActionPerformed

        preencheTabelaPilha();

    }//GEN-LAST:event_btnPilhaActionPerformed

    private void btnBuscaSequencialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaSequencialActionPerformed

        buscaSequencial();

    }//GEN-LAST:event_btnBuscaSequencialActionPerformed

    private void btnBuscaBinariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaBinariaActionPerformed

        buscaBinaria();

    }//GEN-LAST:event_btnBuscaBinariaActionPerformed

    private void btnBubbleSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBubbleSortActionPerformed

        bubbleSort();
        preencherTabela();

    }//GEN-LAST:event_btnBubbleSortActionPerformed

    private void btnQuickSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuickSortActionPerformed

        quickSort(Jogador.getListaJogadores(), 0, Jogador.getListaJogadores().size()-1);
        preencherTabela();

    }//GEN-LAST:event_btnQuickSortActionPerformed
    
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroJogador().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField altura;
    private javax.swing.JButton btnBubbleSort;
    private javax.swing.JButton btnBuscaBinaria;
    private javax.swing.JButton btnBuscaSequencial;
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnPilha;
    private javax.swing.JButton btnQuickSort;
    private javax.swing.JComboBox<Object> cbTime;
    private javax.swing.JTextField idade;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField nome;
    private javax.swing.JTextField peso;
    private javax.swing.JTextField posicao;
    private javax.swing.JSpinner spinnerBuscaBinaria;
    private javax.swing.JTextField txtBuscaSequencial;
    // End of variables declaration//GEN-END:variables
}
