package by.akoltovich.salarycalculationtask.exception;

//@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Employees not found")
public class EmployeesNotFoundException extends EntityException {

    public EmployeesNotFoundException(Long id) {
        super("Employee", id);
    }
}