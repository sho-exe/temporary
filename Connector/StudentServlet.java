/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Connector;

import Dao.StudentDAO;
import Model.Student;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sho
 */
@WebServlet("/students")
public class StudentServlet extends HttpServlet {

    StudentDAO studentDAO = new StudentDAO();


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StudentServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StudentServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
// CREATE (INTRO)
// UPDATE (INTRO)
// RETRIEVE
// DELETE
        try {
            switch (action) {
                case "new":
                    // Show HTML form to create a product
                    request.getRequestDispatcher("student-form.jsp").forward(request, response);
                    break;
                case "edit":
                    // GET BY ID: Fetch existing item and load the form with data
                    int id = Integer.parseInt(request.getParameter("id"));
                    Student student = studentDAO.getStudentById(id);
                    request.setAttribute("student", student);
                    request.getRequestDispatcher("student-form.jsp").forward(request, response);
                    break;
                case "delete":
                    // Handle DELETE directly from a link click
                    int deleteId = Integer.parseInt(request.getParameter("id"));
                    studentDAO.deleteStudent(deleteId);
                    response.sendRedirect("students"); // Reload list
                    break;
                default:
                    // Default to Listing all items
                    List<Student> listStudent = studentDAO.getAllStudents();
                    request.setAttribute("students", listStudent);
                    request.getRequestDispatcher("student-list.jsp").forward(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        
//        UPDATE
//        CREATE
        try {
            String name = request.getParameter("name");
            String age = request.getParameter("age");

            if ("update".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));

                Student student = new Student(id, name, age);
                studentDAO.updateStudent(student);
            } else {
                Student student = new Student(0, name, age);
                studentDAO.insertStudent(student);

            }

            response.sendRedirect("students");
        } catch (Exception e) {
            throw new ServletException(e);
        }

    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
