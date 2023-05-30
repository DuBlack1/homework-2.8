package com.example.homework28.Service;

import com.example.homework28.Class.Employee;
import com.example.homework28.Exception.EmployeeAlreadyAddedException;
import com.example.homework28.Exception.EmployeeNotFoundException;
import com.example.homework28.Exception.IncorrectFirstNameException;
import com.example.homework28.Exception.IncorrectLastNameException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class EmployeeServiceTest {
    private final EmployeeService employeeService = new EmployeeServiceImpl(new ValidatorService());

    public static Stream<Arguments> params(){
        return Stream.of(
                Arguments.of("Екатерина", "Мозаева", 5, 17_000),
                Arguments.of("Пётр", "Волков", 5, 16_000),
                Arguments.of("Ксения", "Зайцева", 2, 19_000)
        );
    }


    @ParameterizedTest
    @MethodSource("params")
    void addNewEmployeeTest(String name,
                            String surname,
                            int department,
                            double salary) {
        Employee expected = new Employee(name, surname, department, salary);
        assertThat(employeeService.getAll()).isEmpty();
        employeeService.addNewEmployee(name, surname, department,salary);
        assertThat(employeeService.getAll())
                .hasSize(1)
                .containsExactly(expected);
        assertThat(employeeService.findEmployee(expected.getFirstname(), expected.getLastName()))
                .isNotNull()
                .isEqualTo(expected);
        assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(()-> employeeService.addNewEmployee(name,surname, department,salary));
    }

    @Test
    public void addNewEmployeeNegativeTest(){
        assertThatExceptionOfType(IncorrectFirstNameException.class)
                .isThrownBy(() -> employeeService.addNewEmployee("Ivan#", "Ivanov", 1,40000));

        assertThatExceptionOfType(IncorrectLastNameException.class)
                .isThrownBy(() -> employeeService.addNewEmployee("Petr", "Petrov!",2,55000));

        assertThatExceptionOfType(IncorrectFirstNameException.class)
                .isThrownBy(() -> employeeService.addNewEmployee(null,"Vasileva",3,80000));
    }

    @ParameterizedTest
    @MethodSource("params")
    public void removeNegativeTest(String name,
                                   String surname,
                                   int department,
                                   double salary) {
        assertThat(employeeService.getAll()).isEmpty();
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.deleteEmployee("test", "test"));

        Employee expected = new Employee(name, surname, department, salary);
        employeeService.addNewEmployee(name, surname, department, salary);
        assertThat(employeeService.getAll())
                .hasSize(1)
                .containsExactly(expected);

        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.deleteEmployee("test", "test"));
    }

    @ParameterizedTest
    @MethodSource("params")
    public void removePositiveTest(String name,
                                   String surname,
                                   int department,
                                   double salary){
        assertThat(employeeService.getAll().isEmpty());
        Employee expected = new Employee(name, surname, department, salary);
        assertThat(employeeService.addNewEmployee(name, surname, department, salary)).isEqualTo(expected);

        assertThat(employeeService.getAll())
                .hasSize(1)
                .containsExactly(expected);
        assertThat(employeeService.deleteEmployee(name,surname)).isEqualTo(expected);
        assertThat(employeeService.getAll()).isEmpty();

    }

    @ParameterizedTest
    @MethodSource("params")
    public void findNegativeTest(String name,
                                 String surname,
                                 int department,
                                 double salary) {
        assertThat(employeeService.getAll()).isEmpty();
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()-> employeeService.findEmployee( "test", "test"));
        Employee expected = new Employee(name,surname,department,salary);
        employeeService.addNewEmployee(name, surname, department, salary);
        assertThat(employeeService.getAll())
                .hasSize(1)
                .containsExactly(expected);

        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()-> employeeService.findEmployee("test", "test"));
    }
//
    @ParameterizedTest
    @MethodSource("params")
    public void findPositiveTest( String name,
                                  String surname,
                                  int department,
                                  double salary) {
        assertThat(employeeService.getAll()).isEmpty();
        Employee expected = new Employee(name, surname, department,salary);
        employeeService.addNewEmployee(name, surname, department, salary);
        assertThat(employeeService.getAll())
                .hasSize(1)
                .containsExactly(expected);
        assertThat(employeeService.findEmployee(name, surname)).isEqualTo(expected);
    }
}