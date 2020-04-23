package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class UserService {



    @Autowired
    UserRepository userRepository;

    List<ProfileModel> GetUser() {

        List<ProfileModel> userlist = userRepository.findAll();
        return userlist;
        // System.out.println("employee" + employeelist.get(0).name);

    }

    void DeleteUser(ProfileModel user){

        userRepository.delete(user);
    }

    ProfileModel getprofile(int Id) {
        return userRepository.findById(Id).get(0);

    }

    ProfileModel  update(ProfileModel p){
return  userRepository.save(p);

    }

    ProfileModel save(ProfileModel p){

     return    userRepository.save(p);
    }

    ProfileModel CreateUser(List<String> user) {
        return   userRepository.save(new ProfileModel(Integer.parseInt(user.get(0)), user.get(1),user.get(2),user.get(3),user.get(4),user.get(5)));

    }

}
