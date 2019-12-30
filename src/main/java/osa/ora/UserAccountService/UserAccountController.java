package osa.ora.UserAccountService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import osa.ora.beans.User;
import osa.ora.customer.exception.JsonMessage;
import osa.ora.customer.persistence.AccountPersistence;

@RestController
@RequestMapping("/api/v1")
public class UserAccountController {
    private final AccountPersistence accountPersistence = new AccountPersistence();

	public UserAccountController() {
		super();
	}

	@GetMapping("/accounts/all")
	public User[] getUserAllUsers() {
		System.out.println("Load all accounts..");
        User[] allAccounts=accountPersistence.findAll();
        for(User account:allAccounts){
            //remove the password
            account.setPassword("*******");
        }
        return allAccounts;
	}
	
	@GetMapping("/accounts/{id}")
	public User getUserById(@PathVariable(value = "id") long id) {
		User account = accountPersistence.findbyId(id);
        if (account != null) {
            System.out.println("Retireve account using: " + id);
            //remove the password
            account.setPassword("*******");
            return account;
        } else {
            throw null;
            
        }
	}
	@GetMapping("/accounts/login/{login}/{password}")
	public User loginUser(@PathVariable(value = "login") String login,@PathVariable(value = "password") String password) {
		return login(login,password);
	}
	@PostMapping("/accounts/login")
	public User loginUser(@RequestBody User user) {
		return login(user.getLogin(),user.getPassword());
	}	
	
    private User login(String login, String password){
        System.out.println("Login....");
        User account = accountPersistence.loginUser(login,password);
        if (account != null) {
            System.out.println("Retireve account using: " + login);
            //remove the password
            account.setPassword("*******");
            return account;
        } else {
            return null;
        }
    }
    @PostMapping("/accounts/add")
	public boolean addUser(@RequestBody User user) {
        JsonMessage jsonMessage = accountPersistence.save(user);
        if (jsonMessage.getType().equals("Success")) {
            System.out.println("Successfully added a new account");
            return true;
        } else {
            return false;
        }
	}
    @PostMapping("/accounts/{id}/update")
	public boolean updateUser(@PathVariable(value = "id") long id,@RequestBody User user) {
        user.setId(id);
        User account = accountPersistence.findbyId(user.getId());
        if (account != null) {
            JsonMessage jsm = accountPersistence.update(account);
            if (jsm.getType().equals("Success")) {
                System.out.println("Successfully updated account with id=" + id);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
	}
    @PostMapping("/accounts/{id}/remove")
	public boolean removeUser(@PathVariable(value = "id") long id,@RequestBody User user) {
        user.setId(id);
        User account = accountPersistence.findbyId(user.getId());
        if (account != null) {
            JsonMessage jsm = accountPersistence.delete(id);
            if (jsm.getType().equals("Success")) {
                System.out.println("Successfully updated account with id=" + id);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
	}
}
