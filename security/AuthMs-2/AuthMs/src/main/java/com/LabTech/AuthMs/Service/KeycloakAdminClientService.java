package com.LabTech.AuthMs.Service;

import com.LabTech.AuthMs.Config.KeycloakProvider;
import com.LabTech.AuthMs.Controller.UserController;
import com.LabTech.AuthMs.Dto.Login;
import com.LabTech.AuthMs.Dto.UserRequest;
import com.LabTech.AuthMs.Models.Delivery;
import com.LabTech.AuthMs.Models.Laboratory;
import com.LabTech.AuthMs.Models.User;
import com.LabTech.AuthMs.Models.UserType;
import com.LabTech.AuthMs.Repository.DeliveryRepository;
import com.LabTech.AuthMs.Repository.LaboratoryRepository;
import com.LabTech.AuthMs.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import java.util.Collections;

@Service
@Slf4j
@RequiredArgsConstructor
public class KeycloakAdminClientService {

    public final static String realm = "labtech-realm";

    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepository ;

    private final LaboratoryRepository laboratoryRepository;

    private final DeliveryRepository deliveryRepository;

    private final KeycloakProvider keycloakProvider;

    public ResponseEntity<AccessTokenResponse> createKeycloakUser(UserRequest userDTO) {
        UsersResource usersResource = keycloakProvider.getInstance().realm(realm).users();
        CredentialRepresentation credentialRepresentation = createPasswordCredentials(userDTO.getPassword());

        System.out.printf("ma3loma"+ String.valueOf(userDTO));

        UserRepresentation user = new UserRepresentation();
        user.setUsername(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setCredentials(Collections.singletonList(credentialRepresentation));
        user.setEnabled(true);

        Response response = usersResource.create(user);

        log.info("the user  is Saved");
//response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1")
        String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
        //If you want to save the user to your other database, do it here, for example:
        User localUser = User.builder()
                .userId(userId)
                .email(userDTO.getEmail())
                .userName(userDTO.getUserName())
                .phoneNbr(userDTO.getPhoneNbr())
                .build();
        //    usersResource.get(userId).sendVerifyEmail();
        userRepository.save(localUser);
        if (userDTO.getUserType().equals(UserType.LABORATORY)){
            Laboratory laboratory = Laboratory.builder()
                    .laboratoryId(localUser)
                    .build();
            laboratoryRepository.save(laboratory) ;
        }
        if (userDTO.getUserType().equals(UserType.DELIVERY)){
            Delivery delivery = Delivery.builder()
                    .deliveryId(localUser)
                    .build();
            deliveryRepository.save(delivery) ;
        }


        Login login = Login.builder()
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();


        return loguser(login) ;
    }

    private static CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }

    public ResponseEntity<AccessTokenResponse> loguser(Login loginRequest) {
        Keycloak keycloak = keycloakProvider.newKeycloakBuilderWithPasswordCredentials(loginRequest.getEmail(), loginRequest.getPassword()).build();

        AccessTokenResponse accessTokenResponse = null;
        try {
            accessTokenResponse = keycloak.tokenManager().getAccessToken();
            return ResponseEntity.status(HttpStatus.OK).body(accessTokenResponse);
        } catch (BadRequestException ex) {
            LOG.warn("invalid account. User probably hasn't verified email.", ex);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(accessTokenResponse);
        }
    }
}
