package com.edmond.bank.api;

import com.edmond.bank.model.AccountTransfer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api", produces = MediaType.APPLICATION_JSON_VALUE)
// For simplicity of this sample, allow all origins. Real applications should configure CORS for their use case.
@CrossOrigin(origins = "*")
public class APIController {

    @GetMapping(value = "/public")
    public AccountTransfer publicEndpoint() {
        return AccountTransfer.builder().accountIdTo(1).accountIdFrom(2).amountToTransfer(100.0).build();
    }

    @GetMapping(value = "/private")
    public AccountTransfer privateEndpoint() {
        return AccountTransfer.builder().accountIdTo(1).accountIdFrom(2).amountToTransfer(100.0).build();
    }

    @GetMapping(value = "/private-scoped")
    public AccountTransfer privateScopedEndpoint() {
        return AccountTransfer.builder().accountIdTo(1).accountIdFrom(2).amountToTransfer(100.0).build();
    }
}