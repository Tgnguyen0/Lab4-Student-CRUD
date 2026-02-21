package com.example.studentcrud.controller;

import com.example.studentcrud.model.Student;
import com.example.studentcrud.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/students")
public class StudentWebController {

    private final StudentService service;

    public StudentWebController(StudentService service) {
        this.service = service;
    }

    // Hiển thị danh sách
    @GetMapping
    public String listStudents(Model model) {
        // Model model là thùng chứa dữ liệu để gửi từ Controller sang file HTML Thymeleaf.
        // attributeName phải chính xác không thì lỗi
        // attributeValue lấy từ service
        model.addAttribute("students", service.getAll());
        return "students"; // Trả về mapping của students.html. nếu trả về sai tên hoặc không có thì báo lỗi
    }

    // Thêm học viên
    @GetMapping("/add")
    public String showAddForm(Model model) {
        // Thymeleaf form binding dữ liệu giữa controller và html
        model.addAttribute("student", new Student());
        return "add-student";
    }

    // Lưu dữ liệu
    // @ModelAttribute là Spring sẽ tự lấy dữ liệu từ form (name, email, age, id...) rồi gán vào object Student.
    @PostMapping("/save")
    public String saveStudent(@ModelAttribute Student student) {
        service.save(student);
        return "redirect:/web/students"; // chuyển hướng về lại trang chính
    }

    // Cập nhật học viên
    // PathVariable lấy id từ url, VD: http://localhost:8080/web/students/edit/1
    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable Integer id, Model model) {
        model.addAttribute("student", service.getById(id));
        return "edit-student";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/web/students";
    }

    // Tìm kiếm học viên
    // @RequestParam nhận id từ tham số query trên URL (sau dấu ?), VD: http://localhost:8080/web/students/search?id=3
    // Sự khác nhau giữa RequestParam và ModelAttribute
    // RequestParam dùng để lấy từng field riêng lẻ của form từ request.
    // ModelAttribute Dùng để lấy dữ liệu form và tự động map vào 1 object.
    @GetMapping("/search")
    public String searchStudentById(@RequestParam("id") Integer id, Model model) {

        Student student = service.getById(id);

        if (student != null) {
            // Chèn dữ liệu vào students
            model.addAttribute("students", java.util.List.of(student));
        } else {
            // Chèn mảng rổng
            model.addAttribute("students", java.util.List.of());
            // Chèn câu thông báo
            model.addAttribute("error", "Không tìm thấy sinh viên với ID = " + id);
        }

        return "students";
    }
}
