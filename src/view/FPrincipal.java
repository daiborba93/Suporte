/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author Daiane
 */
public class FPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form FPrincipal
     */
    public FPrincipal() {
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

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mCadastroPedido = new javax.swing.JMenuItem();
        mCadastroEquipamento = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        mPesquisarPedido = new javax.swing.JMenuItem();
        mPesquisarEquipamento = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("Cadastrar");

        mCadastroPedido.setText("Cadastrar Pedido");
        mCadastroPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mCadastroPedidoActionPerformed(evt);
            }
        });
        jMenu1.add(mCadastroPedido);

        mCadastroEquipamento.setText("Cadastrar Equipamento");
        mCadastroEquipamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mCadastroEquipamentoActionPerformed(evt);
            }
        });
        jMenu1.add(mCadastroEquipamento);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Pesquisar");

        mPesquisarPedido.setText("Pesquisar Pedido");
        jMenu2.add(mPesquisarPedido);

        mPesquisarEquipamento.setText("Pesquisar Equipamento");
        jMenu2.add(mPesquisarEquipamento);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mCadastroPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mCadastroPedidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mCadastroPedidoActionPerformed

    private void mCadastroEquipamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mCadastroEquipamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mCadastroEquipamentoActionPerformed

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    public javax.swing.JMenuItem mCadastroEquipamento;
    public javax.swing.JMenuItem mCadastroPedido;
    public javax.swing.JMenuItem mPesquisarEquipamento;
    public javax.swing.JMenuItem mPesquisarPedido;
    // End of variables declaration//GEN-END:variables
}
