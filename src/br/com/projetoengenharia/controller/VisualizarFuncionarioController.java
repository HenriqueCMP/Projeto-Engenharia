/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetoengenharia.controller;

import br.com.projetoengenharia.dao.FuncionarioDAO;
import br.com.projetoengenharia.model.Funcionario;
import br.com.projetoengenharia.util.ManipulacaoPaginasUtil;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author henri
 */
public class VisualizarFuncionarioController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Funcionario funcionarioVisualizar = ManipulacaoPaginasUtil.getFuncionarioVisualizar();
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    @FXML
    private Button btnEditar;

    @FXML
    private Button btnVoltar;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblCpf;

    @FXML
    private Label lblCargo;

    @FXML
    private Button btnDeletar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencher();
    }    
     private void preencher() {
        lblCpf.setText(funcionarioVisualizar.getCpf());
        lblNome.setText(funcionarioVisualizar.getNome());
        lblCargo.setText(funcionarioVisualizar.getCargo());

    }

    private void confirmarDeletar() {
        if (JOptionPane.showConfirmDialog(null, "Deseja Excluir o Registro", "Confirmar a Exclusão", JOptionPane.YES_NO_OPTION) == 0) {
            try {
                funcionarioDAO.remover(funcionarioVisualizar);
                ManipulacaoPaginasUtil.abreTP();
                ManipulacaoPaginasUtil.fecharVisualizarAluno();
            } catch (Exception ex) {
                Logger.getLogger(VisualizarAlunoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
