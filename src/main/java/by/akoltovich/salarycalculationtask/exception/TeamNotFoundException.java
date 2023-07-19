package by.akoltovich.salarycalculationtask.exception;

public class TeamNotFoundException extends EntityException {
    public TeamNotFoundException(Long id) {
        super("Team", id);
    }
}
