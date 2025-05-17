package com.todo.todoapi.service;

import com.todo.todoapi.model.ToDo;
import com.todo.todoapi.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;

    public ToDo createToDo(ToDo toDo) {
        return toDoRepository.save(toDo);
    }

    public List<ToDo> getAllToDos() {
        return toDoRepository.findAll();
    }

    public Optional<ToDo> getToDoById(Long id) {
        return toDoRepository.findById(id);
    }

    public ToDo updateToDo(Long id, ToDo updatedToDo) {
        return toDoRepository.findById(id)
                .map(todo -> {
                    todo.setDescripcion(updatedToDo.getDescripcion());
                    todo.setFecha(updatedToDo.getFecha());
                    todo.setEstatus(updatedToDo.getEstatus());
                    return toDoRepository.save(todo);
                }).orElse(null);
    }

    public ToDo updateEstatus(Long id, String nuevoEstatus) {
        return toDoRepository.findById(id)
                .map(todo -> {
                    todo.setEstatus(nuevoEstatus);
                    return toDoRepository.save(todo);
                }).orElse(null);
    }

    public boolean deleteToDo(Long id) {
        if (toDoRepository.existsById(id)) {
            toDoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}