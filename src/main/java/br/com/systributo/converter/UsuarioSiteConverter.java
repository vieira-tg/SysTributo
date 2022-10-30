package br.com.systributo.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.systributo.model.UsuarioSite;
import br.com.systributo.repository.UsuariosSites;
import br.com.systributo.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = UsuarioSite.class)
public class UsuarioSiteConverter implements Converter {
	private UsuariosSites usuarios;

	public UsuarioSiteConverter() {
		usuarios = CDIServiceLocator.getBean(UsuariosSites.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		UsuarioSite retorno = null;

		if (value != null) {
			Long id = Long.valueOf(value);
			retorno = usuarios.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			UsuarioSite a = ((UsuarioSite) value);
			return String.valueOf(a.getId());
		}

		return "";
	}
}
