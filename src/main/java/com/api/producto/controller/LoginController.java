	package com.api.producto.controller;
	
	import java.util.Optional;
	//aaaa
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.web.bind.annotation.CrossOrigin;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;
	
	import com.api.producto.entity.Usuario;
	import com.api.producto.repository.UsuarioRepository;
	import com.api.producto.security.JwtUtil;
	import com.api.producto.dto.LoginResponse;
	
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;


	import com.api.producto.entity.MaterialDef;

	import com.api.producto.repository.MaterialDefRepository;
	

	import java.util.List;


	@RestController
	@RequestMapping("/api")
	@CrossOrigin(origins = "*") 
	public class LoginController {

	    @Autowired
	    private UsuarioRepository usuarioRepository;
	    
	    @Autowired
	    private MaterialDefRepository materialDefRepository;
	    
	    @Autowired
	    private JwtUtil jwtUtil;

	    @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestBody Usuario loginData) {
	        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsernameAndPassword(
	                loginData.getUsername(), 
	                loginData.getPassword()
	        );

	        if (usuarioOpt.isPresent()) {
	            Usuario usuario = usuarioOpt.get();
	            String token = jwtUtil.generateToken(usuario.getUsername());

	            List<MaterialDef> materiales = materialDefRepository.findByLocalAndAlmacen(
	                    usuario.getLocal(),
	                    usuario.getAlmacen()
	            );

	            return ResponseEntity.ok(new LoginResponse(
	                "Login correcto",
	                usuario.getNombre_completo(),
	                usuario.getLocal(),
	                usuario.getAlmacen(),
	                materiales,
	                token // <-- NUEVO
	            ));
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
	                new LoginResponse("Usuario o contraseña incorrectos", null, null, null, null, null)
	            );
	        }
	    }
	}