package com.dissekcorportion.notesjava.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.dissekcorportion.notesjava.daos.NoteDao;
import com.dissekcorportion.notesjava.models.Note;

@Database(
        entities = {Note.class},
        version = 1
)
public abstract class NoteRoomDataBase extends RoomDatabase {
    public abstract NoteDao noteDao(); // Para obtener un dato de tipo dao cuando sea requerido.
    private static volatile NoteRoomDataBase INTANCE;   // Para guardar la instancia a la DB.

    public static NoteRoomDataBase getDataBase(Context context) {
        if (INTANCE == null) { // no se ha instanciado.
            synchronized (NoteRoomDataBase.class) {
                if (INTANCE == null) {
                    INTANCE = Room.databaseBuilder(
                            context.getApplicationContext(), // pasamos el contexto.
                            NoteRoomDataBase.class,          // Esta misma clase abstracta.
                            "name_db_test")           // Nombre de la base de datos.
                            .build();
                }
            }
        }
        return INTANCE;
    }
}
