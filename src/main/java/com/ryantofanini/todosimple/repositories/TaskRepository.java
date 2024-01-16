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

package com.ryantofanini.todosimple.repositories;

import com.ryantofanini.todosimple.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {


   // List<Task> findByUser_Id(Long id); // Spring Data JPA usa o nome da tabela e não o nome da classe

    //@Query(value = "SELECT t FROM Task t WHERE t.user.id = :id") // Query usa JPQL para fazer a consulta
    //List<Task> findByUserId(@Param("id") Long id); // JPQL usa o nome da classe e não o nome da tabela

    //@Query(value = "SELECT * FROM task t WHERE t.user_id = :id", nativeQuery = true) // SQL nativo
    //List<Task> findByUserId(@Param("id") Long id);
}

