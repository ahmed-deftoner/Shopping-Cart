package DB
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    // below is the method for creating a database by a sqlite query
    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                NAME_COl + " TEXT," +
                PRICE_COL + " TEXT," +
                IMAGE_COL + " BLOB," +
                DESCRIPTION_COL + " TEXT," +
                BODY_COL + " TEXT," +
                SCALE_COL + " TEXT," +
                RATING_COL + " TEXT" +
                ")")
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // This method is for adding data in our database
    fun addProduct(name : String, price : String, img : ByteArray,
                    desc : String, body : String, scale : String, rating : String){

        val values = ContentValues()

        values.put(NAME_COl, name)
        values.put(IMAGE_COL, img)
        values.put(PRICE_COL, price)
        values.put(DESCRIPTION_COL, desc)
        values.put(BODY_COL, body)
        values.put(SCALE_COL, scale)
        values.put(RATING_COL, rating)

        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }


    fun getProduct(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)

    }

    companion object{
        private const val DATABASE_NAME = "GUITARS"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "Products"
        const val ID_COL = "id"
        const val NAME_COl = "name"
        const val PRICE_COL = "price"
        const val IMAGE_COL = "image"
        const val DESCRIPTION_COL = "description"
        const val SCALE_COL = "scale"
        const val BODY_COL = "body"
        const val RATING_COL = "rating"
    }
}
