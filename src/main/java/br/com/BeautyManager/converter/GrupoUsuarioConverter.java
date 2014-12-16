package br.com.BeautyManager.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.BeautyManager.model.Grupo;
import br.com.BeautyManager.repository.GruposUsuarios;
import br.com.BeautyManager.util.cdi.CDIServiceLocator;

public class GrupoUsuarioConverter implements Converter {

	private GruposUsuarios gruposUsuarios;

	public GrupoUsuarioConverter() {
		gruposUsuarios = CDIServiceLocator.getBean(GruposUsuarios.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Grupo retorno = null;
		if ((value != null) && (value != "")) {
			Long id = new Long(value);
			retorno = gruposUsuarios.porId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,Object value) {
		if(value != null){
			return((Grupo) value).getId().toString();
		}
		return "";
	}
}