import com.techlab.infrastructure.persistence.*;
import com.techlab.domain.repository.*;
import com.techlab.application.*;
import com.techlab.presentation.*;
import java.util.Scanner;


public class App {
    public static void main(String[] args) {
        //Infraestructura
        ArticuloRepository articuloRepository = new ArticuloRepositoryImpl();
        OrdenRepository orderRepository = new OrdenRepositoryImpl();

        //Application
        ArticuloService productoService = new ArticuloService(articuloRepository);
        OrdenService ordenService = new OrdenService(orderRepository, articuloRepository);

        // Presentation
        Scanner scanner = new Scanner(System.in);

        ArticuloMenu productoMenu = new ArticuloMenu(productoService, scanner);
        OrdenMenu ordenMenu = new OrdenMenu(ordenService, scanner);
        Menu menu = new Menu(productoMenu, ordenMenu, scanner);
        menu.mostrarMenuPrincipal();


        

    }
}
