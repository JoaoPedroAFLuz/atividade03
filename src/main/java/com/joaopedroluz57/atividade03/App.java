package com.joaopedroluz57.atividade03;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaopedroluz57.atividade03.borda.impl.GeradorImpl;
import com.joaopedroluz57.atividade03.borda.impl.PesquisadorPacote;
import com.joaopedroluz57.atividade03.encriptacao.chaves.FalhaGeracaoDeChaves;
import com.joaopedroluz57.atividade03.encriptacao.encriptador.FalhaEncriptacao;
import com.joaopedroluz57.atividade03.encriptacao.impl.EncriptadorImpl;
import com.joaopedroluz57.atividade03.encriptacao.impl.GeradorDeChavesImpl;
import com.joaopedroluz57.atividade03.modelo.navio.Navio;
import com.joaopedroluz57.atividade03.modelo.pacote.Pacote;
import com.joaopedroluz57.atividade03.modelo.pacoteEncriptado.PacoteEncriptado;
import com.joaopedroluz57.atividade03.nuvem.impl.RelatorioImpl;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static final String CAMINHO_VIDEO = String.join(
            System.getProperty("file.separator"),
            System.getProperty("user.dir"),
            "src",
            "main",
            "resources",
            "video.mp4"
    );
    private static final Integer QUANTIDADE_DE_NAVIOS = 10;
    private static final Integer QUANTIDADE_DE_PACOTES_ESPECIAIS = 15;

    private static final List<PesquisadorPacote> pesquisadoresPacote = new ArrayList<>();
    private static final List<Thread> executores = new ArrayList<>();

    /*
     * Inicia o processamento de pesquisa dos pacotes especiais
     * <p>
     * A complexidade eh O(N^4), pois existem 4 loops aninhados. Os demais estão no PesquisadorPacote
     *
     */
    public static void iniciarProcessamentoDePesquisa(List<Navio> navios, List<Pacote> pacotesEspeciais) {
        for (Navio navio : navios) {
            PesquisadorPacote pesquisador = new PesquisadorPacote(navio, pacotesEspeciais);
            Thread thread = new Thread(pesquisador);
            pesquisadoresPacote.add(pesquisador);
            executores.add(thread);
            thread.start();
        }
    }

    /*
     * Espera a finalização dos executores
     * <p>
     * a complexidade e linear ja que nao possui loops alinhados.
     *
     */
    public static void esperarFinalizacaoDosExecutores() throws InterruptedException {
        for (Thread executor : executores) {
            executor.join();
        }
    }

    public static List<PacoteEncriptado> encriptarPacotes(GeradorDeChavesImpl gerador) throws JsonProcessingException, FalhaEncriptacao, FalhaGeracaoDeChaves {
        List<PacoteEncriptado> pacotesEncriptados = new ArrayList<>();

        for (PesquisadorPacote pesquisadorPacote : pesquisadoresPacote) {
            for (Pacote pacote : pesquisadorPacote.getPacotesEspeciaisEncontrados()) {
                EncriptadorImpl encriptador = new EncriptadorImpl(gerador);
                PacoteEncriptado pacoteEncriptado = new PacoteEncriptado(encriptador.encriptar(Pacote.toJson(pacote)), encriptador);
                pacotesEncriptados.add(pacoteEncriptado);

                System.out.println("Dados dos pacotes especiais [borda]: " + pacoteEncriptado.getPacote());
            }
        }

        return pacotesEncriptados;
    }

    /*
     * Metodo principal
     * <p>
     * a complexidade e linear ja que nao possui loops alinhados.
     *
     */
    public static void main(String[] args) throws Exception {
        GeradorImpl gerador = new GeradorImpl();
        RelatorioImpl relatorio = new RelatorioImpl();

        GeradorDeChavesImpl geradorDeChaves = new GeradorDeChavesImpl();
        geradorDeChaves.iniciar(CAMINHO_VIDEO);

        List<Navio> naviosMonitorados = gerador.gerarNavio(QUANTIDADE_DE_NAVIOS);
        List<Pacote> pacotesEspeciais = gerador.gerarPacotesEspeciais(QUANTIDADE_DE_PACOTES_ESPECIAIS, naviosMonitorados);

        System.out.println("##### Iniciando pesquisadores de pacotes #####");
        iniciarProcessamentoDePesquisa(naviosMonitorados, pacotesEspeciais);
        esperarFinalizacaoDosExecutores();

        System.out.println("\n### Pacotes especiais encontrados ###");

        List<PacoteEncriptado> pacotesEncriptados = encriptarPacotes(geradorDeChaves);

        relatorio.gerarRelatorio(pacotesEncriptados);

        System.out.println("\n### Simulação finalizada ###");

        geradorDeChaves.finalizar();
    }

}
