package onl.devin.mc_particles.command;

import java.util.List;

public abstract class ParticleCommandComponent {

    protected ParticleCommandComponent previousComponent;
    protected List<String> possibleValues;
    protected String actualValue;

    public ParticleCommandComponent(ParticleCommandComponent previousComponent) {
        this.previousComponent = previousComponent;
    }

    abstract void initializePossibleValues();

    abstract boolean componentIsValid();

    abstract Class<?> nextComponentExpected();

    abstract boolean nextComponentRequired();

    public ParticleCommandComponent getPreviousComponent() {
        return previousComponent;
    }

    public String getActualValue() {
        return actualValue;
    }

    public boolean actualValueIsValid() {
        return possibleValues.contains(actualValue);
    }

    public boolean isIncludedInOptions(String value) {
        return possibleValues.contains(value);
    }

}
