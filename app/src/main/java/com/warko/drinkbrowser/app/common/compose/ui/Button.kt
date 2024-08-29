package com.warko.drinkbrowser.app.common.compose.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.core.content.res.ResourcesCompat
import com.warko.drinkbrowser.app.common.compose.res.AppTypography
import com.warko.drinkbrowser.app.common.compose.res.Dimen
import com.warko.drinkbrowser.app.common.compose.res.Size

@Composable
fun ButtonContent(
    title: String,
    enable: Boolean = true,
    color: Color? = null,
    @DrawableRes image: Int = ResourcesCompat.ID_NULL,
) {
    if (image != ResourcesCompat.ID_NULL) {
        Image(
            painter = painterResource(id = image),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.width(Dimen.Spacing8))
    }
    Text(
        text = title,
        color = color
            ?: if (enable) AppTypography.Button.color else AppTypography.Button_Disabled.color,
        style = if (enable) AppTypography.Button else AppTypography.Button_Disabled,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun SmallPrimaryButton(
    title: String,
    modifier: Modifier = Modifier,
    enable: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    action: () -> Unit = {},
    content: @Composable RowScope.() -> Unit = { ButtonContent(title, enable) },
) {
    Button(
        modifier = modifier.height(Size.ButtonHeight),
        enabled = enable,
        colors = colors,
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = Dimen.Spacing0,
            pressedElevation = Dimen.Spacing0,
            disabledElevation = Dimen.Spacing0,
            hoveredElevation = Dimen.Spacing0,
            focusedElevation = Dimen.Spacing0,
        ),
        shape = RoundedCornerShape(Dimen.Radius16),
        onClick = action
    ) {
        content()
    }
}

@Composable
fun PrimaryButton(
    state: ButtonState,
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    content: @Composable RowScope.() -> Unit = { ButtonContent(state.title, state.enable) },
) {
    SmallPrimaryButton(
        title = state.title,
        modifier = modifier.fillMaxWidth(),
        colors = colors,
        enable = state.enable,
        action = state::action,
        content = content
    )
}

@Stable
class ButtonState(
    val title: String = "",
    defaultShow: Boolean = false,
    defaultEnable: Boolean = false,
    defaultProgress: Boolean = false,
    payloadAction: (Any?) -> Unit = { },
) : ProgressAnimatedContent {

    private var _show by mutableStateOf(defaultShow)
    private var _enable by mutableStateOf(defaultEnable)
    private var _progress by mutableStateOf(defaultProgress)
    private var _payload by mutableStateOf<Any?>(null)
    var payloadAction by mutableStateOf(payloadAction)

    var show: Boolean
        get() = _show
        internal set(value) {
            if (value != _show) {
                _show = value
            }
        }

    var enable: Boolean
        get() = _enable
        internal set(value) {
            if (value != _enable) {
                _enable = value
            }
        }

    var payload: Any?
        get() = _payload
        internal set(value) {
            if (value != _payload) {
                _payload = value
            }
        }

    override var showProgress: Boolean
        get() = _progress
        set(value) {
            if (_progress != value) _progress = value
        }

    fun action() {
        payloadAction(payload)
    }
}