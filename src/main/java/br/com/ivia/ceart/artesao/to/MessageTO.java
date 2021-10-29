package br.com.ivia.ceart.artesao.to;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageTO implements Serializable {

    private static final long serialVersionUID = -2795349486504616322L;

    private String message;

    private Boolean success;

    private int numberOfPages;

    private Object response;

    public MessageTO(String message, Boolean success, Object response) {
        super();
        this.message = message;
        this.success = success;
        this.response = response;
    }

}
