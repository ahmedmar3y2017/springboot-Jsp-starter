package bootjsp.demo;


import bootjsp.demo.Dao.studentRepo;
import bootjsp.demo.model.student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

//@RestController

@Controller
public class studentController {

    @Autowired
    studentRepo studentRepo;

    @RequestMapping(value = {"/", "/home"}, method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getMain() {
        ModelAndView modelAndView = new ModelAndView("index");
//        modelAndView.addObject("name", name);
        return modelAndView;


    }

    @RequestMapping(value = {"/addStudent"}, method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView AddStudent(student student) {
        ModelAndView modelAndView = new ModelAndView("index");
        // save into database
        studentRepo.save(student);

//        modelAndView.addObject("name", name);
        return modelAndView;


    }

    @RequestMapping(value = {"/getStudent"}, method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getStudent(@RequestParam("id") int id) {
        ModelAndView modelAndView = new ModelAndView("showStudent");
        // save into database
        student student = studentRepo.findByName("mohamed");
//        System.out.println(studentRepo.findByIdGreaterThan(1));

        studentRepo.findAllByAddressOrderByName("tanta").stream().forEach(ee -> {

            System.out.println(ee.getName());

        });

        modelAndView.addObject("student", student);

//        modelAndView.addObject("name", name);
        return modelAndView;


    }

    // rest Web Service
    // get student By Id
    @RequestMapping(value = "/getStudentRest/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getStudentRest(@PathVariable("id") int id) {
        // save into database
        student student = studentRepo.findOne(id);
        if (student == null) {
            return new ResponseEntity<String>("student Not Found !!",
                    HttpStatus.NOT_FOUND);

        } else {
            return new ResponseEntity<student>(student, HttpStatus.OK);

        }
    }

    @RequestMapping(value = "/getStudentRest", method = RequestMethod.GET)
    public ResponseEntity<?> getAllStudentRest() {
        // save into database
        List<student> students = (List<bootjsp.demo.model.student>) studentRepo.findAll();
        if (students.size() <= 0) {
            return new ResponseEntity<String>("students Not Found !!",
                    HttpStatus.NOT_FOUND);

        } else {
            return new ResponseEntity<List<student>>(students, HttpStatus.OK);

        }
    }


    @RequestMapping(value = {"object"}, method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getMainObject(student student) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("obj", student);
        return modelAndView;

    }


}
