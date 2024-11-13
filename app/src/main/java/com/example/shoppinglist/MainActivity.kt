package com.example.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shoppinglist.ui.theme.ShoppingListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var darkThemeEnabled by remember { mutableStateOf(false) }
            ShoppingListTheme(darkTheme = darkThemeEnabled) {
                val backgroundColor = MaterialTheme.colorScheme.background
                val textColor = MaterialTheme.colorScheme.onBackground

                Column(
                    modifier = Modifier
                        .background(backgroundColor)
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    ThemeSwitcher(
                        darkThemeEnabled = darkThemeEnabled,
                        onThemeChange = { isEnabled ->
                            darkThemeEnabled = isEnabled
                        },
                        textColor = textColor
                    )
                    // Передаем текстовый цвет в ShoppingListApp
                    ShoppingListApp(textColor = textColor)
                }
            }
        }
    }
}

@Composable
fun ThemeSwitcher(
    darkThemeEnabled: Boolean,
    onThemeChange: (Boolean) -> Unit,
    textColor: androidx.compose.ui.graphics.Color
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = if (darkThemeEnabled) "Dark Theme" else "Light Theme",
            color = textColor
        )
        Spacer(modifier = Modifier.width(8.dp))
        Switch(
            checked = darkThemeEnabled,
            onCheckedChange = onThemeChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.primary
            )
        )
    }
}