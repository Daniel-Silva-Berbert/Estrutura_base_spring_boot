package br.com.jupiter.crud.service.exception;

public class NameNotFoundException extends ApplicationException {
  public NameNotFoundException(String entityName, String nome) {
    super(entityName + " com nome '" + nome + "' não foi encontrado.");
  }
}
