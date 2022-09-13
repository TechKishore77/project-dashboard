package qa.ade.project_dashboard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import qa.ade.project_dashboard.auth.JwtTokenUtil;
import qa.ade.project_dashboard.exceptions.BadRequestException;
import qa.ade.project_dashboard.model.JwtRequest;
import qa.ade.project_dashboard.model.JwtTokenPair;
import qa.ade.project_dashboard.model.RefreshTokenRequest;
import qa.ade.project_dashboard.service.TokenService;
import qa.ade.project_dashboard.service.UserService;

@ComponentScan("qa.ade.project_dashboard")
@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping(value = "/auth/token")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userService
                .findByUsername(authenticationRequest.getUsername()).toUserDetails();
        JwtTokenPair tokenPair = jwtTokenUtil.generateAndSaveTokenPair(userDetails);
        if (tokenPair == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(tokenPair);
    }

    @PostMapping(value = "/auth/logout")
    public void expireToken(@RequestHeader("Authorization") String bearerToken) throws Exception {
        UserDetails userDetails = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            userDetails = (UserDetails) auth.getPrincipal();
        }
        if (userDetails != null && bearerToken.startsWith("Bearer ")) {
            String jwtToken = bearerToken.substring(7);
            tokenService.expireToken(jwtToken);
        }
    }

    @PostMapping(value = "/auth/refresh")
    public JwtTokenPair refreshToken(@RequestBody RefreshTokenRequest request) {
        String refreshToken = request.getRefreshToken();
        String username = jwtTokenUtil.getUsernameFromToken(refreshToken);
        UserDetails userDetails = this.userService.findByUsername(username).toUserDetails();
        if (jwtTokenUtil.validateToken(refreshToken, userDetails)) {
            Boolean isRefresh = jwtTokenUtil.getClaimFromToken(refreshToken, (claims -> {
                Object refresh = claims.getOrDefault("isRefresh", false);
                if (refresh instanceof Boolean) return (Boolean) refresh;
                return false;
            }));
            if (isRefresh)
                return jwtTokenUtil.generateAndSaveTokenPair(userDetails);
            throw new BadRequestException("Invalid refresh token");
        }
        throw new BadRequestException("Invalid refresh token. Refresh failed.");
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
