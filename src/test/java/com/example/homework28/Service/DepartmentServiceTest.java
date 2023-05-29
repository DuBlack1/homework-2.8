package com.example.homework28.Service;

import com.example.homework28.Class.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {
    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @BeforeEach()
    public void beforeEach () {
        List<Employee> employees = List.of(
                new Employee("Алла", "Нестерова", 1, 40000),
                new Employee("Иван", "Иванов", 1, 75000),
                new Employee("Петр", "Петров", 2, 80000),
                new Employee("Мария", "Кулькова", 2, 65000),
                new Employee("Александ", "Невский", 2, 90000)
        );
        when(employeeService.getAll()).thenReturn(employees);
    }

    @ParameterizedTest
    @MethodSource("employeeWithMaxSalaryParams")
    public void maxSalaryDepartmentPositiveTest(int departmentId, Employee expected) {
        assertThat(departmentService.employeeWithMaxSalaryByDepartment(departmentId)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("MaxSalaryParams")
    public void maxSalaryByDepartmentPositiveTest(int departmentId, double expected){
        assertThat(departmentService.maxSalaryByDepartment(departmentId)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("employeeWithMinSalaryParams")
    public void employeeMinSalaryPositiveTest(int departmentId, Employee expected) {
        assertThat(departmentService.employeeWithMinSalaryByDepartment(departmentId)).isEqualTo(expected);
    }
    @ParameterizedTest
    @MethodSource("MinSalaryParams")
    public void employeeMinSalaryPositiveTest(int departmentId, double expected) {
        assertThat(departmentService.minSalaryByDepartment(departmentId)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("employeesFromDepartmentParams")
    public void employeesByDepartmentPositiveTest(int departmentId, List<Employee> expected) {
        assertThat(departmentService.employeesByDepartment(departmentId)).containsExactlyElementsOf(expected);
    }

    @ParameterizedTest
    @MethodSource("employeesFromDepartmentParams")
    public void getAllEmployeesByDepartmentPositiveTest() {
        assertThat(departmentService.getAllEmployeesByDepartment()).containsAllEntriesOf(
                Map.of(
                        1, List.of(new Employee("Алла", "Нестерова", 1, 40000), new Employee("Иван", "Иванов", 1, 75000)),
                        2,List.of(new  Employee("Петр", "Петров", 2, 80000),new Employee("Мария", "Кулькова", 2, 65000), new Employee("Александ", "Невский", 2, 90000))
                )
        );
    }

    public static Stream<Arguments> employeeWithMaxSalaryParams() {
        return Stream.of(
                Arguments.of(1,new Employee("Иван", "Иванов", 1, 75000)),
                Arguments.of(2, new Employee("Александ", "Невский", 2, 90000))
        );
    }
    public static Stream<Arguments> MaxSalaryParams() {
        return Stream.of(
                Arguments.of(1, 75000),
                Arguments.of(2, 90000)
        );
    }
    public static Stream<Arguments> employeesFromDepartmentParams() {
        return Stream.of(
                Arguments.of(1, List.of(new Employee("Алла", "Нестерова", 1, 40000), new Employee("Иван", "Иванов", 1, 75000))),
                Arguments.of(2, List.of(new Employee("Петр", "Петров", 2, 80000), new Employee("Мария", "Кулькова", 2, 65000), new Employee("Александ", "Невский", 2, 90000))),
                Arguments.of(3, Collections.emptyList())
        );
    }

    public static Stream<Arguments> employeeWithMinSalaryParams(){
        return Stream.of(
                Arguments.of(1, new Employee("Алла", "Нестерова", 1, 40000)),
                Arguments.of(2, new Employee("Мария", "Кулькова", 2, 65000))
        );
    }
    public static Stream<Arguments> MinSalaryParams(){
        return Stream.of(
                Arguments.of(1, 40000),
                Arguments.of(2, 65000)
        );
    }

}