/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetoengenharia.controller;

import br.com.projetoengenharia.dao.AlunoDAO;
import br.com.projetoengenharia.dao.FuncionarioDAO;
import br.com.projetoengenharia.model.Aluno;
import br.com.projetoengenharia.model.Funcionario;
import br.com.projetoengenharia.util.ManipulacaoPaginasUtil;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
public class TelaPrincipalController implements Initializable {

    private List<Aluno> alunos = new ArrayList<>();
    private List<Funcionario> funcionarios;
    private AlunoDAO alunoDAO = new AlunoDAO();
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private Aluno alunoAtual;
    private Funcionario funcionarioAtual = ManipulacaoPaginasUtil.getFuncionarioAtual();
    private Funcionario funcionarioVisualizar;

    @FXML
    private Button btnCadastrarAluno;

    @FXML
    private Button btnVisualizarAluno;

    @FXML
    private TextField txtCPFAluno;

    @FXML
    private Button btnCadastrarFuncionario;

    @FXML
    private Button btnVisualizarFuncionario;

    @FXML
    private TextField txtBuscaFuncionario;

    @FXML
    private Button btnRelatorios;

    @FXML
    private Button btnSair;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ManipulacaoPaginasUtil.setAlunoAtual(null);
        ManipulacaoPaginasUtil.setFuncionarioVisualizar(null);
        try {
            alunos = alunoDAO.listarAlunos();
        } catch (Exception ex) {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            funcionarios = funcionarioDAO.listar();
//        
        } catch (Exception ex) {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

        btnCadastrarAluno.setOnMouseClicked((MouseEvent e) -> {
            ManipulacaoPaginasUtil.abreCadastroAluno();
            ManipulacaoPaginasUtil.fechaTP();
        });
        btnCadastrarAluno.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                ManipulacaoPaginasUtil.abreCadastroAluno();
                ManipulacaoPaginasUtil.fechaTP();
            }
        });

        btnVisualizarAluno.setOnMouseClicked((MouseEvent e) -> {
            try {
                verificarCPFAluno();
            } catch (Exception ex) {
                Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnVisualizarAluno.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                try {
                    verificarCPFAluno();
                } catch (Exception ex) {
                    Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        btnCadastrarFuncionario.setOnMouseClicked((MouseEvent e) -> {
            ManipulacaoPaginasUtil.abreCadastroFuncionario();
            ManipulacaoPaginasUtil.fechaTP();
        });
        btnCadastrarFuncionario.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                ManipulacaoPaginasUtil.abreCadastroFuncionario();
                ManipulacaoPaginasUtil.fechaTP();
            }
        });

        btnVisualizarFuncionario.setOnMouseClicked((MouseEvent e) -> {
            try {
                verificarCPFFuncionario();
            } catch (Exception ex) {
                Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnVisualizarFuncionario.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                try {
                    verificarCPFFuncionario();
                } catch (Exception ex) {
                    Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        btnSair.setOnMouseClicked((MouseEvent e) -> {
            logout();
        });
        btnSair.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                logout();
            }
        });

        btnRelatorios.setOnMouseClicked((MouseEvent e) -> {
            try {
                gerarPDF();
            } catch (DocumentException ex) {
                Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnRelatorios.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                try {
                    gerarPDF();
                } catch (DocumentException ex) {
                    Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        if (ManipulacaoPaginasUtil.getFuncionarioAtual() != null) {
            verificarCargo();
        }

    }

    private void verificarCargo() {
//        funcionarioAtual = ManipulacaoPaginasUtil.getFuncionarioAtual();
        if (funcionarioAtual.getCargo().equals("Administrador")) {
            btnCadastrarAluno.setDisable(true);
        } else if (funcionarioAtual.getCargo().equals("Atendente")) {
            btnVisualizarFuncionario.setDisable(true);
            txtBuscaFuncionario.setDisable(true);
            btnCadastrarFuncionario.setDisable(true);

        } else if (funcionarioAtual.getCargo().equals("Personal")) {
            btnVisualizarFuncionario.setDisable(true);
            txtBuscaFuncionario.setDisable(true);
            btnCadastrarFuncionario.setDisable(true);
        }
    }

    private void verificarCPFAluno() throws Exception {
        if (!verificarAlunoExistente()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Aluno Invalido");
            alert.setContentText("CPF Incorreto");
            alert.show();
        } else {

            if (alunoDAO.getPorCPF(txtCPFAluno.getText()) == null) {
                System.out.println("erro");
            } else {

                alunoAtual = alunoDAO.getPorCPF(txtCPFAluno.getText());
                ManipulacaoPaginasUtil.setAlunoAtual(alunoAtual);
                ManipulacaoPaginasUtil.abreVisualizarAluno();
                ManipulacaoPaginasUtil.fechaTP();
            }

        }
    }

    private void verificarCPFFuncionario() throws Exception {
        if (!verificarFuncionarioExistente()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Funcionario Invalido");
            alert.setContentText("CPF Incorreto");
            alert.show();
        } else {
            if (txtBuscaFuncionario.getText() != null) {
                //funcionarioVisualizar = funcionarioDAO.getPorCPF(txtBuscaFuncionario.getText());
                if (funcionarioDAO.buscarPorLogin(txtBuscaFuncionario.getText()) != null) {
                    ManipulacaoPaginasUtil.setFuncionarioVisualizar(funcionarioDAO.buscarPorLogin(txtBuscaFuncionario.getText()));
                    ManipulacaoPaginasUtil.abreVisualizarFuncionario();
                    ManipulacaoPaginasUtil.fechaTP();
                } else {
                    System.out.println("erro");

                }
            } else {
                System.out.println("vazio");
            }

        }
    }

    private boolean verificarAlunoExistente() {

        for (Aluno aluno : alunos) {
            if (aluno.getCpf().equals(txtCPFAluno.getText())) {
                return true;
            }
        }
        return false;
    }

    private boolean verificarFuncionarioExistente() {
        for (Funcionario func : funcionarios) {
            if (func.getLogin().equals(txtBuscaFuncionario.getText()));
            System.out.println(func);
            return true;
        }
        return false;
    }

    private void logout() {
        ManipulacaoPaginasUtil.setFuncionarioAtual(null);
        ManipulacaoPaginasUtil.abreLogin();
        ManipulacaoPaginasUtil.fechaTP();
    }

    private void gerarPDF() throws DocumentException {
        Document doc = new Document();
        try {
            PdfWriter pdf = PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\henri\\Desktop\\Projeto_engenharia\\Alunos.pdf"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        float[] pointColumnWidths = {75F, 75F, 75F, 75F};
        PdfPTable table = new PdfPTable(pointColumnWidths);

        PdfPCell celulaNome = new PdfPCell(new Phrase("Nome"));
        celulaNome.setHorizontalAlignment(Element.ALIGN_CENTER);
        PdfPCell celulaCpf = new PdfPCell(new Phrase("CPF"));
        celulaCpf.setHorizontalAlignment(Element.ALIGN_CENTER);
        PdfPCell celulaTelefone = new PdfPCell(new Phrase("Telefone"));
        celulaTelefone.setHorizontalAlignment(Element.ALIGN_CENTER);
        PdfPCell celulaEmail = new PdfPCell(new Phrase("E-mail"));
        celulaEmail.setHorizontalAlignment(Element.ALIGN_CENTER);

        doc.open();
        table.addCell(celulaNome);
        table.addCell(celulaCpf);
        table.addCell(celulaTelefone);
        table.addCell(celulaEmail);

        for (Aluno aluno : alunos) {
            celulaNome = new PdfPCell(new Phrase(aluno.getNome()));
            celulaCpf = new PdfPCell(new Phrase(aluno.getCpf()));
            celulaTelefone = new PdfPCell(new Phrase(aluno.getContato().getTelefone()));
            celulaEmail = new PdfPCell(new Phrase(aluno.getContato().getEmail()));

            table.addCell(celulaNome);
            table.addCell(celulaCpf);
            table.addCell(celulaTelefone);
            table.addCell(celulaEmail);

        }
        doc.add(table);
        doc.close();
    }

}
