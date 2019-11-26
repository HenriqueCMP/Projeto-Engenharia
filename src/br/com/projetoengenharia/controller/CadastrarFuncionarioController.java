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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author henri
 */
public class CadastrarFuncionarioController implements Initializable {

    private Funcionario funcionario;
    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtLogin;

    @FXML
    private TextField txtCargo;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnCancelar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnCancelar.setOnMouseClicked((MouseEvent e) -> {
            ManipulacaoPaginasUtil.abreTP();
            ManipulacaoPaginasUtil.fecharCadastroFuncionario();
        });
        btnCancelar.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                ManipulacaoPaginasUtil.abreTP();
                ManipulacaoPaginasUtil.fecharCadastroFuncionario();
            }
        });

        btnCadastrar.setOnMouseClicked((MouseEvent e) -> {
            if (!verificarVazio()) {
                if (verificarCargo()) {
                    inserirFuncionario();
                }
            }
        });
        btnCadastrar.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                if (!verificarVazio()) {
                    if (verificarCargo()) {
                        inserirFuncionario();
                    }
                }
            }
        });
    }

    private void inserirFuncionario() {
        funcionario = new Funcionario();

        funcionario.setNome(txtNome.getText());
        funcionario.setCpf(txtCPF.getText());
        funcionario.setLogin(txtLogin.getText());
        funcionario.setSenha(txtSenha.getText());
        funcionario.setCargo(txtCargo.getText());

        try {
            funcionarioDAO.inserir(funcionario);
            ManipulacaoPaginasUtil.abreTP();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exito");
            alert.setHeaderText("Cadastro Concluído");
            alert.setContentText("Contato Cadastrado com Sucesso");
            alert.show();
            ManipulacaoPaginasUtil.fecharCadastroFuncionario();
        } catch (Exception ex) {
            Logger.getLogger(CadastrarAlunoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean verificarVazio() {
        if (txtNome.getText().isEmpty()
                || txtCPF.getText().isEmpty()
                || txtLogin.getText().isEmpty()
                || txtSenha.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Cadastro Invalido");
            alert.setContentText("Campo(s) Obrigatório(s) em Branco");
            alert.show();
            return true;
        }
        return false;
    }

    private boolean verificarCargo() {
        if (txtCargo.getText().equals("Administrador")
                || txtCargo.getText().equals("Atendente")
                || txtCargo.getText().equals("Personal")) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Cadastro Invalido");
            alert.setContentText("Cargo Incorreto");
            alert.show();
            return false;
        }
    }
}
