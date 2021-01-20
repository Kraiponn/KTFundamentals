package com.codemakerlab.ktfundamentals.ui


import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.codemakerlab.ktfundamentals.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class DialogSpinnerActivity : AppCompatActivity() {
    private lateinit var imgReview: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var btnShowDialog: Button
    private lateinit var btnCall: Button
    private lateinit var tvResult: TextView

    private val EMBEDDED_COURSES =
        arrayOf("MCS 51", "PIC", "AVR", "ARM", "Arduino", "Node MCU", "Rasberry PI")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog_spinner)

        initInstance()
    }

    private fun initInstance() {
        imgReview = findViewById<ImageView>(R.id.imgReview)
        tvTitle = findViewById(R.id.tvTitle)
        btnShowDialog = findViewById(R.id.button_ShowDialog)
        btnCall = findViewById(R.id.button_Call)

        imgReview.setOnClickListener {
            Intent(Intent.ACTION_GET_CONTENT).also {
                it.type = "image/*"
                startActivityForResult(it, 0)
            }
//            Log.d("ImageViewEvent", "Image clicked")
        }

        btnShowDialog.setOnClickListener {
            showMaterialDialog()
        }
    }

    private fun showMaterialDialog() {
        MaterialAlertDialogBuilder(this@DialogSpinnerActivity)
            .setTitle("Please choose your choice")
            .setIcon(R.drawable.ic_cart)
            .setSingleChoiceItems(
                EMBEDDED_COURSES,
                0,
                DialogInterface.OnClickListener { _, which ->
                    showSnackBar("${EMBEDDED_COURSES[which]} selected")
                })
            .setPositiveButton("OK", DialogInterface.OnClickListener { _, _ ->
                showSnackBar("You choose OK")
            })
            .setNeutralButton("Later") { _: DialogInterface, _: Int ->
                showSnackBar("You choose later")
            }
            .show()

//        val mDialog = AlertDialog.Builder(this)
//            .setTitle("Opp")
//            .setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int -> }

    }

    private fun showSnackBar(msg: String) {
        Snackbar.make(btnShowDialog, msg, Snackbar.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && requestCode == 0) {
            var uri = data?.data
            imgReview.setImageURI(uri)
        }
    }
}