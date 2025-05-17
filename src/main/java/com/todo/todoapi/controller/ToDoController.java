package com.todo.todoapi.controller;

import com.todo.todoapi.model.ToDo;
import com.todo.todoapi.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    // 1. POST
    @PostMapping
    public ResponseEntity<ToDo> createToDo(@RequestBody ToDo toDo) {
        if (toDo.getDescripcion() == null || toDo.getDescripcion().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(toDoService.createToDo(toDo));
    }

    // 2. GET
    @GetMapping
    public ResponseEntity<List<ToDo>> getAllToDos() {
        return ResponseEntity.ok(toDoService.getAllToDos());
    }

    // 3. PUT
    @PutMapping("/{id}")
    public ResponseEntity<ToDo> updateToDo(@PathVariable Long id, @RequestBody ToDo updatedToDo) {
        ToDo result = toDoService.updateToDo(id, updatedToDo);
        return (result != null) ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    // 4. PATCH
    @PatchMapping("/{id}/estatus")
    public ResponseEntity<ToDo> updateEstatus(@PathVariable Long id, @RequestBody String nuevoEstatus) {
        ToDo result = toDoService.updateEstatus(id, nuevoEstatus.replace("\"", ""));
        return (result != null) ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    // 5. DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToDo(@PathVariable Long id) {
        boolean deleted = toDoService.deleteToDo(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}