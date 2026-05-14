package com.practice;
class Student {

    private int id;
    private String name;
    private int age;
    private String department;

    private Student(StudentBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.age = builder.age;
        this.department = builder.department;
    }

    public void display() {
        System.out.println(id + " " + name + " " + age + " " + department);
    }
    
    public static class StudentBuilder {

        private int id;
        private String name;
        private int age;
        private String department;

        public StudentBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public StudentBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public StudentBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public StudentBuilder setDepartment(String department) {
            this.department = department;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }
}
public class BuilderClassExample {
	public static void main(String[] args) {

        Student s = new Student.StudentBuilder()
                        .setId(101)
                        .setName("Nitya")
                        .setAge(21)
                        .setDepartment("CSE")
                        .build();

        s.display();
    }
}
