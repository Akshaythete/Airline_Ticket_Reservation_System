package com.airline.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
	
    private int id;
	
	@NotEmpty
	@Size (min=6,message="name Should be min 6 character!!!")
	private String name;
	
	@Email
	@Size
	private String email;
	
	@NotEmpty
	@Size(min=6 ,max=12,message="Password should be min 6 character and max 12 character!!!!")
	private String password;
	
	@NotEmpty
	private String location;

}
