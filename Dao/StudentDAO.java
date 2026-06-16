/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sho
 */
public class StudentDAO {
// Database connection details

    private String jdbcURL = "jdbc:mysql://localhost:3306/students_db";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    // Helper method to establish a connection
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";

        // Try-with-resources automatically closes Connection, PreparedStatement, and ResultSet
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql); ResultSet resultSet = preparedStatement.executeQuery()) {

            // Loop through the database rows
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String age = resultSet.getString("age");

                // Map database row to Java Object (Model)
                Student student = new Student(id, name, age);
                students.add(student);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    // 1. GET BY ID (Read a single record)
    public Student getStudentById(int id) {
        Student student = null;
        String sql = "SELECT * FROM students WHERE id = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String age = rs.getString("age");
                    student = new Student(id, name, age);
                } else {
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    // 2. INSERT (Create a new record)
    public boolean insertStudent(Student student) {
        String sql = "INSERT INTO students (name, age) VALUES (?, ?)";
        boolean rowInserted = false;

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setString(2, student.getAge());

            rowInserted = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowInserted;
    }

    // 3. UPDATE (Modify an existing record)
    public boolean updateStudent(Student student) {
        String sql = "UPDATE students SET name = ?, age = ? WHERE id = ?";
        boolean rowUpdated = false;

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setString(2, student.getAge());
            ps.setInt(3, student.getId());

            rowUpdated = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    // 4. DELETE (Remove a record)
    public boolean deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        boolean rowDeleted = false;

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            rowDeleted = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

}
