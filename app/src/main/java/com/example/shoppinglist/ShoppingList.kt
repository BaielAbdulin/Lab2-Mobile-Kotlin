package com.example.shoppinglist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color

@Composable
fun ShoppingListApp(textColor: Color) {
    var sItems by remember { mutableStateOf(listOf<ShoppingItem>()) }
    var showDialog by remember { mutableStateOf(false) }
    var editDialog by remember { mutableStateOf(false) }
    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("1") }
    var editingItemId by remember { mutableStateOf<Int?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(sItems) { item ->
                ShoppingListItem(
                    item = item,
                    onEditClick = {
                        editingItemId = item.id
                        itemName = item.name
                        itemQuantity = item.quantity.toString()
                        editDialog = true
                    },
                    onDeleteClick = {
                        sItems = sItems.filter { it.id != item.id }
                    },
                    textColor = textColor // Passing textColor to ShoppingListItem
                )
            }
        }

        Button(
            onClick = { showDialog = true },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Add Item", color = textColor) // Using textColor here
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = {
                    showDialog = false
                    itemName = ""
                    itemQuantity = "1"
                },
                confirmButton = {},
                title = { Text("Add Shopping Item", color = textColor) },
                text = {
                    Column {
                        OutlinedTextField(
                            value = itemName,
                            onValueChange = { itemName = it },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            label = { Text("Item Name", color = textColor) }
                        )
                        OutlinedTextField(
                            value = itemQuantity,
                            onValueChange = { itemQuantity = it },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            label = { Text("Quantity", color = textColor) }
                        )

                        Row {
                            Button(
                                onClick = {
                                    showDialog = false
                                    itemName = ""
                                    itemQuantity = "1"
                                }
                            ) {
                                Text(text = "Cancel", color = textColor)
                            }

                            Spacer(modifier = Modifier.width(100.dp))

                            Button(
                                onClick = {
                                    if (itemName.isNotBlank() && itemQuantity.isNotBlank()) {
                                        sItems = sItems + ShoppingItem(
                                            id = (sItems.maxOfOrNull { it.id } ?: 0) + 1,
                                            name = itemName,
                                            quantity = itemQuantity.toIntOrNull() ?: 1
                                        )
                                    }
                                    showDialog = false
                                    itemName = ""
                                    itemQuantity = "1"
                                }
                            ) {
                                Text(text = "Add", color = textColor)
                            }
                        }
                    }
                }
            )
        }

        if (editDialog) {
            AlertDialog(
                onDismissRequest = {
                    editDialog = false
                    itemName = ""
                    itemQuantity = "1"
                    editingItemId = null
                },
                confirmButton = {},
                title = { Text("Edit Shopping Item", color = textColor) },
                text = {
                    Column {
                        OutlinedTextField(
                            value = itemName,
                            onValueChange = { itemName = it },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            label = { Text("Item Name", color = textColor) }
                        )
                        OutlinedTextField(
                            value = itemQuantity,
                            onValueChange = { itemQuantity = it },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            label = { Text("Quantity", color = textColor) }
                        )

                        Row {
                            Button(
                                onClick = {
                                    editDialog = false
                                    itemName = ""
                                    itemQuantity = "1"
                                    editingItemId = null
                                }
                            ) {
                                Text(text = "Cancel", color = textColor)
                            }

                            Spacer(modifier = Modifier.width(100.dp))

                            Button(
                                onClick = {
                                    if (editingItemId != null && itemName.isNotBlank() && itemQuantity.isNotBlank()) {
                                        sItems = sItems.map {
                                            if (it.id == editingItemId) {
                                                it.copy(
                                                    name = itemName,
                                                    quantity = itemQuantity.toIntOrNull() ?: it.quantity
                                                )
                                            } else it
                                        }
                                    }
                                    editDialog = false
                                    itemName = ""
                                    itemQuantity = "1"
                                    editingItemId = null
                                }
                            ) {
                                Text(text = "Save", color = textColor)
                            }
                        }
                    }
                }
            )
        }
    }
}