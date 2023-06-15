package com.ebunnygroup.epara.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.ebunnygroup.epara.navigation.Screens
import com.ebunnygroup.epara.ui.theme.EParaTheme


@Composable
fun ScreenContent(screenName: String, previousScreen: String?, onNextScreenClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Screen: $screenName")
        if (previousScreen != null) {
            Text(text = "Previous screen: $previousScreen")
        }
        Button(onClick = onNextScreenClick) {
            Text(text = "Go to next screen")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldComponent(
    labelText: String,
    icon: ImageVector,
    onTextChanged: (String) -> Unit,
    errorStatus: Boolean = false
) {

    val textValue = remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        label = {
            Text(text = labelText)
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextChanged(it)
        },
        leadingIcon = {
            Icon(icon, contentDescription = "")
        },
        isError = errorStatus
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecretTextFieldComponent(
    labelText: String,
    icon: ImageVector,
    onTextSelected: (String) -> Unit,
    errorStatus: Boolean = false
) {

    val localFocusManager = LocalFocusManager.current
    val password = remember {
        mutableStateOf("")
    }

    val passwordVisible = remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        label = {
            Text(text = labelText)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        singleLine = true,
        keyboardActions = KeyboardActions {
            localFocusManager.clearFocus()
        },
        maxLines = 1,
        value = password.value,
        onValueChange = {
            password.value = it
            onTextSelected(it)
        },
        leadingIcon = {
            Icon(icon, contentDescription = "")
        },
        trailingIcon = {

            val iconImage = if (passwordVisible.value) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }

            val description = if (passwordVisible.value) {
                "hide"
            } else {
                "show"
            }

            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(imageVector = iconImage, contentDescription = description)
            }

        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        isError = errorStatus
    )
}

@Composable
fun ButtonComponent(
    text: String,
    onButtonClicked: () -> Unit,
    isEnabled: Boolean = false
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        onClick = onButtonClicked,
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(12.dp),
        enabled = isEnabled
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
fun ClickableTextComponent(
    text: String,
    clickable_text: String,
    onTextSelected: () -> Unit
) {
    val annotatedString = buildAnnotatedString {
        append(text)
        append(" ")
        withStyle(
            style = SpanStyle(
                textDecoration = TextDecoration.Underline
            )
        ) {
            pushStringAnnotation(tag = clickable_text, annotation = clickable_text)
            append(clickable_text)
        }
    }

    ClickableText(
        text = annotatedString,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = MaterialTheme.typography.labelMedium,
        onClick = { offset ->

            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->
                    if (span.item == clickable_text) {
                        onTextSelected()
                    }
                }

        },
    )
}

@Composable
fun AppWatermarkComponent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        Alignment.BottomCenter
    ) {
        Text(
            text = "ePara App",
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun BottomNavigationBarComponent(navController: NavController, item: Screens) {
    IconButton(
        onClick = {
            navController.navigate(item.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = item.label
            )
            Text(
                text = item.label,
                textAlign = TextAlign.Center
            )
        }
    }

}

@Composable
@Preview
fun preview() {
    EParaTheme() {
//        AppWatermarkComponent()
    }
}
