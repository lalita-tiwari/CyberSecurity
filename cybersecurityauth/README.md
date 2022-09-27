This is a simple Oauth2 based Resource Server and client implementation for SpringbootX2 based Java Rest API.

1) For this assignment I'm using Keycloak which is an Open Source Identity and Access Management Tool.
2) This is a Springboot 2.7.4 based application, and I'm utilizing property based autoconfiguration feature of Java
3) The application itself will act as OAuth Client and Resource Server which communicate with Keycloak server using autoconfiguration and application.yml properties defining the Oauth2 endpoints.
4) For utilizing Oauth2 Client and Resource Server capabilities I'm using below dependencies in build.gradle:
````
   implementation 'org.springframework.boot:spring-boot-starter-oauth2-client:2.7.3'
   implementation 'org.springframework.boot:spring-boot-starter-oauth2-resource-server:2.7.3'
````
5) My application.yml defines below properties which will be used by autoconfiguration for authenticating the incoming token:
````
spring:
  security:
    oauth2:
      client:
        registration:
          keycloak:
            client_id: client1  # This is Client Id of the registered client in Keycloak
            client_secret: GbzzCITpQxWcoX008b4dOTuVKBpZCPNK  # This is Client Secret of the registered client in Keycloak
            provider: keycloak # Provider Name could be any text
            authorization-grant-type: authorization_code  # This grant type is specifically used for Bearer Token
            redirect-uri: http://localhost:8070/  # This is a default redirect URI which is used by Resource Server to redirect after successful login.
            scope:
             - openid   # For our assignment we're using openid scope
             - profile
             - roles
        provider:
          keycloak:
            authorization-uri: http://localhost:8080/realms/realm1/protocol/openid-connect/auth # Used by resource server for browser based login and redirect.
            token-uri: http://localhost:8080/realms/realm1/protocol/openid-connect/token # This endpoint will be used by user to obtain access token.
            user-info-uri: http://localhost:8080/realms/realm1/protocol/openid-connect/userinfo # This endpoint will be used to obtain user info based upon token supplied.
            user-name-attribute: preferred_username # Attribute which identifies user
            jwk-set-uri: http://localhost:8080/realms/realm1/protocol/openid-connect/certs # JWK cert endpoint
            issuer-uri: http://localhost:8080/realms/realm1 # Default issuer endpoint in our case we're using realm1
      resourceserver:
        jwt:
          jwk-set-uri: http://localhost:8080/realms/realm1/protocol/openid-connect/certs # Default issuer endpoint in our case we're using realm1, Resource Server this endpoint to validate and authenticate an incoming token.

````
6) This is a Springboot Rest application with main class CybersecurityauthApplication.class
7) Controller is a RestController, both endpoints "/" and "/welcome" are protected by Oauth security.
8) Auth is a Configuration class which dictates what security will be applied based upon application.yml and initializes the Oauth2Client and ResourceServer beans.

