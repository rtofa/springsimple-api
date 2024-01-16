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

import com.ryantofanini.todosimple.models.User;
import com.ryantofanini.todosimple.repositories.TaskRepository;
import com.ryantofanini.todosimple.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.RuntimeErrorException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired // não se instancia a classe UserRepository, o Spring faz isso
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    public User findById(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException("Usuário não encontrado! Id: " + id + ", Tipo: " + User.class.getName())); // retorna o usuário ou lança uma exceção
    }

        @Transactional // transação para garantir que o usuário e as tarefas serão salvo no banco de dados
        public User create(User obj) {
        obj.setId(null); // garante que o usuário será salvo e não atualizado
        obj =  this.userRepository.save(obj);
        this.taskRepository.saveAll(obj.getTasks());
        return obj;
    }

    @Transactional
    public User update(Long id, User obj) {
        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível excluir, pois há entidades relacionadas! Id: " + id + ", Tipo: " + User.class.getName());
        }
            this.userRepository.deleteById(id);
    }
}
