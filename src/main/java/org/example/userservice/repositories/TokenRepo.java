package org.example.userservice.repositories;

import org.example.userservice.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository()
public interface TokenRepo extends JpaRepository<Token,Long> {
    Optional<Token> findByValueAndDeletedEquals(String value,boolean deleted);
}
