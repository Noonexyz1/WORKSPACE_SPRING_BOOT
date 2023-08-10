package com.diego.repository;

import com.diego.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserLoginRepository extends JpaRepository<Usuario, Long> {
    @Query(value = "SELECT * FROM usuario WHERE contrasena = ?1 AND correo = ?2 ", nativeQuery = true)
    Usuario findByPassAndEmail(String contrasena, String email);
}
