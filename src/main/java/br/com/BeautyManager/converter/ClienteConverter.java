package br.com.BeautyManager.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.BeautyManager.model.Cliente;
import br.com.BeautyManager.repository.Clientes;
import br.com.BeautyManager.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Cliente.class)
public class ClienteConverter implements Converter {

	// @Inject
	private Clientes clientes;

	public ClienteConverter() {
		clientes = CDIServiceLocator.getBean(Clientes.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,String value) {
		Cliente retorno = null;
		if ((value != null) && (value != "")){
			Long id = new Long(value);
			retorno = clientes.porId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,Object value) {
		if (value != null) {
			Cliente cliente = (Cliente) value;
			return cliente.getId() == null ? null : cliente.getId().toString();
		}

		return "";
	}
}
