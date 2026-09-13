package controller;

import java.util.Scanner;

import model.Pedido;
import service.PedidoService;
import service.StockInsuficienteException;

public class PedidoController {

    private Scanner scanner = new Scanner(System.in);
    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    public void iniciar() {
        System.out.print("Ingrese nombre del producto: ");
        String producto = scanner.nextLine();

        System.out.print("Ingrese precio unitario: ");
        double precio = Double.parseDouble(scanner.nextLine());

        System.out.print("Ingrese cantidad a pedir: ");
        int cantidad = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese stock disponible: ");
        int stock = Integer.parseInt(scanner.nextLine());

        try {
            Pedido pedido = pedidoService.procesarPedido(producto, precio, cantidad, stock);
            mostrarFactura(pedido);
        } catch (StockInsuficienteException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void mostrarFactura(Pedido pedido) {
        System.out.println("===== FACTURA =====");
        System.out.println("Producto: " + pedido.getProducto());
        System.out.println("Cantidad: " + pedido.getCantidad());
        System.out.println("Precio unitario: $" + pedido.getPrecioUnitario());
        System.out.println("Subtotal: $" + pedido.getSubtotal());
        System.out.println("IVA (21%): $" + pedido.getImpuesto());
        System.out.println("TOTAL: $" + pedido.getTotal());
    }
}