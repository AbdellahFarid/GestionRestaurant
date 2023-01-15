package com.example.gestionrestaurant.repository;

import com.example.gestionrestaurant.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = "SELECT email FROM user WHERE email= :email",nativeQuery = true)
    List<String> checkUserEmail(@Param("email") String email);
    @Query(value = "SELECT password FROM user WHERE password= :password",nativeQuery = true)
    List<String> checkUserPassword(@Param("password") String email);

    @Query(value = "SELECT password FROM user WHERE email= :email",nativeQuery = true)
    String checkUserPasswordByEmail(@Param("email") String email);
    @Query(value = "SELECT * FROM user WHERE email= :email",nativeQuery = true)
    User getUserDetailByEmail(@Param("email") String email);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user(address,email,name,password) VALUES(:address,:email,:name,:password)",nativeQuery = true)
    int registerNewUser(@Param("address") String address,
                        @Param("email") String email,
                        @Param("name") String name,
                        @Param("password") String password

    );


}
