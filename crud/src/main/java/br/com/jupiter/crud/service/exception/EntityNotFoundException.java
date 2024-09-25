package br.com.jupiter.crud.service.exception;

public class EntityNotFoundException extends ApplicationException {
  
  public EntityNotFoundException(String entityName, Long id) {
    super(entityName + " com ID " + id + " não foi encontrado.");
  }
}