import java.util.Set;
import java.util.TreeSet;

public class LaptopFilter {
    private Set<String> laptopMakers; 
    private Set<String> cpuMakers;
    private Set<String> cpuModels;
    private Float ramMin;
    private Float ramMax;
    private Float storageMin;
    private Float storageMax;
    private Set<String> osNames;
    private Set<String> colors;
    private Float priceMin;
    private Float priceMax;
    private Float weightMin;
    private Float weightMax;
    

    LaptopFilter(){
        laptopMakers = new TreeSet<>();
        cpuMakers = new TreeSet<>();
        cpuModels = new TreeSet<>();
        osNames = new TreeSet<>();
        colors = new TreeSet<>();
        ramMax = ramMin = storageMax = storageMin = null;
        priceMin = priceMax = weightMax = weightMin = null;
    }

        
    public boolean isMatch(Laptop l){
        if (   (laptopMakers.size() == 0 || laptopMakers.contains(l.getMakerName()))
            && (cpuMakers.size() == 0 || cpuMakers.contains(l.getCpuMaker()))
            && (cpuModels.size() == 0 || cpuModels.contains(l.getCpuModel()))
            && (osNames.size() == 0 || osNames.contains(l.getOperationalSystem())) 
            && (colors.size() == 0 || colors.contains(l.getColor()))
            && (ramMin == null || (ramMin != null && l.getRamGigabytes() >= ramMin)) 
            && (ramMax == null ||(ramMax != null && l.getRamGigabytes() <= ramMax))
            && (storageMax == null || (storageMax != null && l.getTotalStorageInGigabytes() <= storageMax))
            && (storageMin == null || (storageMin != null && l.getTotalStorageInGigabytes() >= storageMin))
            && (priceMin == null || (priceMin != null && l.getPrice() >= priceMin))
            && (priceMax == null || (priceMax != null && l.getPrice() <= priceMax))
            && (weightMin == null || (weightMin != null && l.getWeight() >= weightMin))
            && (weightMax == null || (weightMax != null && l.getWeight() <= weightMax))
            ) {
                return true;
            } else {
                return false;
            }

    }
    
    public void setPriceMin(Float priceMin) {
        this.priceMin = priceMin;
    }

    public void setPriceMax(Float priceMax) {
        this.priceMax = priceMax;
    }


    public void setWeightMin(Float weightMin) {
        this.weightMin = weightMin;
    }


    public void setWeightMax(Float weightMax) {
        this.weightMax = weightMax;
    }


    public void setStorageMax(Float storageMax) {
        this.storageMax = storageMax;
    }

    public void setStorageMin(Float storageMin) {
        this.storageMin = storageMin;
    }

    public void setRamMax(Float ramMax) {
        this.ramMax = ramMax;
    }

    public void setRamMin(Float ramMin) {
        this.ramMin = ramMin;
    }
    
    public void includeMaker(String maker){
        laptopMakers.add(maker);
    }
    
    public void excludeMaker(String maker){
        laptopMakers.remove(maker);
    }

    public void includeCpuMaker(String cpuMaker){
        cpuMakers.add(cpuMaker);
    }

    public void excludeCpuMaker(String cpuMaker){
        cpuMakers.remove(cpuMaker);
    }
    
    public void includeCpuModel(String cpuModel){
        cpuModels.add(cpuModel);
    }

    public void excludeCpuModel(String cpuModel){
        cpuModels.remove(cpuModel);
    }

    public void includeOsName(String osName){
        osNames.add(osName);
    }

    public void excludeOsName(String osName){
        osNames.remove(osName);
    }

    public void includeColor(String color){
        colors.add(color);
    }

    public void excludeColor(String color){
        colors.remove(color);
    }
    
    
    public Set<String> getLaptopMakers() {
        return laptopMakers;
    }

    public Set<String> getCpuMakers() {
        return cpuMakers;
    }

    public Set<String> getCpuModels() {
        return cpuModels;
    }

    public Float getRamMin() {
        return ramMin;
    }

    public Float getRamMax() {
        return ramMax;
    }

    public Float getStorageMin() {
        return storageMin;
    }

    public Float getStorageMax() {
        return storageMax;
    }

    public Set<String> getOsNames() {
        return osNames;
    }

    public Set<String> getColors() {
        return colors;
    }

    public Float getPriceMin() {
        return priceMin;
    }

    public Float getPriceMax() {
        return priceMax;
    }

    public Float getWeightMin() {
        return weightMin;
    }

    public Float getWeightMax() {
        return weightMax;
    }


    @Override
    public String toString() {                
        StringBuilder sb = new StringBuilder();
        if (laptopMakers.size() !=0) { sb.append("Makers: " + laptopMakers);}
        if (cpuMakers.size() !=0) {sb.append("\ncpuMakers: " + cpuMakers);}
        if (cpuModels.size() !=0) {sb.append("\ncpuModels: " + cpuModels);}
        if (osNames.size() !=0) {sb.append("\nosNames: " + osNames);}
        if (colors.size() !=0) {sb.append("\ncolors:" + colors);} 
        
        if (ramMin != null) {sb.append("\nRAM min: " + ramMin);}
        if (ramMax !=null) {
            if (ramMin == null){sb.append("\n");}
            sb.append(" RAM max: " + ramMax);
        }

        if (storageMin != null) {sb.append("\nStrorage min: " + storageMin);}
        if (storageMax !=null) {
            if (storageMin == null){sb.append("\n");}
            sb.append(" Storage max: " + storageMax);
        }

        if (priceMin != null) {sb.append("\nPrice min: " + priceMin);}
        if (priceMax !=null) {
            if (priceMin == null){sb.append("\n");}
            sb.append(" Price max: " + priceMax);
        }

        if (weightMin != null) {sb.append("\nWeight min: " + weightMin);}
        if (weightMax !=null) {
            if (weightMin == null){sb.append("\n");}
            sb.append(" Weight max: " + weightMax);
        }

        if (sb.length() == 0) { return "\nno active filter";}
        
        return sb.insert(0, "Active filter:\n").toString();
    }
    
}
