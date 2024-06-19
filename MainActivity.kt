package com.example.samplecomplaints

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.samplecomplaints.ui.theme.SampleComplaintsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleComplaintsTheme {
                // A surface container using the 'background' color from the theme
                setContent() { 
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ){
                        Text(
                            text = "Complaints Received",
                            style = MaterialTheme.typography.displayMedium,
                            modifier = Modifier.padding(16.dp)
                        )
                        
                        CompalintsList(compalints = sampleData)
                    }
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                    
                ) {
                    ComplaintsApp()
                }
            }
        }
    }
}

//data class
data class Complaint(
    val personalName: String,
    val complaintText:  String,
    val resolved: Boolean = false
)

//Creating the list
val sampleData = listOf(
    Complaint("John Doe","Slow response from customer support",false),
    Complaint("Alice Smith","Product delivery was damaged",true),
    Complaint("Emma Brown","Incorrect billing amount charged",false),
    Complaint("David Johnson","Website login not working",true),
    Complaint("Sarah Wilson","Unsatisfactory service experience",false)
)

@Composable
fun ComplaintsApp( ) {
    CompalintsList(compalints = sampleData)
}

@Composable
fun CompalintsList(compalints: List<Complaint>) {
    LazyColumn{
        items(compalints){complaint ->
            ComplaintItem(complaint = complaint)
        }
    }
}

@Composable
fun ComplaintItem( complaint : Complaint) {
  Column(
      modifier =  Modifier.padding(16.dp)
  ){
      Text(
          text = complaint.personalName,
          style = MaterialTheme.typography.bodyMedium,
          fontWeight = FontWeight.Bold
      )

      Text(
          text = complaint.complaintText,
          style = MaterialTheme.typography.bodyMedium,
          modifier =  Modifier.padding(top = 4.dp)
      )

      Text(
          text = if(complaint.resolved) "Resolved" else "Unresolved",
          style = MaterialTheme.typography.displaySmall,
          fontWeight = FontWeight.Bold,
          modifier =  Modifier.padding(top = 4.dp)
      )
  }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SampleComplaintsTheme {
        CompalintsList(sampleData)
    }
}

