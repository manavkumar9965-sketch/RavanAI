package com.ravan.ai.ui.splash

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ravan.ai.ui.theme.RavanBlack
import com.ravan.ai.ui.theme.RavanGrey
import com.ravan.ai.ui.theme.RavanRed
import kotlinx.coroutines.delay

private const val SPLASH_DURATION_MS = 2200L

@Composable
fun SplashScreen(onFinished: () -> Unit) {
    val transition = rememberInfiniteTransition(label = "splash_glow")
    val glow by transition.animateFloat(
        initialValue = 0.35f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1100),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glow_alpha"
    )

    LaunchedEffect(Unit) {
        delay(SPLASH_DURATION_MS)
        onFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(RavanBlack),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "RAVAN AI",
                color = RavanRed,
                fontSize = 42.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 6.sp,
                modifier = Modifier.alpha(glow)
            )
            Spacer16()
            Text(
                text = "Aapka personal assistant",
                color = RavanGrey,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
private fun Spacer16() {
    Box(modifier = Modifier.padding(8.dp))
}
