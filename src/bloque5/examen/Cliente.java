package bloque5.examen;

import java.util.Objects;



public class Cliente {
	private final String nombre;
    private final int antigüedad;

    public Cliente(String nombre, int antigüedad) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
        }
        if (antigüedad < 0) {
            throw new IllegalArgumentException("La antigüedad no puede ser negativa");
        }
        this.nombre = nombre;
        this.antigüedad = antigüedad;
    }

    public static Cliente of(String nombre, int antigüedad) {
        return new Cliente(nombre, antigüedad);
    }

    public String getNombre() {
        return nombre;
    }

    public int getAntiguedad() {
        return antigüedad;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Cliente)) return false;
        Cliente otro = (Cliente) obj;
        return Objects.equals(this.nombre, otro.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    @Override
    public String toString() {
        return "Cliente[nombre=" + nombre + ", antigüedad=" + antigüedad + "]";
    }


}
