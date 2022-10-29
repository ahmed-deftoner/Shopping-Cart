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

    // below method is to get
    // all data from our database
    fun getProduct(): Cursor? {

        // here we are creating a readable
        // variable of our database
        // as we want to read value from it
        val db = this.readableDatabase

        // below code returns a cursor to
        // read data from the database
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)

    }

    companion object{
        private val DATABASE_NAME = "GUITARS"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "Products"
        val ID_COL = "id"
        val NAME_COl = "name"
        val PRICE_COL = "price"
        val IMAGE_COL = "image"
        val DESCRIPTION_COL = "description"
        val SCALE_COL = "scale"
        val BODY_COL = "body"
        val RATING_COL = "rating"
    }
}
