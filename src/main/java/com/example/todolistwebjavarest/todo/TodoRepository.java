package com.example.todolistwebjavarest.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
    @Query(value = """
                    SELECT t FROM Todo t
                    WHERE t.userId = ?1
                    ORDER BY t.id DESC
                    LIMIT 1""")
    Optional<Todo> findLastTodoByUserId(int user_id);
}
