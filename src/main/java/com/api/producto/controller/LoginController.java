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
	
	import com.api.producto.dto.LoginResponse;

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

	    @PostMapping("/login")
	    public LoginResponse login(@RequestBody Usuario loginData) {
	        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsernameAndPassword(
	                loginData.getUsername(), 
	                loginData.getPassword()
	        );

	        if (usuarioOpt.isPresent()) {
	            Usuario usuario = usuarioOpt.get();
	            List<MaterialDef> materiales = materialDefRepository.findByLocalAndAlmacen(
	                usuario.getLocal(), 
	                usuario.getAlmacen()
	            );
	            
	            return new LoginResponse(
	                "Login correcto",
	                usuario.getNombre_completo(),
	                usuario.getLocal(),
	                usuario.getAlmacen(),
	                materiales
	            );
	        } else {
	            return new LoginResponse(
	                "Usuario o contraseña incorrectos",
	                null,
	                null,
	                null,
	                null
	            );
	        }
	    }
	}