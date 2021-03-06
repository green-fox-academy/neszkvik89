package com.todo.todo2.controller;

import com.todo.todo2.model.Todo;
import com.todo.todo2.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TodoController {

  private com.todo.todo2.repository.iTodoRepository iTodoRepository;
  private TodoService todoService;
  private com.todo.todo2.repository.iAssigneeRepository iAssigneeRepository;

  @Autowired
  public TodoController(com.todo.todo2.repository.iTodoRepository iTodoRepository,
                        com.todo.todo2.repository.iAssigneeRepository iAssigneeRepository, TodoService iToDoService) {
    this.iTodoRepository = iTodoRepository;
    this.iAssigneeRepository = iAssigneeRepository;
    this.todoService = iToDoService;
  }

  @GetMapping("/todo/add")
  public String add() {
    return "add";
  }

  @PostMapping("/todo/add")
  public String addTodoToRepo(String todo, Model model) {
    iTodoRepository.save(new Todo(todo));
    model.addAttribute("todos", iTodoRepository.findAll());
    return "todolist";
  }

  @GetMapping(value = "/{id}/delete")
  public String delete(@PathVariable("id") long id, Model model) {
    if (id != 0) {
      iTodoRepository.deleteById(id);
      model.addAttribute("todos", iTodoRepository.findAll());
    }
    return "todolist";
  }

  @GetMapping(value = "/{id}/edit")
  public String edit(@PathVariable("id") long id, Model model) {
    model.addAttribute("todo", iTodoRepository.findById(id).orElseGet(null));
    model.addAttribute("assignees",iAssigneeRepository.findAll());
    return "edit";
  }

  @PostMapping(value = "/search")
  public String search(Model model, String text) {
    model.addAttribute("todos", todoService.scanTodoList(text));
    return "todolist";
  }

  @PostMapping(value = "/{id}/edit")
  public String editFields(@RequestParam(name = "todo", required = false) String todo,
                           @RequestParam(name = "urgent", required = false) String urgent,
                           @RequestParam(name = "done", required = false) String done,
                           @PathVariable("id") long id, Model model) {

    Todo myTodo = new Todo();
    myTodo.setTitle(todo);
    myTodo.setId(id);

    if ((urgent != null) && urgent.equals("urgent")) {
      myTodo.setUrgent(true);
    }
    if (done != null && done.equals("done")) {
      myTodo.setDone(true);
    }
    iTodoRepository.save(myTodo);
    model.addAttribute("todos", iTodoRepository.findAll());
    return "todolist";
  }

  @GetMapping(value = {"/", "/list", "/todo"})
  public String list(@RequestParam(name = "isActive", required = false) boolean isActive, Model model) {
    List<Todo> myTodos = new ArrayList<>();

    if (!isActive) {
      model.addAttribute("todos", iTodoRepository.findAll());
    } else {
      iTodoRepository.findAll().forEach(todo -> {
                if (!todo.isDone()) {
                  myTodos.add(todo);
                }
              }
      );
      model.addAttribute("todos", myTodos);
    }
    model.addAttribute("todos", iTodoRepository.findAll());
    return "todolist";
  }


}
