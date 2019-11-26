/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetoengenharia.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author henri
 */
@Entity
public class Antropometria {
    @Id
    private long id;
    private float peso;
    private float altura;
    private float cBranquial;
    private float cCintura;
    private float cQuadril;
    private float cPanturrilha;
    private float dcTricipal;
    private float dcBicipal;
    private float dcSubescapular;
    private float dcSuprailiaca;
    @ManyToOne(cascade = CascadeType.ALL)
    private Aluno aluno;

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getcBranquial() {
        return cBranquial;
    }

    public void setcBranquial(float cBranquial) {
        this.cBranquial = cBranquial;
    }

    public float getcCintura() {
        return cCintura;
    }

    public void setcCintura(float cCintura) {
        this.cCintura = cCintura;
    }

    public float getcQuadril() {
        return cQuadril;
    }

    public void setcQuadril(float cQuadril) {
        this.cQuadril = cQuadril;
    }

    public float getcPanturrilha() {
        return cPanturrilha;
    }

    public void setcPanturrilha(float cPanturrilha) {
        this.cPanturrilha = cPanturrilha;
    }

    public float getDcTricipal() {
        return dcTricipal;
    }

    public void setDcTricipal(float dcTricipal) {
        this.dcTricipal = dcTricipal;
    }

    public float getDcBicipal() {
        return dcBicipal;
    }

    public void setDcBicipal(float dcBicipal) {
        this.dcBicipal = dcBicipal;
    }

    public float getDcSubescapular() {
        return dcSubescapular;
    }

    public void setDcSubescapular(float dcSubescapular) {
        this.dcSubescapular = dcSubescapular;
    }

    public float getDcSuprailiaca() {
        return dcSuprailiaca;
    }

    public void setDcSuprailiaca(float dcSuprailiaca) {
        this.dcSuprailiaca = dcSuprailiaca;
    }

    @Override
    public String toString() {
        return "Antropometria{" + "id=" + id + ", peso=" + peso + ", altura=" + altura + ", cBranquial=" + cBranquial + ", cCintura=" + cCintura + ", cQuadril=" + cQuadril + ", cPanturrilha=" + cPanturrilha + ", dcTricipal=" + dcTricipal + ", dcBicipal=" + dcBicipal + ", dcSubescapular=" + dcSubescapular + ", dcSuprailiaca=" + dcSuprailiaca + ", aluno=" + aluno + '}';
    }

    
    
    
}
