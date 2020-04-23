package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class MainController {





    boolean check[]=new boolean[1000];

    @Autowired

    UserService userService;

    @RequestMapping("/")
    public String Greeting() {


        return "Hamza is a good boy";
    }

    @GetMapping("/profile/{Id}")
    public List<String> GetUser(@PathVariable int Id) {
    List<String> list=new ArrayList<>();
        if(check[Id]==true){
ProfileModel p=userService.getprofile(Id);
            list.add("Id= " + p.id);
            list.add("Name= " + p.name);
            list.add("Gender= " + p.gender);
            list.add("Email= " + p.email);
            list.add("Password= " + p.password);
            list.add("DOB= " + p.dob);

        }

        else {
            list.add("user not logged in");

        }
        return list;
    }

    @PostMapping("/signup")
    public String CreateUser(@RequestBody List<ProfileModel> UserList) {
ProfileModel p=UserList.get(0);

        List<ProfileModel> userlist = userService.GetUser();
        boolean h=false;
        for (ProfileModel userModel : userlist) {

            if (userModel.email.compareTo(UserList.get(0).email) == 0 ) {
                h=true;
                //return "email already exists";
                //break;
            }
        }



if(p.email==null || p.password==null ||p.id==null){
    return "email,id and password are required";

}

else if(h==true){
    return "email already exists";

}



else {
    ProfileModel UserModel = userService.save(p);
    return "User " + UserModel.name + " is signed up";
}
  /*  if(!employeeList.isEmpty()){

        return "Employee " +employeeList.get(0)+ "is created";
    }

return null;
*/
    }


    @PostMapping("/login")
    public List<String> LoginUser(@RequestBody List<String> UserList) {


        List<ProfileModel> userlist = userService.GetUser();
// return employeelist;
        List<String> list = new ArrayList<>();
boolean h=false;
        for (ProfileModel userModel : userlist) {

            if (userModel.email.compareTo(UserList.get(0)) == 0 && userModel.password.compareTo(UserList.get(1)) == 0) {

                list.add("Id= " + userModel.id);
                list.add("Name= " + userModel.name);
                list.add("Gender= " + userModel.gender);
                list.add("Email= " + userModel.email);
                list.add("Password= " + userModel.password);
                list.add("DOB= " + userModel.dob);
                check[userModel.id]=true;
                h=true;
                break;
            }
        }

        if(h==false){

            list.add("user doesnt exist or incorrect credentials");
        }

        return list;


        //  ProfileModel UserModel=userService.CreateUser(UserList);
        // return "User " +UserModel.name+ " is signed up";

  /*  if(!employeeList.isEmpty()){

        return "Employee " +employeeList.get(0)+ "is created";
    }

return null;
*/
    }



    @PutMapping("login/changepass/{Id}")

    public String ChangePass(@PathVariable int Id, @Valid @RequestBody List<String> P)
    {
        String oldp=P.get(0);
        String newp=P.get(1);

        if(check[Id]==true){
            ProfileModel old=userService.getprofile(Id);
            if (oldp.compareTo(old.password)==0){
                if (newp!=null ){

                    if(newp.compareTo(oldp)!=0) {
                        old.password = newp;
                        userService.update(old);
                        return "password updated";
                    }

                    else {

                        return "new and old password cannot be same";
                    }
                }

                else{

                    return "new pass cannot be null";
                }

            }

            else{

                return "original pass not correct";
            }
        }

        else {

            return "user not logged in";
        }

    }



    @PutMapping("login/edit/{Id}")
    public List<String> editProfile(@PathVariable int Id, @Valid @RequestBody List<ProfileModel> P){
        List<String> list = new ArrayList<>();
if(check[Id]==true){

    ProfileModel old=userService.getprofile(Id);
  ProfileModel  edited=P.get(0);

    if(edited.name!=null){

        old.name=edited.name;
    }

    if(edited.dob!=null){
        old.dob=edited.dob;

    }

if(edited.id!=null){

   check [old.id]=false;
   old.id=edited.id;
   check[edited.id]=true;
}

if(edited.gender!=null){
   old.gender=edited.gender;

}

if (edited.email!=null){

    old.email=edited.email;
}
userService.update(old);
//userService.DeleteUser();
    ProfileModel k=userService.update(old);
    list.add("Id= " + old.id);
    list.add("Name= " + old.name);
    list.add("Gender= " + old.gender);
    list.add("Email= " + old.email);
    list.add("Password= " + old.password);
    list.add("DOB= " + old.dob);


}

else {
    list.add("user not logged in");
}

return list;

    }


    @PostMapping("/login/logout/{Id}")
    public String logout(@PathVariable int Id)
    {
        String s;
        if(check[Id]==true){

            check[Id]=false;
            s="user is logged out";
        }

        else{

            s="user is not logged in";

        }

        return s;
    }


    @DeleteMapping("login/delete/{Id}")
    public List<String> DeleteUser(@PathVariable int Id) {


        List<ProfileModel> userlist = userService.GetUser();
// return employeelist;
        List<String> list = new ArrayList<>();

  //      for (ProfileModel userModel : userlist) {
            if (check[Id] == true) {
                ProfileModel P=userService.getprofile(Id);
      //          if (userModel.email.compareTo(UserList.get(0)) == 0 && userModel.password.compareTo(UserList.get(1)) == 0) {
                    userService.DeleteUser(P);
                    list.add(P.name + "'s account is deleted");
                }
            else {

                list.add("User is not logged in");
            }

    //    }

        return list;


    }
}