package br.com.fiapbook.user.application.usecase;

import static br.com.fiapbook.user.model.messages.UserMessages.USER_ID_NOT_FOUND;

import br.com.fiapbook.shared.exception.NoResultException;
import br.com.fiapbook.shared.model.entity.validator.UuidValidator;
import br.com.fiapbook.user.model.entity.User;
import br.com.fiapbook.user.model.service.UserService;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.FieldError;

@Service
public class DeleteUserUseCase {

  private final UserService userService;
  private final UuidValidator uuidValidator;

  public DeleteUserUseCase(
      UserService userService,
      UuidValidator uuidValidator
  ) {
    this.userService = userService;
    this.uuidValidator = uuidValidator;
  }

  @Transactional
  public void execute(String userUuid) {
    uuidValidator.validate(userUuid);
    var user = findUserById(userUuid);
    user.setDeleted(true);
    userService.save(user);
  }

  private User findUserById(String userUuid) {
    return userService.findById(UUID.fromString(userUuid)).orElseThrow(
        () -> new NoResultException(new FieldError(this.getClass().getSimpleName(), "User",
            USER_ID_NOT_FOUND.formatted(userUuid))));
  }
}
