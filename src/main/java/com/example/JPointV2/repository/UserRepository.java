package com.example.JPointV2.repository;

import com.example.JPointV2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByFirstNameOrLastName(String _nameF, String _nameL);

    List<User> findByFirstNameIsStartingWith(String name);



//    List<Person> findByActive(boolean _isActive);
//
//    List<Person> findByDepartmentContaining(String _department);
}
