
package br.com.systributo.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.systributo.model.Uf;

@FacesConverter(forClass = Uf.class)
public class UfConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value.equals("AC")) {
			return Uf.AC;
		}
		
		if (value.equals("AL")) {
			return Uf.AL;
		}
		
		if (value.equals("AP")) {
			return Uf.AP;
		}
		
		if (value.equals("AM")) {
			return Uf.AM;
		}
		
		if (value.equals("BA")) {
			return Uf.BA;
		}
		
		if (value.equals("CE")) {
			return Uf.CE;		
		}
		
		if (value.equals("DF")) {
			return Uf.DF;
		}
		
		if (value.equals("ES")) {
			return Uf.ES;
		}
		
		if (value.equals("GO")) {
			return Uf.GO;
		}

		if (value.equals("MA")) {
			return Uf.MA;
		}

		if (value.equals("MT")) {
			return Uf.MT;
		}
		
		if (value.equals("MS")) {
			return Uf.MS;
		}
		
		if (value.equals("MG")) {
			return Uf.MG;
		}
		
		if (value.equals("PA")) {
			return Uf.PA;
		}
		
		if (value.equals("PB")) {
			return Uf.PB;
		}
		
		if (value.equals("PR")) {
			return Uf.PR;
		}
		
		if (value.equals("PE")) {
			return Uf.PE;
		}
		
		if (value.equals("PI")) {
			return Uf.PI;
		}
		
		if (value.equals("RR")) {
			return Uf.RR;
		}
		
		if (value.equals("RO")) {
			return Uf.RO;
		}
		
		if (value.equals("RJ")) {
			return Uf.RJ;
		}
		
		if (value.equals("RN")) {
			return Uf.RN;
		}
		
		if (value.equals("RS")) {
			return Uf.RS;
		}
		
		if (value.equals("SC")) {
			return Uf.SC;
		}
		
		if (value.equals("SP")) {
			return Uf.SP;
		}
		
		if (value.equals("SE")) {
			return Uf.SE;
		}
		
		if (value.equals("TO")) {
			return Uf.TO;
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return value.toString();
	}
}
