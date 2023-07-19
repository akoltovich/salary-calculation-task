package by.akoltovich.salarycalculationtask.API;

import by.akoltovich.salarycalculationtask.dto.EmployeeDto;
import by.akoltovich.salarycalculationtask.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
@Validated
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping(path = "/id")
    public EmployeeDto getEmployeeById(@Positive @RequestParam Long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addEmployee(@RequestBody @Valid EmployeeDto employee) {
        employeeService.addEmployee(employee);
    }

    @PutMapping(path = "/{employeeId}")
    public void updateEmployee(@Valid @RequestBody EmployeeDto employee, @PathVariable Long employeeId) {
        employeeService.updateEmployee(employee, employeeId);
    }

    @DeleteMapping(path = "/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@Positive @PathVariable Long employeeId) {
        employeeService.deleteEmployee(employeeId);
    }
}
