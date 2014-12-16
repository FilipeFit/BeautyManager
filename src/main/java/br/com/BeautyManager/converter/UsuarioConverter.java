package br.com.BeautyManager.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.BeautyManager.model.Usuario;
import br.com.BeautyManager.repository.Usuarios;
import br.com.BeautyManager.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {

	// @Inject
	private Usuarios usuarios;

	public UsuarioConverter() {
		usuarios = CDIServiceLocator.getBean(Usuarios.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,String value) {
		Usuario retorno = null;
		if ((value != null) && (value != "")){
			Long id = new Long(value);
			retorno = usuarios.porId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,Object value) {
		if (value != null) {
			Usuario usuario = (Usuario) value;
			return usuario.getId() == null ? null : usuario.getId().toString();
		}

		return "";
	}
}
