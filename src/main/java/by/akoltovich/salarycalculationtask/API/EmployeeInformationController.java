package by.akoltovich.salarycalculationtask.API;

import by.akoltovich.salarycalculationtask.dto.EmployeeInformationDto;
import by.akoltovich.salarycalculationtask.entity.EmployeeInformation;
import by.akoltovich.salarycalculationtask.service.EmployeeInformationService;
import by.akoltovich.salarycalculationtask.validation.WorkHoursValidation;
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
@RequestMapping("/employee-information")
@RequiredArgsConstructor
@Validated
public class EmployeeInformationController {

    private final EmployeeInformationService employeeInformationService;

    @GetMapping
    public List<EmployeeInformation> getAllEmployeesInformation() {
        return employeeInformationService.getAllEmployeesInformation();
    }

    @GetMapping(path = "/id")
    public EmployeeInformation getEmployeeInformationById(@Positive @RequestParam Long employeeId) {
        return employeeInformationService.getEmployeeInformationById(employeeId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addEmployeeInformation(@RequestBody @Valid @WorkHoursValidation EmployeeInformationDto employee) {
        employeeInformationService.addEmployeeInformation(employee);
    }

    @PutMapping(path = "/{employeeId}")
    public void updateEmployeeInformation(@Valid @RequestBody @WorkHoursValidation EmployeeInformation employee,
                               @PathVariable Long employeeId) {
        employeeInformationService.updateEmployeeInformation(employee, employeeId);
    }

    @DeleteMapping(path = "/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployeeInformation(@Positive @PathVariable Long employeeId) {
        employeeInformationService.deleteEmployeeInformation(employeeId);
    }
}