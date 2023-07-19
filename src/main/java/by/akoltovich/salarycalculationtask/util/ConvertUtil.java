package by.akoltovich.salarycalculationtask.util;

import by.akoltovich.salarycalculationtask.dto.EmployeeDto;
import by.akoltovich.salarycalculationtask.dto.EmployeeInformationDto;
import by.akoltovich.salarycalculationtask.entity.Employee;
import by.akoltovich.salarycalculationtask.entity.EmployeeInformation;

public class ConvertUtil {

    public static EmployeeDto buildEmployeeDtoFromEmployee(Employee employee) {
        return EmployeeDto.builder()
                .withTeam(Math.toIntExact(employee.getTeam().getTeamId()))
                .withSalary(employee.getSalary())
                .build();
    }

    public static EmployeeInformationDto buildEmployeeInformationDtoFromEmployeeInformation(
            EmployeeInformation information) {
        return EmployeeInformationDto.builder()
                .withBegin(information.getBegin())
                .withEnd(information.getEnd())
                .withDuration(information.getDuration())
                .withEmployee(Math.toIntExact(information.getEmployee().getEmployeeId()))
                .build();
    }
}
