package com.patrykstopyra.bmc2021demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SecretValidationController {
    private static final Logger logger = LoggerFactory.getLogger(SecretValidationController.class);

    private final static Map<String, String> secrets = Map.of(
            "logging", "logging-is-easy",
            "reading", "reading-logs-is-harder,-if-they're-not-managed-correctly");

    @GetMapping("/secrets")
    public ResponseEntity<String> getSecrets() {
        logger.debug("Returned names of available secrets.");
        return ResponseEntity.status(HttpStatus.OK).body(secrets.keySet().toString());
    }

    @PostMapping("/secrets/validations")
    public ResponseEntity<String> validateSecret(@RequestParam(value = "secretName") String secretName,
                                                 @RequestParam(value = "secretValue") String secretValue) {
        if (!secrets.containsKey(secretName)) {
            logger.warn("Trial of unknown secret validation: secretName={}, secretValue={}", secretName, secretValue);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Secret not found!\n");
        }
        if (!secrets.get(secretName).equals(secretValue)) {
            logger.warn("Secret validation failed: secretName={}, secretValue={}", secretName, secretValue);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not know the secret!\n");
        }
        logger.info("Secret validation successful: secretName={}, secretValue={}", secretName, secretValue);
        return ResponseEntity.status(HttpStatus.OK).body("Correct secret validation!\n");
    }
}
