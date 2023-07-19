package by.akoltovich.salarycalculationtask.service;

import by.akoltovich.salarycalculationtask.dto.EmployeeDto;
import by.akoltovich.salarycalculationtask.entity.Employee;
import by.akoltovich.salarycalculationtask.exception.EmployeesNotFoundException;
import by.akoltovich.salarycalculationtask.repository.EmployeeRepository;
import by.akoltovich.salarycalculationtask.util.ConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final TeamService teamService;

    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeDto> employeeDtos;
        List<Employee> employees = employeeRepository.findAll();
        employeeDtos = employees.stream().map(ConvertUtil::buildEmployeeDtoFromEmployee).collect(Collectors.toList());
        return employeeDtos;
    }

    public EmployeeDto getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .map(ConvertUtil::buildEmployeeDtoFromEmployee)
                .orElseThrow(() -> new EmployeesNotFoundException(employeeId));
    }

    public void addEmployee(EmployeeDto employee) {
        employeeRepository.save(Employee.builder()
                .salary(employee.getSalary())
                .team(teamService.getIfExists(Long.valueOf(employee.getTeam())))
                .build());
    }

    public void updateEmployee(EmployeeDto employee, Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.save(Employee.builder()
                    .employeeId(id)
                    .salary(employee.getSalary())
                    .team(teamService.getIfExists(Long.valueOf(employee.getTeam())))
                    .build());
        } else {
            throw new EmployeesNotFoundException(id);
        }
    }

    public void deleteEmployee(Long employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new EmployeesNotFoundException(employeeId);
        } else {
            employeeRepository.deleteById(employeeId);
        }
    }

    public Employee getIfExists(Long employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeesNotFoundException(employeeId));
    }

    public List<Employee> findByTeamId(Long teamId) {
        return employeeRepository.findByTeam(teamService.getIfExists(teamId));
    }
}
