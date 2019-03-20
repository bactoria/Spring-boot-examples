package me.bactoria._25_OAuth2.restController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class ResourceRestController {

    @GetMapping("/api/secret")
    public String secret(Principal principal) {
        return principal.getName();
    }

}
