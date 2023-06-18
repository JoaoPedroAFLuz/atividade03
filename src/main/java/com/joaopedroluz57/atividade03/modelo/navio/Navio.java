package com.joaopedroluz57.atividade03.modelo.navio;

import com.joaopedroluz57.atividade03.modelo.container.Container;

import java.util.List;
import java.util.UUID;

public class Navio {

    private UUID id;
    private List<Container> containers;

    public Navio(List<Container> containers) {
        this.id = UUID.randomUUID();
        this.containers = containers;
    }

    public UUID getId() {
        return id;
    }

    public List<Container> getContainers() {
        return containers;
    }

}
