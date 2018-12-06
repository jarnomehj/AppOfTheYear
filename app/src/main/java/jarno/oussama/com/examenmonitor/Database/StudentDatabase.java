package jarno.oussama.com.examenmonitor.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Student.class},version = 5)
public abstract class StudentDatabase extends RoomDatabase{
    public static StudentDatabase Instance;
    public abstract StudentDao StudentDao();

    public static StudentDatabase getDatabase(Context context ) {
        if (Instance == null){
            Instance =  Room.databaseBuilder(context.getApplicationContext(),StudentDatabase.class,"development")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return Instance;
    }
}
