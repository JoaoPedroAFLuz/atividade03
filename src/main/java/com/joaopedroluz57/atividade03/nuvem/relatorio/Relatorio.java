package com.joaopedroluz57.atividade03.nuvem.relatorio;

import com.joaopedroluz57.atividade03.modelo.pacote.Pacote;

import java.util.List;

public interface Relatorio {

    void gerarRelatorio(List<Pacote> pacotesEspeciaisEncontrados);

}
