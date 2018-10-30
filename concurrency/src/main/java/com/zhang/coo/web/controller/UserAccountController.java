package com.zhang.coo.web.controller;

import com.zhang.coo.service.IAccountService;
import com.zhang.coo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by aa on 2018/10/21.
 */
@Controller
@RequestMapping("/user")
public class UserAccountController {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IUserService userService;

    @RequestMapping( value = "/addUser" ,method = RequestMethod.POST )
    @ResponseBody
    public Object addUser( String name , String password){

       try{
           Object result = userService.addUser( name , password );
           return result;
       }catch (Exception e){
           e.printStackTrace();
       }
        return null;
    }


    @RequestMapping( value = "/transfer" ,method = RequestMethod.POST )
    @ResponseBody
    public Object transfer( Integer fromId , Integer toId , Double amount){

        try{
            Object result = userService.transfer( fromId , toId ,amount);
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping( value = "/deleteAll" ,method = RequestMethod.GET )
    @ResponseBody
    public Object deleteAll(){

        try{
            Object result = userService.deleteAll();
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
