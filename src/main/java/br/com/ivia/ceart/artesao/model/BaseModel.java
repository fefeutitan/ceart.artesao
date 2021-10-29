package br.com.ivia.ceart.artesao.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(Include.NON_NULL)
@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode(callSuper=false)
public abstract class BaseModel implements Serializable {

	private static final long serialVersionUID = 3604720522895839822L;

}
