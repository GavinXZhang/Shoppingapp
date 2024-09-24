package com.example.shoppingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shoppingapp.ui.theme.ShoppingappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingappTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ShoppingList(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ShoppingList(modifier: Modifier = Modifier) {
    // Sample data
    val items = listOf(
        ShoppingItem("Apples", "2 kg"),
        ShoppingItem("Bananas", "6 pcs"),
        ShoppingItem("Milk", "1 L")
    )

    // State for selected items
    var selectedItems by remember { mutableStateOf(setOf<ShoppingItem>()) }

    // Column for the UI components
    Column(modifier = modifier.padding(16.dp)) {
        // Button for action (e.g., Checkout)
        Button(onClick = { /* Perform checkout action */ }, modifier = Modifier.fillMaxWidth()) {
            Text("Checkout")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // LazyColumn for the list of items
        LazyColumn {
            items(items.size) { index ->
                ShoppingListItem(
                    item = items[index],
                    isSelected = selectedItems.contains(items[index]),
                    onCheckedChange = { isChecked ->
                        selectedItems = if (isChecked) {
                            selectedItems + items[index]
                        } else {
                            selectedItems - items[index]
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun ShoppingListItem(
    item: ShoppingItem,
    isSelected: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "${item.name} - ${item.quantity}")
        Checkbox(checked = isSelected, onCheckedChange = onCheckedChange)
    }
}

// Data class for Shopping Item
data class ShoppingItem(val name: String, val quantity: String)

@Preview(showBackground = true)
@Composable
fun ShoppingListPreview() {
    ShoppingappTheme {
        ShoppingList()
    }
}