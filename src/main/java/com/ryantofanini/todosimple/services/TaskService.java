/*


 Copyright (c) 2024. [Acme Corp]

  Permission is hereby granted, free of charge, to any person
  obtaining a copy
  of this software and associated documentation files (the
  "Software"), to deal
  in the Software without restriction, including without limitatio
  the rights
  to use, copy, modify, merge, publish, distribute, sublicense
  and/or sell
  copies of the Software, and to permit persons to whom the
  Software is
  furnished to do so, subject to the following conditions:

  The above copyright notice and this permission notice shall be
  included in
  all copies or substantial portions of the Software.

 */

package com.ryantofanini.todosimple.services;

import com.ryantofanini.todosimple.models.Task;
import com.ryantofanini.todosimple.models.User;
import com.ryantofanini.todosimple.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public Task findById(Long id) {
        Optional<Task> task = this.taskRepository.findById(id);
        return task.orElseThrow(() -> new RuntimeException("Tarefa não encontrada! Id: " + id + ", Tipo: " + Task.class.getName())); // retorna a tarefa ou lança uma exceção
    }

    @Transactional
    public Task create(Task obj) {
        User user = (this.userService.findById(obj.getUser().getId()));
        obj.setId(null); // garante que a tarefa será salva e não atualizada
        obj.setUser(user);
        obj = this.taskRepository.save(obj);
        return obj;
    }

    @Transactional
    public Task update(Long id, Task obj) {
        Task newObj = findById(obj.getId());
        newObj.setDescription(obj.getDescription());
        return this.taskRepository.save(newObj);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível deletar pois há entidades relacionadas! Id: " + id + ", Tipo: " + Task.class.getName());
        }
    }
}