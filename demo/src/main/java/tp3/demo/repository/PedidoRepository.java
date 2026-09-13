package tp3.demo.repository;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import tp3.demo.model.Pedido;

@Repository 
public class PedidoRepository {

    private List<Pedido> pedidosGuardados = new ArrayList<>();

    public void guardar(Pedido pedido) {
        String sql = "INSERT INTO pedidos (producto, cantidad, precio, total) VALUES ('"
                + pedido.getProducto() + "', " + pedido.getCantidad() + ", "
                + pedido.getPrecioUnitario() + ", " + pedido.getTotal() + ");";

        System.out.println("[Repository] Ejecutando consulta simulada: " + sql);
        pedidosGuardados.add(pedido);
    }

    public List<Pedido> listarTodos() {
        return pedidosGuardados;
    }
}
