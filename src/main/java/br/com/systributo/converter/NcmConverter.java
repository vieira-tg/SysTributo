package br.com.systributo.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.systributo.model.Ncm;
import br.com.systributo.repository.Ncms;
import br.com.systributo.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Ncm.class)
public class NcmConverter implements Converter {

	private Ncms ncms;

	public NcmConverter() {
		ncms = CDIServiceLocator.getBean(Ncms.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Ncm retorno = null;

		if (value != null) {
			Long id = Long.valueOf(value);
			retorno = ncms.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Ncm a = ((Ncm) value);
			return String.valueOf(a.getId());
		}

		return "";
	}

}
