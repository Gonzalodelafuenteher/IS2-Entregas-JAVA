package bloque5.examen;

import java.util.Objects;

public final class Compra {
    private final Cliente cliente;
    private final String descripcion;
    private final double importe;

    public Compra(Cliente cliente, String descripcion, double importe) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }
        if (descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException("La descripción no puede ser nula o vacía");
        }
        if (importe < 0) {
            throw new IllegalArgumentException("El importe no puede ser negativo");
        }
        this.cliente = cliente;
        this.descripcion = descripcion;
        this.importe = importe;
    }

    public static Compra of(Cliente cliente, String descripcion, double importe) {
        return new Compra(cliente, descripcion, importe);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getImporte() {
        return importe;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Compra)) return false;
        Compra otra = (Compra) obj;
        return Objects.equals(this.cliente, otra.cliente) &&
               Objects.equals(this.descripcion, otra.descripcion) &&
               Double.compare(this.importe, otra.importe) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, descripcion, importe);
    }

    @Override
    public String toString() {
        return "Compra [Nombre de cliente=" + cliente.getNombre() + 
               ", descripción=" + descripcion + 
               ", importe=" + importe + " €]";
    }
}

