package com.demojsf.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "EmailValidator")
public class EmailValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String email = value.toString();
		if (-1 == email.indexOf("@")) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email error",
					"Email should be formatted like demo@gmail.com");
			throw new ValidatorException(msg);
		}
	}

}
