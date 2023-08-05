package com.empresa.auth;

import com.empresa.jwt.JwtService;
import com.empresa.user.Role;
import com.empresa.user.User;
import com.empresa.user.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/*
* El error NullPointerException que estás recibiendo indica que estás intentando invocar el método save en una instancia nula de userRepository. Esto significa que userRepository no se ha inicializado correctamente.
En tu clase AuthService, estás utilizando la anotación @RequiredArgsConstructor de Lombok, que genera un constructor con un argumento para cada campo final o anotado con @NonNull que no está inicializado. Sin embargo, en tu código, no has marcado ninguno de los campos como final o anotado con @NonNull, por lo que el constructor generado no tiene argumentos y no inicializa ninguno de los campos.
Para solucionar este problema, puedes agregar la anotación @Autowired a cada uno de los campos para que Spring los inyecte automáticamente, o puedes agregar la anotación @NonNull a cada uno de los campos para que Lombok genere un constructor con argumentos para cada uno de ellos y luego asegurarte de pasar las dependencias necesarias al crear una instancia de la clase AuthService.
Avísame si esto resuelve el problema. 😊
Mensaje recibido. El error `NullPointerException` que estás recibiendo indica que estás intentando invocar el método `save` en una instancia nula de `userRepository`. Esto significa que `userRepository` no se ha inicializado correctamente. En tu clase `AuthService`, estás utilizando la anotación `@RequiredArgsConstructor` de Lombok, que genera un constructor con un argumento para cada campo final o anotado con `@NonNull` que no está inicializado. Sin embargo, en tu código, no has marcado ninguno de los campos como final o anotado con `@NonNull`, por lo que el constructor generado no tiene argumentos y no inicializa ninguno de los campos. Para solucionar este problema, puedes agregar la anotación `@Autowired` a cada uno de los campos para que Spring los inyecte automáticamente, o puedes agregar la anotación `@NonNull` a cada uno de los campos para que Lombok genere un constructor con argumentos para cada uno de ellos y luego asegurarte de pasar las dependencias necesarias al crear una instancia de la clase `AuthService`. Avísame si esto resuelve el problema. 😊
y si le pongo final?
Sí, si agregas la palabra clave final a cada uno de los campos en tu clase AuthService, Lombok generará un constructor con un argumento para cada campo final que no esté inicializado. Luego, puedes pasar las dependencias necesarias al crear una instancia de la clase AuthService utilizando este constructor.
Aquí hay un ejemplo de cómo se vería tu clase AuthService después de agregar la palabra clave final a cada uno de los campos:*/


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder().token(token).build();
    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode( request.getPassword()))
                .firstname(request.getFirstname())
                .lastname(request.lastname)
                .country(request.getCountry())
                .role(Role.USER)
                .build();
        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
