package service;

import model.Pedido;
import repository.PedidoRepository;

public class PedidoService {

    private static final double IVA = 0.21;
    private PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido procesarPedido(String producto, double precio, int cantidad, int stock)
            throws StockInsuficienteException {

        validarStock(cantidad, stock);

        Pedido pedido = new Pedido(producto, precio, cantidad);

        double subtotal = precio * cantidad;
        double impuesto = subtotal * IVA;
        double total = subtotal + impuesto;

        pedido.setSubtotal(subtotal);
        pedido.setImpuesto(impuesto);
        pedido.setTotal(total);

        pedidoRepository.guardar(pedido);

        return pedido;
    }

    private void validarStock(int cantidad, int stock) throws StockInsuficienteException {
        if (stock <= 0) {
            throw new StockInsuficienteException("No hay stock disponible.");
        }
        if (cantidad > stock) {
            throw new StockInsuficienteException("No hay suficiente stock para la cantidad pedida.");
        }
    }
}