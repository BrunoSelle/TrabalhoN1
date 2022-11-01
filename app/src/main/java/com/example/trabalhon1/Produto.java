package com.example.trabalhon1;

public class Produto {

    public int id;
    public String nome;
    public int quantidade;
    public boolean urgente;

    public Produto() {

    }

    public Produto(String nome, int quantidade, boolean urgente) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.urgente = urgente;
    }

    public Produto(int id, String nome, int quantidade, boolean urgente) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.urgente = urgente;
    }
}