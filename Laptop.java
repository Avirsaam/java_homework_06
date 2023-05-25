public class Laptop {
    private Integer id;
    private String makerName;
    private String modelName;
    
    private String cpuMaker;
    private String cpuModel;
        
    private Float ramGigabytes;    
    private Float totalStorageInGigabytes;
    
    private String operationalSystem;
    private String color;

    private Float price;
    private Float weight;

    public Laptop(Integer id, String makerName, String modelName, String cpuMaker, String cpuModel, Float ramGigabytes,
    Float totalStorageInGigabytes, String operationalSystem, String color, Float price, Float weight) {
        this.id = id;
        this.makerName = makerName;
        this.modelName = modelName;
        this.cpuMaker = cpuMaker;
        this.cpuModel = cpuModel;
        this.ramGigabytes = ramGigabytes;
        this.totalStorageInGigabytes = totalStorageInGigabytes;
        this.operationalSystem = operationalSystem;
        this.color = color;
        this.price = price;
        this.weight = weight;
    }    
        
    public Laptop (String csvLine){
        String [] split = csvLine.split(",");

        this.id = Integer.parseInt(split[0]);
        this.makerName = split[1];
        this.modelName = split[2];
        this.cpuMaker = split[3];
        this.cpuModel = split[4];
        this.ramGigabytes = Float.parseFloat(split[5]);
        this.totalStorageInGigabytes = Float.parseFloat(split[6]);
        this.operationalSystem = split[7];
        this.color = split[8];
        this.price = Float.parseFloat(split[9]);
        this.weight = Float.parseFloat(split[10]);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Laptop [");
        if (makerName != null) { sb.append(makerName); }
        if (modelName != null) { sb.append(" " + modelName); }
        if (cpuMaker != null) { sb.append(", " + cpuMaker ); }
        if (cpuModel != null) { sb.append(" " + cpuModel); }
        if (ramGigabytes != null) { sb.append(", RAM:" + ramGigabytes + "GB,"); }
        if (totalStorageInGigabytes != null) { sb.append(" Storage:" + totalStorageInGigabytes + "GB,"); }
        if (operationalSystem != null) { sb.append(" " + operationalSystem); }
        if (color != null) { sb.append(", " + color); }
        if (price != null) { sb.append(", $" + price); }
        if (weight != null) { sb.append(", " + weight + "kg"); }
        
        sb.append("]");
        return sb.toString();
    }

    public Integer getId() {
        return id;
    }

    public String getMakerName() {
        return makerName;
    }

    public String getModelName() {
        return modelName;
    }

    public String getCpuMaker() {
        return cpuMaker;
    }

    public String getCpuModel() {
        return cpuModel;
    }

    public Float getRamGigabytes() {
        return ramGigabytes;
    }

    public Float getTotalStorageInGigabytes() {
        return totalStorageInGigabytes;
    }

    public String getOperationalSystem() {
        return operationalSystem;
    }

    public String getColor() {
        return color;
    }

    public Float getPrice() {
        return price;
    }

    public Float getWeight() {
        return weight;
    }
    
}