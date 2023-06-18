package com.joaopedroluz57.atividade03.nuvem.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.joaopedroluz57.atividade03.encriptacao.encriptador.FalhaEncriptacao;
import com.joaopedroluz57.atividade03.encriptacao.impl.EncriptadorImpl;
import com.joaopedroluz57.atividade03.modelo.pacote.Pacote;
import com.joaopedroluz57.atividade03.modelo.pacoteEncriptado.PacoteEncriptado;
import com.joaopedroluz57.atividade03.nuvem.relatorio.Relatorio;

import java.util.List;

public class RelatorioImpl implements Relatorio {

    /*
     * Geracao de relatorio
     * <p>
     * a complexidade e linear ja que nao possui loops alinhados.
     *
     */
    @Override
    public void gerarRelatorio(List<PacoteEncriptado> pacotesEspeciaisEncontrados) throws FalhaEncriptacao, JsonProcessingException {
        for (PacoteEncriptado pacote : pacotesEspeciaisEncontrados) {
            String pacoteDecriptado = pacote.getEncriptador().desencriptar(pacote.getPacote());

            System.out.println("\nDados dos pacotes especiais [nuvem]: " + pacote.getPacote());
            System.out.println("Pacote decriptado: " + pacoteDecriptado);
        }
    }

}
