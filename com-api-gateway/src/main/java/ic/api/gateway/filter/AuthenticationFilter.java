package ic.api.gateway.filter;

import ic.api.gateway.exception.CustomException;
import ic.api.gateway.exception.MissingMandatoryInfo;
import ic.api.gateway.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator routeValidator;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private JwtUtils jwtUtils;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (routeValidator.isSecured.test((ServerHttpRequest) exchange.getRequest())) {
                // header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new MissingMandatoryInfo("Missing Authorization Header.", "MISSING_AUTHORIZATION_HEADER", HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN);
                }
                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (Objects.nonNull(authHeader) && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7); // retrieving actual token
                }
                try {
                    // Calling Auth Service
                    //restTemplate.getForObject("http://AUTH-SERVICE/validate?token="+authHeader, String.class); // instead of creating network call and still compromising security, instead we can validate token here
                    jwtUtils.validateToken(authHeader);
                } catch (Exception e) {
                    System.out.print("Invalid Access!");
                    throw new CustomException("Invalid Token in Header.", "INVALID_AUTHORIZATION_HEADER", HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN);
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}
