package com.example.JPointV2.controller;
import com.example.JPointV2.model.Department;
import com.example.JPointV2.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/depart")
public class DepartmentController {
    private final DepartmentService service;

    @PostMapping("/create/new/departments")
    public ResponseEntity<Department> createDepartments(@RequestBody Department _department) {
        return _department == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(service.createDepartments(_department), HttpStatus.CREATED);
    }


    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getAll() {
        List<Department> _departments = new ArrayList<>(service.getAllDepartments());
        if (_departments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(_departments, HttpStatus.OK);
    }

    @GetMapping("/departments/{departmentId}")
    public ResponseEntity<Optional<Department>> getDepartmentById(@PathVariable("departmentId") Long departmentId) {
        return new ResponseEntity<>(service.getDepartmentId(departmentId), HttpStatus.OK);
    }

    @GetMapping("/search/name")
    public ResponseEntity<List<Department>> searchNames(@RequestParam String name) {
        return new ResponseEntity<>(service.getDepartmentsName(name), HttpStatus.OK);

    }

    @GetMapping("/search/start_name")
    public ResponseEntity<List<Department>> searchStartNames(@RequestParam String name) {
        return new ResponseEntity<>(service.startStartingWithNames(name), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public void update(@RequestBody Department department, @PathVariable("id") Long id) {
        service.updateDepartments(department, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        service.deleteId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
