/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Stefito
 */
public class Medicine {
    private final String Name;
    private final String type;
    private final int active_substance_content;
    private final Disease indicatedDisease;

    public Medicine(String Name, String type, int active_substance_content, Disease indicatedDisease) {
        this.Name = Name;
        this.type = type;
        this.active_substance_content = active_substance_content;
        this.indicatedDisease = indicatedDisease;
    }

    public String getName() {
        return this.Name;
    }

    public String getType() {
        return this.type;
    }

    public int getActive_substance_content() {
        return this.active_substance_content;
    }

    public Disease getIndicatedDisease() {
        return this.indicatedDisease;
    }

}
