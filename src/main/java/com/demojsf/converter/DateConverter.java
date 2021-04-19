package com.demojsf.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.faces.validator.ValidatorException;

@FacesConverter(value = "DateConverter")
public class DateConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date date = null;
		format.setLenient(false); 
		try {
			date = format.parse(value);
		} catch (ParseException e) {
			FacesMessage msg = new FacesMessage("<< Date is incorrect >>");
			throw new ConverterException(msg);
		}
		return date;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return new SimpleDateFormat("MM/dd/yyyy").format((Date) value);
	}

}
