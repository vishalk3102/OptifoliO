package com.optifolio.services.Impl;

import com.optifolio.dto.JwtRequest;
import com.optifolio.dto.JwtResponse;
import com.optifolio.exceptions.BadCredentialsException;
import com.optifolio.models.User;
import com.optifolio.repositories.UserRepository;
import com.optifolio.security.JwtHelper;
import com.optifolio.security.TokenBlacklist;
import com.optifolio.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private  final JwtHelper jwtHelper;

    private final TokenBlacklist tokenBlacklist;

    private UserRepository usersRepository;

    public JwtResponse generateAndAuthenticateToken(JwtRequest jwtRequest) throws BadCredentialsException {
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword());
        Authentication authentication=authenticationManager.authenticate(authenticationToken);

        if(authentication==null || !authentication.isAuthenticated())
        {
            throw new BadCredentialsException();
        }

        //Set the authentication in security context
        SecurityContextHolder.getContext().setAuthentication(authentication);


        // Fetch user details from the database based on the username from authentication
        UserDetails userDetails= (UserDetails) authentication.getPrincipal();
        User user= usersRepository.findByUserEmailId(userDetails.getUsername());

        if(user==null)
        {
            throw new BadCredentialsException();
        }

        //Fetch user roles and permission associated with authenticated users.
        String userId=user.getUserId();

        //Generate JWT after successful LDAP authentication
        String token=this.jwtHelper.generateToken((UserDetails) authentication.getPrincipal(),userId);
        JwtResponse response=JwtResponse.builder().jwtToken(token).username(jwtRequest.getUsername()).build();
        return response;
    }

    @Override
    public Boolean blacklistToken(String token) {
        if(token!=null && token.startsWith("Bearer "))
        {
            String jwtToken=token.substring(7);
            tokenBlacklist.addToBlacklistTokenList(jwtToken);
            return true;
        }
        return false;
    }
}
