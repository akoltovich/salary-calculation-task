package by.akoltovich.salarycalculationtask.validation;

import by.akoltovich.salarycalculationtask.dto.EmployeeInformationDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.temporal.ChronoUnit;

public class BeginAndEndValidation implements ConstraintValidator<WorkHoursValidation, EmployeeInformationDto> {

    @Override
    public boolean isValid(EmployeeInformationDto employeeInformation,
                           ConstraintValidatorContext constraintValidatorContext) {
        return employeeInformation.getBegin().getYear() == employeeInformation.getEnd().getYear()
                && employeeInformation.getBegin().getMonth() == employeeInformation.getEnd().getMonth()
                && employeeInformation.getBegin().getDayOfMonth() == employeeInformation.getEnd().getDayOfMonth()
                && employeeInformation.getBegin().isBefore(employeeInformation.getEnd())
                && employeeInformation.getBegin().until(employeeInformation.getEnd(), ChronoUnit.MINUTES) > 60;
    }
}
