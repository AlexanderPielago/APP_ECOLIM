package Controller;

import java.util.ArrayList;
import java.util.List;

// Controlador para manejar tipos (ejemplo funcional)
public class TypeController {

    private List<String> types; // Lista para almacenar tipos

    // Constructor
    public TypeController() {
        types = new ArrayList<>();
    }

    // Método para agregar un tipo
    public void addType(String type) {
        types.add(type);
    }

    // Método para obtener todos los tipos
    public List<String> getTypes() {
        return new ArrayList<>(types); // Devuelve copia para no exponer la lista interna
    }

    // Método para eliminar un tipo
    public boolean removeType(String type) {
        return types.remove(type);
    }

    // Método main para probar el controlador (sin prints)
    public static void main(String[] args) {
        TypeController controller = new TypeController();
        controller.addType("Orgánico");
        controller.addType("Inorgánico");
        controller.removeType("Orgánico");
        List<String> tipos = controller.getTypes();
        // tipos contiene ["Inorgánico"]
    }
}
