package br.com.systributo.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.systributo.model.Cidade;
import br.com.systributo.repository.Cidades;
import br.com.systributo.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Cidade.class)
public class CidadeConverter implements Converter {

	private Cidades cidades;

	public CidadeConverter() {
		cidades = CDIServiceLocator.getBean(Cidades.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Cidade retorno = null;

		if (value != null) {
			String id = new String(value);
			retorno = cidades.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((Cidade) value).getCodigoIBGE().toString();
		}

		return "";
	}
}
