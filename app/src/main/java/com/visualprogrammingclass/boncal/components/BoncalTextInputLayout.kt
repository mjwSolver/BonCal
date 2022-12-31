package com.visualprogrammingclass.boncal.components

import android.inputmethodservice.Keyboard
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.visualprogrammingclass.boncal.R.drawable.ic_baseline_visibility_24
import com.visualprogrammingclass.boncal.R.drawable.ic_baseline_visibility_off_24
import com.visualprogrammingclass.boncal.views.UseColor
import com.visualprogrammingclass.boncal.views.ui.theme.*

@Composable
// Not done
fun BoncalTextInputLayout(
    label:String,
//    text: State<String>,
    text: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
//    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    focusManager: FocusManager,
    leadIcon: ImageVector = Icons.Default.Email,
    trailingIcon: ImageVector = Icons.Default.Close,
    setText: (String) -> Unit,
    onClick: () -> Unit
){

    OutlinedTextField(
        modifier = Modifier
            .padding(5.dp)
            .focusable(),
        shape = RoundedCornerShape(24.dp),

        value = text, onValueChange = setText,

        label = { Text(
            text = label,
            color = foregroundColor()
        )},
        placeholder = { Text(text = label)},

        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = foregroundColor(),
            backgroundColor = backgroundColor()
        ),

        leadingIcon = { Icon(
            imageVector = leadIcon,
            contentDescription = "leadIcon",
            tint = foregroundColor(),
            modifier = Modifier.offset(x = 5.dp),
        ) },
        trailingIcon = {
            IconButton(onClick = onClick ) {
                Icon(
                    imageVector = trailingIcon,
                    contentDescription = "trailIcon",
                    tint = foregroundColor(),
                    modifier = Modifier.offset(x = (-5).dp))
            }
       },
        
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
        ),
        keyboardActions = KeyboardActions(
            onNext = {focusManager.moveFocus(FocusDirection.Down)}
        ),

        maxLines = 1,
        singleLine = true,

    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BoncalPasswordTextInputLayout(
    password: String,
    setPassword: (String) -> Unit,
    focusManager: FocusManager
){

    var passwordVisibility by rememberSaveable { mutableStateOf(false) }
    val label = "Password"

    val trailIcon = if(passwordVisibility) painterResource(id = ic_baseline_visibility_24)
    else painterResource(id = ic_baseline_visibility_off_24)

    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        modifier = Modifier
            .padding(5.dp)
            .focusable(),
        shape = RoundedCornerShape(24.dp),

        value = password, onValueChange = setPassword,

        placeholder = { Text(text = label) },
        label = { Text( text = label,color = foregroundColor()) },

        visualTransformation = if(passwordVisibility) VisualTransformation.None
            else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {keyboardController?.hide()}
        ),

        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = foregroundColor(),
            backgroundColor = backgroundColor(),
        ),

        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Lock,
                contentDescription = "lock",
                modifier = Modifier.offset(x = 5.dp),
                tint = foregroundColor())
        },
        trailingIcon = {
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(
                    painter = trailIcon,
                    contentDescription = "trailIcon",
                    tint = foregroundColor(),
                    modifier = Modifier.offset(x = (-5).dp))

            }
        }
    )

}

@Composable
@Preview
fun previewJustTheTextInput() {
    BonCalTheme() {
        Surface() {
            val theFocusManager = LocalFocusManager.current
            val (dummyText,setDummyText) = remember { mutableStateOf("") }
                BoncalTextInputLayout(
                    label = "Email",
                    text = dummyText,
                    setText = setDummyText,
                    focusManager = theFocusManager) {}
        }
    }
}

@Composable
@Preview
fun previewThePasswordInput() {
    BonCalTheme() {
        Surface() {
            val theFocusManager = LocalFocusManager.current
            val (dummyText,setDummyText) = remember { mutableStateOf("") }
            BoncalPasswordTextInputLayout(
                password = dummyText,
                setPassword = setDummyText,
                focusManager = theFocusManager
            )

        }
    }
}