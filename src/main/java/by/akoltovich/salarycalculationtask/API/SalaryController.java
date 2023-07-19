package by.akoltovich.salarycalculationtask.API;

import by.akoltovich.salarycalculationtask.dto.EmployeeDto;
import by.akoltovich.salarycalculationtask.service.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/salary")
@RequiredArgsConstructor
@Validated
public class SalaryController {

    private final SalaryService service;

    @GetMapping(path = "/period")
    public List<EmployeeDto> calculateSalaryForPeriod(@RequestParam String date, @RequestParam Integer teamId) {
        return service.calculateSalary(date.replace("-", ""), teamId);
    }
}
