package com.vector.studentmanagementspring.service;

import com.vector.studentmanagementspring.entity.User;
import com.vector.studentmanagementspring.entity.UserType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    User save(User user, MultipartFile multipartFile) throws IOException;

    void deleteById(int id);

    List<User> findByUserType(UserType userType);

    Optional<User> findById(int id);

}
