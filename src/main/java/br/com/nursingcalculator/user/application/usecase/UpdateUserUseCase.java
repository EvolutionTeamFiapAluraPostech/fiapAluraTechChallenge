package br.com.nursingcalculator.user.application.usecase;

import static br.com.nursingcalculator.user.model.messages.UserMessages.USER_ID_NOT_FOUND;

import br.com.nursingcalculator.shared.exception.NoResultException;
import br.com.nursingcalculator.shared.model.entity.validator.UuidValidator;
import br.com.nursingcalculator.user.application.validator.UserEmailAlreadyRegisteredInOtherUserValidator;
import br.com.nursingcalculator.user.model.entity.User;
import br.com.nursingcalculator.user.model.service.UserService;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.FieldError;

@Service
public class UpdateUserUseCase {

  private final UserService userService;
  private final UuidValidator uuidValidator;
  private final UserEmailAlreadyRegisteredInOtherUserValidator userEmailAlreadyRegisteredInOtherUserValidator;

  public UpdateUserUseCase(
      UserService userService,
      UuidValidator uuidValidator,
      UserEmailAlreadyRegisteredInOtherUserValidator userEmailAlreadyRegisteredInOtherUserValidator) {
    this.userService = userService;
    this.uuidValidator = uuidValidator;
    this.userEmailAlreadyRegisteredInOtherUserValidator = userEmailAlreadyRegisteredInOtherUserValidator;
  }

  @Transactional
  public User execute(String userUuid, User userWithUpdatedAttributes) {
    uuidValidator.validate(userUuid);
    userEmailAlreadyRegisteredInOtherUserValidator.validate(userUuid,
        userWithUpdatedAttributes.getEmail());
    var userSaved = findUserBy(userUuid);
    var userToUpdate = updateAttibutesToUser(userSaved, userWithUpdatedAttributes);
    return userService.save(userToUpdate);
  }

  private User updateAttibutesToUser(User userSaved, User userToSave) {
    userSaved.setName(userToSave.getName());
    userSaved.setEmail(userToSave.getEmail());
    return userSaved;
  }

  private User findUserBy(String userUuid) {
    return userService.findById(UUID.fromString(userUuid)).orElseThrow(() -> new NoResultException(
        new FieldError(this.getClass().getSimpleName(), "User",
            USER_ID_NOT_FOUND.formatted(userUuid))));
  }
}
