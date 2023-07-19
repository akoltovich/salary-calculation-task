package by.akoltovich.salarycalculationtask.exception;

public class EmployeeInformationNotFoundException extends EntityException{
    public EmployeeInformationNotFoundException(Long id) {
        super("Employee information", id);
    }
}
