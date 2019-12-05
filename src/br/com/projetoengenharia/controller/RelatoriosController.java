/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetoengenharia.controller;

import br.com.projetoengenharia.dao.AlunoDAO;
import br.com.projetoengenharia.model.Aluno;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author henri
 */
public class RelatoriosController implements Initializable {

    private List<Aluno> alunos = new ArrayList<>();
    private AlunoDAO alunoDAO = new AlunoDAO();

    @FXML
    private Button btnRelatorioAlunos;

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnRelatorioFuncionario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            alunos = alunoDAO.listarAlunos();
        } catch (Exception ex) {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

        btnRelatorioAlunos.setOnMouseClicked((MouseEvent e) -> {
            try {
                gerarPDFAluno();
            } catch (DocumentException ex) {
                Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnRelatorioAlunos.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                try {
                    gerarPDFAluno();
                } catch (DocumentException ex) {
                    Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void gerarPDFAluno() throws DocumentException {
        Document doc = new Document();
        try {
            PdfWriter pdf = PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\henri\\Desktop\\Projeto_engenharia\\Alunos.pdf"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

//        try {
////            p.add(Image.getInstance("@../../../../imagens/LogoPrincipal.jpeg"));
//            p.add(Image.getInstance("C:\\Users\\henri\\Desktop\\Projeto_engenharia\\src\\imagens\\LogoPrincipal.jpeg"));
//            p.setAlignment(Image.ALIGN_MIDDLE);
//        } catch (BadElementException ex) {
//            Logger.getLogger(RelatoriosController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(RelatoriosController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        Paragraph p = new Paragraph();
        p.add("\n");
        p.add(new Phrase("Alunos", new Font(FontFamily.TIMES_ROMAN, 36)));
        p.setAlignment(Element.ALIGN_CENTER);
        p.add("\n\n");

        PdfPTable tabela = new PdfPTable(1); //cria uma tabela com 2 colunas
        PdfPCell celula1 = null;
        try {
            celula1 = new PdfPCell(Image.getInstance("C:\\Users\\henri\\Desktop\\Projeto_engenharia\\src\\imagens\\LogoPrincipal.jpeg"));

        } catch (BadElementException ex) {
            Logger.getLogger(RelatoriosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RelatoriosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        celula1.setBorder(-1);
        celula1.setHorizontalAlignment(Image.ALIGN_CENTER);
        celula1.setVerticalAlignment(Element.ALIGN_TOP);
        tabela.addCell(celula1);
        float[] pointColumnWidths = {75F, 75F, 75F, 75F};
        PdfPTable table = new PdfPTable(pointColumnWidths);

        PdfPCell celulaNome = new PdfPCell(new Phrase("Nome", new Font(FontFamily.TIMES_ROMAN, 12)));
        celulaNome.setHorizontalAlignment(Element.ALIGN_CENTER);
        PdfPCell celulaCpf = new PdfPCell(new Phrase("CPF", new Font(FontFamily.TIMES_ROMAN, 12)));
        celulaCpf.setHorizontalAlignment(Element.ALIGN_CENTER);
        PdfPCell celulaTelefone = new PdfPCell(new Phrase("Telefone", new Font(FontFamily.TIMES_ROMAN, 12)));
        celulaTelefone.setHorizontalAlignment(Element.ALIGN_CENTER);
        PdfPCell celulaEmail = new PdfPCell(new Phrase("E-mail", new Font(FontFamily.TIMES_ROMAN, 12)));
        celulaEmail.setHorizontalAlignment(Element.ALIGN_CENTER);

        doc.open();

        doc.add(tabela);
        doc.add(p);
        table.addCell(celulaNome);
        table.addCell(celulaCpf);
        table.addCell(celulaTelefone);
        table.addCell(celulaEmail);

        for (Aluno aluno : alunos) {
            celulaNome = new PdfPCell(new Phrase(aluno.getNome(), new Font(FontFamily.TIMES_ROMAN, 12)));
            celulaCpf = new PdfPCell(new Phrase(aluno.getCpf(), new Font(FontFamily.TIMES_ROMAN, 12)));
            celulaTelefone = new PdfPCell(new Phrase(aluno.getContato().getTelefone(), new Font(FontFamily.TIMES_ROMAN, 12)));
            celulaEmail = new PdfPCell(new Phrase(aluno.getContato().getEmail(), new Font(FontFamily.TIMES_ROMAN, 12)));

            table.addCell(celulaNome);
            table.addCell(celulaCpf);
            table.addCell(celulaTelefone);
            table.addCell(celulaEmail);

        }
        doc.add(table);
        doc.close();
    }
}
