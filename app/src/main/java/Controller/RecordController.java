package Controller;

import java.util.ArrayList;
import java.util.List;

public class RecordController {

    private List<String> records; // Lista para almacenar registros

    // Constructor
    public RecordController() {
        records = new ArrayList<>();
    }

    // Método para agregar un registro
    public void addRecord(String record) {
        records.add(record);
    }

    // Método para mostrar todos los registros
    public List<String> getRecords() {
        return new ArrayList<>(records); // Devuelve copia para no exponer la lista interna
    }

    // Método para eliminar un registro
    public boolean removeRecord(String record) {
        return records.remove(record);
    }

    // Método main para probar el controlador (sin prints)
    public static void main(String[] args) {
        RecordController controller = new RecordController();
        controller.addRecord("Primer registro");
        controller.addRecord("Segundo registro");
        controller.removeRecord("Primer registro");
        List<String> registros = controller.getRecords();
        // registros contiene ["Segundo registro"]
    }
}
