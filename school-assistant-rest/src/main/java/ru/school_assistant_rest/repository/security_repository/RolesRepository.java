package ru.school_assistant_rest.repository.security_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.school_assistant_rest.model.user_model.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {

}
