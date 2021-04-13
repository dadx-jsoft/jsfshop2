package com.demojsf.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "InputTextEmptyValidator")
public class InputTextEmptyValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String textValue = value.toString();
		if(textValue.isEmpty()) {
			FacesMessage msg = new FacesMessage("Không được bỏ trống");
			throw new ValidatorException(msg);
		}
	}

}
