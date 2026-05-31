package model.classes;

import java.util.Date;

public class Feriado {

    public Feriado(Date dia, String descricao) {
        this.dia = dia;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Feriado{" + "dia=" + dia + ", descricao=" + descricao + '}';
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    private Date dia;
    private String descricao;
}
