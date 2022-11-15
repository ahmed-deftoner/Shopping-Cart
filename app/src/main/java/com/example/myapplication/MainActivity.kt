package com.example.myapplication

import Models.Guitars
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.collections.ArrayList



class MainActivity : AppCompatActivity() {

    lateinit var guitarRV: RecyclerView
    lateinit var guitars: ArrayList<Guitars>
    val ref = FirebaseDatabase.getInstance().getReference("guitars")

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        guitarRV = findViewById(R.id.idRVGuitars)

        /* Here, we have created new array list and added data to it
        guitarModelArrayList = ArrayList()
        guitarModelArrayList.add(
            Guitars("EVH Wolfgang Standard",
                     R.drawable.evh,
                    600,
                    "The EVH Wolfgang WG Standard is available with a basswood body and Maple quilted top. The finish is gloss polyurethane. The Double cutaway body design serves great for higher fret access. The body is shaped in the same distinctive body style that Eddie personally designed. It has a comfortable shallow C-shaped profile bolt-on one-piece maple neck with 22 jumbo frets, and a 12″ to 16″ compound radius. The scale length is 25.5″. There is also a spoke wheel truss rod adjuster located above the 22nd fret. Topping it off is a maple fretboard with dot inlays. It has two EVH branded humbuckers that provide a fierce tone. For the tonal control, there is a master volume and tone control knob, and a three-position pickup switch selector. It has an EVH-branded Floyd Rose Special tremolo with EVH brand tuners that work really well in keeping the intonation stable.",
                    25.5,
                    "Basswood body, maple top",
                    4.3
                )
        )
        guitarModelArrayList.add(
            Guitars("Jackson JS22-7 Dinky",
                R.drawable.js22,
                200,
                "The EVH Wolfgang WG Standard is available with a basswood body and Maple quilted top. The finish is gloss polyurethane. The Double cutaway body design serves great for higher fret access. The body is shaped in the same distinctive body style that Eddie personally designed. It has a comfortable shallow C-shaped profile bolt-on one-piece maple neck with 22 jumbo frets, and a 12″ to 16″ compound radius. The scale length is 25.5″. There is also a spoke wheel truss rod adjuster located above the 22nd fret. Topping it off is a maple fretboard with dot inlays. It has two EVH branded humbuckers that provide a fierce tone. For the tonal control, there is a master volume and tone control knob, and a three-position pickup switch selector. It has an EVH-branded Floyd Rose Special tremolo with EVH brand tuners that work really well in keeping the intonation stable.",
                26.5,
                "Poplar body",
                3.4
            )
        )
        guitarModelArrayList.add(
            Guitars("Schecter Omen 6",
                R.drawable.omen,
                400,
                "The EVH Wolfgang WG Standard is available with a basswood body and Maple quilted top. The finish is gloss polyurethane. The Double cutaway body design serves great for higher fret access. The body is shaped in the same distinctive body style that Eddie personally designed. It has a comfortable shallow C-shaped profile bolt-on one-piece maple neck with 22 jumbo frets, and a 12″ to 16″ compound radius. The scale length is 25.5″. There is also a spoke wheel truss rod adjuster located above the 22nd fret. Topping it off is a maple fretboard with dot inlays. It has two EVH branded humbuckers that provide a fierce tone. For the tonal control, there is a master volume and tone control knob, and a three-position pickup switch selector. It has an EVH-branded Floyd Rose Special tremolo with EVH brand tuners that work really well in keeping the intonation stable.",
                25.5,
                "Basswood body",
                3.9
            )
        )
        guitarModelArrayList.add(
            Guitars("Schecter Hellraiser C-1",
                R.drawable.hellraiser,
                900,
                "The EVH Wolfgang WG Standard is available with a basswood body and Maple quilted top. The finish is gloss polyurethane. The Double cutaway body design serves great for higher fret access. The body is shaped in the same distinctive body style that Eddie personally designed. It has a comfortable shallow C-shaped profile bolt-on one-piece maple neck with 22 jumbo frets, and a 12″ to 16″ compound radius. The scale length is 25.5″. There is also a spoke wheel truss rod adjuster located above the 22nd fret. Topping it off is a maple fretboard with dot inlays. It has two EVH branded humbuckers that provide a fierce tone. For the tonal control, there is a master volume and tone control knob, and a three-position pickup switch selector. It has an EVH-branded Floyd Rose Special tremolo with EVH brand tuners that work really well in keeping the intonation stable.",
                25.5,
                "Mahogany body",
                4.6
            )
        )
        guitarModelArrayList.add(
            Guitars("Ibanez RG421",
                R.drawable.rg,
                300,
                "It is obvious that this guitar is from the RG series as it has a double-cutaway Superstrat body. RG421 is made of a mahogany body and it comes with a thin and sturdy Wizard III maple neck that has great playability. The neck is known to be fast and that is for a reason. The rosewood fretboard with white dot inlay features 24 jumbo frets. The tonal range of the guitar is no joke, full two octaves to play with. The RG421 is fitted with two of Ibanez’s high-output Quantum humbuckers. The passive pickups are controlled by volume and tone control knobs, as well as a selector switch for the pickups. Interestingly enough they went for a fixed bridge hear instead of a tremolo.",
                25.5,
                "Wizard III maple neck",
                3.7
            )
        )
        guitarModelArrayList.add(
            Guitars("Epiphone SG Special",
                R.drawable.sg,
                300,
                "The EVH Wolfgang WG Standard is available with a basswood body and Maple quilted top. The finish is gloss polyurethane. The Double cutaway body design serves great for higher fret access. The body is shaped in the same distinctive body style that Eddie personally designed. It has a comfortable shallow C-shaped profile bolt-on one-piece maple neck with 22 jumbo frets, and a 12″ to 16″ compound radius. The scale length is 25.5″. There is also a spoke wheel truss rod adjuster located above the 22nd fret. Topping it off is a maple fretboard with dot inlays. It has two EVH branded humbuckers that provide a fierce tone. For the tonal control, there is a master volume and tone control knob, and a three-position pickup switch selector. It has an EVH-branded Floyd Rose Special tremolo with EVH brand tuners that work really well in keeping the intonation stable.",
                24.75,
                "Alder body",
                3.9
            )
        )
        guitarModelArrayList.add(
            Guitars("ESD LTD MH1000",
                R.drawable.esd,
                1000,
                "The ESP LTD MH-1000 is made out of mahogany with a beautifully carved maple top and it features a set-thru 3-piece maple neck with a rosewood fretboard. The thin, U-shaped neck has 24 extra-jumbo frets that are all about comfort and speed. The Set-thru neck construction is and quite innovative. The neck is glued to the body and this makes access to the upper frets and the neck-to-body transition much smoother. The finish is flawless the frets have no sharp edges and are polished to be smooth as possible. The locking nut and Floyd Rose bridge are doing their job perfectly, you can push the intonation to the limits. The guitar sounds perfectly balanced especially when distorted. The guitar is equipped with two Seymour Duncan Pickups, a Hot Rails humbucker in the neck position, and a Pegasus humbucker in the bridge position. These two are controlled by a 3-way pickup switch and there is also a coil tap function that you can activate by pulling the tone knob.",
                25.5,
                "Mahogany body, maple top",
                4.4
            )
        )
        guitarModelArrayList.add(
            Guitars("Jackson JS32 Rhoads",
                R.drawable.js32,
                300,
                "The EVH Wolfgang WG Standard is available with a basswood body and Maple quilted top. The finish is gloss polyurethane. The Double cutaway body design serves great for higher fret access. The body is shaped in the same distinctive body style that Eddie personally designed. It has a comfortable shallow C-shaped profile bolt-on one-piece maple neck with 22 jumbo frets, and a 12″ to 16″ compound radius. The scale length is 25.5″. There is also a spoke wheel truss rod adjuster located above the 22nd fret. Topping it off is a maple fretboard with dot inlays. It has two EVH branded humbuckers that provide a fierce tone. For the tonal control, there is a master volume and tone control knob, and a three-position pickup switch selector. It has an EVH-branded Floyd Rose Special tremolo with EVH brand tuners that work really well in keeping the intonation stable.",
                25.5,
                "Basswood body",
                3.6
            )
        )
        */
        /*
        val db = DBHelper(this, null)
        for(guitar in guitarModelArrayList) {
            val bitmap = BitmapFactory.decodeResource(resources, guitar.imgSrc)
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos)
            val img: ByteArray = bos.toByteArray()
            db.addProduct(guitar.name, guitar.price.toString(),
                img, guitar.description, guitar.body, guitar.scaleLength.toString(),
                guitar.rating.toString())
        }
        */

        guitars = arrayListOf()

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (productSnapshot in dataSnapshot.children) {
                    val guitar = productSnapshot.getValue(Guitars::class.java)
                    guitars.add(guitar!!)
                }
                println(guitars)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                throw databaseError.toException()
            }
        })
        val guitarAdapter = GuitarAdapter(this, guitars)
        { position -> onListItemClick(position) }
        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        guitarRV.layoutManager = linearLayoutManager
        guitarRV.adapter = guitarAdapter

        // Write a message to the database
        /*val database = Firebase.database
        val myRef = database.getReference("guitars")

        myRef.setValue(guitarModelArrayList)*/

    }

    // calling on create option menu
    // layout to inflate our menu file.
    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_items, menu)

        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        if (id == R.id.cart_menu) {
            val intent = Intent(this, Cart::class.java).apply {}
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onListItemClick(position: Int) {
        Toast.makeText(this, guitars[position].name, Toast.LENGTH_SHORT).show()
        val intent = Intent(this, Details::class.java).apply {
            putExtra("guitar", guitars[position] as java.io.Serializable)
        }
        startActivity(intent)
        //Toast.makeText(this, templist[position].getCourse_name(), Toast.LENGTH_SHORT).show()
    }
}
