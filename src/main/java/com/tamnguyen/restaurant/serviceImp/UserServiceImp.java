package com.tamnguyen.restaurant.serviceImp;

import com.tamnguyen.restaurant.dto.UserDTO;
import com.tamnguyen.restaurant.entity.Role;
import com.tamnguyen.restaurant.entity.User;
import com.tamnguyen.restaurant.exceptionhandle.UserExistException;
import com.tamnguyen.restaurant.repository.UserRepository;
import com.tamnguyen.restaurant.service.RoleService;
import com.tamnguyen.restaurant.service.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Tam Nguyen
 */
@Service
public class UserServiceImp implements UserService {


    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImp(UserRepository userRepository, RoleService roleService, @Lazy BCryptPasswordEncoder encoder){
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.encoder = encoder;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail((username));
        if(user.isEmpty()){
            throw new UsernameNotFoundException("Invalid login or password");
        }else {
            return new org.springframework.security.core.userdetails.User(user.get().getEmail(),
                    user.get().getPassword(),mapRolesToAuthorities(user.get().getRoles()));
        }

    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role ->
                new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDTO findUserByEmail(String email) {
        Optional<User> userOptional = userRepository.findUserByEmail(email);
       if(userOptional.isPresent()){
           ModelMapper modelMapper = new ModelMapper();
           modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
           return modelMapper.map(userOptional.get(), UserDTO.class);
       }else {
           throw new UsernameNotFoundException("User not found for email" + email);
       }
    }


    @Override
    @Transactional
    public void saveUser(UserDTO userDTO) throws UserExistException {
        if(userRepository.findUserByEmail(userDTO.getEmail()).isPresent())
        {
            throw new UserExistException("User exist");
        }else {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            User user = modelMapper.map(userDTO, User.class);
            user.setRoles(Arrays.asList(roleService.findRoleByRoleName(userDTO.getRole())));
            user.setPassword(encoder.encode(userDTO.getPassword()));
            user.setEnabled("Y");
            userRepository.save(user);
        }

    }

}
