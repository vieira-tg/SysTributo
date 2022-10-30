package br.com.systributo.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.systributo.model.Cofins;
import br.com.systributo.repository.Cofinses;
import br.com.systributo.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Cofins.class)
public class CofinsConverter implements Converter {
	private Cofinses cofinses;

	public CofinsConverter() {
		cofinses = CDIServiceLocator.getBean(Cofinses.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Cofins retorno = null;

		if (value != null) {
			Long id = Long.valueOf(value);
			retorno = cofinses.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {

			Cofins a = ((Cofins) value);
			return String.valueOf(a.getId());
		}

		return "";
	}
}
