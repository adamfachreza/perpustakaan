package com.mandiri.perpustakaan.service.user;

import com.mandiri.perpustakaan.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserService {
  List<User> getAllUser();
  User getUserById(String id);
  User saveUser(User user);
  void delete(String id);
  User updateUser(User user);
  Page<User> searchByNameEmailAddressOrPhoneNumber(
      @Param("name") String name,
      @Param("email") String email,
      @Param("address") String address,
      @Param("phoneNumber") String phoneNumber,
      Pageable pageable);
}
