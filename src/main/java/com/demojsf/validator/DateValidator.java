package com.demojsf.validator;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "DateValidator")
public class DateValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		try {
			Date date = (Date) value;
		}catch(Exception e) {
			FacesMessage msg = new FacesMessage("<<< Date is incorrect >>>");
			throw new ValidatorException(msg);
		}
	}

}
