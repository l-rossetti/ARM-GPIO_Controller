package com.github.arm_gpio_controller.model;

/**
 *
 * @author Rossetti Leonardo, email: leonardo.rossetti5@gmail.com
 * @author Sarti Francesco, email: francescosarti@libero.it
 */
public abstract class Pin {

    public static final String INPUT = "input";
    public static final String OUTPUT = "output";
    public static final int ON_HIGH = 0x1;
    public static final int OFF_HIGH = 0x0;
    public static final int ON_LOW = 0x0;
    public static final int OFF_LOW = 0x1;
    
    protected int ON;
    protected int OFF;
    
    protected String name;
    protected String path;
    protected int value;
    protected String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) throws Exception {
        this.type = type;
    }

    public abstract int getValue() throws Exception;

    public abstract void setValue(int value) throws Exception;

    public abstract int ON();

    public abstract int OFF();

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 53 * hash + (this.path != null ? this.path.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pin other = (Pin) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.path == null) ? (other.path != null) : !this.path.equals(other.path)) {
            return false;
        }
        return true;
    }
}