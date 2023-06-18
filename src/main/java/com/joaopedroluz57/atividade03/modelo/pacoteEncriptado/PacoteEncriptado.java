package com.joaopedroluz57.atividade03.modelo.pacoteEncriptado;

import com.joaopedroluz57.atividade03.encriptacao.impl.EncriptadorImpl;

public class PacoteEncriptado {

    String pacote;
    EncriptadorImpl encriptador;

    public PacoteEncriptado(String pacote, EncriptadorImpl encriptador) {
        this.pacote = pacote;
        this.encriptador = encriptador;
    }

    public String getPacote() {
        return pacote;
    }

    public EncriptadorImpl getEncriptador() {
        return encriptador;
    }

}
