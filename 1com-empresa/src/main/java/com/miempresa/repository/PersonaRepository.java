package com.miempresa.repository;

import com.miempresa.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    //Query Methods
    Persona findByPasswordAndUserName(String userName, String pass);
    //Password y UserName debe ser tal cual en la base de datos


    //JPQL
    @Query("SELECT p FROM Persona p WHERE p.userName = ?1 AND p.password = ?2")
    Persona findByPasswordAndUserNameJPQL(String userName, String pass);



    //NativeQuery
    @Query(value = "SELECT * FROM persona WHERE user_name = ?1 AND password = ?2", nativeQuery = true)
    Persona findByPasswordAndUserNameNativeQuery(String userName, String pass);

}
