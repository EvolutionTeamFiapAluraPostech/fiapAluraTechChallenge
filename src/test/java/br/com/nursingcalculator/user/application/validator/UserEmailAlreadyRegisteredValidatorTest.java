package br.com.nursingcalculator.user.application.validator;

import static br.com.nursingcalculator.shared.testData.user.UserTestData.createNewUser;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import br.com.nursingcalculator.shared.exception.DuplicatedException;
import br.com.nursingcalculator.shared.testData.user.UserTestData;
import br.com.nursingcalculator.user.model.service.UserService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserEmailAlreadyRegisteredValidatorTest {

  @Mock
  private UserService userService;
  @InjectMocks
  private UserEmailAlreadyRegisteredValidator userEmailAlreadyRegisteredValidator;

  @Test
  void shouldValidateWhenUserEmailDoesNotExist() {
    var user = createNewUser();
    when(userService.findByEmail(user.getEmail())).thenReturn(Optional.empty());

    assertDoesNotThrow(() -> userEmailAlreadyRegisteredValidator.validate(user.getEmail()));
  }

  @Test
  void shouldThrowExceptionWhenUserAlreadyExists() {
    var user = UserTestData.createUser();
    when(userService.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

    assertThrows(DuplicatedException.class,
        () -> userEmailAlreadyRegisteredValidator.validate(user.getEmail()));
  }
}
