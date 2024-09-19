package com.warko.drinkbrowser.app.common.compose.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.warko.drinkbrowser.R
import com.warko.drinkbrowser.app.common.compose.res.AppTypography
import com.warko.drinkbrowser.app.common.compose.res.Dimen
import com.warko.drinkbrowser.app.common.compose.res.DrinkBrowserTheme
import com.warko.drinkbrowser.app.common.compose.res.Palette
import com.warko.drinkbrowser.app.common.compose.res.Size

@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    hint: String = stringResource(R.string.search),
    isEnabled: Boolean = true,
    height: Dp = Size.SearchFieldHeight,
    elevation: Dp = Dimen.Elevation2,
    shape: Shape = CircleShape,
    backgroundColor: Color = Palette.White,
    onSearchClicked: () -> Unit = {},
    onTextChange: (String) -> Unit = {},
) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = modifier
            .height(height)
            .fillMaxWidth()
            .shadow(elevation = elevation, shape = shape),
        shape = shape,
        value = text,
        onValueChange = {
            text = it
            onTextChange(text)
        },
        enabled = isEnabled,
        textStyle = AppTypography.TextMdMedium,
        placeholder = {
            Text(
                text = hint,
                color = Color.Gray.copy(alpha = 0.5f),
                fontSize = Dimen.TextSize16,
            )
        },
        trailingIcon = {
            if (text.isNotEmpty()) {
                Icon(
                    modifier = Modifier
                        .padding(Dimen.Spacing10)
                        .clickable { text = "" },
                    painter = painterResource(id = R.drawable.ic_action_close),
                    contentDescription = stringResource(R.string.search),
                    tint = Palette.Black,
                )
            } else {
                Icon(
                    modifier = Modifier
                        .padding(Dimen.Spacing10),
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = stringResource(R.string.search),
                    tint = Palette.Black,
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(onSearch = { onSearchClicked() }),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Palette.Transparent,
            unfocusedBorderColor = Palette.Transparent,
            focusedContainerColor = backgroundColor,
            unfocusedContainerColor = backgroundColor,
        )
    )
}

@Preview
@Composable
private fun SearchPreview() {
    DrinkBrowserTheme {
        SearchField(
            hint = "Search",
            modifier = Modifier.fillMaxWidth(),
            onTextChange = {}
        )
    }
}