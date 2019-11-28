/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetoengenharia.controller;

import br.com.projetoengenharia.dao.AlunoDAO;
import br.com.projetoengenharia.model.Aluno;
import br.com.projetoengenharia.model.Contato;
import br.com.projetoengenharia.model.Endereco;
import br.com.projetoengenharia.util.ManipulacaoPaginasUtil;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author henri
 */
public class CadastrarAlunoController implements Initializable {

    private Aluno aluno;
    private Contato contato;
    private Endereco endereco;
    AlunoDAO alunoDAO = new AlunoDAO();
    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCPF;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtTelefone;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtRua;

    @FXML
    private TextField txtBairro;

    @FXML
    private TextField txtNumero;

    @FXML
    private TextField txtCEP;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnCadastrar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btnCadastrar.setOnMouseClicked((MouseEvent e) -> {
            if (!verificarVazio()) {
                inserirAluno();
            }
        });
        btnCadastrar.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                if (!verificarVazio()) {
                    inserirAluno();
                }
            }
        });

        btnCancelar.setOnMouseClicked((MouseEvent e) -> {
            ManipulacaoPaginasUtil.abreTP();
            ManipulacaoPaginasUtil.fecharCadastroAluno();
        });
        btnCancelar.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                ManipulacaoPaginasUtil.abreTP();
                ManipulacaoPaginasUtil.fecharCadastroAluno();
            }
        });
    }

    private void inserirAluno() {
        aluno = new Aluno();
        contato = new Contato();
        endereco = new Endereco();

        aluno.setCpf(txtCPF.getText());
        aluno.setNome(txtNome.getText());
        
        aluno.setDataNascimento(Date.from(txtDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        contato.setTelefone(txtTelefone.getText());

        if (!txtEmail.getText().isEmpty()) {
            contato.setEmail(txtEmail.getText());
        }
        aluno.setContato(contato);
        endereco.setRua(txtRua.getText());
        endereco.setNumero(txtNumero.getText());
        endereco.setBairro(txtBairro.getText());
        if (!txtCEP.getText().isEmpty()) {
            endereco.setCep(txtCEP.getText());
        }
        aluno.setEndereco(endereco);
        try {
            alunoDAO.inserir(aluno);
            ManipulacaoPaginasUtil.abreTP();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exito");
            alert.setHeaderText("Cadastro Concluído");
            alert.setContentText("Aluno Cadastrado com Sucesso");
            alert.show();
            ManipulacaoPaginasUtil.fecharCadastroAluno();
        } catch (Exception ex) {
            Logger.getLogger(CadastrarAlunoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean verificarVazio() {
        if (txtNome.getText().isEmpty()
                || txtCPF.getText().isEmpty()
                || txtTelefone.getText().isEmpty()
                || txtRua.getText().isEmpty()
                || txtNumero.getText().isEmpty()
                || txtBairro.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Cadastro Invalido");
            alert.setContentText("Campo(s) Obrigatório(s) em Branco");
            alert.show();
            return true;
        }
        return false;
    }

}
