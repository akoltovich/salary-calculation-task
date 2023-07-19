package by.akoltovich.salarycalculationtask.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonDeserialize(builder = EmployeeDto.EmployeeDtoBuilder.class)
public class EmployeeDto {
    private Integer team;
    private Double salary;
}
