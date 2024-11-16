package Modelos.General;

import java.util.Objects;
/*
public class BaseEntity<T extends Comparable<T>> { //Para que sigan un orden natural

    //Atributos:

    private T atributoIdentificador;

    //Constructor:

    public BaseEntity(T atributoIdentificador) {
        this.atributoIdentificador = atributoIdentificador;
    }

    //Getters y setters:

    public T getAtributoIdentificador() {
        return atributoIdentificador;
    }

    public void setAtributoIdentificador(T atributoIdentificador) {
        if (atributoIdentificador == null) {
            throw new IllegalArgumentException("El atributo identificador no puede ser nulo");
        }
        this.atributoIdentificador = atributoIdentificador;
    }

    //Equals y Hashcode:

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        BaseEntity<?> baseEntity = (BaseEntity<?>) object;
        return Objects.equals(atributoIdentificador, baseEntity.atributoIdentificador);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(atributoIdentificador);
    }

}*/
