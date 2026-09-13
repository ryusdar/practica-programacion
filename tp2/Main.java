import controller.PedidoController;
import repository.PedidoRepository;
import service.PedidoService;

public class Main {
    public static void main(String[] args) {
        PedidoRepository pedidoRepository = new PedidoRepository();
        PedidoService pedidoService = new PedidoService(pedidoRepository);
        PedidoController pedidoController = new PedidoController(pedidoService);

        pedidoController.iniciar();
    }
}