package org.spring.mvc.employee.controller;

import jakarta.servlet.http.HttpSession;
import org.spring.mvc.employee.entity.Employee;
import org.spring.mvc.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

//    При обращении к /home нам создается список элементов Employee и передается в сессию в качестве аттрибута,
// еще нам откроется страница home.jsp, на которой этот аттрибут будет выводиться на саму страницу
    @RequestMapping("/home")
    public String home(HttpSession session){
        List<Employee> employeeList = employeeService.findAll();
        session.setAttribute("employeeList", employeeList);
        return "home";
    }

//    на странице home будет ссылка для перехода на этот метод, а метод вернет нам страницу new_employee.jsp
    @RequestMapping("/new")
    public String showEmployee(){
//        Employee employee = new Employee();Map<String, Object> model
//        model.put("employee", employee);
        return "new_employee";
    }

//    добавляем нового сотрудника. @ModelAttribute соотносит параметры из формы с сущностью и мы создаем заполненный экземпляр employee. HttpSession сохранит атрибут(сообщение о добавленном сотруднике) на всю сессию
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveEmployee(@ModelAttribute("employee") Employee employee, HttpSession session){
        employeeService.save(employee);
        session.setAttribute("msg", "Сотрудник добавлен успешно");
        return "redirect:/home";
    }

//    id мы подставляем в @RequestMapping с помощью аннотации @PathVariable это динамическая часть пути
    @RequestMapping("/edit/{id}")
    public String showEmployee(@PathVariable("id") int id, Model m){
        Employee employee = employeeService.getEmployeeById(id);
        m.addAttribute("editEmployee", employee);
        return "edit_employee";
    }

//    обновляем сотрудника. @ModelAttribute соотносит параметры из формы с сущностью и мы создаем заполненный экземпляр employee.
    @RequestMapping(value = "/updateEmployee", method = RequestMethod.POST)
    public String updateEmployee(@ModelAttribute("employee") Employee employee, @RequestParam("id") long id, HttpSession session){
        employeeService.update(employee, id);
        session.setAttribute("msg", "Сотрудник обновлен успешно");
        return "redirect:/home";
    }

    @RequestMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") long id, HttpSession session){
        employeeService.deleteEmployeeById(id);
        session.setAttribute("msg", "Сотрудник удален успешно");
        return "redirect:/home";
    }
}













