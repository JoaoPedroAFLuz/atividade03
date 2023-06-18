package com.joaopedroluz57.atividade03.encriptacao.encriptador;

import com.joaopedroluz57.atividade03.encriptacao.chaves.FalhaGeracaoDeChaves;
import com.joaopedroluz57.atividade03.encriptacao.chaves.GeradorDeChaves;

public abstract class Encriptador<G extends GeradorDeChaves> {
    
    protected G gerador = null;

    public Encriptador(G geradorDeChaves) {
        this.setGerador(geradorDeChaves);
    }

    public void setGerador(G gerador) {
        this.gerador = gerador;
    }

    public abstract String encriptar(String dados) throws FalhaGeracaoDeChaves, FalhaEncriptacao;

    public abstract String desencriptar(String encriptacao) throws FalhaEncriptacao;

}
