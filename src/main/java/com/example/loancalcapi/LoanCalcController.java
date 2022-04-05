package com.example.loancalcapi;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LoanCalcController {

    @CrossOrigin
    @PostMapping("/loanCalc")
    public LoanRequest calcLoan(@RequestBody LoanRequest loanRequest){
        // hardcoded user list
        List<User> users = new ArrayList<>();
        users.add(new User("49002010965", 0));
        users.add(new User("49002010976", 100));
        users.add(new User("49002010987", 300));
        users.add(new User("49002010998", 1000));

        User user = users.stream()
                .filter(user1 -> loanRequest.getUserIdentity().equals(user1.getIdentityCode()))
                .findAny()
                .orElse(null);

        if (user == null){
            loanRequest.setMessage("No user with id '" + loanRequest.getUserIdentity() + "' found!");
            return loanRequest;
        }

        if (!(user.getCreditModifier() > 0)){
            loanRequest.setMessage("Debt! This user can not take a loan!");
            return loanRequest;
        }

        LoanCalcBrain calcBrain = new LoanCalcBrain(2000, 10000, 12, 60);

        try{
            return calcBrain.CalculateLoan(user.getCreditModifier(), loanRequest);
        }catch (LoanCalculationException e){
            loanRequest.setMessage(e.getMessage());
            return loanRequest;
        }
    }
}