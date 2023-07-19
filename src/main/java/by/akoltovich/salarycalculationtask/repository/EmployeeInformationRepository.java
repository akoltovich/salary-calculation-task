package by.akoltovich.salarycalculationtask.repository;

import by.akoltovich.salarycalculationtask.entity.Employee;
import by.akoltovich.salarycalculationtask.entity.EmployeeInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeInformationRepository extends JpaRepository<EmployeeInformation, Long> {
    @Query(value =
            "select ei.id, ei.begin, ei.end, ei.duration, ei.employee_id from employeedb.employee_information ei, employeedb.employee e\n" +
                    " where extract(year_month from(ei.begin)) = ?1 and e.team_id = ?2 and e.employee_id = ei.employee_id",
            nativeQuery = true)
    List<EmployeeInformation> findInformationByPeriod(String period, Integer teamId);
    List<EmployeeInformation> getEmployeeInformationByEmployee(Employee employee);
}
