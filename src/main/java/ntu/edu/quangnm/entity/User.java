package ntu.edu.quangnm.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
public class User implements UserDetails{
	 	
	 	private final Account account;
	 	private final Scientist scientist;
	 	private final Admin admin;

	 	public User(Account account, Scientist scientist) {
	        this.account = account;
	        this.scientist= scientist;
			this.admin = null;
	    }
	 	
	 	public User(Account account, Admin admin) {
	        this.account = account;
			this.scientist = null;
	        this.admin= admin;
	    }

	    public Account getAccount() {
	        return account;
	    }
	    public Scientist getScientist() {
	        return scientist;
	    }
	    public Admin getAdmin() {
	        return admin;
	    }

	    @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        return List.of(new SimpleGrantedAuthority("ROLE_" + account.getRole()));
	    }

	    @Override
	    public String getPassword() {
	        return account.getPassword(); 
	    }

	    @Override
	    public String getUsername() {
	        return account.getUsername();
	    }

	    @Override
	    public boolean isAccountNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	        return true;
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isEnabled() {
	        return true;
	    }

}
