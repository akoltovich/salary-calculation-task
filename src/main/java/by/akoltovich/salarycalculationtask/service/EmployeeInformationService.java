package by.akoltovich.salarycalculationtask.service;

import by.akoltovich.salarycalculationtask.dto.EmployeeInformationDto;
import by.akoltovich.salarycalculationtask.entity.EmployeeInformation;
import by.akoltovich.salarycalculationtask.exception.EmployeeInformationNotFoundException;
import by.akoltovich.salarycalculationtask.exception.EmployeesNotFoundException;
import by.akoltovich.salarycalculationtask.repository.EmployeeInformationRepository;
import by.akoltovich.salarycalculationtask.util.ConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EmployeeInformationService {

    private final EmployeeInformationRepository employeeInformationRepository;

    private final EmployeeService employeeService;

    public List<EmployeeInformation> getAllEmployeesInformation() {
        return employeeInformationRepository.findAll();
    }

    public EmployeeInformation getEmployeeInformationById(Long employeeInformationId) {
        return employeeInformationRepository.findById(employeeInformationId)
//                .map(this::fromEntityToDto)
                .orElseThrow(() -> new EmployeeInformationNotFoundException(employeeInformationId));
    }

    public void addEmployeeInformation(EmployeeInformationDto employeeInformation) {
        employeeInformationRepository.save(EmployeeInformation.builder().begin(employeeInformation.getBegin())
                .end(employeeInformation.getEnd())
                .duration((int) employeeInformation.getBegin().until(employeeInformation.getEnd(), ChronoUnit.MINUTES))
                .employee(employeeService.getIfExists(Long.valueOf(employeeInformation.getEmployee()))).build());
    }

    public void updateEmployeeInformation(EmployeeInformation employeeInformation, Long id) {
        if (employeeInformationRepository.existsById(id)) {
            employeeInformationRepository.save(EmployeeInformation.builder()
                    .id(id)
                    .begin(employeeInformation.getBegin())
                    .end(employeeInformation.getEnd())
                    .duration((int) employeeInformation.getBegin().until(
                            employeeInformation.getEnd(), ChronoUnit.MINUTES))
                    .build());
        } else {
            throw new EmployeeInformationNotFoundException(id);
        }
    }

    public void deleteEmployeeInformation(Long employeeId) {
        if (!employeeInformationRepository.existsById(employeeId)) {
            throw new EmployeeInformationNotFoundException(employeeId);
        } else {
            employeeInformationRepository.deleteById(employeeId);
        }
    }

    public List<EmployeeInformationDto> getByPeriod(String period, Integer teamId) {
        List<EmployeeInformation> informations = employeeInformationRepository.findInformationByPeriod(period, teamId);
        return informations.stream().map(
                ConvertUtil::buildEmployeeInformationDtoFromEmployeeInformation).collect(Collectors.toList());
    }


    public List<EmployeeInformation> getEmployeeInformationByEmployeeId(Long employeeId) {
        return employeeInformationRepository.getEmployeeInformationByEmployee(employeeService.getIfExists(employeeId));
    }
}
