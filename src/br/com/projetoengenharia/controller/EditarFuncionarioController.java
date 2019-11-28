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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author henri
 */
public class EditarFuncionarioController implements Initializable {

    Funcionario funcionario;
    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtCargo;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnCancelar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherEditavel();
        btnCancelar.setOnMouseClicked((MouseEvent e) -> {
            ManipulacaoPaginasUtil.abreVisualizarFuncionario();
            ManipulacaoPaginasUtil.fecharEditarFuncionario();
        });
        btnCancelar.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                ManipulacaoPaginasUtil.abreVisualizarFuncionario();
                ManipulacaoPaginasUtil.fecharEditarFuncionario();
            }
        });
        btnEditar.setOnMouseClicked((MouseEvent e) -> {
            if (verificarVazio()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Alteração Invalida");
                alert.setContentText("Algum Espaço em Branco");
                alert.show();
            } else if (verificarAlterar()) {
                ManipulacaoPaginasUtil.abreVisualizarFuncionario();
                ManipulacaoPaginasUtil.fecharEditarFuncionario();
            } else {
                editarFuncionario();
            }
        });
        btnEditar.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                if (verificarVazio()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Alteração Invalida");
                    alert.setContentText("Algum Espaço em Branco");
                    alert.show();
                } else if (verificarAlterar()) {
                    ManipulacaoPaginasUtil.abreVisualizarFuncionario();
                    ManipulacaoPaginasUtil.fecharEditarFuncionario();
                } else {
                    editarFuncionario();
                }
            }

        });
        
    }
    private boolean verificarVazio() {
        return txtNome.getText().isEmpty()
                || txtCPF.getText().isEmpty()
                || txtCargo.getText().isEmpty();
    }
    
    private void preencherEditavel(){
        txtNome.setText(ManipulacaoPaginasUtil.getFuncionarioVisualizar().getNome());
        txtCPF.setText(ManipulacaoPaginasUtil.getFuncionarioVisualizar().getCpf());
        txtCargo.setText(ManipulacaoPaginasUtil.getFuncionarioVisualizar().getCargo());
    }
    
    private void editarFuncionario(){
        funcionario = ManipulacaoPaginasUtil.getFuncionarioVisualizar();
        funcionario.setNome(txtNome.getText());
        funcionario.setCpf(txtCPF.getText());
        funcionario.setCargo(txtCargo.getText());
        
        try {
            funcionarioDAO.editar(funcionario);
            ManipulacaoPaginasUtil.abreTP();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exito");
            alert.setHeaderText("Edição Concluída");
            alert.setContentText("Funcionario Editado com Sucesso");
            alert.show();
            ManipulacaoPaginasUtil.fecharEditarFuncionario();
        } catch (Exception ex) {
            Logger.getLogger(EditarFuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean verificarAlterar() {
        funcionario = ManipulacaoPaginasUtil.getFuncionarioAtual();
        return funcionario.getCpf().equals(txtCPF.getText())
                && funcionario.getNome().equals(txtNome.getText())
                && funcionario.getCargo().equals(txtCargo.getText());
    }

}
