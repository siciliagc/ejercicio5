package com.example.ejercicio5;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Item {
    private long id =-1;
    private String descripcion;
    private boolean checked;

    @Override
    public String toString(){
        return "Item: ID: "+this.id+". Descripcion: "+this.descripcion+". Checked: "+this.checked;
    }
}
