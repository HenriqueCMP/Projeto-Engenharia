/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetoengenharia.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author henri
 */
@Entity
public class Anamnese {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String tempoAtividade;
    private String desconforto;
    private String objetivos;
    private boolean problemaCardiaco;
    private boolean diabetico;
    private boolean problemaRespiratorio;
    private boolean doresColuna;
    private boolean problemasOrtopedicos;
    private String medicamentos;
    private String alergias;
    private String opiniaoMedica;
    private String nomeContato;
    private String numeroContato;
    @ManyToOne
    private Aluno aluno;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTempoAtividade() {
        return tempoAtividade;
    }

    public void setTempoAtividade(String tempoAtividade) {
        this.tempoAtividade = tempoAtividade;
    }

    public String getDesconforto() {
        return desconforto;
    }

    public void setDesconforto(String desconforto) {
        this.desconforto = desconforto;
    }

    public String getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }

    public boolean isProblemaCardiaco() {
        return problemaCardiaco;
    }

    public void setProblemaCardiaco(boolean problemaCardiaco) {
        this.problemaCardiaco = problemaCardiaco;
    }

    public boolean isDiabetico() {
        return diabetico;
    }

    public void setDiabetico(boolean diabetico) {
        this.diabetico = diabetico;
    }

    public boolean isProblemaRespiratorio() {
        return problemaRespiratorio;
    }

    public void setProblemaRespiratorio(boolean problemaRespiratorio) {
        this.problemaRespiratorio = problemaRespiratorio;
    }

    public boolean isDoresColuna() {
        return doresColuna;
    }

    public void setDoresColuna(boolean doresColuna) {
        this.doresColuna = doresColuna;
    }

    public boolean isProblemasOrtopedicos() {
        return problemasOrtopedicos;
    }

    public void setProblemasOrtopedicos(boolean problemasOrtopedicos) {
        this.problemasOrtopedicos = problemasOrtopedicos;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getOpiniaoMedica() {
        return opiniaoMedica;
    }

    public void setOpiniaoMedica(String opiniaoMedica) {
        this.opiniaoMedica = opiniaoMedica;
    }

    public String getNomeContato() {
        return nomeContato;
    }

    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }

    public String getNumeroContato() {
        return numeroContato;
    }

    public void setNumeroContato(String numeroContato) {
        this.numeroContato = numeroContato;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    @Override
    public String toString() {
        return "Anamnese{" + "id=" + id + ", tempoAtividade=" + tempoAtividade + ", desconforto=" + desconforto + ", objetivos=" + objetivos + ", problemaCardiaco=" + problemaCardiaco + ", diabetico=" + diabetico + ", problemaRespiratorio=" + problemaRespiratorio + ", doresColuna=" + doresColuna + ", problemasOrtopedicos=" + problemasOrtopedicos + ", medicamentos=" + medicamentos + ", alergias=" + alergias + ", opiniaoMedica=" + opiniaoMedica + ", nomeContato=" + nomeContato + ", numeroContato=" + numeroContato + ", aluno=" + aluno + '}';
    }
    
    
    
}
