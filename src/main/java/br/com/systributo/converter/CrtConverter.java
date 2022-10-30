package br.com.systributo.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.systributo.model.Crt;
import br.com.systributo.repository.Crts;
import br.com.systributo.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Crt.class)
public class CrtConverter implements Converter {
	private Crts crts;

	public CrtConverter() {
		crts = CDIServiceLocator.getBean(Crts.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Crt retorno = null;

		if (value != null) {
			Long id = Long.valueOf(value);
			retorno = crts.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value != null) {
			Crt a = ((Crt) value);
			return String.valueOf(a.getId());
		}

		return "";
	}
}
