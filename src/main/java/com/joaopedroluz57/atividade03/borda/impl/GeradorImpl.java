package com.joaopedroluz57.atividade03.borda.impl;

import com.joaopedroluz57.atividade03.borda.gerador.Gerador;
import com.joaopedroluz57.atividade03.modelo.container.Container;
import com.joaopedroluz57.atividade03.modelo.navio.Navio;
import com.joaopedroluz57.atividade03.modelo.pacote.Pacote;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GeradorImpl implements Gerador {

    private final static Integer QUANTIDADE_DE_CONTAINERS_POR_NAVIO = 5;
    private final static Integer QUANTIDADE_DE_PACOTES_POR_CONTAINER = 5;


    /**
     * gera uma lista de pacotes.
     * <p>
     * a complexidade e linear ja que nao possui loops alinhados.
     *
     * @param quantidadeDePacotes que serao gerados.
     * @return lista com a quantidade de pacotes passadas no parametro.
     */
    public List<Pacote> gerarPacotes(Integer quantidadeDePacotes) {
        List<Pacote> pacotes = new ArrayList<>();

        Random random = new Random();
        List<String> cidades = Arrays.asList("São Paulo", "Pequim", "Vitória da Conquista");

        for (int i = 0; i < quantidadeDePacotes; i++) {
            int indexCidadeOrigem = random.nextInt(cidades.size());
            int indexCidadeDestino = random.nextInt(cidades.size());

            Pacote pacote = new Pacote(
                    UUID.randomUUID(), cidades.get(indexCidadeOrigem),
                    cidades.get(indexCidadeDestino)
            );

            pacotes.add(pacote);
        }

        return pacotes;
    }

    /**
     * gera uma lista de pacotes especiais.
     * <p>
     * a complexidade e linear ja que nao possui loops alinhados.
     *
     * @param quantidadeDePacotesEspeciais que serao gerados.
     * @return lista com a quantidade de pacotes especiais passadas no parametro.
     */
    public List<Pacote> gerarPacotesEspeciais(Integer quantidadeDePacotesEspeciais, List<Navio> navios) {
        List<Pacote> pacotesEspeciais = new ArrayList<>();
        List<Pacote> pacotesMonitorados = navios.stream()
                .map(Navio::getContainers)
                .flatMap(Collection::stream)
                .map(Container::getPacotes)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        for (int i = 0; i < quantidadeDePacotesEspeciais; i++) {
            Random random = new Random();

            int indexPacoteEspecial = random.nextInt(pacotesMonitorados.size() * 2);

            if (indexPacoteEspecial < pacotesMonitorados.size()) {

                pacotesEspeciais.add(pacotesMonitorados.get(indexPacoteEspecial));
            }
        }

        return pacotesEspeciais;
    }


    /**
     * gera uma lista de containers.
     * a complexidade e linear ja que nao possui loops alinhados.
     *
     * @param quantidadeDeContainers que serao gerados.
     * @return lista com a quantidade de containers passadas no parametro.
     */
    public List<Container> gerarContainers(Integer quantidadeDeContainers) {
        List<Container> containersMonitorados = new ArrayList<>();

        for (int i = 0; i < quantidadeDeContainers; i++) {
            Container container = new Container();

            List<Pacote> pacotes = gerarPacotes(QUANTIDADE_DE_PACOTES_POR_CONTAINER);

            container.setId(containersMonitorados.size() + 1);
            container.setPacotes(pacotes);

            containersMonitorados.add(container);
        }

        return containersMonitorados;
    }

    /*
     * Geracao de navio
     * <p>
     * a complexidade e linear ja que nao possui loops alinhados.
     *
     */
    @Override
    public List<Navio> gerarNavio(Integer quantidade) {
        return IntStream.rangeClosed(1, quantidade)
                .mapToObj(x -> new Navio(gerarContainers(QUANTIDADE_DE_CONTAINERS_POR_NAVIO)))
                .collect(Collectors.toList());
    }

}
