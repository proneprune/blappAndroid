package com.example.blodpool

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.blodpool.ui.theme.BlodpoolTheme

import org.opencv.android.OpenCVLoader
import org.opencv.android.Utils
import org.opencv.core.Core.inRange
import org.opencv.core.Mat
import org.opencv.core.Scalar
import org.opencv.imgproc.Imgproc
import org.opencv.imgproc.Imgproc.COLOR_RGB2HSV
import org.opencv.core.Core
import org.opencv.core.CvType
import com.google.ar.core.Anchor
import com.google.ar.core.HitResult
import com.google.ar.core.Plane
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import android.annotation.SuppressLint
import android.widget.RelativeLayout

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.Button





class MainActivity : AppCompatActivity() {
    private var arFragment: ArFragment? = null
    private var bloodPoolDistance: Float? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println("hejehejehejehj")

        // Initialize OpenCVLoader
        OpenCVLoader.initDebug()

        // Initialize ARFragment
       // arFragment = supportFragmentManager.findFragmentById(R.id.ar_fragment) as ArFragment

        val button = findViewById<Button>(R.id.btn)
        val intent = Intent("android.media.action.IMAGE_CAPTURE")

        button.setOnClickListener {
            // Start camera capture
            startActivityForResult(intent, 0)
        }

        // Set up ARCore to measure distance
        //arFragment?.setOnTapArPlaneListener { hitResult: HitResult, plane: Plane, motionEvent ->
            // Get the distance to the hit point
           // bloodPoolDistance = hitResult.distance

            // Create an anchor at the tapped point
            //val anchor = hitResult.createAnchor()

            // Get the pose of the anchor; this is the position in space where the tap occurred
          //  val anchorPose = anchor.pose
         //   val anchorNode = AnchorNode(anchor)
       //     anchorNode.setParent(arFragment?.arSceneView?.scene)


            // Get the camera pose
      //      val cameraPose = arFragment?.arSceneView?.arFrame?.camera?.pose

            // Calculate the distance between the camera and the anchor
        //    val dx = anchorPose.tx() - (cameraPose?.tx() ?: 0f)
        //    val dy = anchorPose.ty() - (cameraPose?.ty() ?: 0f)
        //    val dz = anchorPose.tz() - (cameraPose?.tz() ?: 0f)


            // Compute the Euclidean distance
       //     val distance = Math.sqrt((dx * dx + dy * dy + dz * dz).toDouble())

            // Display the distance
       //     runOnUiThread {
       //         Toast.makeText(
        //            this, "Distance to object: ${"%.2f".format(distance)} meters",
       //             Toast.LENGTH_LONG
        //        ).show()






           // Toast.makeText(
            //    applicationContext,
           //     "Distance to blood pool: $bloodPoolDistance meters",
            //    Toast.LENGTH_SHORT
           // ).show()
        }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Declaring and initializing the
        // UI elements from the layout file


        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
           // val image = findViewById<ImageView>(R.id.imageView)

            setContentView(R.layout.captured_image_view)

            val mRelativeLayout = findViewById<RelativeLayout>(R.id.relative_layout_1)

            val mTextViewX = findViewById<TextView>(R.id.text_view_1)
            val mTextViewY = findViewById<TextView>(R.id.text_view_2)
            val image = findViewById<ImageView>(R.id.captured_image)


            val bitmap = (data.extras?.get("data")) as Bitmap




           // val mat = Mat()
           // Utils.bitmapToMat(bitmap, mat)

          //  val hsvMat = Mat()
          //  Imgproc.cvtColor(mat, hsvMat, COLOR_RGB2HSV)

         //   val low_red = Scalar(0.0, 50.0, 50.0)
         //   val high_red = Scalar(10.0, 255.0, 255.0)
          //  inRange(hsvMat, low_red, high_red, hsvMat)

         //   val redHighlight = bitmap.copy(bitmap.config, true)
        //    Utils.matToBitmap(hsvMat, redHighlight)



            // Display the image with blood pool highlight
        //    image.setImageBitmap(redHighlight)


            image.setImageBitmap(bitmap)

            // When relative layout is touched
            mRelativeLayout.setOnTouchListener {_,motionEvent->


                val imageWidth = image.drawable.intrinsicWidth
                val imageHeight = image.drawable.intrinsicHeight

                // X and Y values are fetched relative to the view (mRelativeLayout)
                val mX = motionEvent.x
                val mY = motionEvent.y

                // X and Y values are
                // displayed in the TextView
               // mTextViewX.text = "X: $mX"
               // mTextViewY.text = "Y: $mY"

                // Calculate the corresponding coordinates relative to the original image
                val imageX = (mX / image.width.toFloat() * imageWidth).toInt()
                val imageY = (mY / image.height.toFloat() * imageHeight).toInt()

                println("X: $imageX")
                println("Y: $imageY")

                // Display the coordinates relative to the original image
                mTextViewX.text = "X: $imageX"
                mTextViewY.text = "Y: $imageY"





                true



            }


            //  val maat = Mat()
           // Utils.bitmapToMat(redHighlight, maat)
           // val numObjectPixels = Core.countNonZero(maat)
          //  println(numObjectPixels)

            // If bloodPoolDistance is not null, save the distance
           // bloodPoolDistance?.let { distance ->
                // Save the distance to a variable, database, or wherever you need it
                // For demonstration purposes, let's just print it
             //   println("Distance to blood pool: $distance meters")
         //   }
        }


    }


    override fun onResume() {
        super.onResume()
        arFragment?.arSceneView?.resume()
    }

    override fun onPause() {
        super.onPause()
        arFragment?.arSceneView?.pause()
    }
}




@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BlodpoolTheme {
        Greeting("Android")
    }
}

