package com.mandiri.perpustakaan.controller;

import com.mandiri.perpustakaan.entity.User;
import com.mandiri.perpustakaan.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "users")
@AllArgsConstructor
public class UserController {
  UserService userService;


  @GetMapping("/{id}")
  public User getUser(@PathVariable String id){
    return userService.getUserById(id);
  }

  @PostMapping
  public User createUser(@RequestBody User user) {
    return userService.saveUser(user);
  }

  @PutMapping("/{id}")
  public void updateUser(@PathVariable String id,@RequestBody User user){
    user.setId(id);
    User updateUser = userService.updateUser(user);
    userService.saveUser(user);
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable String id) {
    userService.delete(id);
  }

  @GetMapping
  public Page<User> getUserPerPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                   @RequestParam(name = "size", defaultValue = "3") Integer sizePerPage,
                                   @RequestParam(name = "name", defaultValue = "") String name,
                                   @RequestParam(name = "email", defaultValue = "") String email,
                                   @RequestParam(name = "address", defaultValue = "") String address,
                                   @RequestParam(name = "phone_number", defaultValue = "") String phoneNumber){
    Pageable pageable = PageRequest.of(page, sizePerPage);
    return userService.searchByNameEmailAddressOrPhoneNumber(name, email, address, phoneNumber, pageable);
  }

}
