import com.techlab.infrastructure.persistence.*;
import com.techlab.domain.repository.*;
import com.techlab.domain.model.Categoria;
import com.techlab.application.*;
import com.techlab.presentation.*;
import java.util.Scanner;


public class App {
    public static void main(String[] args) {
        //Infraestructura
        ArticuloRepository articuloRepository = new ArticuloRepositoryImpl();
        OrdenRepository orderRepository = new OrdenRepositoryImpl();
        CategoriaRepository categoriaRepository = new CategoriaRepositoryImpl();

        //Application
        ArticuloService productoService = new ArticuloService(articuloRepository);
        OrdenService ordenService = new OrdenService(orderRepository, articuloRepository);
        CategoriaService categoriaService = new CategoriaService(categoriaRepository, articuloRepository);

        categoriaRepository.guardarCategoria(new Categoria(1, "Electrónica", "Productos tecnológicos y electrónicos"));
        categoriaRepository.guardarCategoria(new Categoria(2, "Periféricos", "Accesorios para computadora"));
        categoriaRepository.guardarCategoria(new Categoria(3, "Alimentos", "Productos alimenticios"));
        categoriaRepository.guardarCategoria(new Categoria(4, "Limpieza", "Artículos de limpieza del hogar"));

        // Presentation
        Scanner scanner = new Scanner(System.in);

        ArticuloMenu articuloMenu = new ArticuloMenu(productoService, scanner, categoriaService);
        CategoriaMenu categoriaMenu = new CategoriaMenu(categoriaService, scanner);
        OrdenMenu ordenMenu = new OrdenMenu(ordenService, scanner);
        MenuPrincipal menuPrincipal = new MenuPrincipal(articuloMenu, categoriaMenu, ordenMenu, scanner);
        menuPrincipal.mostrarMenuPrincipal();


        

    }
}
