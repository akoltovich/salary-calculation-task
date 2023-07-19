package by.akoltovich.salarycalculationtask.service;

import by.akoltovich.salarycalculationtask.dto.EmployeeDto;
import by.akoltovich.salarycalculationtask.entity.Employee;
import by.akoltovich.salarycalculationtask.entity.Team;
import by.akoltovich.salarycalculationtask.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    private static final Team TEAM = new Team(1L, 500, new ArrayList<>());
    private static final Employee EMPLOYEE = Employee.builder()
            .employeeId(1L)
            .salary(20.0)
            .team(TEAM)
            .build();

    private static final EmployeeDto EMPLOYEE_DTO = EmployeeDto.builder()
            .withSalary(20.0)
            .withTeam(1)
            .build();


    private static final List<Employee> EMPLOYEES = new ArrayList<>();
    private static final List<EmployeeDto> EMPLOYEES_DTO = new ArrayList<>();

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private TeamService teamService;

    @InjectMocks
    private EmployeeService employeeService;

    @AfterEach
    void check() {
        verifyNoMoreInteractions(employeeRepository);
    }

    @Test
    void shouldGetAllEmployeesFromDatabase() {
        EMPLOYEES.add(EMPLOYEE);
        EMPLOYEES_DTO.add(EMPLOYEE_DTO);

        when(employeeRepository.findAll()).thenReturn(EMPLOYEES);

        List<EmployeeDto> allEmployees = employeeService.getAllEmployees();

        verify(employeeRepository).findAll();
        assertEquals(EMPLOYEES_DTO, allEmployees);
    }

    @Test
    void shouldAddEmployeeToDatabase() {
        ArgumentCaptor<Employee> employeeEntityCaptor = ArgumentCaptor.forClass(Employee.class);

        when(teamService.getIfExists(1L)).thenReturn(TEAM);

        employeeService.addEmployee(EMPLOYEE_DTO);

        verify(teamService).getIfExists(1L);
        verify(employeeRepository).save(employeeEntityCaptor.capture());
        Employee actualEmployeeEntity = employeeEntityCaptor.getValue();
        assertEquals(20.0, actualEmployeeEntity.getSalary());
        assertEquals(1, actualEmployeeEntity.getTeam().getTeamId());
    }

    @Test
    void shouldChangeEmployeeInformation() {
        when(employeeRepository.existsById(1L)).thenReturn(true);
        when(teamService.getIfExists(1L)).thenReturn(TEAM);

        employeeService.updateEmployee(EMPLOYEE_DTO, 1L);

        verify(teamService).getIfExists(1L);
        verify(employeeRepository).save(EMPLOYEE);
    }

    @Test
    void shouldDeleteEmployeeFromDatabase() {
        when(employeeRepository.existsById(1L)).thenReturn(true);

        employeeService.deleteEmployee(1L);

        verify(employeeRepository).deleteById(1L);
    }

    @Test
    void shouldGetEmployeeByEmployeeId() {
        when(employeeRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(EMPLOYEE));

        EmployeeDto employeeById = employeeService.getEmployeeById(1L);
        verify(employeeRepository).findById(1L);
        assertEquals(EMPLOYEE_DTO, employeeById);
    }
}
