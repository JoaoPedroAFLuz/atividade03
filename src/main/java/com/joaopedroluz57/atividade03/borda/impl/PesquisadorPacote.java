package com.joaopedroluz57.atividade03.borda.impl;

import com.joaopedroluz57.atividade03.modelo.container.Container;
import com.joaopedroluz57.atividade03.modelo.navio.Navio;
import com.joaopedroluz57.atividade03.modelo.pacote.Pacote;

import java.util.ArrayList;
import java.util.List;

public class PesquisadorPacote implements Runnable {

    private final Navio navio;
    private final List<Pacote> pacotesEspeciais;
    private final List<Pacote> pacotesEspeciaisEncontrados = new ArrayList<>();

    public PesquisadorPacote(Navio navio, List<Pacote> pacotesEspeciais) {
        this.navio = navio;
        this.pacotesEspeciais = pacotesEspeciais;
    }

    public List<Pacote> getPacotesEspeciaisEncontrados() {
        return pacotesEspeciaisEncontrados;
    }

    @Override
    public void run() {
        /*
         * procura pelos pacotes especiais dentro da lista de pacotes de cada um dos containers
         *
         * a complexidade eh cubica, O(N^3), pois existem 3 loops aninhados.
         * acredito que não tenha há uma situação que irá ocorrer forca bruta já que não há uma busca em sequencia
         */

        for (Container container : navio.getContainers()) {
            System.out.println("Procurando pacotes especiais no container: " + container.getId()
                    + " do navio com id: " + navio.getId());
            for (Pacote pacote : container.getPacotes()) {
                for (Pacote pacoteEspecial : pacotesEspeciais) {
                    if (pacote.equals(pacoteEspecial)) {
                        System.out.println("Pacote especial de id: " + pacote.getId() + " encontrado no container "
                                + container.getId() + " do navio: " + navio.getId());
                        pacotesEspeciaisEncontrados.add(pacote);
                    }
                }
            }

        }
    }

}
