package com.nscc.webapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
@RequestMapping("/healthz")
public class HealthCheckController {
	
	private final DataSource dataSource;
	
	public HealthCheckController(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@GetMapping
	public ResponseEntity<Void> healthCheck(HttpServletRequest request) {
		if(request.getContentLength() > 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		try(Connection connection = dataSource.getConnection()) {
			return ResponseEntity.status(HttpStatus.OK)
					.header("Cache-Control", "no-cache, no-store, must-revalidate")
					.header("Pragma", "no-cache")
					.header("X-Content-Type-Options", "nosniff")
					.build();
			} catch(SQLException e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}
	}
	
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
	public ResponseEntity<Void> methodNotAllowed() {
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
	}
	
}
