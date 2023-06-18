package com.joaopedroluz57.atividade03.modelo.pacote;

import java.util.UUID;

public class Pacote {

    private UUID id;
    private String origem;
    private String destino;

    public Pacote(UUID id, String origem, String destino) {
        this.id = id;
        this.origem = origem;
        this.destino = destino;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "\nIdentificador: " + this.id + "\nOrigem: " + this.origem + "\nDestino: "
                + this.destino;
    }

}
