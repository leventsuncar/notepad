package com.lsuncar.notepad.core.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter
{
	@Override
	protected void doFilterInternal ( @NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain )
			throws ServletException, IOException
	{
		final String authHeader = request.getHeader( HttpHeaders.AUTHORIZATION );
		final String jwt;
		final String username;
		if( isNull( authHeader ) || !authHeader.startsWith( "Bearer" ) )
		{
			filterChain.doFilter( request, response );
			return;
		}
		jwt = authHeader.substring( 7 ); //Bearer_ 7 chars
		//username =
	}
}
