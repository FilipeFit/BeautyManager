package br.com.BeautyManager.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.BeautyManager.model.Categoria;
import br.com.BeautyManager.repository.Categorias;
import br.com.BeautyManager.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter {

	// @Inject
	private Categorias categorias;

	public CategoriaConverter() {
		categorias = CDIServiceLocator.getBean(Categorias.class);
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		Categoria retorno = null;
		if (value != null) {
			Long id = new Long(value);
			retorno = categorias.porId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		// Caso o objeto recebido que neste caso vai ser uma categoria não for
		// nulo retorno o valor
		if (value != null) {
			// faço uma conversão para categoria e retorno seu ID
			return ((Categoria) value).getId().toString();
		}
		return "";
	}
}
