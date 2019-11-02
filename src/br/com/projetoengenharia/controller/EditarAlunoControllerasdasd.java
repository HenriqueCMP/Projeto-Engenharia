/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetoengenharia.controller;

import br.com.projetoengenharia.dao.AlunoDAO;
import br.com.projetoengenharia.model.Aluno;
import br.com.projetoengenharia.util.ManipulacaoPaginasUtil;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class EditarAlunoControllerasdasd implements Initializable {

    private Aluno alunoAtual;
    private AlunoDAO alunoDAO = new AlunoDAO();
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
    private Button btnConfirmar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnCancelar.setOnMouseClicked((MouseEvent e) -> {
            ManipulacaoPaginasUtil.abreTP();
            ManipulacaoPaginasUtil.fecharEditarAluno();
        });
        btnCancelar.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                ManipulacaoPaginasUtil.abreTP();
                ManipulacaoPaginasUtil.fecharEditarAluno();
            }
        });
        alunoAtual = ManipulacaoPaginasUtil.getAlunoAtual();
        System.out.println(alunoAtual);
        preencherEditavel();
    }
    
    private void preencherEditavel() {
        System.out.println("preenchimento");
        System.out.println(alunoAtual);
        txtCPF.setText(alunoAtual.getCpf());
        txtNome.setText(alunoAtual.getNome());
        txtTelefone.setText(alunoAtual.getContato().getTelefone());
        txtEmail.setText(alunoAtual.getContato().getEmail());
        txtRua.setText(alunoAtual.getEndereco().getRua());
        txtBairro.setText(alunoAtual.getEndereco().getBairro());
        txtCEP.setText(alunoAtual.getEndereco().getCep());
        txtNumero.setText(alunoAtual.getEndereco().getNumero());

    }

}
