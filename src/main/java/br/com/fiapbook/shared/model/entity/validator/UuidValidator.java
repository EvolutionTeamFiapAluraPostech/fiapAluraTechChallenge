package br.com.fiapbook.shared.model.entity.validator;

import static br.com.fiapbook.shared.model.messages.SharedMessages.UUID_INVALID;

import br.com.fiapbook.shared.exception.ValidatorException;
import br.com.fiapbook.shared.util.IsUUID;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

@Component
public class UuidValidator {

  public void validate(String uuid) {
    if (!IsUUID.isUUID().matches(uuid)) {
      throw new ValidatorException(new FieldError(this.getClass().getSimpleName(), "UUID",
          UUID_INVALID.formatted(uuid)));
    }
  }
}
