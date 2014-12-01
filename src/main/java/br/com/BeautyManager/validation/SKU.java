package br.com.BeautyManager.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

//Digo onde eu posso usar essa anotação (somente em metodos atributos)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
//Significa que o Bean validation pode ler a anotação em tempo de execusão
@Retention(RetentionPolicy.RUNTIME)
// Posso incluir uma classe para fazer a valisação neste caso não tempo pois so usaremos o regular expressopn
@Constraint(validatedBy = {})
@Pattern(regexp = "([a-zA-Z]{2}\\d{4,18})?")
public @interface SKU {
	
	// Faço um override para que a anotação pegue a mensagem padrão da classe
	// faço o Override aqui pois posso querer customizar a mensagem do SKU usando a anotação
	@OverridesAttribute(constraint = Pattern.class,name ="message")
	// br.com.BeautyManager.constrainsts.SKU.message está no arquivo /resources/ValidationMessages.properties
	String message() default "{br.com.BeautyManager.constrainsts.SKU.message}";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};

}
