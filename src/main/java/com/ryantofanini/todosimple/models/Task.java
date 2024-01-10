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

package com.ryantofanini.todosimple.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = Task.TABLE_NAME)
public class Task {
    public static final String TABLE_NAME = "task";

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", unique = true)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false, updatable = false)
        private User user;

        @Column(name = "description", length = 255, nullable = false)
        @NotNull
        @NotEmpty
        @Size(min = 1, max = 255)
        private String description;


    public Task(){
    }
    public Task(Long id, User user, String description) {
        this.id = id;
        this.user = user;
        this.description = description;
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser(){
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Task id(Long id) {
        setId(id);
        return this;
    }

    public Task user(User user) {
        setUser(user);
        return this;
    }

    public Task description(String description) {
        setDescription(description);
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if(obj == null)
            return false;
        if (!(obj instanceof  Task))
            return false;
        Task other = (Task) obj;
        if (this.id == null)
            if(other.id != null)
                return false;
            else if (!this.id.equals(other.id))
                return false;
        return Objects.equals(this.id, other.id) && Objects.equals(this.user, other.user) && Objects.equals(this.description, other.description);

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }
    }

