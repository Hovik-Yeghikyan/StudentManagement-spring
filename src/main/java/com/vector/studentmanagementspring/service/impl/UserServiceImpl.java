package com.vector.studentmanagementspring.service.impl;

import com.vector.studentmanagementspring.entity.User;
import com.vector.studentmanagementspring.entity.UserType;
import com.vector.studentmanagementspring.repository.UserRepository;
import com.vector.studentmanagementspring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Value("${studentManagement.upload.path}")
    private String uploadPath;

    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user, MultipartFile multipartFile) throws IOException {
        String fileName;
        if (!multipartFile.isEmpty()) {
            fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File(uploadPath, fileName);
            multipartFile.transferTo(file);
            user.setImageName(fileName);
        }
        return userRepository.save(user);
    }
    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findByUserType(UserType userType) {
        return userRepository.findByUserType(userType);
    }

    @Override
    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }
}
