package br.com.systributo.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.systributo.model.Pis;
import br.com.systributo.repository.Pises;
import br.com.systributo.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Pis.class)
public class PisConverter implements Converter {

	private Pises pises;

	public PisConverter() {
		pises = CDIServiceLocator.getBean(Pises.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Pis retorno = null;

		if (value != null) {
			Long id = Long.valueOf(value);
			retorno = pises.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Pis a = ((Pis) value);
			return String.valueOf(a.getId());
		}

		return "";
	}

}
