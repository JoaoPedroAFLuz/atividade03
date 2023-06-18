package com.joaopedroluz57.atividade03.nuvem.relatorio;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.joaopedroluz57.atividade03.encriptacao.encriptador.FalhaEncriptacao;
import com.joaopedroluz57.atividade03.encriptacao.impl.EncriptadorImpl;
import com.joaopedroluz57.atividade03.modelo.pacote.Pacote;
import com.joaopedroluz57.atividade03.modelo.pacoteEncriptado.PacoteEncriptado;

import java.util.List;

public interface Relatorio {

    void gerarRelatorio(List<PacoteEncriptado> pacotesEspeciaisEncontrados) throws FalhaEncriptacao, JsonProcessingException;

}
