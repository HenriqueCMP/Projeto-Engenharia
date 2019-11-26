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
import java.time.LocalDate;
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
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author henri
 */
public class EditarAlunoController implements Initializable {

    Aluno alunoAtual = ManipulacaoPaginasUtil.getAlunoAtual();
    Aluno aluno;
    Endereco endereco;
    Contato contato;
    AlunoDAO alunoDAO = new AlunoDAO();

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCPF;

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
        preencherEditavel();
        btnCancelar.setOnMouseClicked((MouseEvent e) -> {
            ManipulacaoPaginasUtil.abreVisualizarAluno();
            ManipulacaoPaginasUtil.fecharEditarAluno();
        });
        btnCancelar.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                ManipulacaoPaginasUtil.abreVisualizarAluno();
                ManipulacaoPaginasUtil.fecharEditarAluno();
            }
        });

        btnConfirmar.setOnMouseClicked((MouseEvent e) -> {
            if (verificarVazio()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Alteração Invalida");
                alert.setContentText("Algum Espaço em Branco");
                alert.show();
            } else if (verificarAlterar()) {
                ManipulacaoPaginasUtil.abreVisualizarAluno();
                ManipulacaoPaginasUtil.fecharEditarAluno();
            } else {
                editarAluno();
            }
        });
        btnConfirmar.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                if (verificarVazio()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Alteração Invalida");
                    alert.setContentText("Algum Espaço em Branco");
                    alert.show();
                } else if (verificarAlterar()) {
                    ManipulacaoPaginasUtil.abreVisualizarAluno();
                    ManipulacaoPaginasUtil.fecharEditarAluno();
                } else {
                    editarAluno();
                }
            }

        });

    }

    private void preencherEditavel() {
        txtCPF.setText(alunoAtual.getCpf());
        txtNome.setText(alunoAtual.getNome());
//        LocalDate localDate = alunoAtual.getDataNascimento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//        txtDate.setValue(localDate);
        txtTelefone.setText(alunoAtual.getContato().getTelefone());
        txtEmail.setText(alunoAtual.getContato().getEmail());
        txtRua.setText(alunoAtual.getEndereco().getRua());
        txtBairro.setText(alunoAtual.getEndereco().getBairro());
        txtCEP.setText(alunoAtual.getEndereco().getCep());
        txtNumero.setText(alunoAtual.getEndereco().getNumero());
    }

    private void editarAluno() {
        aluno = new Aluno();
        contato = new Contato();
        endereco = new Endereco();

        aluno.setCpf(txtCPF.getText());
        aluno.setNome(txtNome.getText());
//        aluno.setDataNascimento(Date.from(txtDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        contato.setTelefone(txtTelefone.getText());
        contato.setEmail(txtEmail.getText());
        aluno.setContato(contato);
        endereco.setRua(txtRua.getText());
        endereco.setNumero(txtNumero.getText());
        endereco.setBairro(txtBairro.getText());
        endereco.setCep(txtCEP.getText());
        

        aluno.setEndereco(endereco);
        try {
            alunoDAO.editar(aluno);
            ManipulacaoPaginasUtil.abreTP();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exito");
            alert.setHeaderText("Edição Concluída");
            alert.setContentText("Contato Editado com Sucesso");
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
            return true;
        }
        return false;
    }

    private boolean verificarAlterar() {
        alunoAtual = ManipulacaoPaginasUtil.getAlunoAtual();
        return alunoAtual.getCpf().equals(txtCPF.getText())
                && alunoAtual.getNome().equals(txtNome.getText())
                && alunoAtual.getContato().getTelefone().equals(txtTelefone.getText())
//                && alunoAtual.getContato().getEmail().equals(txtEmail.getText())
                && alunoAtual.getEndereco().getRua().equals(txtRua.getText())
                && alunoAtual.getEndereco().getNumero().equals(txtNumero.getText())
                && alunoAtual.getEndereco().getBairro().equals(txtBairro.getText());
//                && alunoAtual.getEndereco().getCep().equals(txtCEP.getText());
    }

}
