package ComparatorEx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ComparatorEx {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = "";

        // 학생 정보를 저장할 배열 생성
        Student[] students = new Student[4];

        // 각 배열에 학생 정보 저장
        students[0] = new Student(202234001, "김", "AI", 4);
        students[1] = new Student(202234002, "이", "Computer Science", 3);
        students[2] = new Student(202234003, "박", "Software engineering", 2);
        students[3] = new Student(202234004, "최", "Information and Communication Engineering", 1);
        
        System.out.println("정렬기준을 입력해주세요.");
        System.out.println("학번 : 0  이름 : 1  학과 : 2 학년 : 3");

        // try-catch 문으로 EOF 예외 처리 발생시, 에러 출력 없이 종료.
        try {
            // EOF 예외 처리로 엔터시, 종료
            while ((input = br.readLine()) != null) {
                // 정렬 기준 입력
                int sortBy = Integer.parseInt(input);

                // 정렬기준 범위 벗어 났을 시, 다시 입력.
                if (sortBy < 0 || 3 < sortBy) {
                    System.out.println("0 ~ 3을 입력해주세요.");
                    continue;
                }

                // Comparator 구현
                Comparator<Student> comparator = new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        switch (sortBy) {
                            case 0: // 학번
                                return o1.studentId - o2.studentId;
                            case 1: // 이름
                                return o1.name.compareTo(o2.name);
                            case 2: // 학과
                                return o1.department.compareTo(o2.department);
                            case 3: // 학년
                                return o1.grade - o2.grade;
                            default:
                                return 0;
                        }
                    }
                };

                // 정렬
                Arrays.sort(students, comparator);


                // 정렬 결과 출력
                for (Student student : students) {
                    System.out.println(student.studentId + " " + student.name + " " + student.department + " " + student.grade);
                }

                System.out.println("정렬기준을 입력해주세요.");
                System.out.println("학번 : 0  이름 : 1  학과 : 2 학년 : 3");
            }
        } catch (NumberFormatException e) {
            System.out.print("");
        }
    }
}

class Student {

    int studentId;
    String name;
    String department;
    int grade;

    public Student(int studentId, String name, String department, int grade) {
        this.studentId = studentId;
        this.name = name;
        this.department = department;
        this.grade = grade;
    }
}
