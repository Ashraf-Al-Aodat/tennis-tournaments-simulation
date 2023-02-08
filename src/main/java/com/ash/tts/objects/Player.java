package com.ash.tts.objects;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;


public class Player {
    private final String name;
    private final int performanceValue;
    @JsonCreator
    public Player(@JsonProperty("name") String name, @JsonProperty("performanceValue") int performanceValue) {
        this.name = name;
        this.performanceValue = performanceValue;
    }

    public String getName() {
        return name;
    }

    public int getPerformanceValue() {
        return performanceValue;
    }
}
