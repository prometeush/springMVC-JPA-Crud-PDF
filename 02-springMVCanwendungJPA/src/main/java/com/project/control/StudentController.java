package com.project.control;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lowagie.text.DocumentException;
import com.project.dao.StudentRepository;
import com.project.entity.PdfExport;
import com.project.entity.Student;

@Controller
public class StudentController {
	
	@Autowired
	private StudentRepository repository;
	
	@RequestMapping("/")
	public String indexPage(Model model) {
		return "home";
	}
	
	@RequestMapping("/home")
	public String homePage(Model model) {
		return "home";
	}
	
	@RequestMapping("/studentform")
	public String studentForm(Model model) {
		model.addAttribute("studentmodel", new Student());
		List<String>list1=Arrays.asList("Java","JavaFX","Spring Boot","Spring MVC","Angular 10","Javascript",
				"jQuery","JSP","JSF","Thymeleaf");
		model.addAttribute("proglist", list1);
		return "student-form";
	}
	@RequestMapping("/studentSave")
	public String studentSave(@ModelAttribute("studentmodel")Student student) {
		if(student.getId()!=null) {
			this.repository.save(student);
		}else {
			this.repository.save(student);
		}
		return "redirect:/studentlist";
	
	}
	
	@RequestMapping("/studentlist")
	public String studentList(Model model) {
		List<Student>list=this.repository.findAll();
		model.addAttribute("studentlist1", list);
		return "student-list";
	}
	
	@RequestMapping("/delete")
	public String studentDelete(@RequestParam("id")Integer id) {
		this.repository.deleteById(id);
		return "redirect:/studentlist";
	}
	@RequestMapping("/update")
	public String studentUpdate(@RequestParam("id")Integer id,Model model) {
		Student student=this.repository.findById(id).get();
		model.addAttribute("studentmodel", student);
		List<String>list1=Arrays.asList("Java","JavaFX","Spring Boot","Spring MVC","Angular 10","Javascript",
				"jQuery","JSP","JSF","Thymeleaf");
		model.addAttribute("proglist", list1);
		return "student-form";
	}
	
	@RequestMapping("/studentsearch")
	public String studentSearch(@RequestParam("keyword")String keyword,Model model) {
		List<Student> list2=this.repository.studentSearch(keyword);
		model.addAttribute("studentlist2", list2);
		return "student-search";
		
	}
	
	@RequestMapping("/exportpdf")
	public void studentPDF(HttpServletResponse response) throws DocumentException, IOException {
		
        response.setContentType("application/pdf");
		
		//DateFormat format=new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		//String currentDate=format.format(new Date());
		
		String headerKey="Content-Disposition";
		String headerValue="attachment; filename=student_"+".pdf";
		response.setHeader(headerKey, headerValue);
		
		List<Student> list4=this.repository.findAll();
		PdfExport export1=new PdfExport(list4);
		export1.exportData(response);
		
	}

}
