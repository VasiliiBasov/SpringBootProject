package com.vasilii.notificationhub.config;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "app.jwt")
@Validated
public record JwtProperties(
        @NotBlank String secret,
        @Min(60) long accessTtlSeconds,
        @Min(60) long refreshTtlSeconds,
        @NotBlank String issuer
) {}
