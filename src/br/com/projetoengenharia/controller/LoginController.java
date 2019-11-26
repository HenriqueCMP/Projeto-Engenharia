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
import java.util.List;
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
public class LoginController implements Initializable {

    List<Funcionario> funcionarios;
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private Funcionario funcionarioAtual;
    
    @FXML
    private TextField txtLogin;
    
    @FXML
    private PasswordField txtSenha;
    
    @FXML
    private Button btnEntrar;

    @FXML
    private Button btnSair;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            funcionarios = funcionarioDAO.listar();
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        btnEntrar.setOnMouseClicked((MouseEvent e) -> {
            try {
                verificarLogin();
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        btnEntrar.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                try {
                    verificarLogin();
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        btnSair.setOnMouseClicked((MouseEvent e) -> {
            ManipulacaoPaginasUtil.fecharLogin();
            System.exit(0);
        });
    }

    private void verificarLogin() throws Exception {
        if (txtLogin.getText().isEmpty() || txtSenha.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Login Invalido");
            alert.setContentText("Usuario ou Senha em branco");
            alert.show();
        } else if (!verificarFuncionarioExistente()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Login Invalido");
            alert.setContentText("Usuario ou Senha Incorretos");
            alert.show();
        } else {
            funcionarioAtual = funcionarioDAO.buscarPorLogin(txtLogin.getText());
            ManipulacaoPaginasUtil.setFuncionarioAtual(funcionarioAtual);
            ManipulacaoPaginasUtil.abreTP();
            ManipulacaoPaginasUtil.fecharLogin();
        }
    }
    
    private boolean verificarFuncionarioExistente() {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getLogin().equals(txtLogin.getText())) {
                if (funcionario.getSenha().equals(txtSenha.getText())) {
                    return true;
                }
            }
        }
        return false;
    }
    
}
