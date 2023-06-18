package com.joaopedroluz57.atividade03.modelo.pacote;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    public static String toJson(Pacote pacote) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(pacote);
    }

    @Override
    public String toString() {
        return "\nIdentificador: " + this.id + "\nOrigem: " + this.origem + "\nDestino: "
                + this.destino;
    }

}
