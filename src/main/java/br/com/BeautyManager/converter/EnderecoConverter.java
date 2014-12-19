package br.com.BeautyManager.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.BeautyManager.model.Endereco;
import br.com.BeautyManager.repository.Enderecos;
import br.com.BeautyManager.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Endereco.class)
public class EnderecoConverter implements Converter {

	// @Inject
	private Enderecos enderecos;

	public EnderecoConverter() {
		enderecos = CDIServiceLocator.getBean(Enderecos.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,String value) {
		Endereco retorno = null;
		if ((value != null) && (value != "")){
			Long id = new Long(value);
			retorno = enderecos.porId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,Object value) {
		if (value != null) {
			Endereco endereco = (Endereco) value;
			return endereco.getId() == null ? null : endereco.getId().toString();
		}

		return "";
	}
}
