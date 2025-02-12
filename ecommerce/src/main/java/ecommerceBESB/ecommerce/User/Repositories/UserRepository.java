package ecommerceBESB.ecommerce.User.Repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ecommerceBESB.ecommerce.User.User;

public interface UserRepository extends JpaRepository<User, UUID>{
    @Query(value = "SELECT * FROM Users", nativeQuery = true)
    List<User> findAllUsers();

    @Query(value = "SELECT * FROM Users WHERE email = :email", nativeQuery = true)
    Optional<User> findUserByEmail(String email);

    @Query(value = "SELECT * FROM Users WHERE id = :id", nativeQuery = true)
    Optional<User> findUserById(UUID id);
}

