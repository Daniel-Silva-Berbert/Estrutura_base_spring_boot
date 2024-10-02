package br.com.jupiter.crud.entity;

public enum Permissao {
    ADMIN("admin"),
    USER("user"),
    CLIENT("client");

    private String permissao;

    Permissao(String permissao){
        this.permissao = permissao;
    }

    public String getPermissao(){
        return permissao;
    }

}
