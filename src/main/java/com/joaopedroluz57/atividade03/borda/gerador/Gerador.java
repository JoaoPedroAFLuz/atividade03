package com.joaopedroluz57.atividade03.borda.gerador;

import com.joaopedroluz57.atividade03.encriptacao.impl.GeradorDeChavesImpl;
import com.joaopedroluz57.atividade03.modelo.container.Container;
import com.joaopedroluz57.atividade03.modelo.navio.Navio;
import com.joaopedroluz57.atividade03.modelo.pacote.Pacote;

import java.util.List;

public interface Gerador {

    List<Pacote> gerarPacotes(Integer quantidade);

    List<Pacote> gerarPacotesEspeciais(Integer quantidade, List<Navio> navios);

    List<Navio> gerarNavio(Integer quantidade);

    List<Container> gerarContainers(Integer quantidade);

}
