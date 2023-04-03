package com.mandiri.perpustakaan.service.user;


import com.mandiri.perpustakaan.entity.User;
import com.mandiri.perpustakaan.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

  UserRepository userRepository;

  @Override
  public List<User> getAllUser() {
   return userRepository.findAll();
  }

  @Override
  public User getUserById(String id) {
    return userRepository.findById(id).get();
  }

  @Override
  public User saveUser(User user) {
    return userRepository.save(user);
  }

  @Override
  public void delete(String id) {
    userRepository.deleteById(id);
  }


  @Override
  public User updateUser(User user) {
    if(userRepository.findById(user.getId()).isPresent()){

      return saveUser(user);
    }else{
      throw new EntityNotFoundException("User with id" + user.getId() +"not found");
    }
  }

  @Override
  public Page<User> searchByNameEmailAddressOrPhoneNumber(String name, String email, String address, String phoneNumber, Pageable pageable) {
    return userRepository.searchByNameEmailAddressOrPhoneNumber(name, email, address, phoneNumber, pageable);
  }

}
