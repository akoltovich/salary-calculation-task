package by.akoltovich.salarycalculationtask.service;

import by.akoltovich.salarycalculationtask.dto.EmployeeDto;
import by.akoltovich.salarycalculationtask.dto.EmployeeInformationDto;
import by.akoltovich.salarycalculationtask.entity.Employee;
import by.akoltovich.salarycalculationtask.entity.EmployeeInformation;
import by.akoltovich.salarycalculationtask.entity.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static by.akoltovich.salarycalculationtask.util.ConvertUtil.buildEmployeeDtoFromEmployee;

@RequiredArgsConstructor
@Service
public class SalaryService {

    private final EmployeeService employeeService;
    private final EmployeeInformationService employeeInformationService;
    private final TeamService teamService;

    public List<EmployeeDto> calculateSalary(String period, Integer teamId) {
        List<EmployeeInformationDto> informations = employeeInformationService.getByPeriod(period, teamId);
        int totalDuration = informations.stream().mapToInt(EmployeeInformationDto::getDuration).sum();
        Team team = teamService.getTeamById(Long.valueOf(teamId));
        double salaryPerMin = (double) team.getTotalAmount() / totalDuration;
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        List<Employee> employees = employeeService.findByTeamId(Long.valueOf(teamId));
        employees.forEach(employee -> {
            List<EmployeeInformation> informationList = employeeInformationService.getEmployeeInformationByEmployeeId(
                    employee.getEmployeeId());
            int currentEmployeeDuration = informationList.stream().mapToInt(EmployeeInformation::getDuration).sum();
            employee.setSalary(currentEmployeeDuration * salaryPerMin);
            employeeService.updateEmployee(buildEmployeeDtoFromEmployee(employee), employee.getEmployeeId());
            employeeDtos.add(buildEmployeeDtoFromEmployee(employee));
        });
        return employeeDtos;
    }
}
