package com.joaopedroluz57.atividade03.encriptacao.chaves;

public interface GeradorDeChaves {
    
    void iniciar(String caminhoVideo) throws FalhaGeracaoDeChaves;

    String gerarChave() throws FalhaGeracaoDeChaves;

    String getUltimaChave();

    void finalizar()  throws FalhaGeracaoDeChaves;

}
