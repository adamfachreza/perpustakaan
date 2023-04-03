package com.mandiri.perpustakaan.repository;

import com.mandiri.perpustakaan.entity.Book;
import com.mandiri.perpustakaan.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
  @Query("SELECT u FROM User u WHERE " +
      "(:name IS NULL OR u.name LIKE %:name%) AND " +
      "(:email IS NULL OR u.email LIKE %:email%) AND " +
      "(:address IS NULL OR u.address LIKE %:address%) AND " +
      "(:phoneNumber IS NULL OR u.phoneNumber LIKE %:phoneNumber%)")
  Page<User> searchByNameEmailAddressOrPhoneNumber(
      @Param("name") String name,
      @Param("email") String email,
      @Param("address") String address,
      @Param("phoneNumber") String phoneNumber,
      Pageable pageable);
}

