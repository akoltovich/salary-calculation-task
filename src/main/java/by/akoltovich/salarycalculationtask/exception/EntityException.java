package by.akoltovich.salarycalculationtask.exception;

public class EntityException extends RuntimeException {

    public EntityException(String entityName ,Long id) {
        super(entityName + " with id " + id + " not found.");
    }
}
