package Model;

public class RecoleccionModel {

    private int id;
    private double organico;
    private double plastico;
    private double vidrio;
    private double metal;

    // 🔹 Constructor vacío (necesario para algunas operaciones)
    public RecoleccionModel() {
    }

    // 🔹 Constructor completo
    public RecoleccionModel(int id, double organico, double plastico, double vidrio, double metal) {
        this.id = id;
        this.organico = organico;
        this.plastico = plastico;
        this.vidrio = vidrio;
        this.metal = metal;
    }

    // 🔹 Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getOrganico() {
        return organico;
    }

    public void setOrganico(double organico) {
        this.organico = organico;
    }

    public double getPlastico() {
        return plastico;
    }

    public void setPlastico(double plastico) {
        this.plastico = plastico;
    }

    public double getVidrio() {
        return vidrio;
    }

    public void setVidrio(double vidrio) {
        this.vidrio = vidrio;
    }

    public double getMetal() {
        return metal;
    }

    public void setMetal(double metal) {
        this.metal = metal;
    }
}
