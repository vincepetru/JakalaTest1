package com.Jakala.Test.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Jakala.Test.model.Contract;
import com.Jakala.Test.model.User;
import com.Jakala.Test.model.UserContract;
import com.Jakala.Test.repository.ContractRepository;
import com.Jakala.Test.repository.UserRepository;

@RestController
public class Controller
{
    @Autowired
    UserRepository userRepository;
    @Autowired
    ContractRepository contractRepository;

    @PostMapping("/addUserContracts/{id}")
    public ResponseEntity<String> addUserContracts
    (@PathVariable("id") int id, @RequestBody Contract contract) 
    {
        Optional<User> matchedUser = userRepository.findById(id);
        if (matchedUser.isEmpty())
           return new ResponseEntity<>("Utente non trovato", HttpStatus.BAD_REQUEST);

        contract.setUser(matchedUser.get());
        contractRepository.save(contract);

        return new ResponseEntity<String> ("contratto aggiunto con successo", HttpStatus.OK);
    }
    @GetMapping("/userlist")
    public Iterable<User> findAllUsers()
    {
        return userRepository.findAll();
    }
    @GetMapping("/contractlist")
    public Iterable<Contract> findAllContract()
    {
    	return contractRepository.findAll();
    }
    @GetMapping("/searchContracts")
    public ResponseEntity<List<Contract>> searchContracts(@RequestBody UserContract filter) 
    {
        List<Contract> contracts = contractRepository.searchContracts
            (
                filter.getName(),
                filter.getStartdate(),
                filter.getContracttype(),
                filter.getUsertype()
            );

        if (contracts.isEmpty()) 
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } 
        else 
        {
            return new ResponseEntity<>(contracts, HttpStatus.OK);
        }
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Contract>> getContractsByUserId(@PathVariable int userId) 
     {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) 
        {
            return ResponseEntity.notFound().build();
        }

        User user = userOptional.get();
        List<Contract> contracts = contractRepository.findByUser(user);

        return ResponseEntity.ok(contracts);
     }
}

