package epita.service;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collections;
public class CloakConfiguration {
    static{
        System.setProperty("application.file","src/java/resources/application.properties");
    }

    private String username;
    private String fname;
    private String lname;
    private String email;

    public CloakConfiguration(String username, String fname, String lname, String email) {
        this.username = username;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }

    public void configuration(){
//        String serverUrl = Configuration.getInstance().getCloakURL();
//        String realm = Configuration.getInstance().getCloakrealm();
//        String clientId = Configuration.getInstance().getCloakclientID();
//        String clientSecret = Configuration.getInstance().getCloakClientSecret();

        String serverUrl = "http://localhost:8080/auth/";
        String realm = "netflix";
        String clientId = "netlfix";
        String clientSecret = "9gRn3IO1eabrl34HG8ECLtdq8PCjrJBu";
        String usernameClient = "fashan";
        String passwordClient = "Qwerty#123";


        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .grantType(OAuth2Constants.PASSWORD)
                .clientId(clientId)
                .username(usernameClient)
                .password(passwordClient)
                .resteasyClient(new ResteasyClientBuilder()
                        .connectionPoolSize(10).build())
                .build();

        // Define user
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(this.username);
        user.setFirstName(this.fname);
        user.setLastName(this.lname);
        user.setEmail(this.email);
        user.setAttributes(Collections.singletonMap("origin", Arrays.asList("demo")));

        // Get realm
        RealmResource realmResource = keycloak.realm(realm);
        UsersResource usersRessource = realmResource.users();

        // Create user (requires manage-users role)
        Response response = usersRessource.create(user);
//        System.out.printf("Repsonse: %s %s%n", response.getStatus(), response.getStatusInfo());
//        System.out.println(response.getLocation());
        String userId = CreatedResponseUtil.getCreatedId(response);

//        System.out.printf("User created with userId: %s%n", userId);

        // Define password credential
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue("Qwerty#123");

        UserResource userResource = usersRessource.get(userId);

        // Set password credential
        userResource.resetPassword(passwordCred);
    }

}
