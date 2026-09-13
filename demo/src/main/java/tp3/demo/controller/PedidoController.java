package tp3.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tp3.demo.model.Pedido;
import tp3.demo.service.PedidoService;
import tp3.demo.service.StockInsuficienteException;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<?> crearPedido(@RequestBody PedidoRequest request) {
        try {
            Pedido pedido = pedidoService.procesarPedido(
                    request.getProducto(),
                    request.getPrecio(),
                    request.getCantidad(),
                    request.getStock()
            );
            return ResponseEntity.ok(pedido);
        } catch (StockInsuficienteException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listarPedidos() {
        return ResponseEntity.ok(pedidoService.listarPedidos());
    }

    public static class PedidoRequest {
        private String producto;
        private double precio;
        private int cantidad;
        private int stock;

        public String getProducto() { return producto; }
        public void setProducto(String producto) { this.producto = producto; }
        public double getPrecio() { return precio; }
        public void setPrecio(double precio) { this.precio = precio; }
        public int getCantidad() { return cantidad; }
        public void setCantidad(int cantidad) { this.cantidad = cantidad; }
        public int getStock() { return stock; }
        public void setStock(int stock) { this.stock = stock; }
    }
}