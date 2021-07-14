package sg.edu.rp.c346.id20023243.demodatabasecrud;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Note implements Serializable { //object become serializable; can pass around
    private int id;
    private String noteContent; //standard practice private ; dw them change wiw/o you knowing, be in control


    public Note(int id, String noteContent) {
        this.id = id;
        this.noteContent = noteContent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    @Override
    //override means parent class already have method, but you want use own one
    public String toString() {
        return "ID: " + id + ", " + noteContent;
    }
}
