package com.taskmanager.taskmanager.specification;

import com.taskmanager.taskmanager.model.Todo;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class TodoSpecification {

    public static Specification<Todo> hasTitle(String title) {
        return (root,query,cb)->title == null ? null :
                cb.like(
                        cb.lower(root.get("title")),
                        "%" + title.toLowerCase() + "%"
                );
    }

    public static Specification<Todo> isCompleted(Boolean completed) {
        return (root, query, cb) ->
                completed == null ? null :
                        cb.equal(root.get("completed"), completed);
    }

    public static Specification<Todo> createdAfter(LocalDateTime from) {
        return (root, query, cb) ->
                from == null ? null :
                        cb.greaterThanOrEqualTo(root.get("createdAt"), from);
    }

    public static Specification<Todo> createdBefore(LocalDateTime to) {
        return (root, query, cb) ->
                to == null ? null :
                        cb.lessThanOrEqualTo(root.get("createdAt"), to);
    }
}
