package com.savings_app.Savings.Aplication.auth;


import com.savings_app.Savings.Aplication.entity.FinancialData;
import com.savings_app.Savings.Aplication.entity.Role;
import com.savings_app.Savings.Aplication.entity.User;
import com.savings_app.Savings.Aplication.repository.UserRepository;
import com.savings_app.Savings.Aplication.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        FinancialData financialData = new FinancialData();
        financialData.setAccountBalance(0.0);
        financialData.setMonthlyIncome(0.0);
        financialData.setMonthlyExpenses(0.0);
        financialData.setOtherFinancialInfo("");

        var user = User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .financialData(financialData)
                .build();

        var savedUser = repository.save(user);
        var jwtToken = jwtService.generateToken(savedUser);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userId(savedUser.getId())
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user = repository.findByUsername(request.getUsername())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userId(user.getId())
                .build();
    }

}