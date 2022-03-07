import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SeatingChart {

    // Map student list collum by collum to a 2d array of students starting with index 0 of the list in 0,0 of the chart;
    private Student[][] chart;

    public SeatingChart(List<Student> studentList, int rows, int cols) {
        chart = new Student[rows][cols];
        int row = 0;
        int col = 0;
        for (Student student : studentList) {
            chart[row][col] = student;
            col++;
            if (col == cols) {
                row++;
                col = 0;
            }
        }
    }


    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("John", 0));
        studentList.add(new Student("Jane", 1));
        studentList.add(new Student("Jack", 2));
        studentList.add(new Student("Jill", 3));

        SeatingChart chart = new SeatingChart(studentList, 2, 2);
        System.out.println(chart.chart[0][1].getName());
    }

}

